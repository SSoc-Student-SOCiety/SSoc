import { ScrollView, Text, View } from 'react-native'
import { StackHeader } from '../../../modules/StackHeader'
import * as Color from '../../../components/Colors/colors'
import ContentDetailTitle from '../../../modules/Board/ContentDetailTitle'
import ContentDetailPost from '../../../modules/Board/ContentDetailPost'
import ContentDetailFooter from '../../../modules/Board/ContentDetailFooter'

const ContentDetailScreen = (props) => {
  console.log(props.route.params); 
  const content = props.route.params.content
  return (
    <View style={{ flex: 1, backgroundColor: Color.WHITE }}>
      <StackHeader title="게시글"></StackHeader>
      <ScrollView>
        <ContentDetailTitle content={content} />
        <ContentDetailPost content={content} />
      </ScrollView>
      <ContentDetailFooter content={content} />
    </View>
  )
}

export default ContentDetailScreen
