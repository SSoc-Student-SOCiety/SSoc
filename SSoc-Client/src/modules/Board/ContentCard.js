import { View, Text, TouchableOpacity } from 'react-native'
import { Divider } from '../../components/Basic/Divider'
import { ProfileImage } from '../../modules/ProfileImage'
import * as Color from '../../components/Colors/colors'
import { useNavigation } from '@react-navigation/native'

export const ContentCard = (props) => {
  const content = props.content
  const navigation = useNavigation()

  return (
    <View style={{ margin: 8, flexDirection: 'column', borderRadius: 10, borderWidth: 0.2, borderBottomWidth: 2, borderColor: Color.GRAY }}>
      <TouchableOpacity
        onPress={() => {
          navigation.navigate('ContentDetailScreen', { content: content, reload: props.reload, setReload: props.setReload })
        }}
      >
        <View style={{ flex: 1, flexDirection: 'row', marginBottom: 8, marginTop: 8, alignItems: 'center', marginHorizontal: 25 }}>
          <ProfileImage
            size={40}
            url={content.profileImg}
          />
          <View style={{ marginLeft: 6, flexDirection: 'column' }}>
            <Text style={{ paddingBottom: 4, fontSize: 18, fontWeight: 'bold' }}>{content.title}</Text>
            <Text style={{ paddingBottom: 3, color: Color.GRAY }}>{content.nickname}</Text>
            <Text style={{ paddingBottom: 3, color: Color.GRAY }}>{content.createdAt}</Text>
          </View>
        </View>

        <View style={{ flex: 1, flexDirection: 'row', marginBottom: 8, marginTop: 4, alignItems: 'center', marginHorizontal: 32, height: 60 }}>
          <Text>{content.content}</Text>
        </View>

        {/* <View style={{ flex: 1, flexDirection: 'row', alignItems: 'center', marginHorizontal: 30, justifyContent: 'space-evenly', marginVertical: 15 }}>
          <View style={{ flexGrow: 1, alignItems: 'center' }}>
            <Text style={{ color: Color.GRAY }}>좋아요 {content.likes}</Text>
          </View>
          <View style={{ flexGrow: 1, alignItems: 'center' }}>
            <Text style={{ color: Color.GRAY }}>댓글 {content.comments}</Text>
          </View>
          <View style={{ flexGrow: 1, alignItems: 'center' }}>
            <Text style={{ color: Color.GRAY }}>조회수 {content.views}</Text>
          </View>
        </View> */}
      </TouchableOpacity>
    </View>
  )
}
