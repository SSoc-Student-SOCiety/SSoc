import ActionButton from "react-native-action-button";
import { Ionicons } from "@expo/vector-icons";
import { StyleSheet } from "react-native";
import * as Color from "../../components/Colors/colors"
import { useNavigation } from "@react-navigation/native";

export const ManagerActionButton =({groupMemberRole})=>{
  const navigation = useNavigation();

    return(
        <>
      {groupMemberRole != 'MANAGER' ? null 
      :(
        <ActionButton buttonColor="rgba(231,76,60,1)" useNativeFeedback={false}>
            <ActionButton.Item
              buttonColor="#3498db"
              title="물품 예약관리"
              onPress={() => {
                navigation.navigate("BookingManageScreen")
              }}
            >
              <Ionicons
                name="cart"
                style={styles.actionButtonIcon}
              />
            </ActionButton.Item>
            <ActionButton.Item
              buttonColor="#9b59b6"
              title="영수증 등록"
              onPress={() => {
                navigation.navigate("AddReceiptScreen")
              }}
            >
              <Ionicons name="receipt" style={styles.actionButtonIcon}/>
            </ActionButton.Item>
            <ActionButton.Item
              buttonColor="#3498db"
              title="신청 및 그룹원 관리"
              onPress={() => {
                navigation.navigate("MemberManageScreen")
              }}
            >
              <Ionicons
                name="person"
                style={styles.actionButtonIcon}
                size={30} color={Color.WHITE}
              />
            </ActionButton.Item>
            <ActionButton.Item
              buttonColor="#1abc9c"
              title="일정등록"
              onPress={()=>{
                navigation.navigate("AddScheduleScreen")
              }}
            >
              <Ionicons name="calendar-sharp" style={styles.actionButtonIcon}/>
            </ActionButton.Item>
          </ActionButton>
      )}
      </>
    )
}
const styles = StyleSheet.create({
    actionButtonIcon: {
        fontSize: 20,
        height: 22,
        color: 'white',
      },
})
