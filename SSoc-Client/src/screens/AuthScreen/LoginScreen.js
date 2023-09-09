import React, { useState, useEffect } from 'react'
import { View, Text, Alert, TouchableOpacity } from 'react-native'
import { Typography } from '../../components/Basic/Typography'
import { Logo } from '../../modules/Logo'
import * as Color from '../../components/Colors/colors'
import { Spacer } from '../../components/Basic/Spacer'
import { Button } from '../../components/Basic/Button'
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view'
import AuthInput from '../../components/Input/AuthInput'
import { useNavigation } from '@react-navigation/native'
import { getLoginDataFetch } from '../../util/FetchUtil'
import { useRecoilState } from 'recoil'
import { goMainPageState, UserInfoState } from '../../util/RecoilUtil/Atoms'
import { AuthStackHeader } from '../../modules/AuthModules/AuthStackHeader'
import { setTokens } from '../../util/TokenUtil'

const LoginScreen = (props) => {
  const navigation = useNavigation()

  const [userEmail, setUserEmail] = useState('')
  const [userPassWord, setUserPassWord] = useState('')

  const [authInfo, setAuthInfo] = useState(null)
  const [user, setUser] = useRecoilState(UserInfoState)
  const [goMainPage, setGoMainPage] = useRecoilState(goMainPageState)

  const tempData = {
    dataHeader: {
      successCode: 0,
      resultCode: 1,
      resultMessage: null,
    },
    dataBody: {
      userInfo: {
        userEmail: 'shinhan@ssafy.ac.kr',
        userName: '김싸피',
        userNickName: '김신한',
        userImageUrl: 'https://images.pexels.com/photos/14268955/pexels-photo-14268955.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2',
      },
      token: {
        grantType: 'bearer',
        accessToken: 'testSplashScreen_REFLASH_AccessToken',
        refleshToken: 'testSplashScreen_REFLASH_RefleshToken',
        accessTokenExpiresIn: 12345153351,
      },
    },
  }

  const getLoginData = async () => {
    try {
      const response = await getLoginDataFetch(userEmail, userPassWord)
      const data = await response.json()
      await setAuthInfo(tempData)
    } catch (e) {
      console.log(e)
    }
  }

  const onPressFindPw = () => {
    navigation.navigate('FindPassWord')
  }

  const onPressRegister = () => {
    navigation.reset({ routes: [{ name: 'SchoolEmail' }] })
  }

  const onPressLogin = () => {
    if (userEmail.length == 0) {
      Alert.alert('아이디를 입력해주세요.')
    } else if (userPassWord.length < 8) {
      Alert.alert('비밀번호는 8자 이하일 수 잆습니다.')
    } else {
      getLoginData(userEmail, userPassWord)
    }
  }

  useEffect(() => {
    if (authInfo != null) {
      if (authInfo.dataHeader.successCode == 0) {
        if (authInfo.dataHeader.resultCode == 1) {
          setTokens(authInfo.dataBody.token.accessToken, authInfo.dataBody.token.refleshToken)
        }
        setUser(authInfo.dataBody.userInfo)
        setGoMainPage(true)
      } else {
        Alert.alert('아이디 또는 비밀번호를 확인해주세요.')
      }
      setAuthInfo(null)
    }
  }, [authInfo])

  return (
    <View
      backgroundColor={Color.WHITE}
      style={{ flex: 1, alignContent: 'center' }}
    >
      <AuthStackHeader title="로그인" />
      <KeyboardAwareScrollView>
        <Logo />
        <Spacer space={10} />
        <View style={{ margin: 20 }}>
          {/* 입력 폼 */}
          <AuthInput
            fontSize={16}
            title="학교 이메일"
            placeholder="ex) test@shinhan.ac.kr"
            inputColor={Color.BLUE}
            onChangeText={(text) => {
              setUserEmail(text)
            }}
          />
          <Spacer space={12} />
          <AuthInput
            fontSize={16}
            title="비밀번호"
            inputColor={Color.BLUE}
            secureTextEntry={true}
            onChangeText={(text) => {
              setUserPassWord(text)
            }}
          />
          <Spacer space={24} />

          {/* 로그인 버튼 */}
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

          {/* 회원가입 하러가기 */}
          <View style={{ marginTop: 30, flex: 1, flexDirection: 'row', paddingHorizontal: 10 }}>
            <View>
              <Typography
                fontSize={14}
                color={Color.GRAY}
              >
                <Text>비밀번호를 잊으셨나요?</Text>
              </Typography>
              <Spacer space={5} />
              <Button>
                <TouchableOpacity onPress={onPressFindPw}>
                  <View>
                    <Typography
                      fontSize={15}
                      color={Color.DARK_BLUE}
                    >
                      <Text style={{ fontWeight: 'bold', textDecorationLine: 'underline' }}>비밀번호 찾기</Text>
                    </Typography>
                  </View>
                </TouchableOpacity>
              </Button>
            </View>

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
        </View>
      </KeyboardAwareScrollView>
    </View>
  )
}
export default LoginScreen
