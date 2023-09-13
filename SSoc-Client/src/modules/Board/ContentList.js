import { useEffect, useState } from 'react'
import { TouchableOpacity, View, Text, FlatList, ScrollView, Alert } from 'react-native'
import { useRecoilState } from 'recoil'
import { getContentListFetch } from '../../util/FetchUtil'
import { UserInfoState } from '../../util/RecoilUtil/Atoms'
import { getTokens } from '../../util/TokenUtil'
import { ContentCard } from './ContentCard'

const ContentList = (props) => {
  const board = props.board
  const groupMemberRole = props.groupMemberRole
  const [user, setUser] = useRecoilState(UserInfoState)
  const [data, setData] = useState([])
  const [lastPostId, setLastPostId] = useState('')

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  const getContentListData = async () => {
    try {
      const response = await getContentListFetch(accessToken, refreshToken, board.groupId, '', board.category, lastPostId)
      const newData = await response.json()
      return newData
    } catch (e) {
      console.log(e)
      return []
    }
  }

  const loadData = async () => {
    const newData = await getContentListData()
    if (newData.dataHeader.successCode == 0) {
      if (newData.dataBody.length > 0) {
        setLastPostId(newData.dataBody[newData.dataBody.length - 1].postId.toString())
        setData((prevData) => [...prevData, ...newData.dataBody])
      }
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
