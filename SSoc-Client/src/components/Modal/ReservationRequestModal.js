import { useState, useEffect } from 'react'
import { View, StyleSheet, Pressable, Text, Alert } from 'react-native'
import * as Color from '../Colors/colors'
import { Modal } from 'react-native'
import { useNavigation } from '@react-navigation/native'
import { getReservationRequestFetch } from '../../util/FetchUtil'
import { getTokens } from '../../util/TokenUtil'

export const ReservationRequestModal = ({ isModalVisible, selectedItemId, selectedItemName, selectedGroupId, setIsModalVisible, selectedDate, selectedTime }) => {
  const navigation = useNavigation()

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  useEffect(() => {
    getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
  })

  const getReservationRequestData = async () => {
    try {
      const response = await getReservationRequestFetch(accessToken, refreshToken, selectedItemId, selectedDate, selectedTime)
      const data = await response.json()
      console.log(data)
      if (data != null && data.dataHeader != undefined) {
        if (data.dataHeader.successCode == 0) {
          Alert.alert('예약이 완료되었습니다.', '', [
            {
              text: '확인',
              onPress: () => {
                setIsModalVisible(false)
                navigation.goBack()
              },
            },
          ])
        }
      } else {
        Alert.alert('서버 통신 에러 발생', '다시 시도해주세요.')
      }
    } catch (e) {
      console.log(e)
    }
  }

  return (
    <Modal
      animationType="slide"
      transparent={true}
      visible={isModalVisible}
      onRequestClose={() => {
        setIsModalVisible(false)
      }}
    >
      <View style={styles.modalView}>
        <Text style={styles.modalText}>
          {selectedItemId} {selectedItemName} {selectedDate} {selectedTime} ~ {selectedTime + 1}
        </Text>
        <Text style={styles.modalText}>예약 하시겠습니까?</Text>
        <View style={{ flexDirection: 'row' }}>
          <View style={{ margin: 10 }}>
            <Pressable
              style={[styles.button, styles.buttonClose]}
              onPress={() => {
                getReservationRequestData()
              }}
            >
              <Text style={styles.textStyle}>확인</Text>
            </Pressable>
          </View>
          <View style={{ margin: 10 }}>
            <Pressable
              style={[styles.button, styles.buttonClose]}
              onPress={() => {
                setIsModalVisible(false)
              }}
            >
              <Text style={styles.textStyle}>취소</Text>
            </Pressable>
          </View>
        </View>
      </View>
    </Modal>
  )
}
const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  item: {
    flex: 1,
    flexDirection: 'row',
    borderRadius: 10,
    padding: 10,
    marginRight: 10,
    marginTop: 17,
    backgroundColor: Color.LIGHT_BLUE,
    height: 60,
    width: 300,
    justifyContent: 'space-around',
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
