import { useState, useEffect } from 'react'
import { TouchableOpacity, TouchableWithoutFeedback, View, Text, ScrollView, Alert } from 'react-native'
import * as Color from '../../components/Colors/colors'
import { MultiLineInput } from '../../components/Input/MultiLineInput'
import CheckBox from 'react-native-check-box'
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view'
import { getEditReplyFetch } from '../../util/FetchUtil'
import { getTokens } from '../../util/TokenUtil'

const EditReply = (props) => {
  const reply = props.reply
  const postId = props.postId

  const [isChecked, setIsChecked] = useState(false)
  const [inputContent, setInputContent] = useState(reply.content)

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  const getEditReplyData = async () => {
    try {
      if (inputContent.length == 0) {
        Alert.alert('내용을 필수로 입력해주세요.')
        return
      }
      const response = await getEditReplyFetch(accessToken, refreshToken, props.postId, reply.replyId, inputContent, isChecked)
      const data = await response.json()
      if (data != null) {
        if (data.dataHeader.successCode == 0) {
          Alert.alert('답글이 수정되었습니다.', props.setAnswerCommentModal(false))
        } else {
          Alert.alert(data.dataHeader.resultMessage, props.setEditReply(false))
        }
        return
      }
      Alert.alert('답글 수정에 실패했습니다.', '알 수 없는 에러', props.setEditReply(false))
    } catch (e) {
      console.log(e)
    }
  }

  useEffect(() => {
    if (!isTokenGet) {
      getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
    }
  }, [])

  return (
    <>
      <TouchableOpacity
        onPress={() => {
          props.setEditReply(false)
        }}
        style={{ flex: 1, justifyContent: 'center', alignItems: 'center', backgroundColor: 'rgba(211, 211, 211, 0.5)' }}
      >
        <TouchableWithoutFeedback>
          <View style={{ flexDirection: 'column', width: '90%', backgroundColor: Color.WHITE, borderColor: Color.BLUE, borderWidth: 1, borderRadius: 20 }}>
            <View style={{ backgroundColor: Color.BLUE, width: '100%', height: '12%', borderTopRightRadius: 15, borderTopLeftRadius: 15, alignItems: 'center', justifyContent: 'center' }}>
              <Text style={{ fontSize: 20, color: Color.WHITE, fontWeight: 'bold' }}>답글 수정</Text>
            </View>
            <KeyboardAwareScrollView>
              <View style={{ flexDirection: 'column' }}>
                <View style={{ flex: 1, flexDirection: 'row', alignItems: 'center', paddingHorizontal: 10, borderColor: Color.GRAY, borderWidth: 0.3, borderBottomWidth: 2, borderRadius: 5, margin: 10 }}>
                  <Text style={{ fontSize: 18 }}>익명 여부: </Text>
                  <CheckBox
                    style={{ flex: 1, padding: 10 }}
                    onClick={() => {
                      setIsChecked(isChecked ? false : true)
                    }}
                    isChecked={isChecked}
                  />
                </View>

                <View style={{ flex: 1, flexDirection: 'row', alignItems: 'center', paddingHorizontal: 10, paddingVertical: 5, borderColor: Color.GRAY, borderWidth: 0.3, borderBottomWidth: 2, borderRadius: 5, margin: 10, marginBottom: 0 }}>
                  <View style={{ width: '20%', justifyContent: 'center' }}>
                    <Text style={{ fontSize: 18 }}>내용: </Text>
                  </View>
                  <View style={{ width: '80%' }}>
                    <MultiLineInput
                      fontSize={18}
                      value={inputContent}
                      onChangeText={(text) => {
                        setInputContent(text)
                      }}
                    />
                  </View>
                </View>
              </View>
            </KeyboardAwareScrollView>
            <View style={{ flexDirection: 'row', margin: 20, marginTop: 0, justifyContent: 'space-around' }}>
              <TouchableOpacity
                onPress={() => {
                  props.setEditReply(false)
                }}
              >
                <View style={{ padding: 15, backgroundColor: Color.BLUE, borderRadius: 10 }}>
                  <Text style={{ fontSize: 18, color: Color.WHITE }}>수정 취소</Text>
                </View>
              </TouchableOpacity>
              <TouchableOpacity
                onPress={() => {
                  getEditReplyData()
                }}
              >
                <View style={{ padding: 15, backgroundColor: Color.DARK_BLUE, borderRadius: 10 }}>
                  <Text style={{ fontSize: 18, color: Color.WHITE }}>답글 수정</Text>
                </View>
              </TouchableOpacity>
            </View>
          </View>
        </TouchableWithoutFeedback>
      </TouchableOpacity>
    </>
  )
}

export default EditReply
