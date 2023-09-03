# Color Palette

## Color Palette

- 모든 ui/ux는 다음 컬러 팔레트 내에 있는 색으로만 이루어져있습니다.

![Untitled](Color%20Pallette%20f7c0b5ee6250482ab5ed5be71e83fd46/Untitled.png)

- 컬러 코드는 HexCode를 사용합니다.
- src/components/Color/color.js에 저장된 상수를 사용합니다.
    
    ```jsx
    // 파란색 계열
    export const GRAY_BLUE = "#f1f3f8";
    export const LIGHT_BLUE = "#a1cffd";
    export const DARK_BLUE = "#0e62df";
    export const BLUE = "#2d94fb";
    
    // 단색 계열
    export const WHITE = "#ffffff";
    export const LIGHT_GRAY = "#f2f2f8";
    export const GRAY = "#919195";
    export const BLACK = "#000000";
    ```
    
- 컬러 팔레트를 사용하는 예시 코드는 다음과 같습니다

### import  하기

```jsx
import * as Color from "../components/Colors/colors";
```

- 코드의 가독성을 위해

### 원하는 색 넣어주기

```jsx
<View
      style={{
        height: 210,
        width: 140,
        borderRadius: 10,
        marginHorizontal: 5,
      }}
      **backgroundColor={Color.LIGHT_BLUE}**
/>
```

- 위 코드를 적용하면 다음과 같은 컴포넌트가 렌더링 됩니다.

![Untitled](Color%20Pallette%20f7c0b5ee6250482ab5ed5be71e83fd46/Untitled%201.png)
