import { useNavigation } from '@react-navigation/native'
import { TouchableOpacity, View, Text, FlatList, ScrollView } from 'react-native'
import { ContentCard } from './ContentCard'

const ContentList = (props) => {
  const navigation = useNavigation()
  //   console.log(props)
  // TO-DO
  // props에서 board 정보 가져와서 그거에 맞는 content 가져오기
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
        content:
          '금액이 뭔가 좀 이상한 거 같은데 이 부분 설명해주세요.\n\n만천하의 가슴이 속에서 능히 속에 착목한는 이상은 듣기만 칼이다. 과실이 관현악이며, 몸이 얼마나 풍부하게 그들은 이것을 것이다.보라, 군영과 말이다. 그들은 무한한 얼마나 현저하게 때에, 장식하는 내는 보라. 시들어 불어 없으면, 작고 무엇을 돋고, 대중을 운다. 역사를 못할 청춘의 피가 인류의 대한 인생의 가는 노래하며 것이다. 하는 너의 뜨고, 무엇을 얼마나 있다.\n\n열락의 너의 가치를 천지는 이는 인류의 피어나기 뿐이다. 속에서 웅대한 두손을 어디 속잎나고, 같지 것이다. 얼마나 되려니와, 그들은 스며들어 반짝이는 이것이다. 살 실현에 할지니, 수 사라지지 스며들어 새 아름다우냐? 얼마나 넣는 하였으며, 사랑의 전인 무엇을 것이다.금액이 뭔가 좀 이상한 거 같은데 이 부분 설명해주세요.\n\n만천하의 가슴이 속에서 능히 속에 착목한는 이상은 듣기만 칼이다. 과실이 관현악이며, 몸이 얼마나 풍부하게 그들은 이것을 것이다.보라, 군영과 말이다. 그들은 무한한 얼마나 현저하게 때에, 장식하는 내는 보라. 시들어 불어 없으면, 작고 무엇을 돋고, 대중을 운다. 역사를 못할 청춘의 피가 인류의 대한 인생의 가는 노래하며 것이다. 하는 너의 뜨고, 무엇을 얼마나 있다.\n\n열락의 너의 가치를 천지는 이는 인류의 피어나기 뿐이다. 속에서 웅대한 두손을 어디 속잎나고, 같지 것이다. 얼마나 되려니와, 그들은 스며들어 반짝이는 이것이다. 살 실현에 할지니, 수 사라지지 스며들어 새 아름다우냐? 얼마나 넣는 하였으며, 사랑의 전인 무엇을 것이다.금액이 뭔가 좀 이상한 거 같은데 이 부분 설명해주세요.\n\n만천하의 가슴이 속에서 능히 속에 착목한는 이상은 듣기만 칼이다. 과실이 관현악이며, 몸이 얼마나 풍부하게 그들은 이것을 것이다.보라, 군영과 말이다. 그들은 무한한 얼마나 현저하게 때에, 장식하는 내는 보라. 시들어 불어 없으면, 작고 무엇을 돋고, 대중을 운다. 역사를 못할 청춘의 피가 인류의 대한 인생의 가는 노래하며 것이다. 하는 너의 뜨고, 무엇을 얼마나 있다.\n\n열락의 너의 가치를 천지는 이는 인류의 피어나기 뿐이다. 속에서 웅대한 두손을 어디 속잎나고, 같지 것이다. 얼마나 되려니와, 그들은 스며들어 반짝이는 이것이다. 살 실현에 할지니, 수 사라지지 스며들어 새 아름다우냐? 얼마나 넣는 하였으며, 사랑의 전인 무엇을 것이다.금액이 뭔가 좀 이상한 거 같은데 이 부분 설명해주세요.\n\n만천하의 가슴이 속에서 능히 속에 착목한는 이상은 듣기만 칼이다. 과실이 관현악이며, 몸이 얼마나 풍부하게 그들은 이것을 것이다.보라, 군영과 말이다. 그들은 무한한 얼마나 현저하게 때에, 장식하는 내는 보라. 시들어 불어 없으면, 작고 무엇을 돋고, 대중을 운다. 역사를 못할 청춘의 피가 인류의 대한 인생의 가는 노래하며 것이다. 하는 너의 뜨고, 무엇을 얼마나 있다.\n\n열락의 너의 가치를 천지는 이는 인류의 피어나기 뿐이다. 속에서 웅대한 두손을 어디 속잎나고, 같지 것이다. 얼마나 되려니와, 그들은 스며들어 반짝이는 이것이다. 살 실현에 할지니, 수 사라지지 스며들어 새 아름다우냐? 얼마나 넣는 하였으며, 사랑의 전인 무엇을 것이다.',
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
        userNick: '김싸피피',
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
