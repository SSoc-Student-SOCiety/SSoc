import { View, Image, ScrollView, Animated } from "react-native";
import { LinearGradient } from "expo-linear-gradient";
import { StackHeader } from "../modules/StackHeader";
import defaultBg from "../../assets/basic_group_bgf.jpeg";
import { ProfileImage } from "../modules/ProfileImage";
import * as Color from "../components/Colors/colors";
import { Typography } from "../components/Basic/Typography";
import { Spacer } from "../components/Basic/Spacer";
import { useScrollEvent } from "../hooks/useScrollEvnet";
export const GroupDetailScreen = () => {
  const { onScrollEndDrag, onScrollBeginDrag, onScroll, headerAnim } =
    useScrollEvent();

  return (
    <View style={{ flex: 1 }}>
      <Animated.View
        style={{
          marginTop: headerAnim.interpolate({
            inputRange: [-3, 0, 90],
            outputRange: [0, 0, -250],
            extrapolate: "clamp",
          }),
          opacity: headerAnim.interpolate({
            inputRange: [-3, 0, 200],
            outputRange: [1, 1, 0],
            extrapolate: "clamp",
          }),
        }}
      >
        <View
          style={{
            position: "absolute",
            height: 250,
            width: "100%",
            backgroundColor: "#11111111",
          }}
        >
          <View style={{ flex: 1 }}>
            <Image source={defaultBg} style={{ width: "100%", height: 250 }} />
          </View>
          <View
            style={{
              backgroundColor: "rgba(0, 0, 0, 0.5)",
              height: 90,
            }}
          >
            <View style={{ alignItems: "center", flexDirection: "row" }}>
              <ProfileImage size={70} url={"https://picsum.photos/500"} />
              <View style={{ marginHorizontal: 3 }}>
                <Typography fontSize={30} color="white">
                  달리샤
                </Typography>
                <Spacer space={4} />
                <Typography fontSize={20} color="white">
                  싸피대학교
                </Typography>
              </View>
            </View>
          </View>
        </View>
      </Animated.View>
      <ScrollView
        scrollEventThrottle={1}
        onScrollBeginDrag={onScrollBeginDrag}
        onScroll={onScroll}
        onScrollEndDrag={onScrollEndDrag}
      >
        <Typography fontSize={1000}>d</Typography>
      </ScrollView>
    </View>
  );
};
