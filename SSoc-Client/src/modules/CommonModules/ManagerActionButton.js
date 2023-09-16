import ActionButton from "react-native-action-button";
import { Ionicons } from "@expo/vector-icons";
import { StyleSheet } from "react-native";
import * as Color from "../../components/Colors/colors";
import { useNavigation } from "@react-navigation/native";

export const ManagerActionButton = ({ groupMemberRole, groupId }) => {
  console.log("버튼의", groupId);
  const navigation = useNavigation();
  return (
    <>
      {groupMemberRole != "MANAGER" ? null : (
        <ActionButton buttonColor="rgba(231,76,60,1)" useNativeFeedback={false}>
          <ActionButton.Item
            buttonColor="#3498db"
            title="물품 예약관리"
            onPress={() => {
              navigation.navigate("BookingManageTopTabs", {
                groupId: groupId,
                groupMemberRole: groupMemberRole,
              });
            }}
          >
            <Ionicons name="cart" style={styles.actionButtonIcon} />
          </ActionButton.Item>

          <ActionButton.Item
            buttonColor="#3498db"
            title="신청 및 그룹원 관리"
            onPress={() => {
              navigation.navigate("MemberManageTopTabs", {
                groupId: groupId,
                groupMemberRole: groupMemberRole,
              });
            }}
          >
            <Ionicons
              name="person"
              style={styles.actionButtonIcon}
              size={30}
              color={Color.WHITE}
            />
          </ActionButton.Item>
          <ActionButton.Item
            buttonColor="#1abc9c"
            title="일정등록"
            onPress={() => {
              navigation.navigate("AddScheduleScreen", {
                groupId: groupId,
                groupMemberRole: groupMemberRole,
              });
            }}
          >
            <Ionicons name="calendar-sharp" style={styles.actionButtonIcon} />
          </ActionButton.Item>
        </ActionButton>
      )}
    </>
  );
};
const styles = StyleSheet.create({
  actionButtonIcon: {
    fontSize: 20,
    height: 22,
    color: "white",
  },
});
