import { useEffect, useState } from 'react'
import AuthStackNavigation from './navigations/AuthNavigations/AuthStackNavigation'
import { useNavigation } from '@react-navigation/native'
import { getAuthDataFetch } from './util/FetchUtil'
import { getTokens, setTokens } from './util/TokenUtil'
import { useRecoilState } from 'recoil'
import { goMainPageState, UserInfoState } from './util/RecoilUtil/Atoms'

export const SplashView = () => {
  const navigation = useNavigation()

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  const [authInfo, setAuthInfo] = useState(null)

  const [user, setUser] = useRecoilState(UserInfoState)
  const [goMainPage, setGoMainPage] = useRecoilState(goMainPageState)

  const getAuthData = async () => {
    try {
      const response = await getAuthDataFetch(accessToken, refreshToken)
      const data = await response.json()
      console.log('SplashView.js (getAuthFetch): ', data)
      await setAuthInfo(data)
    } catch (e) {
      console.log(e)
    }
  }

  const authFlow = () => {
    if (!isTokenGet) {
      getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
    } else {
      if (authInfo == null) {
        // if (accessToken == null || refreshToken == null) {
        //   setTimeout(() => {
        //     navigation.reset({ routes: [{ name: 'SchoolEmail' }] })
        //   }, 1500)
        // } else {
        //   getAuthData()
        // }
        setGoMainPage(true)
      }

      if (authInfo != null) {
        if (authInfo.dataHeader.successCode == 0) {
          setUser(authInfo.dataBody.userInfo)
          if (authInfo.dataHeader.resultCode == 1) {
            setTokens(authInfo.dataBody.token.accessToken, authInfo.dataBody.token.refreshToken)
          }
          setTimeout(() => {
            setGoMainPage(true)
          }, 1500)
        } else {
          setTimeout(() => {
            navigation.reset({ routes: [{ name: 'Login' }] })
          }, 1500)
        }
      }
    }
  }

  useEffect(() => {
    authFlow()
  }, [isTokenGet, authInfo])

  return <AuthStackNavigation />
}
