import { createNativeStackNavigator } from "@react-navigation/native-stack"
import { MainBottomTabNavigation } from "./MainBottomTabNavigation";
import { StackDetailScreen } from "../../screens/StackDetailScreen";
import { SettingScreen } from "../../screens/MainBottomTabs/SettingScreen";
const Stack = createNativeStackNavigator(); 
export const RootStackNavigation = ()=>{

    return(
        <Stack.Navigator 
        screenOptions={{
            headerShown: false,
        }}>
            <Stack.Screen name="MainBottomTab" component={MainBottomTabNavigation} />
            <Stack.Screen name="StackDetailScreen" component={StackDetailScreen} />
            <Stack.Screen name="SettingScreen" component={SettingScreen} />
        </Stack.Navigator>
    )
}