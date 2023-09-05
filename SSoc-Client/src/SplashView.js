import { useCallback, useEffect, useState } from 'react';
import AsyncStorage from '@react-native-async-storage/async-storage';
import AuthStackNavigation from './navigations/AuthNavigations/AuthStackNavigation';
import { useNavigation } from '@react-navigation/native';

export const SplashView = (props) => {
  //유저 식별 로직
  // const signUserIdentify = useCallback(() => {});
  const navigation = useNavigation();
  const [accessToken, setAccessToken] = useState(null);
  const [refleshToken, setRefleshToken] = useState(null);
  const tmpRefleshToken = {
    '': '',
  };

  //recoil 혹은 async storage에 저장되어있는 jwt로 로그인 시도
  const userSilentLogin = useCallback(() => {
    setTimeout(() => {
      //토큰에 저장된 값이 없을 경우
      //회원가입
      if (refleshToken === null || accessToken === null) {
        navigation.reset({
          routes: [{ name: 'SchoolEmail', onFinishLoad: props.onFinishLoad }],
        });
      } else {
        // 1. 토큰 존재
        // 1-1.리프레시 토큰 만료시
        //토큰 재발급
        // 1-2. 액세스 토큰 만료시
        //로그인 창
        // props.onFinishLoad();
      }
    }, 2000);
  }, []);

  //처음 앱 실행시 recoil 혹은 async storage에 저장되어있는 jwt로 로그인 시도
  useEffect(() => {
    const getTokens = async () => {
      const accessTokenData = JSON.parse(await AsyncStorage.getItem('accessToken'));
      const refleshTokenData = JSON.parse(await AsyncStorage.getItem('refleshToken'));
      if (accessTokenData !== null) setAccessToken(accessTokenData);
      if (refleshTokenData !== null) setRefleshToken(refleshTokenData);
    };
    getTokens();
    userSilentLogin();
  }, []);

  return <AuthStackNavigation />;
};
