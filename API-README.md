- [1. 개요](#1-개요)
- [2. API 목록](#2-api-목록)
- [3. 인터페이스 상세](#3-인터페이스-상세)
  - [3-1 로그인](#3-1-로그인) 
  - [3-2 펫](#3-2-펫) 

## 1. 개요. 
이 문서는 애견 APP 개발의 Backend로부터 API를 통해 정보를 핸들링 하기 위한 API 에 규격을 및 내용을 설명한다.

## 2. API 목록

| API ID | 개요 | API | contents |
| --- | --- | --- | --- |
| 1 | 로그인 | /api/v1/login | 로그인 토큰 발급 |
| 2 | 펫 | /api/v1/pet/all | 펫 정보 전체 조회 |

## **3. 인터페이스 상세**

### 3-1 로그인

**3-1-1 로그인** 

1) API

|  |  |  |  |
| --- | --- | --- | --- |
2) Headers

|  |  |  |  |
| --- | --- | --- | --- |
|  |  |  |  |
|  |  |  |  |

3) Req Parameter

|  |  |  |  |
| --- | --- | --- | --- |
|  |  |  |  |
|  |  |  |  |

4) Req Body

|  |  |  |  |
| --- | --- | --- | --- |
|  |  |  |  |
|  |  |  |  |

5) Response

| Name | Type |  |  |
| --- | --- | --- | --- |
|  |  |  |  |
|  |  |  |  |

5) Example

```json
[Request]
URL : http://localhost/login
Method : POST
Header :
> accept: application/json
[Req Body]
{
	"id": "ahyeon1117@naver.com"
	"pw": "1234567890"
}
[Response]
[
	{
		"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwiaWQiOiJhaHllb24xMTE3QG5hdmVyLmNvbSIsImlhdCI6MTUxNjIzOTAyMn0.mQUUOV82ILoqC3swzi_AEpSFq6RoqguoNEfnUYVGsrU",
	}
]
```

### 3-2 펫
**3-2 전체 펫 정보 조회**
1) API

| url | method |
| --- | --- | 
| api/v1/pet/all | GET |

2) Headers

| topic | contents |
| --- | --- |
| Authorization | Bearer |

3) Req Parameter

| name | type |
| --- | --- |
| - | - |

4) Req Body

| name | type |
| --- | --- |
| - | - |

5) Response

| Name | Type |
| --- | --- |
| user | User |
| petId | long |
| age | String |
| gender | String |

5) Example

```json
[Request]
URL : http://localhost/login
Method : POST
Header :
> accept: application/json
[Req Body]
{
	"id": "ahyeon1117@naver.com"
	"pw": "1234567890"
}
[Response]
[
	{
		"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwiaWQiOiJhaHllb24xMTE3QG5hdmVyLmNvbSIsImlhdCI6MTUxNjIzOTAyMn0.mQUUOV82ILoqC3swzi_AEpSFq6RoqguoNEfnUYVGsrU",
	}
]
```