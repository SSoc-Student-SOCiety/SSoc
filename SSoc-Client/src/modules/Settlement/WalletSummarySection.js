import { View } from "react-native";
import { Spacer } from "../../components/Basic/Spacer";
import { Divider } from "../../components/Basic/Divider";
import { Typography } from "../../components/Basic/Typography";
import * as Color from "../../components/Colors/colors";
import { SummaryCard } from "./SummaryCard";

export const WalletSummarySction = ({ totalConsumption, balance, month }) => {
  return (
    <View>
      <View style={{ marginHorizontal: 25, marginVertical: 10, marginTop: 20 }}>
        <Typography fontSize={24}>잔액</Typography>
      </View>
      <Divider></Divider>
      <Spacer space={24} />
      <View style={{ justifyContent: "center", alignItems: "center" }}>
        <View
          style={{
            backgroundColor: Color.LIGHT_BLUE,
            height: 200,
            width: 350,
            borderRadius: 20,
          }}
        >
          <View style={{ padding: 20 }}>
            <Typography color={Color.WHITE} fontSize={20}>
              이번 달 얼마나 썼을까요
            </Typography>
            <Spacer space={5} />
            <Typography color={Color.WHITE}>
              {month}월 한달 동안의 소비 금액이에요.
            </Typography>
            <Spacer space={3} />
            <SummaryCard category={"소비액"} result={totalConsumption} />
            <SummaryCard category={"잔액"} result={balance} />
          </View>
        </View>
      </View>
    </View>
  );
};
