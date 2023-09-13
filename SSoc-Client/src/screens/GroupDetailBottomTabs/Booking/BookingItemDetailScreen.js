import { View, FlatList } from "react-native";
import { StackHeader } from "../../../modules/StackHeader";
import * as Color from "../../../components/Colors/colors";
import { Calendar } from "react-native-calendars";
import { Text } from "react-native";
import React, { useEffect, useState } from "react";

export const BookingItemDetailScreen = () => {
  const currentDate = new Date(); // 현재 날짜 가져오기
  const year = currentDate.getFullYear();
  const month = (currentDate.getMonth() + 1).toString().padStart(2, "0"); // 월을 2자리 숫자로 포맷팅
  const day = currentDate.getDate().toString().padStart(2, "0"); // 일을 2자리 숫자로 포맷팅
  const today = `${year}-${month}-${day}`;

  const [timeList, setTimeList] = useState(mockResponse.dataBody[today]);
  const [selected, setSelected] = useState("");

  useEffect(() => {
    // 컴포넌트가 마운트될 때 초기 데이터를 설정합니다.
    setTimeList(mockResponse.dataBody[today]);
  }, []);

  const List = ({ selectedDate }) => {
    // selectedDate에 따라 다른 데이터를 표시하도록 수정
    const listData = mockResponse.dataBody[selectedDate] || [];

    return (
      <FlatList
        data={listData}
        renderItem={renderItem}
        keyExtractor={(item, index) => index.toString()}
      />
    );
  };

  const renderItem = ({ item }) => {
    console.log(item);
    return (
      <View>
        <Text>
          {item.time} {item.available ? 1 : 0}
        </Text>
      </View>
    );
  };

  return (
    <View style={{ backgroundColor: Color.WHITE }}>
      <StackHeader title={"예약아이템 상세"} />

      <Calendar
        onDayPress={(day) => {
          setSelected(day.dateString);
          console.log(day);
        }}
        markedDates={{
          [today]: { selected: true, marked: true },
          [selected]: {
            selected: true,
            disableTouchEvent: true,
            selectedDotColor: Color.RED,
            color: Color.RED,
          },
        }}
      />
      {/* selected 상태가 변경될 때마다 List 컴포넌트를 렌더링 */}
      <List selectedDate={selected} />
    </View>
  );
};

const mockResponse = {
  dataHeader: {
    successCode: 0,
    resultCode: null,
    resultMessage: null,
  },
  dataBody: {
    "2023-09-14": [
      { time: 9, available: true },
      { time: 10, available: true },
      { time: 11, available: true },
      { time: 12, available: true },
      { time: 13, available: true },
      { time: 14, available: true },
      { time: 15, available: true },
      { time: 16, available: true },
      { time: 17, available: true },
      { time: 18, available: true },
      { time: 19, available: true },
      { time: 20, available: true },
      { time: 21, available: true },
      { time: 22, available: true },
    ],
    "2023-09-15": [
      { time: 9, available: true },
      { time: 10, available: true },
      { time: 11, available: true },
      { time: 12, available: true },
      { time: 13, available: true },
      { time: 14, available: true },
      { time: 15, available: true },
      { time: 16, available: true },
      { time: 17, available: true },
      { time: 18, available: true },
      { time: 19, available: true },
      { time: 20, available: true },
      { time: 21, available: true },
      { time: 22, available: true },
    ],
    "2023-09-16": [
      { time: 9, available: true },
      { time: 10, available: true },
      { time: 11, available: true },
      { time: 12, available: true },
      { time: 13, available: true },
      { time: 14, available: true },
      { time: 15, available: true },
      { time: 16, available: true },
      { time: 17, available: true },
      { time: 18, available: true },
      { time: 19, available: true },
      { time: 20, available: true },
      { time: 21, available: true },
      { time: 22, available: true },
    ],
    "2023-09-17": [
      { time: 9, available: true },
      { time: 10, available: true },
      { time: 11, available: true },
      { time: 12, available: true },
      { time: 13, available: true },
      { time: 14, available: true },
      { time: 15, available: true },
      { time: 16, available: true },
      { time: 17, available: true },
      { time: 18, available: true },
      { time: 19, available: true },
      { time: 20, available: true },
      { time: 21, available: true },
      { time: 22, available: true },
    ],
    "2023-09-18": [
      { time: 9, available: true },
      { time: 10, available: true },
      { time: 11, available: true },
      { time: 12, available: true },
      { time: 13, available: true },
      { time: 14, available: true },
      { time: 15, available: true },
      { time: 16, available: true },
      { time: 17, available: true },
      { time: 18, available: true },
      { time: 19, available: true },
      { time: 20, available: true },
      { time: 21, available: true },
      { time: 22, available: true },
    ],
    "2023-09-19": [
      { time: 9, available: true },
      { time: 10, available: true },
      { time: 11, available: true },
      { time: 12, available: true },
      { time: 13, available: true },
      { time: 14, available: true },
      { time: 15, available: true },
      { time: 16, available: true },
      { time: 17, available: true },
      { time: 18, available: true },
      { time: 19, available: true },
      { time: 20, available: true },
      { time: 21, available: true },
      { time: 22, available: true },
    ],
    "2023-09-20": [
      { time: 9, available: true },
      { time: 10, available: true },
      { time: 11, available: true },
      { time: 12, available: true },
      { time: 13, available: true },
      { time: 14, available: true },
      { time: 15, available: true },
      { time: 16, available: true },
      { time: 17, available: true },
      { time: 18, available: true },
      { time: 19, available: true },
      { time: 20, available: true },
      { time: 21, available: true },
      { time: 22, available: true },
    ],
    // 나머지 날짜 데이터 추가
  },
};
