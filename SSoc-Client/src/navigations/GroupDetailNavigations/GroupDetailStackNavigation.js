import { createNativeStackNavigator } from '@react-navigation/native-stack'
import { BoardDetailScreen } from '../../screens/GroupDetailBottomTabs/Board/BoardDetailScreen'
import { GroupDetailBottomTabNavigation } from './GroupDetailBottomTabNavigation'

const Stack = createNativeStackNavigator()
export const GroupDetailStackNavigation = ({ route }) => {
  const { tabName } = route.params

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

      <Stack.Screen
        name="BoardDetailScreen"
        component={BoardDetailScreen}
        options={{ tabName: tabName }}
      />
    </Stack.Navigator>
  )
}
