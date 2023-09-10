import { ScrollView, Text, View } from 'react-native'
import * as Color from '../../../components/Colors/colors'
import { BoardTopTabs } from './BoardTopTab'

export const BoardListScreen = () => {
  return (
    <View style={{ flex: 1, backgroundColor: Color.WHITE }}>
      <BoardTopTabs />
    </View>
  )
}
