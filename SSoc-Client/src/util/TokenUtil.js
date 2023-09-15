import { setAsync, getAsync, removeAsync } from "./AsyncUtil";

export const getTokens = async (
  setAccessToken,
  setRefreshToken,
  setIsTokenGet
) => {
  getAsync("accessToken").then((res) => {
    setAccessToken(res);
  });
  getAsync("refreshToken").then((res) => {
    setRefreshToken(res);
    setIsTokenGet(true);
  });
};

export const setTokens = async (accessToken, refreshToken) => {
  if (accessToken != null && refreshToken != null) {
    setAsync("accessToken", "Bearer " + accessToken);
    setAsync("refreshToken", "Bearer " + refreshToken);
  }
};

export const removeTokens = async () => {
  removeAsync("accessToken");
  removeAsync("refreshToken");
};
