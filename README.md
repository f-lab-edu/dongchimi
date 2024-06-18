## 소개
<p align="center">
  <img src="https://github.com/f-lab-edu/dong-chi-mi/assets/51324045/f7cc15e4-8592-4eab-a3ec-15c346fe5ec9" alt="동치미logo" />
</p>

<h1 align="center">동치미</h1>

<h3 align="center">🏸 동네에서 취미를 갖고 싶을 때는 동치미 🏀</h3>

### 동치미란?
동치미는 '동네 친구들과 함께하는 미팅'의 의미를 지니는 용어입니다. 이는 동네에서 취미가 비슷한 사람들끼리 모여 함께 취미 생활을 즐기는 서비스입니다. 사용자는 플랫폼을 통해 자신의 취미를 공유하고, 같은 관심사를 가진 이웃과 연결될 수 있습니다. 
</br>
</br>
동치미 로고는 서로 다른 사람들 하나의 취미를 함께 하기 위해 한 곳에 모여 이루어진다는 의미를 가지도록 디자인 되었습니다.

### 프로젝트 정보
- [테이블 설계](https://github.com/f-lab-edu/dongchimi/wiki/%ED%85%8C%EC%9D%B4%EB%B8%94-%EC%84%A4%EA%B3%84)
- [USECASE](https://github.com/f-lab-edu/dongchimi/wiki/USECASE)
- [코드 컨벤션](https://github.com/f-lab-edu/dongchimi/wiki/%EC%BD%94%EB%93%9C-%EC%8A%A4%ED%83%80%EC%9D%BC)
- [Git 컨벤션](https://github.com/f-lab-edu/dongchimi/wiki/Git-%EC%BB%A8%EB%B2%A4%EC%85%98)


## 프로젝트 멀티 모듈 구조
### api 모듈
- 이 모듈은 각 서비스의 API 인터페이스를 정의합니다.
- 비즈니스 로직만을 포함하여 도메인과의 의존성을 최소화합니다.

### reactive 모듈
- 실시간 기능을 처리하는 모듈로, 채팅 및 알림 기능을 구현합니다.
- 비동기 처리와 이벤트 기반 아키텍처 사용을 중점적으로 다룹니다.

### consumer 모듈
- Message를 구독하는 Consumer들을 구현하는 모듈입니다.
- Redis, Kafaka 등 외부에서 전달 받은 Message를 활용

### domain 모듈
- 동치미에 구성된 모든 도메인 정보를 제공하는 모듈입니다.
![image](https://github.com/f-lab-edu/dong-chi-mi/assets/51324045/d93ea816-9f26-4537-a792-275492ced1c8)



## 아키텍처 구성도
![20240511_구성도](https://github.com/f-lab-edu/dong-chi-mi/assets/51324045/7f700826-d2e6-4504-8c6a-e0ac2f6f7e5e)



## 기술 소개
### 백엔드
![백엔드2](https://github.com/f-lab-edu/dong-chi-mi/assets/51324045/ebe18f8f-9643-46e1-96b0-4b321df5538c)









## 개발자
| Backend |
| ------- |
| <img src="https://github.com/stc9606.png" width="100" height="100"> |
| [노승철](https://github.com/stc9606) |
