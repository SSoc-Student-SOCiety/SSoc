import { View, StyleSheet, Pressable, Text } from "react-native";
import * as Color from "../Colors/colors";
import { Modal } from "react-native";
export const ReservationAcceptRejectModal = ({
  isModalVisible,
  selectedBookingId,
  selectedUserName,
  selectedDate,
  selectedTime,
  selectedProductName,
  setIsModalVisible,
}) => {
  return (
    <Modal
      animationType="slide"
      transparent={true}
      visible={isModalVisible}
      onRequestClose={() => {
        setIsModalVisible(false);
      }}
    >
      <View style={styles.modalView}>
        <Text style={styles.modalText}>
          {selectedUserName}이 {selectedProductName} 을
        </Text>
        <Text style={styles.modalText}>
          {selectedDate} {selectedTime}시에 신청하였습니다. #{selectedBookingId}
        </Text>
        <View style={{ flexDirection: "row" }}>
          <View style={{ margin: 10 }}>
            <Pressable
              style={[styles.button, styles.buttonClose]}
              onPress={() => setIsModalVisible(false)}
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
  );
};
const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  item: {
    flex: 1,
    flexDirection: "row",
    borderRadius: 10,
    padding: 10,
    marginRight: 10,
    marginTop: 17,
    backgroundColor: Color.LIGHT_BLUE,
    height: 60,
    width: 300,
    justifyContent: "space-around",
    alignItems: "center",
  },
  centeredView: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    marginTop: 22,
  },
  modalView: {
    margin: 20,
    marginTop: 350,
    backgroundColor: "white",
    borderRadius: 20,
    padding: 35,
    alignItems: "center",
    shadowColor: "#000",
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
    backgroundColor: "#F194FF",
  },
  buttonClose: {
    backgroundColor: "#2196F3",
  },
  textStyle: {
    color: "white",
    fontWeight: "bold",
    textAlign: "center",
  },
  modalText: {
    marginBottom: 15,
    textAlign: "center",
  },
});
