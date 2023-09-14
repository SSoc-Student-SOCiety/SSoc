import { ScrollView, Text, TouchableOpacity, View } from 'react-native'
import * as Color from '../../../components/Colors/colors'
import ContentDetailTitle from '../../../modules/Board/ContentDetailTitle'
import ContentDetailPost from '../../../modules/Board/ContentDetailPost'
import ContentDetailFooter from '../../../modules/Board/ContentDetailFooter'
import { DetailContentStackHeader } from '../../../modules/Board/DetailContentStackHeader'

const ContentDetailScreen = (props) => {
  const content = props.route.params.content
  const reload = props.route.params.reload
  const setReload = props.route.params.setReload
  return (
    <View style={{ flex: 1, backgroundColor: Color.WHITE }}>
      <DetailContentStackHeader
        title="게시글 상세"
        reload={reload}
        setReload={setReload}
      ></DetailContentStackHeader>
      <ScrollView>
        <ContentDetailTitle
          content={content}
          reload={reload}
          setReload={setReload}
        />
        <ContentDetailPost content={content} />
      </ScrollView>
      <ContentDetailFooter content={content} />
    </View>
  )
}

export default ContentDetailScreen
