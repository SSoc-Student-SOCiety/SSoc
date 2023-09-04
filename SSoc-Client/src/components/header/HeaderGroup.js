import React from "react";
import { View } from "react-native";

export const HeaderGroup = (props) => {
  return (
    <View
      style={{
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "space-between",
      }}
    >
      {props.children}
    </View>
  );
};
