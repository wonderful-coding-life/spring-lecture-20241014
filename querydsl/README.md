
## Add dynamic query using QueryDSL for JPA

- build.gradle
```
implementation 'com.querydsl:querydsl-jpa::jakarta'
annotationProcessor 'com.querydsl:querydsl-apt::jakarta'
annotationProcessor 'jakarta.annotation:jakarta.annotation-api'
annotationProcessor 'jakarta.persistence:jakarta.persistence-api'
```
- Product.java
```java
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
}
```
- QueryDSL for JPA는 @Entity 애너테이션이 사용된 엔티티 객체에 대해 다이나믹 쿼리에 사용할 Q로 시작하는 클래스를 생성합니다. 생성하는 위치는 build/generated/sources/annotationProcessor/java/main/com.example.demo/QProduct.java 입니다.
- QueryDSL for JPA는 JPA 엔티티 메니저를 사용하기 때문에 이를 사용한 팩토리 빈을 등록해야 합니다.
```java
@Configuration
public class QueryDslConfig {
    @PersistenceContext
    private EntityManager entityManager;
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
```
- 이제 QueryDSL을 사용한 리파지토리 클래스를 만들고 이것을 제품 리파지토리 선언시 JpaRepository와 함께 확장 선언합니다.
  - QueryDSL을 사용한 리파지토리 인터페이스 작성합니다. queryByKeyword는 매개변수로 전달된 키워드를 searchType에 따라 제품의 제목 또는 본문에서 검색한 결과를 페이지 단위로 반환하는 메서드입니다.
```java
public interface ProductQueryDslRepository {
  enum SearchType {
    TITLE,
    DESCRIPTION,
    BOTH
  }
  List<Product> queryByKeyword(String keyword, SearchType searchType, long offset, long limit);
}
```
  - QueryDSL을 사용한 리파지토리 클래스 작성
```java
@Override
public List<Product> queryByKeyword(String keyword, SearchType searchType, long offset, long limit) {
  var query = jpaQueryFactory.selectFrom(product);
  if (keyword != null && searchType != null) {
    if (searchType == TITLE) {
      query.where(product.title.contains(keyword));
    } else if (searchType == DESCRIPTION) {
      query.where(product.description.contains(keyword));
    } else {
      query.where(product.title.contains(keyword).or(product.description.contains(keyword)));
    }
  }
  return query.orderBy(product.id.desc())
          .offset(offset)
          .limit(limit)
          .fetch();
}
```
where()를 통해 다이나믹 쿼리를 수행할 수 있으며 where()절에 여러개의 컨디션을 AND 또는 OR로 추가할 수 있습니다.
단순히 조건을 콤마(,)로 추가하면 AND와 동일합니다.
```java
query.where(product.title.contains(keyword).or(product.description.contains(keyword)));
// sample for 'AND' condition
// query.where(product.title.contains(keyword).and(product.description.contains(keyword)));
// query.where(product.title.contains(keyword), product.description.contains(keyword));
```
일반 JPA 쿼리와 마찬가지로 대량의 데이터 처리를 위해 stream으로 전달받을 수도 있습니다.
이때 stream은 영속성 컨텍스트가 필요한 것에 주의해야 합니다. 만약 JUnit으로 테스트를 하기 위해서는 테스트 메서드에 @Transactional을 사용하면 됩니다.
```java
return query.orderBy(product.id.desc())
        .offset(offset)
        .limit(limit)
        .stream();
```
  - 제품 리파지토리 작성 - 이제 다음과 같이 JpaRepository와 함께 앞서 작성한 리파지토리도 확장하여 작성합니다.
```java
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductQueryDslRepository {
}
```
  - 테스트 클래스 작성
```
@Test
//use @Transactional for stream
public void queryByKeyword() {
    var products = productRepository.queryByKeyword("와치", TITLE, 0, 10);
    assertThat(products.size()).isEqualTo(4);
    products = productRepository.queryByKeyword("애플", DESCRIPTION, 0, 10);
    assertThat(products.size()).isEqualTo(1);
    products = productRepository.queryByKeyword("스마트", BOTH, 0, 10);
    assertThat(products.size()).isEqualTo(4);
}
```
