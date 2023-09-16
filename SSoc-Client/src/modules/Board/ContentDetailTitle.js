import { useEffect, useState } from 'react'
import { Alert, Modal, Text, TouchableOpacity, View } from 'react-native'
import { ProfileImage } from '../ProfileImage'
import * as Color from '../../components/Colors/colors'
import { useRecoilValue } from 'recoil'
import { UserInfoState } from '../../util/RecoilUtil/Atoms'
import { getTokens } from '../../util/TokenUtil'
import { useNavigation } from '@react-navigation/native'
import { getContentDeleteFetch } from '../../util/FetchUtil'
import EditContent from './EditContent'

const ContentDetailTitle = (props) => {
  const navigation = useNavigation()
  const content = props.content
  const user = useRecoilValue(UserInfoState)
  const [editContent, setEditContent] = useState(false)

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  const getContentDeleteData = async () => {
    try {
      const response = await getContentDeleteFetch(accessToken, refreshToken, content.postId)
      const data = await response.json()
      if (data.dataHeader.successCode == 0) {
        Alert.alert('게시글이 삭제되었습니다.', props.setReload(!props.reload), navigation.goBack())
      } else {
        Alert.alert(data.dataHeader.resultMessage)
      }
    } catch (e) {
      console.log(e)
    }
  }

  useEffect(() => {
    if (!isTokenGet) {
      getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
    }
  }, [])

  return (
    <View style={{ flexDirection: 'column', marginTop: 5, marginHorizontal: 10, padding: 10, borderRadius: 5, borderWidth: 0.2, borderBottomWidth: 2, borderColor: Color.GRAY }}>
      {content.userId == user.id ? (
        <View style={{ position: 'absolute', top: 10, right: 20, zIndex: 1 }}>
          <TouchableOpacity
            onPress={() => {
              getContentDeleteData()
            }}
          >
            <View style={{ backgroundColor: Color.GRAY, borderRadius: 5, height: 30, width: 40, alignItems: 'center', justifyContent: 'center' }}>
              <Text style={{ color: Color.WHITE }}>삭제</Text>
            </View>
          </TouchableOpacity>
        </View>
      ) : null}
      {content.userId == user.id ? (
        <View style={{ position: 'absolute', top: 10, right: 70, zIndex: 1 }}>
          <TouchableOpacity
            onPress={() => {
              setEditContent(true)
            }}
          >
            <View style={{ backgroundColor: Color.BLUE, borderRadius: 5, height: 30, width: 40, alignItems: 'center', justifyContent: 'center' }}>
              <Text style={{ color: Color.WHITE }}>수정</Text>
            </View>
          </TouchableOpacity>
        </View>
      ) : null}
      <View>
        <Text style={{ fontSize: 22, fontWeight: 'bold' }}>{content.title}</Text>
      </View>
      <View style={{ flexDirection: 'row', alignItems: 'center', padding: 5 }}>
        <View>
          <ProfileImage
            size={50}
            url={content.profileImg}
          />
        </View>
        <View style={{ flex: 1, marginLeft: 6, flexDirection: 'column', justifyContent: 'space-evenly' }}>
          <Text style={{ color: Color.GRAY, fontWeight: 'bold', marginVertical: 4, fontSize: 16, fontWeight: 'bold' }}>{content.nickname}</Text>
          <View style={{ flexDirection: 'row', marginVertical: 4 }}>
            <Text style={{ color: Color.GRAY }}>{content.createdAt + ' '}</Text>
            {/* <Text style={{ color: Color.GRAY }}>조회: {content.views}</Text> */}
          </View>
        </View>
      </View>
      <Modal
        animationType="fade"
        transparent={true}
        visible={editContent}
        onBackdropPress={() => setEditContent(false)}
      >
        <EditContent
          content={content}
          reload={props.reload}
          setReload={props.setReload}
          setEditContent={setEditContent}
        />
      </Modal>
    </View>
  )
}

export default ContentDetailTitle
