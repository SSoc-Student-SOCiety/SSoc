import { createNativeStackNavigator } from '@react-navigation/native-stack'
import { BoardDetailScreen } from '../../screens/GroupDetailBottomTabs/Board/BoardDetailScreen'
import { GroupDetailBottomTabNavigation } from './GroupDetailBottomTabNavigation'
import ContentDetailScreen from '../../screens/GroupDetailBottomTabs/Board/ContentDetailScreen'
import { AddScheduleScreen } from '../../screens/GroupDetailBottomTabs/Manage/AddScheduleScreen'
import { AddReceiptScreen } from '../../screens/GroupDetailBottomTabs/Manage/AddReceiptScreen'
import { BookingManageScreen } from '../../screens/GroupDetailBottomTabs/Manage/BookingManageScreen'
import { MemberManageScreen } from '../../screens/GroupDetailBottomTabs/Manage/MemberManage/MemberManageScreen'
import { MemberManageTopTabs } from '../../screens/GroupDetailBottomTabs/Manage/MemberManage/MemberManageTopTabs'

const Stack = createNativeStackNavigator()
export const GroupDetailStackNavigation = ({ route }) => {
  const { tabName } = route.params
  // TO-DO
  // 이전 GroupDetialScreen내에서 가져온 groupId를 GroupDetailTab의 route내부에서 계속해 끌고 올 듯 하다. + 그룹 매니저인지도 가져올 듯 합니다.
  // props로 다 연결해서 가져와주는 작업 필요
  // 우선 임시값 1로 주고 작업
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
        name ="AddScheduleScreen"
        component ={AddScheduleScreen}
        options={{tabName: tabName}}
        />
        <Stack.Screen 
        name ="AddReceiptScreen"
        component ={AddReceiptScreen}
        options={{tabName: tabName}}
        />
        <Stack.Screen 
        name ="BookingManageScreen"
        component ={BookingManageScreen}
        options={{tabName: tabName}}
        />
        <Stack.Screen 
        name ="MemberManageScreen"
        component ={MemberManageTopTabs}
        options={{tabName: tabName}}
        />
    </Stack.Navigator>
  )
}
