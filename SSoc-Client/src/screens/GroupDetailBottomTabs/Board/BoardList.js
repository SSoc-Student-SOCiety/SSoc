import { useNavigation } from '@react-navigation/native'
import { TouchableOpacity, View, Text, FlatList, ScrollView } from 'react-native'
import { Divider } from '../../../components/Basic/Divider'
import { Typography } from '../../../components/Basic/Typography'
import * as Color from '../../../components/Colors/colors'

const BoardList = (props) => {
  const navigation = useNavigation()
  const groupId = props.groupId

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
        boardName: '전체글 보기',
        category: 'TOTAL',
      },
      {
        groupId: '1',
        boardId: '2',
        boardName: '공지 게시판',
        category: 'ARLET',
      },
      {
        groupId: '1',
        boardId: '3',
        boardName: '자유 게시판',
        category: 'FREE',
      },
      {
        groupId: '1',
        boardId: '4',
        boardName: '일정 게시판',
        category: 'SCEDULE',
      },
      {
        groupId: '1',
        boardId: '5',
        boardName: '대여하기',
        category: '대여',
      },
      {
        groupId: '1',
        boardId: '6',
        boardName: '건의함',
        category: '건의',
      },
    ],
  }

  return (
    <View>
      {tempData.dataBody.map((item) => (
        <View>
          <TouchableOpacity
            onPress={() => {
              navigation.navigate('BoardDetailScreen', { board: item })
            }}
          >
            <View style={{ marginBottom: 10, marginTop: 18, marginHorizontal: 10, paddingLeft: 20, justifyContent: 'center' }}>
              <Typography fontSize={16}>
                <Text style={{ color: Color.GRAY }}>{item.boardName}</Text>
              </Typography>
            </View>
          </TouchableOpacity>
          <Divider />
        </View>
      ))}
    </View>
  )
}

export default BoardList
