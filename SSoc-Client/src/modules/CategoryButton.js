import { View } from "react-native"
import { Typography } from "../components/Basic/Typography"
import * as Color from "../components/Colors/colors"
import { TouchableOpacity } from "react-native"
export const CategoryButton =(props)=>{
    return (

            <View style={{
                width: 90, 
                height:40, 
                justifyContent:"center", 
                alignItems:"center", 
                marginHorizontal:5,
                borderColor:Color.GRAY,
                borderRadius:10
                }}   
                borderWidth ={props.isActivated ? 0:1} 
                backgroundColor ={props.isActivated ? Color.BLUE : Color.WHITE}
            > 
                <Typography fontSize={13} color={props.isActivated ?  Color.WHITE: Color.GRAY}>{props.title}</Typography>
            </View>
    
    )
}
