### 테스트 하기전 터미널에서 h2 실행해야됨!!

### JPQL이 제공하는 모든 검색 조건 제공
member.username.eq("member1") // username = 'member1'
member.username.ne("member1") //username != 'member1'  
member.username.eq("member1").not() // username != 'member1'  
member.username.isNotNull() //이름이 is not null  
member.age.in(10, 20) // age in (10,20)  
member.age.notIn(10, 20) // age not in (10, 20)
member.age.between(10,30) //between 10, 30  
member.age.goe(30) // age >= 30  
member.age.gt(30) // age > 30  
member.age.loe(30) // age <= 30  
member.age.lt(30) // age < 30  
member.username.like("member%") //like 검색
member.username.contains("member") // like ‘%member%’ 검색
member.username.startsWith("member") //like ‘member%’ 검색

### 결과 조회
- fetch() : 리스트 조회, 데이터 없으면 빈 리스트 반환  
- fetchOne() : 단 건 조회  
  - 결과가 없으면 : null  
  - 결과가 둘 이상이면 : com.querydsl.core.NonUniqueResultException  
- fetchFirst() : limit(1).fetchOne()   

// List  
List<Member> fetch = queryFactory  
    .selectFrom(member)  
    .fetch();  

// 단 건  
Member findMember1 = queryFactory  
    .selectFrom(member)  
    .fetchOne();  

// 처음 한 건 조회  
Member findMember2 = queryFactory  
    .selectFrom(member)  
    .fetchFirst();  

// count 쿼리로 변경  
Long totalCount = queryFactory  
    //.select(Wildcard.count) //select count(*)  
    .select(member.count()) //select count(member.id)  
    .from(member)  
    .fetchOne();  

### 정렬
- desc() , asc() : 일반 정렬
- nullsLast() , nullsFirst() : null 데이터 순서 부여


