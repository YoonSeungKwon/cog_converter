1. 개발 환경
<hr/>
Java 17.0
SpringBoot 3.4
-JPA/Hibernate
H2 2.3
GDAL 3.10.0

2. API 서비스 구성
<hr/>
도메인

ConvertibleImage<interface> - 변환되는 이미지의 정보를 저장하는 객체

GdalConvertibleImage<class> GDAL 라이브러리를 통하여 변환을 수행하는 도메인 객체 내부적으로 Dataset을 저장하며 캡슐화를 통하여 메타데이터 반환
 
서비스

<storage>
StorageManager<interface>	이미지 저장 및 삭제를 정의하는 인터페이스
  
S3StorageManager<class>	S3기반으로 이미지 저장 및 삭제를 구현한 클래스

GdalImageLoader<abstract class> GDAL라이브러리를 이용하여 이미지를 로딩하는 공통 함수를 상속시키는 추상 클래스

<convert>

ConverterFactory<class>	Filformat을 통하여 적절한 Converter를 생성하는 객체

ImageConverter<interface>	이미지 변환을 정의하는 인터페이스

CogConverter<class>		어댑터 패턴으로 COG파일로 변환을 구현한 클래스

GdalCogConverter<class>	GDAL을 이용하여 실제 파일 변환을 구현한 클래스

<application>

ConvertService<class>	변환기능의 흐름을 담당하는 서비스 객체 

DataService<class>		데이터 검색 기능의 흐름을 담당하는 서비스 객체
	

인프라

CogImageData<class>	데이터베이스에 저장되는 메타데이터를 가지고 있는 영속성 객체

CogJpaRepository<interface>	JPA를 이용하여 데이터베이스에 저장하는 객체

CogDataRepositoryImpl<class>	어댑터 패턴으로 서비스 레이어의 리포지토리를 구현한 객체


3. API 명세
<hr/>

<convert>

GET(“/api/v1/converts/list”)	
response
{List{filename:(String), size:(long), lastModified:(Date)}}

POST(“/api/v1/converts/tif-cog”)
request 
{key:(String 필수), compressType(String default=LZW), blockSize(int default=512)}
response
{fileName:(String), width:(int), height:(int), bandCount:(int), blockSize:(int), compressType:(String), createdAt:(String)}

POST(“/api/v1/converts/tif-cog/all”)
request
{key:(String[] 필수), compressType(String default=NONE), blockSize(int default=512)}
response
{List{fileName:(String), width:(int), height:(int), bandCount:(int), blockSize:(int), compressType:(String), createdAt:(String)}}
<data>

GET(“/api/v1/data?width=(int)”)
response
{List{fileName:(String), width:(int), height:(int), bandCount:(int), blockSize:(int), compressType:(String), createdAt:(String)}}

GET(“/api/v1/data?height=(int)”)
response
{List{fileName:(String), width:(int), height:(int), bandCount:(int), blockSize:(int), compressType:(String), createdAt:(String)}}

GET(“/api/v1/data?width=(int)&height=(int)”)
response
{List{fileName:(String), width:(int), height:(int), bandCount:(int), blockSize:(int), compressType:(String), createdAt:(String)}}

GET(“/api/v1/data?bandCount=(int)”)
response
{List{fileName:(String), width:(int), height:(int), bandCount:(int), blockSize:(int), compressType:(String), createdAt:(String)}}

GET(“/api/v1/data?compressType=(String)”)
response
{List{fileName:(String), width:(int), height:(int), bandCount:(int), blockSize:(int), compressType:(String), createdAt:(String)}}

GET(“/api/v1/data”)
response
{List{fileName:(String), width:(int), height:(int), bandCount:(int), blockSize:(int), compressType:(String), createdAt:(String)}}


인프라 구성
<hr/>

사용 방법
<hr/>

