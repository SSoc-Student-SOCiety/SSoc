import { View, Text, TouchableOpacity } from 'react-native'
import { Typography } from '../../components/Basic/Typography'
import * as Color from '../../components/Colors/colors'
import { Icon } from '../../components/Icons/Icons'

const ContentDetailFooter = (props) => {
  const content = props.content
  return (
    <View style={{ height: '10%', borderTopWidth: 0.5 }}>
      <View style={{ flex: 1, flexDirection: 'row', alignItems: 'center', justifyContent: 'flex-end', paddingHorizontal: 20 }}>
        <TouchableOpacity>
          <View style={{ flexDirection: 'row', padding: 10, margin: 2, alignItems: 'center' }}>
            <Icon
              name="heart-outline"
              color={Color.RED}
              size={26}
            />
            <Text style={{ fontSize: 20 }}>{' ' + content.likes}</Text>
          </View>
        </TouchableOpacity>
        <TouchableOpacity>
          <View style={{ flexDirection: 'row', padding: 15, margin: 3, alignItems: 'center' }}>
            <Icon
              name="md-chatbubble-ellipses-outline"
              color={Color.BLUE}
              size={26}
            />
            <Text style={{ fontSize: 20 }}>{' ' + content.comments}</Text>
          </View>
        </TouchableOpacity>
      </View>
    </View>
  )
}

export default ContentDetailFooter
