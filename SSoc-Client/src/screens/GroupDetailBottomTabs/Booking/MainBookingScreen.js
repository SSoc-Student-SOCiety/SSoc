import {
  TouchableOpacity,
  View,
  StyleSheet,
  ScrollView,
  Image,
} from "react-native";
import { Typography } from "../../../components/Basic/Typography";
import { StackHeader } from "../../../modules/StackHeader";
import { useNavigation } from "@react-navigation/native";
import React, { useCallback, useState } from "react";
import { Button } from "../../../components/Basic/Button";
import { CategoryButton } from "../../../modules/CategoryButton";
import * as Color from "../../../components/Colors/colors";
import { FlatList } from "react-native";
import { Spacer } from "../../../components/Basic/Spacer";
import { Divider } from "../../../components/Basic/Divider";
import { RemoteImage } from "../../../components/ImageLoader/RemoteImage";
export const MainBookingScreen = () => {
  const [selectedCategory, setSelectedCategory] = useState("all");

  const onPressOption = (selectedOption) => {
    setSelectedCategory(selectedOption);
  };
  const SelectSearchOption = () => {
    return (
      <View style={styles.commonItem}>
        <ScrollView horizontal showsHorizontalScrollIndicator={false}>
          <Button onPress={() => onPressOption("all")}>
            <CategoryButton
              title={"전체 검색"}
              isActivated={selectedCategory === "all"}
            />
          </Button>

          <Button onPress={() => onPressOption("convenience")}>
            <CategoryButton
              title={"편의성"}
              isActivated={selectedCategory === "convenience"}
            />
          </Button>
          <Button onPress={() => onPressOption("party")}>
            <CategoryButton
              title={"행사용품"}
              isActivated={selectedCategory === "party"}
            />
          </Button>
          <Button onPress={() => onPressOption("book")}>
            <CategoryButton
              title={"전공서적"}
              isActivated={selectedCategory === "book"}
            />
          </Button>
        </ScrollView>
      </View>
    );
  };

  const renderRow = (item1, item2) => {
    return (
      <View style={{ flexDirection: "row", justifyContent: "center" }}>
        <ItemCard item={item1} />
        <ItemCard item={item2} />
      </View>
    );
  };

  const renderItem = ({ item, index }) => {
    if (index % 2 === 0 && index + 1 < mockResponse.dataBody.length) {

      return renderRow(
        mockResponse.dataBody[index],
        mockResponse.dataBody[index + 1]
      );
    } else if (index === mockResponse.dataBody.length - 1) {
      if (mockResponse.dataBody.length % 2 === 1)
        // 마지막 항목이 홀수 개일 경우
        return (
          <View style={{ flexDirection: "row", justifyContent: "center" }}>
            <ItemCard item={item} />
            <EmptyCard />
          </View>
        );
    } else {
      return null; // 홀수 번째 항목은 무시
    }
  };

  return (
    <View style={{ flex: 1, backgroundColor: Color.WHITE }}>
      <View style={{ justifyContent: "center" }}>
        <Spacer space={10} />
        <View
          style={{
            height: 50,
            justifyContent: "center",
            borderRadius: 10,
          }}
        >
          <SelectSearchOption />
        </View>

        <Spacer space={5} />
        <View style={{ marginHorizontal: -15 }}>
          <Divider />
        </View>

        <Spacer space={10} />
        <FlatList
          data={mockResponse.dataBody}
          renderItem={renderItem}
          keyExtractor={(item, index) => index.toString()}
        />
      </View>
    </View>
  );
};
const EmptyCard = () => {
  return (
    <View style={styles.emptyCard}>
      <Typography color={Color.WHITE}>빈 카드</Typography>
    </View>
  );
};
const ItemCard = ({ item }) => {
  const navigation = useNavigation();

  const onPressGoDetail = useCallback((item) => {
    navigation.navigate("BookingItemDetialScreen", {item: item});
  });
  return (
    <TouchableOpacity onPress={()=>onPressGoDetail(item)}>
      <View style={styles.card}>
        <View
          style={{
            alignItems: "center",
            justifyContent: "center",
            margin: 10,
          }}
        >
          <Image
            source={{ uri: item.imgUrl }} // 외부 이미지 URL 설정
            style={{ width: 100, height: 130, borderRadius: 15 }}
          />
        </View>
        <View>
          <Typography color={Color.WHITE}>{item.name}</Typography>
          <Typography color={Color.LIGHT_GRAY} fontSize={10}>
            {item.description}
          </Typography>
        </View>
      </View>
    </TouchableOpacity>
  );
};
const styles = StyleSheet.create({
  commonItem: { paddingHorizontal: 10 },
  card: {
    backgroundColor: Color.LIGHT_BLUE,
    width: 150,
    height: 250,
    borderRadius: 18,
    margin: 10,
    marginVertical: 20,
    justifyContent: "space-evenly",
    alignItems: "center",
  },
  emptyCard: {
    backgroundColor: Color.WHITE,
    width: 150,
    height: 250,
    borderRadius: 18,
    margin: 10,
    marginVertical: 20,
    justifyContent: "center",
    alignItems: "center",
  },
});

