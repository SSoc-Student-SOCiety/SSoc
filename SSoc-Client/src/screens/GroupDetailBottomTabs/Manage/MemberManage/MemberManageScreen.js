import React, { useCallback, useState, useEffect } from 'react'
import { FlatList, TouchableOpacity, View, StyleSheet, Text } from 'react-native'
import { Divider } from '../../../../components/Basic/Divider'
import { ProfileImage } from '../../../../modules/ProfileImage'
import { Typography } from '../../../../components/Basic/Typography'
import * as Color from '../../../../components/Colors/colors'
import { getDeleteGroupMemberFetch, getGroupMemberListFetch } from '../../../../util/FetchUtil'
import { getTokens } from '../../../../util/TokenUtil'

export const MemberManageScreen = (props) => {
  const [requests, setRequests] = useState([])
  const [deleteGroupMemberData, setDeleteGroupMemberData] = useState(null)
  const groupId = props.route.params.groupId
  const groupMemberRole = props.route.params.groupMemberRole

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  const getGroupMemberListData = async () => {
    try {
      const response = await getGroupMemberListFetch(accessToken, refreshToken, groupId)
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

  const getDeleteGroupMemberData = async (groupMemberId) => {
    try {
      const response = await getDeleteGroupMemberFetch(accessToken, refreshToken, groupId, groupMemberId)
      const data = await response.json()
      console.log(data)
      if (data != null) {
        if (data.dataHeader != undefined) {
          if (data.dataHeader.successCode == 0) setDeleteGroupMemberData(data.dataBody)
        }
      }
    } catch (e) {
      console.log(e)
    }
  }

  const onPressRejcet = useCallback((groupMemberId) => {
    console.log('삭제', groupMemberId)
    getDeleteGroupMemberData(groupMemberId)
  })

  useEffect(() => {
    if (!isTokenGet) {
      getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
    } else {
      getGroupMemberListData()
    }
  }, [isTokenGet, deleteGroupMemberData])

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
                    <TouchableOpacity onPress={() => onPressRejcet(item.groupMemberId, item.groupId)}>
                      <View style={{ backgroundColor: Color.LIGHT_RED, width: 100, height: 40, borderRadius: 10, justifyContent: 'center', alignItems: 'center', marginHorizontal: 5 }}>
                        <Typography
                          fontSize={15}
                          color={Color.WHITE}
                        >
                          {' '}
                          그룹에서 삭제{' '}
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
          <Text style={{ color: Color.BLUE }}>가입된 그룹원이 없습니다.</Text>
        </View>
      )}
    </View>
  )
}

var styles = StyleSheet.create({
  commonItem: { paddingTop: 30, paddingHorizontal: 20 },
})
