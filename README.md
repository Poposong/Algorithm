# PJT readme.md

# 1. 메인페이지
## 1-1) 로그인을 하지 않은 상태의 메인페이지
![image](https://user-images.githubusercontent.com/81174840/226148949-7a2fdd39-e5af-4170-89e7-7b1bccc59f48.png)
### → header tag에는 오늘의 뉴스, 공지사항, 로그인, 회원가입 기능을 포함하고 있고 main tag에서는 아파트의 매매 정보를 확인할 수 있는 select tag를 포함하고 있다.
***
## 1-2) 로그인을 한 상태의 메인페이지
![image](https://user-images.githubusercontent.com/81174840/226148967-058bbabf-28a5-4346-93d8-e05063b5dc73.png)
### → 로그인을 한 상태이므로 이전의 메인페이지 상태에서 로그인, 회원가입 버튼이 사라지고 로그아웃, 마이페이지 버튼이 생긴다.

<br><br><br>

# 2. 회원가입
![image](https://user-images.githubusercontent.com/81174840/226148970-00f304e9-a384-4c4d-b8d1-df96117559e7.png)
## (1) 회원 정보를 입력함
#### 아이디 : ssafy1234
#### 비밀번호 : 1234
#### 이름 : 호모사피언스
#### 주소 : 서울특별시
#### 전화번호 : 010-1234-1234

<br>

## (2) 알림메세지
![image](https://user-images.githubusercontent.com/81174840/226148976-4dc9d5e0-aed0-462a-ba6a-3dd2ab1816dc.png)

<br>

## (3) localStorage에 저장함
![image](https://user-images.githubusercontent.com/81174840/226148979-8f929101-7e27-44dd-bc3b-c73e68e6b610.png)


<br><br><br>

# 3. 로그인/로그아웃
![image](https://user-images.githubusercontent.com/81174840/226148982-2cf1cd0c-63e6-43c6-8388-2119088e3894.png)
## (1) 회원 정보를 입력함
#### 아이디 : ssafy1234
#### 비밀번호 : 1234

<br>

## (2) 알림메세지
![image](https://user-images.githubusercontent.com/81174840/226148985-6881eb66-5447-4948-ab96-2c5c51b466b0.png)

<br>

## (3) sessionStorage에 저장함
![image](https://user-images.githubusercontent.com/81174840/226148986-d4d742a3-ed02-4883-a29b-ecb16f42d1ad.png)

<br><br><br>

# 4. 마이페이지(수정/삭제)
![image](https://user-images.githubusercontent.com/81174840/226148989-ab46f5c1-b838-4e0c-8ead-d04b50666784.png)
### → [수정] 버튼을 통해 해당 유저의 정보를 수정해서 localStorage와 sessionStorage에 반영한다.
### → [삭제] 버튼을 통해서 sessionStorage와 localStorage에서 해당 유저의 정보만 삭제한다.

<br><br><br>

# 5. 지도
![image](https://user-images.githubusercontent.com/81174840/226148993-289005a9-84b8-401b-a09a-361abcf3c856.png)
### 거주지 매매정보를 지도와 함께 볼 수 있다.
### 방법은 다음과 같다.
### 메인페이지에 시/도, 구/군, 동을 선택하는 탭과 '아파트매매정보' 버튼이 있다.
### 메인페이지에 시/도를 선택한다.
### 시/도를 선택하면 그 지역에 해당하는 구/군을 선택할 수 있다.
### 구/군을 선택하면 그 지역에 해당하는 동을 선택할 수 있다.
### 세 가지 옵션을 모두 선택한 뒤 '아파트매매정보'버튼을 클릭하면 아래에 지도와 매매정보가 나온다.

<br><br><br>

# 6. 지도 검색
![image](https://user-images.githubusercontent.com/81174840/226148999-2c50e5e8-b33e-4611-827d-1235cfb10d81.png)
### 매매정보를 누르면 지도가 해당 지역으로 이동이 된다.

### 지도는 위치기본값인 구미SSAFY를 보여주고 있고 옆에있는 매매정보는 거주지 이름과 매매가격이 있다.

### 지도는 '지도'와 '스카이뷰'를 지정할 수 있고, 지도에서 보여주는 범위를 지정할 수 있다.

### 확인하고싶은 매매정보를 클릭하면, 지도가 해당 매매정보의 도로명주소와 위치를 보여준다.
