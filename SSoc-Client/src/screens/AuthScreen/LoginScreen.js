import React, { useState } from 'react'
import { View, Text, Alert, TouchableOpacity } from 'react-native'
import { Typography } from '../../components/Basic/Typography'
import { Logo } from '../../modules/Logo'
import * as Color from '../../components/Colors/colors'
import { Spacer } from '../../components/Basic/Spacer'
import { Button } from '../../components/Basic/Button'
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view'
import AuthInput from '../../components/Input/AuthInput'
import { useNavigation } from '@react-navigation/native'

const LoginScreen = (props) => {
  const navigation = useNavigation()
  const [userId, setUserId] = useState('')
  const [userPw, setUserPw] = useState('')
  const onFinishLoad = props.route.params.onFinishLoad
  const userData = {
    accessToken: {
      id: 'test123@shinhan.ac.kr',
      pw: 'test1234',
      iat: '123453425',
      exp: '123431513',
    },
    refleshToken: {
      id: 'test123@shinhan.ac.kr',
      pw: 'test1234',
      iat: '123453425',
      exp: '123431513',
    },
    result: {
      code: 0,
      error: '',
    },
  }

  const onPressRegister = () => {
    navigation.reset({
      routes: [
        {
          name: 'SchoolEmail',
          params: {
            onFinishLoad: onFinishLoad,
          },
        },
      ],
    })
  }

  const onPressLogin = () => {
    if (userId.length == 0) {
      Alert.alert('아이디를 입력해주세요.')
    } else if (userPw.length < 8) {
      Alert.alert('비밀번호는 8자 이하일 수 잆습니다.')
    } else {
      // TO-DO
      // Server로 userId, userPw 보내고 Token/에러 받기
      const res = userData
      if (res != null && res.result.code != '-1') {
        onFinishLoad()
      } else {
        Alert.alert('아이디 또는 비밀번호를 확인해주세요.')
      }
    }
  }

  return (
    <KeyboardAwareScrollView
      backgroundColor={Color.WHITE}
      style={{ flex: 1, alignContent: 'center' }}
    >
      <Spacer space={16} />
      <Logo />
      <Spacer space={10} />
      <View style={{ margin: 20 }}>
        <AuthInput
          fontSize={16}
          title="학교 이메일"
          placeholder="ex) test@shinhan.ac.kr"
          inputColor={Color.BLUE}
          onChangeText={(text) => {
            setUserId(text)
          }}
        />
        <Spacer space={12} />
        <AuthInput
          fontSize={16}
          title="비밀번호"
          inputColor={Color.BLUE}
          secureTextEntry={true}
          onChangeText={(text) => {
            setUserPw(text)
          }}
        />
        <Spacer space={16} />
        <Button>
          <TouchableOpacity onPress={onPressLogin}>
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
                로그인
              </Typography>
              <Spacer space={10} />
            </View>
          </TouchableOpacity>
        </Button>
        <Spacer space={14} />
        <View style={{ flex: 1, alignItems: 'flex-end' }}>
          <Typography
            fontSize={14}
            color={Color.GRAY}
          >
            <Text>아직 회원이 아니신가요?</Text>
          </Typography>
          <Spacer space={5} />
          <Button>
            <TouchableOpacity onPress={onPressRegister}>
              <View>
                <Typography
                  fontSize={15}
                  color={Color.DARK_BLUE}
                >
                  <Text style={{ fontWeight: 'bold', textDecorationLine: 'underline' }}>회원가입 하러가기</Text>
                </Typography>
              </View>
            </TouchableOpacity>
          </Button>
        </View>
      </View>
    </KeyboardAwareScrollView>
  )
}
export default LoginScreen
