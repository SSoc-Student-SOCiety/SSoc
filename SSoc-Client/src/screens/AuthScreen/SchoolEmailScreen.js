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

const SchoolEmailScreen = (props) => {
  const navigation = useNavigation();
  // TO-DO
  // API 받아서 중복체크 -> 분기 처리 필요
  const onPressRegister = () => {
    navigation.navigate('Register', { email: 'test1234@shinhan.ac.kr', onFinishLoad: props.route.onFinishLoad });
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
          title="학교"
          placeholder="ex) OO대학교"
          inputColor={Color.BLUE}
        />
        <Spacer space={12} />
        <AuthInput
          fontSize={16}
          title="학교 이메일"
          placeholder="ex) test@shinhan.ac.kr"
          inputColor={Color.BLUE}
        />
        <Typography
          fontSize={14}
          color={Color.LIGHT_BLUE}
        >
          학교 이메일이 필요해요!
        </Typography>
        <Spacer space={16} />
        <Button onPress={onPressRegister}>
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
        </Button>
      </View>
    </KeyboardAwareScrollView>
  );
};
export default SchoolEmailScreen;
