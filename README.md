냉장고 공유 관리 시스템

프로젝트 개요
이 프로젝트는 사용자가 냉장고의 식재료를 효율적으로 관리하고 공유할 수 있는 시스템을 구축하는 것을 목표로 합니다. 주요 기능으로는 재고 관리, 유통기한 알림, 제철 식재료 정보 제공 등이 있습니다.

프로젝트 목적
효율적인 식재료 관리: 사용자가 식재료를 신선하게 유지하고 소비할 수 있도록 돕습니다.
공유 냉장고 관리: 가족 구성원이나 동거인들이 함께 식재료를 관리하고 공유할 수 있습니다.
건강한 식습관 촉진: 제철 식재료 정보를 제공하여 건강한 식단 계획을 지원합니다.
타겟
건강한 식습관을 추구하는 1인 가구 및 사용자
식재료 및 식단을 공유하고 싶어 하는 사용자
가정에서 요리를 즐기는 사용자
영향
경제적인 소비: 냉장고에 있는 식재료를 관리하고 낭비를 줄여 경제적인 소비를 도모합니다.
건강한 식사 유지: 바쁜 일상 속에서도 건강한 식사를 유지하도록 지원합니다.
효율적인 식사 계획: 식사 계획을 더욱 효율적으로 수립하여 건강한 식습관을 유지합니다.
소통 활성화: 가족 구성원 또는 동거인들 간의 소통을 활성화하여 화목한 가정 분위기를 조성합니다.
기능 및 사용된 기술
핵심 기능
식재료 관리:
식재료 추가, 수정, 삭제 기능
유통기한 표시 및 알림 기능
유통기한 알림:
유통기한이 임박한 식재료 알림
제철 식재료 정보 제공:
Edamam API를 통해 제철 식재료 정보 제공
QR 코드 접근:
QR 코드를 통해 시스템에 쉽게 접근 가능
사용된 기술
프론트엔드:
HTML, CSS, JavaScript, jQuery, Bootstrap, Thymeleaf, AJAX
백엔드:
Java, Spring, Spring Boot, Spring Data JPA, Spring MVC, Spring Security
데이터베이스:
MySQL
API:
Edamam Food Database API, FullCalendar, Spring Mail
도구:
IntelliJ IDEA, VSCode, Workbench, GitHub, Figma, Zoom, Slack
개발 환경
플랫폼 및 도구:
IntelliJ IDEA, VSCode, Workbench, GitHub, Figma, Zoom, Slack
프론트엔드:
HTML, CSS, JavaScript, jQuery, Bootstrap, Thymeleaf, AJAX
백엔드:
Java, Spring, Spring Boot, Spring Data JPA, Spring MVC, Spring Security
데이터베이스:
MySQL
API:
Edamam Food Database API, FullCalendar, Spring Mail
주요 기능 및 설명
식재료 관리:
AJAX를 사용해 실시간으로 식재료를 추가, 수정, 삭제
유통기한이 다가오는 식재료에 대해 색상으로 표시 (1일, 3일, 7일 기준)
유통기한 알림:
유통기한이 임박한 식재료에 대한 알림 기능
제철 식재료 정보 제공:
Edamam API를 사용해 제철 식재료 정보를 제공
QR 코드 접근:
QR 코드를 통해 사용자가 시스템에 쉽게 접근
트러블슈팅 사례
JPA 순환 참조 문제:
문제: 엔티티 간의 순환 참조로 인해 발생하는 무한 루프
해결: @JsonManagedReference와 @JsonBackReference를 사용해 순환 참조 해결
API 키 관리 문제:
문제: API 호출 실패 (API 키 유효성 만료)
해결: 새로운 API 키 발급 및 설정
비동기 처리 문제:
문제: 비동기 처리 시 UI 블로킹
해결: AJAX와 Fetch API를 사용해 비동기 처리
미래 개선 사항
인터넷 마켓 연동:
SSG와 같은 온라인 마켓과의 연동 기능 추가
공유 기능 향상:
사용자가 식재료를 더 쉽게 공유할 수 있는 기능 추가
인공지능 추천 기능:
사용자 선호도에 맞춘 식재료 및 레시피 추천 기능 추가
모바일 앱 개발:
모바일 앱을 개발해 더 쉬운 접근성 제공
고급 분석 기능:
소비 패턴에 대한 상세한 분석 기능 제공
기획 의도 및 근거
문제점
음식물 폐기물 문제:

1인당 하루 음식물 폐기물이 310.9g에 달함
이는 환경 문제와 가계 경제에 부정적 영향
2024년 소비자 물가지수:

식재료 가격 상승으로 인해 경제적 부담 증가
효율적인 식재료 관리 필요성 대두
프로젝트 목표
효율적인 식재료 관리 및 소비 촉진
경제적이고 환경적으로 지속 가능한 소비 유도
건강한 식습관 형성 지원
설문조사 연관
설문조사 결과, 많은 사용자들이 식재료 관리의 복잡함과 유통기한 관리의 어려움을 느끼고 있음
이를 해결하기 위해 유통기한 알림과 제철 식재료 정보 제공 기능이 필요
기여
프로젝트에 기여하고 싶으신 분들은 저장소를 포크하고 변경 사항을 푸시한 후 풀 리퀘스트를 제출해 주세요.

라이선스
이 프로젝트는 MIT 라이선스에 따라 라이선스가 부여됩니다. 자세한 내용은 LICENSE 파일을 참조하십시오.

문의
질문이나 피드백이 있으시면 your-email@example.com으로 연락해 주세요.
