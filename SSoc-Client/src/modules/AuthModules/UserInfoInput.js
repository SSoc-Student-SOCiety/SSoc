import { View, Text } from 'react-native';
import { Spacer } from '../../components/Basic/Spacer';
import AuthInput from '../../components/Input/AuthInput';
import * as Color from '../../components/Colors/colors';
import { Button } from '../../components/Basic/Button';
import { Typography } from '../../components/Basic/Typography';
import { useNavigation } from '@react-navigation/native';

const UserInfoInput = (props) => {
  const navigation = useNavigation();
  const onPressRegister = () => {
    // TO-DO
    // 인증번호 입력값과 emailCode(API에서 보내준 값)이
    // 일치하는지 확인 후 에러/로그인페이지 분기처리
    if (props.emailCode === '1234') {
      navigation.navigate('RegisterSuccess', { onFinishLoad: props.onFinishLoad });
    } else {
    }
  };
  return (
    <View>
      <Spacer space={10} />
      <AuthInput
        fontSize={16}
        title="이메일 인증번호"
        placeholder={props.emailCode}
      />
      <Spacer space={10} />
      <AuthInput
        fontSize={16}
        title="비밀번호"
        secureTextEntry={true}
      />
      <Spacer space={10} />
      <AuthInput
        fontSize={16}
        title="이름"
      />
      <Spacer space={10} />
      <AuthInput
        fontSize={16}
        title="닉네임"
      />
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
            회원가입
          </Typography>
        </View>
      </Button>
    </View>
  );
};
export default UserInfoInput;
