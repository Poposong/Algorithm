# PJT readme.md

# 1. 메인페이지
## 1-1) 로그인을 하지 않은 상태의 메인페이지
![image](/uploads/ad4ad3e1812f52bd61246ba737ce5b4a/image.png)
### → header tag에는 오늘의 뉴스, 공지사항, 로그인, 회원가입 기능을 포함하고 있고 main tag에서는 아파트의 매매 정보를 확인할 수 있는 select tag를 포함하고 있다.
***
## 1-2) 로그인을 한 상태의 메인페이지
![image](/uploads/c8fe8b9672c4354cb6e252afbc9d1666/image.png)
### → 로그인을 한 상태이므로 이전의 메인페이지 상태에서 로그인, 회원가입 버튼이 사라지고 로그아웃, 마이페이지 버튼이 생긴다.

<br><br><br>

# 2. 회원가입
![image](/uploads/ecff0c519850dfbfda30011cf1c52c4e/image.png)
## (1) 회원 정보를 입력함
#### 아이디 : ssafy1234
#### 비밀번호 : 1234
#### 이름 : 호모사피언스
#### 주소 : 서울특별시
#### 전화번호 : 010-1234-1234

<br>

## (2) 알림메세지
![image](/uploads/79cba2816f1531c9bf0bc019704b5869/image.png)

<br>

## (3) localStorage에 저장함
![image](/uploads/21013abb74f3387eeded95368a81580a/image.png)


<br><br><br>

# 3. 로그인/로그아웃
![image](/uploads/b607a3739a15eab0b27d485a62cb945d/image.png)
## (1) 회원 정보를 입력함
#### 아이디 : ssafy1234
#### 비밀번호 : 1234

<br>

## (2) 알림메세지
![image](/uploads/db856b6b99fccd9793c4104875653b26/image.png)

<br>

## (3) sessionStorage에 저장함
![image](/uploads/93b6138e40fd793b267abc3b0f81dc81/image.png)

<br><br><br>

# 4. 마이페이지(수정/삭제)
![image](/uploads/d148a3ce2bcfbe88811a962ecd065642/image.png)
### → [수정] 버튼을 통해 해당 유저의 정보를 수정해서 localStorage와 sessionStorage에 반영한다.
### → [삭제] 버튼을 통해서 sessionStorage와 localStorage에서 해당 유저의 정보만 삭제한다.

<br><br><br>

# 5. 지도
![image](/uploads/c4616b8d6d8e6b063aea19760d1b3387/image.png)
### 거주지 매매정보를 지도와 함께 볼 수 있다.
### 방법은 다음과 같다.
### 메인페이지에 시/도, 구/군, 동을 선택하는 탭과 '아파트매매정보' 버튼이 있다.
### 메인페이지에 시/도를 선택한다.
### 시/도를 선택하면 그 지역에 해당하는 구/군을 선택할 수 있다.
### 구/군을 선택하면 그 지역에 해당하는 동을 선택할 수 있다.
### 세 가지 옵션을 모두 선택한 뒤 '아파트매매정보'버튼을 클릭하면 아래에 지도와 매매정보가 나온다.

<br><br><br>

# 6. 지도 검색
![image](/uploads/d90eb682ae182f442ce2f751b00498ea/image.png)
### 매매정보를 누르면 지도가 해당 지역으로 이동이 된다.

### 지도는 위치기본값인 구미SSAFY를 보여주고 있고 옆에있는 매매정보는 거주지 이름과 매매가격이 있다.

### 지도는 '지도'와 '스카이뷰'를 지정할 수 있고, 지도에서 보여주는 범위를 지정할 수 있다.

### 확인하고싶은 매매정보를 클릭하면, 지도가 해당 매매정보의 도로명주소와 위치를 보여준다.
