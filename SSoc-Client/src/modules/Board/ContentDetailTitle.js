import { Text, View } from 'react-native'
import { ProfileImage } from '../ProfileImage'
import * as Color from '../../components/Colors/colors'

const ContentDetailTitle = (props) => {
  const content = props.content
  return (
    <View style={{ flexDirection: 'column', marginTop: 5, marginHorizontal: 10, padding: 10, borderRadius: 5, borderWidth: 0.2, borderBottomWidth: 2, borderColor: Color.BLUE }}>
      <View>
        <Text style={{ fontSize: 22, fontWeight: 'bold' }}>{content.title}</Text>
      </View>
      <View style={{ flexDirection: 'row', alignItems: 'center', padding: 5 }}>
        <View>
          <ProfileImage
            size={50}
            url={content.userImage}
          />
        </View>
        <View style={{ flex: 1, marginLeft: 6, flexDirection: 'column', justifyContent: 'space-evenly' }}>
          <Text style={{ color: Color.GRAY, fontWeight: 'bold', marginVertical: 4, fontSize: 16, fontWeight: 'bold' }}>{content.anonymous_flag == 1 ? '익명' : content.userNick}</Text>
          <View style={{ flexDirection: 'row', marginVertical: 4 }}>
            <Text style={{ color: Color.GRAY }}>{content.createDate + ' '}</Text>
            <Text style={{ color: Color.GRAY }}>{content.modifyDate != '' ? '(' + content.modifyDate + ')  ' : null}</Text>
            <Text style={{ color: Color.GRAY }}>조회: {content.views}</Text>
          </View>
        </View>
      </View>
    </View>
  )
}

export default ContentDetailTitle
