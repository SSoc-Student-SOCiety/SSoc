import { View } from "react-native";
import * as Color from "../../../components/Colors/colors";
import { useCurrentDate } from "../../../util/hooks/useCurrentDate";
import { WalletSummarySction } from "../../../modules/Settlement/WalletSummarySection";
import { PieChartSection } from "../../../modules/Settlement/PieChartSection";
import { useCurrentWeekNumber } from "../../../util/hooks/useCurrentWeekNumber";
import { getTransactionByDurationFetch } from "../../../util/FetchUtil";
import { useState, useEffect } from "react";
import { getTokens } from "../../../util/TokenUtil";
import { ActivityIndicator } from "react-native-paper";
import { getBalanceFetch } from "../../../util/Fetch2Util";
import { getMonthlyStaticsFetch } from "../../../util/FetchUtil";
export const SettlementReportScreen = () => {
  const { month, year } = useCurrentDate();
  const { weekNumber } = useCurrentWeekNumber();

  const [isLoading, setIsLoading] = useState(true); // 로딩 상태 추가
  const [isListLoading, setIsListLoading] = useState(true);
  const [accessToken, setAccessToken] = useState(null);
  const [refreshToken, setRefreshToken] = useState(null);
  const [isTokenGet, setIsTokenGet] = useState(false);

  const [transactionList, setTransactionList] = useState([]);
  const [lastId, setLastId] = useState("");
  const [balance, setBalance] = useState("");
  const [yWithdrawals, setYWithdrawals] = useState([]);

  const getToday = () => {
    var date = new Date();
    var year = date.getFullYear();
    var month = (date.getMonth() + 1).toString().padStart(2, "0");
    var day = date.getDate().toString().padStart(2, "0");
    return year + "-" + month + "-" + day;
  };
  const [totalConsume, setTotalConsume] = useState(31259);

  const getMonthlyStaticsData = async () => {
    try {
      const response = await getMonthlyStaticsFetch(
        accessToken,
        refreshToken,
        1,
        getSevenDaysAgo(),
        getToday()
      );
      const data = await response.json();
      if (data != null && data.dataHeader != undefined) {
        const withdrawals = data.dataBody.map((item) =>
          parseFloat(item.withdrawal)
        );
        const months = data.dataBody.map((item) => item.date.slice(-2) + "일");
        setYWithdrawals(withdrawals);
        setIsLoading(false);
        console.log(withdrawals);
      }
    } catch (e) {
      console.error(e);
    }
    if (isLoading) {
    }
  };
  const getSevenDaysAgo = () => {
    var today = new Date();
    var date = new Date(today);
    date.setDate(today.getDate() - 7);
    var year = date.getFullYear();
    var month = (date.getMonth() + 1).toString().padStart(2, "0");
    var day = date.getDate().toString().padStart(2, "0");
    return year + "-" + month + "-" + day;
  };

  useEffect(() => {
    getTokens(setAccessToken, setRefreshToken, setIsTokenGet);
  }, []);

  useEffect(() => {
    if (isTokenGet) {
      getBalance();
      getTransactionData();
      getMonthlyStaticsData();
      console.log("test", yWithdrawals);
    }
  }, [isTokenGet]);

  const getTransactionData = async () => {
    try {
      const response = await getTransactionByDurationFetch(
        accessToken,
        refreshToken,
        1,
        getSevenDaysAgo(),
        getToday()
      );
      const data = await response.json();
      console.log("마지막", data.dataBody);
      if (data != null && data.dataHeader != undefined) {
        setTransactionList(convertTransactionsToPieGraphData(data.dataBody));
        setIsLoading(false);
      }
    } catch (e) {
      console.error(e);
    }
  };

  const getBalance = async () => {
    try {
      const response = await getBalanceFetch(accessToken, refreshToken, 1);
      const data = await response.json();
      console.log("마지막", data.dataBody);
      if (data != null && data.dataHeader != undefined) {
        setBalance(data.dataBody.balance);
        setIsLoading(false);
      }
    } catch (e) {
      console.error(e);
    }
  };
  return (
    <View style={{ backgroundColor: Color.WHITE, flex: 1 }}>
      {isLoading ? (
        // 로딩 중일 때 로딩 메시지 표시
        <ActivityIndicator size="large" color="#0000ff" />
      ) : (
        <>
          <PieChartSection
            data={transactionList}
            title={`${month}월 ${weekNumber}번째 주`}
          />
          <WalletSummarySction
            totalConsumption={totalConsume}
            balance={balance}
            month={month}
          />
        </>
      )}
    </View>
  );
};

const convertTransactionsToPieGraphData = (transactions) => {
  const data = transactions.map((transaction) => ({
    name: `${transaction.detail} (${transaction.branch})`,
    value: parseInt(transaction.withdrawal), // 문자열을 정수로 변환
    color: `rgba(${Math.floor(Math.random() * 100)}, ${Math.floor(
      Math.random() * 100
    )}, ${Math.floor(Math.random() * 100) + 155}, 0.7)`,
    legendFontColor: "#7F7F7F",
    legendFontSize: 10,
  }));

  data.sort((a, b) => b.value - a.value);

  return data;
};
