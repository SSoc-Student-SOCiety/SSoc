import { useState, useEffect } from 'react'
import { View, Text, TouchableOpacity, Alert } from 'react-native'
import { useRecoilValue } from 'recoil'
import * as Color from '../../components/Colors/colors'
import { getReplyDeleteFetch } from '../../util/FetchUtil'
import { UserInfoState } from '../../util/RecoilUtil/Atoms'
import { getTokens } from '../../util/TokenUtil'

const AnswerCommentCard = (props) => {
  const reply = props.answerComment
  const postId = props.postId
  const user = useRecoilValue(UserInfoState)

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  console.log(props)

  const onPressEdit = () => {
    props.setReply(reply)
    props.setEditReply(true)
  }

  const onPressDel = () => {
    getReplyDeleteData()
  }

  const getReplyDeleteData = async () => {
    try {
      const response = await getReplyDeleteFetch(accessToken, refreshToken, postId, reply.replyId)
      const newData = await response.json()
      if (newData.dataHeader.successCode == 0) {
        Alert.alert('답글이 삭제되었습니다.', props.setAnswerCommentModal(false))
      } else {
        Alert.alert(newData.dataHeader.resultMessage)
      }
    } catch (e) {
      console.log(e)
    }
  }

  useEffect(() => {
    getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
  }, [])

  return (
    <View style={{ flex: 1, width: '100%', minHeight: 100, padding: 10, borderBottomColor: Color.BLUE, borderBottomWidth: 3, marginTop: 5, borderRadius: 15, borderWidth: 0.2 }}>
      <View style={{ flexDirection: 'column', width: '100%', height: '100%' }}>
        <View style={{ flexDirection: 'row', alignItems: 'center' }}>
          <Text style={{ fontSize: 18, color: Color.GRAY, fontWeight: 'bold', marginRight: 6 }}>{reply.nickname}</Text>
          <Text style={{ fontSize: 16, color: Color.GRAY, marginRight: 6 }}>{reply.createdAt}</Text>
        </View>
        <View style={{ padding: 10 }}>
          <Text style={{ fontSize: 18 }}>{reply.content}</Text>
        </View>
        {user.id == reply.userId ? (
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
        ) : (
          <View style={{ flexDirection: 'row', justifyContent: 'flex-start' }}></View>
        )}
      </View>
    </View>
  )
}

export default AnswerCommentCard
