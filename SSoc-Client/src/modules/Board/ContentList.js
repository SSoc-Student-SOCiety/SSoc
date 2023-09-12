import { useEffect, useState } from 'react'
import { TouchableOpacity, View, Text, FlatList, ScrollView } from 'react-native'
import { Colors } from 'react-native/Libraries/NewAppScreen'
import { useRecoilState } from 'recoil'
import { getContentListFetch } from '../../util/FetchUtil'
import { UserInfoState } from '../../util/RecoilUtil/Atoms'
import { ContentCard } from './ContentCard'

const ContentList = (props) => {
  const board = props.board
  const groupMemberRole = props.groupMemberRole
  const [user, setUser] = useRecoilState(UserInfoState)
  const [data, setData] = useState([])
  const [lastPostId, setLastPostId] = useState('')

  const getContentListData = async () => {
    try {
      const response = await getContentListFetch(board.groupId, '', board.category, lastPostId)
      // console.log(response)
      // const newData = await response.json()
      // console.log(tempData)
      // return newData
    } catch (e) {
      console.log(e)
      return []
    }
  }

  const loadData = async () => {
    const newData = await getContentListData()
    if (newData.length > 0) {
      setLastPostId(newData[newData.length - 1].postId.toString())
      setData((prevData) => [...prevData, ...newData])
    }
  }

  useEffect(() => {
    loadData()
  }, [])

  const handleEndReached = () => {
    loadData()
  }

  return (
    <View>
      <FlatList
        contentContainerStyle={{ paddingBottom: 70 }}
        data={data}
        renderItem={({ item }) => (
          <View>
            <ContentCard content={item} />
          </View>
        )}
        keyExtractor={(item) => item.postId.toString()}
        onEndReached={handleEndReached}
        onEndReachedThreshold={0.2}
      />
    </View>
  )
}

export default ContentList

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
      title: '굴 제목',
      nickname: '동근',
      createdAt: '2023-09-12',
      content: '이 글은 1956년 런던에서 시작되었으며',
    },
    {
      postId: 2,
      groupId: 1,
      title: '굴 제목',
      nickname: '동근',
      createdAt: '2023-09-12',
      content: '이 글은 1956년 런던에서 시작되었으며',
    },
    {
      postId: 3,
      groupId: 1,
      title: '굴 제목',
      nickname: '동근',
      createdAt: '2023-09-12',
      content: '이 글은 1956년 런던에서 시작되었으며',
    },
  ],
}
