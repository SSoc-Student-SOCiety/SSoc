import { Image, ImageBackground, ImageBackgroundBase } from 'react-native/'
import * as Color from '../components/Colors/colors'
export const SettingProfileImage = (props) => {
  return (
    <ImageBackground
      source={{
        uri: props.url,
      }}
      style={{
        width: props.size,
        height: props.size,
      }}
      imageStyle={{
        borderRadius: 100,
        borderColor: Color.GRAY,
        borderWidth: 0.5,
      }}
    >
      {props.children}
    </ImageBackground>
  )
}
