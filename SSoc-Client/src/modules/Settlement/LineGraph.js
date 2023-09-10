import { View, Dimensions } from "react-native";
import { LineChart } from "react-native-chart-kit";
import * as Color from "../../components/Colors/colors";

export const LineGraph = ({ labels, mockData }) => {
  return (
    <View style={{ marginHorizontal: 20 }}>
      <LineChart
        data={{
          labels: labels,
          datasets: [
            {
              data: mockData,
            },
          ],
        }}
        width={Dimensions.get("window").width} // from react-native
        height={220}
        yAxisSuffix="원"
        yAxisInterval={1000} // optional, defaults to 1
        chartConfig={{
          backgroundColor: Color.WHITE,
          backgroundGradientFrom: Color.WHITE,
          backgroundGradientTo: Color.WHITE,
          decimalPlaces: 2, // optional, defaults to 2dp
          color: (opacity = 0.7) => `rgba(0, 0,250, ${opacity})`,
          labelColor: (opacity = 0.8) => `rgba(0, 0, 0, ${opacity})`,
          style: {
            borderRadius: 10,
          },
          propsForDots: {
            r: "6",
            strokeWidth: "2",
            stroke: Color.WHITE,
          },
        }}
        bezier
        style={{
          marginVertical: 8,
          borderRadius: 16,
        }}
      />
    </View>
  );
};
