import { useState } from 'react'
import { SplashView } from './SplashView'
import { RootStackNavigation } from './navigations/MainNavigations/RootStackNavigation'
import { useRecoilState } from 'recoil'
import { goMainPageState } from './util/RecoilUtil/Atoms'

export const RootApp = () => {
  const [goMainPage, setGoMainPage] = useRecoilState(goMainPageState)

  if (!goMainPage) {
    return <SplashView />
  }
  return (
    //추후 네비게이션 컨테이너 호출
    <RootStackNavigation />
  )
}
