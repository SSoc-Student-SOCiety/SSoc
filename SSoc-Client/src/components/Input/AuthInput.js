import { View, Text } from 'react-native'
import { Spacer } from '../../components/Basic/Spacer'
import { Typography } from '../../components/Basic/Typography'
import * as Color from '../../components/Colors/colors'
import { SingleLineInput } from '../../components/Input/SingleLineInput'

const AuthInput = (props) => {
  return (
    <View>
      <Typography
        fontSize={props.fontSize}
        color={Color.DARK_BLUE}
      >
        <Text style={{ fontWeight: 'bold' }}>{props.title}</Text>
      </Typography>
      <Spacer space={4} />
      <View style={{ backgroundColor: props.bgColor ? props.bgColor : Color.LIGHT_GRAY, borderRadius: 10 }}>
        <SingleLineInput
          style={{ color: Color.BLACK }}
          value={props.value}
          placeholder={props.placeholder}
          fontSize={20}
          editable={props.editable}
          secureTextEntry={props.secureTextEntry}
          onChangeText={props.onChangeText}
        />
      </View>
    </View>
  )
}
export default AuthInput
