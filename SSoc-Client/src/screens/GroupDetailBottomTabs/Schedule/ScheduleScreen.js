import { StyleSheet, Text, View, TouchableOpacity, Modal, Pressable, Alert } from 'react-native'
import { Agenda } from 'react-native-calendars'
import React, { useCallback, useState, useEffect } from 'react'
import { StackHeader } from '../../../modules/StackHeader'
import * as Color from '../../../components/Colors/colors'
import { Ionicons } from '@expo/vector-icons'
import { Typography } from '../../../components/Basic/Typography'
import { DeleteModal } from '../../../components/Modal/DeleteModal'
import { ManagerActionButton } from '../../../modules/CommonModules/ManagerActionButton'
import { getTokens } from '../../../util/TokenUtil'
import { getSceduleListFetch } from '../../../util/FetchUtil'
import { Spacer } from '../../../components/Basic/Spacer'

const timeToString = (time) => {
  const date = new Date(time)
  return date.toISOString().split('T')[0]
}

export const ScheduleScreen = (props) => {
  const [isModalVisible, setIsModalVisible] = useState(false)
  const [items, setItems] = React.useState({})
  const [reload, setReload] = useState(false)

  const [selectedItemId, setSelectedItemId] = useState()
  const [selectedItemName, setSelectedItemName] = useState()
  const [selectedItemContent, setSelectedItemContent] = useState()

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  const [beforeData, setBeforeData] = useState([])
  const [selectedDate, setSelectedDate] = useState(new Date().toISOString().split('T')[0])

  const groupMemberRole = props.route.params.groupMemberRole
  const groupId = props.route.params.groupId

  const onPressDelete = useCallback((name, sceduleId, content) => {
    setIsModalVisible(true)
    setSelectedItemName(name)
    setSelectedItemId(sceduleId)
    setSelectedItemContent(content)
  })

  const loadItems = () => {
    const newItems = {}
    const daysToLoad = 15 // 로드할 날짜 범위 설정
    // selectedDate를 Date 객체로 변환
    const selectedDateObj = new Date(selectedDate)

    for (let i = 0; i <= daysToLoad; i++) {
      const date = new Date(selectedDateObj)
      date.setDate(selectedDateObj.getDate() + i)
      const formattedDate = date.toISOString().split('T')[0]
      newItems[formattedDate] = []
    }

    // beforeData를 반복하여 해당 날짜의 배열에 데이터 추가
    beforeData.forEach((item) => {
      const { date, ...rest } = item
      const formattedDate = date.split('T')[0]
      if (newItems[formattedDate]) {
        newItems[formattedDate].push(rest)
      }
    })

    setItems(newItems)
  }

  const getSceduleListData = async () => {
    try {
      const response = await getSceduleListFetch(accessToken, refreshToken, groupId, selectedDate)
      const data = await response.json()
      if (data != null && data.dataHeader != undefined) {
        if (data.dataHeader.successCode == 0) {
          setBeforeData(data.dataBody)
        } else {
          Alert.alert(data.dataHeader.resultMessage)
        }
      } else {
        Alert.alert('서버 통신 오류 발생', '다시 시도해주세요.')
      }
    } catch (e) {
      console.log(e)
    }
  }

  const renderItem = (item) => {
    return (
      <View style={styles.item}>
        <View style={{ flexDirection: 'column' }}>
          <Typography
            fontSize={16}
            color={Color.WHITE}
          >
            {'일정: ' + item.title}
          </Typography>
          <Spacer space={4} />
          <Typography
            fontSize={14}
            color={Color.LIGHT_GRAY}
          >
            {'내용: ' + item.content}
          </Typography>
        </View>
        {groupMemberRole != 'MANAGER' ? null : (
          <TouchableOpacity onPress={() => onPressDelete(item.title, item.scheduleId, item.content)}>
            <View>
              <Ionicons
                name={'trash'}
                size={20}
                color={Color.WHITE}
              />
            </View>
          </TouchableOpacity>
        )}
      </View>
    )
  }

  useEffect(() => {
    if (isTokenGet) getSceduleListData()
  }, [reload])

  useEffect(() => {
    if (!isTokenGet) {
      getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
    } else {
      getSceduleListData()
    }
  }, [isTokenGet])

  useEffect(() => {
    loadItems()
  }, [beforeData])

  useEffect(() => {
    if (isTokenGet) getSceduleListData()
  }, [selectedDate])

  const handleDayPress = (day) => {
    setSelectedDate(day.dateString)
  }

  return (
    <View
      style={styles.container}
      key={reload}
    >
      <View style={{ backgroundColor: 'white' }}>
        <StackHeader title={'일정'} />
      </View>

      <Agenda
        hideKnob={false}
        items={items}
        loadItemsForMonth={loadItems}
        onDayPress={handleDayPress}
        selected={selectedDate}
        refreshControl={null}
        showClosingKnob={true}
        refreshing={false}
        renderItem={renderItem}
        renderEmptyDate={() => (
          <View style={styles.noItem}>
            <Typography
              fontSize={16}
              color={Color.WHITE}
            >
              해당 날짜에 일정이 없습니다.
            </Typography>
          </View>
        )}
      />

      <ManagerActionButton
        groupMemberRole={groupMemberRole}
        groupId={groupId}
      />

      <DeleteModal
        groupId={groupId}
        isModalVisible={isModalVisible}
        selectedItemId={selectedItemId}
        selectedItemName={selectedItemName}
        selectedItemContent={selectedItemContent}
        setIsModalVisible={setIsModalVisible}
        option={'일정'}
        reload={reload}
        setReload={setReload}
      />
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  noItem: {
    flex: 1,
    flexDirection: 'row',
    borderRadius: 10,
    padding: 10,
    marginRight: 10,
    marginTop: 17,
    backgroundColor: Color.GRAY,
    height: 60,
    width: 300,
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  item: {
    flex: 1,
    flexDirection: 'row',
    borderRadius: 10,
    padding: 10,
    marginRight: 10,
    marginTop: 17,
    backgroundColor: Color.BLUE,
    height: 60,
    width: 300,
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  centeredView: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: 22,
  },
  modalView: {
    margin: 20,
    marginTop: 350,
    backgroundColor: 'white',
    borderRadius: 20,
    padding: 35,
    alignItems: 'center',
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 2,
    },
    shadowOpacity: 0.25,
    shadowRadius: 4,
    elevation: 5,
  },
  button: {
    borderRadius: 20,
    padding: 10,
    elevation: 2,
  },
  buttonOpen: {
    backgroundColor: '#F194FF',
  },
  buttonClose: {
    backgroundColor: '#2196F3',
  },
  textStyle: {
    color: 'white',
    fontWeight: 'bold',
    textAlign: 'center',
  },
  modalText: {
    marginBottom: 15,
    textAlign: 'center',
  },
})
