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
                            학생회 / 동아리 공금 현황 보러가기
                            </Typography>
                            <Spacer space={10} horizontal={true} />
                            <Icon name="airplane-outline" size={15} color={Color.WHITE} />
                        </View>
    )
}
