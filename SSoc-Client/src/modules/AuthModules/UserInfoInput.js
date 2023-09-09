import { View, Alert, TouchableOpacity } from 'react-native'
import { useEffect } from 'react'
import { Spacer } from '../../components/Basic/Spacer'
import AuthInput from '../../components/Input/AuthInput'
import * as Color from '../../components/Colors/colors'
import { Button } from '../../components/Basic/Button'
import { Typography } from '../../components/Basic/Typography'
import { useNavigation } from '@react-navigation/native'
import { useState } from 'react'
import { getRegisterResultFetch } from '../../util/FetchUtil'

const UserInfoInput = (props) => {
  const navigation = useNavigation()
  const [emailCode, setEmailCode] = useState('')
  const [userPw, setUserPw] = useState('')
  const [userName, setUserName] = useState('')
  const [userNick, setUserNick] = useState('')
  const [registerResultData, setRegisterResultData] = useState(null)

  const tempData = {
    dataHeader: {
      successCode: 0,
      resultCode: null,
      resultMessage: null,
    },
    dataBody: null,
  }

  const getRegisterResultData = async () => {
    try {
      const response = await getRegisterResultFetch(props.userEmail, userPw, userName, userNick)
      const data = await response.json()
      setRegisterResultData(tempData)
    } catch (e) {
      console.log(e)
    }
  }

  const onPressRegister = () => {
    if (emailCode.length == 0 || userName.length < 1 || userNick == 0) {
      Alert.alert('정보를 모두 입력해주세요.')
    } else if (userPw.length < 8) {
      Alert.alert('비밀번호는 8자 이상부터 가능합니다.')
    } else if (props.emailCode != emailCode) {
      Alert.alert('이메일 인증번호를 다시 확인해주세요.')
    } else {
      getRegisterResultData()
    }
  }

  useEffect(() => {
    if (registerResultData != null) {
      if (registerResultData.dataHeader.successCode == 0) {
        navigation.reset({ routes: [{ name: 'RegisterSuccess' }] })
      } else {
        Alert.alert('회원가입에 실패했습니다.', '다시 시도해주세요.')
      }
    }
    setRegisterResultData(null)
  }, [registerResultData])

  return (
    <View>
      <Spacer space={10} />
      <AuthInput
        fontSize={16}
        title="이메일 인증번호"
        placeholder={props.emailCode}
        onChangeText={(text) => {
          setEmailCode(text)
        }}
      />
      <Spacer space={10} />
      <AuthInput
        fontSize={16}
        title="비밀번호"
        secureTextEntry={true}
        onChangeText={(text) => {
          setUserPw(text)
        }}
      />
      <Spacer space={10} />
      <AuthInput
        fontSize={16}
        title="이름"
        onChangeText={(text) => {
          setUserName(text)
        }}
      />
      <Spacer space={10} />
      <AuthInput
        fontSize={16}
        title="닉네임"
        onChangeText={(text) => {
          setUserNick(text)
        }}
      />
      <Spacer space={16} />
      <Button>
        <TouchableOpacity onPress={onPressRegister}>
          <View
            backgroundColor={Color.DARK_BLUE}
            borderRadius={10}
            style={{
              alignSelf: 'stretch',
              height: 50,
              alignItems: 'center',
              justifyContent: 'center',
              flexDirection: 'row',
            }}
          >
            <Typography
              fontSize={16}
              color={Color.WHITE}
            >
              회원가입
            </Typography>
          </View>
        </TouchableOpacity>
      </Button>
    </View>
  )
}
export default UserInfoInput
