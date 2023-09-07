import { StyleSheet, View } from 'react-native'
import { Button } from '../components/Basic/Button'
import * as Color from '../components/Colors/colors'
import { Icon } from '../components/Icons/Icons'
import * as ImagePicker from 'expo-image-picker'
import { useState } from 'react'

const SettingImgEditButton = (props) => {
  const imageUrl = props.userData.imageUrl
  const [newImageUrl, setNewImageUrl] = useState('')
  const pressEditImage = async () => {
    console.log('editImage')
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
    console.log(newImageUrl)
  }
  return (
    <View style={styles.editImage}>
      <Button
        onPress={pressEditImage}
        paddingHorizontal={5}
        paddingVertical={5}
      >
        <Icon
          name="brush"
          size={16}
          color={Color.WHITE}
        />
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
