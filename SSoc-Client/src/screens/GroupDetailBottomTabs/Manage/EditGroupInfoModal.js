import { useState, useEffect } from 'react'
import { TouchableOpacity, TouchableWithoutFeedback, View, Text, ScrollView, Alert } from 'react-native'
import * as Color from '../../../components/Colors/colors'
import { SingleLineInput } from '../../../components/Input/SingleLineInput'
import { MultiLineInput } from '../../../components/Input/MultiLineInput'
import CheckBox from 'react-native-check-box'
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view'
import { getEditContentFetch, getEditGroupInfoFetch } from '../../../util/FetchUtil'
import { getTokens } from '../../../util/TokenUtil'
import { useNavigation } from '@react-navigation/native'
import { Typography } from '../../../components/Basic/Typography'

const EditGroupInfoModal = (props) => {
  const groupInfo = props.groupInfo
  const navigation = useNavigation()

  const [inputName, setInputName] = useState(groupInfo.name)
  const [inputAboutUs, setInputAboutUs] = useState(groupInfo.aboutUs)
  const [inputContent, setInputContent] = useState(groupInfo.introduce)
  const [inputTumbnail, setInputTumbnail] = useState(groupInfo.tumbnail == null ? '' : groupInfo.tumbnail)

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  const [editGroupInfoData, setEditGroupInfoData] = useState(null)

  const getEditGroupInfoData = async () => {
    try {
      const response = await getEditGroupInfoFetch(accessToken, refreshToken, groupInfo.groupId, inputName, inputAboutUs, inputContent, inputTumbnail)
      const data = await response.json()
      console.log(data)
      if (data != null && data.dataHeader != undefined) {
        if (data.dataHeader.successCode == 0) {
          Alert.alert('그룹 정보를 변경하였습니다.', '', [
            {
              text: '확인',
              onPress: () => {
                props.setEditGroupForm(false)
                props.setReload(!props.reload)
              },
            },
          ])
        } else {
          Alert.alert(data.dataHeader.resultMessage)
        }
      } else {
        Alert.alert('서버 통신 오류', '다시 시도해주세요.')
      }
      //   await setEditGroupInfoData(data)
    } catch (e) {
      console.log(e)
    }
  }

  useEffect(() => {
    if (isTokenGet) {
      getEditGroupInfoData()
      props.setEditGroupForm(false)
    }
  }, [isTokenGet])

  return (
    <>
      <TouchableOpacity
        onPress={() => {
          props.setEditGroupForm(false)
        }}
        style={{ flex: 1, justifyContent: 'center', alignItems: 'center', backgroundColor: 'rgba(211, 211, 211, 0.5)' }}
      >
        <TouchableWithoutFeedback>
          <View style={{ flexDirection: 'column', width: '90%', backgroundColor: Color.WHITE, borderColor: Color.GRAY, borderWidth: 1, borderRadius: 20 }}>
            <View style={{ backgroundColor: Color.LIGHT_RED, width: '100%', height: '12%', borderTopRightRadius: 15, borderTopLeftRadius: 15, alignItems: 'center', justifyContent: 'center' }}>
              <Text style={{ fontSize: 20, color: Color.WHITE, fontWeight: 'bold' }}>그룹 정보 수정</Text>
            </View>
            <KeyboardAwareScrollView>
              <View style={{ flexDirection: 'column' }}>
                <View style={{ flex: 1, flexDirection: 'row', alignItems: 'center', paddingHorizontal: 10, paddingVertical: 5, borderColor: Color.GRAY, borderWidth: 0.3, borderBottomWidth: 2, borderRadius: 5, margin: 10, marginBottom: 0 }}>
                  <View style={{ width: '25%', justifyContent: 'center' }}>
                    <Text style={{ fontSize: 15 }}>그룹 이름: </Text>
                  </View>
                  <View style={{ width: '75%', backgroundColor: Color.LIGHT_GRAY, borderRadius: 5 }}>
                    <SingleLineInput
                      value={inputName}
                      onChangeText={(text) => {
                        setInputName(text)
                      }}
                    />
                  </View>
                </View>

                <View style={{ flex: 1, flexDirection: 'row', alignItems: 'center', paddingHorizontal: 10, paddingVertical: 5, borderColor: Color.GRAY, borderWidth: 0.3, borderBottomWidth: 2, borderRadius: 5, margin: 10, marginBottom: 0 }}>
                  <View style={{ width: '25%', justifyContent: 'center' }}>
                    <Text style={{ fontSize: 15 }}>그룹 소개: </Text>
                  </View>
                  <View style={{ width: '75%', backgroundColor: Color.LIGHT_GRAY, borderRadius: 5 }}>
                    <SingleLineInput
                      value={inputAboutUs}
                      onChangeText={(text) => {
                        setInputAboutUs(text)
                      }}
                    />
                  </View>
                </View>

                <View style={{ flex: 1, flexDirection: 'row', alignItems: 'center', paddingHorizontal: 10, paddingVertical: 5, borderColor: Color.GRAY, borderWidth: 0.3, borderBottomWidth: 2, borderRadius: 5, margin: 10, marginBottom: 0 }}>
                  <View style={{ justifyContent: 'center', width: '30%' }}>
                    <Text style={{ fontSize: 16 }}>이미지 변경: </Text>
                  </View>
                  <View style={{ height: '100%', width: '47%', maxWidth: '45%', padding: 5, backgroundColor: Color.GRAY, justifyContent: 'center', borderRadius: 5, marginRight: 4 }}>
                    <Text
                      numberOfLines={1}
                      style={{ textDecorationLine: 'underline', fontSize: 14 }}
                    >
                      {inputTumbnail}
                    </Text>
                  </View>
                  <View style={{ justifyContent: 'center', width: '25%', alignItems: 'center' }}>
                    {/* TO-DO */}
                    {/* 변경버튼 클릭 후 이미지 변경 시 url 가져와서 setInputTumbnail()을 변경 url로 inputTumbnail 바꿔넣기 */}
                    <TouchableOpacity
                      onPress={() => {
                        setInputTumbnail('testUrl')
                      }}
                    >
                      <View style={{ padding: 8, backgroundColor: Color.LIGHT_RED, borderRadius: 8 }}>
                        <Text style={{ fontSize: 12, color: Color.WHITE }}>변경 하기</Text>
                      </View>
                    </TouchableOpacity>
                  </View>
                </View>

                <View style={{ flex: 1, flexDirection: 'row', alignItems: 'center', paddingHorizontal: 10, paddingVertical: 5, borderColor: Color.GRAY, borderWidth: 0.3, borderBottomWidth: 2, borderRadius: 5, margin: 10, marginBottom: 0 }}>
                  <View style={{ width: '25%', justifyContent: 'center' }}>
                    <Text style={{ fontSize: 15 }}>그룹 내용: </Text>
                  </View>
                  <View style={{ width: '75%' }}>
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
            <View style={{ flexDirection: 'row', margin: 15, justifyContent: 'space-around' }}>
              <TouchableOpacity
                onPress={() => {
                  props.setEditGroupForm(false)
                }}
              >
                <View style={{ padding: 15, backgroundColor: Color.GRAY, borderRadius: 10 }}>
                  <Text style={{ fontSize: 18, color: Color.WHITE }}>수정 취소</Text>
                </View>
              </TouchableOpacity>
              <TouchableOpacity
                onPress={() => {
                  getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
                }}
              >
                <View style={{ padding: 15, backgroundColor: Color.LIGHT_RED, borderRadius: 10 }}>
                  <Text style={{ fontSize: 18, color: Color.WHITE }}>수정 하기</Text>
                </View>
              </TouchableOpacity>
            </View>
          </View>
        </TouchableWithoutFeedback>
      </TouchableOpacity>
    </>
  )
}

export default EditGroupInfoModal
