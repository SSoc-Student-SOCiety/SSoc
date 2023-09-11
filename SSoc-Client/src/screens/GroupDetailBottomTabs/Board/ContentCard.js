import { View, Text, TouchableOpacity } from 'react-native'
import { Divider } from '../../../components/Basic/Divider'
import { ProfileImage } from '../../../modules/ProfileImage'
import * as Color from '../../../components/Colors/colors'

export const ContentCard = (props) => {
  const content = props.content
  return (
    <View style={{ padding: 10, flexDirection: 'column' }}>
      <TouchableOpacity>
        <Divider />
        <View style={{ flex: 1, flexDirection: 'row', marginBottom: 10, marginTop: 8, alignItems: 'center', marginHorizontal: 25 }}>
          <ProfileImage
            size={40}
            url={content.userImage}
          />
          <View style={{ marginLeft: 6, flexDirection: 'column' }}>
            <Text style={{ paddingBottom: 4, fontSize: 18, fontWeight: 'bold' }}>{content.title}</Text>
            <Text style={{ paddingBottom: 3, color: Color.GRAY }}>{content.anonymous_flag == 1 ? '익명' : content.userNick}</Text>
            <Text style={{ paddingBottom: 3, color: Color.GRAY }}>
              {content.createDate}
              {content.modifyDate != '' ? ' (수정일: ' + content.modifyDate + ')' : null}
            </Text>
          </View>
        </View>
        <View style={{ flex: 1, flexDirection: 'row', marginBottom: 10, marginTop: 8, alignItems: 'center', marginHorizontal: 32 }}>
          <Text style={{ color: Color.GRAY }}>{content.content}</Text>
        </View>
        <View style={{ flex: 1, flexDirection: 'row', marginBottom: 10, marginTop: 8, alignItems: 'center', marginHorizontal: 32, justifyContent: 'space-evenly' }}>
          <View style={{ borderColor: Color.BLUE, borderRadius: 10, padding: 10, borderWidth: 1 }}>
            <Text style={{ color: Color.DARK_BLUE }}>좋아요 {content.likes}</Text>
          </View>
          <View style={{ borderColor: Color.BLUE, borderRadius: 10, padding: 10, borderWidth: 1 }}>
            <Text style={{ color: Color.DARK_BLUE }}>댓글 {content.comments}</Text>
          </View>
          <View style={{ borderColor: Color.BLUE, borderRadius: 10, padding: 10, borderWidth: 1 }}>
            <Text style={{ color: Color.DARK_BLUE }}>조회수 {content.views}</Text>
          </View>
        </View>
      </TouchableOpacity>
    </View>
  )
}
