import {
  View,
  Image,
  ScrollView,
  Animated,
  TouchableOpacity,
} from "react-native";
import { LinearGradient } from "expo-linear-gradient";
import { StackHeader } from "../modules/StackHeader";
import defaultBg from "../../assets/basic_group_bgf.jpeg";
import { ProfileImage } from "../modules/ProfileImage";
import * as Color from "../components/Colors/colors";
import { Typography } from "../components/Basic/Typography";
import { Spacer } from "../components/Basic/Spacer";
import { useScrollEvent } from "../hooks/useScrollEvnet";
import { SearchButton } from "../modules/SearchButton";
import { useSafeAreaInsets } from "react-native-safe-area-context";
import { Icon } from "../components/Icons/Icons";
import { SafeAreaView } from "react-native-safe-area-context";
import { useCallback } from "react";
import { useNavigation } from "@react-navigation/native";
export const GroupDetailScreen = ({ route }) => {
  //to-do
  // 그룹아이디 같이 보냄
  // 해당 유저가 동아리 소속원인지, 동아리 소속원이 아닌지
  // 해당 유저가 동아리 매니저인지 동아리 매니저인지 받아옴
  //  버튼 동적 표현 구현

  const { onScrollEndDrag, onScrollBeginDrag, onScroll, headerAnim } =
    useScrollEvent();
  const insets = useSafeAreaInsets();
  const navigation = useNavigation();

  const { name, groupType, school, url, val, unit, subName } = route.params;

  const onPressGoBack = useCallback(() => {
    navigation.goBack();
  });

  const onPressGoToTab = useCallback((tabName) => {
    navigation.navigate("GroupDetailTab", { tabName });
  });

  // TO-DO (수린)
  // 4개 버튼 탭 눌렀을 때 groupId값도 같이 넘겨줘야함. 위의 navigate tabname과 함께 같이 쏴야할 듯 합니당
  const ButtonData = [
    {
      title: "공금 사용현황",
      tabName: "settlement",
    },
    {
      title: "커뮤니티 바로가기",
      tabName: "board",
    },
    {
      title: "동아리 / 학생회 일정",
      tabName: "schedlue",
    },
    {
      title: "물품 예약",
      tabName: "booking",
    },
  ];

  return (
    <View style={{ flex: 1 }}>
      <TouchableOpacity onPress={onPressGoBack} style={{ zIndex: 1 }}>
        <View
          style={{
            position: "absolute",
            paddingTop: insets.top,
            paddingHorizontal: 20,
          }}
        >
          <Icon name="arrow-back" color="black" size={30} />
        </View>
      </TouchableOpacity>
      <Animated.View
        style={{
          marginTop: headerAnim.interpolate({
            inputRange: [-3, 0, 300],
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
            backgroundColor: Color.BLACK,
          }}
        >
          <View style={{ flex: 1 }}>
            <Image
              source={require("../../assets/basic_group_bgf.jpeg")}
              style={{ width: "100%", height: 250 }}
            />
          </View>

          <View
            style={{
              backgroundColor: "rgba(0, 0, 0, 0.5)",
              height: 90,
              justifyContent: "center",
              paddingHorizontal: 15,
            }}
          >
            <View style={{ alignItems: "center", flexDirection: "row" }}>
              <ProfileImage size={70} url={url} />
              <View style={{ marginHorizontal: 5, marginBottom: 3 }}>
                <Typography fontSize={30} color="white">
                  {name}
                </Typography>
                <Spacer space={4} />
                <Typography fontSize={20} color="white">
                  {school}
                </Typography>
              </View>
            </View>
          </View>
        </View>
      </Animated.View>
      <SafeAreaView>
        <Spacer space={250} />
        <ScrollView
          scrollEventThrottle={1}
          onScrollBeginDrag={onScrollBeginDrag}
          onScroll={onScroll}
          onScrollEndDrag={onScrollEndDrag}
        >
          <View style={{ marginHorizontal: 20 }}>
            <Typography fontSize={30}>{school}</Typography>
            <Typography fontSize={22}>{subName}</Typography>
            <Spacer space={5} />
            <Typography fontSize={17} color={Color.GRAY}>
              Update {val}
              {unit} ago
            </Typography>
            <Spacer space={30} />
            {ButtonData.map((button, index) => (
              <TouchableOpacity
                key={index}
                onPress={() => onPressGoToTab(button.tabName)}
              >
                <View
                  style={{
                    backgroundColor: Color.BLUE,
                    borderRadius: 10,
                    marginVertical: 5,
                  }}
                >
                  <SearchButton
                    color={Color.BLUE}
                    title={button.title}
                    iconName="airplane-outline"
                    isIcon={false}
                  />
                </View>
              </TouchableOpacity>
            ))}
            <Spacer space={40} />
            <Typography fontSize={22}>About us</Typography>
            <Spacer space={40} />
            <Typography fontSize={15}>
              {/* to-do :  group id 값으로 받아오기 */}
              교내 중앙동아리 중 유일한 달리기 동아리이다. 2011년 나이키
              트레이닝 런에 참여하던 모임에서 출발해 함께 마라톤대회에 나가고
              러닝 프로그램에 참여하는 교내 소모임의 성격이었다가, 2012년 하반기
              가동아리 신청과 함께 본격적인 교내 동아리로 발전하였다. 달리샤라는
              이름은 동아리화에 대해 이야기하다 이것저것 막 던지다 정해진
              것으로, 달리기+人(사람 인)+정문 샤 모양의 조합으로 달리人F로
              정했고, 달리샤로 부르게 되었다. 평일에는 교내 운동장 트랙 및
              순환도로 달리기, 주말에는 나이키 트레이닝 런 또는 대회 참여 등
              교외 달리기로 주2회 모임을 기본으로 하여, 현재(2018년 상반기
              기준)도 수요일 저녁 교내 달리기, 토요일 오전 교외 달리기의 기조가
              유지되고 있다(+모임 후기 작성자를 가위바위보로 정하는 것도 계속
              유지되고 있다. 과거 활동했던 회원들에게는 아련한 추억일 듯하다.).
              한때는 맴버들끼리 '먹고 마시고 달리샤'라 부를 정도로 거의 매
              모임마다 뒤풀이 및 번개 모임과 뒤풀이, 또는 그냥 먹고 마시는
              모임들을 가졌었으나 정동아리가 되면서 회원수가 급증하고부터는
              운동동아리 본연의 모습으로 돌아왔다. 교내 구성원이면 상시 가입
              가능하며, 모임 참여 및 회비 납부를 활동회원의 기준으로 하고 있다.
              2020년 상반기에는 코로나19로 인해 다른 기준 적용되고 있다. 자세한
              내용은 인스타그램 달리샤
              계정(http://www.instagram.com/snu_dalisha) 공지글 참조. 2018년
              상반기에 동아리방을 얻게 되어, 학생회관 616호를 동아리방으로 사용
              중이다.(2020년 8월 기준) 2021년에도 코로나로 인한 힘든시기를
              겪고있으나 회장의 뛰어난 리더쉽으로 이끌어가는중이다.
            </Typography>
          </View>
        </ScrollView>
      </SafeAreaView>
    </View>
  );
};
