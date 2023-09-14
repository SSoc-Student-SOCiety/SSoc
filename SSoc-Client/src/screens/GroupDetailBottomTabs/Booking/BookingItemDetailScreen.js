import { View, FlatList, Touchable, TouchableOpacity } from "react-native";
import { StackHeader } from "../../../modules/StackHeader";
import * as Color from "../../../components/Colors/colors";
import { Calendar } from "react-native-calendars";
import { Text } from "react-native";
import React, { useEffect, useState } from "react";
import { Typography } from "../../../components/Basic/Typography";
import { Divider } from "../../../components/Basic/Divider";
import { Spacer } from "../../../components/Basic/Spacer";
export const BookingItemDetailScreen = () => {
  const currentDate = new Date(); // 현재 날짜 가져오기
  const year = currentDate.getFullYear();
  const month = (currentDate.getMonth() + 1).toString().padStart(2, "0"); // 월을 2자리 숫자로 포맷팅
  const day = currentDate.getDate().toString().padStart(2, "0"); // 일을 2자리 숫자로 포맷팅
  const today = `${year}-${month}-${day}`;

  const [timeList, setTimeList] = useState(mockResponse.dataBody[today]);
  const [selected, setSelected] = useState(today);

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
      item.available ?(
        <TouchableOpacity>
          <View style={{backgroundColor:Color.LIGHT_BLUE, width: 320, height:40, justifyContent:"space-between", alignItems:"center", margin:5, flexDirection:"row", borderRadius:12}}>
            <View style={{margin:10}}>
              <Typography fontSize={15} color={Color.WHITE}> 
                {item.time}시부터 {item.time+1}시까지 
              </Typography>
            </View>
            <View style={{marginHorizontal:15}}>
              <Typography color={Color.WHITE}>예약가능</Typography>
            </View>
          </View>
        </TouchableOpacity>
        ) :(
        <View style={{backgroundColor:Color.GRAY, width: 320, height:40, justifyContent:"space-between", alignItems:"center", margin:5, flexDirection:"row", borderRadius:12}}>
          <View style={{margin:10}}>
            <Typography fontSize={15} color={Color.WHITE}> 
              {item.time}시부터 {item.time+1}시까지 
            </Typography>
          </View>
          <View style={{marginHorizontal:15}}>
            <Typography color={Color.WHITE}> 
              예약 불가
            </Typography>
          </View>
        </View>)
      
    );
  };

  return (
    <View style={{ backgroundColor: Color.WHITE, flex:1 }}>
      <StackHeader title={"예약아이템 상세"} />
      <View style={{flex:1}}>
        <Calendar
          onDayPress={(day) => {
            setSelected(day.dateString);
            console.log(day);
          }}
          markedDates={{
            [selected]: {
              selected: true,
              disableTouchEvent: true,
              selectedDotColor: Color.RED,
              color: Color.RED,
            },
          }}
        />
      </View>
      
      {/* selected 상태가 변경될 때마다 List 컴포넌트를 렌더링 */}
      <Spacer space={3}/>
      <Divider/>
      <Spacer space={3}/>
      <View style={{flex:1, justifyContent:"center", alignItems:"center"}}>
        <List selectedDate={selected} />
      </View>
      
    </View>
  );
};
//실제예약일을 2023-09-14의 형태로 입력을 넘겨줌
  //예약일을 키값으로 하여 
    //9~22까지의 time, avaliable을 포함한 객체 배열 넘기기
  //예약 테이블 내 (반납확인 false && 예약승인여부 true)인 시간을 availavle 값을 true로, 
    //그외 시간은 전부 false로 보내주기  
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
      { time: 12, available: false },
      { time: 13, available: true },
      { time: 14, available: true },
      { time: 15, available: true },
      { time: 16, available: true },
      { time: 17, available: false },
      { time: 18, available: true },
      { time: 19, available: true },
      { time: 20, available: false },
      { time: 21, available: true },
      { time: 22, available: true },
    ],
    "2023-09-15": [
      { time: 9, available: true },
      { time: 10, available: false },
      { time: 11, available: true },
      { time: 12, available: true },
      { time: 13, available: true },
      { time: 14, available: true },
      { time: 15, available: true },
      { time: 16, available: true },
      { time: 17, available: true },
      { time: 18, available: true },
      { time: 19, available: false },
      { time: 20, available: true },
      { time: 21, available: true },
      { time: 22, available: true },
    ],
    "2023-09-16": [
      { time: 9, available: false },
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
