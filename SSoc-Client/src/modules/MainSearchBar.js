import { Touchable, View } from "react-native";
import { Typography } from "../components/Basic/Typography";
import { SingleLineInput } from "../components/Input/SingleLineInput";
import { Button } from "../components/Basic/Button";
import { Spacer } from "../components/Basic/Spacer";
import { Icon } from "../components/Icons/Icons";
import { StyleSheet } from "react-native";
import * as Color from "../components/Colors/colors";
import { useCallback } from "react";
import { TouchableOpacity } from "react-native";
import { SearchButton } from "./SearchButton";
export const MainSearchBar = () => {
  const onPressSearch = useCallback(() => {});

  return (
    <View style={styles.searchBar}>
      <Typography fontSize={15} fontColor={Color.GRAY}>
        결산 내역이 궁금하다면?{" "}
      </Typography>
      <View style={{ backgroundColor: Color.GRAY_BLUE }}>
        <SingleLineInput
          color={Color.GRAY}
          placeholder="✎) ex. 00대학교 총학생회"
        />
      </View>

      <Spacer space={10} />

      <Button onPress={onPressSearch}>
        <TouchableOpacity>
          <SearchButton
            color={Color.DARK_BLUE}
            title={"학생회 / 동아리 공금 현황 보러가기"}
            iconName={"airplane-outline"}
            isIcon={true}
          />
        </TouchableOpacity>
      </Button>
    </View>
  );
};

var styles = StyleSheet.create({
  searchBar: { paddingTop: 30, paddingHorizontal: 20 },
});
