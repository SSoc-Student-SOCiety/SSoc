import { View, Text, Alert } from 'react-native';
import { Spacer } from '../../components/Basic/Spacer';
import AuthInput from '../../components/Input/AuthInput';
import * as Color from '../../components/Colors/colors';
import { Button } from '../../components/Basic/Button';
import { Typography } from '../../components/Basic/Typography';
import { useNavigation } from '@react-navigation/native';
import { useState } from 'react';

const UserInfoInput = (props) => {
  const navigation = useNavigation();
  const [emailCode, setEmailCode] = useState('');
  const [userPw, setUserPw] = useState('');
  const [userName, setUserName] = useState('');
  const [userNick, setUserNick] = useState('');
  const onPressRegister = () => {
    if (emailCode.length == 0 || userName.length < 1 || userNick == 0) {
      Alert.alert('정보를 모두 입력해주세요.');
    } else if (userPw.length < 8) {
      Alert.alert('비밀번호는 8자 이상부터 가능합니다.');
    } else if (props.emailCode != emailCode) {
      Alert.alert('이메일 인증번호를 다시 확인해주세요.');
    } else {
      navigation.reset({
        routes: [{ name: 'RegisterSuccess', onFinishLoad: props.onFinishLoad }],
      });
    }
  };
  return (
    <View>
      <Spacer space={10} />
      <AuthInput
        fontSize={16}
        title="이메일 인증번호"
        placeholder={props.emailCode}
        onChangeText={(text) => {
          setEmailCode(text);
        }}
      />
      <Spacer space={10} />
      <AuthInput
        fontSize={16}
        title="비밀번호"
        secureTextEntry={true}
        onChangeText={(text) => {
          setUserPw(text);
        }}
      />
      <Spacer space={10} />
      <AuthInput
        fontSize={16}
        title="이름"
        onChangeText={(text) => {
          setUserName(text);
        }}
      />
      <Spacer space={10} />
      <AuthInput
        fontSize={16}
        title="닉네임"
        onChangeText={(text) => {
          setUserNick(text);
        }}
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
