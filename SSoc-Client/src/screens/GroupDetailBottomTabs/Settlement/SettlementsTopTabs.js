import { createMaterialTopTabNavigator } from "@react-navigation/material-top-tabs";
import { SettlementReportScreen } from "./SettlementReportScreen";
import { MonthlySettlementScreen } from "./MonthlySettlementSceen";
import { DaillySettlementScreen } from "./DailySettlementScreen";
import { useSafeAreaInsets } from "react-native-safe-area-context";
import { View } from "react-native";
import { StackHeader } from "../../../modules/StackHeader";
const Tab = createMaterialTopTabNavigator();

export const SettlementsTopTabs = () => {
  const insets = useSafeAreaInsets();

  return (
    <View style={{ flex: 1, backgroundColor: "white" }}>
      <StackHeader title="정산안"></StackHeader>
      <Tab.Navigator
        screenOptions={({ route }) => ({
          headerShown: false,
        })}
      >
        <Tab.Screen
          name="Report"
          component={SettlementReportScreen}
        ></Tab.Screen>
        <Tab.Screen
          name="Daily"
          component={DaillySettlementScreen}
        ></Tab.Screen>
        <Tab.Screen
          name="Monthly"
          component={MonthlySettlementScreen}
        ></Tab.Screen>
      </Tab.Navigator>
    </View>
  );
};
