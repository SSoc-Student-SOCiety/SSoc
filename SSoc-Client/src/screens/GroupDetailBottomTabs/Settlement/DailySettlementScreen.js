import { View, StyleSheet } from "react-native";
import * as Color from "../../../components/Colors/colors";
import { useCurrentDate } from "../../../util/hooks/useCurrentDate";
import { LineGraphSection } from "../../../modules/Settlement/LineGraphSection";
import { TransactionItem } from "../../../modules/Settlement/TransactionItem";
import { Typography } from "../../../components/Basic/Typography";
import { Divider } from "../../../components/Basic/Divider";
import { FlatList } from "react-native";
import React, { useState } from "react";

const weekLabels = ["월", "화", "수", "목", "금", "토", "일"];
const mockData = [
  Math.random() * 100,
  Math.random() * 100,
  Math.random() * 100,
  Math.random() * 100,
  Math.random() * 100,
  Math.random() * 100,
  Math.random() * 100,
];
export const DaillySettlementScreen = () => {
  const { year, month, day, today } = useCurrentDate();

  return (
    <View style={{ flex: 1, backgroundColor: Color.WHITE }}>
      <View style={{ flex: 1 }}>
        {/* <LineGraphSection
          title={`지난 7일 모아보기`}
          labels={weekLabels}
          data={mockData}
        /> */}
      </View>
      <View style={{ flex: 1.2 }}>
        <View style={{ marginHorizontal: 25, marginVertical: 10 }}>
          <Typography fontSize={24}>거래 내역</Typography>
        </View>
        <Divider />
        <FlatList
          contentContainerStyle={{ paddingBottom: 30 }}
          style={styles.commonItem}
          data={transactionList}
          renderItem={({ item }) => {
            return <TransactionItem item={item}></TransactionItem>;
          }}
        />
      </View>
    </View>
  );
};

const transactionList = [
  {
    id: 1,
    name: "김밥천국",
    note: "간식사업 구매  비용",
    date: "2023.03.18",
    withdrawl: "220000",
    deposit: "0",
  },
  {
    id: 2,
    name: "사계진미 숯불 닭갈비",
    note: "정기 회의 후 회식",
    date: "2023.03.17",
    withdrawl: "320000",
    deposit: "0",
  },
  {
    id: 3,
    name: "광산약국",
    note: "상시 사업 약품 비용",
    date: "2023.03.15",
    withdrawl: "54900",
    deposit: "0",
  },
  {
    id: 4,
    name: "스타벅스",
    note: "개강 소통 이벤트 상품",
    date: "2023.03.12",
    withdrawl: "24000",
    deposit: "0",
  },
  {
    id: 5,
    name: "알파문구",
    note: "구비 용품 구매",
    date: "2023.04.15",
    withdrawl: "194600",
    deposit: "0",
  },
  {
    id: 6,
    name: "알파문구3",
    note: "구비 용품 구매",
    date: "2023.04.15",
    withdrawl: "194600",
    deposit: "0",
  },
  {
    id: 7,
    name: "알파문구2",
    note: "구비 용품 구매",
    date: "2023.04.15",
    withdrawl: "194600",
    deposit: "0",
  },
  {
    id: 8,
    name: "알파문구1",
    note: "구비 용품 구매",
    date: "2023.04.15",
    withdrawl: "194600",
    deposit: "0",
  },
];

var styles = StyleSheet.create({
  commonItem: { paddingTop: 30, paddingHorizontal: 20 },
});
