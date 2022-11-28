# CodingJavaProject1 - Blackjack (22.10.19 ~ 22.10.27)

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">  

![blackjack_on_game1](https://user-images.githubusercontent.com/109140000/202410443-f9565e2e-2732-4c02-bacd-853f4792545b.png)  

![blackjack_on_game2](https://user-images.githubusercontent.com/109140000/202410433-23d07894-418a-4ee3-9cbd-e20a7ad6fc19.png)  


### 목차
개발 동기 - 설계 - 후기 - 개선 사항 - 참고

## 개발 동기  
- 블랙잭 제작
- 평소에 복습 겸, 수업이 끝나고 나면 배운 내용들을 활용해 간단한 프로그램들을 만들고는 했다.
(참고 : Repository - JavaStudy : https://github.com/SiHoonChris/JavaStudy/commits/master/JVs/src/SelfMade )
그러던 중, 프로그램을 만드는 것보다 무엇을 만들지 생각해내는 것이 더 어려워지던 시점,
같이 학원에서 수업을 듣는 형이 자신이 지금 만들고 있는 프로그램을 나도 한 번 만들어 볼 것을 권유했다.
그것은 블랙잭이었다.
- 정확한 정보인지는 모르겠지만, 그 형의 말에 따르면 2017년도 '배달의 민족' 입사 문제였다고 한다.
- 흥미가 생긴 나는 도전하기로 한다.

## 설계
(전체 코드 구조)  
(카드 호출 방식 : 배열)  

## 후기  
학원에서 자바를 처음 경험하고 나서, 내 손으로 직접 만들어본 자바 프로그램들 중 제일 긴 프로그램이었다 - 
이후 Minesweeper( https://github.com/SiHoonChris/CodingJavaProject2_Minesweeper )를 만들기 전까지는.
그래서 다 만들고 나서 매우 뿌듯했고, 한편으로는 후련했다. 이 프로그램을 만들 당시 나는 학원에서는 한창 객체지향 개념을
배우고 있었다. 그래서 어떻게든, 한 번이라도 그 수업 내용들을 내 손으로 직접 활용해보고자 하는 욕심이 있었다.
그 때문인지, 이 글을 쓰고 있는 지금 시점(2022.11.28)에서 다시 내 코드를 훑어 봤을 때, 불필요하고 억지스러운 부분도 어느 정도 보이고,
수정할 점들과 개선할 점들도 다소 보인다. 이 프로그램을 한창 만들고 있었을 때가 자바를 배우기 시작한지 한 달 정도 된 시점이었는데,
한 달 배운 다른 사람들의 수준이 어느 정도인지는 잘 모르겠으나, 나 스스로는 잘했다고 생각한다.

이 프로그램을 만들면서 전반적으로 어려웠던 점은 머릿속에 있는 아이디어들을 코드로 구현해 내는 것이었다. 고민에 고민을 거쳐
기발한 아이디어를 생각해내도 그것을 어떻게 코드로 작성할지 막막할 때가 많았으며, 어떨 때는 '현재 내 수준에서는 불가능하다'라는
판단에 포기하고 다른 방법을 찾기도 했다.
어려웠던 점들을 구체적으로 떠올려 보면 다음과 같다.

어려웠던 점 0) Match에서 사용된 마지막 카드의 후속 카드들을 배열에서 어떻게 호출할 것인가  
어려웠던 점 1) 남은 카드 수 계산 시, 처음에만 계산이 되고 이후에는 몇 장을 소모하든 계산이 되지 않음  
어려웠던 점 2) 배열 내부의 값들을 호출하는 방식으로 인해 예외 발생 : 남아있는 카드가 4장 이하일 시 정상적인 게임 불가   

## 개선 사항 
- Blackjack  
(이 프로그램을 처음 만들 때는 배운게 많지 않아서 활용하거나 시도할 방법들이 별로 없었다.
하지만 지금은 그 때보다 더 배웠고 경험도 쌓였으니, 그 동안 배운 내용들과 경험들을 최대한 활용할 수 있도록 하자.)  
1) 코드 정리 : 더 직관적이고 심플하게. 관리가 용이하고, 작동 원리를 이해/파악하기 쉽게<br>
2) --게임 중 hit/stay에 대한 선택 시, 대소문자 구분 없이 입력되도록 코드 수정-- (2022.11.27 수정 완료)  
3) 게임 시작 동의 여부를 묻는 도입부 만들기. <br>('Yes'일 경우 5초 카운트하고 시작, 'No'일 경우 바로 프로그램 종료)   
4) Match가 이뤄질 때마다 전적(승무패) 표시  
5) 베팅 기능 추가 : 게임 진행 시 얼마를 걸었으며, 얼마를 잃거나 얻었고, 게임 종료 후 최종 결과가 어떻게 되는지 출력  
6) 카드가 단 4장 남았을시, 게임 진행 동의 여부 묻기('Yes'일 경우 베팅 걸고 마지막 Match 진행, 'No'일 경우 패널티 금액 내고 경기 종료)<br>
(기존 코드에서는 카드가 단 4장 남았을 시, 프로그램이 자동으로 Match를 바로 진행.)   
7) 'Match'보다는 'Turn'이 더 어울릴 것 같다. 그렇게 중요한 부분은 아니니, 다른 개선사항들 먼저 마치고 나서 나중에 실시   

