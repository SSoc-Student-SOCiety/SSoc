import { Image } from "react-native/";
import * as Color from "../components/Colors/colors";
export const ProfileImage = (props) => {
  console.log(props.size);
  return (
    <Image
      source={{
        uri: `https://picsum.photos/600`,
      }}
      style={{
        width: props.size,
        height: props.size,
        borderRadius: 100,
        marginRight: 7,
        borderColor: Color.LIGHT_GRAY,
        borderWidth: 0.3,
      }}
    />
  );
};
