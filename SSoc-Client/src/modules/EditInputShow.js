import { Alert, View } from 'react-native'
import { Spacer } from '../components/Basic/Spacer'
import SettingBtn from './SettingButton'
import SettingInfoInput from './SettingInfoInput'
import * as Color from '../components/Colors/colors'
import { useState, useEffect } from 'react'
import { useRecoilState } from 'recoil'
import { goMainPageState, UserInfoState } from '../util/RecoilUtil/Atoms'
import { Typography } from '../components/Basic/Typography'
import { SingleLineInput } from '../components/Input/SingleLineInput'
import { getChangeAllFetch, getChangePasswordFetch, getChangNickNameFetch } from '../util/FetchUtil'
import { getTokens, setTokens } from '../util/TokenUtil'

const EditInputShow = (props) => {
  const [user, setUser] = useRecoilState(UserInfoState)
  const [goMainPage, setGoMainPage] = useRecoilState(goMainPageState)

  const userNick = props.userInfo.userNickname // 전 닉네임_readOnly
  const userEmail = props.userInfo.userEmail // 내 이메일_readOnly
  const userImageUrl = props.userInfo.userImageUrl // 내 이미지_readOnly

  const [inputUserNick, setInputUserNick] = useState(userNick)
  const [inputUserCurPw, setInputUserCurPw] = useState('')
  const [inputUserNewPw, setInputUserNewPw] = useState('')

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  const getChangeNickNameData = async () => {
    try {
      const response = await getChangNickNameFetch(accessToken, refreshToken, userEmail, inputUserNick)
      const data = await response.json()
      if (data.dataHeader != undefined) {
        if (data.dataHeader.successCode == 0 && data.dataHeader.resultCode == null) {
          // 성공
          setUser({ ...user, userNickname: inputUserNick })
          Alert.alert('닉네임이 변경되었습니다.')
        } else if (data.dataHeader.successCode == 0 && data.dataHeader.resultCode == 1) {
          // 토큰 재발급
          setTokens(data.dataBody.token.accessToken, data.dataBody.token.refreshToken)
          Alert.alert('닉네임 변경에 실패했습니다.', '다시 시도해주세요.')
        } else {
          // 토큰 만료
          Alert.alert('닉네임 변경에 실패했습니다.', '로그인 시간 만료')
          setGoMainPage(false)
        }
      } else {
        Alert.alert('닉네임 변경에 실패했습니다.', '서버 통신 에러')
      }
    } catch (e) {
      console.log(e)
    }
  }

  const getChangePasswordData = async () => {
    try {
      const response = await getChangePasswordFetch(accessToken, refreshToken, userEmail, inputUserCurPw, inputUserNewPw)
      const data = await response.json()
      if (data.dataHeader != undefined) {
        if (data.dataHeader.successCode == 0) {
          if (data.dataHeader.resultCode == null) {
            // 성공
            setInputUserCurPw('')
            setInputUserNewPw('')
            Alert.alert('비밀번호 변경에 성공했습니다.')
          } else if (data.dataHeader.resultCode == 1) {
            // 토큰 재발급
            setTokens(data.dataBody.token.accessToken, data.dataBody.token.refreshToken)
            Alert.alert('비밀번호 변경에 실패했습니다.', '다시 시도해주세요.')
          }
        } else {
          if (data.dataHeader.resultCode == null) {
            // 비밀 번호 같은 경우 혹은 현재 비밀번호가 틀린 경우
            Alert.alert(data.dataHeader.resultMessage)
          } else if (data.dataHeader.resultCode == 2) {
            // 토큰 두 개 다 만료
            Alert.alert('비밀번호 변경에 실패했습니다.', '로그인 시간 만료', setGoMainPage(false))
          }
        }
      } else {
        Alert.alert('비밀번호 변경에 실패했습니다.', '서버 통신 에러')
      }
    } catch (e) {
      console.log(e)
    }
  }

  const getChangeAllData = async () => {
    try {
      const response = await getChangeAllFetch(accessToken, refreshToken, userEmail, inputUserCurPw, inputUserNewPw, inputUserNick, userImageUrl)
      const data = await response.json()
      if (data.dataHeader != undefined) {
        if (data.dataHeader.successCode == 0) {
          setUser({ ...user, userNickname: inputUserNick })
          props.editCanclePress(false)
          Alert.alert('회원정보 변경에 성공했습니다.')
        } else {
          Alert.alert(data.dataHeader.resultMessage)
        }
      } else {
        Alert.alert('회원정보 변경에 실패했습니다.', '서버 통신 에러')
      }
    } catch (e) {
      console.log(e)
    }
  }

  const pressSaveNick = () => {
    if (userNick == inputUserNick) {
      Alert.alert('변경사항이 존재하지 않습니다.')
    } else if (inputUserNick.length == 0) {
      Alert.alert('바꿀 닉네임을 입력해주세요.')
    } else {
      getChangeNickNameData()
    }
  }

  const pressSavePw = () => {
    if (inputUserCurPw.length < 8) {
      Alert.alert('현재 비밀번호의 길이가 너무 짧습니다.', '비밀번호 길이: 8자 이상')
    } else if (inputUserNewPw.length < 8) {
      Alert.alert('바꿀 비밀번호의 길이가 너무 짧습니다.', '비밀번호 길이: 8자 이상')
    } else {
      getChangePasswordData()
    }
  }

  const pressSaveAll = () => {
    if (inputUserNick.length == 0) {
      Alert.alert('바꿀 닉네임을 입력해주세요.')
    } else if (inputUserCurPw.length < 8) {
      Alert.alert('현재 비밀번호의 길이가 너무 짧습니다.', '비밀번호 길이: 8자 이상')
    } else if (inputUserNewPw.length < 8) {
      Alert.alert('바꿀 비밀번호의 길이가 너무 짧습니다.', '비밀번호 길이: 8자 이상')
    } else {
      getChangeAllData()
    }
  }

  useEffect(() => {
    if (!isTokenGet) {
      getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
    }
  }, [isTokenGet])

  return (
    <View>
      <Spacer space={8} />
      <View>
        <SettingInfoInput
          editContent="닉네임"
          placeholder={userNick}
          value={inputUserNick}
          onPress={pressSaveNick}
          onChangeText={(text) => {
            setInputUserNick(text)
          }}
        />

        <View style={{ marginLeft: 4, marginTop: 30, marginBottom: 12 }}>
          <Typography fontSize={16}>현재 비밀번호</Typography>
          <View style={{ flexDirection: 'row', alignItems: 'center', justifyContent: 'center' }}>
            <View style={{ backgroundColor: Color.LIGHT_GRAY, borderRadius: 4, width: '70%' }}>
              <SingleLineInput
                value={inputUserCurPw}
                secureTextEntry={true}
                onChangeText={(text) => {
                  setInputUserCurPw(text)
                }}
              />
            </View>
            <View style={{ width: '30%' }}></View>
          </View>
        </View>
        <SettingInfoInput
          editContent="새로운 비밀번호"
          value={inputUserNewPw}
          secureTextEntry={true}
          onPress={pressSavePw}
          onChangeText={(text) => {
            setInputUserNewPw(text)
          }}
        />
      </View>
      <Spacer space={16} />
      <View style={{ flexDirection: 'row', alignItems: 'center', justifyContent: 'space-evenly' }}>
        <SettingBtn
          text="취소"
          onPress={props.editCanclePress}
          btnColor={Color.GRAY}
        />
        <SettingBtn
          text="모두 저장"
          onPress={pressSaveAll}
          btnColor={Color.DARK_BLUE}
        />
      </View>
    </View>
  )
}
export default EditInputShow
