import React, { useState } from 'react'
import { View, Text, TextInput } from 'react-native'
import { Logo } from '../../modules/Logo'
import * as Color from '../../components/Colors/colors'
import { Spacer } from '../../components/Basic/Spacer'
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view'
import SchoolEmailInput from '../../modules/AuthModules/SchoolEmailInput'
import UserInfoInput from '../../modules/AuthModules/UserInfoInput'
import { AuthStackHeader } from '../../modules/AuthModules/AuthStackHeader'
const RegisterScreen = (props) => {
  const [initialized, setInitialized] = useState(false)
  const [emailCode, setEmailCode] = useState('')

  return (
    <View
      backgroundColor={Color.WHITE}
      style={{ flex: 1, alignContent: 'center' }}
    >
      <AuthStackHeader title="회원가입" />
      <KeyboardAwareScrollView>
        <Logo />
        <View style={{ margin: 20 }}>
          <SchoolEmailInput
            userEmail={props.route.params.userEmail}
            onPressCheck={() => {
              setInitialized(true)
            }}
            setEmailCode={setEmailCode}
          />
          {initialized ? (
            <UserInfoInput
              userEmail={props.route.params.userEmail}
              emailCode={emailCode}
            />
          ) : null}
        </View>
      </KeyboardAwareScrollView>
    </View>
  )
}
export default RegisterScreen
