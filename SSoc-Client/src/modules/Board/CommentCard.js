import { useState, useEffect } from 'react'
import { View, Text, TouchableOpacity, Alert } from 'react-native'
import { Modal } from 'react-native-paper'
import { useRecoilValue } from 'recoil'
import * as Color from '../../components/Colors/colors'
import { getCommentDeleteFetch } from '../../util/FetchUtil'
import { UserInfoState } from '../../util/RecoilUtil/Atoms'
import { getTokens } from '../../util/TokenUtil'
import EditComment from './EditComment'

const CommentCard = (props) => {
  const comment = props.comment
  const user = useRecoilValue(UserInfoState)
  const setShowComment = props.setShowComment

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  const getCommentDeleteData = async () => {
    try {
      const response = await getCommentDeleteFetch(accessToken, refreshToken, postId, comment.commentId)
      const newData = await response.json()
      console.log(newData)
      if (newData.dataHeader.successCode == 0) {
        Alert.alert('댓글이 삭제되었습니다.', setShowComment(false))
      } else {
        Alert.alert(newData.dataHeader.resultMessage)
      }
    } catch (e) {
      console.log(e)
    }
  }

  const onPressEdit = () => {
    props.setEditComment(true)
    props.setComment(comment)
  }

  const onPressDel = () => {
    getCommentDeleteData()
  }

  useEffect(() => {
    if (!isTokenGet) {
      getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
    }
  }, [])

  return (
    <View style={{ flex: 1, width: '100%', minHeight: 120, padding: 10, borderBottomColor: Color.DARK_BLUE, borderBottomWidth: 3, marginTop: 5, borderRadius: 15, borderWidth: 0.2 }}>
      <View style={{ flexDirection: 'column', width: '100%', height: '100%' }}>
        <View style={{ flexDirection: 'row', alignItems: 'center' }}>
          <Text style={{ fontSize: 18, color: Color.GRAY, fontWeight: 'bold', marginRight: 6 }}>{comment.nickname}</Text>
          <Text style={{ fontSize: 16, color: Color.GRAY, marginRight: 6 }}>{comment.createdAt}</Text>
        </View>
        <View style={{ padding: 10 }}>
          <Text style={{ fontSize: 18 }}>{comment.delete_flag == '1' ? '삭제된 댓글입니다.' : comment.content}</Text>
        </View>
        <View style={{ marginTop: 5, flexDirection: 'row', justifyContent: 'space-between' }}>
          {user.id == comment.userId ? (
            <View style={{ flexDirection: 'row', justifyContent: 'flex-start' }}>
              <TouchableOpacity onPress={onPressDel}>
                <View style={{ height: 35, width: 50, marginRight: 5, alignItems: 'center', justifyContent: 'center', backgroundColor: Color.LIGHT_RED, borderRadius: 10 }}>
                  <Text style={{ color: Color.WHITE }}>삭제</Text>
                </View>
              </TouchableOpacity>
              <TouchableOpacity onPress={onPressEdit}>
                <View style={{ height: 35, width: 50, alignItems: 'center', justifyContent: 'center', backgroundColor: Color.BLUE, borderRadius: 10 }}>
                  <Text style={{ color: Color.WHITE }}>수정</Text>
                </View>
              </TouchableOpacity>
            </View>
          ) : null}
          <View style={{ flexDirection: 'row', justifyContent: 'flex-end' }}>
            <TouchableOpacity
              onPress={() => {
                props.setAnswerCommentModal(true)
              }}
            >
              <View style={{ height: 35, width: 50, alignItems: 'center', justifyContent: 'center', backgroundColor: Color.GRAY, borderRadius: 10 }}>
                <Text style={{ color: Color.WHITE }}>답글</Text>
              </View>
            </TouchableOpacity>
          </View>
        </View>
      </View>
    </View>
  )
}

export default CommentCard
