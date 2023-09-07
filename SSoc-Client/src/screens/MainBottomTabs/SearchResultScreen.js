import { Typography } from "../../components/Basic/Typography"
import { ScrollView, View, StyleSheet, FlatList } from "react-native"
import { MainHeader } from "../../modules/MainHeader"
import { SingleLineInput } from "../../components/Input/SingleLineInput"
import * as Color from "../../components/Colors/colors"
import { Icon } from "../../components/Icons/Icons"
import React, { useCallback, useState } from "react"
import { Button } from "../../components/Basic/Button"
import { SearchResult } from "../../modules/SearchResult"
import { TouchableOpacity } from "react-native"
import { Spacer } from "../../components/Basic/Spacer"
import {SearchOptionCategoryScroll} from "../../modules/SearchOptionCategoryScroll"
import { Divider } from "../../components/Basic/Divider"
import { SearchButton } from "../../modules/SearchButton"
export const SearchResultScreen=()=>{


    return (
        <View style={{flex:1 }} backgroundColor={Color.WHITE}>
            <MainHeader name={"김싸피"} url={"https://picsum.photos/600"} />
            <View style={styles.commonItem}   >

                 <SearchOptionCategoryScroll />
                 
                <View style={{marginHorizontal:20, marginVertical:10}}>
                    <View backgroundColor={Color.LIGHT_GRAY} borderRadius={10} marginVertical={10}>
                        <SingleLineInput
                            placeholder="✎) ex. 00대학교 총학생회"
                        />
                    </View>          
                    <TouchableOpacity>
                        <SearchButton color={Color.BLUE} title={"학생회 / 동아리 공금 현황 보러가기"} iconName={"airplane-outline" } isIcon={true}/>

                    </TouchableOpacity>
                </View>
            </View>
            

            <View style={styles.commonItem}>
                <View style={{paddingHorizontal:20}}>
                    <Typography fontSize={15}>What's new</Typography>    
                </View>
            </View>

            {/* to-do 무한 스크롤 구현하기 */}
            <FlatList
                style={styles.commonItem}
                data={IMAGE_LIST}
                renderItem={({ item }) => {
                return(
                    <Button >
                         <SearchResult item={item}></SearchResult>
                    </Button>
                )
                }}
            />
                        
        </View>
        
    )
}

var styles = StyleSheet.create({
    mainItem: { paddingTop: 60, paddingHorizontal: 20,  },
    searchBar: { paddingTop: 30, paddingHorizontal: 20, backgroundColor:Color.GRAY},
    commonItem : { paddingTop: 30, paddingHorizontal: 20 }
  });





const IMAGE_LIST = [
    {name : "Enactus", school: "신한대학교", subName:"신한대학교 사회혁신 경영학회", val:"3",unit:"days", url: "https://images.pexels.com/photos/11860376/pexels-photo-11860376.jpeg?auto=compress&cs=tinysrgb&w=1600&lazy=load"},
    {name : "K.U.D.T", school: "신한대학교", subName:"신한대학교 사회혁신 경영학회", val:"3",unit:"days",url:"https://images.pexels.com/photos/14268955/pexels-photo-14268955.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"},
    {name : 1, school: "신한대학교", subName:"신한대학교 사회혁신 경영학회", val:"3",unit:"days",url:"https://images.pexels.com/photos/11897873/pexels-photo-11897873.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"},
    {name : 2, school: "신한대학교", subName:"신한대학교 사회혁신 경영학회", val:"3",unit:"days",url:"https://images.pexels.com/photos/13240319/pexels-photo-13240319.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"},
    {name : 4, school: "신한대학교", subName:"신한대학교 사회혁신 경영학회", val:"3",unit:"days",url:"https://images.pexels.com/photos/12290139/pexels-photo-12290139.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"},
    {name : 5, school: "신한대학교", subName:"신한대학교 사회혁신 경영학회", val:"3",unit:"days",url:"https://images.pexels.com/photos/10550714/pexels-photo-10550714.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"},
    {name : 6, school: "신한대학교", subName:"신한대학교 사회혁신 경영학회", val:"3",unit:"days",url:"https://images.pexels.com/photos/13350289/pexels-photo-13350289.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"},
    {name : 7, school: "신한대학교", subName:"신한대학교 사회혁신 경영학회", val:"3",unit:"days",url:"https://images.pexels.com/photos/14270535/pexels-photo-14270535.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"},
    {name : 8, school: "신한대학교", subName:"신한대학교 사회혁신 경영학회", val:"3",unit:"days",url:"https://images.pexels.com/photos/9420617/pexels-photo-9420617.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"},
    {name : 9, school: "신한대학교", subName:"신한대학교 사회혁신 경영학회", val:"3",unit:"days",url:"https://images.pexels.com/photos/12938821/pexels-photo-12938821.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"}
  ];
  