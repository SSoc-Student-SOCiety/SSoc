import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { MainBottomTabNavigation } from "./MainBottomTabNavigation";
import { GroupDetailScreen } from "../../screens/GroupDetailScreen";
import { SettingScreen } from "../../screens/MainBottomTabs/SettingScreen";
const Stack = createNativeStackNavigator();
export const RootStackNavigation = () => {
  return (
    <Stack.Navigator
      screenOptions={{
        headerShown: false,
      }}
    >
      <Stack.Screen name="MainBottomTab" component={MainBottomTabNavigation} />
      <Stack.Screen name="GroupDetailScreen" component={GroupDetailScreen} />
      <Stack.Screen name="SettingScreen" component={SettingScreen} />
    </Stack.Navigator>
  );
};
