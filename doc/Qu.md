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
