import React, { useState, useEffect } from 'react'
import { View, Text, Alert, TouchableOpacity } from 'react-native'
import { Typography } from '../../components/Basic/Typography'
import { Logo } from '../../modules/Logo'
import * as Color from '../../components/Colors/colors'
import { Spacer } from '../../components/Basic/Spacer'
import { Button } from '../../components/Basic/Button'
import { useNavigation } from '@react-navigation/native'
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view'
import AuthInput from '../../components/Input/AuthInput'
import { SingleLineInput } from '../../components/Input/SingleLineInput'
import { AuthStackHeader } from '../../modules/AuthModules/AuthStackHeader'
import { getEmailCheckFetch } from '../../util/FetchUtil'

const SchoolEmailScreen = (props) => {
  const navigation = useNavigation()
  // const [school, setSchool] = useState('');
  const [userId, setUserId] = useState('')
  const [email, setEmail] = useState('')
  const [userEmail, setUserEmail] = useState('')
  const [checkEmailData, setCheckEmailData] = useState(null)

  const schoolList = {
    신한대학교: 'shinhan.ac.kr',
    싸피대학교: 'ssafy.ac.kr',
  }

  const tempData = {
    dataHeader: {
      successCode: 0,
      resultCode: null,
      resultMessage: '해당 이메일이 존재합니다.',
    },
    dataBody: null,
  }

  const onPressRegister = () => {
    if (email.length < 2 || userId.length < 2) {
      Alert.alert('학교와 이메일을 정확히 기입해주세요.')
    } else {
      setUserEmail(userId + '@' + email)
      getEmailCheckData(userEmail)
    }
  }

  const onPressLogin = () => {
    navigation.reset({ routes: [{ name: 'Login' }] })
  }

  const getEmailCheckData = async (userEmail) => {
    try {
      const response = await getEmailCheckFetch(userEmail)
      const data = await response.json()
      await setCheckEmailData(tempData)
    } catch (e) {
      console.log(e)
    }
  }

  useEffect(() => {
    if (checkEmailData != null) {
      if (checkEmailData.dataHeader.successCode == 0) {
        navigation.navigate('Register', { userEmail: userEmail })
      } else {
        Alert.alert(checkEmailData.dataHeader.resultMessage)
      }
      setCheckEmailData(null)
    }
  }, [userEmail, checkEmailData])

  return (
    <View
      backgroundColor={Color.WHITE}
      style={{ flex: 1, alignContent: 'center' }}
    >
      <AuthStackHeader title="이메일 중복체크" />
      <KeyboardAwareScrollView>
        <Logo />
        <Spacer space={10} />
        <View style={{ margin: 20 }}>
          <AuthInput
            fontSize={16}
            title="학교"
            placeholder="ex) OO대학교"
            onChangeText={(text) => {
              if (schoolList[text]) {
                setEmail(schoolList[text])
              } else {
                setEmail('')
              }
            }}
          />
          <Typography
            fontSize={14}
            color={Color.GRAY}
          >
            <Text>* 학교명을 정확히 기입해주세요!{'\n  '}정확히 기입시 이메일이 나타납니다 :)</Text>
          </Typography>
          <Spacer space={16} />
          <View>
            <Typography
              fontSize={16}
              color={Color.DARK_BLUE}
            >
              <Text style={{ fontWeight: 'bold' }}>학교 이메일</Text>
            </Typography>
            <Spacer space={4} />
            <View style={{ flex: 1, flexDirection: 'row' }}>
              <View style={{ flex: 5, backgroundColor: Color.LIGHT_GRAY, borderRadius: 10 }}>
                <SingleLineInput
                  fontSize={20}
                  onChangeText={(text) => {
                    setUserId(text)
                  }}
                />
              </View>
              <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
                <Text>@</Text>
              </View>
              <View style={{ flex: 5, backgroundColor: Color.GRAY, borderRadius: 10, alignItems: 'center', justifyContent: 'center' }}>
                <Typography fontSize={16}>{email}</Typography>
              </View>
            </View>

            <Typography
              fontSize={14}
              color={Color.LIGHT_BLUE}
            >
              학교 이메일이 필요해요!
            </Typography>
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
                    중복체크
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
              <Text>이미 회원이신가요?</Text>
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
export default SchoolEmailScreen
