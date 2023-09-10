import { View } from "react-native";
import { Typography } from "../../components/Basic/Typography";
import { Divider } from "../../components/Basic/Divider";
import { PieGraph } from "./PieGraph";
export const PieChartSection = ({ title, data }) => {
  return (
    <View>
      <View style={{ marginHorizontal: 25, marginVertical: 10, marginTop: 20 }}>
        <Typography fontSize={24}>{title}</Typography>
      </View>
      <Divider></Divider>
      <PieGraph data={data} />
    </View>
  );
};
