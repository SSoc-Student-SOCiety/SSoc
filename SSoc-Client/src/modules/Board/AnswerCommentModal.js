import { useState } from 'react'
import { FlatList, KeyboardAvoidingView, Modal, Platform, Text, TouchableOpacity, TouchableWithoutFeedback, View } from 'react-native'
import CheckBox from 'react-native-check-box'
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view'
import { useRecoilValue } from 'recoil'
import * as Color from '../../components/Colors/colors'
import { Icon } from '../../components/Icons/Icons'
import { SingleLineInput } from '../../components/Input/SingleLineInput'
import { UserInfoState } from '../../util/RecoilUtil/Atoms'
import AnswerCommentCard from './AnswerCommentCard'
import CommentCard from './CommentCard'

const AnswerCommentModal = (props) => {
  const commentId = props.commentId
  const user = useRecoilValue(UserInfoState)
  const [isChecked, setIsChecked] = useState(false)

  const tempData = [
    {
      postId: '1',
      userId: '1',
      userNickName: '김싸피',
      parentId: '1',
      annonymous_flag: '1',
      content: '남는 무엇을 역사를 장식하는 품고 끓는다. 가슴이 어디 싹이 내는 청춘의 힘차게 칼이다.',
      delete_flag: '0',
      create_date: '2023-09-09',
    },
    {
      postId: '1',
      userId: '1',
      userNickName: '김신한',
      parentId: '1',
      annonymous_flag: '0',
      content: 'asdgasdgasdgsdgasdags',
      delete_flag: '1',
      create_date: '2023-09-09',
    },
    {
      postId: '1',
      userId: '1',
      userNickName: '김땡땡',
      parentId: '1',
      annonymous_flag: '1',
      content: '커다란 품었기 곳으로 이성은 있으랴?',
      delete_flag: '0',
      create_date: '2023-09-09',
    },
    {
      postId: '1',
      userId: '1',
      userNickName: '김땡땡',
      parentId: '1',
      annonymous_flag: '1',
      content: '커다란 품었기 곳으로 이성은 있으랴?',
      delete_flag: '0',
      create_date: '2023-09-09',
    },
    {
      postId: '1',
      userId: '1',
      userNickName: '김땡땡',
      parentId: '1',
      annonymous_flag: '1',
      content: '커다란 품었기 곳으로 이성은 있으랴?',
      delete_flag: '0',
      create_date: '2023-09-09',
    },
    {
      postId: '1',
      userId: '1',
      userNickName: '김땡땡',
      parentId: '1',
      annonymous_flag: '1',
      content: '커다란 품었기 곳으로 이성은 있으랴?',
      delete_flag: '0',
      create_date: '2023-09-09',
    },
  ]

  return (
    <>
      <TouchableOpacity
        onPress={() => {
          props.setAnswerCommentModal(false)
        }}
        style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}
      >
        <TouchableWithoutFeedback>
          <View style={{ flex: 1, width: '100%', marginTop: 80, backgroundColor: Color.WHITE, borderTopLeftRadius: 20, borderTopRightRadius: 20 }}>
            <TouchableOpacity
              style={{ position: 'absolute', top: 20, right: 20, zIndex: 1 }}
              onPress={() => {
                props.setAnswerCommentModal(false)
              }}
            >
              <Icon
                name="close"
                size={30}
                color={Color.WHITE}
              />
            </TouchableOpacity>
            <View style={{ width: '100%', height: 70, backgroundColor: Color.BLUE, borderTopLeftRadius: 20, borderTopRightRadius: 20, alignItems: 'center', justifyContent: 'center' }}>
              <Text style={{ color: Color.WHITE, fontSize: 20, fontWeight: 'bold' }}>답글</Text>
            </View>
            <KeyboardAvoidingView
              style={{ flex: 1, width: '100%', backgroundColor: Color.WHITE, borderTopLeftRadius: 20, borderTopRightRadius: 20 }}
              behavior={Platform.OS === 'ios' ? 'padding' : null}
              keyboardVerticalOffset={Platform.OS === 'ios' ? 70 : 0} // iOS에서 조정이 필요할 수 있습니다.
            >
              <FlatList
                data={tempData}
                renderItem={({ item }) => {
                  console.log(item)
                  return (
                    <View onStartShouldSetResponder={() => true}>
                      <AnswerCommentCard answerComment={item} />
                    </View>
                  )
                }}
              />
              <View style={{ height: 80, borderTopWidth: 1, borderColor: Color.BLACK }}>
                <View style={{ alignItems: 'center' }}>
                  <View style={{ flexDirection: 'row', borderColor: Color.GRAY, borderWidth: 1, width: '95%', height: 50, borderRadius: 10, marginVertical: 5, alignItems: 'center', justifyContent: 'space-around' }}>
                    <View style={{ flexDirection: 'row', justifyContent: 'center', alignItems: 'center', width: '15%' }}>
                      <CheckBox
                        onClick={() => {
                          setIsChecked(isChecked ? false : true)
                        }}
                        isChecked={isChecked}
                      />
                      <Text style={{ marginHorizontal: 5 }}>익명</Text>
                    </View>
                    <View style={{ width: '65%' }}>
                      <SingleLineInput placeholder="답글을 입력하세요." />
                    </View>
                    <View style={{ width: '5%' }}>
                      <TouchableOpacity>
                        <Icon
                          size={16}
                          color={Color.BLUE}
                          name="send"
                        />
                      </TouchableOpacity>
                    </View>
                  </View>
                </View>
              </View>
            </KeyboardAvoidingView>
          </View>
        </TouchableWithoutFeedback>
      </TouchableOpacity>
    </>
  )
}
export default AnswerCommentModal
