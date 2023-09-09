export const getAuthDataFetch = async (refleshToken, accessToken) => {
  //TO-DO
  // api Url 나오면 바꾸기
  return await fetch('https://quotes-by-api-ninjas.p.rapidapi.com/v1/quotes', {
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

export const getRegisterResultCodeFetch = async (userEmail, userPassword, userName, userNickName) => {
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
