import { View, Text, StyleSheet } from 'react-native'
import { Spacer } from '../../components/Basic/Spacer'
import { Typography } from '../../components/Basic/Typography'
import { SettingHeader } from '../../modules/SettingHeader'
import * as Color from '../../components/Colors/colors'
import { Icon } from '../../components/Icons/Icons'
import { SettingProfileImage } from '../../modules/SettingProfileImage'
import SettingImgEditButton from '../../modules/ImgEditButton'
import { useState } from 'react'
import SettingBtn from '../../modules/SettingButton'
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view'
import EditInputShow from '../../modules/EditInputShow'
import { Logo } from '../../modules/Logo'

export const SettingScreen = () => {
  const [isEditMode, setIsEditMode] = useState(false)
  const isEditPress = () => {
    setIsEditMode(true)
  }
  const editCanclePress = () => {
    setIsEditMode(false)
  }

  return (
    <View style={{ flex: 1, backgroundColor: Color.WHITE }}>
      <SettingHeader title={'Setting'} />
      <KeyboardAwareScrollView>
        <View style={styles.basicUserDataContainer}>
          {/* 유저 이미지 및 반갑습니다 View */}
          <View style={styles.basicUserInfo}>
            <SettingProfileImage
              size={110}
              url={userData.imageUrl}
            >
              {/* 이미지 수정 아이콘을 오른쪽 밑에 두기 위한 temp flex */}
              <View style={{ flex: 1 }}></View>
              {isEditMode ? <SettingImgEditButton userData={userData} /> : null}
            </SettingProfileImage>
            <Spacer space={18} />
            <Typography>
              <Text style={{ fontSize: 20, fontWeight: 'bold' }}>{userData.nick}</Text>
              <Text style={{ fontSize: 18, color: Color.DARK_BLUE }}> 님 반갑습니다!</Text>
            </Typography>
            <Spacer space={6} />
            <Typography fontSize={15}>
              <Text>
                {userData.email}
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
              userData={userData}
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
      </KeyboardAwareScrollView>
    </View>
  )
}

const userData = {
  imageUrl: 'https://images.pexels.com/photos/14268955/pexels-photo-14268955.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2',
  name: '김신한',
  email: 'shinhan@shinhan.ac.kr',
  nick: '김싸피',
  password: 'qwer1234',
}

const styles = StyleSheet.create({
  editImage: {
    flex: 1,
    backgroundColor: Color.DARK_BLUE,
    borderRadius: 100,
    width: 28,
    height: 30,
    alignSelf: 'flex-end',
  },
  basicUserDataContainer: { justifyContent: 'center', borderRadius: 6, margin: 20, padding: 8, borderColor: Color.LIGHT_BLUE, borderWidth: 2 },
  basicUserInfo: { alignItems: 'center', margin: 8 },
})
