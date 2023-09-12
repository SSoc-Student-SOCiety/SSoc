import { Typography } from "../../../components/Basic/Typography"
import { View } from "react-native"
import { StackHeader } from "../../../modules/StackHeader"
export const AddReceiptScreen = ()=>{
    return(
    <View style={{flex:1, alignItems:"center", justifyContent:"center"}}>
        <StackHeader title={"관리"}/>
        <Typography> 영수증 추가 페이지 </Typography>
    </View>)
}