import React, { useState, useEffect } from 'react'
import { View, ScrollView, StyleSheet } from 'react-native'
import { MainHeader } from '../../modules/MainHeader'
import { Logo } from '../../modules/Logo'
import { MainSearchBar } from '../../modules/MainSerachBar'
import { Card } from '../../modules/Card'
import { useNavigation } from '@react-navigation/native'
import { Typography } from '../../components/Basic/Typography'
import { Spacer } from '../../components/Basic/Spacer'
import * as Color from '../../components/Colors/colors'
import { getGroupListFetch } from '../../util/FetchUtil'
import { getTokens } from '../../util/TokenUtil'

export const MainScreen = () => {
  const navigation = useNavigation()
  const [groupListData, setGroupListData] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)
  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)

  const getGroupListData = async () => {
    try {
      const response = await getGroupListFetch(accessToken, refreshToken, '', '', '')
      const data = await response.json()
      console.log('MainScreen.js (getGroupListFetch): ', data)
      await setGroupListData(data)
    } catch (e) {
      console.log(e)
    }
  }

  useEffect(() => {
    if (!isTokenGet) {
      getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
    } else {
      getGroupListData()
    }
  }, [isTokenGet])

  return (
    <View style={styles.container}>
      <MainHeader
        name={'김싸피'}
        url={'https://picsum.photos/600'}
      />
      <Logo />
      <MainSearchBar />

      <View style={styles.cardContainer}>
        <Typography
          fontSize={16}
          color={Color.GRAY}
        >
          {' '}
          * 최근 만들어진 학생회/동아리
        </Typography>
        <Spacer space={10} />
        <ScrollView
          horizontal
          showsHorizontalScrollIndicator={false}
        >
          {cardsData.map((card, index) => (
            <Card
              key={index}
              {...card}
            />
          ))}
        </ScrollView>
      </View>
    </View>
  )
}

const cardsData = [
  {
    groupId: 1,
    name: 'TestName1',
    aboutUs: '제36대 총학생회',
    school: '전남대',
    thumbnail: 'https://picsum.photos/600',
    memberCnt: 1,
  },
  {
    groupId: 2,
    name: 'TestName2',
    aboutUs: '제37대 총학생회',
    school: '서울대',
    thumbnail: 'https://picsum.photos/500',
    memberCnt: 124,
  },
  {
    groupId: 3,
    name: 'TestName3',
    aboutUs: '제38대 총학생회',
    school: '신한대',
    thumbnail: 'https://picsum.photos/700',
    memberCnt: 55,
  },
  {
    groupId: 4,
    name: 'TestName4',
    aboutUs: '제39대 총학생회',
    school: '싸피대',
    thumbnail: 'https://picsum.photos/400',
    memberCnt: 3,
  },
]

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: 'white' },
  cardContainer: { paddingTop: 60, paddingHorizontal: 15 },
})
