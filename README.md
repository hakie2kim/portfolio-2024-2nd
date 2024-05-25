# 포트폴리오

## 💬 소개

## 🔨 기능 요구사항

### 로그인

`login.jsp` - `<form>` ⮂ `LoginController.java` - `login()` ⮂ `LoginService.java` - `login()` ⮂ `MemberRepository.java` - `findMemberByMemberId()`, `PasswordUtil.java` - `verifyPassword()`

- [x] 다음 페이지는 로그인 없이 접근할 수 없습니다.
  - 공지사항 목록 페이지 (`/forum//notice/listPage.do`)
  - 공지사항 작성 페이지 (`/forum/notice/writePage.do`)

#### Filter 등록하는 방법

##### `WebContent/WEB-INF/web.xml`

##### `@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, servletNames = { "pf" })`

- 위 필터 설정은 필터가 직접 클라이언트로부터 오는 요청(DispatcherType.REQUEST)에 대해서만 호출되며, 이러한 요청이 "pf"라는 이름의 서블릿(DispatcherServlet)으로 향할 때만 적용된다는 것을 나타낸다.
- 이 기준에 맞는 필터는 서블릿이 요청을 처리하기 전에 실행되어, 로깅, 인증 확인, 요청 수정과 같은 작업을 수행할 수 있습니다.

#### `LoginFilter.java`

1. 요청 URI(`requestURI`)가 로그인이 필요한 URI 배열(`LOGIN_REQUIRED_URI`)에 존재하는지 확인한다.
2. 세션에 로그인 할 때 저장했던 값(`memberId`)이 존재하는지 확인한다.
3. `2.`의 값이 존재하지 않으면 로그인 페이지(`/auth/loginPage.do`)로 리다이렉트한다.

- [x] 로그인에 실패한 경우 경우를 구분해 다음과 같은 메시지를 보여주어야 합니다.
  - 아이디 또는 비밀번호를 잘못 입력했습니다.

### 회원가입

- [x] 회원가입 시 다음과 같은 제약 사항이 있습니다.
  - 아이디는 공백 또는 빈 칸일 수 없고 4~20자의 영어 소문자, 숫자만 사용 가능합니다.
  - 비밀번호는 8~16자의 영문 대/소문자, 숫자를 사용하고, 특수문자를 1개 이상 포함해야 합니다.
  - 이름은 공백 또는 빈 칸일 수 없습니다.
  - 이메일은 공백 또는 빈 칸일 수 없고 이메일 형식이어야 합니다.
- [x] 패스워드는 DB에 암호화 후 저장되어야 합니다.

### 프로젝트 환경 설정

#### Docker DB

```
# for Windows
docker run --name mysql-lecture -p 53306:3306 -v c:/dev/docker/mysql:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=admin_123 -d mysql:8.3.0

# for Mac
docker run --name mysql-lecture -p 53306:3306 -v ~/dev/docker/mysql:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=admin_123 -d mysql:8.3.0
```

#### MyBatis

##### `pom.xml`

```xml
<dependency>
	<groupId>org.mybatis</groupId>
	<artifactId>mybatis</artifactId>
	<version>3.5.16</version>
</dependency>
<dependency>
	<groupId>org.mybatis</groupId>
	<artifactId>mybatis-spring</artifactId>
	<version>2.1.2</version>
</dependency>
```

`mybatis-spring` 의존성 추가할 때 `spring-context`, `spring-jdbc`와 호환되는 버전을 확인하자. `spring` 버전 `5.x.x`와 호환되는 것을 확인할 수 있다.

##### `src/main/resources/context-beans.xml`