const mockResponse = {
  dataHeader: {
    successCode: 0,
    resultCode: null,
    resultMessage: null,
  },
  dataBody: [
    {
      productId: 2,
      productCategory: "편의성",
      name: "보조배터리",
      description: "1일 이내 반납을 완료해야합니다.",
      imgUrl:
        "https://image.msscdn.net/images/goods_img/20230208/3068612/3068612_16758386621147_500.jpg",
    },
    {
      productId: 3,
      productCategory: "편의성",
      name: "아이폰 핸드폰 충전기A",
      description: "1일 이내 반납을 완료해야합니다.",
      imgUrl:
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fshop1.phinf.naver.net%2F20220206_180%2F1644119469853XXfYc_JPEG%2F9913751.jpg&type=sc960_832",
    },
    {
      productId: 4,
      productCategory: "편의성",
      name: "아이폰 핸드폰 충전기B",
      description: "1일 이내 반납을 완료해야합니다.",
      imgUrl:
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fshop1.phinf.naver.net%2F20220206_180%2F1644119469853XXfYc_JPEG%2F9913751.jpg&type=sc960_832",
    },
    {
      productId: 5,
      productCategory: "편의성",
      name: "갤럭시 핸드폰 충전기A",
      description: "1일 이내 반납을 완료해야합니다.",
      imgUrl:
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fshop1.phinf.naver.net%2F20230909_240%2F1694186475378yUKlB_JPEG%2F7465877220560695_821391124.JPG&type=sc960_832",
    },
    {
      productId: 6,
      productCategory: "편의성",
      name: "우산A",
      description: "1일 이내 반납을 완료해야합니다.",
      imgUrl: "https://noranusan.kr/web/product/big/unicozz_1524.jpg",
    },
    {
      productId: 7,
      productCategory: "편의성",
      name: "우산B",
      description: "1일 이내 반납을 완료해야합니다.",
      imgUrl: "https://noranusan.kr/web/product/big/unicozz_1524.jpg",
    },
    {
      productId: 8,
      productCategory: "행사용품",
      name: "마이크",
      description: "1일 이내 반납을 완료해야합니다.",
      imgUrl:
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fshop1.phinf.naver.net%2F20230319_227%2F1679162475347O3CBv_JPEG%2F80298371082822823_1474162544.jpg&type=sc960_832",
    },
    {
      productId: 9,
      productCategory: "행사용품",
      name: "마이크 스탠드",
      description: "1일 이내 반납을 완료해야합니다.",
      imgUrl:
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fshop1.phinf.naver.net%2F20230721_209%2F16899276801011EEVc_JPEG%2F2448496881959917_1391815355.jpg&type=sc960_832",
    },
    {
      productId: 10,
      productCategory: "행사용품",
      name: "릴선",
      description: "1일 이내 반납을 완료해야합니다.",
      imgUrl:
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fshop1.phinf.naver.net%2F20200109_167%2F15785675338242eyGw_PNG%2F15929076432844282_999398388.PNG&type=sc960_832",
    },
    {
      productId: 11,
      productCategory: "행사용품",
      name: "L카",
      description: "1일 이내 반납을 완료해야합니다.",
      imgUrl:
        "https://search.pstatic.net/common/?src=http%3A%2F%2Fcafefiles.naver.net%2F20160727_2%2Fymm0230_1469577559911xKedQ_JPEG%2F1469577385722.jpg&type=sc960_832",
    },

    //예약 현황 조회
    //날짜, 대여물품 id 입력 => 해당 날짜의 예약 테이블을 확인하여, 대여물품의 id가 같으며 승인여부가 true인 데이터가 있는지 확인 => 있다면 예약 불가임

    //예약 신청 요청
    //날짜, 대여물품 id, 유저 id 입력 => 해당 날짜의 대여물품 id의 대여결과 칼럼 확인

    //예약 승인
    //해당 예약 id => 실제 예약일, 물품 아이디와 동일하며 승인여부가 true인 데이터가 있는지 확인 => 없을 경우 최종 승인 처리

    // 관리자용 예약 전체 리스트 조회
    //물품명
    //유저명
    //유저 이메일
    //실제 예약일

    // 관리자용 대여 중 전체 리스트 조회
    //예약테이블 중에서 반납확인이 false이고 승인여부
  ],
};
