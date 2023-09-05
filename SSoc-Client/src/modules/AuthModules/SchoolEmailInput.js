import { View, Text, Alert } from 'react-native';
import { Button } from '../../components/Basic/Button';
import { Spacer } from '../../components/Basic/Spacer';
import { Typography } from '../../components/Basic/Typography';
import * as Color from '../../components/Colors/colors';

const SchoolEmailInput = (props) => {
  const email = props.email;
  const onPressCheckEmail = () => {
    // TO-DO
    // 8줄 email Server로 쏴서 response 받기
    // API로 메일 인증 버튼 클릭 시 인증번호가 오면 onPressCheck() 아니면 에러
    let res = 'testAuthCode';
    if (res != '-1') {
      props.onPressCheck();
      props.setEmailCode(res);
    } else {
      Alert.alert('잘못된 이메일입니다.');
    }
  };
  return (
    <View>
      <Typography
        fontSize={16}
        color={Color.DARK_BLUE}
      >
        <Text style={{ fontWeight: 'bold' }}>학교 이메일</Text>
      </Typography>
      <Spacer space={4} />
      <View style={{ flex: 1, flexDirection: 'row', justifyContent: 'space-between' }}>
        <View
          backgroundColor={Color.GRAY}
          borderRadius={4}
          style={{
            flex: 4,
            height: 45,
            alignContent: 'stretch',
            alignItems: 'center',
            justifyContent: 'center',
            flexDirection: 'row',
          }}
        >
          <Typography
            fontSize={16}
            color={Color.BLACK}
          >
            {email}
          </Typography>
        </View>
        <View style={{ marginLeft: 10, flex: 1 }}>
          <Button onPress={onPressCheckEmail}>
            <View
              backgroundColor={Color.BLUE}
              borderRadius={10}
              style={{
                height: 45,
                alignItems: 'center',
                justifyContent: 'center',
                flexDirection: 'row',
              }}
            >
              <Typography
                fontSize={14}
                color={Color.WHITE}
              >
                메일인증
              </Typography>
            </View>
          </Button>
        </View>
      </View>
    </View>
  );
};

export default SchoolEmailInput;
