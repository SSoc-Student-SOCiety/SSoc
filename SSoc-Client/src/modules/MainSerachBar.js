import { View } from "react-native";
import { Typography } from "../components/Basic/Typography";
import { SingleLineInput } from "../components/Input/SingleLineInput";
import { Button } from "../components/Basic/Button";
import { Spacer } from "../components/Basic/Spacer";
import { Icon } from "../components/Icons/Icons";
import { StyleSheet } from "react-native";
import * as Color from "../components/Colors/colors";

export const MainSearchBar = () => {
  return (
    <View style={styles.searchBar}>
      <Typography fontSize={15} fontColor={Color.GRAY}>
        결산 내역이 궁금하다면?{" "}
      </Typography>
      <View style={{ backgroundColor: Color.GRAY_BLUE, borderRadius: 3 }}>
        <SingleLineInput
          color={Color.GRAY}
          placeHolder="ex. 00대학교 총학생회"
        />
      </View>

      <Spacer space={10} />
      <Button>
        <View
          backgroundColor={Color.DARK_BLUE}
          borderRadius={10}
          style={{
            alignSelf: "stretch",
            height: 50,
            alignItems: "center",
            justifyContent: "center",
            flexDirection: "row",
          }}
        >
          <Typography fontSize={16} color={Color.WHITE}>
            학생회 / 동아리 공금 현황 보러가기
          </Typography>
          <Spacer space={10} horizontal={true} />
          <Icon name="airplane-outline" size={15} color={Color.WHITE} />
        </View>
      </Button>
    </View>
  );
};

var styles = StyleSheet.create({
  searchBar: { paddingTop: 30, paddingHorizontal: 20 },
});
