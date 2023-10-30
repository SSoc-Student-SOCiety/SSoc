# SSoc
<img width="834" alt="image" src="https://github.com/SSoc-Student-SOCiety/SSoc/assets/119592507/7259b14b-1332-407c-b4cb-42c48948e517">

## 📸 Summary 
핸드폰으로 편리하게 소속 동아리/학생회 공금 사용 내역을 확인하고 교류할 수 있는 커뮤니티 서비스 


<br>

## 📚 Tech Stack 
- BE : JAVA, Spring boot, MySQL,  Spring Data JPA, Query DSL, Spring Security, Spring Batch
APP
- APP : React Native, ES6, Recoil 
Deploy (ci/cd)
- 서버 배포 : Docker, Github Actions , Oracle Cloud Instance
- 앱 배포    :  Code push, Fast Lane

<br>


## ⭐️  기능 
<img width="831" alt="image" src="https://github.com/SSoc-Student-SOCiety/SSoc/assets/119592507/1f5f2406-788d-41ed-87af-b3c320904cce">


<br>


## 🔍 기능 명세
### 1. 계좌 내역 연동을 통한 거래 내역 자동 공시 기능
   ![ezgif com-resize (4)](https://github.com/SSoc-Student-SOCiety/SSoc/assets/119592507/ff998917-3ed3-43c5-b468-e32945701bc4)
    - 신한 은행 계좌 1원 인증 api를 이용해 금융 계좌와 서비스 내 학생회/동아리 관리 계정 연동
    - 신한 은행 거래내역 조회 api를 활용해 매일 전날의 거래 내역을 배치를 통해 DB에 저장
    - 거래내역 자체가 저장되어 수정되지 않으므로 신뢰성 높은 자료 제공 및 거래 내역에 대한 추가 설명 작성 가능

### 2. 보기 쉬운 일간, 월간 거래 금액 통계 자료 제공
   
   ![9팀_싸광_정산안-모아보기](https://github.com/SSoc-Student-SOCiety/SSoc/assets/119592507/a5da39f1-b96c-4eda-bd24-ae28418a614f)
    
    - 매일, 매월 배치를 통해 거래 금액을 집계하여 지입,지출을 한 눈에 파악할 수 있도록 통계 자료 제공
    - 배치 시, 대량의 쿼리 발생 가능성으로 JDBC Batch Insert 및 대량의 데이터 접근을 위한 No Offset 페이징 사용

### 3. 학생회 및 동아리 활동을 돕기 위한 커뮤니티 기능
   - 공지 및 소속원 게시판
     
     ![ezgif com-resize (3)](https://github.com/SSoc-Student-SOCiety/SSoc/assets/119592507/643e7703-9f63-4990-aa9f-fb378c75c48b)

     

   - 물품 대여
     
     ![ezgif com-resize (5)](https://github.com/SSoc-Student-SOCiety/SSoc/assets/119592507/e8ad176e-b45d-4471-8e0b-c7609b18dfd3)



### 4. JWT 기반 로그인, 로그아웃 기능 구현

   ![9팀_싸광_회원가입_로그인](https://github.com/SSoc-Student-SOCiety/SSoc/assets/119592507/b71b007b-5a48-4095-b006-fae060706964)
   - 학교 이메일과 계좌 인증을 통한 본인 인증
   - Spring Security와 JWT를 이용한 로그인 , 로그아웃
  


<br>



## 🧑‍🤝‍🧑 팀원 소개

|  ![image](https://avatars.githubusercontent.com/u/51315222?v=4)   | ![8A47FFE3-01CC-4D5D-BFEF-2442C199](https://avatars.githubusercontent.com/u/119592507?v=4)     |   ![imoge](https://avatars.githubusercontent.com/u/42789808?v=4)  |  ![ldh](https://github.com/SSoc-Student-SOCiety/SSoc/assets/87813831/937c6e7a-cbb5-44c3-8dc0-4af4094842ac)    | 
| :---------------------------------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------: |
|Front-End|Front-End|Back-End|Back-End|Back-End|Back-End|
| [김수린](https://github.com/uhyeon19) | [김한주](https://github.com/dev1week) | [신동근](https://github.com/shindonggeun) | [이도훈](https://github.com/Dokuny) |


<br>


## 🏢Architecture

![프로젝트아키텍처](https://github.com/SSoc-Student-SOCiety/SSoc/assets/87813831/f73472d9-97e8-4a66-816c-0aeee181d2aa)

<br>


## 🎛️ ERD
<img width="1215" alt="image" src="https://github.com/SSoc-Student-SOCiety/SSoc/assets/87813831/0ebea72d-da68-40f9-8fc2-ae9ba041d7ab">

