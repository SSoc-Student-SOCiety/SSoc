import { useEffect, useState } from 'react'
import { TouchableOpacity, View, Text, FlatList, ScrollView, Alert } from 'react-native'
import { useRecoilState } from 'recoil'
import { getContentListFetch } from '../../util/FetchUtil'
import { getTokens } from '../../util/TokenUtil'
import { ContentCard } from './ContentCard'

const ContentList = (props) => {
  const board = props.board
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
      } else {
        if (data.length > 10) Alert.alert('마지막 페이지입니다.')
      }
    }
  }

  useEffect(() => {
    if (!isTokenGet) {
      getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
    }
  }, [isTokenGet])

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
            <ContentCard
              content={item}
              setReload={props.setReload}
              reload={props.reload}
            />
          </View>
        )}
        onEndReached={handleEndReached}
        onEndReachedThreshold={0.1}
      />
    </View>
  )
}

export default ContentList
