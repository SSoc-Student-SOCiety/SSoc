import { useState, useEffect } from 'react'
import { View, Text, Alert, TouchableOpacity } from 'react-native'
import { Button } from '../../components/Basic/Button'
import { Spacer } from '../../components/Basic/Spacer'
import { Typography } from '../../components/Basic/Typography'
import * as Color from '../../components/Colors/colors'
import { getEmailAuthCodeFetch } from '../../util/FetchUtil'

const SchoolEmailInput = (props) => {
  const userEmail = props.userEmail
  const [emailAuthCodeData, setEmailAuthCodeData] = useState(null)
  const [waitTime, setWaitTime] = useState(false)

  const tempData = {
    dataHeader: {
      successCode: 0,
      resultCode: null,
      resultMessage: null,
    },
    dataBody: {
      userEmail: 'tlsehdrms124@yonsei.ac.kr',
      emailCode: '5naH20s2',
    },
  }

  const onPressCheckEmail = () => {
    if (waitTime == false) {
      getEmailAuthCodeData()
      setWaitTime(true)
    } else {
      Alert.alert('아직 3분이 지나지 않았습니다.', '이메일 인증코드 발송은 3분에 한 번 가능합니다.')
    }
  }

  const getEmailAuthCodeData = async () => {
    try {
      const response = await getEmailAuthCodeFetch(userEmail)
      const data = await response.json()
      await setEmailAuthCodeData(tempData)
    } catch (e) {
      console.log(e)
    }
  }

  useEffect(() => {
    if (emailAuthCodeData != null) {
      setTimeout(() => {
        setWaitTime(false)
      }, 180000)
      if (emailAuthCodeData.dataHeader.successCode == 0) {
        Alert.alert('이메일 인증코드가 발송되었습니다.', '이메일을 확인해주세요.')
        props.onPressCheck()
        props.setEmailCode(emailAuthCodeData.dataBody.emailCode)
      } else {
        Alert.alert('잘못된 이메일입니다.')
      }
      setEmailAuthCodeData(null)
    }
  }, [emailAuthCodeData])

  return (
    <View>
      <Typography
        fontSize={16}
        color={Color.DARK_BLUE}
      >
        <Text style={{ fontWeight: 'bold' }}>학교 이메일</Text>
      </Typography>
      <Spacer space={4} />
      <View style={{ flex: 1, flexDirection: 'row', justifyContent: 'space-between' }}>
        <View
          backgroundColor={Color.GRAY}
          borderRadius={4}
          style={{
            flex: 4,
            height: 45,
            alignContent: 'stretch',
            alignItems: 'center',
            justifyContent: 'center',
            flexDirection: 'row',
          }}
        >
          <Typography
            fontSize={16}
            color={Color.BLACK}
          >
            {userEmail}
          </Typography>
        </View>
        <View style={{ marginLeft: 10, flex: 1 }}>
          <Button>
            <TouchableOpacity onPress={onPressCheckEmail}>
              <View
                backgroundColor={Color.BLUE}
                borderRadius={10}
                style={{
                  height: 45,
                  alignItems: 'center',
                  justifyContent: 'center',
                  flexDirection: 'row',
                }}
              >
                <Typography
                  fontSize={14}
                  color={Color.WHITE}
                >
                  메일인증
                </Typography>
              </View>
            </TouchableOpacity>
          </Button>
        </View>
      </View>
    </View>
  )
}

export default SchoolEmailInput
