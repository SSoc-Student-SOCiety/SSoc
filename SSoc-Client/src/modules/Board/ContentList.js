import { useNavigation } from '@react-navigation/native'
import { useEffect, useState } from 'react'
import { TouchableOpacity, View, Text, FlatList, ScrollView, Alert } from 'react-native'
import { Colors } from 'react-native/Libraries/NewAppScreen'
import { useRecoilState } from 'recoil'
import { getContentListFetch } from '../../util/FetchUtil'
import { UserInfoState } from '../../util/RecoilUtil/Atoms'
import { getTokens } from '../../util/TokenUtil'
import { ContentCard } from './ContentCard'

const ContentList = (props) => {
  const navigation = useNavigation()
  const board = props.board
  const groupMemberRole = props.groupMemberRole
  const [user, setUser] = useRecoilState(UserInfoState)
  const [data, setData] = useState([])
  const [lastPostId, setLastPostId] = useState('')

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  const tempData = {
    dataHeader: {
      successCode: 0,
      resultCode: null,
      resultMessage: null,
    },
    dataBody: [
      {
        postId: 1,
        groupId: 1,
        title: '스리라차 소스',
        nickname: null,
        createdAt: '2023-09-13',
        content: '이 글은 1956년 런던에서 시작되었으며',
        commentCnt: 7,
        profileImg: null,
        userId: 1,
      },
      {
        postId: 2,
        groupId: 1,
        title: '스리라차 소스',
        nickname: null,
        createdAt: '2023-09-13',
        content: '이 글은 1956년 런던에서 시작되었으며',
        commentCnt: 7,
        profileImg: null,
        userId: 1,
      },
      {
        postId: 3,
        groupId: 1,
        title: '스리라차 소스',
        nickname: null,
        createdAt: '2023-09-13',
        content: '이 글은 1956년 런던에서 시작되었으며',
        commentCnt: 7,
        profileImg: null,
        userId: 1,
      },
      {
        postId: 4,
        groupId: 1,
        title: '스리라차 소스',
        nickname: null,
        createdAt: '2023-09-13',
        content: '이 글은 1956년 런던에서 시작되었으며',
        commentCnt: 7,
        profileImg: null,
        userId: 1,
      },
      {
        postId: 5,
        groupId: 1,
        title: '스리라차 소스',
        nickname: null,
        createdAt: '2023-09-13',
        content: '이 글은 1956년 런던에서 시작되었으며',
        commentCnt: 7,
        profileImg: null,
        userId: 1,
      },
    ],
  }

  const getContentListData = async () => {
    try {
      const response = await getContentListFetch(accessToken, refreshToken, board.groupId, '', board.category, lastPostId)
      const newData = await response.json()
      console.log(newData)
      return newData
    } catch (e) {
      console.log(e)
      return []
    }
  }

  const loadData = async () => {
    const newData = await getContentListData()
    // console.log('category : ', board.category, '   scroll: ', newData)
    if (newData.dataHeader.successCode == 0) {
      if (newData.dataBody.length > 0) {
        setLastPostId(newData.dataBody[newData.dataBody.length - 1].postId.toString())
        setData((prevData) => [...prevData, ...newData.dataBody])
      }
    } else {
      Alert.alert(newData.dataHeader.resultMessage, navigation.goBack())
    }
  }

  useEffect(() => {
    if (!isTokenGet) {
      getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
      loadData()
    }
  }, [])

  const handleEndReached = () => {
    loadData()
  }

  return (
    <View style={{ minHeight: '100%' }}>
      <FlatList
        contentContainerStyle={{ paddingBottom: 70 }}
        data={data}
        renderItem={({ item }) => (
          <View>
            <ContentCard content={item} />
          </View>
        )}
        onEndReached={handleEndReached}
        onEndReachedThreshold={0.1}
      />
    </View>
  )
}

export default ContentList
