import { useState, useEffect } from 'react'
import { Alert, FlatList, KeyboardAvoidingView, Modal, Platform, Text, TouchableOpacity, TouchableWithoutFeedback, View } from 'react-native'
import CheckBox from 'react-native-check-box'
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view'
import { useRecoilValue } from 'recoil'
import * as Color from '../../components/Colors/colors'
import { Icon } from '../../components/Icons/Icons'
import { SingleLineInput } from '../../components/Input/SingleLineInput'
import { getCommentListFetch, getWriteCommentFetch } from '../../util/FetchUtil'
import { UserInfoState } from '../../util/RecoilUtil/Atoms'
import { getTokens } from '../../util/TokenUtil'
import AnswerCommentModal from './AnswerCommentModal'
import CommentCard from './CommentCard'
import EditComment from './EditComment'

const CommentModal = (props) => {
  const content = props.content
  const [isChecked, setIsChecked] = useState(false)

  const [answerCommentModal, setAnswerCommentModal] = useState(false)
  const [inputComment, setInputComment] = useState('')

  const [data, setData] = useState([])
  const [lastCommentId, setLastCommentId] = useState('')

  const [editComment, setEditComment] = useState(false)
  const [comment, setComment] = useState(null)

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  const getCommentListData = async () => {
    try {
      // userId, postId, lastCommentId
      const response = await getCommentListFetch(accessToken, refreshToken, content.postId, lastCommentId)
      const newData = await response.json()
      return newData
      // return tempData
    } catch (e) {
      console.log(e)
      return []
    }
  }

  const getWriteCommentData = async () => {
    try {
      const response = await getWriteCommentFetch(accessToken, refreshToken, content.postId, inputComment, isChecked)
      const newData = await response.json()
      if (newData.dataHeader.successCode == 0) {
        Alert.alert('댓글이 작성되었습니다.', props.setReload(!props.reload), props.setShowComment(false))
      } else {
        Alert.alert(newData.dataHeader.resultMessage)
      }
    } catch (e) {
      console.log(e)
    }
  }

  const loadData = async () => {
    const newData = await getCommentListData()
    if (newData.dataHeader.successCode == 0) {
      if (newData.dataBody.length > 0) {
        setLastCommentId(newData.dataBody[newData.dataBody.length - 1].commentId.toString())
        setData((prevData) => [...prevData, ...newData.dataBody])
      } else {
        if (data.length > 10) Alert.alert('마지막 댓글입니다.')
      }
    }
  }

  useEffect(() => {
    if (!isTokenGet) {
      getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
    } else {
      loadData()
    }
  }, [isTokenGet])

  const handleEndReached = () => {
    loadData()
  }

  return (
    <>
      <TouchableOpacity
        onPress={() => {
          props.setShowComment(false)
        }}
        style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}
      >
        <TouchableWithoutFeedback>
          <View style={{ flex: 1, width: '100%', marginTop: 70, backgroundColor: Color.WHITE, borderTopLeftRadius: 20, borderTopRightRadius: 20 }}>
            <TouchableOpacity
              style={{ position: 'absolute', top: 20, right: 20, zIndex: 1 }}
              onPress={() => {
                props.setShowComment(false)
              }}
            >
              <Icon
                name="close"
                size={30}
                color={Color.WHITE}
              />
            </TouchableOpacity>
            <View style={{ width: '100%', height: 70, backgroundColor: Color.DARK_BLUE, borderTopLeftRadius: 20, borderTopRightRadius: 20, alignItems: 'center', justifyContent: 'center' }}>
              <Text style={{ color: Color.WHITE, fontSize: 20, fontWeight: 'bold' }}>댓글</Text>
            </View>
            <KeyboardAvoidingView
              style={{ flex: 1, width: '100%', backgroundColor: Color.WHITE, borderTopLeftRadius: 20, borderTopRightRadius: 20 }}
              behavior={Platform.OS === 'ios' ? 'padding' : null}
              keyboardVerticalOffset={Platform.OS === 'ios' ? 60 : 0}
            >
              <FlatList
                data={data}
                renderItem={({ item }) => {
                  return (
                    <View onStartShouldSetResponder={() => true}>
                      <CommentCard
                        comment={item}
                        setShowComment={props.setShowComment}
                        setAnswerCommentModal={setAnswerCommentModal}
                        setEditComment={setEditComment}
                        setComment={setComment}
                      />
                    </View>
                  )
                }}
                onEndReached={handleEndReached}
                onEndReachedThreshold={0.1}
              />
              <View style={{ height: 80, borderTopWidth: 1, borderColor: Color.BLACK }}>
                <View style={{ alignItems: 'center' }}>
                  <View style={{ flexDirection: 'row', borderColor: Color.GRAY, borderWidth: 1, width: '95%', height: 50, borderRadius: 10, marginVertical: 5, alignItems: 'center', justifyContent: 'space-around' }}>
                    <View style={{ flexDirection: 'row', justifyContent: 'center', alignItems: 'center', width: '15%' }}>
                      <CheckBox
                        onClick={() => {
                          setIsChecked(isChecked ? false : true)
                        }}
                        isChecked={isChecked}
                      />
                      <Text style={{ marginHorizontal: 5 }}>익명</Text>
                    </View>
                    <View style={{ width: '65%' }}>
                      <SingleLineInput
                        placeholder="댓글을 입력하세요."
                        onChangeText={(text) => {
                          setInputComment(text)
                        }}
                      />
                    </View>
                    <View style={{ width: '5%' }}>
                      <TouchableOpacity
                        onPress={() => {
                          getWriteCommentData()
                        }}
                      >
                        <Icon
                          size={18}
                          color={Color.DARK_BLUE}
                          name="send"
                        />
                      </TouchableOpacity>
                    </View>
                  </View>
                </View>
              </View>
            </KeyboardAvoidingView>
          </View>
        </TouchableWithoutFeedback>

        <Modal
          animationType="slide"
          transparent={true}
          visible={answerCommentModal}
          onBackdropPress={() => setAnswerCommentModal(false)}
          onRequestClose={() => {
            setAnswerCommentModal(false)
          }}
        >
          <AnswerCommentModal
            postId={content.postId}
            comment={comment}
            setAnswerCommentModal={setAnswerCommentModal}
          />
        </Modal>

        <Modal
          animationType="fade"
          transparent={true}
          visible={editComment}
          onBackdropPress={() => setEditComment(false)}
        >
          <EditComment
            comment={comment}
            setEditComment={setEditComment}
            setShowComment={props.setShowComment}
            postId={content.postId}
          />
        </Modal>
      </TouchableOpacity>
    </>
  )
}
export default CommentModal
