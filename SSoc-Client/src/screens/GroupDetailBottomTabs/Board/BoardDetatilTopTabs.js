import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs'
import { View } from 'react-native'
import { StackHeader } from '../../../modules/StackHeader'
import { BoardContentScreen } from './BoardContentScreen'

const Tab = createMaterialTopTabNavigator()

export const BoardDetailTopTabs = () => {
  const tempData = {
    dataHeader: {
      successCode: 0,
      resultCode: null,
      resultMessage: null,
    },
    dataBody: [
      {
        groupId: '1',
        boardId: '1',
        boardName: '전체글',
        category: 'TOTAL',
      },
      {
        groupId: '1',
        boardId: '2',
        boardName: '공지게시판',
        category: 'ALERT',
      },
      {
        groupId: '1',
        boardId: '3',
        boardName: '자유게시판',
        category: 'FREE',
      },
      {
        groupId: '1',
        boardId: '4',
        boardName: '건의함',
        category: '건의',
      },
    ],
  }

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
        {tempData.dataBody.map((item) => {
          return (
            <Tab.Screen
              name={item.boardName}
              children={() => (
                <BoardContentScreen
                  key={item.boardId}
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
