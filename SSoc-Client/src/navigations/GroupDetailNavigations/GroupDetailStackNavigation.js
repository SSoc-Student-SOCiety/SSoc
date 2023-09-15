import { createNativeStackNavigator } from '@react-navigation/native-stack'
import { BoardDetailScreen } from '../../screens/GroupDetailBottomTabs/Board/BoardDetailScreen'
import { GroupDetailBottomTabNavigation } from './GroupDetailBottomTabNavigation'
import ContentDetailScreen from '../../screens/GroupDetailBottomTabs/Board/ContentDetailScreen'
import { AddScheduleScreen } from '../../screens/GroupDetailBottomTabs/Manage/AddScheduleScreen'
import { AddReceiptScreen } from '../../screens/GroupDetailBottomTabs/Manage/AddReceiptScreen'
import { BookingManageScreen } from '../../screens/GroupDetailBottomTabs/Manage/BookingManageScreen'
import { BookingItemDetailScreen } from '../../screens/GroupDetailBottomTabs/Booking/BookingItemDetailScreen'
import { MemberManageScreen } from '../../screens/GroupDetailBottomTabs/Manage/MemberManage/MemberManageScreen'
import { MemberManageTopTabs } from '../../screens/GroupDetailBottomTabs/Manage/MemberManage/MemberManageTopTabs'
const Stack = createNativeStackNavigator()
export const GroupDetailStackNavigation = ({ route }) => {
  const { tabName } = route.params.tabName
  const groupId = route.params.groupId
  const groupMemberRole = route.params.groupMemberRole

  return (
    <Stack.Navigator
      screenOptions={{
        headerShown: false,
      }}
    >
      <Stack.Screen
        name="GroupDetailBottomTab"
        initialParams={{ groupId: groupId, groupMemberRole: groupMemberRole }}
        component={GroupDetailBottomTabNavigation}
        options={{ tabName: tabName }}
      />

      <Stack.Screen
        name="BoardDetailScreen"
        component={BoardDetailScreen}
        options={{ tabName: tabName }}
      />
      <Stack.Screen
        name="ContentDetailScreen"
        component={ContentDetailScreen}
        options={{ tabName: tabName }}
      />
      <Stack.Screen
        name="AddScheduleScreen"
        component={AddScheduleScreen}
        options={{ tabName: tabName }}
      />
      <Stack.Screen
        name="AddReceiptScreen"
        component={AddReceiptScreen}
        options={{ tabName: tabName }}
      />
      <Stack.Screen
        name="BookingManageScreen"
        component={BookingManageScreen}
        options={{ tabName: tabName }}
      />
      <Stack.Screen
        name="MemberManageScreen"
        component={MemberManageScreen}
        options={{ tabName: tabName }}
      />
      <Stack.Screen
        name="BookingItemDetialScreen"
        component={BookingItemDetailScreen}
        options={{ tabName: tabName }}
      />
      <Stack.Screen
        name="MemberManageTopTabs"
        component={MemberManageTopTabs}
        initialParams={{ groupId: groupId, groupMemberRole: groupMemberRole }}
        options={{ tabName: tabName }}
      ></Stack.Screen>
    </Stack.Navigator>
  )
}
