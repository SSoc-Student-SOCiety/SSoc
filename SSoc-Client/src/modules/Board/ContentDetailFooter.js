import { useState } from 'react'
import { View, Text, TouchableOpacity, Modal } from 'react-native'
import * as Color from '../../components/Colors/colors'
import { Icon } from '../../components/Icons/Icons'
import CommentModal from './CommentModal'

const ContentDetailFooter = (props) => {
  const content = props.content
  const [showComment, setShowComment] = useState(false)
  return (
    <View style={{ height: '10%', borderTopWidth: 0.5 }}>
      <View style={{ flex: 1, flexDirection: 'row', alignItems: 'center', justifyContent: 'flex-end', paddingHorizontal: 20 }}>
        {/* <TouchableOpacity>
          <View style={{ flexDirection: 'row', padding: 10, margin: 2, alignItems: 'center' }}>
            <Icon
              name="heart-outline"
              color={Color.RED}
              size={26}
            />
            <Text style={{ fontSize: 20 }}>{' ' + content.likes}</Text>
          </View>
        </TouchableOpacity> */}
        <TouchableOpacity
          onPress={() => {
            setShowComment(true)
          }}
        >
          <View style={{ flexDirection: 'row', padding: 15, paddingBottom: 25, margin: 3, alignItems: 'center' }}>
            <Icon
              name="md-chatbubble-ellipses-outline"
              color={Color.BLUE}
              size={26}
            />
            {/* 댓글 수 오면 수정 필요 */}
            {/* <Text style={{ fontSize: 20 }}>{' ' + content.comments}</Text> */}
          </View>
        </TouchableOpacity>
        <Modal
          animationType="slide"
          transparent={true}
          visible={showComment}
          onBackdropPress={() => setShowComment(false)}
          onRequestClose={() => {
            setShowComment(false)
          }}
        >
          <CommentModal
            setShowComment={setShowComment}
            content={content}
          />
        </Modal>
      </View>
    </View>
  )
}

export default ContentDetailFooter
