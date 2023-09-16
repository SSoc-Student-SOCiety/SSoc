import { View, ActivityIndicator } from "react-native";
import { Typography } from "../../../components/Basic/Typography";
import * as Color from "../../../components/Colors/colors";
import { useCurrentDate } from "../../../util/hooks/useCurrentDate";
import { LineGraphSection } from "../../../modules/Settlement/LineGraphSection";
import { Divider } from "../../../components/Basic/Divider";
import { FlatList } from "react-native";
import { StyleSheet } from "react-native";
import { TransactionItem } from "../../../modules/Settlement/TransactionItem";
import { getMonthlyStaticsFetch } from "../../../util/FetchUtil";
import { useEffect, useState } from "react";
import { getTokens } from "../../../util/TokenUtil";
import { getTransactionFetch } from "../../../util/FetchUtil";
export const MonthlySettlementScreen = () => {
  const { year, month, day, today } = useCurrentDate();
  const [isLoading, setIsLoading] = useState(true); // 로딩 상태 추가
  const [isListLoading, setIsListLoading] = useState(true);
  const [accessToken, setAccessToken] = useState(null);
  const [refreshToken, setRefreshToken] = useState(null);
  const [isTokenGet, setIsTokenGet] = useState(false);

  const [data, setData] = useState([]);

  const [xMonths, setXMonths] = useState([]);
  const [yWithdrawals, setYWithdrawals] = useState([]);

  const [transactionList, setTransactionList] = useState([]);

  const getMonthlyStaticsData = async () => {
    try {
      const response = await getMonthlyStaticsFetch(
        accessToken,
        refreshToken,
        year,
        1
      );
      const data = await response.json();

      if (data != null && data.dataHeader != undefined) {
        const withdrawals = data.dataBody.map((item) =>
          parseInt(item.withdrawal / 10000)
        );
        const months = data.dataBody.map((item) => item.month);
        setXMonths(months);
        setYWithdrawals(withdrawals);
        setIsLoading(false);

        console.log(months);
      }
    } catch (e) {
      console.error(e);
    }
  };

  const getTransactionData = async () => {
    try {
      var accountId = 1;
      const response = await getTransactionFetch(accessToken, refreshToken, 1);
      const data = await response.json();

      console.log("transaction", data.dataBody);
      setTransactionList(data.dataBody);
    } catch (e) {
      console.error(e);
    }
  };

  useEffect(() => {
    if (!isTokenGet) {
      getTokens(setAccessToken, setRefreshToken, setIsTokenGet);
    } else {
      getMonthlyStaticsData();
      getTransactionData();
    }
  }, [isTokenGet]);

  return (
    <View style={{ flex: 1, backgroundColor: Color.WHITE }}>
      <View style={{ flex: 1 }}>
        {isLoading ? (
          // 로딩 중일 때 로딩 메시지 표시
          <ActivityIndicator size="large" color="#0000ff" />
        ) : (
          // 데이터가 로딩 완료되면 LineGraphSection 렌더링
          <LineGraphSection
            title={`${year}년 모아보기`}
            labels={xMonths}
            data={yWithdrawals}
          />
        )}
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

const monthLabels = [
  "Jan",
  "Feb",
  "Mar",
  "Apr",
  "May",
  "Jun",
  "Jul",
  "Agu",
  "Sep",
  "Oct",
  "Nov",
  "Dec",
];

const mockData = [
  Math.random() * 100,
  Math.random() * 100,
  Math.random() * 100,
  Math.random() * 100,
  Math.random() * 100,
  Math.random() * 100,
  Math.random() * 100,
  Math.random() * 100,
  Math.random() * 100,
  Math.random() * 100,
  Math.random() * 100,
  Math.random() * 100,
];
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
];

var styles = StyleSheet.create({
  commonItem: { paddingTop: 30, paddingHorizontal: 20 },
});
