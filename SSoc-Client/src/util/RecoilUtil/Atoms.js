import { atom } from 'recoil'

export const UserInfoState = atom({
  key: 'userInfoState',
  default: null,
})

export const goMainPageState = atom({
  key: 'goMainPageState',
  default: false,
})
