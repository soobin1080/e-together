## [#1. Web/Mobile_웹기술 PJT : 웹 프론트엔드 / 백엔드 개발]

### [공통-Sub PJT II] 백엔드 구성 및 기본 기능 구현

#### 1. 소개

###### < 단체 장보기 서비스  "e-투계더" >

e-투계더는 emart 상품을 기반으로 워크샵, MT, 여행 등 단체로 장을 봐야 하는 상황에 카테고리 별로 필요한 상품을 보여주고 인원 별, 예산 별 상품을 추천해주는 서비스입니다. 선택한 상품의 가격을 계산해주고, 장보기 리스트를 저장할 수 있습니다.



#### 2. 환경

- Node.js
- Vue.js, Vue-cli, Vuetify
- Spring, Restful API
- Mysql Database
- Firebase
- IDE: Visual Studio Code, Spring Tool Suite



#### 3. 프로젝트 진행 사항

- User, Product API 도출
- Database 테이블 생성

- 회원가입/로그인 페이지 & 기능 구현
- 메인 페이지 구현
- 장보기 페이지 구현
- 웹 사이트 크롤링 - Database 저장
- 장보기 페이지 기능 구현 - 카테고리 탭, 검색 기능, 페이징 기능



#### 4. Restful API 

###### 	User API

- `POST /regi` : 회원가입
- `POST /emailCheck` : 이메일 중복체크
- `POST /login` : 로그인
- `POST /logout` : 로그아웃
- `POST /pwdCheck` : 내 정보 확인 시 비밀번호 체크
- `POST /myselfDetail/{email}` : 현재 로그인 되어있는 사용자의 상세정보 보기
- `POST /findEmail` : 이메일 찾기
- `POST /findPwd` : 비밀번호 찾기 및 임시 비밀번호 발급
- `POST /updateMyself` : 현재 로그인된 사용자의 정보 수정
- `POST /updatePwd` : 현재 로그인된 사용자의 비밀번호 수정
- `POST /deleteMyself` : 탈퇴
- `GET /findAllUsers` : 전체 회원 정보 리스트 반환
- `GET /fingUserByEmail/{email}` : 이메일로 회원 검색
- `POST /auth/signin` : 권한을 가지고 로그인
- `POST /auth/signup` : 권한을 가지고 회원가입



###### 	Product API

- `GET /product` : 상품 목록 표시
- `GET /product/{keyword}` : 전체 상품에서 상품명으로 상품 검색
- `GET /product_ctg/{category}` : 카테고리 별로 상품 표시
- `GET /product_ctg/{category}/{keyword}` : 카테고리 내에서 상품 검색



------

