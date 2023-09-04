import { View, Text } from 'react-native';
import { Button } from '../../components/Basic/Button';
import { Spacer } from '../../components/Basic/Spacer';
import { Typography } from '../../components/Basic/Typography';
import * as Color from '../../components/Colors/colors';

const SchoolEmailInput = (props) => {
  const email = props.email;
  const onPressCheckEmail = () => {
    console.log('checkEmail');
    // TO-DO
    // API로 메일 인증 버튼 클릭 시 인증번호가 오면 onPressCheck() 아니면 에러
    let res = true;
    if (res) {
      props.onPressCheck();
      console.log(props.emailCode);
      props.setEmailCode('1234');
    } else {
      //   props.onPressCheck();
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
      <View style={{ flexDirection: 'row', justifyContent: 'space-between' }}>
        <View
          backgroundColor={Color.GRAY}
          borderRadius={4}
          style={{
            alignSelf: 'stretch',
            height: 45,
            width: 230,
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
        <Button onPress={onPressCheckEmail}>
          <View
            backgroundColor={Color.BLUE}
            borderRadius={10}
            style={{
              alignSelf: 'stretch',
              height: 45,
              width: 80,
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
  );
};

export default SchoolEmailInput;
