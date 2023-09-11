import { View } from 'react-native'
import * as Color from '../../components/Colors/colors'
import { SingleLineInput } from '../../components/Input/SingleLineInput'
import SettingBtn from '../SettingButton'

const BoardSearch = () => {
  return (
    <View style={{ marginVertical: 5, flexDirection: 'row', alignItems: 'center', justifyContent: 'center' }}>
      <View style={{ backgroundColor: Color.GRAY_BLUE, width: '70%', borderRadius: 10 }}>
        <SingleLineInput
          color={Color.GRAY}
          placeholder=" 🔎 Search"
        />
      </View>
      <SettingBtn
        text="검색"
        onPress={() => {}}
        btnColor={Color.BLUE}
      />
    </View>
  )
}

export default BoardSearch
