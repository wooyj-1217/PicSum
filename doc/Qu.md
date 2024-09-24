# 1. Application Context를 멤버변수로 가지고 있을 경우 어떤 위험성이 있나요?
> Memory Leak이 발생할 수 있습니다.
> Application Context는 앱이 종료되기 전까지 살아있는데, 이를 가지고 있는 객체가 살아있는 동안 계속 참조하고 있으면 GC가 수거하지 못하고 메모리 누수가 발생할 수 있습니다.
## GC
> 원리 : 안쓰는 객체를 수거한다. -> 안쓰는지 어떻게 알아요??? -> refCount == 0
> Strong Ref
> Weak Ref

# 2. 4대 컴포넌트들의 Context가 각각 다른 역할이 있다고 하는데 보통 이 4가지를 사용할 때 보면 뭉뚱그러서 Context로 적고 call 하잖아요. 내부에서 알아서 구분을 해줘서 뭉뚱그러서 부르는건가요?
> 네

# 3. Toast는 왜 Application Context도 받는건가요?(앱이 닫혀도 토스트 메시지가 띄워져야 하는 때가 있는걸까요?
>  OS 에서 유일한 소통 창구

# 4. XML에서 Constraint Layout 쓰는 것은 괜찮은건가요?
> XML -> 3가지(Frame, Linear, Constraint)
> Frame > ScrollView > Constraint
> Linear > Appbar : 56dp, Body  weight: 1 -> Frame > ScrollView > Constraint

# 5. 기존의 앱보다 성능이 올라갔다거나 내려갔다는 것을 측정하는 도구는 어떤것을 추천하시나요?
> 오늘 수업

# 6. Android Dev -> Use iPhone ??? 배신자, 너는 뭐냐, .....
> 꼰대 마인드 -> 왜 니앱을 니가 안쓰냐??
 
# 7. 네이티브 개발자의 미래..? 매번 네이티브로 회귀한다고는 하지만 크로스플랫폼은 계속나오고.. 계속 네이티브여도 되는건지..ㅠㅠ?
> Native -> Android(Kotlin, Java), iOS(Swift)
> Cross  -> Flutter(Dart), ReactNative(React, JavaScript -> TypeScript)
> Hybrid -> Kotlin - KMM, KMP
> WebView 중간에 들어가는건 Basic
---
> WebApp -> WebView만 있는 앱 -> Native 개발자 필요 없음 

# 8. 웹 하시는 분들은 저희가 하고있는 아키텍처와 디자인패턴 없이 작업하나요
# 스터디원이 풀스택이긴 한데 프론트는 웹만 해봤고, 저랑 플러터 같이 공부하시는데 화면에 데이터 코드 다넣고 하시다가 
# 제가 상태관리 해야된다 하고 MVVM이랑 클린 아키텍처 자료 보내주니까 왜 이렇게 해야되는지 모르시겠다고 하셔요.

> System 차이......
> 클린 아키는 하는게 맞아요

# 9. (ListScreen) 쪽에서 하트 데이터는 local, pagingData는 remote인데 합쳐서 보여주는거까진 했는데 현재 코드대로 가면 
#    화면 전체 깜빡임 + 스크롤 최상단(당연한 거지만...) pagingData 때문에 뭐 어떻게 나눌 수도 없고 지금 꽉막혔어요..^_ㅠ
#    Flow<PagingData<*> 이 객체여야 list.collectAsLazyPagingItems()으로 그릴수 있어서 바꾸기도 애매하고
#    favoriteList를 따로 가지고 있다가 ui에서 비교하는 로직넣고 그려주는것도 답은 아닌데 돌아버리겟서여.
# 9-1. PagingData에서 list 데이터를 왜 못꺼내는걸까요???????????? 별 수를 다써봐도 안되네요.
# 9-2. 구조상 UseCase에서 paging flow랑 local flow를 합쳐서 보내줘야 하는건가요?

> 무응답 UseCase 부터 하고 다시 옵니다

# 10. 비슷한 객체(인자가 한두개정도 차이나는..그정도?)가 Remote에도 있고 Local에도 있고 심지어 UI에도 있으면 통합해서 써야되나요?

> X 각각 모델링 한다

# 11. UML 찾아보니까 너무 다양한 다이어그램이 있는데... 이 중에 어떤걸 해야되는걸까요..?
# 11-1. 개발 시 설계가 우선된다고 하는데 너무 모호해서 시작 지점을 못잡겠어요.
# 종류가..? https://online.visual-paradigm.com/drive/#diagramlist:proj=0&dashboard

> System, UseCase, Class, Sequ

# 11-2. compose screen은 function인데 어떻게 그려야 할까요..?
# https://www.figma.com/board/kOxEvkbsPJL8YiKaXwkj78/Untitled?node-id=0-1&t=Re2uNV1NsqtJI0vo-1

