import AsyncStorage from '@react-native-async-storage/async-storage';

export async function setAsync(key, value) {
  try {
    await AsyncStorage.setItem(key, value);
  } catch (e) {
    console.log('isFinLoad 세팅 실패');
  }
}
export async function getIsFinLoadAsync(key) {
  try {
    return await AsyncStorage.getItem(key);
  } catch (e) {
    console.log('isFinLoad 가져오기 실패');
  }
}
