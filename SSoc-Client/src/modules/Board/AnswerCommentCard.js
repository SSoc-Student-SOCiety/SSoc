import { View, Text, TouchableOpacity } from 'react-native'
import * as Color from '../../components/Colors/colors'

const AnswerCommentCard = (props) => {
  const comment = props.answerComment
  return (
    <View style={{ flex: 1, width: '100%', minHeight: 100, padding: 10, borderBottomColor: Color.BLUE, borderBottomWidth: 3, marginTop: 5, borderRadius: 15, borderWidth: 0.2 }}>
      <View style={{ flexDirection: 'column', width: '100%', height: '100%' }}>
        <View style={{ flexDirection: 'row', alignItems: 'center' }}>
          <Text style={{ fontSize: 18, color: Color.GRAY, fontWeight: 'bold', marginRight: 6 }}>{comment.annonymous_flag == '1' ? '익명' : comment.userNickName}</Text>
          <Text style={{ fontSize: 16, color: Color.GRAY, marginRight: 6 }}>{comment.create_date}</Text>
        </View>
        <View style={{ padding: 10 }}>
          <Text style={{ fontSize: 18 }}>{comment.delete_flag == '1' ? '삭제된 답글입니다.' : comment.content}</Text>
        </View>
        <View style={{ flexDirection: 'row', justifyContent: 'flex-end' }}>
          <TouchableOpacity
            onPress={() => {
              props.setAnswerCommentId(comment.postId)
              props.setAnswerCommentModal(true)
            }}
          ></TouchableOpacity>
        </View>
      </View>
    </View>
  )
}

export default AnswerCommentCard
