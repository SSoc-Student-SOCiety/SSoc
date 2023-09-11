import { View } from 'react-native'
import * as Color from '../../../components/Colors/colors'
import { BoardDetailTopTabs } from './BoardDetatilTopTabs'

export const BoardDetailScreen = () => {
  return (
    <View style={{ flex: 1, backgroundColor: Color.WHITE }}>
      <BoardDetailTopTabs />
    </View>
  )
}
