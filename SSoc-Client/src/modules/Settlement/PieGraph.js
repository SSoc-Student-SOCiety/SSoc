import { PieChart } from "react-native-chart-kit";
import { Dimensions } from "react-native";
import * as Color from "../../components/Colors/colors";
export const PieGraph = ({ data }) => {
  return (
    <PieChart
      data={data}
      width={Dimensions.get("window").width}
      height={250}
      chartConfig={{
        backgroundColor: Color.WHITE,
        backgroundGradientFrom: Color.WHITE,
        backgroundGradientTo: Color.WHITE,
        decimalPlaces: 2, // optional, defaults to 2dp
        color: (opacity = 1) => `rgba(0, 255,0, ${opacity})`,
        labelColor: (opacity = 1) => `rgba(0, 0, 0, ${opacity})`,
      }}
      style={{
        marginVertical: 2,
      }}
      accessor={"value"}
      backgroundColor={"transparent"}
      center={[15, 3]}
    />
  );
};
