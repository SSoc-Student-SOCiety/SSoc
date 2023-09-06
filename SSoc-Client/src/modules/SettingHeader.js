import { Header } from '../components/header/Header'
import React, { useCallback } from 'react'
import { useNavigation } from '@react-navigation/native'
import { View } from 'react-native'

export const SettingHeader = (props) => {
  const navigation = useNavigation()

  const onPressBack = useCallback(() => {
    navigation.goBack()
  }, [])

  return (
    <Header>
      <Header.Icon
        iconName="arrow-back"
        onPress={onPressBack}
      />
      <Header.Title title={props.title}></Header.Title>
      <View style={{ width: 25 }}></View>
    </Header>
  )
}
