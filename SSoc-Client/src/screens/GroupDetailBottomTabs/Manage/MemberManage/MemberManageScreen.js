import React, {useCallback, useState} from "react"
import { FlatList, TouchableOpacity, View, StyleSheet } from "react-native"
import { Divider } from "../../../../components/Basic/Divider"
import { ProfileImage } from "../../../../modules/ProfileImage"
import { Typography } from "../../../../components/Basic/Typography"
import * as Color from "../../../../components/Colors/colors"


const mockResponse = {
    "dataHeader": {
        "successCode": 0,
        "resultCode": null,
        "resultMessage": null
    },
    "dataBody": [
        {
            "groupSignupId": 1,
            "groupId": 1,
            "userEmail": "dohun@inha.co.kr",
            "userName": "도훈",
            "userNickname": "도훈이 테스트",
            "userImgUrl": "https://images.pexels.com/photos/11897873/pexels-photo-11897873.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            "role": "USER",
            "signupStatus": false
        },
        {
            "groupSignupId": 2,
            "groupId": 1,
            "userEmail": "donggeun@yonsei.co.kr",
            "userName": "동근",
            "userNickname": "동근이 테스트",
            "userImgUrl": "https://images.pexels.com/photos/11897873/pexels-photo-11897873.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            "role": "USER",
            "signupStatus": false
        },
        {
            "groupSignupId": 3,
            "groupId": 1,
            "userEmail": "hanju@yonsei.co.kr",
            "userName": "한주",
            "userNickname": "한주 테스트",
            "userImgUrl": "https://images.pexels.com/photos/11897873/pexels-photo-11897873.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            "role": "USER",
            "signupStatus": false
        },
        {
            "groupSignupId": 4,
            "groupId": 1,
            "userEmail": "dohun@inha.co.kr",
            "userName": "도훈",
            "userNickname": "도훈이 테스트",
            "userImgUrl": "https://images.pexels.com/photos/11897873/pexels-photo-11897873.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            "role": "USER",
            "signupStatus": false
        },
        {
            "groupSignupId": 5,
            "groupId": 1,
            "userEmail": "dohun@inha.co.kr",
            "userName": "도훈",
            "userNickname": "도훈이 테스트",
            "userImgUrl": "https://images.pexels.com/photos/11897873/pexels-photo-11897873.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            "role": "USER",
            "signupStatus": false
        },
        {
            "groupSignupId": 6,
            "groupId": 1,
            "userEmail": "dohun@inha.co.kr",
            "userName": "도훈",
            "userNickname": "도훈이 테스트",
            "userImgUrl": "https://images.pexels.com/photos/11897873/pexels-photo-11897873.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            "role": "USER",
            "signupStatus": false
        },
        {
            "groupSignupId": 7,
            "groupId": 1,
            "userEmail": "dohun@inha.co.kr",
            "userName": "도훈",
            "userNickname": "도훈이 테스트",
            "userImgUrl": "https://images.pexels.com/photos/11897873/pexels-photo-11897873.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            "role": "USER",
            "signupStatus": false
        },
        {
            "groupSignupId": 8,
            "groupId": 1,
            "userEmail": "dohun@inha.co.kr",
            "userName": "도훈",
            "userNickname": "도훈이 테스트",
            "userImgUrl": "https://images.pexels.com/photos/11897873/pexels-photo-11897873.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            "role": "USER",
            "signupStatus": false
        },
        {
            "groupSignupId": 9,
            "groupId": 1,
            "userEmail": "dohun@inha.co.kr",
            "userName": "도훈",
            "userNickname": "도훈이 테스트",
            "userImgUrl": "https://images.pexels.com/photos/11897873/pexels-photo-11897873.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            "role": "USER",
            "signupStatus": false
        },
        {
            "groupSignupId": 10,
            "groupId": 1,
            "userEmail": "dohun@inha.co.kr",
            "userName": "도훈",
            "userNickname": "도훈이 테스트",
            "userImgUrl": "https://images.pexels.com/photos/11897873/pexels-photo-11897873.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            "role": "USER",
            "signupStatus": false
        },
    ]
}

export const MemberManageScreen = ()=>{
    const [requests, setRequests] = useState(mockResponse["dataBody"]); 

    const onPressRejcet = useCallback((groupSignupId, groupId)=>{
        console.log("거절", groupSignupId); 
    })


    return(

        <View style={{flex:1}}>
        <FlatList
                style={styles.commonItem}
                contentContainerStyle={{ paddingBottom: 30 }}
                data={requests}
                renderItem={({ item }) => {
                return(
                    <View>
                    <Divider/>
                    <View style ={{marginHorizontal:15, flexDirection:"row", justifyContent:"space-between", alignItems:"center"}}> 
                        <View style={{marginVertical:4 }} flexDirection={"row"}>
                                <View style={{alignItems:"center", justifyContent:"center"}}>
                                    <ProfileImage size={60} url={item.userImgUrl} />
                                </View>
                                <View >  
                                    <Typography fontSize={15}>{item.userName}</Typography>
                                    <Typography fontSize={12}>{item.userNickname}</Typography>
                                    <Typography fontSize={10} color={Color.GRAY}>{item.userEmail}</Typography>
                                </View> 
                            </View>
                            <View style={{flexDirection:"row"}}>
                                <TouchableOpacity onPress={()=>onPressRejcet(item.groupSignupId, item.groupId)}>
                                    <View style={{backgroundColor:Color.LIGHT_RED, width:100, height:40, borderRadius:10, justifyContent:"center", alignItems:"center", marginHorizontal: 5}}>
                                        <Typography fontSize={15} color={Color.WHITE}> 그룹에서 삭제 </Typography>
                                    </View>
                                </TouchableOpacity>
                            </View>

                           
                        </View>
                       
                    </View>
                )
                }}
        />
    </View>
    )
}

var styles = StyleSheet.create({
    commonItem : { paddingTop: 30, paddingHorizontal: 20 }
  });
