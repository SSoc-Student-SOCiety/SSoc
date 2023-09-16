import AsyncStorage from "@react-native-async-storage/async-storage"

export async function setAsync(key, value) {
  try {
    await AsyncStorage.setItem(key, JSON.stringify(value))
  } catch (e) {
    console.log(e)
    console.log(key + ' 세팅 실패')
  }
}

export async function getAsync(key) {
  try {
    return await AsyncStorage.getItem(key)
  } catch (e) {
    console.log(key + ' 가져오기 실패')
  }
}

export async function removeAsync(key) {
  try {
    return await AsyncStorage.removeItem(key)
  } catch (e) {
    console.log(key + ' 삭제 실패')
  }
}
