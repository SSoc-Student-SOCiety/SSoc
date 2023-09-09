import { useEffect, useState } from 'react'
import { View, Text, StyleSheet, Alert } from 'react-native'
import * as Color from '../components/Colors/colors'
import { Typography } from '../components/Basic/Typography'
import SettingBtn from './SettingButton'
import { Spacer } from '../components/Basic/Spacer'
import { useRecoilState } from 'recoil'
import { goMainPageState } from '../util/RecoilUtil/Atoms'
import { removeTokens } from '../util/TokenUtil'

const SettingLogout = () => {
  const [isTokenRemove, setIsTokenRemove] = useState(false)
  const [goMainPage, setGoMainPage] = useRecoilState(goMainPageState)

  const onPressLogout = async () => {
    Alert.alert(
      '로그아웃',
      '정말 로그아웃 하시겠습니까?',
      [
        { text: '취소', onPress: () => {}, style: 'cancle' },
        {
          text: '로그아웃',
          onPress: () => {
            removeTokens()
            setIsTokenRemove(true)
          },
          style: 'destructive',
        },
      ],
      {
        cancelable: true,
      }
    )
  }

  useEffect(() => {
    if (isTokenRemove == true) {
      setGoMainPage(false)
    }
  }, [isTokenRemove])

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
