import { useState } from "react";
import { MainScreen } from "./screens/MainScreen";
import { SplashView } from "./SplashView";
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
    <MainScreen />
  );
};