import { useState, useEffect } from 'react'
import { Alert, FlatList, KeyboardAvoidingView, Modal, Platform, Text, TouchableOpacity, TouchableWithoutFeedback, View } from 'react-native'
import CheckBox from 'react-native-check-box'
import { useRecoilValue } from 'recoil'
import * as Color from '../../components/Colors/colors'
import { Icon } from '../../components/Icons/Icons'
import { SingleLineInput } from '../../components/Input/SingleLineInput'
import { getReplyListFetch, getWriteReplyFetch } from '../../util/FetchUtil'
import { UserInfoState } from '../../util/RecoilUtil/Atoms'
import { getTokens } from '../../util/TokenUtil'
import AnswerCommentCard from './AnswerCommentCard'
import EditReply from './EditReply'

const AnswerCommentModal = (props) => {
  const comment = props.comment
  const postId = props.postId
  const user = useRecoilValue(UserInfoState)

  const [editReply, setEditReply] = useState(false)
  const [reply, setReply] = useState(null)

  const [inputReply, setInputReply] = useState('')
  const [isChecked, setIsChecked] = useState(false)

  const [data, setData] = useState([])
  const [lastReplyId, setLastReplyId] = useState('')

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  const getWriteReplyData = async () => {
    try {
      const response = await getWriteReplyFetch(accessToken, refreshToken, postId, comment.commentId, inputReply, isChecked)
      const newData = await response.json()
      console.log(newData)
      if (newData.dataHeader.successCode == 0) {
        Alert.alert('답글이 작성되었습니다.', props.setAnswerCommentModal(false))
      } else {
        Alert.alert(newData.dataHeader.resultMessage)
      }
    } catch (e) {
      console.log(e)
    }
  }

  const getReplyListData = async () => {
    try {
      const response = await getReplyListFetch(accessToken, refreshToken, comment.commentId, postId, lastReplyId)
      const newData = await response.json()
      return newData
    } catch (e) {
      console.log(e)
      return []
    }
  }
  const loadData = async () => {
    const newData = await getReplyListData()
    if (newData.dataHeader.successCode == 0) {
      if (newData.dataBody.length > 0) {
        setLastReplyId(newData.dataBody[newData.dataBody.length - 1].replyId.toString())
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
          props.setAnswerCommentModal(false)
        }}
        style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}
      >
        <TouchableWithoutFeedback>
          <View style={{ flex: 1, width: '100%', marginTop: 80, backgroundColor: Color.WHITE, borderTopLeftRadius: 20, borderTopRightRadius: 20 }}>
            <TouchableOpacity
              style={{ position: 'absolute', top: 20, right: 20, zIndex: 1 }}
              onPress={() => {
                props.setAnswerCommentModal(false)
              }}
            >
              <Icon
                name="close"
                size={30}
                color={Color.WHITE}
              />
            </TouchableOpacity>
            <View style={{ width: '100%', height: 70, backgroundColor: Color.BLUE, borderTopLeftRadius: 20, borderTopRightRadius: 20, alignItems: 'center', justifyContent: 'center' }}>
              <Text style={{ color: Color.WHITE, fontSize: 20, fontWeight: 'bold' }}>답글</Text>
            </View>
            <KeyboardAvoidingView
              style={{ flex: 1, width: '100%', backgroundColor: Color.WHITE, borderTopLeftRadius: 20, borderTopRightRadius: 20 }}
              behavior={Platform.OS === 'ios' ? 'padding' : null}
              keyboardVerticalOffset={Platform.OS === 'ios' ? 70 : 0} // iOS에서 조정이 필요할 수 있습니다.
            >
              <FlatList
                data={data}
                renderItem={({ item }) => {
                  return (
                    <View onStartShouldSetResponder={() => true}>
                      <AnswerCommentCard
                        setReply={setReply}
                        setEditReply={setEditReply}
                        setAnswerCommentModal={props.setAnswerCommentModal}
                        answerComment={item}
                        postId={postId}
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
                        placeholder="답글을 입력하세요."
                        onChangeText={(text) => {
                          setInputReply(text)
                        }}
                      />
                    </View>
                    <View style={{ width: '5%' }}>
                      <TouchableOpacity
                        onPress={() => {
                          getWriteReplyData()
                        }}
                      >
                        <Icon
                          size={16}
                          color={Color.BLUE}
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
          animationType="fade"
          transparent={true}
          visible={editReply}
          onBackdropPress={() => setEditReply(false)}
        >
          <EditReply
            reply={reply}
            setEditReply={setEditReply}
            postId={postId}
            setAnswerCommentModal={props.setAnswerCommentModal}
          />
        </Modal>
      </TouchableOpacity>
    </>
  )
}
export default AnswerCommentModal
