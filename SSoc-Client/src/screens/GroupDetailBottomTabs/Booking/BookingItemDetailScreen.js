import { View, FlatList, Touchable, TouchableOpacity } from 'react-native'
import { StackHeader } from '../../../modules/StackHeader'
import * as Color from '../../../components/Colors/colors'
import { Calendar } from 'react-native-calendars'
import { Text } from 'react-native'
import React, { useEffect, useState, useCallback } from 'react'
import { Typography } from '../../../components/Basic/Typography'
import { Divider } from '../../../components/Basic/Divider'
import { Spacer } from '../../../components/Basic/Spacer'
import { useRoute } from '@react-navigation/native'
import { Image } from 'react-native'
import { ReservationRequestModal } from '../../../components/Modal/ReservationRequestModal'
import { getTokens } from '../../../util/TokenUtil'
import { getReservationTimeFetch } from '../../../util/FetchUtil'
export const BookingItemDetailScreen = () => {
  const route = useRoute()
  const currentDate = new Date() // 현재 날짜 가져오기
  const year = currentDate.getFullYear()
  const month = (currentDate.getMonth() + 1).toString().padStart(2, '0') // 월을 2자리 숫자로 포맷팅
  const day = currentDate.getDate().toString().padStart(2, '0') // 일을 2자리 숫자로 포맷팅
  const today = `${year}-${month}-${day}`

  const [selected, setSelected] = useState(today)
  const [product, setProduct] = useState(route.params.item)
  const [selectedTime, setSelectedTime] = useState()

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  const [isModalVisible, setIsModalVisible] = useState(false)

  const [realData, setRealData] = useState([])

  // API에서 준 데이터(beforeData) 필요에 맞게 가공 작업
  const makeRealData = (beforeData) => {
    const data = {
      [selected]: [], // 동적으로 생성된 키값
    }

    const reservationTimeList = beforeData.dataBody.map((item) => item.reservationTime)
    const resultCode = beforeData.dataHeader.resultCode

    for (let time = 9; time <= 22; time++) {
      const isReserved = reservationTimeList.includes(time)
      const available = resultCode === 0 ? true : !isReserved
      data[selected].push({ time, available })
    }
    return data
  }

  const getReservationTimeData = async () => {
    try {
      const response = await getReservationTimeFetch(accessToken, refreshToken, product.productId, selected)
      const data = await response.json()
      if (data != null && data.dataHeader != undefined) {
        if (data.dataHeader.successCode == 0) {
          setRealData(makeRealData(data))
        }
      } else {
        Alert.alert('서버 통신 에러 발생', '다시 시도해주세요.')
      }
    } catch (e) {
      console.log(e)
    }
  }
n
  useEffect(() => {
    // 컴포넌트가 마운트될 때 초기 데이터를 설정합니다.
    if (!isTokenGet) {
      getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
    } else {
      getReservationTimeData()
    }
  }, [isTokenGet, selected])

  const List = ({ selectedDate }) => {
    // selectedDate에 따라 다른 데이터를 표시하도록 수정
    const listData = realData[selectedDate] || []

    return (
      <View style={{ alignItems: 'center', justifyContent: 'center' }}>
        <FlatList
          data={listData}
          renderItem={renderItem}
          keyExtractor={(item, index) => index.toString()}
        />
      </View>
    )
  }

  const onPressRequest = useCallback((item) => {
    console.log(item)
    console.log(selected)
    console.log(product)
    setSelectedTime(item.time)
    setIsModalVisible(true)
  })

  const renderItem = ({ item }) => {
    return item.available ? (
      <TouchableOpacity onPress={() => onPressRequest(item)}>
        <View style={{ backgroundColor: Color.LIGHT_BLUE, width: 320, height: 40, justifyContent: 'space-between', alignItems: 'center', margin: 5, flexDirection: 'row', borderRadius: 12 }}>
          <View style={{ margin: 10 }}>
            <Typography
              fontSize={15}
              color={Color.WHITE}
            >
              {item.time}시부터 {item.time + 1}시까지
            </Typography>
          </View>
          <View style={{ marginHorizontal: 15 }}>
            <Typography color={Color.WHITE}>예약 가능</Typography>
          </View>
        </View>
      </TouchableOpacity>
    ) : (
      <View style={{ backgroundColor: Color.GRAY, width: 320, height: 40, justifyContent: 'space-between', alignItems: 'center', margin: 5, flexDirection: 'row', borderRadius: 12 }}>
        <View style={{ margin: 10 }}>
          <Typography
            fontSize={15}
            color={Color.WHITE}
          >
            {item.time}시부터 {item.time + 1}시까지
          </Typography>
        </View>
        <View style={{ marginHorizontal: 15 }}>
          <Typography color={Color.WHITE}>예약 불가</Typography>
        </View>
      </View>
    )
  }

  return (
    <>
      <View style={{ backgroundColor: Color.WHITE, flex: 1 }}>
        <StackHeader title="예약하기" />
        <View style={{ flex: 1, margin: 25, flexDirection: 'row', borderRadius: 18, boderWidth: 1, borderColor: Color.GRAY }}>
          <Image
            source={{ uri: route.params.item.imgUrl }} // 외부 이미지 URL 설정
            style={{ width: 100, height: 130, borderRadius: 15 }}
          />
          <View style={{ margin: 10 }}>
            <Typography fontSize={20}>{route.params.item.name}</Typography>
            <Spacer space={3} />
            <Typography>{route.params.item.description}</Typography>
          </View>
        </View>

        <Calendar
          onDayPress={(day) => {
            setSelected(day.dateString)
          }}
          markedDates={{
            [selected]: {
              selected: true,
              disableTouchEvent: true,
              selectedDotColor: Color.RED,
              color: Color.RED,
            },
          }}
        />

        {/* selected 상태가 변경될 때마다 List 컴포넌트를 렌더링 */}
        <View style={{ flex: 4, alignContent: 'center', justifyContent: 'center' }}>
          <Spacer space={10} />
          <Divider />
          <Spacer space={10} />
          <List
            selectedDate={selected}
            product={route.params.item}
          />
        </View>

        <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}></View>
      </View>
      <ReservationRequestModal
        isModalVisible={isModalVisible}
        selectedItemId={product.productId}
        selectedItemName={product.name}
        setIsModalVisible={setIsModalVisible}
        selectedDate={selected}
        selectedTime={selectedTime}
      />
    </>
  )
}
