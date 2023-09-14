import { useEffect, useState } from 'react'
import { View, Text, StyleSheet, Alert } from 'react-native'
import * as Color from '../components/Colors/colors'
import { Typography } from '../components/Basic/Typography'
import SettingBtn from './SettingButton'
import { Spacer } from '../components/Basic/Spacer'
import { useRecoilState } from 'recoil'
import { goMainPageState, UserInfoState } from '../util/RecoilUtil/Atoms'
import { removeTokens, getTokens } from '../util/TokenUtil'
import { getLogoutFetch } from '../util/FetchUtil'

const SettingLogout = () => {
  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)
  const [logoutData, setLogoutData] = useState(null)

  const [goMainPage, setGoMainPage] = useRecoilState(goMainPageState)
  const [userInfo, setUserInfo] = useRecoilState(UserInfoState)

  const onPressLogout = async () => {
    Alert.alert(
      '로그아웃',
      '정말 로그아웃 하시겠습니까?',
      [
        { text: '취소', onPress: () => {}, style: 'cancle' },
        {
          text: '로그아웃',
          onPress: () => {
            getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
          },
          style: 'destructive',
        },
      ],
      {
        cancelable: true,
      }
    )
  }

  const getLogoutData = async () => {
    try {
      const response = await getLogoutFetch(accessToken, refreshToken)
      const data = await response.json()
      if (data.dataHeader.successCode == 0) {
        removeTokens()
        setGoMainPage(false)
      } else {
        Alert.alert('로그아웃에 실패했습니다.', '알 수 없는 에러.')
      }
    } catch (e) {
      console.log(e)
    }
  }

  useEffect(() => {
    if (isTokenGet) {
      getLogoutData()
    }
  }, [isTokenGet])

  return (
    <View>
      <View style={{ marginTop: 20, marginLeft: 20 }}>
        <Text style={{ fontWeight: 'bold', fontSize: 20 }}>* 로그아웃</Text>
      </View>
      <View style={styles.basicUserDataContainer}>
        <View style={{ marginTop: 5, marginLeft: 5 }}>
          <Typography
            fontSize={14}
            color={Color.GRAY}
          >
            로그아웃 시 초기화면으로 돌아갑니다.
          </Typography>
          <Spacer space={10} />
          <SettingBtn
            text="로그아웃"
            btnColor={Color.GRAY_BLUE}
            color={Color.BLACK}
            onPress={onPressLogout}
          />
        </View>
      </View>
    </View>
  )
}
export default SettingLogout

const styles = StyleSheet.create({
  basicUserDataContainer: { justifyContent: 'center', borderRadius: 6, margin: 20, padding: 8, borderColor: Color.LIGHT_GRAY, borderWidth: 2 },
})