-----
2024.08.03
# 12. 회사 앱 빌드툴을 .kts로 바꾸면서 이전보다 빌드속도가 빨라질 줄 알았더니 오히려 기존보다 늦어지더라구요.
# 전에는 한 5분에서 7분정도면 됐었는데 거의 15분정도 걸리더라구요.
# 이게 원래 정상적인 상황인건가요?
# github action으로 app distribution에 자동빌드되게 어제 yml도 작성해서 테스트했는데 거기도 마찬가지로 느렸습니다.ㅠㅠ
> 늦어 지는건 맞다

# 13. doc에 firebase_app_distribution_deploy.yml 에 올려놨는데 혹시 제가 잘못 작성한건지 봐주세요.
> Lint, DevRelease deploy

# 14. remote랑 local 데이터를 합쳐서 가공하는 방법 찾으니까 Remote Mediator가 나왔거든요.
# 캐싱용으로 쓰는 DB를 따로 만들어서 저장해서 쓰던데 
# DB에 꼭 저장해놓아야 하는 걸까요?
# 캐싱을 쓰지않고 구현하려고 애써봤는데 잘 안되더라구요.
# https://developer.android.com/topic/libraries/architecture/paging/v3-network-db?hl=ko
> ㄴㄴ > 오늘 알려 준 걸로 하세요 편하게......

# 15. UseCase 정리하다가 같은 뷰를 쓰는데 보여주는 내용만 다르면 이거는 다른 UseCase인가요..?
# usecase.puml로 그리긴 했는데^_ㅠ... 이게 맞는건지 모르겠어요.
# 이번 케이스의 경우에도 상세페이지를 보여주는 데이터가 2가지로 나뉜다고 생각했는데 따로 케이스를 나눠야됐는지 너무 헷갈렸어요.
> 다이어그램은 각자 페이지 입장에서 각자 그리면 편합니다
> 
---------
2024.08.14

# Usecase 다시 그리긴 했습니당^_ㅠ..
https://drive.google.com/file/d/1zxJV-yLTskYP7efE-lgbpXaZeRv4Itra/view?usp=sharing
> OK

# 16. Cache data를 쓰기에 유리한 환경은 어떤걸까요?
> 질문을 바꿔서 불리한 환경을 고민해보면 됩니다 / 대부분은 캐시 쓰는게 좋다

# 17. 예전에 debug 빌드때는 문제없었는데 release build 때 난독화때문에 문제가 생긴 적 있었는데요.
# 혹시 테스트코드도 release build후에 난독화와 같은 영향을 미치나요?
> D8 / R8 

# 18. 회사코드 리팩토링 할 때 안정성을 생각한다면 지켜야 할 순서같은게 있나요?
> 1. TestCode 작성
> 2. 옮기기
> 3. 설계 -> 코딩
> 4. 삭제 -> 변경

# 19. Room DB에 캐시데이터를 활용하려고 했거든요.. 
# 홈 메인 데이터가 서버에서도 하루에 1번 업데이트 된다고 들어서 
# 저희도 그 주기에 맞춰서 하루에 한번 서버에서 데이터 받아오는게 어떠냐고
# 저희 개발팀장에게 여쭤봤더니 앱 용량이 늘어나서 사람들이 삭제한다고 안된다고 하더라구요.
# 19-1. 캐시데이터를 쓰는게 맞는 상황일까요?
# 19-2. 개발팀장의 말은 맞는 말인가요?
> Youtube 사례 -> Cache 정책, 관리

-----------
2024.08.21

# 20. 이번 작업할때..
# usecase 작성하다가 이 데이터 필요하다 해서 data 폴더로 이동하고 
# data 작성하고 다시 usecase 연동해서 다시 개발하고 이런식으로 가는데
# 이것도 설계를 하지 않아서 생기는 과정인가요? 이쪽도 설계를 한다면 어떤 uml을 그려야 하나요?
> 설계 할때랑 개발 할 때는 좀 다를 수 있다.

# 21. 휴대폰별 하드웨어 이슈는 보통 어떻게 대응하나요..? 테스트 휴대폰은 보통 어떻게 구비하나요?
> 대응 불가 -> 테스트 휴대폰 구매
> S23, S22, (Plus, Note) 플립 X, 폴드

# 22. Rx, Coroutine.
# 예전에는 Rx를 쓰는게 필수였던거같은데 어느순간 대부분 Coroutine으로 바뀌었잖아요.
# 그럼에도 불구하고 Rx를 쓰는 회사들은 어떤 이점이 있어서 쓰는걸까요?
# 그리고 Coroutine에 대해서 조금 더 깊이있게 설명한거 보니까
# 비선점형 멀티태스킹에 서브루틴??? 뭐 이런게 나오긴 하는데 잘 모르겠습니다..^_ㅠ...
> 간단해요 -> Rx는 라이브러리고, Coroutine은 언어 기능입니다.

# 23. compileSdkVersion, targetSdkVersion은 보통 일치시키는걸로 아는데 실무에서 버전을 다르게 쓰는 경우도 있나요?
> 80~90% 일치, 10~20% 다름

# 24. 현재 회사 앱 navigation xml의 모든 화면에 전부 다 global action으로만 되어있거든요. -> Toss
# global action으로 설정했었을 때 이점이 있어서 이런건지... global action으로 설정하는 이유는 뭘까요?
# 리팩토링 해야될거같은데.. 딥링크도 무섭고... 푸시알림에 따라서 화면이동하는것도 있어서 쫄리는데...
# 그대로 둬야될까요...?
> Architecture
> SDD -> Server Driven Design
> 

