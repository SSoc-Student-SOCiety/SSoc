# SSoc
<img width="834" alt="image" src="https://github.com/SSoc-Student-SOCiety/SSoc/assets/119592507/7259b14b-1332-407c-b4cb-42c48948e517">

## 📸 Summary 
핸드폰으로 편리하게 소속 동아리/학생회 공금 사용 내역을 확인하고 교류할 수 있는 커뮤니티 서비스 



### 📚 Tech Stack 
- BE : JAVA, Spring boot, MySQL,  Spring Data JPA, Query DSL, Spring Security, Spring Batch
APP
- APP : React Native, ES6, Recoil 
Deploy (ci/cd)
- 서버 배포 : Docker, Github Actions , Oracle Cloud Instance
- 앱 배포    :  Code push, Fast Lane

## ⭐️  기능 
<img width="831" alt="image" src="https://github.com/SSoc-Student-SOCiety/SSoc/assets/119592507/1f5f2406-788d-41ed-87af-b3c320904cce">


## 📄 ERD
<img width="615" alt="image" src="https://github.com/SSoc-Student-SOCiety/SSoc/assets/119592507/b6e65a45-d9ff-42d2-94d9-9829e048075e">


### 🔍 기능 명세 
1. 계좌 내역 연동을 통한 결산 내역 작성 및 자동 공시 기능 
    - 계좌 1원 인증 api를 통해 신한은행 계좌와 웹사이트 내 학생회/동아리 관리 계정 연동 
    - 매일 결산 내역을 배치를 통해 자동으로 DB에 저장
    - DB 내 결산 내역을 토대로 추가적인 내용 작성 및 자동 공시

2. 예산안 업로드 기능
    - 네이버 클라우드 ocr api 이용, 기존의 예산안 이미지/엑셀을 업로드하면 텍스트 변환 
    - 기본으로 제공되는 예산안 틀에 맞춰 예산안 작성 가능

3. 결산안/ 예산안 엑셀 다운로드 기능 
    - POI 또는 JXL 라이브러리 활용하여 db 내 결산안/예산안 내역 엑셀 다운로드 기능 제공

4. 학생회 및 동아리 활동을 돕기 위한 커뮤니티 기능
   - 공지 및 소속원 게시판
   - 건의함
   - 일정 캘린더
   - 물품 대여

5. api를 활용한 보조 기능
   -  이체 api를 이용한 mt 비용, 회식 비용 등 정산
   -  앱 푸시나 SNS api를 이용한 데일리 결산, 행사 및 공지 알림

6. JWT 기반 로그인, 로그아웃 기능 구현
   - 학교 이메일과 계좌 인증을 통한 본인 인증
   - Spring Security와 JWT를 이용한 로그인 , 로그아웃

<br>


## 🧑‍🤝‍🧑 팀원 소개

|  ![image](https://user-images.githubusercontent.com/49369306/195608027-5633bd06-1c29-4916-bf75-65567de3b2a5.png)   | ![8A47FFE3-01CC-4D5D-BFEF-2442C199 (1)](https://user-images.githubusercontent.com/101342819/195843132-6ac73804-173b-42a2-82fd-46abbe4fadb4.jpg)     |   ![imoge](https://user-images.githubusercontent.com/49369306/195610304-6a7a322e-ffc0-491b-a56b-3bae9ff83c2e.jpg)  |  ![ldh](https://avatars.githubusercontent.com/u/87813831?v=4)    | 
| :---------------------------------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------: |
|Front-End|Front-End|Back-End|Back-End|Back-End|Back-End|
| [김수린](https://github.com/younjaewon) | [김한주](https://github.com/onLuke) | [신동근](https://github.com/malslapq) | [이도훈](https://github.com/Dokuny) |



## 🏢Architecture
![프로젝트아키텍처](https://github.com/SSoc-Student-SOCiety/SSoc/assets/87813831/f73472d9-97e8-4a66-816c-0aeee181d2aa)
