import React from "react";
import { View, ScrollView, StyleSheet } from "react-native";
import { MainHeader } from "../../modules/MainHeader";
import { Logo } from "../../modules/Logo";
import { MainSearchBar } from "../../modules/MainSerachBar";
import { Card } from "../../modules/Card";
import { useNavigation } from "@react-navigation/native";

export const MainScreen = () => {
  const navigation = useNavigation();

  const onPressDetail = () => {
    console.log("test");
    navigation.navigate("GroupDetailScreen");
  };

  return (
    <View style={styles.container}>
      <MainHeader name={"김싸피"} url={"https://picsum.photos/600"} />
      <Logo />
      <MainSearchBar />
      <ScrollView
        horizontal
        showsHorizontalScrollIndicator={false}
        style={styles.cardContainer}
      >
        {cardsData.map((card, index) => (
          <Card key={index} {...card} onPress={onPressDetail} />
        ))}
      </ScrollView>
    </View>
  );
};

const cardsData = [
  {
    url: "https://picsum.photos/600",
    schoolName: "한국대학교",
    groupName: "정오",
    groupType: "총학생회",
    group: "제36대 총학생회",
    val: "1",
    unit: "days",
  },
  {
    url: "https://picsum.photos/700",
    schoolName: "한국대학교",
    groupName: "정일",
    groupType: "동아리",
    group: "중앙 밴드동아리",
    val: "1",
    unit: "days",
  },
  {
    url: "https://picsum.photos/500",
    schoolName: "한국대학교",
    groupName: "정일",
    groupType: "동아리",
    group: "중앙 밴드동아리",
    val: "1",
    unit: "days",
  },
  {
    url: "https://picsum.photos/300",
    schoolName: "한국대학교",
    groupName: "정일",
    groupType: "동아리",
    group: "중앙 밴드동아리",
    val: "1",
    unit: "days",
  },
];

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: "white" },
  cardContainer: { paddingTop: 60, paddingHorizontal: 20 },
});
