import { View, TouchableOpacity, FlatList } from "react-native";
import { Typography } from "../../../../components/Basic/Typography";
import React, { useState, useEffect, useCallback } from "react";
import { getAllBookingListFetch } from "../../../../util/FetchUtil";
import { getTokens } from "../../../../util/TokenUtil";

export const AllBookingListScreen = (props) => {
  const [data, setData] = useState(mockResponse.dataBody);
  const [groupId, setGroupId] = useState(props.route.params.groupId);
  const [accessToken, setAccessToken] = useState(null);
  const [refreshToken, setRefreshToken] = useState(null);
  const [isTokenGet, setIsTokenGet] = useState(false);
  const getAllBookingListData = async () => {
    console.log(groupId);
    try {
      const response = await getAllBookingListFetch(
        accessToken,
        refreshToken,
        1
      );
      const newData = await response.json();
      console.log("api 결과", newData);

      console.log(newData);
      return newData;
    } catch (e) {
      console.error(e);
      return [];
    }
  };

  useEffect(() => {
    if (!isTokenGet) {
      getTokens(setAccessToken, setRefreshToken, setIsTokenGet);
    }
  }, [isTokenGet]);

  const onPressGetTest = useCallback(() => {
    getAllBookingListData();
  });

  const loadData = async () => {
    const newData = await getAllBookingListData();

    if (newData.dataHeader.successCode == 0) {
      if (newData.dataBody.length > 0) {
        setData((prevData) => [...prevData, ...newData.dataBody]);
      }
    }
  };
  const handleEndReached = () => {
    loadData();
  };

  return (
    <View style={{ flex: 1 }}>
      <TouchableOpacity onPress={onPressGetTest}>
        <Typography>ddd</Typography>
      </TouchableOpacity>
      <FlatList
        horizontal
        showsHorizontalScrollIndicator={false}
        data={data}
        renderItem={({ item, index }) => {
          console.log(item);
          return <View></View>;
        }}
        onEndReached={handleEndReached}
        onEndReachedThreshold={0.1}
      />
    </View>
  );
};

const mockResponse = {
  dataHeader: {
    successCode: 0,
    resultCode: null,
    resultMessage: null,
  },
  dataBody: [
    {
      productName: "물품 테스트1 (편의성1)",
      imageUrl: "https://imageUrl",
      userName: "한주",
      userEmail: "hanju@yonsei.ac.kr",
      reservationCreatedAt: "2023-09-15 11:59",
      reservationCancelFlag: false,
      reservationReturnStatus: false,
      reservationApproveStatus: "ACCEPT",
      reservationRealDate: "2023-09-15",
      reservationTime: 11,
    },
    {
      productName: "물품 테스트2 (편의성2)",
      imageUrl: "https://imageUrl",
      userName: "한주",
      userEmail: "hanju@yonsei.ac.kr",
      reservationCreatedAt: "2023-09-15 11:59",
      reservationCancelFlag: true,
      reservationReturnStatus: false,
      reservationApproveStatus: "NOTCONFIRM",
      reservationRealDate: "2023-09-15",
      reservationTime: 16,
    },
    {
      productName: "물품 테스트3 (행사용품1)",
      imageUrl: "https://imageUrl",
      userName: "한주",
      userEmail: "hanju@yonsei.ac.kr",
      reservationCreatedAt: "2023-09-15 11:59",
      reservationCancelFlag: false,
      reservationReturnStatus: false,
      reservationApproveStatus: "REJECT",
      reservationRealDate: "2023-09-15",
      reservationTime: 17,
    },
    {
      productName: "물품 테스트4 (전공서적1)",
      imageUrl: "https://imageUrl",
      userName: "한주",
      userEmail: "hanju@yonsei.ac.kr",
      reservationCreatedAt: "2023-09-15 11:59",
      reservationCancelFlag: false,
      reservationReturnStatus: true,
      reservationApproveStatus: "ACCEPT",
      reservationRealDate: "2023-09-15",
      reservationTime: 10,
    },
    {
      productName: "물품 테스트1 (편의성1)",
      imageUrl: "https://imageUrl",
      userName: "수린",
      userEmail: "surin@inha.co.kr",
      reservationCreatedAt: "2023-09-15 11:59",
      reservationCancelFlag: false,
      reservationReturnStatus: false,
      reservationApproveStatus: "ACCEPT",
      reservationRealDate: "2023-09-15",
      reservationTime: 12,
    },
  ],
};
