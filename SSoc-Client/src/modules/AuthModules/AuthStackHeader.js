import React, { useCallback } from 'react'
import { View } from 'react-native'
import { Header } from '../../components/header/Header'
import { StackHeader } from '../StackHeader'

export const AuthStackHeader = (props) => {
  return (
    <View>
      <Header>
        <View></View>
        <Header.Title title={props.title}></Header.Title>
        <View></View>
      </Header>
    </View>
  )
}
