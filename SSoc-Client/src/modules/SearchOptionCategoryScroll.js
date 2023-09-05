
import React, { useState } from "react";
import { View, ScrollView, StyleSheet } from "react-native";
import { Button } from "../components/Basic/Button";
import { CategoryButton } from "./CategoryButton";
import * as Color from "../components/Colors/colors";

export const SearchOptionCategoryScroll = () => {
  const [options, setOptions] = useState({
    allOption: true,
    onlySchoolOption: false,
    onlySocietyOption: false,
    onlyUnionOption: false,
  });

  const onPressOption = (selectedOption) => {
    setOptions({
      allOption: false,
      onlySchoolOption: false,
      onlySocietyOption: false,
      onlyUnionOption: false,
      [selectedOption]: true,
    });
  };

  return (
    <View style={styles.commonItem}>
      <ScrollView horizontal showsHorizontalScrollIndicator={false}>
        <Button onPress={() => onPressOption("allOption")}>
          <CategoryButton title={"전체 검색"} isActivated={options.allOption} />
        </Button>
        <Button onPress={() => onPressOption("onlySchoolOption")}>
          <CategoryButton title={"학교로 찾기"} isActivated={options.onlySchoolOption} />
        </Button>
        <Button onPress={() => onPressOption("onlySocietyOption")}>
          <CategoryButton title={"학생회로 찾기"} isActivated={options.onlySocietyOption} />
        </Button>
        <Button onPress={() => onPressOption("onlyUnionOption")}>
          <CategoryButton title={"동아리로 찾기"} isActivated={options.onlyUnionOption} />
        </Button>
      </ScrollView>
    </View>
  );
};

const styles = StyleSheet.create({
  commonItem: { paddingHorizontal: 10 },
});
