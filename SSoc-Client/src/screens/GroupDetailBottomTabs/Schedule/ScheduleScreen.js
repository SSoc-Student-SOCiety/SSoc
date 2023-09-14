import {
  StyleSheet,
  Text,
  View,
  TouchableOpacity,
  Modal,
  Pressable,
} from "react-native";
import { Agenda } from "react-native-calendars";
import React, { useCallback, useState } from "react";
import { StackHeader } from "../../../modules/StackHeader";
import * as Color from "../../../components/Colors/colors";
import { Ionicons } from "@expo/vector-icons";
import { Typography } from "../../../components/Basic/Typography";
import { DeleteModal } from "../../../components/Modal/DeleteModal";
import { ManagerActionButton } from "../../../modules/CommonModules/ManagerActionButton";

const timeToString = (time) => {
  const date = new Date(time);
  return date.toISOString().split("T")[0];
};
export const ScheduleScreen = () => {
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [items, setItems] = React.useState({});
  const [writeNewContent, setWriteNewContent] = useState(false);
  const [selectedItemId, setSelectedItemId] = useState();
  const [selectedItemName, setSelectedItemName] = useState();
  const [selectedDate, setSelectedDate] = useState();
  const groupMemberRole = "MANAGER";
  // const groupMemberRole = 'MEMBER'

  const onPressDelete = useCallback((name, id, date) => {
    setIsModalVisible(true);
    setSelectedItemName(name);
    setSelectedItemId(id);
    setSelectedDate(date);
  });

  const loadItems = (day) => {
    setTimeout(() => {
      let count = 0;
      for (let i = -15; i < 15; i++) {
        const time = day.timestamp + i * 24 * 60 * 60 * 1000;

        const strTime = timeToString(time);

        if (!items[strTime]) {
          items[strTime] = [];

          const numItems = Math.floor(Math.random() * 3 + 1);
          for (let j = 0; j < numItems; j++) {
            items[strTime].push({
              id: count,
              name: "Item for " + strTime + " #" + j,
              day: strTime,
            });
            count++;
          }
        }
      }
      const newItems = {};
      Object.keys(items).forEach((key) => {
        newItems[key] = items[key];
      });

      setItems(newItems);
    }, 50);
  };
  const renderItem = (item) => {
    return (
      <View style={styles.item}>
        <Typography fontSize={15} color={Color.WHITE}>
          {item.name + " " + item.id}
        </Typography>
        {groupMemberRole != "MANAGER" ? null : (
          <TouchableOpacity
            onPress={() => onPressDelete(item.name, item.id, item.day)}
          >
            <View>
              <Ionicons name={"trash"} size={20} color={Color.WHITE} />
            </View>
          </TouchableOpacity>
        )}
      </View>
    );
  };
  const onPressAdd = () => {
    setWriteNewContent(true);
  };

  return (
    <View style={styles.container}>
      <View style={{ backgroundColor: "white" }}>
        <StackHeader title={"일정"} />
      </View>

      <Agenda
        hideKnob={false}
        items={items}
        loadItemsForMonth={loadItems}
        selected={"2022-07-07"}
        refreshControl={null}
        showClosingKnob={true}
        refreshing={false}
        renderItem={renderItem}
      />

      <ManagerActionButton groupMemberRole={groupMemberRole} />

      <DeleteModal
        isModalVisible={isModalVisible}
        selectedItemId={selectedItemId}
        selectedItemName={selectedItemName}
        setIsModalVisible={setIsModalVisible}
        option = {"일정"}
      />
    </View>
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
