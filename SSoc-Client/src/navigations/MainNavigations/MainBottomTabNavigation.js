import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import React from "react";

import { MainScreen } from "../../screens/MainBottomTabs/MainScreen";
import { SettingScreen } from "../../screens/MainBottomTabs/SettingScreen";
import { SearchResultScreen } from "../../screens/MainBottomTabs/SearchResultScreen";
import { TabIcon } from "../../components/Icons/TabIcon";
const Tabs = createBottomTabNavigator();

export const MainBottomTabNavigation = ({ tabName }) => {
  return (
    <Tabs.Navigator
      screenOptions={({ route }) => ({
        headerShown: false,
        tabBarIcon: ({ focused, color, size }) => {
          const getIconName = () => {
            if (route.name === "Main") {
              return "home";
            }
            if (route.name === "SearchResult") {
              return "compass";
            }
            if (route.name == "Setting") {
              return "ios-settings-outline";
            }
          };
          const iconName = getIconName();
          return (
            <TabIcon iconName={iconName} iconColor={focused ? color : "gray"} />
          );
        },
      })}
    >
      <Tabs.Screen name="Main" component={MainScreen}></Tabs.Screen>
      <Tabs.Screen
        name="SearchResult"
        component={SearchResultScreen}
      ></Tabs.Screen>
      <Tabs.Screen name="Setting" component={SettingScreen}></Tabs.Screen>
    </Tabs.Navigator>
  );
};
