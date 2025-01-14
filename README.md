# github Action 적용

Hello there~ 👋 

- 깃허브를 이용해 협업, 코드 관리 그리고 CI 등등을 효율적으로 관리할 수 있다.    

- `CI(지속적 통합)`를 강력하게 지원해주는 `Github Action`을 이용하여 내가 작성한 코드가 병합이 되기전에 기존 코드와의 호환성과   
안정성을 위해서 pr 시에 테스트 코드를 자동으로 실행시키게 하고 싶다.

## Repository description
스프린트 미션1을 진행하며 작은 단위 하나씩 테스트 코드를 작성하며 많은 장점을 직접적으로 느끼고 있다. 하지만, `gitAction` 의 주제와는 거리가 있으니, 간결하게 정리해보겠습니다.


> 내가 만든 기능에 새로운 기능을 추가한 후 검증하기 위해 main 메소드에서 연관되어 있는 기능들도 모두 함께 실행해야한다.   
테스트 코드를 작성하면, 추가된 기능의 테스트 코드를 작성한 후 기존 테스트 코드를 함께 실행시키면 이상이 없음을 확인할 수 있다.
<br>

- 코딩을 하며 **안정감**을 느낄 수 있음
- 실패한 원인과 로직을 디버깅하는데 **두려움이 줄어듦**

테스트 코드를 작성했으니, 테스트 자동화를 통해 안정성을 높이기 위해 `githubAction`을 이용해 미션 `제출 pr`에 적용해보고 싶었습니다.

## Issue 
스프린트 미션 레포지터리를 `fork` 한 후 개인 레포지터리에 작업한 코드를 `pr` 요청을 통해 코드리뷰를 받는 형식입니다. 내게 발생했던 이슈들을 하나하나씩 정리보겠습니다.


### 1. 개인 레포지터리의 브랜치가 `part1-백재우-sprint1`가 최상단이 아니다.
   깃허브 액션의 설정파일을 가지고 있지 않은 `main` 에서는 당연히 인식하지 못하였으며, 액션이 등록되지 않았다. 이에 레포지터리 설정에서 `default brach`를 바꾸어주었다.

<br>

<div style="display: flex; flex-direction: column; align-items: center;">
   <img src="/imageStore/defaultBranch.png" style="width:900px; border-radius:10px; margin-bottom: 30px">
   <img src="/imageStore/defaultBranchHome.png" style="width:900px; border-radius:10px;">
</div>

<br>

--- 

### 2. github에 액션 설정 파일   
   경로 `.github/workflows`에 다음과 같이 코딩해주었습니다.
```
name: sprint mission 1 PR Test

on:
  push:
    branches:
      - 'part1-백재우-sprint1'
  pull_request:
    branches:
      - 'part1-백재우'
    paths:
      - 'codeit-bootcamp-spring/1-sprint-mission/**'

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout repository
        uses: actions/checkout@v3

      - name: ☕ JDK 17 세팅
        uses: actions/setup-java@v3
        with:
          distribution: 'adopt'
          java-version: '17'


      - name: ☕ gradlew 실행 권한 설정
        run: find . -name "gradlew" -exec chmod +x {} \;

      - name: 🚀 테스트 진행
        run: cd codeit-bootcamp-spring/1-sprint-mission && ./gradlew --info test
```

위의 코드를 작성하는데도 고민되는 부분이 있었습니다. 

- `push` 를 할 때도 테스트 코드를 실행시켜야 하는가?
- `pr`의 브랜치를 설정해주고, 앞으로 새로 나올 미션에 대한 패키지의 path설정은 어떻게 해주어야 하는가?
- 코드를 `push` 한 후에 실제로 예상한 결과대로 나오지 않음

처음에는 push 설정이 없이 실행해 본 결과, 테스트 코드가 실패하는 코드임에도 아무런 제약 없이 `pr`이 성공적으로 요청이 되었습니다. 문제를 해결하기 위해서 `git action` 탭에 붙어 확인해 보았으나, 아무런 반응을 확인할 수 없었습니다.    

<div style="display: flex; flex-direction: column; align-items: center;">
   <img src="/imageStore/failPr.png" style="width:900px; border-radius:10px; margin-bottom: 30px">
   <img src="/imageStore/successAction.png" style="width:900px; border-radius:10px;">
</div>

설정 어디의 문제인지 확인하기 위해 개인 레포지터리의 `push` 했을 경우 `gitaction`이 동작하는지 확인 결과 정상적으로 `빌드 실패`의 결과를 볼 수 있었습니다. 범위가 전체에서 `개인 레포` -> `fork 한 레포` 로 좁혀졌습니다.

<br>

---

### 3. 조작할 수 있는 권한, 사이드 이펙트
   소유권이 내게 존재하지 않고, 모든 교육생들이 이용하고 있는 레포지터리에 테스트 할 수는 없고 경험해보지 않은 영역이라 사이드 이펙트를 무시할 수 없어 다른 방법을 시도했습니다.

우선 개인 레포지터리를 하나 생성하여 (현재 레포지터리) 같은 환경을 구축하였고, 기존 미션 코드를 모두 복사했습니다.





