import { TouchableOpacity, View, StyleSheet, ScrollView, Image, Alert } from 'react-native'
import { Typography } from '../../../components/Basic/Typography'
import { StackHeader } from '../../../modules/StackHeader'
import { useNavigation } from '@react-navigation/native'
import React, { useCallback, useState, useEffect } from 'react'
import { Button } from '../../../components/Basic/Button'
import { CategoryButton } from '../../../modules/CategoryButton'
import * as Color from '../../../components/Colors/colors'
import { FlatList } from 'react-native'
import { Spacer } from '../../../components/Basic/Spacer'
import { Divider } from '../../../components/Basic/Divider'
import { RemoteImage } from '../../../components/ImageLoader/RemoteImage'
import { Ionicons } from '@expo/vector-icons'
import { DeleteModal } from '../../../components/Modal/DeleteModal'
import { getTokens } from '../../../util/TokenUtil'
import { getProductCategoryListFetch, getProductListFetch } from '../../../util/FetchUtil'

export const MainBookingScreen = (props) => {
  const [selectedCategory, setSelectedCategory] = useState('all')
  const groupMemberRole = props.route.params.groupMemberRole
  const groupId = props.route.params.groupId

  const [isModalVisible, setIsModalVisible] = useState(false)
  const [selectedItemId, setSelectedItemId] = useState()
  const [selectedItemName, setSelectedItemName] = useState()

  const [accessToken, setAccessToken] = useState(null)
  const [refreshToken, setRefreshToken] = useState(null)
  const [isTokenGet, setIsTokenGet] = useState(false)

  const [productListData, setProductListData] = useState([])

  const onPressDelete = useCallback((name, id) => {
    setIsModalVisible(true)
    setSelectedItemName(name)
    setSelectedItemId(id)
  })

  const getProductListData = async () => {
    try {
      const response = await getProductListFetch(accessToken, refreshToken, groupId)
      const data = await response.json()
      if (data != null && data.dataHeader != undefined) {
        if (data.dataHeader.successCode == 0) {
          setProductListData(data)
        }
      } else {
        Alert.alert('서버 통신 에러 발생', '다시 시도해주세요.')
      }
    } catch (e) {
      console.log(e)
    }
  }

  const getProductCategoryListData = async () => {
    try {
      const response = await getProductCategoryListFetch(accessToken, refreshToken, groupId, selectedCategory)
      const data = await response.json()
      if (data != null && data.dataHeader != undefined) {
        if (data.dataHeader.successCode == 0) {
          setProductListData(data)
        }
      } else {
        Alert.alert('서버 통신 에러 발생', '다시 시도해주세요.')
      }
    } catch (e) {
      console.log(e)
    }
  }

  useEffect(() => {
    if (!isTokenGet) {
      getTokens(setAccessToken, setRefreshToken, setIsTokenGet)
    } else {
      if (selectedCategory == 'all') {
        getProductListData()
      } else {
        getProductCategoryListData()
      }
    }
  }, [isTokenGet, selectedCategory])

  const onPressOption = (selectedOption) => {
    setSelectedCategory(selectedOption)
  }

  const SelectSearchOption = () => {
    return (
      <View style={styles.commonItem}>
        <ScrollView
          horizontal
          showsHorizontalScrollIndicator={false}
        >
          <Button onPress={() => onPressOption('all')}>
            <CategoryButton
              title={'전체 검색'}
              isActivated={selectedCategory === 'all'}
            />
          </Button>

          <Button onPress={() => onPressOption('CONVENIENCE')}>
            <CategoryButton
              title={'편의성'}
              isActivated={selectedCategory === 'CONVENIENCE'}
            />
          </Button>
          <Button onPress={() => onPressOption('PARTY')}>
            <CategoryButton
              title={'행사용품'}
              isActivated={selectedCategory === 'PARTY'}
            />
          </Button>
          <Button onPress={() => onPressOption('BOOK')}>
            <CategoryButton
              title={'전공서적'}
              isActivated={selectedCategory === 'BOOK'}
            />
          </Button>
        </ScrollView>
      </View>
    )
  }

  const renderRow = (item1, item2) => {
    return (
      <View style={{ flexDirection: 'row', justifyContent: 'center' }}>
        <ItemCard item={item1} />
        <ItemCard item={item2} />
      </View>
    )
  }

  const renderItem = ({ item, index }) => {
    if (index % 2 === 0 && index + 1 < productListData.dataBody.length) {
      return renderRow(productListData.dataBody[index], productListData.dataBody[index + 1])
    } else if (index === productListData.dataBody.length - 1) {
      if (productListData.dataBody.length % 2 === 1)
        // 마지막 항목이 홀수 개일 경우
        return (
          <View style={{ flexDirection: 'row', justifyContent: 'center' }}>
            <ItemCard item={item} />
            <EmptyCard />
          </View>
        )
    } else {
      return null // 홀수 번째 항목은 무시
    }
  }
  const EmptyCard = () => {
    return (
      <View style={styles.emptyCard}>
        <Typography color={Color.WHITE}>빈 카드</Typography>
      </View>
    )
  }
  const ItemCard = ({ item }) => {
    const navigation = useNavigation()

    const onPressGoDetail = useCallback((item) => {
      navigation.navigate('BookingItemDetialScreen', { item: item })
    })
    return (
      <TouchableOpacity onPress={() => onPressGoDetail(item)}>
        <View style={styles.card}>
          <View
            style={{
              alignItems: 'center',
              justifyContent: 'center',
              margin: 10,
            }}
          >
            <Image
              source={{ uri: item.imgUrl }} // 외부 이미지 URL 설정
              style={{ width: 100, height: 130, borderRadius: 15 }}
            />
          </View>
          <View>
            <Typography color={Color.WHITE}>{item.name}</Typography>
            <Typography
              color={Color.LIGHT_GRAY}
              fontSize={10}
            >
              {item.description}
            </Typography>
          </View>
          {groupMemberRole != 'MANAGER' ? null : (
            <TouchableOpacity onPress={() => onPressDelete(item.name, item.productId)}>
              <View>
                <Ionicons
                  name={'trash'}
                  size={20}
                  color={Color.WHITE}
                />
              </View>
            </TouchableOpacity>
          )}
        </View>
      </TouchableOpacity>
    )
  }
  const styles = StyleSheet.create({
    commonItem: { paddingHorizontal: 10 },
    card: {
      backgroundColor: Color.LIGHT_BLUE,
      width: 150,
      height: 250,
      borderRadius: 18,
      margin: 10,
      marginVertical: 20,
      justifyContent: 'space-evenly',
      alignItems: 'center',
    },
    emptyCard: {
      backgroundColor: Color.WHITE,
      width: 150,
      height: 250,
      borderRadius: 18,
      margin: 10,
      marginVertical: 20,
      justifyContent: 'center',
      alignItems: 'center',
    },
  })

  //예약 현황 조회
  //날짜, 대여물품 id 입력 => 해당 날짜의 예약 테이블을 확인하여, 대여물품의 id가 같으며 승인여부가 true인 데이터가 있는지 확인 => 있다면 예약 불가임

  //예약 신청 요청
  //날짜, 대여물품 id, 유저 id 입력 => 해당 날짜의 대여물품 id의 대여결과 칼럼 확인

  //예약 승인
  //해당 예약 id => 실제 예약일, 물품 아이디와 동일하며 승인여부가 true인 데이터가 있는지 확인 => 없을 경우 최종 승인 처리

  // 관리자용 예약 전체 리스트 조회
  //물품명
  //유저명
  //유저 이메일
  //실제 예약일

  // 관리자용 대여 중 전체 리스트 조회
  //예약테이블 중에서 반납확인이 false이고 승인여부

  return (
    <View style={{ flex: 1, backgroundColor: Color.WHITE }}>
      <View style={{ justifyContent: 'center' }}>
        <Spacer space={10} />
        <View
          style={{
            height: 50,
            justifyContent: 'center',
            borderRadius: 10,
          }}
        >
          <SelectSearchOption />
        </View>

        <Spacer space={5} />
        <View style={{ marginHorizontal: -15 }}>
          <Divider />
        </View>

        <Spacer space={10} />
        <FlatList
          data={productListData.dataBody}
          renderItem={renderItem}
          keyExtractor={(item, index) => index.toString()}
        />
      </View>
      <DeleteModal
        isModalVisible={isModalVisible}
        selectedItemId={selectedItemId}
        selectedItemName={selectedItemName}
        setIsModalVisible={setIsModalVisible}
        option={'물품'}
      />
    </View>
  )
}
