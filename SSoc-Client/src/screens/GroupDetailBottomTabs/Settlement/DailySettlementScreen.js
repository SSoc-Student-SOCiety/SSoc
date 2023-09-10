import { View } from "react-native";
import * as Color from "../../../components/Colors/colors";
import { useCurrentDate } from "../../../util/hooks/useCurrentDate";
import { LineGraphSection } from "../../../modules/Settlement/LineGraphSection";
import { TransactionListSection } from "../../../modules/Settlement/TransactionListSection";

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
      <LineGraphSection
        title={`지난 7일 모아보기`}
        labels={weekLabels}
        data={mockData}
      />
      <TransactionListSection transactionList={TRANSACTION_LIST} />
    </View>
  );
};

const useCurrentWeekNumber = (month, year) => {
  const today = new Date();

  const firstDayOfMonth = new Date(year, month - 1, 1);

  const dayOfWeek = firstDayOfMonth.getDay();

  const firstWeekStartDate = new Date(firstDayOfMonth);
  firstWeekStartDate.setDate(1 - dayOfWeek);
  const currentDay = today.getDate();
  const weekNumber = Math.ceil((currentDay + dayOfWeek) / 7);

  return weekNumber;
};

const TRANSACTION_LIST = [
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
