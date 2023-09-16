import { View } from "react-native";
import { Typography } from "../../components/Basic/Typography";
import { Spacer } from "../../components/Basic/Spacer";
import { Divider } from "../../components/Basic/Divider";
import * as Color from "../../components/Colors/colors";

export const TransactionItem = ({ item }) => {
  return (
    <View>
      <View style={{ marginHorizontal: 10 }}>
        <View
          style={{
            marginHorizontal: 15,
            marginVertical: 4,
            flexDirection: "row",
            justifyContent: "space-between",
          }}
        >
          <View>
            <Typography fontSize={16}>{item.branch}</Typography>
            <Spacer space={3} />
            <Typography fontSize={12} color={Color.GRAY}>
              {item.detail}
            </Typography>
            <Spacer space={14} />
          </View>
          <View>
            <Typography fontSize={16} color={Color.RED}>
              -{item.withdrawal}원
            </Typography>
            <Spacer space={3} />
            <Typography fontSize={12} color={Color.GRAY}>
              {item.date}
            </Typography>
          </View>
        </View>
      </View>
      <Divider />
    </View>
  );
};
