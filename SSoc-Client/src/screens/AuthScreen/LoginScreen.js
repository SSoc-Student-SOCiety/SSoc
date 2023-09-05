import React from 'react';
import { View, Text } from 'react-native';
import { Typography } from '../../components/Basic/Typography';
import { Logo } from '../../modules/Logo';
import * as Color from '../../components/Colors/colors';
import { Spacer } from '../../components/Basic/Spacer';
import { Button } from '../../components/Basic/Button';
import { useNavigation } from '@react-navigation/native';
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view';
import AuthInput from '../../components/Input/AuthInput';

const LoginScreen = (props) => {
  console.log(props);
  // TO-DO
  // API 받아서 중복체크 -> 분기 처리 필요
  const onPressLogin = () => {
    props.route.onFinishLoad();
  };

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
        />
        <Spacer space={12} />
        <AuthInput
          fontSize={16}
          title="비밀번호"
          inputColor={Color.BLUE}
          secureTextEntry={true}
        />
        <Spacer space={16} />
        <Button onPress={onPressLogin}>
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
        </Button>
      </View>
    </KeyboardAwareScrollView>
  );
};
export default LoginScreen;
