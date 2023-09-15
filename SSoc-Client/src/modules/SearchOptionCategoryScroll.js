import React, { useState } from 'react'
import { View, ScrollView, StyleSheet } from 'react-native'
import { Button } from '../components/Basic/Button'
import { CategoryButton } from './CategoryButton'
import * as Color from '../components/Colors/colors'

export const SearchOptionCategoryScroll = (props) => {
  const [options, setOptions] = useState({
    all: true,
    COUNCIL: false,
    CLUB: false,
  })

  const onPressOption = (selectedOption) => {
    setOptions({
      all: false,
      COUNCIL: false,
      CLUB: false,
      [selectedOption]: true,
    })
    props.setSearchCategory(selectedOption)
  }

  return (
    <View style={styles.commonItem}>
      <ScrollView
        horizontal
        showsHorizontalScrollIndicator={false}
      >
        <Button onPress={() => onPressOption('all')}>
          <CategoryButton
            title={'전체 검색'}
            isActivated={options.all}
          />
        </Button>
        <Button onPress={() => onPressOption('COUNCIL')}>
          <CategoryButton
            title={'학생회로 찾기'}
            isActivated={options.COUNCIL}
          />
        </Button>
        <Button onPress={() => onPressOption('CLUB')}>
          <CategoryButton
            title={'동아리로 찾기'}
            isActivated={options.CLUB}
          />
        </Button>
      </ScrollView>
    </View>
  )
}

const styles = StyleSheet.create({
  commonItem: { paddingHorizontal: 10 },
})
