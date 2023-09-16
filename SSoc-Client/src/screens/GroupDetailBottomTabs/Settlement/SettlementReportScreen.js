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
export const SettlementReportScreen = () => {
  const data = convertTransactionsToPieGraphData(TRANSACTION_LIST);
  const { month, year } = useCurrentDate();
  const { weekNumber } = useCurrentWeekNumber();

  const [isLoading, setIsLoading] = useState(true); // 로딩 상태 추가
  const [isListLoading, setIsListLoading] = useState(true);
  const [accessToken, setAccessToken] = useState(null);
  const [refreshToken, setRefreshToken] = useState(null);
  const [isTokenGet, setIsTokenGet] = useState(false);

  const [transactionList, setTransactionList] = useState([]);
  const [lastId, setLastId] = useState("");

  const getToday = () => {
    var date = new Date();
    var year = date.getFullYear();
    var month = (date.getMonth() + 1).toString().padStart(2, "0");
    var day = date.getDate().toString().padStart(2, "0");
    return year + "-" + month + "-" + day;
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
      getTransactionData();
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
          <WalletSummarySction totalConsumption={0} balance={0} month={month} />
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

const TRANSACTION_LIST = [
  {
    id: 1,
    name: "김밥천국",
    note: "간식사업 구매  비용",
    date: "2023.03.18",
    withdrawl: 220000,
    deposit: "0",
  },
  {
    id: 2,
    name: "사계진미 숯불 닭갈비",
    note: "정기 회의 후 회식",
    date: "2023.03.17",
    withdrawl: 320000,
    deposit: "0",
  },
  {
    id: 3,
    name: "광산약국",
    note: "상시 사업 약품 비용",
    date: "2023.03.15",
    withdrawl: 54900,
    deposit: "0",
  },
  {
    id: 4,
    name: "스타벅스",
    note: "개강 소통 이벤트 상품",
    date: "2023.03.12",
    withdrawl: 24000,
    deposit: "0",
  },
  {
    id: 5,
    name: "알파문구",
    note: "구비 용품 구매",
    date: "2023.04.15",
    withdrawl: 194600,
    deposit: "0",
  },
  {
    id: 5,
    name: "알파문구",
    note: "구비 용품 구매",
    date: "2023.04.15",
    withdrawl: 194600,
    deposit: "0",
  },
  {
    id: 5,
    name: "알파문구",
    note: "구비 용품 구매",
    date: "2023.04.15",
    withdrawl: 194600,
    deposit: "0",
  },
  {
    id: 5,
    name: "알파문구",
    note: "구비 용품 구매",
    date: "2023.04.15",
    withdrawl: 194600,
    deposit: "0",
  },
  {
    id: 5,
    name: "알파문구",
    note: "구비 용품 구매",
    date: "2023.04.15",
    withdrawl: 194600,
    deposit: "0",
  },
  {
    id: 5,
    name: "알파문구",
    note: "구비 용품 구매",
    date: "2023.04.15",
    withdrawl: 194600,
    deposit: "0",
  },
  {
    id: 5,
    name: "알파문구",
    note: "구비 용품 구매",
    date: "2023.04.15",
    withdrawl: 194600,
    deposit: "0",
  },
  {
    id: 5,
    name: "알파문구",
    note: "구비 용품 구매",
    date: "2023.04.15",
    withdrawl: 194600,
    deposit: "0",
  },
];