```xml
<!-- mybatis start -->
<!-- DAO 구현체 역할을 대신 해주는 클래스 기본설정 4가지가 필요 -->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
	<!-- 1. DB에 접속 하기 위해서 설정 -->
	<property name="dataSource" ref="dataSource" />

	<!-- 2. myBatis 기본 설정 -->
	<property name="configLocation" value="classpath:mybatis-config.xml" />

	<!-- 3. query가 적힌 xml 위치 -->
	<property name="mapperLocations" value="classpath:sql/SQL.*.xml" />

	<!-- 4. 트랜잭션 관리 -->
	<property name="transactionFactory">
		<bean class="org.mybatis.spring.transaction.SpringManagedTransactionFactory" />
	</property>
</bean>

<!-- 작업 지시서 DAO 위치를 지정해야 사용 할 수 있음 -->
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
	<property name="basePackage" value="com.portfolio.www.dao.mybatis" />
</bean>

<!-- 트랜잭션 관리를 위한 bean -->
<bean id="transactionManager"
	class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	<property name="dataSource" ref="dataSource" />
</bean>
<!-- mybatis end -->
```

##### `src/main/resources/mybatis-config.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<settings>
		<!-- 예) member_id -> memberId -->
		<setting name="mapUnderscoreToCamelCase" value="true" />
	</settings>

  <!-- 쿼리 수행 결과를 DTO에 자동 매핑하기 위해 DTO 검색 -->
	<typeAliases>
		<package name="com.portfolio.www.dto" />
	</typeAliases>
	<!-- 개별로 setting 하는 방법
	<typeAlias alias="Employees" type="com.edu.dto.Employees" />
	-->
</configuration>
```

#### Tiles

##### `pom.xml`

```xml
<dependency>
	<groupId>org.apache.tiles</groupId>
	<artifactId>tiles-core</artifactId>
	<version>3.0.8</version>
</dependency>
<dependency>
	<groupId>org.apache.tiles</groupId>
	<artifactId>tiles-jsp</artifactId>
	<version>3.0.8</version>
</dependency>
<dependency>
	<groupId>org.apache.tiles</groupId>
	<artifactId>tiles-servlet</artifactId>
	<version>3.0.8</version>
</dependency>
<dependency>
	<groupId>org.apache.tiles</groupId>
	<artifactId>tiles-extras</artifactId>
	<version>3.0.8</version>
</dependency>
```

##### `WebContent/WEB-INF/tiles/tiles-config.xml`

###### 기본 레이아웃

```xml
<definition name="tiles-default" template="/WEB-INF/views/layout/default.jsp">
	<put-attribute name="menu" value="/WEB-INF/views/layout/menu.jsp" />
	<put-attribute name="body" value="" />
	<put-attribute name="footer" value="/WEB-INF/views/layout/footer.jsp" />
</definition>
```

###### 회원가입, 로그인 페이지

```xml
<!-- 회원가입 -->
<definition name="auth/join" extends="tiles-default">
	<put-attribute name="title" value="회원가입" />
	<put-attribute name="body" value="/WEB-INF/views/auth/join.jsp" />
</definition>
<!-- 로그인 -->
<definition name="auth/login" extends="tiles-default">
	<put-attribute name="title" value="로그인" />
	<put-attribute name="body" value="/WEB-INF/views/auth/login.jsp" />
</definition>
```

###### 각 요청에 대한 매핑 화면

```xml
<!-- 결과로 /WEB-INF/views/layout/default.jsp 화면이 반환되고 이 화면에는 menu, body, footer가 존재 -->
<definition name="WILDCARD:*" extends="tiles-default">
	<put-attribute name="body" value="/WEB-INF/views/{1}.jsp" />
</definition>
<definition name="WILDCARD:*/*" extends="tiles-default">
	<put-attribute name="body" value="/WEB-INF/views/{1}/{2}.jsp" />
</definition>
<definition name="WILDCARD:*/*/*" extends="tiles-default">
	<put-attribute name="body" value="/WEB-INF/views/{1}/{2}/{3}.jsp" />
</definition>
```

### RestController 구성 및 요청

### Servlet 구성 및 요청

### 예외 처리

### 기타

## 🚨 트러블 슈팅

## 📝 메모
