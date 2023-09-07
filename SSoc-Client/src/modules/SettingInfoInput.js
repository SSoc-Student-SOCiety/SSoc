import { View } from 'react-native'
import { Typography } from '../components/Basic/Typography'
import { SingleLineInput } from '../components/Input/SingleLineInput'
import * as Color from '../components/Colors/colors'
import { Spacer } from '../components/Basic/Spacer'
import SettingBtn from './SettingButton'
import { Icon } from '../components/Icons/Icons'

const SettingInfoInput = (props) => {
  return (
    <View style={{ marginLeft: 4 }}>
      <Typography fontSize={16}>
        {props.editContent}
        <Icon />
      </Typography>
      <View style={{ flexDirection: 'row', alignItems: 'center', justifyContent: 'center' }}>
        <View style={{ backgroundColor: Color.LIGHT_GRAY, borderRadius: 4, width: '70%' }}>
          <SingleLineInput
            placeholder={props.placeholder}
            onChangeText={props.onChangeText}
            secureTextEntry={props.secureTextEntry}
          />
        </View>
        <View style={{ width: '30%' }}>
          <SettingBtn
            text="저장"
            onPress={props.onPress}
            btnColor={Color.BLUE}
          />
        </View>
      </View>
    </View>
  )
}
export default SettingInfoInput
