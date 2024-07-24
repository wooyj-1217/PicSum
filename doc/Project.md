# Project 진행

## As - Is
Ui -> ViewModel -> Api 연결한다
Test 안되면 백엔드 싸운다 -> 다시 맞춘다 -> 다시 싸운다 -> 다시 맞춘다 -> Test -> Depoly

## To-Be
1. Ui(Figma / ppt) -> State 추출 / 생성 / 구성
2. Api interface(Json model)
    1. null 사용금지(구두전설....., 문서없어... 근데 뭐 해달래..)
        1. price : null -> 상품이 품절표시, 상품을 보여주지 마세요
        2. isVisible
    2. list -> [] empty
3. UseCase 찾기
    1. 사용자가 할 행동들 (Click, Action)
    2. 내가 시킬 행동 (Init, Save, Load)
        1. ex) LoadLatestTab
        2. ex) getList -> getRemoteList, getLocalList
4. ViewModel 설계 (UseCase 구성, 요구사항 해결)
    1. usecase -> state
5. Ui - Presentation(Android, iOS, Flutter, Web, Backend)