import { useState } from 'react'
import { Modal, ScrollView, StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import * as Color from '../../../components/Colors/colors'
import { Icon } from '../../../components/Icons/Icons'
import BoardSearch from '../../../modules/Board/BoardSearch'
import ContentList from '../../../modules/Board/ContentList'
import WriteContent from '../../../modules/Board/WriteContentForm'

export const BoardContentScreen = (props) => {
  const [writeNewContent, setWriteNewContent] = useState(false)
  const board = props.board
  const groupMemberRole = props.groupMemberRole

  console.log('BoardContentScreen.js (props)', props)

  return (
    <View style={{ backgroundColor: Color.WHITE, marginBottom: 70 }}>
      <BoardSearch category={board.category} />
      <ContentList board={board} />
      {board.category == 'NOTICE' && groupMemberRole != 'MANAGER' ? null : (
        <View style={styles.plusBtn}>
          <TouchableOpacity
            onPress={() => {
              setWriteNewContent(true)
            }}
          >
            <Icon
              name="add-circle"
              size={50}
              color={Color.BLUE}
            />
          </TouchableOpacity>
        </View>
      )}

      <Modal
        animationType="fade"
        transparent={true}
        visible={writeNewContent}
        onBackdropPress={() => setWriteNewContent(false)}
      >
        <WriteContent
          board={board}
          groupMemberRole={groupMemberRole}
          setWriteNewContent={setWriteNewContent}
        />
      </Modal>
    </View>
  )
}

const styles = StyleSheet.create({
  plusBtn: {
    position: 'absolute',
    bottom: 30,
    right: 40,
  },
})
