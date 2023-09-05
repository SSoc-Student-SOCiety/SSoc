import { useState } from 'react';
import { SplashView } from './SplashView';
import { RootStackNavigation } from './navigations/MainNavigations/RootStackNavigation';

export const RootApp = () => {
  const [initialized, setInitialized] = useState(false);

  if (!initialized) {
    return (
      <SplashView
        onFinishLoad={() => {
          setInitialized(true);
        }}
      ></SplashView>
    );
  }
  return (
    //추후 네비게이션 컨테이너 호출
    <RootStackNavigation />
  );
};
