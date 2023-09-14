import { createNativeStackNavigator } from '@react-navigation/native-stack'
import { BoardDetailScreen } from '../../screens/GroupDetailBottomTabs/Board/BoardDetailScreen'
import { GroupDetailBottomTabNavigation } from './GroupDetailBottomTabNavigation'
import ContentDetailScreen from '../../screens/GroupDetailBottomTabs/Board/ContentDetailScreen'
import { AddScheduleScreen } from '../../screens/GroupDetailBottomTabs/Manage/AddScheduleScreen'
import { AddReceiptScreen } from '../../screens/GroupDetailBottomTabs/Manage/AddReceiptScreen'
import { BookingManageScreen } from '../../screens/GroupDetailBottomTabs/Manage/BookingManageScreen'

import { BookingItemDetailScreen } from '../../screens/GroupDetailBottomTabs/Booking/BookingItemDetailScreen'
import { MemberManageTopTabs } from '../../screens/GroupDetailBottomTabs/Manage/MemberManage/MemberManageTopTabs'

const Stack = createNativeStackNavigator()
export const GroupDetailStackNavigation = ({ route }) => {
  const { tabName } = route.params
  // TO-DO
  // 이전 GroupDetialScreen내에서 가져온 groupId를 GroupDetailTab의 route내부에서 계속해 끌고 올 듯 하다. + 그룹 매니저인지도 가져올 듯 합니다.
  // props로 다 연결해서 가져와주는 작업 필요
  // 아니면 그룹 정보 자체를 보내서 확인해도 됨. 정보 너무 많이 끌고 오면 별로일까봐 우선 board에서 가지고올 정보만 끌고 와봤습니다..
  // 우선 임시값 1, 'MANAGER'로 주고 작업
  const groupId = '1'
  const groupMemberRole = 'MANAGER'

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
      />
      <Stack.Screen
        name="ContentDetailScreen"
        component={ContentDetailScreen}
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
        component={MemberManageTopTabs}
        options={{ tabName: tabName }}
      />
        <Stack.Screen
        name="BookingItemDetialScreen"
        component = {BookingItemDetailScreen}
        options={{tabName: tabName}}
        />

    </Stack.Navigator>
  )
}
