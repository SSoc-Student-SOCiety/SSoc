import React, { useState, useEffect } from 'react'
import { View, Text, TextInput, Alert, TouchableOpacity } from 'react-native'
import { Typography } from '../../components/Basic/Typography'
import { Logo } from '../../modules/Logo'
import * as Color from '../../components/Colors/colors'
import { Spacer } from '../../components/Basic/Spacer'
import { Button } from '../../components/Basic/Button'
import { useNavigation } from '@react-navigation/native'
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view'
import AuthInput from '../../components/Input/AuthInput'
import { AuthStackHeader } from '../../modules/AuthModules/AuthStackHeader'
import { getTempPassWordFetch } from '../../util/FetchUtil'

const FindPassWordScreen = () => {
  const navigation = useNavigation()
  const [userEmail, setUserEmail] = useState('')
  const [userTempPwData, setUserTempPwData] = useState(null)

  const tempData = {
    dataHeader: {
      successCode: 0,
      resultCode: null,
      resultMessage: null,
    },
    dataBody: null,
  }

  const getTempPassWordData = async () => {
    try {
      const response = await getTempPassWordFetch(userEmail)
      const data = await response.json()
      await setUserTempPwData(tempData)
    } catch (e) {
      console.log(e)
    }
  }

  const onPressFindPw = () => {
    if (userEmail.length == 0) {
      Alert.alert('학교와 이메일을 정확히 기입해주세요.')
    } else {
      getTempPassWordData()
    }
  }

  const onPressLogin = () => {
    navigation.reset({ routes: [{ name: 'Login' }] })
  }

  useEffect(() => {
    if (userTempPwData != null) {
      Alert.alert('작성된 이메일로 임시 비밀번호가 발급되었습니다.', '비밀번호 확인 후 마이페이지에서 수정해주세요!')
      navigation.reset({ routes: [{ name: 'Login' }] })
    }
  }, [userTempPwData])

  return (
    <View
      backgroundColor={Color.WHITE}
      style={{ flex: 1, alignContent: 'center' }}
    >
      <AuthStackHeader title="비밀번호 찾기" />
      <KeyboardAwareScrollView>
        <Logo />
        <Spacer space={20} />
        <View style={{ margin: 20 }}>
          <AuthInput
            fontSize={16}
            title="학교 이메일"
            placeholder="ex) shinhan@shinhan.ac.kr"
            onChangeText={(text) => {
              setUserEmail(text)
            }}
          />
          <Spacer space={40} />
          <View>
            <Button>
              <TouchableOpacity onPress={onPressFindPw}>
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
                    비밀번호 찾기
                  </Typography>
                  <Spacer space={10} />
                </View>
              </TouchableOpacity>
            </Button>
          </View>
          <View style={{ flex: 1, alignItems: 'flex-end' }}>
            <Spacer space={20} />
            <Typography
              fontSize={14}
              color={Color.GRAY}
            >
              <Text>비밀번호를 알고 계신가요?</Text>
            </Typography>
            <Spacer space={5} />
            <Button>
              <TouchableOpacity onPress={onPressLogin}>
                <View>
                  <Typography
                    fontSize={15}
                    color={Color.DARK_BLUE}
                  >
                    <Text style={{ fontWeight: 'bold', textDecorationLine: 'underline' }}>로그인 하러가기</Text>
                  </Typography>
                </View>
              </TouchableOpacity>
            </Button>
          </View>
        </View>
      </KeyboardAwareScrollView>
    </View>
  )
}
export default FindPassWordScreen
