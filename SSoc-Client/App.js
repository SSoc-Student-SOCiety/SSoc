import { SafeAreaProvider } from "react-native-safe-area-context";
import { RootApp } from "./src/RootApp";
import { SettingScreen } from "./src/screens/MainBottomTabs/SettingScreen";
import {SearchResultScreen} from "./src/screens/MainBottomTabs/SearchResultScreen"
import { NavigationContainer } from "@react-navigation/native";
export default function App() {
  return (
    <SafeAreaProvider>
      <NavigationContainer><RootApp/></NavigationContainer>
       
    </SafeAreaProvider>
  );
}
