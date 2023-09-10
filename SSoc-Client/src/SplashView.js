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
      successCode: 0,
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

  const getAuthData = async (refleshToken, accessToken) => {
    try {
      const response = await getAuthDataFetch(refleshToken, accessToken)
      const data = await response.json()
      await setAuthInfo(tempData)
    } catch (e) {
      console.log(e)
    }
  }

  const authFlow = () => {
    // 토큰 얻어오기(async에서)
    if (!isTokenGet) {
      getTokens(setAccessToken, setRefleshToken, setIsTokenGet)
    } else {
      // 토큰 가져왔음
      // Fetch 보내기 전. 즉, authInfo 요청 아직 안 함
      if (authInfo == null) {
        // 가져온 토큰 확인 -> 토큰 없으면 회원가입 하러 감
        if (accessToken == null || refleshToken == null) {
          setTimeout(() => {
            navigation.reset({ routes: [{ name: 'SchoolEmail' }] })
          }, 1500)
        } else {
          getAuthData() // 토큰 없어서 데이터 요청함
        }
      }

      // 요청 받아서 가져옴
      if (authInfo != null) {
        if (authInfo.dataHeader.successCode == 0) {
          // recoil에 유저 정보 담기
          setUser(authInfo.dataBody.userInfo)
          if (authInfo.dataHeader.resultCode == 1) {
            // 토큰 재발급인 경우에 async 업데이트
            setTokens(authInfo.dataBody.token.accessToken, authInfo.dataBody.token.refleshToken)
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
