import React, { useState } from 'react';
import { View, Text, TextInput } from 'react-native';
import { Logo } from '../../modules/Logo';
import * as Color from '../../components/Colors/colors';
import { Spacer } from '../../components/Basic/Spacer';
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view';
import SchoolEmailInput from '../../modules/AuthModules/SchoolEmailInput';
import UserInfoInput from '../../modules/AuthModules/UserInfoInput';
const RegisterScreen = (props) => {
  const [initialized, setInitialized] = useState(false);
  const [emailCode, setEmailCode] = useState('');
  return (
    <KeyboardAwareScrollView
      backgroundColor={Color.WHITE}
      style={{ flex: 1, alignContent: 'center' }}
    >
      <Spacer space={16} />
      <Logo />
      <View style={{ margin: 20 }}>
        <SchoolEmailInput
          email={props.route.params.email}
          onPressCheck={() => {
            setInitialized(true);
          }}
          setEmailCode={setEmailCode}
        />
        {initialized ? (
          <UserInfoInput
            emailCode={emailCode}
            onFinishLoad={props.route.params.onFinishLoad}
          />
        ) : null}
      </View>
    </KeyboardAwareScrollView>
  );
};
export default RegisterScreen;
