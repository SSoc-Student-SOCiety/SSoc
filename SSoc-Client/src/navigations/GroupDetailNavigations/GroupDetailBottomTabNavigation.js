import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import React from "react";

import { BoardListScreen } from "../../screens/GroupDetailBottomTabs/Board/BoardListScreen";
import { BookingScreen } from "../../screens/GroupDetailBottomTabs/Booking/BookingScreen";
import { ScheduleScreen } from "../../screens/GroupDetailBottomTabs/Schedule/ScheduleScreen";
import { SettlementScreen } from "../../screens/GroupDetailBottomTabs/Settlement/SettlementScreen";

import { TabIcon } from "../../components/Icons/TabIcon";
const Tabs = createBottomTabNavigator();

export const GroupDetailBottomTabNavigation = () => {
  return (
    <Tabs.Navigator
      screenOptions={({ route }) => ({
        headerShown: false,
        tabBarIcon: ({ focused, color, size }) => {
          const getIconName = () => {
            if (route.name === "게시판") {
              return "brush";
            }
            if (route.name === "결산안") {
              return "analytics";
            }
            if (route.name == "일정") {
              return "calendar";
            }
            if (route.name == "물품 예약") {
              return "cart";
            }
          };
          const iconName = getIconName();
          return (
            <TabIcon iconName={iconName} iconColor={focused ? color : "gray"} />
          );
        },
      })}
    >
      <Tabs.Screen name="게시판" component={BoardListScreen}></Tabs.Screen>
      <Tabs.Screen name="결산안" component={SettlementScreen}></Tabs.Screen>
      <Tabs.Screen name="일정" component={ScheduleScreen}></Tabs.Screen>
      <Tabs.Screen name="물품 예약" component={BookingScreen}></Tabs.Screen>
    </Tabs.Navigator>
  );
};
