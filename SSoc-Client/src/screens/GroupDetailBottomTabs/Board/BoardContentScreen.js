import { useState } from 'react'
import { Modal, ScrollView, StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import * as Color from '../../../components/Colors/colors'
import { Icon } from '../../../components/Icons/Icons'
import BoardSearch from '../../../modules/Board/BoardSearch'
import ContentList from '../../../modules/Board/ContentList'
import WriteContent from '../../../modules/Board/WriteContentForm'

export const BoardContentScreen = (props) => {
  const [writeNewContent, setWriteNewContent] = useState(false)
  const groupMemberRole = 'MANAGER'
  // const groupMemberRole = 'MEMBER'
  return (
    <View style={{ backgroundColor: Color.WHITE, marginBottom: 70 }}>
      <BoardSearch category={props.board.category} />
      <ContentList board={props.board} />
      {props.board.id == 2 && groupMemberRole != 'MANAGER' ? null : (
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
          board={props.board}
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
