import { useEffect, useState } from 'react'
import { View, Text } from 'react-native'
import * as Color from '../../components/Colors/colors'

const MyReservationCard = (props) => {
  const reservationInfo = props.item
  const [reservationState, setReservationState] = useState('확인안함')
  const [reservationStateColor, setReservstionStateColor] = useState(Color.GRAY)

  console.log(reservationInfo)

  makeState = () => {
    if (reservationInfo.reservationApproveStatus == 'ACCEPT') {
      setReservationState('승인')
      setReservationStateColor(Color.BLUE)
    } else if (reservationInfo.reservationApproveStatus == 'REJECT') {
      setReservationState('거절')
      setReservstionStateColor(Color.LIGHT_RED)
    }
  }
  useEffect(() => {
    makeState
  }, [])

  return (
    <View style={{ flex: 1, width: '95%', minHeight: 100, padding: 10, borderBottomColor: Color.LIGHT_BLUE, borderBottomWidth: 3, marginTop: 5, borderRadius: 15, borderWidth: 0.2, marginHorizontal: 10 }}>
      <View style={{ flex: 1, flexDirection: 'row', width: '100%', height: '100%' }}>
        <View style={{ flexDirection: 'column', justifyContent: 'space-evenly', width: '75%' }}>
          <Text style={{ fontSize: 18, color: Color.GRAY, fontWeight: 'bold', marginRight: 6 }}>예약물품: {reservationInfo.productName}</Text>
          <Text style={{ fontSize: 16, color: Color.GRAY, marginRight: 6 }}> 예약일자: {reservationInfo.reservationCreatedAt}</Text>
          <Text style={{ fontSize: 16, color: Color.GRAY, marginRight: 6 }}> 예약시간: {reservationInfo.reservationTime}시 예약</Text>
        </View>
        <View style={{ alignItems: 'center', justifyContent: 'center' }}>
          <View style={{ backgroundColor: reservationStateColor, borderRadius: 10, padding: 15, alignItems: 'center', justifyContent: 'center' }}>
            <Text style={{ color: Color.WHITE }}>{reservationState}</Text>
          </View>
        </View>
      </View>
    </View>
  )
}

export default MyReservationCard
