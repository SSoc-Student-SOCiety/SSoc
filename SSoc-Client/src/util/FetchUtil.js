export const getAuthDataFetch = async (refleshToken, accessToken) => {
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
