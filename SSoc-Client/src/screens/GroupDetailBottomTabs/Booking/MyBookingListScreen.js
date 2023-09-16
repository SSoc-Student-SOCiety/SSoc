import { useEffect, useState } from 'react'
import { Alert, FlatList, View, Text } from 'react-native'
import MyReservationCard from '../../../modules/Reservation/MyReservationCard'
import * as Color from '../../../components/Colors/colors'
import { getTokens } from '../../../util/TokenUtil'
import { getMyReservationFetch } from '../../../util/FetchUtil'

export const MyBookingListScreen = (props) => {
  const groupId = props.route.params.groupId
  const [myReservationData, setMyReservationData] = useState([])
  const [isLoading, setIsLoading] = useState(true)

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  useEffect(() => {
    if (!isTokenGet) {
      getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
    } else {
      getMyReservationData()
    }
  }, [isTokenGet])

  const getMyReservationData = async () => {
    try {
      const response = await getMyReservationFetch(accessToken, refreshToken, groupId)
      const data = await response.json()
      if (data != null && data.dataHeader != undefined) {
        setMyReservationData(data.dataBody)
      } else {
        Alert.alert('서버 통신 에러 발생', '다시 시도해주세요.')
      }
    } catch (e) {
      console.error(e)
    }
    console.log(myReservationData)
  }

  return (
    <View style={{ flex: 1, backgroundColor: Color.WHITE }}>
      {myReservationData.length !== 0 ? (
        <FlatList
          data={myReservationData}
          renderItem={({ item }) => <MyReservationCard item={item} />}
        />
      ) : (
        <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
          <Text style={{ color: Color.BLUE }}>예약 내역이 존재하지 않습니다.</Text>
        </View>
      )}
    </View>
  )
}
