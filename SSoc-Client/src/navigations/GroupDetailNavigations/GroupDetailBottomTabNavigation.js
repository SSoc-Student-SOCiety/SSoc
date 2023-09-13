import { createBottomTabNavigator } from '@react-navigation/bottom-tabs'

import { BoardDetailScreen } from '../../screens/GroupDetailBottomTabs/Board/BoardDetailScreen'
import { BookingTopTabs } from '../../screens/GroupDetailBottomTabs/Booking/BookingTopTabs'
import { ScheduleScreen } from '../../screens/GroupDetailBottomTabs/Schedule/ScheduleScreen'
import { SettlementsTopTabs } from '../../screens/GroupDetailBottomTabs/Settlement/SettlementsTopTabs'
import { TabIcon } from '../../components/Icons/TabIcon'
const Tabs = createBottomTabNavigator()

export const GroupDetailBottomTabNavigation = () => {
  return (
    <Tabs.Navigator
      screenOptions={({ route }) => ({
        headerShown: false,
        tabBarIcon: ({ focused, color, size }) => {
          const getIconName = () => {
            if (route.name === '게시판') {
              return 'brush'
            }
            if (route.name === '결산안') {
              return 'analytics'
            }
            if (route.name == '일정') {
              return 'calendar'
            }
            if (route.name == '물품 예약') {
              return 'cart'
            }
          }
          const iconName = getIconName()
          return (
            <TabIcon
              iconName={iconName}
              iconColor={focused ? color : 'gray'}
            />
          )
        },
      })}
    >
      <Tabs.Screen
        name="게시판"
        component={BoardDetailScreen}
      ></Tabs.Screen>
      <Tabs.Screen
        name="결산안"
        component={SettlementsTopTabs}
      ></Tabs.Screen>
      <Tabs.Screen
        name="일정"
        component={ScheduleScreen}
      ></Tabs.Screen>
      <Tabs.Screen
        name="물품 예약"
        component={BookingTopTabs}
      ></Tabs.Screen>
    </Tabs.Navigator>
  )
}
