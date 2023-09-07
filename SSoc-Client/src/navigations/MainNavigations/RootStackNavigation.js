import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { MainBottomTabNavigation } from "./MainBottomTabNavigation";
import { GroupDetailScreen } from "../../screens/GroupDetailScreen";
import { SettingScreen } from "../../screens/MainBottomTabs/SettingScreen";
import { GroupDetailStackNavigation } from "../GroupDetailNavigations/GroupDetailStackNavigation";
const Stack = createNativeStackNavigator();
export const RootStackNavigation = ({ route }) => {
  console.log(route);
  return (
    <Stack.Navigator
      screenOptions={{
        headerShown: false,
      }}
    >
      <Stack.Screen name="MainBottomTab" component={MainBottomTabNavigation} />
      <Stack.Screen name="GroupDetailScreen" component={GroupDetailScreen} />
      <Stack.Screen
        name="GroupDetailTab"
        component={GroupDetailStackNavigation}
        options={route}
      />
      <Stack.Screen name="SettingScreen" component={SettingScreen} />
    </Stack.Navigator>
  );
};
