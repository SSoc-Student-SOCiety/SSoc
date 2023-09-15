import React, { useCallback, useState, useEffect } from 'react'
import { FlatList, TouchableOpacity, View, StyleSheet, Text } from 'react-native'
import { Divider } from '../../../../components/Basic/Divider'
import { ProfileImage } from '../../../../modules/ProfileImage'
import { Typography } from '../../../../components/Basic/Typography'
import * as Color from '../../../../components/Colors/colors'
import { getApproveGroupSignUpMemberFetch, getGroupSignupMemberListFetch, getRejectGroupSignUpMemberFetch } from '../../../../util/FetchUtil'
import { getTokens } from '../../../../util/TokenUtil'

export const RequestManageScreen = (props) => {
  const [requests, setRequests] = useState([])
  const [rejectGroupSignupMemberData, setRejectGroupSignupMemberData] = useState(null)
  const [approveGroupSignupMemberData, setApproveGroupSignupMemberData] = useState(null)

  const groupId = props.route.params.groupId
  const groupMemberRole = props.route.params.groupMemberRole

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  const onPressAccept = useCallback((groupSignupId) => {
    console.log('수락', groupSignupId)
  })

  const onPressRejcet = useCallback((groupSignupId) => {
    getRejectGroupSignupMemberData(groupSignupId)
  })

  const getGroupSingupMemberListData = async () => {
    try {
      const response = await getGroupSignupMemberListFetch(accessToken, refreshToken, groupId)
      const data = await response.json()
      if (data != null) {
        if (data.dataHeader != undefined) {
          if (data.dataHeader.successCode == 0) setRequests(data.dataBody)
        }
      }
    } catch (e) {
      console.log(e)
    }
  }

  const getRejectGroupSignupMemberData = async (groupSignupId) => {
    try {
      const response = await getRejectGroupSignUpMemberFetch(accessToken, refreshToken, groupSignupId)
      const data = await response.json()
      console.log(data)
      if (data != null) {
        if (data.dataHeader != undefined) {
          if (data.dataHeader.successCode == 0) setRejectGroupSignupMemberData(data.dataBody)
        }
      }
    } catch (e) {
      console.log(e)
    }
  }

  const getApproveGroupSignUpMemberData = async (groupSignupId) => {
    try {
      const response = await getApproveGroupSignUpMemberFetch(accessToken, refreshToken, groupSignupId)
      const data = await response.json()
      console.log(data)
      if (data != null) {
        if (data.dataHeader != undefined) {
          if (data.dataHeader.successCode == 0) setApproveGroupSignupMemberData(data.dataBody)
        }
      }
    } catch (e) {
      console.log(e)
    }
  }

  useEffect(() => {
    if (!isTokenGet) {
      getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
    } else {
      getGroupSingupMemberListData()
    }
  }, [isTokenGet, rejectGroupSignupMemberData, approveGroupSignupMemberData])

  return (
    <View style={{ flex: 1 }}>
      {requests != null && requests.length != 0 ? (
        <FlatList
          style={styles.commonItem}
          contentContainerStyle={{ paddingBottom: 30 }}
          data={requests}
          renderItem={({ item }) => {
            return (
              <View>
                <Divider />
                <View style={{ marginHorizontal: 15, flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                  <View
                    style={{ marginVertical: 4 }}
                    flexDirection={'row'}
                  >
                    <View style={{ alignItems: 'center', justifyContent: 'center' }}>
                      <ProfileImage
                        size={60}
                        url={item.userImgUrl}
                      />
                    </View>
                    <View>
                      <Typography fontSize={15}>{item.userName}</Typography>
                      <Typography fontSize={12}>{item.userNickname}</Typography>
                      <Typography
                        fontSize={10}
                        color={Color.GRAY}
                      >
                        {item.userEmail}
                      </Typography>
                    </View>
                  </View>
                  <View style={{ flexDirection: 'row' }}>
                    <TouchableOpacity onPress={() => onPressAccept(item.groupSignupId, item.groupId)}>
                      <View style={{ backgroundColor: Color.LIGHT_BLUE, width: 60, height: 30, borderRadius: 10, justifyContent: 'center', alignItems: 'center', marginHorizontal: 5 }}>
                        <Typography
                          fontSize={15}
                          color={Color.WHITE}
                        >
                          {' '}
                          수락{' '}
                        </Typography>
                      </View>
                    </TouchableOpacity>

                    <TouchableOpacity onPress={() => onPressRejcet(item.groupSignupId, item.groupId)}>
                      <View style={{ backgroundColor: Color.LIGHT_RED, width: 60, height: 30, borderRadius: 10, justifyContent: 'center', alignItems: 'center', marginHorizontal: 5 }}>
                        <Typography
                          fontSize={15}
                          color={Color.WHITE}
                        >
                          {' '}
                          거절{' '}
                        </Typography>
                      </View>
                    </TouchableOpacity>
                  </View>
                </View>
              </View>
            )
          }}
        />
      ) : (
        <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
          <Text style={{ color: Color.BLUE }}>가입 신청한 그룹원이 없습니다.</Text>
        </View>
      )}
    </View>
  )
}

var styles = StyleSheet.create({
  commonItem: { paddingTop: 30, paddingHorizontal: 20 },
})
