import { createMaterialTopTabNavigator } from "@react-navigation/material-top-tabs";
import { useSafeAreaInsets } from "react-native-safe-area-context";
import { View } from "react-native";
import { StackHeader } from "../../../../modules/StackHeader";
import { AddProductScreen } from "./AddProductScreen";
import { AllBookingListScreen } from "./AllBookingListScreen";
import { NotApprovedScreen } from "./NotApprovedScreen";
import { NotReturnedistScreen } from "./NotReturnedListScreen";
const Tab = createMaterialTopTabNavigator();

export const BookingManageTopTabs = (props) => {
  const insets = useSafeAreaInsets();
  const groupId = props.route.params.groupId;
  const groupMemberRole = props.route.params.groupMemberRole;

  return (
    <View style={{ flex: 1, backgroundColor: "white" }}>
      <StackHeader title="예약 관리"></StackHeader>
      <Tab.Navigator
        screenOptions={({ route }) => ({
          headerShown: false,
        })}
      >
        <Tab.Screen
          name="전체 예약"
          component={AllBookingListScreen}
          initialParams={{ groupId: groupId, groupMemberRole: groupMemberRole }}
        ></Tab.Screen>
        <Tab.Screen
          name="미반납 예약"
          component={NotReturnedistScreen}
          initialParams={{ groupId: groupId, groupMemberRole: groupMemberRole }}
        ></Tab.Screen>
        <Tab.Screen
          name="미승인 예약"
          component={NotApprovedScreen}
          initialParams={{ groupId: groupId, groupMemberRole: groupMemberRole }}
        ></Tab.Screen>
        <Tab.Screen
          name="물품 등록"
          component={AddProductScreen}
          initialParams={{ groupId: groupId, groupMemberRole: groupMemberRole }}
        ></Tab.Screen>
      </Tab.Navigator>
    </View>
  );
};
