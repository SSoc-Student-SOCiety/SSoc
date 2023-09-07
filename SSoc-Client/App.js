import { SafeAreaProvider } from 'react-native-safe-area-context'
import { RootApp } from './src/RootApp'
import { NavigationContainer } from '@react-navigation/native'
import { RecoilRoot } from 'recoil'
export default function App() {
  return (
    <RecoilRoot>
      <SafeAreaProvider>
        <NavigationContainer>
          <RootApp />
        </NavigationContainer>
      </SafeAreaProvider>
    </RecoilRoot>
  )
}
