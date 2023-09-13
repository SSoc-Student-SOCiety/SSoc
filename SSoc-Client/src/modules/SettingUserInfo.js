import { View, Text, StyleSheet } from 'react-native'
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view'
import { useState } from 'react'
import { Spacer } from '../components/Basic/Spacer'
import { Typography } from '../components/Basic/Typography'
import * as Color from '../components/Colors/colors'
import { Icon } from '../components/Icons/Icons'
import { SettingProfileImage } from '../modules/SettingProfileImage'
import SettingImgEditButton from '../modules/ImgEditButton'
import SettingBtn from '../modules/SettingButton'
import EditInputShow from '../modules/EditInputShow'
import { useRecoilValue } from 'recoil'
import { UserInfoState } from '../util/RecoilUtil/Atoms'

const SettingUserInfo = () => {
  const userInfo = useRecoilValue(UserInfoState)
  const [isEditMode, setIsEditMode] = useState(false)
  const isEditPress = () => {
    setIsEditMode(true)
  }
  const editCanclePress = () => {
    setIsEditMode(false)
  }

  return (
    <View>
      <View style={{ marginTop: 20, marginLeft: 20 }}>
        <Text style={{ fontWeight: 'bold', fontSize: 20 }}>* 기본정보</Text>
      </View>
      <View style={styles.basicUserDataContainer}>
        {/* 유저 이미지 및 반갑습니다 View */}
        <View style={styles.basicUserInfo}>
          <SettingProfileImage
            size={110}
            url={userInfo.userImageUrl}
          >
            {/* 이미지 수정 아이콘을 오른쪽 밑에 두기 위한 temp flex */}
            <View style={{ flex: 1 }}></View>
            {isEditMode ? <SettingImgEditButton userInfo={userInfo} /> : null}
          </SettingProfileImage>
          <Spacer space={18} />
          <Typography>
            <Text style={{ fontSize: 20, fontWeight: 'bold' }}>{userInfo.userName}</Text>
            <Text style={{ fontSize: 18, color: Color.DARK_BLUE }}> 님 반갑습니다!</Text>
          </Typography>
          <Spacer space={6} />
          <Typography fontSize={15}>
            <Text>
              {userInfo.userEmail}
              {'  '}
            </Text>
            <Icon
              name="checkmark-circle"
              size={15}
              color={Color.BLUE}
            />
          </Typography>
        </View>

        {isEditMode ? (
          <EditInputShow
            userInfo={userInfo}
            editCanclePress={editCanclePress}
          />
        ) : (
          <SettingBtn
            text="수정"
            onPress={isEditPress}
            btnColor={Color.DARK_BLUE}
          />
        )}
      </View>
    </View>
  )
}

export default SettingUserInfo

const styles = StyleSheet.create({
  editImage: {
    flex: 1,
    backgroundColor: Color.DARK_BLUE,
    borderRadius: 100,
    width: 28,
    height: 30,
    alignSelf: 'flex-end',
  },
  basicUserDataContainer: { justifyContent: 'center', borderRadius: 6, margin: 20, padding: 8, borderColor: Color.LIGHT_GRAY, borderWidth: 2 },
  basicUserInfo: { alignItems: 'center', margin: 8 },
})
