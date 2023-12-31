import { useEffect, useState } from 'react'
import { View, Text, StyleSheet, Alert } from 'react-native'
import * as Color from '../components/Colors/colors'
import { Typography } from '../components/Basic/Typography'
import SettingBtn from './SettingButton'
import { Spacer } from '../components/Basic/Spacer'
import { useRecoilState } from 'recoil'
import { goMainPageState, UserInfoState } from '../util/RecoilUtil/Atoms'
import { getTokens, removeTokens } from '../util/TokenUtil'
import { getDeleteUserFetch } from '../util/FetchUtil'

const SettingDeleteUser = () => {
  const [isTokenRemove, setIsTokenRemove] = useState(false)

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  const [user, setUser] = useRecoilState(UserInfoState)
  const [goMainPage, setGoMainPage] = useRecoilState(goMainPageState)

  const onPressDel = () => {
    Alert.alert(
      '계정을 삭제하시겠습니까?',
      '삭제 시 되돌릴 수 없습니다.',
      [
        { text: '취소', onPress: () => {}, style: 'cancle' },
        {
          text: '삭제',
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

  const getDeleteUserData = async () => {
    try {
      const response = await getDeleteUserFetch(accessToken, refreshToken)
      const data = await response.json()
      console.log(data)
      if (data.dataHeader.successCode == 0) {
        removeTokens()
        setGoMainPage(false)
        setUser(null)
      } else {
        Alert.alert('회원탈퇴에 실패했습니다.', '알 수 없는 에러.')
      }
    } catch (e) {
      console.log(e)
    }
  }

  useEffect(() => {
    if (isTokenGet) {
      getDeleteUserData()
    }
  }, [isTokenGet])

  return (
    <View>
      <View style={{ marginTop: 20, marginLeft: 20 }}>
        <Text style={{ fontWeight: 'bold', fontSize: 20 }}>* 계정 삭제</Text>
      </View>
      <View style={styles.basicUserDataContainer}>
        <View style={{ marginTop: 5, marginLeft: 5 }}>
          <Typography
            fontSize={14}
            color={Color.GRAY}
          >
            계정 삭제 시 프로필 및{'\n'}사용자 정보가 모두 삭제됩니다.
          </Typography>
          <Spacer space={10} />
          <SettingBtn
            text="삭제하기"
            btnColor={Color.GRAY_BLUE}
            color={Color.RED}
            onPress={onPressDel}
          />
        </View>
      </View>
    </View>
  )
}
export default SettingDeleteUser

const styles = StyleSheet.create({
  basicUserDataContainer: { justifyContent: 'center', borderRadius: 6, margin: 20, padding: 8, borderColor: Color.LIGHT_GRAY, borderWidth: 2 },
})
