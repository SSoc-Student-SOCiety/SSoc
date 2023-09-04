import React from 'react';
import { View, Text } from 'react-native';
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view';
import { Logo } from '../../modules/Logo';
import * as Color from '../../components/Colors/colors';
import { Spacer } from '../../components/Basic/Spacer';

const LoginScreen = () => {
  return (
    <KeyboardAwareScrollView
      backgroundColor={Color.WHITE}
      style={{ flex: 1, alignContent: 'center' }}
    >
      <Spacer space={16} />
      <Logo />
    </KeyboardAwareScrollView>
  );
};
export default LoginScreen;
