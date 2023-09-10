import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs'
import { useSafeAreaInsets } from 'react-native-safe-area-context'
import { View } from 'react-native'
import { StackHeader } from '../../../modules/StackHeader'
import { BoardContentScreen } from './BoardContentScreen'
const Tab = createMaterialTopTabNavigator()

export const BoardDetailTopTabs = (props) => {
  const board = props.board
  return (
    <View style={{ flex: 1, backgroundColor: 'white' }}>
      <StackHeader title="게시판"></StackHeader>
      <Tab.Navigator
        screenOptions={({ route }) => ({
          headerShown: false,
        })}
      >
        <Tab.Screen
          name={board.boardName}
          children={() => <BoardContentScreen board={board} />}
        ></Tab.Screen>
        <Tab.Screen
          name="공지 사항"
          children={() => <BoardContentScreen board={board} />}
        ></Tab.Screen>
      </Tab.Navigator>
    </View>
  )
}
