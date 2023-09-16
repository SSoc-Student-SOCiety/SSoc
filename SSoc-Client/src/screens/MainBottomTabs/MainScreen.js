import React, { useState, useEffect } from 'react'
import { View, ScrollView, StyleSheet, FlatList } from 'react-native'
import { MainHeader } from '../../modules/MainHeader'
import { Logo } from '../../modules/Logo'
import { MainSearchBar } from '../../modules/MainSearchBar'
import { Card } from '../../modules/Card'
import { Typography } from '../../components/Basic/Typography'
import { Spacer } from '../../components/Basic/Spacer'
import * as Color from '../../components/Colors/colors'
import { getMyGroupListFetch } from '../../util/FetchUtil'
import { getTokens } from '../../util/TokenUtil'
import { useRecoilValue } from 'recoil'
import { UserInfoState } from '../../util/RecoilUtil/Atoms'

export const MainScreen = () => {
  const [lastGroupId, setLastGroupId] = useState('')
  const [data, setData] = useState([])
  const user = useRecoilValue(UserInfoState)
  console.log(user)

  const [isTokenGet, setIsTokenGet] = useState(false)
  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)

  const getMyGroupListData = async () => {
    try {
      const response = await getMyGroupListFetch(accessToken, refreshToken, lastGroupId, '', '')
      const data = await response.json()
      return data
    } catch (e) {
      console.log(e)
    }
  }

  const loadData = async () => {
    const newData = await getMyGroupListData()
    if (newData.dataHeader.successCode == 0) {
      if (newData.dataBody.length > 0) {
        setLastGroupId(newData.dataBody[newData.dataBody.length - 1].groupId)
        setData((prevData) => [...prevData, ...newData.dataBody])
      }
    }
  }

  useEffect(() => {
    if (!isTokenGet) getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
    else loadData()
  }, [isTokenGet])

  const handleEndReached = () => {
    loadData()
  }

  return (
    <View style={styles.container}>
      <MainHeader
        name={user.userNickname}
        url={user.userImageUrl}
      />
      <ScrollView>
        <Logo />
        <MainSearchBar />

        <View style={styles.cardContainer}>
          <Typography
            fontSize={16}
            color={Color.GRAY}
          >
            {' '}
            * 내가 가입한 학생회/동아리
          </Typography>
          <Spacer space={8} />
          <FlatList
            horizontal
            showsHorizontalScrollIndicator={false}
            data={data}
            renderItem={({ item, index }) => (
              <Card
                key={index}
                {...item}
              />
            )}
            onEndReached={handleEndReached}
            onEndReachedThreshold={0.1}
          />
        </View>
      </ScrollView>
    </View>
  )
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: 'white' },
  cardContainer: { paddingTop: 25, paddingHorizontal: 15 },
})
