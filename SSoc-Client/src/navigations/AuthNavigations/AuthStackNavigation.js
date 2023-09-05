import { createNativeStackNavigator } from '@react-navigation/native-stack';
import LoginScreen from '../../screens/AuthScreen/LoginScreen';
import RegisterScreen from '../../screens/AuthScreen/RegisterScreen';
import RegisterSuccessScreen from '../../screens/AuthScreen/RegisterSuccessScreen';
import SchoolEmailScreen from '../../screens/AuthScreen/SchoolEmailScreen';
import SplashScreen from '../../screens/AuthScreen/SplashScreen';

const Stack = createNativeStackNavigator();
const AuthStackNavigation = () => {
  return (
    <Stack.Navigator initialRouteName="SplashScreen">
      <Stack.Screen
        name="SplashScreen"
        component={SplashScreen}
        options={{ headerShown: false }}
      />
      <Stack.Screen
        name="SchoolEmail"
        component={SchoolEmailScreen}
        options={{ headerShown: false }}
      />
      <Stack.Screen
        name="Register"
        component={RegisterScreen}
        options={{ headerShown: false }}
      />
      <Stack.Screen
        name="Login"
        component={LoginScreen}
        options={{ headerShown: false }}
      />
      <Stack.Screen
        name="RegisterSuccess"
        component={RegisterSuccessScreen}
        options={{ headerShown: false }}
      />
    </Stack.Navigator>
  );
};
export default AuthStackNavigation;
