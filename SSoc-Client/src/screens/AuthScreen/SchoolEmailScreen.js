import React, { useState } from 'react'
import { View, Text, TextInput, Alert, TouchableOpacity } from 'react-native'
import { Typography } from '../../components/Basic/Typography'
import { Logo } from '../../modules/Logo'
import * as Color from '../../components/Colors/colors'
import { Spacer } from '../../components/Basic/Spacer'
import { Button } from '../../components/Basic/Button'
import { useNavigation } from '@react-navigation/native'
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view'
import AuthInput from '../../components/Input/AuthInput'
import { SingleLineInput } from '../../components/Input/SingleLineInput'
import { goMainPageState } from '../../util/RecoilUtil/Atoms'
import { useRecoilState } from 'recoil'

const SchoolEmailScreen = (props) => {
  const navigation = useNavigation()
  // const [school, setSchool] = useState('');
  const [userId, setUserId] = useState('')
  const [email, setEmail] = useState('')
  const [userEmail, setUserEmail] = useState('')
  const schoolList = {
    신한대학교: 'shinhan.ac.kr',
    싸피대학교: 'ssafy.ac.kr',
  }

  // TO-DO
  // userEmail Server로 보내기 -> 정상: return값 메일 인증번호 = Register페이지로 prop 보내기
  // userEmail Server로 보내기 -> 에러: return값 에러코드 = 에러코드 발생 시 Alert 띄우기
  const onPressRegister = () => {
    if (email.length < 2 || userId.length < 2) {
      Alert.alert('학교와 이메일을 정확히 기입해주세요.')
    } else {
      setUserEmail(userId + '@' + email)
      navigation.navigate('Register', { email: userEmail })
    }
  }

  const onPressLogin = () => {
    navigation.reset({ routes: [{ name: 'Login' }] })
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
          title="학교"
          placeholder="ex) OO대학교"
          onChangeText={(text) => {
            if (schoolList[text]) {
              setEmail(schoolList[text])
            } else {
              setEmail('')
            }
            setUserEmail(userId + '@' + schoolList[text])
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
                  setUserEmail(text + '@' + email)
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
  )
}
export default SchoolEmailScreen