-----------
2024.08.28
# 25. 네이버 아이디 로그인은 왜 자꾸 key 문제가 생기는건가요? 4.2.6에서 벗어날 수가 없습니다.
# 네이버 아이디 로그인 SDK가 해결해주길 기다려야되나요..?
# 그리고 구글 문제와도 엮여있다고 하셨는데 그게 무엇인지 궁금합니다.
> KeyStore 방식 변경, 초기화 이슈

# 26. 지금 androidTest 코드를 github action으로 돌리는 스크립트를 작성하고 있는데
# emulator를 실행시켜야 하잖아요.
# https://github.com/ReactiveCircus/android-emulator-runner 이걸써서 첨에 했는데 34 버전 테스트 하려고 하니까
# 실행도 안되고.. 보고된 이슈 항목에도 저랑 같은 이슈인 분 있어서
# 어제 뒤지다가
# emulator 실행 cmd로 작성하는 방법 코드 제공해주신 분 덕분에 해결했는데
# https://medium.com/innovies-club/run-android-emulator-out-of-the-box-with-github-actions-b84cba766e62
# 작업했던 코드는 doc/emulator에 넣어놨습니다.
# 26-1) 유지보수성을 위해서는 github action marketplace에 있는 것을 쓰는게 좋을지 아니면 이대로 두는게 좋을지 모르겠습니다.
> Star, Use
 # 26-2) 그리고 앱 개발할 때 cmd로 에뮬레이터를 실행시키는 경우는 어떤 경우인지도 궁금합니다.
> Test 

# 27. 워크플로로 공통 작업을 작성할 수 있다고 하는데
# 이게 작업을 하다보니까
# jobs:
#   A 작업
#   B 작업
# 이렇게 되어있으면 A 작업과 B 작업은 독립적이더라구요. 코드 체크아웃도 각각 다 받아줘야되고 설정도 똑같이 또 해줘야되고 말아죠.
# 27-1) uses를 써도 작업 순서만 정해주는 거지 A작업을 이어받아서 B를 한다던지 그런게 없는거 같은데.. 제대로 파악하고 있는건가요...?
> Cache, Upload, TempFolder
# 27-2) workflow_call ? workflow_dispatch? 둘다 역할이 비슷한거 같은데.. 무슨 차이인지도 궁금합니다.
# 자동실행 수동실행이라고 하긴하던데... 다른 작업에 갖다쓰는거면 어짜피 그 작업에서 실행시켜야 실행이 될텐데 무슨 차이인지 잘 모르겠습니다.

# 28. 효율적인 디버깅 방법이 있나요?
# 저는 일단 'Debug App'로 빌드한 다음에 디버그 위치마다 툭툭 찍어가면서 하고 있슴다.
# 근데 그냥 'Run app' 한 다음에 로그 찍어놓은거 보면서 하는 분들도 있더라구요.
# 이 방법들 말고 다른 방법이나 조금 더 효율적으로 할 방법이 있는지 궁금합니다.
> 1. Lifecycle, 
> 2. Method Call ( Parameter, Return )
> 3. DataSource, Repository, UseCase


-------
2024.09.25
# 29. 정리가 안되서 그려왔어요..
# https://drive.google.com/file/d/1CVxXnXlpm86geDsw8HUqxTpHDnROpEmu/view?usp=sharing

# 30. 멀티모듈 폴더링 관련
# 30-1) 현재 navigation 폴더가 :feature:main 내에 있는데 navigation도 module을 따로 나눠야될까요?
# 30-2) 각 feature 별로 debug, release, main이 다 있어야 될까요?
# 30-3) 빈혈클래스는 :domain에 넣어놓고 정의했는데, :feature:A:ui 의 ViewModel에서 이 빈혈 클래스를 쓸 때 :domain을 implement 해야되나요?
#       현재는 :feature:A:domain에 한번 더 정의해놓고 :feature:A:ui 에 :domain을 implementation 하지 않았습니다.
# ex)
# 
# package com.wooyj.picsum.feature.favdetail.domain
#
# @Reusable
# class RemoveFavoriteNotVisibleUseCase
#   @Inject
#   constructor(
#      private val usecase: RemoveFavoriteNotVisibleUseCase,
#   ) {
#      suspend operator fun invoke() = usecase()
#   }


# 31. 멀티모듈 빌드 관련
#     build varient에서 :feature:a:ui는 release 선택된 상태에서 :app을 debug로 빌드하면... :app 내에서 :feature:a:ui는 release로 빌드되나요?

# 32. detail 화면 관련
#     맨 처음 :feature:detail 화면 빌드 시 savedStateHandle 데이터가 없어서 죽는데, 이럴 때는 해당 데이터를 강제로 넣어주어야 할까요?

# 33. feature 모듈 관련
#     Screen 하나당 feature 하나씩이라고 생각하면 되는걸까요?



