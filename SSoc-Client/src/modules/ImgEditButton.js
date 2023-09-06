import { StyleSheet, View } from 'react-native'
import { Button } from '../components/Basic/Button'
import * as Color from '../components/Colors/colors'
import { Icon } from '../components/Icons/Icons'
import * as ImagePicker from 'expo-image-picker'

const SettingImgEditButton = (props) => {
  const imageUrl = props.userData.imageUrl
  const pressEditImage = () => {
    console.log('editImage')
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
