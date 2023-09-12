import { Dimensions, Text, View } from 'react-native'
import * as Color from '../../components/Colors/colors'

const ContentDetailPost = (props) => {
  const content = props.content
  const windowHeight = Dimensions.get('window').height
  return (
    <View style={{ minHeight: windowHeight * 0.6, flexDirection: 'column', marginVertical: 5, marginHorizontal: 10, padding: 15, borderRadius: 5, borderWidth: 0.2, borderBottomWidth: 2, borderColor: Color.GRAY }}>
      <Text style={{ fontSize: 18 }}>{content.content}</Text>
    </View>
  )
}

export default ContentDetailPost
