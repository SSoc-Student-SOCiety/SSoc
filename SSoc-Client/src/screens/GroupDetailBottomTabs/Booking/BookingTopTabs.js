import { createMaterialTopTabNavigator } from "@react-navigation/material-top-tabs";
import { useSafeAreaInsets } from "react-native-safe-area-context";
import { View } from "react-native";
import { StackHeader } from "../../../modules/StackHeader";
import { MainBookingScreen } from "./MainBookingScreen";
import { MyBookingListScreen } from "./MyBookingListScreen";
const Tab = createMaterialTopTabNavigator();

export const BookingTopTabs = () => {
  const insets = useSafeAreaInsets();

  return (
    <View style={{ flex: 1, backgroundColor: "white" }}>
      <StackHeader title="예약"></StackHeader>
      <Tab.Navigator
        screenOptions={({ route }) => ({
          headerShown: false,
        })}
      >
        <Tab.Screen
          name="물품 조회"
          component={MainBookingScreen}
        ></Tab.Screen>
        <Tab.Screen
          name="내 예약"
          component={MyBookingListScreen}
        ></Tab.Screen>
      </Tab.Navigator>
    </View>
  );
};
