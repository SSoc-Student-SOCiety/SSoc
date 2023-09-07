import { View } from "react-native";
import { Typography } from "../../../components/Basic/Typography";
import { StackHeader } from "../../../modules/StackHeader";

export const BoardListScreen = () => {
  return (
    <View style={{ flex: 1 }}>
      <StackHeader title={"게시판"}></StackHeader>
    </View>
  );
};
