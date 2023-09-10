import { FlatList, ScrollView, StyleSheet, Text, View } from 'react-native'
import BoardList from './BoardList'
import BoardSearch from './BoardSearch'
import * as Color from '../../../components/Colors/colors'
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view'

export const BoardScreen = () => {
  const groupId = '1'
  return (
    <ScrollView style={styles.container}>
      <BoardSearch />
      <View style={{ flexDirection: 'column' }}>
        <BoardList groupId={groupId} />
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
