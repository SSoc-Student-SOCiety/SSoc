import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs'
import { View } from 'react-native'
import { StackHeader } from '../../../modules/StackHeader'
import { AllBoardListScreen, BoardScreen } from './BoardScreen'
const Tab = createMaterialTopTabNavigator()

export const BoardTopTabs = () => {
  return (
    <View style={{ flex: 1, backgroundColor: 'white' }}>
      <StackHeader title="게시판"></StackHeader>
      <Tab.Navigator
        screenOptions={({ route }) => ({
          headerShown: false,
        })}
      >
        <Tab.Screen
          name="전체 게시판"
          component={BoardScreen}
        ></Tab.Screen>
        <Tab.Screen
          name="즐겨찾는 게시판"
          component={BoardScreen}
        ></Tab.Screen>
      </Tab.Navigator>
    </View>
  )
}
