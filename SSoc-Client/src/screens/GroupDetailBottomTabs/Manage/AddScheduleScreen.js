import { useEffect, useState } from 'react'
import { View, Text, TouchableOpacity, ScrollView, Alert } from 'react-native'
import { StackHeader } from '../../../modules/StackHeader'
import * as Color from '../../../components/Colors/colors'
import { SingleLineInput } from '../../../components/Input/SingleLineInput'
import { MultiLineInput } from '../../../components/Input/MultiLineInput'
import DatePicker from 'react-native-date-picker'
import { Icon } from '../../../components/Icons/Icons'
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view'
import { useNavigation } from '@react-navigation/native'
import { getTokens } from '../../../util/TokenUtil'
import { getCreateScheduleFetch } from '../../../util/FetchUtil'

export const AddScheduleScreen = (props) => {
  const groupId = props.route.params.groupId
  //   const groupId = props.route.params.groupId
  //   const groupMemberRole = props.route.params.groupMemberRole
  const naviagtion = useNavigation()
  const [open, setOpen] = useState(false)

  const [eventName, setEventName] = useState('')
  const [eventContent, setEventContent] = useState('')
  const [eventCategory, setEventCategory] = useState('')
  const [date, setDate] = useState(new Date())

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  const onPressCreate = () => {
    if (eventName.length != 0 && eventContent != 0 && eventCategory != 0 && date != null) {
      getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
    } else {
      Alert.alert('모든 데이터를 입력해주세요.')
    }
  }

  useEffect(() => {
    if (isTokenGet) {
      getCreateScheduleData()
    }
  }, [isTokenGet])

  const getCreateScheduleData = async () => {
    try {
      const response = await getCreateScheduleFetch(accessToken, refreshToken, groupId, eventName, eventContent, eventCategory, date.toISOString().split('T')[0])
      const data = await response.json()
      console.log(data)
      if (data != null && data.dataHeader != undefined) {
        if (data.dataHeader.successCode == 0) {
          Alert.alert('일정이 등록되었습니다.', '', [
            {
              text: '확인',
              onPress: () => {
                naviagtion.goBack()
              },
            },
          ])
        } else {
          Alert.alert(data.dataHeader.resultMessage)
        }
      } else {
        Alert.alert('서버 통신 에러 발생', '다시 시도해주세요.')
      }
      setIsTokenGet(false)
    } catch (e) {
      console.log(e)
    }
  }

  return (
    <View style={{ flex: 1, backgroundColor: Color.WHITE }}>
      <StackHeader title={'일정 추가하기'} />
      <KeyboardAwareScrollView style={{ flexDirection: 'column', backgroundColor: Color.WHITE, flex: 1 }}>
        <View style={{ alignItems: 'center', paddingHorizontal: 10, paddingVertical: 5, borderColor: Color.GRAY, borderWidth: 0.3, borderBottomWidth: 2, borderRadius: 5, margin: 10, marginBottom: 0, marginTop: 25 }}>
          <View style={{ width: '100%' }}>
            <SingleLineInput
              placeholder="Event Name*"
              onChangeText={(text) => {
                setEventName(text)
              }}
            />
          </View>
        </View>
        <View style={{ alignItems: 'center', paddingHorizontal: 10, paddingVertical: 5, borderColor: Color.GRAY, borderWidth: 0.3, borderBottomWidth: 2, borderRadius: 5, margin: 10, marginBottom: 0 }}>
          <View style={{ width: '100%' }}>
            <MultiLineInput
              placeholder="Write The Contents Of Event Here..."
              onChangeText={(text) => {
                setEventContent(text)
              }}
            />
          </View>
        </View>
        <View style={{ alignItems: 'center', paddingHorizontal: 10, paddingVertical: 5, borderColor: Color.GRAY, borderWidth: 0.3, borderBottomWidth: 2, borderRadius: 5, margin: 10, marginBottom: 0 }}>
          <View style={{ width: '100%' }}>
            <SingleLineInput
              placeholder="Write Category"
              onChangeText={(text) => {
                setEventCategory(text)
              }}
            />
          </View>
        </View>
        <TouchableOpacity onPress={() => setOpen(true)}>
          <View style={{ alignItems: 'center', paddingHorizontal: 10, paddingVertical: 5, borderColor: Color.GRAY, borderWidth: 0.3, borderBottomWidth: 2, borderRadius: 5, margin: 10, marginBottom: 0 }}>
            <View style={{ width: '100%', alignSelf: 'stretch', paddingHorizontal: 12, paddingVertical: 8, borderRadius: 4 }}>
              <View style={{ flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between' }}>
                <Text style={{ fontSize: 20, color: Color.GRAY }}>{date.toDateString()}</Text>
                <Icon
                  color={Color.BLUE}
                  name="calendar"
                  size={20}
                />
              </View>
            </View>
          </View>
        </TouchableOpacity>
        <View style={{ flexDirection: 'row', margin: 15, marginTop: 30, justifyContent: 'space-around' }}>
          <TouchableOpacity
            onPress={() => {
              naviagtion.goBack()
            }}
          >
            <View style={{ padding: 15, backgroundColor: Color.GRAY, borderRadius: 10 }}>
              <Text style={{ fontSize: 18, color: Color.WHITE }}>일정 추가 취소</Text>
            </View>
          </TouchableOpacity>
          <TouchableOpacity
            onPress={() => {
              onPressCreate()
            }}
          >
            <View style={{ padding: 15, backgroundColor: Color.LIGHT_RED, borderRadius: 10 }}>
              <Text style={{ fontSize: 18, color: Color.WHITE }}>일정 추가하기</Text>
            </View>
          </TouchableOpacity>
        </View>
      </KeyboardAwareScrollView>
      <DatePicker
        modal
        open={open}
        date={date}
        mode="date"
        onConfirm={(date) => {
          setOpen(false)
          setDate(date)
        }}
        onCancel={() => {
          setOpen(false)
        }}
      />
    </View>
  )
}
