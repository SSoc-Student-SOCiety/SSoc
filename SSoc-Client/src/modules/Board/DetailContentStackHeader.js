import { Header } from '../../components/header/Header'
import React, { useCallback } from 'react'
import { useNavigation } from '@react-navigation/native'
import { Ionicons } from '@expo/vector-icons'

export const DetailContentStackHeader = (props) => {
  const navigation = useNavigation()
  const setReload = props.setReload
  const reload = props.reload
  const onPressBack = useCallback(() => {
    navigation.goBack()
    setReload(!reload)
  }, [])

  return (
    <Header>
      <Header.Icon
        iconName="arrow-back"
        onPress={onPressBack}
      />
      <Header.Title title={props.title}></Header.Title>
      <Ionicons
        name="settings"
        color="white"
      />
    </Header>
  )
}
