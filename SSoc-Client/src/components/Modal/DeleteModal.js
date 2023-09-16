import { useEffect, useState } from 'react'
import { View, StyleSheet, Pressable, Text, Alert } from 'react-native'
import * as Color from '../Colors/colors'
import { Modal } from 'react-native'
import { getTokens } from '../../util/TokenUtil'
import { getDeleteProductFetch, getDeleteSceduleFetch } from '../../util/FetchUtil'
// export const DeleteModal = ({ isModalVisible, selectedItemId, groupId, selectedItemName, selectedItemContent, setIsModalVisible, option, reload, setReload, selectedBookingId }) => {
export const DeleteModal = (props) => {
  const groupId = props.groupId
  const isModalVisible = props.isModalVisible
  const option = props.option
  const reload = props.reload
  const selectedItemContent = props.selectedItemContent
  const selectedItemId = props.selectedItemId
  const selectedItemName = props.selectedItemName
  const setIsModalVisible = props.setIsModalVisible
  const setReload = props.setReload

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  const getDeleteSceduleData = async () => {
    try {
      const response = await getDeleteSceduleFetch(accessToken, refreshToken, groupId, selectedItemId)
      const data = await response.json()

      console.log(data)
      if (data != null && data.dataHeader != undefined) {
        if (data.dataHeader.successCode == 0) {
          Alert.alert('일정이 삭제되었습니다.', '', [
            {
              text: '확인',
              onPress: () => {
                setReload(!reload)
                setIsModalVisible(false)
              },
            },
          ])
        } else {
          Alert.alert(data.dataHeader.resultMessage, '', [
            {
              text: '확인',
              onPress: () => {
                setIsModalVisible(false)
              },
            },
          ])
        }
      } else {
        Alert.alert('서버 통신 오류 발생', '다시 시도해주세요.')
      }
    } catch (e) {
      console.log(e)
    }
  }

  const getDeleteProductData = async () => {
    try {
      console.log(groupId)
      const response = await getDeleteProductFetch(accessToken, refreshToken, groupId, selectedItemId)
      const data = await response.json()

      console.log(data)
      if (data != null && data.dataHeader != undefined) {
        if (data.dataHeader.successCode == 0) {
          Alert.alert('물품이 삭제되었습니다.', '', [
            {
              text: '확인',
              onPress: () => {
                setReload(!reload)
                setIsModalVisible(false)
              },
            },
          ])
        } else {
          Alert.alert(data.dataHeader.resultMessage, '', [
            {
              text: '확인',
              onPress: () => {
                setIsModalVisible(false)
              },
            },
          ])
        }
      } else {
        Alert.alert('서버 통신 오류 발생', '다시 시도해주세요.')
      }
    } catch (e) {
      console.log(e)
    }
  }

  useEffect(() => {
    getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
  }, [])

  const onPressDelete = () => {
    if (option == '일정') {
      getDeleteSceduleData()
    } else {
      getDeleteProductData()
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
          {selectedItemName} {selectedItemContent}
        </Text>
        <Text style={styles.modalText}>등록된 {option}을 삭제하시겠습니까?</Text>
        <View style={{ flexDirection: 'row' }}>
          <View style={{ margin: 10 }}>
            <Pressable
              style={[styles.button, styles.buttonOpen]}
              onPress={onPressDelete}
            >
              <Text style={styles.textStyle}>확인</Text>
            </Pressable>
          </View>
          <View style={{ margin: 10 }}>
            <Pressable
              style={[styles.button, styles.buttonClose]}
              onPress={() => setIsModalVisible(false)}
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
    backgroundColor: Color.BLUE,
  },
  buttonClose: {
    backgroundColor: Color.GRAY,
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
