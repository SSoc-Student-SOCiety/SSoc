import { useState, useEffect } from "react";
import {
  TouchableOpacity,
  TouchableWithoutFeedback,
  View,
  Text,
  ScrollView,
  Alert,
} from "react-native";
import * as Color from "../../components/Colors/colors";
import { SingleLineInput } from "../../components/Input/SingleLineInput";
import { MultiLineInput } from "../../components/Input/MultiLineInput";
import CheckBox from "react-native-check-box";
import { KeyboardAwareScrollView } from "react-native-keyboard-aware-scroll-view";
import { getEditContentFetch, getWritePostFetch } from "../../util/FetchUtil";
import { getTokens } from "../../util/TokenUtil";
import { useNavigation } from "@react-navigation/native";

const EditContent = (props) => {
  const navigation = useNavigation();
  const content = props.content;
  const [isChecked, setIsChecked] = useState(
    content.nickname == "익명" ? true : false
  );
  const [inputTitle, setInputTitle] = useState(content.title);
  const [inputContent, setInputContent] = useState(content.content);

  const [accessToken, setAccessToken] = useState(null);
  const [refreshToken, setRefreshToken] = useState(null);
  const [isTokenGet, setIsTokenGet] = useState(false);

  const getEditContentData = async () => {
    try {
      if (inputTitle.length == 0 || inputContent.length == 0) {
        Alert.alert("제목과 내용을 필수로 입력해주세요.");
        return;
      }
      const response = await getEditContentFetch(
        accessToken,
        refreshToken,
        content.postId,
        inputTitle,
        inputContent,
        content.category,
        isChecked
      );
      const data = await response.json();
      if (data != null) {
        if (data.dataHeader.successCode == 0) {
          Alert.alert(
            "게시글이 수정되었습니다.",
            navigation.goBack(),
            props.setReload(!props.reload)
          );
        } else {
          Alert.alert(
            data.dataHeader.resultMessage,
            props.setEditContent(false)
          );
        }
        return;
      }
      Alert.alert(
        "게시글 수정에 실패했습니다.",
        "알 수 없는 에러",
        props.setEditContent(false)
      );
    } catch (e) {
      console.log(e);
    }
  };

  useEffect(() => {
    if (!isTokenGet) {
      getTokens(setAccessToken, setRefreshToken, setIsTokenGet);
    }
  }, []);

  return (
    <>
      <TouchableOpacity
        onPress={() => {
          props.setEditContent(false);
        }}
        style={{
          flex: 1,
          justifyContent: "center",
          alignItems: "center",
          backgroundColor: "rgba(211, 211, 211, 0.5)",
        }}
      >
        <TouchableWithoutFeedback>
          <View
            style={{
              flexDirection: "column",
              width: "90%",
              backgroundColor: Color.WHITE,
              borderColor: Color.BLUE,
              borderWidth: 1,
              borderRadius: 20,
            }}
          >
            <View
              style={{
                backgroundColor: Color.BLUE,
                width: "100%",
                height: "12%",
                borderTopRightRadius: 15,
                borderTopLeftRadius: 15,
                alignItems: "center",
                justifyContent: "center",
              }}
            >
              <Text
                style={{ fontSize: 20, color: Color.WHITE, fontWeight: "bold" }}
              >
                게시글 수정
              </Text>
            </View>
            <KeyboardAwareScrollView>
              <View style={{ flexDirection: "column" }}>
                <View
                  style={{
                    flex: 1,
                    flexDirection: "row",
                    alignItems: "center",
                    paddingHorizontal: 10,
                    paddingVertical: 5,
                    borderColor: Color.GRAY,
                    borderWidth: 0.3,
                    borderBottomWidth: 2,
                    borderRadius: 5,
                    margin: 10,
                    marginBottom: 0,
                  }}
                >
                  <View style={{ width: "20%", justifyContent: "center" }}>
                    <Text style={{ fontSize: 18 }}>제목: </Text>
                  </View>
                  <View
                    style={{
                      width: "80%",
                      backgroundColor: Color.LIGHT_GRAY,
                      borderRadius: 5,
                    }}
                  >
                    <SingleLineInput
                      value={inputTitle}
                      onChangeText={(text) => {
                        setInputTitle(text);
                      }}
                    />
                  </View>
                </View>

                <View
                  style={{
                    flex: 1,
                    flexDirection: "row",
                    alignItems: "center",
                    paddingHorizontal: 10,
                    borderColor: Color.GRAY,
                    borderWidth: 0.3,
                    borderBottomWidth: 2,
                    borderRadius: 5,
                    margin: 10,
                  }}
                >
                  <Text style={{ fontSize: 18 }}>익명 여부: </Text>
                  <CheckBox
                    style={{ flex: 1, padding: 10 }}
                    onClick={() => {
                      setIsChecked(isChecked ? false : true);
                    }}
                    isChecked={isChecked}
                  />
                </View>

                <View
                  style={{
                    flex: 1,
                    flexDirection: "row",
                    alignItems: "center",
                    paddingHorizontal: 10,
                    paddingVertical: 5,
                    borderColor: Color.GRAY,
                    borderWidth: 0.3,
                    borderBottomWidth: 2,
                    borderRadius: 5,
                    margin: 10,
                    marginBottom: 0,
                  }}
                >
                  <View style={{ width: "20%", justifyContent: "center" }}>
                    <Text style={{ fontSize: 18 }}>내용: </Text>
                  </View>
                  <View style={{ width: "80%" }}>
                    <MultiLineInput
                      fontSize={18}
                      value={inputContent}
                      onChangeText={(text) => {
                        setInputContent(text);
                      }}
                    />
                  </View>
                </View>
              </View>
            </KeyboardAwareScrollView>
            <View
              style={{
                flexDirection: "row",
                margin: 20,
                justifyContent: "space-around",
              }}
            >
              <TouchableOpacity
                onPress={() => {
                  props.setEditContent(false);
                }}
              >
                <View
                  style={{
                    padding: 15,
                    backgroundColor: Color.BLUE,
                    borderRadius: 10,
                  }}
                >
                  <Text style={{ fontSize: 18, color: Color.WHITE }}>
                    수정 취소
                  </Text>
                </View>
              </TouchableOpacity>
              <TouchableOpacity
                onPress={() => {
                  getEditContentData();
                }}
              >
                <View
                  style={{
                    padding: 15,
                    backgroundColor: Color.DARK_BLUE,
                    borderRadius: 10,
                  }}
                >
                  <Text style={{ fontSize: 18, color: Color.WHITE }}>
                    수정 하기
                  </Text>
                </View>
              </TouchableOpacity>
            </View>
          </View>
        </TouchableWithoutFeedback>
      </TouchableOpacity>
    </>
  );
};

export default EditContent;
