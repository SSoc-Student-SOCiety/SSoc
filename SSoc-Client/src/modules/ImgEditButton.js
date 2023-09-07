import { StyleSheet, TouchableOpacity, View } from 'react-native'
import { Button } from '../components/Basic/Button'
import * as Color from '../components/Colors/colors'
import { Icon } from '../components/Icons/Icons'
import * as ImagePicker from 'expo-image-picker'
import { useState } from 'react'

const SettingImgEditButton = (props) => {
  const imageUrl = props.userInfo.userImageUrl
  const [newImageUrl, setNewImageUrl] = useState('')
  // 권한 요청을 위한 hooks
  const [status, requestPermission] = ImagePicker.useMediaLibraryPermissions()

  const pressEditImage = async () => {
    // 이미지 접근 권한 관련 요청
    if (!status?.granted) {
      const permission = await requestPermission()
      if (!permission.granted) {
        return null
      }
    }

    // 이미지 가져오기 요청
    const result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.Images,
      allowsEditing: false,
      quality: 1,
      aspect: [1, 1],
    })
    if (result.canceled) {
      return null
    }
    setNewImageUrl(result.assets.uri)
    // TO-DO
    // Fetch 쏴서 이미지 업로드하기
    console.log(result.assets)
  }
  return (
    <View style={styles.editImage}>
      <Button
        paddingHorizontal={5}
        paddingVertical={5}
      >
        <TouchableOpacity onPress={pressEditImage}>
          <Icon
            name="brush"
            size={16}
            color={Color.WHITE}
          />
        </TouchableOpacity>
      </Button>
    </View>
  )
}

const styles = StyleSheet.create({
  editImage: {
    backgroundColor: Color.BLUE,
    borderRadius: 100,
    width: '25%',
    height: '25%',
    alignSelf: 'flex-end',
  },
})

export default SettingImgEditButton
