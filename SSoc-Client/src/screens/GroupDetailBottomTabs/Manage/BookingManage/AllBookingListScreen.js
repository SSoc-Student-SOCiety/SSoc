import { View, TouchableOpacity, FlatList } from "react-native";
import { Typography } from "../../../../components/Basic/Typography";
import React, { useState, useEffect, useCallback } from "react";
import { getAllBookingListFetch } from "../../../../util/FetchUtil";
import { getTokens } from "../../../../util/TokenUtil";
import { Divider } from "../../../../components/Basic/Divider";
import { ProfileImage } from "../../../../modules/ProfileImage";
import * as Color from "../../../../components/Colors/colors";
import { Spacer } from "../../../../components/Basic/Spacer";
import { ReservationAcceptRejectModal } from "../../../../components/Modal/ReservationAcceptRejectModal";
export const AllBookingListScreen = (props) => {
  const [data, setData] = useState([]);
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

  //예약 상태 변화 모달 띄우는 버튼
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [selectedBookingId, setSelectedBookingId] = useState();
  const [selectedUserName, setSelectedUserName] = useState();
  const [selectedDate, setSelectedDate] = useState();
  const [selectedTime, setSelectedTime] = useState();
  const [selectedProductName, setSelectedProductName] = useState();

  return (
    <View style={{ flex: 1 }}>
      <Spacer space={10} />

      <FlatList
        showsHorizontalScrollIndicator={false}
        data={data}
        renderItem={({ item, index }) => {
          return (
            <View>
              <Divider />
              <View
                style={{
                  marginHorizontal: 15,
                  flexDirection: "row",
                  justifyContent: "space-between",
                  alignItems: "center",
                }}
              >
                <View style={{ marginVertical: 4 }} flexDirection={"row"}>
                  <View
                    style={{ alignItems: "center", justifyContent: "center" }}
                  >
                    <ProfileImage size={60} url={item.imageUrl} />
                  </View>
                  <View>
                    <Typography fontSize={15}>{item.userName}</Typography>
                    <Typography>{item.productName}</Typography>
                    <Typography>
                      {item.reservationRealDate} {item.reservationTime}시 예약
                    </Typography>

                    <Typography fontSize={10} color={Color.GRAY}>
                      {item.userEmail}
                    </Typography>
                  </View>
                </View>
                <View style={{ flexDirection: "row" }}>
                  {/* Render TouchableOpacity conditionally */}
                  {item.reservationApproveStatus === "NOTCONFIRM" ? (
                    <TouchableOpacity
                      onPress={() => {
                        setSelectedBookingId(item.selectedBookingId);
                        setSelectedUserName(item.userName);
                        setSelectedDate(item.reservationRealDate);
                        setSelectedTime(item.reservationTime);
                        setSelectedProductName(item.productName);
                        setIsModalVisible(true);
                      }}
                    >
                      <View
                        style={{
                          backgroundColor:
                            colorStatus[item.reservationApproveStatus],
                          width: 100,
                          height: 40,
                          borderRadius: 10,
                          justifyContent: "center",
                          alignItems: "center",
                          marginHorizontal: 5,
                        }}
                      >
                        <Typography fontSize={15} color={Color.WHITE}>
                          {koreanStatus[item.reservationApproveStatus]}
                        </Typography>
                      </View>
                    </TouchableOpacity>
                  ) : (
                    <View
                      style={{
                        backgroundColor:
                          colorStatus[item.reservationApproveStatus],
                        width: 100,
                        height: 40,
                        borderRadius: 10,
                        justifyContent: "center",
                        alignItems: "center",
                        marginHorizontal: 5,
                      }}
                    >
                      <Typography fontSize={15} color={Color.WHITE}>
                        {koreanStatus[item.reservationApproveStatus]}
                      </Typography>
                    </View>
                  )}
                </View>
              </View>
            </View>
          );
        }}
        onEndReached={handleEndReached}
        onEndReachedThreshold={0.1}
      />
      <ReservationAcceptRejectModal
        isModalVisible={isModalVisible}
        selectedBookingId={selectedBookingId}
        selectedUserName={selectedUserName}
        selectedDate={selectedDate}
        selectedTime={selectedTime}
        selectedProductName={selectedProductName}
        setIsModalVisible={setIsModalVisible}
      />
    </View>
  );
};

const colorStatus = {
  ACCEPT: Color.LIGHT_BLUE,
  REJECT: Color.LIGHT_RED,
  NOTCONFIRM: Color.GRAY,
};

const koreanStatus = {
  ACCEPT: "승인됨",
  REJECT: "거절됨",
  NOTCONFIRM: "확인안함",
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
