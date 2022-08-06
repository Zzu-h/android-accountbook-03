# android-accountbook-03
프로젝트 #3 가계부앱 - 이주호

## 🛠 요구사항 분석
![Group 1](https://user-images.githubusercontent.com/72387349/183258636-a6e1e85a-9494-4196-be66-9a0d4cbef917.png)
...
- [더보기](https://docs.google.com/spreadsheets/d/1N7iqhx4ZgWVZsj-KgZH7UAKfbsCtXaZ8Pck50kKToYw/edit?usp=sharing)

## 📝 네이밍 컨벤션
- Compose Naming 규칙
    - [docs](https://github.com/androidx/androidx/blob/androidx-main/compose/docs/compose-api-guidelines.md#compose-baseline)를 기반으로 Compose 네이밍을 한다.
    - entities의 function
        - `@Composable`이 붙은 함수

- [더보기](https://github.com/woowa-techcamp-2022/android-accountbook-03/wiki/Coding-Convention)

## 📌 Git 사용 규칙
1. Git Rule
    - 특정 작업에 대해 issue 생성하기
    - feature 브랜치를 만들기
    - add, commit, push, pull request, merge 순으로 진행
- [더보기](https://github.com/woowa-techcamp-2022/android-accountbook-03/wiki/Git-%EC%82%AC%EC%9A%A9%EB%B2%95)

## 🧾 ERD
![erd](https://user-images.githubusercontent.com/72387349/182981652-c5b7a49a-9ef6-4c8e-b55c-a8bf73a4e1d4.png)
- 왜 타입 테이블을 나누는가?
    1. 타입이 추가될 수 있다.
        - 가계부에 잠시 수입과 지출 없이 메모장으로 쓰는 경우가 있다. 이를 대비함
    2. 새로운 테이블이 생길 수 있다.
        - 그 테이블에서 타입을 요구할 수 있으며, 그 타입을 타입 테이블에서 가져오기만 하면 됨

## 📈 설계
![설계-1](https://user-images.githubusercontent.com/72387349/182981674-fe4c9e01-6d71-4c30-897f-85950e77b226.png)

![설계-1](https://user-images.githubusercontent.com/72387349/182981678-c8ebdfaf-cf51-4584-ac92-d5caf461ccff.png)

- Local Api를 왜 나누는가?
    - 현재는 Local DB로 동작하지만, 이 프로젝트가 확장되어 클라우드 서비스를 제공하게 된다면 이에 쉽게 대응하기 위함임
## 프로젝트 영상
- [링크]()