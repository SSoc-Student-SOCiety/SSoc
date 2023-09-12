import { Typography } from "../../../components/Basic/Typography"
import { View } from "react-native"
import { StackHeader } from "../../../modules/StackHeader"
export const MemberManageScreen = ()=>{
    return(
    <View style={{flex:1, alignItems:"center", justifyContent:"center"}}>
        <StackHeader title={"관리"}/>
        <Typography> 신청 및 그룹원 관리 </Typography>
    </View>)
}