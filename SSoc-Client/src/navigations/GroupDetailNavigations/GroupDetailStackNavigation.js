import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { GroupDetailBottomTabNavigation } from "./GroupDetailBottomTabNavigation";

const Stack = createNativeStackNavigator();
export const GroupDetailStackNavigation = ({ route }) => {
  const { tabName } = route.params;
  console.log(tabName);
  return (
    <Stack.Navigator
      screenOptions={{
        headerShown: false,
      }}
    >
      <Stack.Screen
        name="GroupDetailBottomTab"
        component={GroupDetailBottomTabNavigation}
        options={{ tabName: tabName }}
      />
    </Stack.Navigator>
  );
};