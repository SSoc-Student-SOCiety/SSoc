import { Platform } from 'react-native'

const _ANDROID_AVD_API_HOST = 'http://10.0.2.2:8080'
const _IOS_API_HOST = 'http://localhost:8080'
export default getAPIHost = () => {
  if (Platform.OS === 'ios') {
    return _IOS_API_HOST
  } else if (Platform.OS === 'android') {
    return _ANDROID_AVD_API_HOST
  } else {
    throw 'Platform not supported'
  }
}
const url = getAPIHost()

export const getAuthDataFetch = async (refleshToken, accessToken) => {
  return await fetch(url + '/user/info', {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      refleshToken: refleshToken,
      accessToken: accessToken,
    }),
  })
}

export const getLoginDataFetch = async (userEmail, userPassword) => {
  return await fetch(url + '/user/login', {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      userEmail: userEmail,
      userPassword: userPassword,
    }),
  })
}

export const getRegisterResultFetch = async (userEmail, userPassword, userName, userNickName) => {
  return await fetch(url + '/user/signup', {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      userEmail: userEmail,
      userPassword: userPassword,
      userName: userName,
      userNickName: userNickName,
    }),
  })
}

export const getEmailAuthCodeFetch = async (userEmail) => {
  return await fetch(url + '/user/email/send', {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      userEmail: userEmail,
    }),
  })
}

export const getEmailCheckFetch = async (userEmail) => {
  return await fetch(url + '/user/email/check', {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      userEmail: userEmail,
    }),
  })
}

export const getTempPassWordFetch = async (userEmail) => {
  return await fetch(url + '/user/email/password', {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      userEmail: userEmail,
    }),
  })
}

export const getChangNickNameFetch = async (userEmail, userNowPassword, userChangePassword, userNickName, userImage) => {
  return await fetch(url + '/user/update/nickname', {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      userEmail: userEmail,
      userNowPassword: userNowPassword,
      userChangePassword: userChangePassword,
      userNickName: userNickName,
      userImage: userImage,
    }),
  })
}

export const getChangePasswordFetch = async (userEmail, userNowPassword, userChangePassword, userNickName, userImage) => {
  return await fetch(url + '/user/update/password', {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      userEmail: userEmail,
      userNowPassword: userNowPassword,
      userChangePassword: userChangePassword,
      userNickName: userNickName,
      userImage: userImage,
    }),
  })
}

export const getChangeAllFetch = async (userEmail, userNowPassword, userChangePassword, userNickName, userImage) => {
  return await fetch(url + '/user/update/nickname/password', {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      userEmail: userEmail,
      userNowPassword: userNowPassword,
      userChangePassword: userChangePassword,
      userNickName: userNickName,
      userImage: userImage,
    }),
  })
}
