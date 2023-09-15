import { View, Image, ScrollView, Animated, TouchableOpacity, Alert } from 'react-native'
import { ProfileImage } from '../modules/ProfileImage'
import * as Color from '../components/Colors/colors'
import { Typography } from '../components/Basic/Typography'
import { Spacer } from '../components/Basic/Spacer'
import { useScrollEvent } from '../hooks/useScrollEvnet'
import { SearchButton } from '../modules/SearchButton'
import { useSafeAreaInsets } from 'react-native-safe-area-context'
import { Icon } from '../components/Icons/Icons'
import { SafeAreaView } from 'react-native-safe-area-context'
import { useCallback, useEffect, useState } from 'react'
import { useNavigation } from '@react-navigation/native'
import { getGroupDetailFetch, getGroupRoleFetch } from '../util/FetchUtil'
import { getTokens } from '../util/TokenUtil'
export const GroupDetailScreen = ({ route }) => {
  const { onScrollEndDrag, onScrollBeginDrag, onScroll, headerAnim } = useScrollEvent()
  const insets = useSafeAreaInsets()
  const navigation = useNavigation()

  //to-do
  // 그룹아이디 같이 보냄
  // 해당 유저가 동아리 소속원인지, 동아리 소속원이 아닌지
  // 해당 유저가 동아리 매니저인지 동아리 매니저인지 받아옴
  //  버튼 동적 표현 구현
  const groupId = route.params.groupId

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)
  const [groupDetailData, setGroupDetailData] = useState(null)
  const [groupMemberRole, setGroupMemberRole] = useState('')

  const getGroupDetailData = async () => {
    try {
      const response = await getGroupDetailFetch(accessToken, refreshToken, groupId)
      const data = await response.json()
      await setGroupDetailData(data.dataBody)
    } catch (e) {
      console.log(e)
    }
  }

  // 그룹원인 경우 = groupMemberRole = enum(MEMBER, MANAGER)
  // 그룹원이 아닌 경우 = groupMemberRole = ''
  const getGroupRoleData = async () => {
    try {
      const response = await getGroupRoleFetch(accessToken, refreshToken, groupId)
      const data = await response.json()
      if (data.dataHeader != undefined) {
        if (data.dataHeader.successCode == 0) {
          setGroupMemberRole(data.dataBody.groupMemberRole)
        }
      } else {
        Alert.alert('알 수 없는 에러 발생')
      }
    } catch (e) {
      console.log(e)
    }
  }

  useEffect(() => {
    if (!isTokenGet) getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
    else {
      getGroupRoleData()
      getGroupDetailData()
    }
  }, [isTokenGet])

  const onPressGoBack = useCallback(() => {
    navigation.goBack()
  })

  const onPressGoToTab = useCallback((tabName) => {
    navigation.navigate('GroupDetailTab', { tabName, groupId, groupMemberRole })
  })

  const ButtonData = [
    {
      title: '공금 사용현황',
      tabName: 'settlement',
    },
    {
      title: '커뮤니티 바로가기',
      tabName: 'board',
    },
    {
      title: '동아리 / 학생회 일정',
      tabName: 'schedlue',
    },
    {
      title: '물품 예약',
      tabName: 'booking',
    },
  ]

  if (groupDetailData != null)
    return (
      <View style={{ flex: 1 }}>
        <TouchableOpacity
          onPress={onPressGoBack}
          style={{ zIndex: 1 }}
        >
          <View
            style={{
              position: 'absolute',
              paddingTop: insets.top,
              paddingHorizontal: 20,
            }}
          >
            <Icon
              name="arrow-back"
              color="black"
              size={30}
            />
          </View>
        </TouchableOpacity>
        <Animated.View
          style={{
            marginTop: headerAnim.interpolate({
              inputRange: [-3, 0, 300],
              outputRange: [0, 0, -250],
              extrapolate: 'clamp',
            }),
            opacity: headerAnim.interpolate({
              inputRange: [-3, 0, 200],
              outputRange: [1, 1, 0],
              extrapolate: 'clamp',
            }),
          }}
        >
          <View
            style={{
              position: 'absolute',
              height: 250,
              width: '100%',
              backgroundColor: Color.BLACK,
            }}
          >
            <View style={{ flex: 1 }}>
              <Image
                source={require('../../assets/basic_group_bgf.jpeg')}
                style={{ width: '100%', height: 250 }}
              />
            </View>

            <View
              style={{
                backgroundColor: 'rgba(0, 0, 0, 0.5)',
                height: 90,
                justifyContent: 'center',
                paddingHorizontal: 15,
              }}
            >
              <View style={{ alignItems: 'center', flexDirection: 'row' }}>
                <ProfileImage
                  size={70}
                  url={groupDetailData.thumbnail}
                />
                <View style={{ marginHorizontal: 5, marginBottom: 3 }}>
                  <Typography
                    fontSize={30}
                    color="white"
                  >
                    {groupDetailData.name}
                  </Typography>
                  <Spacer space={4} />
                  <Typography
                    fontSize={20}
                    color="white"
                  >
                    {groupDetailData.school}
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
            <View style={{ marginHorizontal: 15 }}>
              <Typography fontSize={22}>{groupDetailData.aboutUs}</Typography>
              <Spacer space={5} />
              <Typography
                fontSize={17}
                color={Color.GRAY}
              >
                가입자 수 : {groupDetailData.memberCnt}명
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
              <Typography fontSize={15}>{groupDetailData.introduce}</Typography>
            </View>
          </ScrollView>
        </SafeAreaView>
      </View>
    )
}
