# <img width = 30px, src = "https://user-images.githubusercontent.com/98693746/175188648-a5d2eb2f-e965-433c-8c91-6daf225d7c3b.png"> VELOG 클론코딩

개발자들을 위한 블로그 서비스 velog를 클론코딩 해보고, 불편했던 점을 수정해보아요

제작 기간 : 2022.06.17 ~ 2022.06.23 (7일)

# :information_desk_person: 팀원 소개

👩‍💻 : **BACK-END (3명)**

 + 김민주 : 로그인, 회원가입, 인증(JWT), 댓글 CRUD, 좋아요 기능 구현

 + 김형준 : 게시글 CRUD, 게시글 태그 기준 조회 기능 구현

 + 홍예준

👨‍💻 : **FRONT-END (2명)** <a href="https://github.com/sparrowscout/velog_frontEnd">front-end github</a>

 + 김민경

 + 이재엽

# :dizzy: 핵심기능
> 1) 회원가입 / 로그인
 + JWT 인증 방식으로 로그인 구현
> 2) 게시글 조회 / 등록
 + 게시글 조회
 + 게시글 상세 조회
 + 게시글 등록
> 3) 댓글 CRUD
 +댓글 등록, 댓글 수정, 댓글 삭제
# :tv: 데모영상
<img src="https://img.shields.io/badge/YouTube-FF0000?style=flat&logo=YouTube&logoColor=white"/>

# :computer: 기술 스택 
#### Server 
  <img src="https://img.shields.io/badge/aws-232F3E?style=for-the-badge&logo=AmazonAWS&logoColor=white">
  
#### Framework
  <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"><img src="https://img.shields.io/badge/Springboot-6DB33F?style=for-the-badge&logo=Springboot&logoColor=white">
  
#### Language
  <img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white">
  
#### Database
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
  
#### Tool
  <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"><img src="https://img.shields.io/badge/Git-00000?style=for-the-badge&logo=Git&logoColor=F05032]"/><img src="https://img.shields.io/badge/Github-181717?style=for-the-badge&logo=Github&logoColor=white]"/>

# :key: 트러블 슈팅
> 1) 백엔드 - Token 오류
 + 백과 프론트 연결 후 token 사용 시 토큰이 유효하지 않다는 에러 메세지와, Cors 오류가 발생됨
 + 백엔드에서 Cors Access-Control-Allow-Headers 설정에 Authorization을 추가해 주어서 토큰을 정상적으로 사용할 수 있게 함.
> 2) 백엔드 - ENTITY 테이블
 + Like Entity 사용 시 테이블 예약어 에러 발생 
 + @Table(name = "LIKE_TABLE") 컬럼을 설정하여 사용 가능하게 함
> 3) 백엔드 - 텍스트 길이제한
 + 게시글 작성 시 content와, 댓글 작성 시 comment의 길이가 255글자를 넘을 시 에러 발생
 + 이전에 쓰던 DB 테이블을 DROP 후 @Column (length = 2000) 컬럼을 재설정하여 
길이 제한을 2000자로 늘려줌
> 4) 백엔드 - CORS 도메인 
 + 프론트 서버 배포 후 CORS 허용 대상 도메인에 프론트 서버 주소를 기입하였지만 에러 발생
 + 허용 대상 도메인 작성 시 주소 맨 뒤의 / 를 지워주어 RequestHeaders과 Referer의 주소가 알맞도록 설정해 줌
