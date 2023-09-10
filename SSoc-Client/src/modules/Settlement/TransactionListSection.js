import { View, FlatList, StyleSheet } from "react-native";
import { Divider } from "../../components/Basic/Divider";
import { TransactionItem } from "./TransactionItem";
import { Typography } from "../../components/Basic/Typography";
export const TransactionListSection = ({ transactionList }) => {
  return (
    <View>
      <View style={{ marginHorizontal: 25, marginVertical: 10 }}>
        <Typography fontSize={24}>거래 내역</Typography>
      </View>
      <Divider />
      <FlatList
        style={styles.commonItem}
        data={transactionList}
        renderItem={({ item }) => {
          return <TransactionItem item={item}></TransactionItem>;
        }}
      />
    </View>
  );
};

var styles = StyleSheet.create({
  commonItem: { paddingTop: 30, paddingHorizontal: 20 },
});
