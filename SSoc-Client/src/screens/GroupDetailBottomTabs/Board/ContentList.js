import { useNavigation } from '@react-navigation/native'
import { TouchableOpacity, View, Text, FlatList, ScrollView } from 'react-native'
import { Divider } from '../../../components/Basic/Divider'
import { Typography } from '../../../components/Basic/Typography'
import * as Color from '../../../components/Colors/colors'
import { ContentCard } from './ContentCard'

const ContentList = (props) => {
  const navigation = useNavigation()
  //   console.log(props)
  const tempData = {
    dataHeader: {
      successCode: 0,
      resultCode: null,
      resultMessage: null,
    },
    dataBody: [
      {
        postId: '1',
        boardId: '1',
        userEmail: 'shinhan@shinhan.ac.kr',
        userNick: '김신한',
        userImage: 'https://picsum.photos/600',
        title: '8월 17일 내역 질문',
        content: '금액이 뭔가 좀 이상한 거 같은데 이 부분 설명해주세요',
        anonymous_flag: '1',
        createDate: '2023-09-09',
        modifyDate: '2023-09-09',
        contentImage: 'url',
        likes: '3',
        comments: '2',
        views: '5',
      },
      {
        postId: '2',
        boardId: '1',
        userEmail: 'test@shinhan.ac.kr',
        userNick: '김싸피',
        userImage: 'https://picsum.photos/600',
        title: '8월 18일 내역 질문',
        content: '금액이 뭔가 좀 이상한 거 같은데 이 부분 설명해주세요',
        anonymous_flag: '0',
        createDate: '2023-09-09',
        modifyDate: '2023-09-09',
        contentImage: 'url',
        likes: '3',
        comments: '2',
        views: '5',
      },
      {
        postId: '3',
        boardId: '1',
        userEmail: 'test@shinhan.ac.kr',
        userNick: '김땡땡',
        userImage: 'https://picsum.photos/600',
        title: '8월 19일 내역 질문',
        content: '금액이 뭔가 좀 이상한 거 같은데 이 부분 설명해주세요',
        anonymous_flag: '1',
        createDate: '2023-09-09',
        modifyDate: '2023-09-09',
        contentImage: 'url',
        likes: '3',
        comments: '2',
        views: '5',
      },
      {
        postId: '4',
        boardId: '1',
        userEmail: 'test@shinhan.ac.kr',
        userNick: '김아현',
        userImage: 'https://picsum.photos/600',
        title: '8월 19일 내역 질문',
        content: '금액이 뭔가 좀 이상한 거 같은데 이 부분 설명해주세요',
        anonymous_flag: '0',
        createDate: '2023-09-09',
        modifyDate: '2023-09-09',
        contentImage: 'url',
        likes: '3',
        comments: '2',
        views: '5',
      },
    ],
  }

  return (
    <View>
      {tempData.dataBody.map((item) => (
        <View>
          <ContentCard content={item} />
        </View>
      ))}
    </View>
  )
}

export default ContentList
