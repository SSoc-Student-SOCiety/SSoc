import React, { useCallback } from "react";
import { Header } from "../components/header/Header";
import { View } from "react-native";
import { ProfileImage } from "./ProfileImage";
import { Typography } from "../components/Basic/Typography";
import { StyleSheet } from "react-native";
import { useNavigation } from "@react-navigation/native";
export const MainHeader = (props) => {
  const navigation = useNavigation();
  //to-do setting 화면가기 구현하기
  const onPressSetting = useCallback(() => {
    console.log("setting button test");
    navigation.navigate("SettingScreen");
  }, []);
  return (
    <Header>
      <View flexDirection="row">
        <ProfileImage size={40} url={props.url} />
        <View>
          <Typography fontSize={13}>안녕하세요</Typography>
          <Typography fontSize={20}>{props.name} 님</Typography>
        </View>
      </View>
      <Header.Icon iconName="ios-settings-outline" onPress={onPressSetting} />
    </Header>
  );
};

var styles = StyleSheet.create({
  mainItem: { paddingTop: 60, paddingHorizontal: 20 },
});
