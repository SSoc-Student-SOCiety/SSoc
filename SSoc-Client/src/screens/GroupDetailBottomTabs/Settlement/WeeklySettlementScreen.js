import { View } from "react-native";
import { Typography } from "../../../components/Basic/Typography";
import { StackHeader } from "../../../modules/StackHeader";
import { Dimensions } from "react-native";
import * as Color from "../../../components/Colors/colors";
import {
  LineChart,
  BarChart,
  PieChart,
  ProgressChart,
  ContributionGraph,
  StackedBarChart,
} from "react-native-chart-kit";

export const WeeklySettlementScreen = () => {
  return (
    <View style={{ flex: 1, backgroundColor: Color.WHITE }}>
      <View>
        <LineChart
          data={{
            labels: [
              "Jan",
              "Feb",
              "Mar",
              "Apr",
              "May",
              "Jun",
              "Jul",
              "Agu",
              "Sep",
              "Oct",
              "Nov",
              "Dec",
            ],
            datasets: [
              {
                data: [
                  Math.random() * 100,
                  Math.random() * 100,
                  Math.random() * 100,
                  Math.random() * 100,
                  Math.random() * 100,
                  Math.random() * 100,
                  Math.random() * 100,
                  Math.random() * 100,
                  Math.random() * 100,
                  Math.random() * 100,
                  Math.random() * 100,
                  Math.random() * 100,
                ],
              },
            ],
          }}
          width={Dimensions.get("window").width} // from react-native
          height={220}
          yAxisLabel="$"
          yAxisSuffix="won"
          yAxisInterval={1} // optional, defaults to 1
          chartConfig={{
            backgroundColor: Color.WHITE,
            backgroundGradientFrom: Color.WHITE,
            backgroundGradientTo: Color.WHITE,
            decimalPlaces: 2, // optional, defaults to 2dp
            color: (opacity = 1) => `rgba(0, 255,0, ${opacity})`,
            labelColor: (opacity = 1) => `rgba(0, 0, 0, ${opacity})`,
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
    </View>
  );
};
