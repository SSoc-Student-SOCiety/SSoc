import { Alert, View } from 'react-native'
import { Spacer } from '../components/Basic/Spacer'
import SettingBtn from './SettingButton'
import SettingInfoInput from './SettingInfoInput'
import * as Color from '../components/Colors/colors'
import { useState, useEffect } from 'react'
import { useRecoilState } from 'recoil'
import { UserInfoState } from '../util/RecoilUtil/Atoms'
import { Typography } from '../components/Basic/Typography'
import { SingleLineInput } from '../components/Input/SingleLineInput'
import { getChangePasswordFetch, getChangNickNameFetch } from '../util/FetchUtil'

const EditInputShow = (props) => {
  const [user, setUser] = useRecoilState(UserInfoState)
  const [inputUserNick, setInputUserNick] = useState('')
  const [inputUserCurPw, setInputUserCurPw] = useState('')
  const [inputUserNewPw, setInputUserNewPw] = useState('')
  const [changeNickNameData, setChangeNickNameData] = useState(null)
  const [changePasswordData, setChangePasswordData] = useState(null)
  const [changeAllData, setChangeAllData] = useState(null)
  const userNick = props.userInfo.userNickName
  const userEmail = props.userInfo.userEmail

  const tempData = {
    dataHeader: {
      successCode: 0,
      resultCode: null,
      resultMessage: null,
    },
    dataBody: null,
  }

  const getChangeNickNameData = async () => {
    try {
      const response = await getChangNickNameFetch(userEmail, '', '', inputUserNick, '')
      const data = await response.json()
      await setChangeNickNameData(tempData)
    } catch (e) {
      console.log(e)
    }
  }

  const getChangePasswordData = async () => {
    try {
      const response = await getChangePasswordFetch(userEmail, inputUserCurPw, inputUserNewPw, '', '')
      const data = await response.json()
      await setChangePasswordData(tempData)
    } catch (e) {
      console.log(e)
    }
  }

  const getChangeAllData = async () => {
    try {
      const response = await getChangePasswordFetch(userEmail, inputUserCurPw, inputUserNewPw, inputUserNick, '')
      const data = await response.json()
      await setChangeAllData(tempData)
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
    if (userNick == inputUserNick) {
      Alert.alert('변경사항이 존재하지 않습니다.')
    } else if (inputUserNick.length == 0) {
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
    if (changeNickNameData != null) {
      if (changeNickNameData.dataHeader.successCode == 0) {
        setUser({ ...user, userNickName: inputUserNick })
        Alert.alert('변경사항이 저장되었습니다.')
      } else {
        Alert.alert('알수 없는 에러가 발생하였습니다.', '다시 시도해주세요.')
      }
      setChangeNickNameData(null)
    }

    if (changePasswordData != null) {
      if (changePasswordData.dataHeader.successCode == 0) {
        Alert.alert('변경사항이 저장되었습니다.')
      } else {
        Alert.alert(changePasswordData.dataHeader.resultMessage)
      }
      setChangePasswordData(null)
      props.editCanclePress()
    }

    if (changeAllData != null) {
      if (changeAllData.dataHeader.successCode == 0) {
        setUser({ ...user, userNickName: inputUserNick })
        Alert.alert('변경사항이 모두 저장되었습니다.')
      } else {
        Alert.alert(changeAllData.dataHeader.resultMessage)
      }
      setChangeAllData(null)
      props.editCanclePress()
    }
  }, [changeNickNameData, changePasswordData, changeAllData])

  return (
    <View>
      <Spacer space={8} />
      <View>
        <SettingInfoInput
          editContent="닉네임"
          placeholder={props.userInfo.userNickName}
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
                onChangeText={(text) => {
                  setInputUserCurPw(text)
                }}
                secureTextEntry={true}
              />
            </View>
            <View style={{ width: '30%' }}></View>
          </View>
        </View>
        <SettingInfoInput
          editContent="새로운 비밀번호"
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
