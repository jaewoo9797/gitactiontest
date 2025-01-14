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
스프린트 미션 레포지터리를 `fork` 한 후 개인 레포지터리에 작업한 코드를 `pr` 요청을 통해 코드리뷰를 받는 형식입니다.. 내게 발생했던 이슈들을 하나하나씩 정리보겠습니다.


1. 개인 레포지터리의 브랜치가 `part1-백재우-sprint1`가 최상단이 아니다.
   깃허브 액션의 설정파일을 가지고 있지 않은 `main` 에서는 당연히 인식하지 못하였으며, 액션이 등록되지 않았다. 이에 레포지터리 설정에서 `default brach`를 바꾸어주었다.

<style>
  .image-container img {
    width: 900px;
    border-radius: 10px;
    margin-bottom: 20px;
  }
  .image-container img:last-child {
    margin-bottom: 0; /* 마지막 이미지는 간격 제거 */
  }
</style>

<div class="image-container" style="text-align: center;">
   <img src="/imageStore/defaultBranch.png">
   <img src="/imageStore/defaultBranchHome.png">
</div>



