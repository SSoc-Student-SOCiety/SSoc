import { NavigationContainer } from "@react-navigation/native";
import { createMaterialTopTabNavigator } from "@react-navigation/material-top-tabs";
import { TabActions } from "@react-navigation/native";
import { DailySettlementScreen } from "./DailySettlementScreen";
import { MonthlySettlementScreen } from "./MonthlySettlementSceen";
import { WeeklySettlementScreen } from "./WeeklySettlementScreen";
import { SafeAreaView } from "react-native-safe-area-context";
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
        <Tab.Screen name="Daily" component={DailySettlementScreen}></Tab.Screen>
        <Tab.Screen
          name="Weekly"
          component={WeeklySettlementScreen}
        ></Tab.Screen>
        <Tab.Screen
          name="Monthly"
          component={MonthlySettlementScreen}
        ></Tab.Screen>
      </Tab.Navigator>
    </View>
  );
};
