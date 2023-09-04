import { Logo } from "./modules/Logo";
import * as Color from "./components/Colors/colors";
import { useCallback, useEffect, useState } from "react";
import { View } from "react-native";
export const SplashView = (props) => {
  //유저 식별 로직
  const signUserIdentify = useCallback(() => {});

  //recoil 혹은 async storage에 저장되어있는 jwt로 로그인 시도
  const userSilentLogin = useCallback(() => {
    setTimeout(() => {
      props.onFinishLoad();
    }, 2000);

    //토큰에 저장된 값이 없을 경우
    //회원가입

    //리프레시 토큰 만료시
    //토큰 재발급

    //액세스 토큰 만료시
    //로그인 창
  }, []);

  //처음 앱 실행시 recoil 혹은 async storage에 저장되어있는 jwt로 로그인 시도
  useEffect(() => {
    userSilentLogin();
  }, []);
  return (
    <View
      style={{
        flex: 1,
        alignItems: "center",
        justifyContent: "center",
      }}
      backgroundColor={Color.BLUE}
    >
      <Logo />
    </View>
  );
};
