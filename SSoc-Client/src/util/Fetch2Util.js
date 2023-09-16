//가입 그룹 계좌 조회
const url = "https://ssafy.xyz";
export const getBalanceFetch = async (accessToken, refreshToken, accountId) => {
  const baseUrl = `${url}/accounts/${accountId}/balance`;

  return await fetch(baseUrl, {
    method: "GET",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
      Authorization: accessToken,
      Refresh: refreshToken,
    },
  });
};
