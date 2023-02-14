# Spring_TDD
단위 테스트 학습

1. POJO 상품등록 구현
   - 순수 java만을 이용해 상품등록 구현
2. SpringBootTest 전환
   - spring bean 등록
   - @Component, @autowired를 통한 의존성 주입
3. HTTP API 구현
   - rest-assured 라이브러리 추가 및 response 생성
   - RestController 추가
4. JPA 적용
   - ProductRepository -> interface 및 JpaRepository로 변경
   - JPA 적용으로 인한 Product @Entity 및 기타 필수 설정
   - DatabaseCleanUp 설정 클래스 추가 : 테스트 진행할 때 테이블에 있는 튜플 값들을 다 지우고 id값 1로 세팅한 다음 다시 시작하기 위한 용도
