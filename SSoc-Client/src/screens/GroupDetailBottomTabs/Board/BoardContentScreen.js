import { ScrollView, StyleSheet, Text, View } from 'react-native'
import BoardSearch from './BoardSearch'
import * as Color from '../../../components/Colors/colors'
import ContentList from './ContentList'

export const BoardContentScreen = (props) => {
  //   console.log(props.board)
  return (
    <ScrollView style={styles.container}>
      <BoardSearch />
      <View style={{ flexDirection: 'column' }}>
        <ContentList board={props.board} />
      </View>
    </ScrollView>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: Color.WHITE,
  },
})
