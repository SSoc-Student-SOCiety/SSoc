import { View } from "react-native";
import { Typography } from "../../components/Basic/Typography";
import * as Color from "../../components/Colors/colors";
export const SummaryCard = ({ category, result }) => {
  return (
    <View
      style={{
        width: 300,
        height: 35,
        borderRadius: 10,
        marginTop: 12,
        borderColor: Color.WHITE,
        borderWidth: 1,
        justifyContent: "center",
        alignContent: "center",
        padding: 3,
      }}
    >
      <View
        style={{
          justifyContent: "space-between",
          alignContent: "center",
          flexDirection: "row",
          paddingHorizontal: 10,
        }}
      >
        <Typography fontSize={20} color={Color.WHITE}>
          {category}
        </Typography>
        <Typography fontSize={20} color={Color.WHITE}>
          {result}원
        </Typography>
      </View>
    </View>
  );
};
