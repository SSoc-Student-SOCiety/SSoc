import { View } from "react-native"
import { Typography } from "../components/Basic/Typography"
import { Spacer } from "../components/Basic/Spacer"
import { Icon } from "../components/Icons/Icons"
import * as Color from "../components/Colors/colors"
export const SearchButton =(props)=>{
    return (
        <View
            backgroundColor={props.color}
            borderRadius={10}
            style={{
            alignSelf: "stretch",
            height: 50,
            alignItems: "center",
            justifyContent: "center",
            flexDirection: "row",
            }}
        >
            <Typography fontSize={16} color={Color.WHITE}>
                {props.title}
            </Typography>
            <Spacer space={10} horizontal={true} />
            {/* "airplane-outline" */}
            {props.isIcon ? <Icon name={props.iconName} size={15} color={Color.WHITE} />: <></> }
        </View>
    )
}
