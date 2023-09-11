import { useState } from 'react'
import { TouchableOpacity, TouchableWithoutFeedback, View, Text, ScrollView } from 'react-native'
import * as Color from '../../components/Colors/colors'
import { SingleLineInput } from '../../components/Input/SingleLineInput'
import { MultiLineInput } from '../../components/Input/MultiLineInput'
import CheckBox from 'react-native-check-box'
import { Button } from '../../components/Basic/Button'
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view'

const WriteContent = (props) => {
  const [isChecked, setIsChecked] = useState(false)
  const board = props.board
  const groupMemberRole = props.groupMemberRole

  const [options, setOptions] = useState({
    freeBoardSelect: false,
    alertBoardSelect: false,
    askBoardSelect: false,
  })

  const onPressOption = (selectedOption) => {
    setOptions({
      freeBoardSelect: false,
      alertBoardSelect: false,
      askBoardSelect: false,
      [selectedOption]: true,
    })
  }

  return (
    <>
      <TouchableOpacity
        onPress={() => {
          props.setWriteNewContent(false)
        }}
        style={{ flex: 1, justifyContent: 'center', alignItems: 'center', backgroundColor: 'rgba(211, 211, 211, 0.5)' }}
      >
        <TouchableWithoutFeedback>
          <View style={{ flexDirection: 'column', width: '85%', backgroundColor: Color.WHITE, borderColor: Color.BLUE, borderWidth: 1, borderRadius: 20 }}>
            <View style={{ backgroundColor: Color.BLUE, width: '100%', height: board.boardId == 1 ? '10%' : '12%', borderTopRightRadius: 15, borderTopLeftRadius: 15, alignItems: 'center', justifyContent: 'center' }}>
              <Text style={{ fontSize: 20, color: Color.WHITE, fontWeight: 'bold' }}>{board.boardId == 1 ? '' : board.boardName + ' > '}게시글 작성</Text>
            </View>
            <KeyboardAwareScrollView>
              {board.boardId == 1 ? (
                <View style={{ flex: 1, flexDirection: 'col', paddingHorizontal: 10, paddingVertical: 5, borderColor: Color.GRAY, borderWidth: 0.3, borderBottomWidth: 2, borderRadius: 5, margin: 10, marginBottom: 0 }}>
                  <View style={{ flex: 1, marginHorizontal: 4, marginVertical: 2 }}>
                    <Text style={{ fontSize: 16 }}>작성할 게시판 선택: </Text>
                  </View>
                  <View style={{ flex: 1, flexDirection: 'row', justifyContent: 'space-evenly', marginVertical: 5, alignItems: 'center' }}>
                    <Button
                      onPress={() => {
                        onPressOption('freeBoardSelect')
                      }}
                    >
                      <View style={{ backgroundColor: options.freeBoardSelect ? Color.BLUE : Color.WHITE, borderColor: Color.BLACK, borderWidth: 0.2, borderBottomWidth: 2, borderRadius: 5, padding: 10 }}>
                        <Text style={{ color: options.freeBoardSelect ? Color.WHITE : Color.BLACK }}>자유게시판</Text>
                      </View>
                    </Button>
                    <Button
                      onPress={() => {
                        onPressOption('askBoardSelect')
                      }}
                    >
                      <View style={{ backgroundColor: options.askBoardSelect ? Color.BLUE : Color.WHITE, borderColor: Color.BLACK, borderWidth: 0.2, borderBottomWidth: 2, borderRadius: 5, padding: 10 }}>
                        <Text style={{ color: options.askBoardSelect ? Color.WHITE : Color.BLACK }}>건의함</Text>
                      </View>
                    </Button>
                    {groupMemberRole == 'MANAGER' ? (
                      <Button
                        onPress={() => {
                          onPressOption('alertBoardSelect')
                        }}
                      >
                        <View style={{ backgroundColor: options.alertBoardSelect ? Color.BLUE : Color.WHITE, borderColor: Color.BLACK, borderWidth: 0.2, borderBottomWidth: 2, borderRadius: 5, padding: 10 }}>
                          <Text style={{ color: options.alertBoardSelect ? Color.WHITE : Color.BLACK }}>공지게시판</Text>
                        </View>
                      </Button>
                    ) : null}
                  </View>
                </View>
              ) : null}
              <View style={{ flexDirection: 'column' }}>
                <View style={{ flex: 1, flexDirection: 'row', alignItems: 'center', paddingHorizontal: 10, paddingVertical: 5, borderColor: Color.GRAY, borderWidth: 0.3, borderBottomWidth: 2, borderRadius: 5, margin: 10, marginBottom: 0 }}>
                  <View style={{ width: '20%', justifyContent: 'center' }}>
                    <Text style={{ fontSize: 18 }}>제목: </Text>
                  </View>
                  <View style={{ width: '80%', backgroundColor: Color.LIGHT_GRAY, borderRadius: 5 }}>
                    <SingleLineInput
                      fontSize={18}
                      placeholder="제목을 입력해주세요."
                    />
                  </View>
                </View>

                <View style={{ flex: 1, flexDirection: 'row', alignItems: 'center', paddingHorizontal: 10, borderColor: Color.GRAY, borderWidth: 0.3, borderBottomWidth: 2, borderRadius: 5, margin: 10 }}>
                  <Text style={{ fontSize: 18 }}>익명 여부: </Text>
                  <CheckBox
                    style={{ flex: 1, padding: 10 }}
                    onClick={() => {
                      setIsChecked(isChecked ? false : true)
                    }}
                    isChecked={isChecked}
                  />
                </View>

                <View style={{ flex: 1, flexDirection: 'row', alignItems: 'center', paddingHorizontal: 10, paddingVertical: 5, borderColor: Color.GRAY, borderWidth: 0.3, borderBottomWidth: 2, borderRadius: 5, margin: 10, marginBottom: 0 }}>
                  <View style={{ width: '20%', justifyContent: 'center' }}>
                    <Text style={{ fontSize: 18 }}>내용: </Text>
                  </View>
                  <View style={{ width: '80%' }}>
                    <MultiLineInput
                      fontSize={18}
                      placeholder="내용을 입력해주세요."
                    />
                  </View>
                </View>
              </View>
            </KeyboardAwareScrollView>
            <View style={{ flexDirection: 'row', margin: 20, justifyContent: 'space-around' }}>
              <TouchableOpacity
                onPress={() => {
                  props.setWriteNewContent(false)
                }}
              >
                <View style={{ padding: 15, backgroundColor: Color.BLUE, borderRadius: 10 }}>
                  <Text style={{ fontSize: 18, color: Color.WHITE }}>작성 취소</Text>
                </View>
              </TouchableOpacity>
              <TouchableOpacity>
                <View style={{ padding: 15, backgroundColor: Color.DARK_BLUE, borderRadius: 10 }}>
                  <Text style={{ fontSize: 18, color: Color.WHITE }}>게시 하기</Text>
                </View>
              </TouchableOpacity>
            </View>
          </View>
        </TouchableWithoutFeedback>
      </TouchableOpacity>
    </>
  )
}

export default WriteContent
