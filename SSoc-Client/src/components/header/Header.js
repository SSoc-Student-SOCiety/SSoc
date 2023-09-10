import React from "react";
import { useSafeAreaInsets } from "react-native-safe-area-context";
import { View, useWindowDimensions } from "react-native";
import { Spacer } from "../Basic/Spacer";
import { HeaderTitle } from "./HeaderTitle";
import { HeaderIcon } from "./HeaderButton";
import { HeaderGroup } from "./HeaderGroup";
import { SafeAreaProvider } from "react-native-safe-area-context";
import { GRAY } from "../Colors/colors";
export const Header = (props) => {
  const insets = useSafeAreaInsets();
  const { width } = useWindowDimensions();

  return (
    <View style={{ paddingTop: insets.top }}>
      <View
        style={{
          width: width,
          flexDirection: "row",
          height: 56,
          borderBottomColor: GRAY,
          borderBottomWidth: 1,
          alignItems: "center",
        }}
      >
        <Spacer horizontal={true} space={12} />
        <View
          style={{
            flex: 1,
            flexDirection: "row",
            justifyContent: "space-between",
            alignItems: "center",
          }}
        >
          {props.children}
        </View>
        <Spacer horizontal={true} space={12} />
      </View>
    </View>
  );
};

Header.Title = HeaderTitle;
Header.Icon = HeaderIcon;
Header.Group = HeaderGroup;
