# Programmers
https://programmers.co.kr/ 에서 제공하는 알고리즘을 풀었습니다.

# Todo
***알고리즘 시간복잡도를 최우선으로 두지 않습니다.***

실제 프로그래밍 일을 진행 할 때, 필요할 요소를 고려하여 작성했습니다.
- 코드 가독성
  - `Java8 Stream API`로 가독성을 높일 수 있는 코드는 최대한 `Stream`을 이용했습니다.
  
- 객체지향 캡슐화
  - 요구사항을 `클래스로 정의`했습니다.
  - `디미터 법칙`을 최대한 적용했습니다.
  
해당 문제를 푸는것 보다, 새로운 문제를 받았을 때, 접근할 수 있는 힘을 기르는것이 더 중요하다고 생각합니다.
- 문제를 못 풀었을시, 답안을 참고 하되 배운점을 토대로 `생각하는 방법`에 대해 기술 했습니다.
- 답안을 참고 해도, 이상하다 싶은 문제는 `이상한 이유`에 대해 기술 했습니다. (개인의견이라 틀릴 수 있음.)

알고리즘 개념에 대해 정리하고 공부합니다.
 - 외우는것이 아닌 이해하는걸 목표로 글로 작성하여, 설명했습니다.
 - 알고리즘 개념 공부 리스트를 참고해주세요.
 
# Algorithm Concept
- [퀵정렬 알고리즘 개념 분석하기.](https://mommoo.tistory.com/91)

# UnSolved List
도전 했지만 풀지 못한 문제 리스트 입니다.

| level |      name       | reason |
| :---: | :-------------: |:------|
|2 | H-Index| 정렬 후, 큰 값에서 차례대로, h번 이상 인용된 논문을 찾는 코드에서 무엇이 틀렸는지 모르겠습니다.
|  3   | 종이접기        |
|   3   |   N으로 표현    |
| 3  |  입국심사 | 시간 기준 바이너리 서치로 문제를 해결하는 것은 좋지만, 발견된 해 보다 더 작은 시간이 있을 수 있다는 조건이 이상한거 같습니다. 
|   3   | 브라이언의 고민 |
|   4   | 4단 고음    |


# What Learned
| level |      name       | reason |
| :---: | :-------------: |:-----|
|2|조이스틱|바꿀 문자열을 찾는 알고리즘를 그리디로 해결 해야하는지, NP로 해결해야 하는지 감이 오지 않은거 같습니다. 그리디 vs NP에 대해서 학습해야 합니다.|
|2|라면공장|스톡을 날짜에 맞춰 사용하는 불필요한 코딩을 하여, 알고리즘이 더 어려워졌습니다. 요구사항이 아닐시엔, 필요없는 로직은 작성하지 않기로 했습니다. 또한, 잘못된 동시성 생각이 이문제를 어렵게 생각한 이유습니다. 특정 날짜를 지나치면 그 날짜를 다시 못고를거라 생각했지만, 뒤늦게라도 그 날짜를 추가해주면 과거에 그날짜를 선택하고 나아간것처럼 만들 수 있어서, 최대 힙 자료구조로도 풀 수 있는 형태로 바꿀 수 있다는것을 배웠습니다.
|2|영어 끝말잇기|끝에 다다르면, 다시 처음 순서로 돌아오는 구현을 Queue 로 시도했습니다. 이것도 나쁘진 않지만 컬럼의 수가 정해져 있다면, `%`연산과 `/` 연산으로 `column` , `row` 를 구하면 더 쉬운 코드가 됩니다. `column` = n % index, `row` = n / index 
|3|입국심사|완전 탐색이 아닌, 최소로 걸리는 시간과 최대로 걸리는 시간을 구할 수 있으면 그 시간 사이 에서의 경우로 문제해결을 시도할 수 있습니다.| 