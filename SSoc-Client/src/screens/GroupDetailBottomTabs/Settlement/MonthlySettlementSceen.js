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
  const [lastId, setLastId] = useState("");

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
      }
    } catch (e) {
      console.error(e);
    }
  };

  const getTransactionData = async () => {
    try {
      var accountId = 1;
      const response = await getTransactionFetch(
        accessToken,
        refreshToken,
        1,
        lastId
      );
      const data = await response.json();

      console.log("heeadf");
      return data;
    } catch (e) {
      console.error(e);
    }
  };

  const loadData = async () => {
    const newData = await getTransactionData();
    // console.log(lastId);
    if (newData.dataHeader.successCode == 0) {
      if (newData.dataBody.length > 0) {
        setLastId(
          newData.dataBody[newData.dataBody.length - 1].transactionId.toString()
        );
        setTransactionList((prevData) => [...prevData, ...newData.dataBody]);
      } else {
        if (data.length >= 10) Alert.alert("마지막 페이지입니다.");
      }
    }
  };

  useEffect(() => {
    getTokens(setAccessToken, setRefreshToken, setIsTokenGet);
  }, []);

  useEffect(() => {
    if (isTokenGet) {
      getMonthlyStaticsData();
      // getTransactionData();
      loadData();
    }
  }, [isTokenGet]);

  const handleEndReached = () => {
    loadData();
  };

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
          onEndReached={handleEndReached}
          onEndReachedThreshold={0.2}
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

var styles = StyleSheet.create({
  commonItem: { paddingTop: 30, paddingHorizontal: 20 },
});
