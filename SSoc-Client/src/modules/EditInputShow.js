import { Alert, View } from 'react-native'
import { Spacer } from '../components/Basic/Spacer'
import SettingBtn from './SettingButton'
import SettingInfoInput from './SettingInfoInput'
import * as Color from '../components/Colors/colors'
import { useState } from 'react'

const EditInputShow = (props) => {
  const userNick = props.userInfo.userNickName
  const [inputUserNick, setInputUserNick] = useState('')
  const [inputUserPw, setInputUserPw] = useState('')

  const pressSaveNick = () => {
    if (inputUserNick.length != 0 && userNick != inputUserNick) {
      // TO-DO
      // Fetch 쏴서 result 분기
      Alert.alert('변경사항이 저장되었습니다.')
    } else {
      Alert.alert('변경사항이 존재하지 않습니다.')
    }
  }

  const pressSavePw = () => {
    if (inputUserPw.length < 8) {
      Alert.alert('비밀번호는 8자 이상이어야 합니다.')
    } else {
      // TO-DO
      // Fetch 쏴서 result 분기
      Alert.alert('변경사항이 저장되었습니다.')
    }
  }

  const pressSaveAll = () => {
    if (inputUserNick.length != 0 && userNick != inputUserNick && inputUserPw.length >= 8) {
      Alert.alert('변경사항이 모두 저장되었습니다.')
      props.editCanclePress()
    } else {
      Alert.alert('변경사항이 없거나\n비밀번호의 길이가 짧습니다.', '비밀번호 길이: 8자 이상')
    }
  }

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
        <SettingInfoInput
          editContent="비밀번호"
          secureTextEntry={true}
          onPress={pressSavePw}
          onChangeText={(text) => {
            setInputUserPw(text)
          }}
        />
      </View>
      <Spacer space={8} />
      <View style={{ flexDirection: 'row', alignItems: 'center', justifyContent: 'center' }}>
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
