import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs'
import { View } from 'react-native'
import { StackHeader } from '../../../modules/StackHeader'
import { BoardAllContentScreen, BoardNoticeContentScreen, BoardSuggestContentScreen, BoardFreeContentScreen } from './BoardContentScreen'

const Tab = createMaterialTopTabNavigator()

export const BoardDetailTopTabs = (props) => {
  const groupId = props.groupId
  const groupMemberRole = props.groupMemberRole
  console.log('BoardDetailTopTabs.js (groupId) : ', groupId)
  console.log('BoardDetailTopTabs.js (groupMemberRole) : ', groupMemberRole)

  const boardList = [
    {
      groupId: groupId,
      boardName: '전체글',
      category: '',
    },
    {
      groupId: groupId,
      boardName: '공지게시판',
      category: 'NOTICE',
    },
    {
      groupId: groupId,
      boardName: '자유게시판',
      category: 'FREE',
    },
    {
      groupId: groupId,
      boardName: '건의함',
      category: 'SUGGEST',
    },
  ]

  return (
    <View style={{ flex: 1, backgroundColor: 'white' }}>
      <StackHeader title="게시판"></StackHeader>
      <Tab.Navigator
        screenOptions={({ route }) => ({
          headerShown: false,
        })}
      >
        <Tab.Screen
          name={boardList[0].boardName}
          component={BoardAllContentScreen}
          initialParams={{ board: boardList[0], groupMemberRole: groupMemberRole }}
        />
        <Tab.Screen
          name={boardList[1].boardName}
          component={BoardNoticeContentScreen}
          initialParams={{ board: boardList[1], groupMemberRole: groupMemberRole }}
        />
        <Tab.Screen
          name={boardList[2].boardName}
          component={BoardFreeContentScreen}
          initialParams={{ board: boardList[2], groupMemberRole: groupMemberRole }}
        />
        <Tab.Screen
          name={boardList[3].boardName}
          component={BoardSuggestContentScreen}
          initialParams={{ board: boardList[3], groupMemberRole: groupMemberRole }}
        />
      </Tab.Navigator>
    </View>
  )
}
