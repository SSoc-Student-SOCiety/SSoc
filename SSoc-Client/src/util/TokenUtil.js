import { setAsync, getAsync, removeAsync } from './AsyncUtil'

export const getTokens = async (setAccessToken, setRefleshToken, setIsTokenGet) => {
  getAsync('accessToken').then((res) => {
    setAccessToken(res)
  })
  getAsync('refleshToken').then((res) => {
    setRefleshToken(res)
    setIsTokenGet(true)
  })
}

export const setTokens = async (accessToken, refleshToken) => {
  setAsync('accessToken', accessToken)
  setAsync('refleshToken', refleshToken)
}

export const removeTokens = async () => {
  removeAsync('accessToken')
  removeAsync('refleshToken')
}
