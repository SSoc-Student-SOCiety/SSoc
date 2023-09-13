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

export const makeQueryStringForGet = (baseUrl, queryParams) => {
  const queryString = Object.keys(queryParams)
    .filter((key) => queryParams[key] !== undefined)
    .map((key) => `${encodeURIComponent(key)}=${encodeURIComponent(queryParams[key])}`)
    .join('&')

  const fullUrl = `${baseUrl}?${queryString}`
  return fullUrl
}

// UserFetch
///////////////////
export const getAuthDataFetch = async (accessToken, refreshToken) => {
  return await fetch(url + '/user/start', {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
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

export const getLogoutFetch = async (accessToken, refreshToken) => {
  return await fetch(url + '/user/logout', {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
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

// BoardFetch
///////////////////
export const getContentListFetch = async (accessToken, refreshToken, groupId, keyword, category, lastPostId) => {
  const baseUrl = `${url}/posts`
  const queryParams = {
    groupId: groupId,
    pageSize: 10,
    'filter.keyword': keyword,
    category: category,
    'filter.lastPostId': lastPostId,
  }

  const fullUrl = makeQueryStringForGet(baseUrl, queryParams)

  return await fetch(fullUrl, {
    method: 'GET',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
  })
}

export const getWriteContentFetch = async (accessToken, refreshToken, postId, title, content, category, isAnonymous) => {
  const baseUrl = `${url}/posts/${postId}`

  return await fetch(baseUrl, {
    method: 'PUT',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({
      title: title,
      content: content,
      category: category,
      isAnonymous: isAnonymous,
    }),
  })
}
