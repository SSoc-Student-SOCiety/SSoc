import { useCallback, useEffect, useState } from 'react';
import AuthStackNavigation from './navigations/AuthNavigations/AuthStackNavigation';
import { useNavigation } from '@react-navigation/native';
import { setAsync, getAsync } from './util/AsyncModule';

export const SplashView = (props) => {
  const navigation = useNavigation();
  const [accessToken, setAccessToken] = useState(null);
  const [refleshToken, setRefleshToken] = useState(null);

  // TO-DO
  // async storage에 저장되어있는 jwt로 로그인 시도
  const userSilentLogin = useCallback(() => {
    setTimeout(() => {
      //토큰에 저장된 값이 없을 경우
      //회원가입
      if (refleshToken === null || accessToken === null) {
        navigation.reset({
          routes: [{ name: 'SchoolEmail', onFinishLoad: props.onFinishLoad }],
        });
      } else {
        if (accessToken.date > 0) {
          // 1. accessToken 살아있음
          // 바로 메인화면으로 가기
          props.onFinishLoad();
        } else if (refleshToken.date > 0) {
          // 2. refleshToken 살아있음
          // API에 새로운 토큰들 달라고 요청 후 메인화면
          props.onFinishLoad();
        } else if (refleshToken.date < 0) {
          // 3. accessToken, refleshToken 모두 죽음
          // 로그인 화면으로 가기
          navigation.reset({
            routes: [{ name: 'Login', onFinishLoad: props.onFinishLoad }],
          });
        }
      }
      // navigation.reset({
      //   routes: [{ name: 'Login', onFinishLoad: props.onFinishLoad }],
      // });
    }, 2000);
  }, []);

  //처음 앱 실행시 recoil 혹은 async storage에 저장되어있는 jwt로 로그인 시도
  useEffect(() => {
    // TO-DO
    // API 나오면 주석 해제 후 작업
    // const getTokens = async () => {
    //   const accessTokenData = getAsync('accessToken');
    //   const refleshTokenData = getAsync('refleshToken');
    //   if (accessTokenData !== null) setAccessToken(accessTokenData);
    //   if (refleshTokenData !== null) setRefleshToken(refleshTokenData);
    // };
    // getTokens();
    userSilentLogin();
  }, []);

  return <AuthStackNavigation />;
};
