import { ScrollView, Text, View } from 'react-native'
import * as Color from '../../../components/Colors/colors'
import { BoardDetailTopTabs } from './BoardDetatilTopTab'

export const BoardDetailScreen = (props) => {
  return (
    <View style={{ flex: 1, backgroundColor: Color.WHITE }}>
      <BoardDetailTopTabs board={props.route.params.board} />
    </View>
  )
}
