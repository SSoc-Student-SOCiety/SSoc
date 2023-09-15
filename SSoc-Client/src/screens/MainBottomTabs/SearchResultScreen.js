import { Typography } from '../../components/Basic/Typography'
import { ScrollView, View, StyleSheet, FlatList } from 'react-native'
import { MainHeader } from '../../modules/MainHeader'
import { SingleLineInput } from '../../components/Input/SingleLineInput'
import * as Color from '../../components/Colors/colors'
import React, { useCallback, useState, useEffect } from 'react'
import { Button } from '../../components/Basic/Button'
import { SearchResult } from '../../modules/SearchResult'
import { TouchableOpacity } from 'react-native'
import { SearchOptionCategoryScroll } from '../../modules/SearchOptionCategoryScroll'
import { SearchButton } from '../../modules/SearchButton'
import { getGroupListFetch } from '../../util/FetchUtil'
import { getTokens } from '../../util/TokenUtil'
export const SearchResultScreen = () => {
  const [data, setData] = useState([])
  const [lastGroupId, setLastGroupId] = useState('')

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  const [isLoading, setIsLoading] = useState(false)

  const [searchValue, setSearchValue] = useState('')
  const [searchCategory, setSearchCategory] = useState('all')

  const getGroupListData = async () => {
    try {
      const response = await getGroupListFetch(accessToken, refreshToken, lastGroupId, searchValue, searchCategory == 'all' ? '' : searchCategory)
      const newData = await response.json()
      return newData
    } catch (e) {
      console.log(e)
      return []
    }
  }

  const loadData = async () => {
    if (isLoading) {
      return
    }
    setIsLoading(true)
    const newData = await getGroupListData()
    if (newData.dataHeader.successCode == 0) {
      if (newData.dataBody.length > 0) {
        setLastGroupId(newData.dataBody[newData.dataBody.length - 1].groupId)
        setData((prevData) => [...prevData, ...newData.dataBody])
      }
    }
    setIsLoading(false)
  }

  useEffect(() => {
    if (!isTokenGet) {
      getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
    } else {
      loadData()
    }
  }, [isTokenGet])

  const handleEndReached = () => {
    if (!isLoading) {
      loadData()
    }
  }

  // TO-DO
  // lastGroupId가 자꾸 완전 초기화가 되는 것이 아닌 오락가락 하는 중
  // 확인 필요
  const onPressSearch = () => {
    setLastGroupId('')
    setData([])
    loadData()
  }

  return (
    <View
      style={{ flex: 1 }}
      backgroundColor={Color.WHITE}
    >
      <MainHeader
        name={'김싸피'}
        url={'https://picsum.photos/600'}
      />
      <View style={styles.commonItem}>
        <SearchOptionCategoryScroll setSearchCategory={setSearchCategory} />

        <View style={{ marginHorizontal: 20, marginVertical: 10 }}>
          <View
            backgroundColor={Color.LIGHT_GRAY}
            borderRadius={10}
            marginVertical={10}
          >
            <SingleLineInput
              onChangeText={(text) => {
                setSearchValue(text)
              }}
              placeholder="✎) ex. 00대학교 총학생회"
            />
          </View>
          <TouchableOpacity onPress={onPressSearch}>
            <SearchButton
              color={Color.BLUE}
              title={'학생회 / 동아리 공금 현황 보러가기'}
              iconName={'airplane-outline'}
              isIcon={true}
            />
          </TouchableOpacity>
        </View>
      </View>

      <View style={styles.commonItem}>
        <View style={{ paddingHorizontal: 20 }}>
          <Typography fontSize={15}>What's new</Typography>
        </View>
      </View>

      <FlatList
        style={styles.commonItem}
        contentContainerStyle={{ paddingBottom: 30 }}
        data={data}
        renderItem={({ item }) => {
          return (
            <Button>
              <SearchResult item={item}></SearchResult>
            </Button>
          )
        }}
        onEndReached={handleEndReached}
        onEndReachedThreshold={0.1}
      />
    </View>
  )
}

var styles = StyleSheet.create({
  mainItem: { paddingTop: 60, paddingHorizontal: 20 },
  searchBar: { paddingTop: 30, paddingHorizontal: 20, backgroundColor: Color.GRAY },
  commonItem: { paddingTop: 30, paddingHorizontal: 20 },
})
