import { Alert, StyleSheet, TouchableOpacity, View } from "react-native";
import { Button } from "../components/Basic/Button";
import * as Color from "../components/Colors/colors";
import { Icon } from "../components/Icons/Icons";
import * as ImagePicker from "expo-image-picker";
import { useCallback, useState } from "react";
import { UserInfoState } from "../util/RecoilUtil/Atoms";
import { useRecoilState } from "recoil";
import storage from "@react-native-firebase/storage";
import { useEffect } from "react";
import { getChangeProfileFetch } from "../util/FetchUtil";
import { getTokens, setTokens } from "../util/TokenUtil";
import { getAsync } from "../util/AsyncUtil";
const SettingImgEditButton = (props) => {
  const [userInfo, setUserInfo] = useRecoilState(UserInfoState);

  // 권한 요청을 위한 hooks
  const [status, requestPermission] = ImagePicker.useMediaLibraryPermissions();

  const [accessToken, setAccessToken] = useState(null);
  const [refreshToken, setRefreshToken] = useState(null);
  const [isTokenGet, setIsTokenGet] = useState(false);
  useEffect(async () => {
    if (!isTokenGet) {
      await getTokens(setAccessToken, setRefreshToken, setIsTokenGet);
    }
  }, [isTokenGet]);

  const [profile, setProfile] = useState(userInfo.userImageUrl);

  const getChangeProfile = async () => {
    console.log("access", accessToken);
    console.log("refresh", refreshToken);
    try {
      const response = await getChangeProfileFetch(
        accessToken,
        refreshToken,
        userInfo.userEmail,
        profile
      );
      const data = await response.json();
      if (data.dataHeader != undefined) {
        if (data.dataHeader.successCode == 0) {
          setUserInfo({ ...userInfo, userImageUrl: profile });
          Alert.alert("프로필 이미지를 변경하였습니다.");
        } else if (
          data.dataHeader.successCode == 0 &&
          data.dataHeader.resultCode == 1
        ) {
          // 토큰 재발급
          setTokens(
            data.dataBody.token.accessToken,
            data.dataBody.token.refreshToken
          );
          Alert.alert("닉네임 변경에 실패했습니다.", "다시 시도해주세요.");
        } else {
          Alert.alert(data.dataHeader.resultMessage);
        }
      } else {
        Alert.alert("회원정보 변경에 실패했습니다.");
      }
    } catch (e) {
      console.error(e);
    }
  };
  // const getChangeProfileFetch = async (
  //   acessToken,
  //   refreshToken,
  //   userEmail,
  //   userImage
  // )
  const onPressEditImage = useCallback(async () => {
    // 이미지 접근 권한 관련 요청
    if (!status?.granted) {
      const permission = await requestPermission();
      if (!permission.granted) {
        return null;
      }
    }

    const result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.Images,
      allowsEditing: false,
      quality: 0.3,
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
        setProfile(downloadUrl);
        getChangeProfile();
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
