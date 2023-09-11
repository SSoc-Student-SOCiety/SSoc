import {
  StyleSheet,
  Text,
  View,
  TouchableOpacity,
  StatusBar,
} from "react-native";
import { Agenda } from "react-native-calendars";
import { Card } from "react-native-paper";
import React, { useState } from "react";
import { StackHeader } from "../../../modules/StackHeader";
import * as Color from "../../../components/Colors/colors";
const timeToString = (time) => {
  const date = new Date(time);
  return date.toISOString().split("T")[0];
};
export const ScheduleScreen = () => {
  const [selected, setSelected] = useState("");
  const [items, setItems] = React.useState({});
  const loadItems = (day) => {
    setTimeout(() => {
      for (let i = -15; i < 15; i++) {
        const time = day.timestamp + i * 24 * 60 * 60 * 1000;

        const strTime = timeToString(time);

        if (!items[strTime]) {
          items[strTime] = [];

          const numItems = Math.floor(Math.random() * 3 + 1);
          for (let j = 0; j < numItems; j++) {
            items[strTime].push({
              name: "Item for " + strTime + " #" + j,
              day: strTime,
            });
          }
        }
      }
      const newItems = {};
      Object.keys(items).forEach((key) => {
        newItems[key] = items[key];
      });

      setItems(newItems);
    }, 1000);
  };
  const renderItem = (item) => {
    return (
      <TouchableOpacity style={styles.item}>
        <Card>
          <Card.Content>
            <View>
              <Text>{item.name}</Text>
            </View>
          </Card.Content>
        </Card>
      </TouchableOpacity>
    );
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
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  item: {
    flex: 1,
    borderRadius: 5,
    padding: 10,
    marginRight: 10,
    marginTop: 17,
  },
});
