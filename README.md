
![logo png](https://github.com/heejin9/gulbihouse-project/assets/152829380/07ec77c4-c2d8-464f-b084-dfca516381ea)

### 프로젝트 개요![Uploading logo.png.png…]()


### 💡 프로젝트 주제 및 선정 배경

냉장고 공유 관리 시스템은 사용자가 냉장고 속 식재료를 효율적으로 관리하고 공유할 수 있도록 돕는 프로젝트입니다. 1인 가구 및 바쁜 현대인들이 식재료를 신선하게 관리하고 낭비를 줄이는 데 중점을 두고 있습니다.

### 📌 기획 의도

**기존 서비스와 차별점**

- **저비용 효율성:** 비스포크 패밀리허브와 같은 고가의 스마트 냉장고 없이도 저비용으로 효율적인 식재료 관리를 제공합니다. 이를 통해 사용자들이 추가적인 하드웨어 비용 없이도 냉장고 관리 시스템을 활용할 수 있습니다.
- **사용자 친화적 인터페이스:** 복잡한 설치 과정이나 사용법 없이, 간단한 UI/UX 디자인으로 누구나 쉽게 접근하고 사용할 수 있습니다.
- **다양한 기능 통합:** 기존 서비스가 개별적으로 제공하는 기능들을 하나의 플랫폼에서 통합하여 제공합니다. 이는 식재료 유통기한 관리, 제철 식재료 정보 제공, 레시피 추천, 메모 및 캘린더 기능, QR 코드 생성 및 공유 기능을 포함합니다.

---

### 프로젝트 주요 내용

### 🛠️ 플랫폼 및 도구

- **PLATFORM & TOOLS:** IntelliJ IDEA, VScode, Workbench, GitHub, Figma, Zoom, Slack
- **FRONTEND:** HTML, CSS, JavaScript, jQuery, Bootstrap, Thymeleaf, AJAX
- **BACKEND:** Java, Spring, Spring Boot, Spring Data JPA, Spring MVC, Spring Security
- **DATABASE:** MySQL
- **API:** Edamam Food Database API, FullCalendar, Spring Mail

### 🧩 **architecture**
![아키텍처 drawio](https://github.com/heejin9/gulbihouse-project/assets/152829380/db0e80e5-3355-4d8e-822a-b5ffae0134e8)


### 🖇️ ERD
![굴비하우스](https://github.com/heejin9/gulbihouse-project/assets/152829380/93bd2ae2-41de-4c18-9f5c-b125f555e8b7)


### 🎨 Wireframe
![최종프로젝트 발표 PPT](https://github.com/heejin9/gulbihouse-project/assets/152829380/f0168445-67bd-4e12-8e4a-924c044b8e5f)


---

### 주요 기능 설명

### ⚙️ 전체 기능

- **냉장고 식재료 관리**
    - **추가, 수정, 삭제 기능:** 식재료의 추가, 수정, 삭제를 통해 사용자가 쉽게 냉장고 내용을 업데이트 가능
    - **식재료 목록 조회:** 냉장, 냉동, 실온으로 분류하여 이름순, 유통기한 순으로 정렬 가능
- **제철 식재료 정보 제공**
    - **레시피 추천:** Edamam API를 통해 제철 식재료를 기반으로 다양한 요리 레시피를 추천
    - **건강 정보 제공:** 제철 식재료의 영양 정보와 건강상의 이점 제공
- **임박 재료 알림**
    - **알림 기능:** 유통기한이 임박한 재료를 색상으로 구분하여 시각적으로 쉽게 인식할 수 있습니다. 유통기한 D-day는 빨간색, D-3은 노란색, D-7은 파란색으로 표시
- **메모 및 일정 관리**
    - **캘린더 기능:** FullCalendar를 사용하여 직관적인 달력 UI 제공
    - **메모장 기능:** 중요한 메모를 쉽게 기록하고 조회
- **QR 코드 생성 및 친구 추가 기능**
    - **QR 코드 생성:** ZXing 라이브러리를 사용하여 식재료 목록을 QR 코드로 생성
    - **협업 기능:** 친구 추가를 통해 서로의 냉장고 내용을 공유하고, 공동으로 식재료를 관리
- **로그인 및 회원가입**
    - **사용자 인증과 권한 부여:** Spring Security를 사용하여 사용자 인증과 권한 부여
    - **토큰 기반 인증:** JWT를 통해 안전한 세션 관리
- **비밀번호 재설정**
    - **이메일 발송:** Spring Mail을 사용하여 비밀번호 재설정 인증번호를 이메일로 발송

### 🧨트러블슈팅

**문제:** 

- **상황:**
- **해결 방법:**
- **결과:**
- **개선 사항:**


---

### 🗂️ 부록

- 프로젝트 기획안
    
    [기획안_구희진 .docx](https://docs.google.com/document/d/1g-d4-9GrPVB0MQC5XVmNUcj-fRDLyrLC/edit?usp=sharing&ouid=100970501915328887484&rtpof=true&sd=true)
    
- 데이터 수집(설문조사)
    
    [냉장고 공유 관리 시스템 사용자 설문](https://docs.google.com/forms/d/e/1FAIpQLSeiel08S3YRLeDcRM6ZGgFxs-qYFBUsUBIXXCOR43s3lRpcZA/viewform?usp=sf_link)
    
- Figma(UI 화면)
    
    [굴비하우스](https://www.figma.com/design/g2Htw50k1HM8dfyrESluum/%EA%B5%B4%EB%B9%84%ED%95%98%EC%9A%B0%EC%8A%A4?node-id=0-1&t=YlBOTF7MJRqEYe8f-1)
    
- API 명세서
    
    [API 명세서_구희진.xlsx](https://docs.google.com/spreadsheets/d/1njFGO3Bu1ZK3mluHmGpUaDVdavb6zCow/edit?usp=sharing&ouid=100970501915328887484&rtpof=true&sd=true)
    
- 프로젝트 발표 ppt & 시연 영상
[최종프로젝트 발표 PPT](https://www.canva.com/design/DAGHXRsH_UM/jMeHrtklOiE4Q2stqZbJ1w/edit?utm_content=DAGHXRsH_UM&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)
