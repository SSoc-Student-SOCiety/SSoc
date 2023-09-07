import React from "react";
import { View, ScrollView, StyleSheet } from "react-native";
import { MainHeader } from "../../modules/MainHeader";
import { Logo } from "../../modules/Logo";
import { MainSearchBar } from "../../modules/MainSerachBar";
import { Card } from "../../modules/Card";
import { useNavigation } from "@react-navigation/native";

export const MainScreen = () => {
  const navigation = useNavigation();

 
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
          
          <Card key={index} {...card} />
        ))}
      </ScrollView>
    </View>
  );
};

const cardsData = [
  {
    url: "https://picsum.photos/600",
    school: "한국대학교",
    name: "정오",
    groupType: "총학생회",
    subName: "제36대 총학생회",
    val: "3",
    unit: "days",
  },
  {
    url: "https://picsum.photos/700",
    school: "연세대학교",
    name: "정일",
    groupType: "동아리",
    subName: "중앙 밴드동아리",
    val: "1",
    unit: "days",
  },
  {
    url: "https://picsum.photos/500",
    school: "서울대학교",
    name: "싸피",
    groupType: "동아리",
    subName: "알고리즘 동아리",
    val: "1",
    unit: "hours",
  },
  {
    url: "https://picsum.photos/300",
    school: "고려대학교",
    name: "mdn",
    groupType: "동아리",
    subName: "사회혁신 학회",
    val: "1",
    unit: "miniutes",
  },
];

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: "white" },
  cardContainer: { paddingTop: 60, paddingHorizontal: 20 },
});
