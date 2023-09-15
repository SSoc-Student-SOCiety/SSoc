import {
  DrawerLayoutAndroidBase,
  StyleSheet,
  TouchableOpacity,
  View,
} from "react-native";
import { Button } from "../components/Basic/Button";
import * as Color from "../components/Colors/colors";
import { Icon } from "../components/Icons/Icons";
import * as ImagePicker from "expo-image-picker";
import { useCallback, useState } from "react";
import { useImagePickAndUpload } from "../hooks/useImagePickAndUpload";
import { UserInfoState } from "../util/RecoilUtil/Atoms";
import { useRecoilState, useRecoilValue } from "recoil";
import { useCurrentDate } from "../util/hooks/useCurrentDate";
import database from "@react-native-firebase/database";
import storage from "@react-native-firebase/storage";

import { setStatusBarNetworkActivityIndicatorVisible } from "expo-status-bar";
const SettingImgEditButton = (props) => {
  const [userInfo, setUserInfo] = useRecoilState(UserInfoState);
  const imageUrl =
    props.userInfo.userImageUrl == null ? "" : props.userInfo.userImageUrl;
  const [newImageUrl, setNewImageUrl] = useState("");
  // 권한 요청을 위한 hooks
  const [status, requestPermission] = ImagePicker.useMediaLibraryPermissions();

  const onPressEditImage = useCallback(async () => {
    // 이미지 접근 권한 관련 요청
    if (!status?.granted) {
      const permission = await requestPermission();
      if (!permission.granted) {
        return null;
      }
    }

    // 이미지 가져오기 요청
    const imagePickResult = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.Images,
      allowsEditing: false,
      quality: 1,
      aspect: [1, 1],
    });

    const result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.Images,
      allowsEditing: false,
      quality: 1,
      aspect: [1, 1],
    });

    console.log(result.assets[0].uri);
    const cvtUri =
      Platform.OS === "ios"
        ? result.assets[0].uri.replace("file://", "")
        : result.assets[0].uri;
    console.log(cvtUri);

    const userDBPath = `/users/${userInfo.id}`;
    setUserInfo((prevState) => {
      return {
        ...prevState,
        userImageUrl: cvtUri,
      };
    });

    try {
      const uploadTaskResult = await storage().ref(userDBPath).putFile(cvtUri);

      if (uploadTaskResult.state === "success") {
        const downloadUrl = await storage()
          .ref(uploadTaskResult.metadata.fullPath)
          .getDownloadURL();

        console.log("다운로드 URL: ", downloadUrl);
      } else {
        console.log("파일 업로드 실패");
      }
    } catch (error) {
      console.error("파일 업로드 또는 URL을 생성하는 과정 중 오류 발생");
    }
  }, [userInfo]);

  return (
    <View style={styles.editImage}>
      <Button paddingHorizontal={5} paddingVertical={5}>
        <TouchableOpacity onPress={onPressEditImage}>
          <Icon name="brush" size={16} color={Color.WHITE} />
        </TouchableOpacity>
      </Button>
    </View>
  );
};

const styles = StyleSheet.create({
  editImage: {
    backgroundColor: Color.BLUE,
    borderRadius: 100,
    width: "25%",
    height: "25%",
    alignSelf: "flex-end",
  },
});

export default SettingImgEditButton;
