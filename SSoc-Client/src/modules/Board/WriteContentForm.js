import { useState, useEffect } from 'react'
import { TouchableOpacity, TouchableWithoutFeedback, View, Text, ScrollView, Alert } from 'react-native'
import * as Color from '../../components/Colors/colors'
import { SingleLineInput } from '../../components/Input/SingleLineInput'
import { MultiLineInput } from '../../components/Input/MultiLineInput'
import CheckBox from 'react-native-check-box'
import { Button } from '../../components/Basic/Button'
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view'
import { getWritePostFetch } from '../../util/FetchUtil'
import { getTokens } from '../../util/TokenUtil'

const WriteContent = (props) => {
  const [isChecked, setIsChecked] = useState(false)
  const [inputTitle, setInputTitle] = useState('')
  const [inputContent, setInputContent] = useState('')

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  const board = props.board
  const groupMemberRole = props.groupMemberRole
  console.log('writeContentForm.js: ', props)

  const [options, setOptions] = useState({
    FREE: false,
    NOTICE: false,
    SUGGEST: false,
  })

  const onPressOption = (selectedOption) => {
    setOptions({
      FREE: false,
      NOTICE: false,
      SUGGEST: false,
      [selectedOption]: true,
    })
  }

  const getWritePostData = async () => {
    try {
      let selectedOption = board.category
      if (board.category == '') {
        selectedOption = Object.keys(options).find((key) => options[key] === true)
        if (!selectedOption) {
          Alert.alert('게시하고자 하는 게시판을 선택해주세요.')
          return
        }
      }
      if (inputTitle.length == 0 || inputContent == 0) {
        Alert.alert('제목과 내용을 필수로 입력해주세요.')
        return
      }
      const response = await getWritePostFetch(accessToken, refreshToken, board.groupId, inputTitle, inputContent, selectedOption, isChecked)
      const data = await response.json()
      if (data != null) {
        if (data.dataHeader.successCode == 0) {
          Alert.alert('게시글이 작성되었습니다.', props.setWriteNewContent(false))
        } else {
          Alert.alert(data.dataHeader.resultMessage, props.setWriteNewContent(false))
        }
        return
      }
      Alert.alert('게시글 작성에 실패했습니다.', props.setWriteNewContent(false))
    } catch (e) {
      console.log(e)
    }
  }

  useEffect(() => {
    if (!isTokenGet) {
      getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
    }
  }, [])

  return (
    <>
      <TouchableOpacity
        onPress={() => {
          props.setWriteNewContent(false)
        }}
        style={{ flex: 1, justifyContent: 'center', alignItems: 'center', backgroundColor: 'rgba(211, 211, 211, 0.5)' }}
      >
        <TouchableWithoutFeedback>
          <View style={{ flexDirection: 'column', width: '90%', backgroundColor: Color.WHITE, borderColor: Color.BLUE, borderWidth: 1, borderRadius: 20 }}>
            <View style={{ backgroundColor: Color.BLUE, width: '100%', height: board.boardId == 1 ? '10%' : '12%', borderTopRightRadius: 15, borderTopLeftRadius: 15, alignItems: 'center', justifyContent: 'center' }}>
              <Text style={{ fontSize: 20, color: Color.WHITE, fontWeight: 'bold' }}>{board.boardId == 1 ? '' : board.boardName + ' > '}게시글 작성</Text>
            </View>
            <KeyboardAwareScrollView>
              {board.category == '' ? (
                <View style={{ flex: 1, flexDirection: 'col', paddingHorizontal: 10, paddingVertical: 5, borderColor: Color.GRAY, borderWidth: 0.3, borderBottomWidth: 2, borderRadius: 5, margin: 10, marginBottom: 0 }}>
                  <View style={{ flex: 1, marginHorizontal: 4, marginVertical: 2 }}>
                    <Text style={{ fontSize: 16 }}>작성할 게시판 선택: </Text>
                  </View>
                  <View style={{ flex: 1, flexDirection: 'row', justifyContent: 'space-evenly', marginVertical: 5, alignItems: 'center' }}>
                    <Button
                      onPress={() => {
                        onPressOption('FREE')
                      }}
                    >
                      <View style={{ backgroundColor: options.FREE ? Color.BLUE : Color.WHITE, borderColor: Color.BLACK, borderWidth: 0.2, borderBottomWidth: 2, borderRadius: 5, padding: 10 }}>
                        <Text style={{ color: options.FREE ? Color.WHITE : Color.BLACK }}>자유게시판</Text>
                      </View>
                    </Button>
                    <Button
                      onPress={() => {
                        onPressOption('SUGGEST')
                      }}
                    >
                      <View style={{ backgroundColor: options.SUGGEST ? Color.BLUE : Color.WHITE, borderColor: Color.BLACK, borderWidth: 0.2, borderBottomWidth: 2, borderRadius: 5, padding: 10 }}>
                        <Text style={{ color: options.SUGGEST ? Color.WHITE : Color.BLACK }}>건의함</Text>
                      </View>
                    </Button>
                    {groupMemberRole == 'MANAGER' ? (
                      <Button
                        onPress={() => {
                          onPressOption('NOTICE')
                        }}
                      >
                        <View style={{ backgroundColor: options.NOTICE ? Color.BLUE : Color.WHITE, borderColor: Color.BLACK, borderWidth: 0.2, borderBottomWidth: 2, borderRadius: 5, padding: 10 }}>
                          <Text style={{ color: options.NOTICE ? Color.WHITE : Color.BLACK }}>공지게시판</Text>
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
                      placeholder="제목을 입력해주세요."
                      onChangeText={(text) => {
                        setInputTitle(text)
                      }}
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
                      onChangeText={(text) => {
                        setInputContent(text)
                      }}
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
              <TouchableOpacity
                onPress={() => {
                  getWritePostData()
                }}
              >
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