- SiHoonChris
1) '초심자의 행운', '소 뒷걸음질 치다 쥐 잡는 격'. 이런 방식의 해결이 많았는데 이제 운이 아니라 실력으로 해결 방법을 찾아내는 경우가 더 많아져야 할 것이다.  
2) 막히거나 어려움이 있었다면 자세하게 기록해두자. 그리고 다시 보면서, 내가 어떤 부분에서 어려움이 있었고 그것을 어떻게 해결했는지 파악하고,
더 나은 방법은 없었는지 생각해보면서 공부하자.

## 참고
- 11-17-2022 부, 별도 관리
- 현재 Repository에서의 기록  
11-17-2022 / JavaStudy에 있던 프로그램 파일 현Repo로 이전  
11-27-2022 / hit/stay 입력시 대소문자 구분 없앰  

- 이전 Repository : JavaStudy
- JavaStudy 내의 Commit 기록들  
(10-19-2022) &nbsp; https://github.com/SiHoonChris/JavaStudy/commit/4cb7c10c8469d6c5b418385774517e116f8d1fd4  
(10-20-2022) &nbsp; https://github.com/SiHoonChris/JavaStudy/commit/b03d5f3a5eb06121c930cd74359c4fe455ed9463  
(10-21-2022) &nbsp; https://github.com/SiHoonChris/JavaStudy/commit/0443ceddd06bbabaf0fa4651f3588bc1e82d0ada  
(10-22-2022) &nbsp; https://github.com/SiHoonChris/JavaStudy/commit/6974413870c21b48baf140a8d13f18c51ed9ba71  
(10-23-2022) &nbsp; https://github.com/SiHoonChris/JavaStudy/commit/1710c51f52bd9c5bb7fb89049dbfe2217f4b93b5  
(10-24-2022) &nbsp; https://github.com/SiHoonChris/JavaStudy/commit/3883b58b9d55c4bfbe46e8adfdbda3fc4dd9be2b  
(10-25-2022) &nbsp; https://github.com/SiHoonChris/JavaStudy/commit/c222508e2be73998a19f73b097231e75799b6547  
(10-26-2022) &nbsp; https://github.com/SiHoonChris/JavaStudy/commit/c5f8895a5ad644eca09dad834c3d03ea1259d085  
(10-27-2022) &nbsp; https://github.com/SiHoonChris/JavaStudy/commit/f8729d5d7bd4d09eff7aeb82f24f4b115bd7953d  
