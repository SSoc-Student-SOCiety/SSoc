import { createMaterialTopTabNavigator } from "@react-navigation/material-top-tabs";
import { useSafeAreaInsets } from "react-native-safe-area-context";
import { View } from "react-native";
import { StackHeader } from "../../../../modules/StackHeader";
import { MemberManageScreen } from "./MemberManageScreen";
import { RequestManageScreen } from "./RequestManageScreen";
const Tab = createMaterialTopTabNavigator();

export const MemberManageTopTabs = () => {
  const insets = useSafeAreaInsets();

  return (
    <View style={{ flex: 1, backgroundColor: "white" }}>
      <StackHeader title="소속원 관리"></StackHeader>
      <Tab.Navigator
        screenOptions={({ route }) => ({
          headerShown: false,
        })}
      >
        <Tab.Screen
          name="그룹원"
          component={MemberManageScreen}
        ></Tab.Screen>
        <Tab.Screen
          name="가입 신청"
          component={RequestManageScreen}
        ></Tab.Screen>
      </Tab.Navigator>
    </View>
  );
};
