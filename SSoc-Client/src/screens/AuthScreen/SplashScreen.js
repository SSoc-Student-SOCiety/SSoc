import React from 'react';
import { ActivityIndicator, View } from 'react-native';
import { Logo } from '../../modules/Logo';
import * as Color from '../../components/Colors/colors';

const SplashScreen = () => {
  return (
    <View
      style={{
        flex: 1,
        alignItems: 'center',
        justifyContent: 'center',
      }}
      backgroundColor={Color.BLUE}
    >
      <Logo />
      <ActivityIndicator
        color={Color.WHITE}
        size="large"
        style={{ alignItems: 'center', height: 80 }}
      />
    </View>
  );
};
export default SplashScreen;
