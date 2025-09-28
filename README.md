## 리포지토리 소개

이 프로젝트는 [capstone_final_back](https://github.com/yunsu1231231/capstone_final_back)을 **리팩토링 진행 중인 백엔드**입니다.

---

## 주요 특징

### 1. DDD 기반 설계 시도

* 계층 간 의존성을 인터페이스로 역전
* DTO · Domain · Entity를 분리하여 도메인 규칙을 명확히 표현
* 확장성과 테스트 용이성을 고려한 구조

<p align="center">  
  <img width="400" src="https://github.com/user-attachments/assets/bc0f7b09-ddcb-4a64-bb87-c6e4d25dd818" alt="DDD 구조 다이어그램" />  
</p>  

---

### 2. 코칭 요청 및 채팅 흐름

* HTTP 요청/응답 기반 코칭 요청 처리
* WebSocket을 통한 실시간 메시지 전송 구조

<p align="center">  
  <img width="600" src="https://github.com/user-attachments/assets/da88ad51-edd1-49e4-b8c8-aebbf08cb617" alt="코칭 요청 및 채팅 시퀀스 다이어그램" />  
</p>  

---

### 3. 로그 수집 및 시각화

* 로컬 Docker Compose 환경에서 ELK 스택(Logstash · Elasticsearch · Kibana) 구성
* Logback 로그를 수집하고 Kibana 대시보드로 시각화

<p align="center">  
  <img width="500" src="https://github.com/user-attachments/assets/bef21d17-1714-4572-ab21-18b3dc72f5e8" alt="ELK 스택 아키텍처" />  
</p>  

**현재는 요구사항 수정 및 기능 개발 단계입니다.**






