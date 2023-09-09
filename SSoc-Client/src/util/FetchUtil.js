export const getAuthDataFetch = async (refleshToken, accessToken) => {
  return await fetch('http://localhost:8080', {
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
  return await fetch('http://localhost:8080/user/login', {
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
  return await fetch('http://localhost:8080/user/signup', {
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
  return await fetch('http://localhost:8080/user/email/send', {
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
  return await fetch('http://localhost:8080/user/email/check', {
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
  return await fetch('http://localhost:8080/user/email/password', {
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
  return await fetch('http://localhost:8080/user/update/nickname', {
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
  return await fetch('http://localhost:8080/user/update/password', {
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
  return await fetch('http://localhost:8080/user/update/nickname/password', {
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
