import { Header } from "../components/header/Header";
import React, { useCallback } from "react";
import { useNavigation } from "@react-navigation/native";
import { Ionicons } from "@expo/vector-icons";

export const StackHeader = (props) => {
  const navigation = useNavigation();
  //to-do 뒤로가기 구현하기
  //   const navigation = useNavigation();
  const onPressBack = useCallback(() => {
    navigation.goBack();
  }, []);

  //to-do setting 화면가기 구현하기


  return (
    <Header>
      <Header.Icon iconName="arrow-back" onPress={onPressBack} />

      <Header.Title title={props.title}></Header.Title>
      <Ionicons name="settings" color="white"/>
    </Header>
  );
};
