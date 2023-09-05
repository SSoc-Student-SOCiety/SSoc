import { Header } from "../components/header/Header";
import React, { useCallback } from "react";
import { useNavigation } from "@react-navigation/native";



export const StackHeader = (props) => {
  const navigation = useNavigation();
  //to-do 뒤로가기 구현하기
  //   const navigation = useNavigation();
  const onPressBack = useCallback(() => {
    console.log("go back button test ");
    navigation.goBack();
  }, []);

  //to-do setting 화면가기 구현하기
  const onPressSetting = useCallback(() => {
    console.log("setting butotn test");
    navigation.navigate("SettingScreen");
  }, []);

  return (
    <Header>
      <Header.Icon iconName="arrow-back" onPress={onPressBack} />

      <Header.Title title={props.title}></Header.Title>

      <Header.Icon iconName="ios-settings-outline" onPress={onPressSetting} />
    </Header>
  );
};
