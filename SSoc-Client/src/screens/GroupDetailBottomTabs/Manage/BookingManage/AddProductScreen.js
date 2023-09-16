import { useState, useEffect } from 'react'
import { View, Text, TouchableOpacity, Alert } from 'react-native'
import CheckBox from 'react-native-check-box'
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view'
import * as Color from '../../../../components/Colors/colors'
import { MultiLineInput } from '../../../../components/Input/MultiLineInput'
import { SingleLineInput } from '../../../../components/Input/SingleLineInput'
import { Button } from '../../../../components/Basic/Button'
import { getProductCreateFetch } from '../../../../util/FetchUtil'
import { useNavigation } from '@react-navigation/native'
import { getTokens } from '../../../../util/TokenUtil'

export const AddProductScreen = (props) => {
  console.log(props)
  const naviagtion = useNavigation()

  const [inputName, setInputName] = useState('')
  const [inputContent, setInputContent] = useState('')
  const [inputDescription, setInputDescription] = useState('')
  const [imageUrl, setImageUrl] = useState('')

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  const [options, setOptions] = useState({
    CONVENIENCE: false,
    PARTY: false,
    BOOK: false,
  })

  const onPressOption = (selectedOption) => {
    setOptions({
      CONVENIENCE: false,
      PARTY: false,
      BOOK: false,
      [selectedOption]: true,
    })
  }

  useEffect(() => {
    getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
  }, [])

  const getProductCreateData = async () => {
    try {
      selectedOption = Object.keys(options).find((key) => options[key] === true)
      if (!selectedOption) {
        Alert.alert('등록하고자 하는 물품의\n카테고리를 선택해주세요.')
        return
      } else if (inputName.length == 0) {
        Alert.alert('등록하고자 하는 물품의\n이름을 작성해주세요.')
        return
      } else if (inputContent.length == 0) {
        Alert.alert('등록하고자 하는 물품의\n소개를 작성해주세요.')
        return
      } else if (inputDescription.length == 0) {
        Alert.alert('등록하고자 하는 물품의\n설명을 작성해주세요.')
        return
      }

      const response = await getProductCreateFetch(accessToken, refreshToken, props.route.params.groupId, selectedOption, inputName, inputDescription, inputContent, imageUrl)
      const data = await response.json()
      console.log(data)
      if (data != null && data.dataHeader != undefined) {
        if (data.dataHeader.successCode == 0) {
          Alert.alert('물품을 등록하였습니다.', '', [
            {
              text: '확인',
              onPress: () => {
                setImageUrl('')
                setInputContent('')
                setInputDescription('')
                setInputName('')
                naviagtion.goBack()
              },
            },
          ])
        }
      } else {
        Alert.alert('서버 통신 오류', '다시 시도해주세요.')
      }
    } catch (e) {
      console.log(e)
    }
  }

  return (
    <View style={{ flex: 1, flexDirection: 'column', backgroundColor: Color.WHITE, paddingBottom: 40 }}>
      <KeyboardAwareScrollView>
        {/* 카테고리 선택란 CONVENIENCE, PARTY, BOOK */}
        <View style={{ flex: 1, flexDirection: 'col', paddingHorizontal: 10, paddingVertical: 5, borderColor: Color.GRAY, borderWidth: 0.3, borderBottomWidth: 2, borderRadius: 5, margin: 10, marginBottom: 0 }}>
          <View style={{ flex: 1, marginHorizontal: 4, marginVertical: 2 }}>
            <Text style={{ fontSize: 16 }}>카테고리 선택: </Text>
          </View>
          <View style={{ flex: 1, flexDirection: 'row', justifyContent: 'space-evenly', marginVertical: 5, alignItems: 'center' }}>
            {/* 행사용품 */}
            <Button
              onPress={() => {
                onPressOption('CONVENIENCE')
              }}
            >
              <View style={{ backgroundColor: options.CONVENIENCE ? Color.BLUE : Color.WHITE, borderColor: Color.BLACK, borderWidth: 0.2, borderBottomWidth: 2, borderRadius: 5, padding: 10 }}>
                <Text style={{ color: options.CONVENIENCE ? Color.WHITE : Color.BLACK }}>편의성</Text>
              </View>
            </Button>

            {/* 전공서적 */}
            <Button
              onPress={() => {
                onPressOption('PARTY')
              }}
            >
              <View style={{ backgroundColor: options.PARTY ? Color.BLUE : Color.WHITE, borderColor: Color.BLACK, borderWidth: 0.2, borderBottomWidth: 2, borderRadius: 5, padding: 10 }}>
                <Text style={{ color: options.PARTY ? Color.WHITE : Color.BLACK }}>행사용품</Text>
              </View>
            </Button>

            {/* 편의성 */}
            <Button
              onPress={() => {
                onPressOption('BOOK')
              }}
            >
              <View style={{ backgroundColor: options.BOOK ? Color.BLUE : Color.WHITE, borderColor: Color.BLACK, borderWidth: 0.2, borderBottomWidth: 2, borderRadius: 5, padding: 10 }}>
                <Text style={{ color: options.BOOK ? Color.WHITE : Color.BLACK }}>전공서적</Text>
              </View>
            </Button>
          </View>
        </View>

        {/* 상품등록 name, description, content, imageUrl */}
        <View style={{ flexDirection: 'column' }}>
          {/* 물품명 */}
          <View style={{ flex: 1, flexDirection: 'row', alignItems: 'center', paddingHorizontal: 10, paddingVertical: 5, borderColor: Color.GRAY, borderWidth: 0.3, borderBottomWidth: 2, borderRadius: 5, margin: 10, marginBottom: 0 }}>
            <View style={{ width: '22%', justifyContent: 'center' }}>
              <Text style={{ fontSize: 16 }}>물품명: </Text>
            </View>
            <View style={{ width: '78%', backgroundColor: Color.LIGHT_GRAY, borderRadius: 5 }}>
              <SingleLineInput
                fontSize={16}
                placeholder="물품명을 입력해주세요."
                onChangeText={(text) => {
                  setInputName(text)
                }}
              />
            </View>
          </View>

          {/* content 물품 한 줄 소개 */}
          <View style={{ flex: 1, flexDirection: 'row', alignItems: 'center', paddingHorizontal: 10, paddingVertical: 5, borderColor: Color.GRAY, borderWidth: 0.3, borderBottomWidth: 2, borderRadius: 5, margin: 10, marginBottom: 0 }}>
            <View style={{ width: '22%', justifyContent: 'center' }}>
              <Text style={{ fontSize: 16 }}>한 줄 소개: </Text>
            </View>
            <View style={{ width: '78%', backgroundColor: Color.LIGHT_GRAY, borderRadius: 5 }}>
              <SingleLineInput
                fontSize={16}
                placeholder="물품 한 줄 소개를 입력해주세요."
                onChangeText={(text) => {
                  setInputContent(text)
                }}
              />
            </View>
          </View>

          {/* description 물품 설명 */}
          <View style={{ flex: 1, flexDirection: 'row', alignItems: 'center', paddingHorizontal: 10, paddingVertical: 5, borderColor: Color.GRAY, borderWidth: 0.3, borderBottomWidth: 2, borderRadius: 5, margin: 10, marginBottom: 0 }}>
            <View style={{ width: '22%', justifyContent: 'center' }}>
              <Text style={{ fontSize: 16 }}>설명: </Text>
            </View>
            <View style={{ width: '78%' }}>
              <MultiLineInput
                fontSize={16}
                placeholder="물품에 대한 설명을 입력해주세요."
                onChangeText={(text) => {
                  setInputDescription(text)
                }}
              />
            </View>
          </View>

          {/* TO-DO */}
          {/* 이미지Url 가져오기 */}
          <View>
            <View style={{ flex: 1, flexDirection: 'row', alignItems: 'center', paddingHorizontal: 10, paddingVertical: 5, borderColor: Color.GRAY, borderWidth: 0.3, borderBottomWidth: 2, borderRadius: 5, margin: 10, marginBottom: 0 }}>
              <View style={{ justifyContent: 'center', width: '30%' }}>
                <Text style={{ fontSize: 16 }}>이미지 변경: </Text>
              </View>
              <View style={{ height: '100%', width: '47%', maxWidth: '47%', padding: 5, backgroundColor: Color.LIGHT_GRAY, justifyContent: 'center', borderRadius: 5, marginRight: 4 }}>
                <Text
                  numberOfLines={1}
                  style={{ textDecorationLine: 'underline', fontSize: 14 }}
                >
                  {imageUrl}
                </Text>
              </View>
              <View style={{ justifyContent: 'center', width: '25%', alignItems: 'center' }}>
                {/* TO-DO */}
                {/* 변경버튼 클릭 후 이미지 변경 시 url 가져와서 setInputTumbnail()을 변경 url로 inputTumbnail 바꿔넣기 */}
                <TouchableOpacity
                  onPress={() => {
                    setImageUrl('testUrl')
                    // 현재는 그냥 testUrl이라는 글자 들어가는 중
                  }}
                >
                  <View style={{ padding: 8, backgroundColor: Color.LIGHT_BLUE, borderRadius: 8 }}>
                    <Text style={{ fontSize: 12, color: Color.WHITE }}>변경 하기</Text>
                  </View>
                </TouchableOpacity>
              </View>
            </View>
            <View style={{ flex: 1, flexDirection: 'row', alignItems: 'center', paddingHorizontal: 10, margin: 5 }}>
              <Text style={{ fontSize: 12, color: Color.GRAY, textDecorationLine: 'line-through' }}> * 이미지는 필수가 아닙니다.</Text>
            </View>
          </View>
        </View>
      </KeyboardAwareScrollView>

      {/* 등록 버튼, 취소 버튼 */}
      <View style={{ flexDirection: 'row', margin: 20, justifyContent: 'space-around' }}>
        <TouchableOpacity
          onPress={() => {
            naviagtion.goBack()
          }}
        >
          <View style={{ padding: 15, backgroundColor: Color.BLUE, borderRadius: 10 }}>
            <Text style={{ fontSize: 18, color: Color.WHITE }}>작성 취소</Text>
          </View>
        </TouchableOpacity>
        <TouchableOpacity
          onPress={() => {
            getProductCreateData()
          }}
        >
          <View style={{ padding: 15, backgroundColor: Color.DARK_BLUE, borderRadius: 10 }}>
            <Text style={{ fontSize: 18, color: Color.WHITE }}>게시 하기</Text>
          </View>
        </TouchableOpacity>
      </View>
    </View>
  )
}
