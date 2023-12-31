import React from 'react'
import { View, Text, Image, TouchableOpacity } from 'react-native'
import { Typography } from '../../components/Basic/Typography'
import { Logo } from '../../modules/Logo'
import * as Color from '../../components/Colors/colors'
import { Spacer } from '../../components/Basic/Spacer'
import { Button } from '../../components/Basic/Button'
import { useNavigation } from '@react-navigation/native'

const RegisterSuccessScreen = (props) => {
  const navigation = useNavigation()

  const onPressLogin = () => {
    navigation.reset({ routes: [{ name: 'Login' }] })
  }

  return (
    <View
      backgroundColor={Color.WHITE}
      style={{ flex: 1, alignContent: 'center' }}
    >
      <View style={{ flex: 0.2 }}>
        <Spacer space={16} />
        <Logo />
      </View>
      <View style={{ flex: 0.8, alignItems: 'center', justifyContent: 'center', margin: 20 }}>
        <Image
          source={require('../../../assets/success.png')}
          style={{ height: 90, width: 200 }}
          resizeMode="contain"
        />
        <Spacer space={16} />
        <Typography
          fontSize={16}
          color={Color.BLUE}
        >
          회원가입이 완료되었습니다!
        </Typography>
      </View>
      <View style={{ flex: 0.2, margin: 20 }}>
        <Spacer space={16} />
        <Button>
          <TouchableOpacity onPress={onPressLogin}>
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
                로그인 하러가기
              </Typography>
            </View>
          </TouchableOpacity>
        </Button>
      </View>
    </View>
  )
}
export default RegisterSuccessScreen
