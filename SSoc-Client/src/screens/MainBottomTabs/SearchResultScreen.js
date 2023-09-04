import { Typography } from "../../components/Basic/Typography"
import { View } from "react-native"
import { MainHeader } from "../../modules/MainHeader"
export const SearchResultScreen=()=>{


    return (
        <View>
            <MainHeader name={"김싸피"} url={"https://picsum.photos/600"} />
                <View style={{
                flex:1 , 
                alignItems: "center",
                justifyContent: "center",}}>
                <Typography fontSize={12}>Searh Result </Typography>
             </View>
        </View>
        
    )
}