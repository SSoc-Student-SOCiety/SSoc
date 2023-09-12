import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs'
import { View } from 'react-native'
import { StackHeader } from '../../../modules/StackHeader'
import { BoardContentScreen } from './BoardContentScreen'

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

  // TO-DO
  // FlatList로 할지 map으로 할지 좀 더 고민해봐야할 듯
  // 무한스크롤 구현 필요
  return (
    <View style={{ flex: 1, backgroundColor: 'white' }}>
      <StackHeader title="게시판"></StackHeader>
      <Tab.Navigator
        screenOptions={({ route }) => ({
          headerShown: false,
        })}
      >
        {boardList.map((item) => {
          return (
            <Tab.Screen
              name={item.boardName}
              children={() => (
                <BoardContentScreen
                  key={item.category}
                  groupMemberRole={groupMemberRole}
                  board={item}
                />
              )}
            ></Tab.Screen>
          )
        })}
      </Tab.Navigator>
    </View>
  )
}
