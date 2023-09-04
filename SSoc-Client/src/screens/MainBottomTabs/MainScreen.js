import { View, ScrollView, StyleSheet } from "react-native/";
import { MainHeader } from "../../modules/MainHeader";
import { Logo } from "../../modules/Logo";
import { MainSearchBar } from "../../modules/MainSerachBar";
import { Typography } from "../../components/Basic/Typography";
import { Spacer } from "../../components/Basic/Spacer";
import { Card } from "../../modules/Card";
import { Button } from "../../components/Basic/Button";
import { useNavigation } from "@react-navigation/native";

export const MainScreen = () => {

  const navigation = useNavigation();
  const onPressDetail =()=>{
    console.log("test");
    navigation.navigate("StackDetailScreen");
  }

  return (
    <View style={{ flex: 1  }} backgroundColor={"white"}>
      <MainHeader name={"김싸피"} url={"https://picsum.photos/600"} />
      <Logo />
      <MainSearchBar />
      <View style={styles.mainItem}>
        <Typography fontSize={12}>자주 방문한 동아리 결산 보러가기</Typography>
        <Spacer space={4} />
        <ScrollView horizontal showsHorizontalScrollIndicator={false}>
          <Button onPress={onPressDetail}>
            <Card
              url={"https://picsum.photos/600"}
              schoolName={"한국대학교"}
              groupName={"정오"}
              groupType={"총학생회"}
              group={"제36대 총학생회"}
              val={1}
              unit={"days"}
            />
          </Button>
          <Card
            url={"https://picsum.photos/700"}
            schoolName={"한국대학교"}
            groupName={"정일"}
            groupType={"동아리"}
            group={"중앙 밴드동아리"}
            val={1}
            unit={"days"}
          />
          <Card
            url={"https://picsum.photos/500"}
            schoolName={"한국대학교"}
            groupName={"정일"}
            groupType={"동아리"}
            group={"중앙 밴드동아리"}
            val={1}
            unit={"days"}
          />
          <Card
            url={"https://picsum.photos/300"}
            schoolName={"한국대학교"}
            groupName={"정일"}
            groupType={"동아리"}
            group={"중앙 밴드동아리"}
            val={1}
            unit={"days"}
          />
        </ScrollView>
      </View>
    </View>
  );
};
var styles = StyleSheet.create({
  mainItem: { paddingTop: 60, paddingHorizontal: 20 },
  searchBar: { paddingTop: 30, paddingHorizontal: 20 },
});
