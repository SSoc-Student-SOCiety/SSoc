import { Platform } from 'react-native'

// const _ANDROID_AVD_API_HOST = 'http://10.0.2.2:8080'
// const _IOS_API_HOST = 'http://localhost:8080'
// export default getAPIHost = () => {
//   if (Platform.OS === 'ios') {
//     return _IOS_API_HOST
//   } else if (Platform.OS === 'android') {
//     return _ANDROID_AVD_API_HOST
//   } else {
//     throw 'Platform not supported'
//   }
// }
// const url = getAPIHost()

const url = 'https://ssafy.xyz'

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

export const getChangNickNameFetch = async (accessToken, refreshToken, userEmail, userNickName) => {
  return await fetch(url + '/user/update/nickname', {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({
      userEmail: userEmail,
      userNickName: userNickName,
    }),
  })
}

export const getChangePasswordFetch = async (accessToken, refreshToken, userEmail, userNowPassword, userChangePassword) => {
  return await fetch(url + '/user/update/password', {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({
      userEmail: userEmail,
      userNowPassword: userNowPassword,
      userChangePassword: userChangePassword,
    }),
  })
}

export const getChangeProfileFetch = async (accessToken, refreshToken, userEmail, userImage) => {
  return await fetch(url + '/user/update/image', {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({
      userEmail: userEmail,
      userImage: userImage,
    }),
  })
}

export const getChangeAllFetch = async (accessToken, refreshToken, userEmail, userNowPassword, userChangePassword, userNickName, userImageUrl) => {
  return await fetch(url + '/user/update/nickname/password', {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({
      userEmail: userEmail,
      userNowPassword: userNowPassword,
      userChangePassword: userChangePassword,
      userNickName: userNickName,
      userImage: userImageUrl,
    }),
  })
}

export const getDeleteUserFetch = async (accessToken, refreshToken) => {
  return await fetch(`${url}/user/delete`, {
    method: 'DELETE',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({}),
  })
}

//BookingManageFetch
export const getAllBookingListFetch = async (accessToken, refreshToken, groupId) => {
  const baseUrl = `${url}/reservation/list/${groupId}`

  return await fetch(baseUrl, {
    method: 'GET',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
  })
}

