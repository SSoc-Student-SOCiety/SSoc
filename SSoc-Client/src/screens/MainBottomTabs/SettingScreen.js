import { View, Text, StyleSheet } from 'react-native'
import { SettingHeader } from '../../modules/SettingHeader'
import * as Color from '../../components/Colors/colors'
import SettingUserInfo from '../../modules/SettingUserInfo'
import SettingDeleteUser from '../../modules/SettingDeleteUser'
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view'
import SettingLogout from '../../modules/SettingLogout'

export const SettingScreen = () => {
  return (
    <View style={{ flex: 1, backgroundColor: Color.WHITE }}>
      <SettingHeader title={'Setting'} />
      <KeyboardAwareScrollView>
        <View style={{ flexDirection: 'column' }}>
          <SettingUserInfo />
          <SettingLogout />
          <SettingDeleteUser />
        </View>
      </KeyboardAwareScrollView>
    </View>
  )
}
