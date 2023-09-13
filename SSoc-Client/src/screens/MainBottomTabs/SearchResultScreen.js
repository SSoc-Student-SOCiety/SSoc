import { Typography } from '../../components/Basic/Typography'
import { ScrollView, View, StyleSheet, FlatList } from 'react-native'
import { MainHeader } from '../../modules/MainHeader'
import { SingleLineInput } from '../../components/Input/SingleLineInput'
import * as Color from '../../components/Colors/colors'
import { Icon } from '../../components/Icons/Icons'
import React, { useCallback, useState } from 'react'
import { Button } from '../../components/Basic/Button'
import { SearchResult } from '../../modules/SearchResult'
import { TouchableOpacity } from 'react-native'
import { Spacer } from '../../components/Basic/Spacer'
import { SearchOptionCategoryScroll } from '../../modules/SearchOptionCategoryScroll'
import { Divider } from '../../components/Basic/Divider'
import { SearchButton } from '../../modules/SearchButton'
export const SearchResultScreen = () => {
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
        <SearchOptionCategoryScroll />

        <View style={{ marginHorizontal: 20, marginVertical: 10 }}>
          <View
            backgroundColor={Color.LIGHT_GRAY}
            borderRadius={10}
            marginVertical={10}
          >
            <SingleLineInput placeholder="✎) ex. 00대학교 총학생회" />
          </View>
          <TouchableOpacity>
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

      {/* to-do 무한 스크롤 구현하기 */}
      <FlatList
        style={styles.commonItem}
        contentContainerStyle={{ paddingBottom: 30 }}
        data={IMAGE_LIST}
        renderItem={({ item }) => {
          return (
            <Button>
              <SearchResult item={item}></SearchResult>
            </Button>
          )
        }}
      />
    </View>
  )
}

var styles = StyleSheet.create({
  mainItem: { paddingTop: 60, paddingHorizontal: 20 },
  searchBar: { paddingTop: 30, paddingHorizontal: 20, backgroundColor: Color.GRAY },
  commonItem: { paddingTop: 30, paddingHorizontal: 20 },
})

const IMAGE_LIST = [
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
