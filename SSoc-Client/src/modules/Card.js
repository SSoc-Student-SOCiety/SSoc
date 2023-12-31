import { Text, TouchableOpacity, View } from 'react-native'
import { Typography } from '../components/Basic/Typography'
import * as Color from '../components/Colors/colors'
import { ProfileImage } from './ProfileImage'
import { Spacer } from '../components/Basic/Spacer'
import { useNavigation } from '@react-navigation/native'
export const Card = (props) => {
  const navigation = useNavigation()

  const onPressDetail = () => {
    const { onPress, ...otherProps } = props
    navigation.navigate('GroupDetailScreen', otherProps)
  }

  return (
    <TouchableOpacity onPress={onPressDetail}>
      <View
        style={{
          height: 230,
          width: 150,
          borderRadius: 10,
          marginHorizontal: 5,
        }}
        backgroundColor={Color.BLUE}
      >
        <View style={{ marginHorizontal: 10, marginVertical: 10 }}>
          <ProfileImage
            size={50}
            url={props.thumbnail}
          />
          <Spacer space={10} />
          <Typography
            fontSize={16}
            color={Color.WHITE}
          >
            {props.school}
          </Typography>
          <Spacer space={5} />
          <Typography>
            <Text
              numberOfLines={1}
              style={{ fontSize: 14, color: Color.WHITE }}
            >
              {props.name}
            </Text>
          </Typography>
          <Spacer space={5} />
          <View style={{ height: '35%' }}>
            <Text
              numberOfLines={3}
              style={{ fontSize: 12, color: Color.LIGHT_GRAY }}
            >
              {props.aboutUs}
            </Text>
          </View>
          <Typography
            fontSize={13}
            color={Color.WHITE}
          >
            가입자 수: {props.memberCnt}명
          </Typography>
        </View>
      </View>
    </TouchableOpacity>
  )
}
