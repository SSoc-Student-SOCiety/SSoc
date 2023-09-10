import { TouchableOpacity, View } from "react-native";
import { Typography } from "../components/Basic/Typography";
import * as Color from "../components/Colors/colors";
import { ProfileImage } from "./ProfileImage";
import { Spacer } from "../components/Basic/Spacer";
import { useNavigation } from "@react-navigation/native";
export const Card = (props) => {
  const navigation = useNavigation();
  const onPressDetail = () => {
    const { onPress, ...otherProps } = props;

    navigation.navigate("GroupDetailScreen", otherProps);
  };
  return (
    <TouchableOpacity onPress={onPressDetail}>
      <View
        style={{
          height: 210,
          width: 140,
          borderRadius: 10,
          marginHorizontal: 5,
        }}
        backgroundColor={Color.BLUE}
      >
        <View style={{ marginHorizontal: 10, marginVertical: 10 }}>
          <ProfileImage size={50} url={props.url} />
          <Spacer space={10} />
          <Typography fontSize={20} color={Color.WHITE}>
            {props.school}
          </Typography>
          <Spacer space={5} />
          <Typography fontSize={17} color={Color.WHITE}>
            {props.name}
          </Typography>
          <Spacer space={5} />
          <Typography fontSize={15} color={Color.LIGHT_GRAY}>
            {props.groupType}
          </Typography>
          <Spacer space={5} />
          <Typography fontSize={12} color={Color.LIGHT_GRAY}>
            {props.subName}
          </Typography>
          <Spacer space={20} />
          <Typography fontSize={13} color={Color.WHITE}>
            최근 업데이트
          </Typography>
          <Typography fontSize={12} color={Color.WHITE}>
            {props.val} {props.unit} ago
          </Typography>
        </View>
      </View>
    </TouchableOpacity>
  );
};
