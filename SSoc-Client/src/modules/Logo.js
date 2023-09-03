import { View, Image, StyleSheet } from "react-native";
import { Typography } from "../components/Basic/Typography";
export const Logo = () => {
  return (
    <View style={styles.mainItem}>
      <Typography fontSize={20}>우리의 공금 내역 내폰으로,</Typography>
      <Image
        source={require("../../assets/SSOC_Logo.png")}
        style={{ height: 70, width: 200 }}
        resizeMode="contain"
      />
    </View>
  );
};

var styles = StyleSheet.create({
  mainItem: { paddingTop: 60, paddingHorizontal: 20 },
});
