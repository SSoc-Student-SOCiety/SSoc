import { TouchableOpacity, View } from "react-native";
import { Typography } from "../../../components/Basic/Typography";
import { StackHeader } from "../../../modules/StackHeader";
import { useNavigation } from "@react-navigation/native";
import React, {useCallback} from "react";
export const MainBookingScreen = () => {
  const navigation = useNavigation();

  const onPressGoDetail = useCallback(()=>{
    navigation.navigate("BookingItemDetialScreen")
  })
  
  return (
    <View style={{ flex: 1 }}>
      <TouchableOpacity onPress={onPressGoDetail}>
        <Typography >dddd</Typography>
      </TouchableOpacity>
    </View>
  );
};
