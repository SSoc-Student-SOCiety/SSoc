import { StyleSheet, View } from 'react-native'
import { Button } from '../components/Basic/Button'
import { Typography } from '../components/Basic/Typography'
import * as Color from '../components/Colors/colors'

const SettingBtn = (props) => {
  return (
    <View style={styles.editBtn}>
      <Button onPress={props.onPress}>
        <View
          backgroundColor={props.btnColor}
          borderRadius={10}
          style={styles.editBtnView}
        >
          <Typography
            fontSize={16}
            color={Color.WHITE}
          >
            {props.text}
          </Typography>
        </View>
      </Button>
    </View>
  )
}

const styles = StyleSheet.create({
  editBtn: { alignItems: 'flex-end', margin: 8 },
  editBtnView: {
    alignSelf: 'stretch',
    height: 50,
    width: 75,
    alignItems: 'center',
    justifyContent: 'center',
    flexDirection: 'row',
  },
})

export default SettingBtn
