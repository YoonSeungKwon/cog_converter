# COG Image Converter API

Java와 GDAL을 이용하여 이미지를 변환하고, 변환된 메타데이터를 조회할 수 있는 API 서비스입니다.

<br/>

## 1. 개발 환경
---
- **Java 17.0**  
- **Spring Boot 3.4**  
- **JPA / Hibernate**  
- **H2 Database 2.3**  
- **GDAL 3.10.0** *(인프라 추가 없이 Spring 프로젝트 내에서 COG변환을 처리하기 위하여 사용)*
<br/>

## 2. 테스트 환경
---
- **OS Window 11 64bit**
- **RAM 8GB**
- **CPU 12th Gen Intel(R) Core(TM) i7-1260P   2.10 GHz**

<br/>

## 3. API 서비스 구성
---

### A. 도메인

| Entity | Info |
|--------|------|
| `ConvertibleImage` `<interface>` | 서비스내에서 변환 전/후의 이미지를 정의하는 인터페이스 |
| `GdalConvertibleImage` `<class>` | GDAL 라이브러리를 통해 변환을 수행하는 ConvertibleImage 구현체. 내부적으로 Dataset을 저장 |

---

### B. 서비스

#### Storage

| Entity | Info |
|--------|------|
| `StorageManager` `<interface>` | 이미지 저장 및 삭제를 정의하는 인터페이스 |
| `S3StorageManager` `<class>` | S3 기반으로 이미지 저장 및 삭제를 구현한 클래스 |
| `GdalImageLoader` `<abstract class>` | GDAL을 이용하여 이미지를 로딩하는 공통 함수를 제공하는 추상 클래스 |

#### Convert

| Entity | Info |
|--------|------|
| `ConverterFactory` `<class>` | 파일 포맷을 기반으로 적절한 Converter를 생성하는 팩토리 클래스 |
| `ImageConverter` `<interface>` | 이미지 변환을 정의하는 인터페이스 |
| `CogConverter` `<class>` | 어댑터 역할로 라이브러리를 이용하여 COG 파일로의 변환을 연결 |
| `GdalCogConverter` `<class>` | GDAL을 이용하여 실제 파일 변환을 수행하는 클래스 |

#### Application

| Entity | Info |
|--------|------|
| `ConvertService` `<class>` | 이미지 변환 기능의 흐름을 담당하는 서비스 레이어 객체 |
| `DataService` `<class>` | 데이터 검색 기능의 흐름을 담당하는 서비스 레이어 객체 |

<br/>

## 4. API 명세
---
### ConvertController

#### 1. GET `/api/v1/converts/list` 
**변환 가능한 모든 파일을 버킷에서 로드**

**Response**
```json
[
  {
    "filename": "string",
    "size": "long",
    "lastModified": "date"
  }
]
```

#### 2. POST `/api/v1/converts/tif-cog` 
**tif/tiff파일을 COG로 변환**

**Request**
```json
{
  "key": "string (필수)",
  "compressType": "string (선택 default=LZW)",
  "blockSize": "int (선택 default=512)"
}
```

**Response**
```json
{
  "fileName": "string",
  "width": "int",
  "height": "int",
  "bandCount": "int",
  "blockSize": "int",
  "compressType": "string",
  "createdAt": "string"
}
```

#### 3. POST `/api/v1/converts/tif-cog/all` 
**tif/tiff파일들을 COG로 변환**

**Request**
```json
{
  "key": "[string, ...] (필수)",
  "compressType": "string (선택 default=LZW)",
  "blockSize": "int (선택 default=512)"
}
```

**Response**
```json
[
  {
    "fileName": "string",
    "width": "int",
    "height": "int",
    "bandCount": "int",
    "blockSize": "int",
    "compressType": "string",
    "createdAt": "string"
  }
]
```

---

### DataController

#### 1. GET `/api/v1/data`
**쿼리 파라미터 기반 메타데이터 검색**

- `?width=(int)`
- `?height=(int)`
- `?width=(int)&height=(int)`
- `?bandCount=(int)`
- `?compressType=(string)`
- 최신순 조회: `GET /api/v1/data`

**Response**
```json
[
  {
    "fileName": "string",
    "width": "int",
    "height": "int",
    "bandCount": "int",
    "blockSize": "int",
    "compressType": "string",
    "createdAt": "string"
  }
]
```
## 5. 인프라 구조

**배포시 구조**

[이미지]
		
**자동화 아이디어**

##### 업로드 시 자동화를 한다면 S3에서 지원하는 메시징 방식을 스프링부트에 연동하여 사용할 것 같지만, 그 외로는 스케줄 스레드를 이용해서 주기적으로 버킷을 확인하여 변환시키거나 while 문을 이용한 스핀 방식으로 버킷을 지속적으로 확인하는 방법을 생각했습니다.


## 6. 사용 방법



## 7. 다운로드
