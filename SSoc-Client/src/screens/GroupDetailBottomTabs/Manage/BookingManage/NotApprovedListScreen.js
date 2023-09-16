import React, { useState, useEffect, useCallback } from "react";
import { View, TouchableOpacity, FlatList } from "react-native";
import { Typography } from "../../../../components/Basic/Typography";
import { getAllBookingListFetch } from "../../../../util/FetchUtil";
import { Divider } from "../../../../components/Basic/Divider";
import { ProfileImage } from "../../../../modules/ProfileImage";
import * as Color from "../../../../components/Colors/colors";
import { Spacer } from "../../../../components/Basic/Spacer";
import { ReservationAcceptRejectModal } from "../../../../components/Modal/ReservationAcceptRejectModal";
import { getTokens } from "../../../../util/TokenUtil";

export const NotApprovedScreen = (props) => {
  const [data, setData] = useState([]);
  const [groupId, setGroupId] = useState(props.route.params.groupId);
  const [accessToken, setAccessToken] = useState(null);
  const [refreshToken, setRefreshToken] = useState(null);
  const [isTokenGet, setIsTokenGet] = useState(false);

  const getAllBookingListData = async () => {
    try {
      const response = await getAllBookingListFetch(
        accessToken,
        refreshToken,
        1
      );
      const newData = await response.json();
      console.log("api 결과", newData);
      setData(newData);
    } catch (e) {
      console.error(e);
      setData([]);
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

  // 예약 상태 변화 모달 띄우는 버튼
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [selectedBookingId, setSelectedBookingId] = useState();
  const [selectedUserName, setSelectedUserName] = useState();
  const [selectedDate, setSelectedDate] = useState();
  const [selectedTime, setSelectedTime] = useState();
  const [selectedProductName, setSelectedProductName] = useState();

  const openReservationModal = (item) => {
    setSelectedBookingId(item.selectedBookingId);
    setSelectedUserName(item.userName);
    setSelectedDate(item.reservationRealDate);
    setSelectedTime(item.reservationTime);
    setSelectedProductName(item.productName);
    setIsModalVisible(true);
  };

  return (
    <View style={{ flex: 1 }}>
      <Spacer space={10} />
      <TouchableOpacity onPress={onPressGetTest}>
        <Typography Typography>ddd</Typography>
      </TouchableOpacity>
      <FlatList
        showsHorizontalScrollIndicator={false}
        data={data}
        renderItem={({ item, index }) => (
          <BookingListItem
            item={item}
            openReservationModal={openReservationModal}
          />
        )}
        keyExtractor={(item) => item.selectedBookingId.toString()}
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

const BookingListItem = ({ item, openReservationModal }) => {
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
          <View style={{ alignItems: "center", justifyContent: "center" }}>
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
            <TouchableOpacity onPress={() => openReservationModal(item)}>
              <View
                style={{
                  backgroundColor: colorStatus[item.reservationApproveStatus],
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
                backgroundColor: colorStatus[item.reservationApproveStatus],
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
