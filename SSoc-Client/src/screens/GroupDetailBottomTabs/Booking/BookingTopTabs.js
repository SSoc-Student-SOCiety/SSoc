import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs'
import { useSafeAreaInsets } from 'react-native-safe-area-context'
import { View } from 'react-native'
import { StackHeader } from '../../../modules/StackHeader'
import { MainBookingScreen } from './MainBookingScreen'
import { MyBookingListScreen } from './MyBookingListScreen'
const Tab = createMaterialTopTabNavigator()

export const BookingTopTabs = (props) => {
  const insets = useSafeAreaInsets()
  const groupId = props.route.params.groupId
  const groupMemberRole = props.route.params.groupMemberRole

  return (
    <View style={{ flex: 1, backgroundColor: 'white' }}>
      <StackHeader title="예약"></StackHeader>
      <Tab.Navigator
        screenOptions={({ route }) => ({
          headerShown: false,
        })}
      >
        <Tab.Screen
          name="물품 조회"
          component={MainBookingScreen}
          initialParams={{ groupId: groupId, groupMemberRole: groupMemberRole }}
        ></Tab.Screen>
        <Tab.Screen
          name="내 예약"
          component={MyBookingListScreen}
          initialParams={{ groupId: groupId, groupMemberRole: groupMemberRole }}
        ></Tab.Screen>
      </Tab.Navigator>
    </View>
  )
}
