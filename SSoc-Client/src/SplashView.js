import { useCallback, useEffect, useState } from 'react'
import AuthStackNavigation from './navigations/AuthNavigations/AuthStackNavigation'
import { useNavigation } from '@react-navigation/native'
import { getAuthDataFetch } from './util/FetchUtil'
import { getTokens, setTokens } from './util/TokenUtil'
import { useRecoilState } from 'recoil'
import { goMainPageState, UserInfoState } from './util/RecoilUtil/Atoms'

export const SplashView = () => {
  const navigation = useNavigation()

  const [refleshToken, setRefleshToken] = useState(null)
  const [accessToken, setAccessToken] = useState(null)

  const [authInfo, setAuthInfo] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  const [user, setUser] = useRecoilState(UserInfoState)
  const [goMainPage, setGoMainPage] = useRecoilState(goMainPageState)

  const tempData = {
    dataHeader: {
      successCode: 1,
      resultCode: 0,
      resultMessage: null,
    },
    dataBody: {
      userInfo: {
        userEmail: 'shinhan@ssafy.ac.kr',
        userName: '김싸피',
        userNickName: '김신한',
        userImageUrl: 'https://images.pexels.com/photos/14268955/pexels-photo-14268955.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2',
      },
      token: {
        grantType: 'bearer',
        accessToken: 'testSplashScreen_REFLASH_AccessToken',
        refreshToken: 'testSplashScreen_REFLASH_RefleshToken',
        accessTokenExpiresIn: 12345153351,
      },
    },
  }

  const getAuthData = async () => {
    try {
      const response = await getAuthDataFetch()
      const data = await response.json()
      await setAuthInfo(tempData)
    } catch (e) {
      console.log(e)
    }
  }

  const authFlow = () => {
    if (!isTokenGet) {
      getTokens(setAccessToken, setRefleshToken, setIsTokenGet)
    } else {
      if (authInfo == null) {
        if (accessToken == null || refleshToken == null) {
          setTimeout(() => {
            navigation.reset({ routes: [{ name: 'SchoolEmail' }] })
          }, 1500)
          return
        }
      }
      if (authInfo != null) {
        setUser(authInfo.dataBody.userInfo)
        if (authInfo.dataHeader.successCode == 0) {
          if (authInfo.dataHeader.resultCode == 1) {
            setTokens(authInfo.dataBody.accessToken, authInfo.dataBody.refleshToken)
          }
          setTimeout(() => {
            setGoMainPage(true)
          }, 1500)
        } else {
          setTimeout(() => {
            navigation.reset({ routes: [{ name: 'Login' }] })
          }, 1500)
        }
      } else {
        getAuthData()
      }
    }
  }

  //처음 앱 실행시 async storage에 저장되어있는 jwt로 로그인 시도
  useEffect(() => {
    authFlow()
  }, [isTokenGet, authInfo])

  return <AuthStackNavigation />
}
