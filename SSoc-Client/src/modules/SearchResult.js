import { View } from "react-native"
import { Divider } from "../components/Basic/Divider"
import { Typography } from "../components/Basic/Typography"
import { Spacer } from "../components/Basic/Spacer"
import { ProfileImage } from "./ProfileImage"
import { TouchableOpacity } from "react-native"
import { useNavigation } from "@react-navigation/native"
import * as Color from "../components/Colors/colors"
export const SearchResult = (props)=>{
    const navigation = useNavigation();
    const onPressDetail =()=>{
      console.log("test");
      navigation.navigate("StackDetailScreen");
    }
    return (
        <TouchableOpacity onPress={onPressDetail}>
        <View>
             <Divider/>
             <View style ={{marginHorizontal:10}}> 
                <View style={{marginVertical:4 }} flexDirection={"row"}>
                    <View style={{alignItems:"center", justifyContent:"center"}}>
                        
                        <ProfileImage size={60} url={props.item.url} />
                    </View>
                    <View>  
                        <Typography fontSize={15}>{props.item.title}</Typography>
                        <Typography fontSize={12}>{props.item.school}</Typography>
                        <Typography fontSize={10} color={Color.GRAY}>{props.item.subtitle}</Typography>
                        <Spacer space={14}/>
                        <Typography fontSize={9} color={Color.GRAY}>{props.item.updatedAt}</Typography>
                        <Spacer space={14}/>
                    </View> 
                    </View>
                </View>
        </View>
        </TouchableOpacity>
    )
}