export const getAllBookingListByOptionFetch = async (
  accessToken,
  refreshToken,
  groupId,
  option
) => {
  const baseUrl = `${url}/reservation/list/${groupId}?approveStatus=${option}`;

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

// BoardFetch
///////////////////
export const getContentListFetch = async (accessToken, refreshToken, groupId, keyword, category, lastPostId) => {
  const baseUrl = `${url}/posts`
  const queryParams = {
    groupId: groupId,
    'filter.pageSize': 10,
    'filter.keyword': keyword,
    'filter.category': category,
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

export const getWritePostFetch = async (accessToken, refreshToken, groupId, title, content, category, isAnonymous) => {
  return await fetch(url + '/posts', {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({
      groupId: groupId,
      title: title,
      content: content,
      category: category,
      isAnonymous: isAnonymous,
    }),
  })
}

export const getContentDeleteFetch = async (accessToken, refreshToken, postId) => {
  return await fetch(`${url}/posts/${postId}`, {
    method: 'DELETE',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({}),
  })
}

export const getEditContentFetch = async (accessToken, refreshToken, postId, title, content, category, isAnonymous) => {
  return await fetch(`${url}/posts/${postId}`, {
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

export const getCommentListFetch = async (accessToken, refreshToken, postId, lastCommentId) => {
  const baseUrl = `${url}/posts/${postId}/comments`
  const queryParams = {
    'filter.lastCommentId': lastCommentId,
    'filter.pageSize': 10,
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

export const getWriteCommentFetch = async (accessToken, refreshToken, postId, content, isAnonymous) => {
  return await fetch(`${url}/posts/${postId}/comments`, {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({
      content: content,
      isAnonymous: isAnonymous,
    }),
  })
}

export const getCommentDeleteFetch = async (accessToken, refreshToken, postId, commentId) => {
  return await fetch(`${url}/posts/${postId}/comments/${commentId}`, {
    method: 'DELETE',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({}),
  })
}

export const getEditCommentFetch = async (accessToken, refreshToken, postId, commentId, content, isAnonymous) => {
  return await fetch(`${url}/posts/${postId}/comments/${commentId}`, {
    method: 'PUT',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({
      content: content,
      isAnonymous: isAnonymous,
    }),
  })
}

export const getReplyListFetch = async (accessToken, refreshToken, commentId, postId, lastReplyId) => {
  const baseUrl = `${url}/posts/${postId}/replies`
  const queryParams = {
    commentId: commentId,
    'filter.lastReplyId': lastReplyId,
    'filter.pageSize': 10,
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

export const getWriteReplyFetch = async (accessToken, refreshToken, postId, commentId, content, isAnonymous) => {
  return await fetch(`${url}/posts/${postId}/replies`, {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({
      commentId: commentId,
      content: content,
      isAnonymous: isAnonymous,
    }),
  })
}

export const getReplyDeleteFetch = async (accessToken, refreshToken, postId, replyId) => {
  return await fetch(`${url}/posts/${postId}/replies/${replyId}`, {
    method: 'DELETE',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({}),
  })
}

export const getEditReplyFetch = async (accessToken, refreshToken, postId, replyId, content, isAnonymous) => {
  return await fetch(`${url}/posts/${postId}/replies/${replyId}`, {
    method: 'PUT',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({
      content: content,
      isAnonymous: isAnonymous,
    }),
  })
}

// GroupFetch
///////////////////
export const getGroupListFetch = async (accessToken, refreshToken, lastGroupId, keyword, category) => {
  const baseUrl = `${url}/groups`
  const queryParams = {
    lastGroupId: lastGroupId, // 생략 가능
    keyword: keyword, // 생략 가능
    category: category, // 생략 가능
    pageSize: 5,
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

export const getMyGroupListFetch = async (accessToken, refreshToken, lastGroupId, keyword, category) => {
  const baseUrl = `${url}/groups/my-groups`
  const queryParams = {
    lastGroupId: lastGroupId,
    keyword: keyword,
    category: category,
    pageSize: 5,
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

export const getGroupDetailFetch = async (accessToken, refreshToken, groupId) => {
  const baseUrl = `${url}/groups/${groupId}`

  return await fetch(baseUrl, {
    method: 'GET',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
  })
}

export const getGroupRoleFetch = async (accessToken, refreshToken, groupId) => {
  const baseUrl = `${url}/groups/${groupId}/my-role`

  return await fetch(baseUrl, {
    method: 'GET',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
  })
}

// 소속원 관리
// /groups/{groupId}/member/list
export const getGroupMemberListFetch = async (accessToken, refreshToken, groupId) => {
  const baseUrl = `${url}/groups/${groupId}/member/list`

  return await fetch(baseUrl, {
    method: 'GET',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
  })
}

// 소속원 삭제
// /groups/{groupId}/member/delete/{userId}
export const getDeleteGroupMemberFetch = async (accessToken, refreshToken, groupId, userId) => {
  return await fetch(`${url}/groups/${groupId}/member/delete/${userId}`, {
    method: 'DELETE',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({}),
  })
}

// 유저의 그룹 가입 신청
// /group/signup/user/{groupId}
export const getGroupSignUpFetch = async (accessToken, refreshToken, groupId) => {
  return await fetch(`${url}/group/signup/user/${groupId}`, {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({}),
  })
}

// 가입신청된 유저 목록 조회
// /group/signup/list/{groupId}
export const getGroupSignupMemberListFetch = async (accessToken, refreshToken, groupId) => {
  const baseUrl = `${url}/group/signup/list/${groupId}`

  return await fetch(baseUrl, {
    method: 'GET',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
  })
}

// 가입 요청 거절
// /group/signup/reject/{groupSignupId}
export const getRejectGroupSignUpMemberFetch = async (accessToken, refreshToken, groupSignupId) => {
  return await fetch(`${url}/group/signup/reject/${groupSignupId}`, {
    method: 'DELETE',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({}),
  })
}

// 가입 요청 수락
// /group/signup/approve/{groupSignupId}
export const getApproveGroupSignUpMemberFetch = async (accessToken, refreshToken, groupSignupId) => {
  return await fetch(`${url}/group/signup/approve/${groupSignupId}`, {
    method: 'PUT',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({}),
  })
}

// 그룹 정보 변경
// /groups/{groupId}
// 관리자만 가능 -> 관리자만 볼 수 있는 버튼
export const getEditGroupInfoFetch = async (accessToken, refreshToken, groupId, name, aboutUs, introduce, thumbnail) => {
  const baseUrl = `${url}/groups/${groupId}`

  return await fetch(baseUrl, {
    method: 'PUT',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({
      name: name,
      aboutUs: aboutUs,
      introduce: introduce,
      thumbnail: thumbnail,
    }),
  })
}

// product(reservation)
///////////////////

// 대여물품 전체조회
// /product/list/{groupId}
export const getProductListFetch = async (accessToken, refreshToken, groupId) => {
  const baseUrl = `${url}/product/list/${groupId}`

  return await fetch(baseUrl, {
    method: 'GET',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
  })
}

// 대여물품 카테고리별 조회
// /product/list/{groupId}/{category}
// CONVENIENCE, PARTY, BOOK
export const getProductCategoryListFetch = async (accessToken, refreshToken, groupId, category) => {
  const baseUrl = `${url}/product/list/${groupId}/${category}`

  return await fetch(baseUrl, {
    method: 'GET',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
  })
}

// 대여물품 상세정보 -> 예약 가능 시간 확인
// /reservation/detail/{productId}/{date}
export const getReservationTimeFetch = async (accessToken, refreshToken, productId, date) => {
  const baseUrl = `${url}/reservation/detail/${productId}/${date}`

  return await fetch(baseUrl, {
    method: 'GET',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
  })
}

// 대여물품 예약하기
// /reservation/detail/{productId}/{date}/{time}/ok
export const getReservationRequestFetch = async (accessToken, refreshToken, productId, date, time) => {
  const baseUrl = `${url}/reservation/detail/${productId}/${date}/${time}/ok`

  return await fetch(baseUrl, {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
  })
}

// 대여 물품 생성
// /product/create/{groupId}
export const getProductCreateFetch = async (accessToken, refreshToken, groupId, category, name, description, content, imageUrl) => {
  const baseUrl = `${url}/product/create/${groupId}`

  return await fetch(baseUrl, {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({
      category: category,
      name: name,
      description: description,
      content: content,
      imageUrl: imageUrl,
    }),
  })
}

// 대여 물품 삭제
// /product/delete/{groupId}/{productId}
export const getDeleteProductFetch = async (accessToken, refreshToken, groupId, productId) => {
  return await fetch(`${url}/product/delete/${groupId}/${productId}`, {
    method: 'DELETE',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({}),
  })
}

// 내가 예약한 대여물품
// /reservation/my/list/{groupId}
export const getMyReservationFetch = async (accessToken, refreshToken, groupId) => {
  const baseUrl = `${url}/reservation/my/list/${groupId}`

  return await fetch(baseUrl, {
    method: 'GET',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
  })
}

// 대여 물품 생성
// /product/create/{groupId}
export const getProductCreateFetch = async (
  accessToken,
  refreshToken,
  groupId,
  category,
  name,
  description,
  content,
  imageUrl
) => {
  const baseUrl = `${url}/product/create/${groupId}`;

  return await fetch(baseUrl, {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({
      category: category,
      name: name,
      description: description,
      content: content,
      imageUrl: imageUrl,
    }),
  });
};

// 대여 물품 삭제
// /product/delete/{groupId}/{productId}
export const getDeleteProductFetch = async (
  accessToken,
  refreshToken,
  groupId,
  productId
) => {
  return await fetch(`${url}/product/delete/${groupId}/${productId}`, {
    method: "DELETE",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({}),
  });
};

// 내가 예약한 대여물품
// /reservation/my/list/{groupId}
export const getMyReservationFetch = async (
  accessToken,
  refreshToken,
  groupId
) => {
  const baseUrl = `${url}/reservation/my/list/${groupId}`;

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

// calendar
///////////////////

// 일정 생성하기
// /groups/{groupId}/schedules
export const getCreateScheduleFetch = async (accessToken, refreshToken, groupId, title, content, category, startedAt) => {
  const baseUrl = `${url}/groups/${groupId}/schedules`

  return await fetch(baseUrl, {
    method: 'POST',
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
      startedAt: startedAt,
    }),
  })
}

// 일정 삭제
// /groups/{groupId}/schedules/{scheduleId}
export const getDeleteSceduleFetch = async (accessToken, refreshToken, groupId, scheduleId) => {
  return await fetch(`${url}/groups/${groupId}/schedules/${scheduleId}`, {
    method: 'DELETE',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
    body: JSON.stringify({}),
  })
}

// 일정 리스트 정보
// /groups/{groupId}/schedules
export const getSceduleListFetch = async (accessToken, refreshToken, groupId, date) => {
  const baseUrl = `${url}/groups/${groupId}/schedules`
  const queryParams = {
    date: date,
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

//transactions 시작
//월간 통계 내역 조회
export const getMonthlyStaticsFetch = async (accessToken, refreshToken, year, accountId) => {
  const baseUrl = `${url}/accounts/${accountId}/monthly-statistics?year=${year}`

  return await fetch(baseUrl, {
    method: 'GET',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
  })
}

//가입 그룹 계좌 조회
export const getTransactionFetch = async (
  accessToken,
  refreshToken,
  accountId,
  lastTransactionId
) => {
  const baseUrl = `${url}/accounts/${accountId}/transactions?filter.pageSize=10&filter.lastTransactionId=${lastTransactionId}`;

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

//가입 그룹 계좌 조회
export const getDailyStaticsFetch = async (
  accessToken,
  refreshToken,
  accountId,
  startDate,
  endDate
) => {
  const baseUrl = `${url}/accounts/${accountId}/daily-statistics?startDate=${startDate}&endDate=${endDate}`;
  console.log("baseUrl", baseUrl);
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

//가입 그룹 계좌 조회
export const getTransactionByDurationFetch = async (
  accessToken,
  refreshToken,
  accountId,
  startDate,
  endDate
) => {
  const baseUrl = `${url}/accounts/${accountId}/transactions?filter.pageSize=30&filter.startDate=${startDate}&filter.endDate=${endDate}`;

  return await fetch(baseUrl, {
    method: 'GET',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      Authorization: accessToken,
      Refresh: refreshToken,
    },
  })
}
