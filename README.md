# CodingJavaProject1 - Blackjack (22.10.19 ~ 22.10.27)
![blackjack_game_start](https://user-images.githubusercontent.com/109140000/206840638-fc655fc7-6e7e-49e1-b431-f34f383851ce.png)  
![blackjack_game_ing](https://user-images.githubusercontent.com/109140000/206840642-b597c0ba-c885-4f71-988d-22963822342a.png)  
![blackjack_game_end](https://user-images.githubusercontent.com/109140000/206840649-11896ccf-847f-4e93-ad92-a310b47c8019.png)  

### Description  
&nbsp;&nbsp;&nbsp;&nbsp;JAVA로 구현한 블랙잭 게임

### Tech Stack  
&nbsp;&nbsp;&nbsp;&nbsp;Java (SE 11)  
&nbsp;&nbsp;&nbsp;&nbsp;(Tool : Eclipse)

### Additional  
- 카드 호출 방식 (배열 내부의 값 수정 방식)  
![Array_of_Blackjack](https://user-images.githubusercontent.com/109140000/206840661-d630d2c1-4490-4986-b892-f4c8a2e3c7ba.png)  
- 규칙
1) 게임의 진행 및 운영 등 전반적인 규칙은 기존 블랙잭과 동일하다. <br>
그렇기에, 기존의 블랙잭 규칙에서 축소/추가된 내용만을 다루면 다음과 같다.
2) 축소된 규칙  
&nbsp; - Dealer는 본인이 받은 카드의 합이 17 이상의 수가 되지 않았을 시, 단 한 장의 카드만을 받게 된다.
3) 추가된 규칙  
3-1. 베팅과 관련된 규칙  
&nbsp; - 게임 시작 전, Player가 게임에 참여할 때 보유한 전체 금액에 따라, 최소 베팅 금액과 최대 베팅 금액이 설정된다.  
&nbsp; &nbsp;   이 값은 전체 게임이 종료될 때까지 고정된다.  
&nbsp; - Player는 지정된 최소 베팅 금액 미만으로 베팅할 수 없으며, 지정된 최대 베팅 금액을 초과하여 베팅할 수 없다.  
&nbsp; &nbsp;   (Dealer의 베팅 금액은 설정된 최대 베팅 금액으로 고정된다.)  
&nbsp; - Player는 추가 카드를 받을 때마다 최소 베팅 금액만큼 베팅해야 한다.  
&nbsp; &nbsp;   (이후 그 게임에서 승리할 시, Player는 자신이 추가 카드에 대해 지불한 금액만큼을 Dealer에게서 추가로 보상 받을 수 있다.)  
&nbsp; - 참가자들이 베팅한 금액은 승부가 결정될 때까지, 가상의 공간 'Bank'에 저장되는데,  
&nbsp; &nbsp;   무승부로 게임이 끝났을 시, 참가자들이 베팅한 금액은 Bank에 저장된 상태로 다음 게임으로 넘어간다.  
&nbsp; &nbsp;   이후 게임에서 결정된 승자가 Bank에 모인 모든 금액을 가져간다.  
&nbsp; - 60초 안에 한 게임이 끝나지 않으면, 게임의 결과에 상관없이 Player는 Dealer에게 최소 베팅 금액만큼을 패널티로 지불해야 한다.  
&nbsp; - 한 참가자가 보유한 카드의 합이 '21'인 상태로 게임이 종료됐을 시,  
&nbsp; &nbsp; 승리한 참가자는 패배한 참가자로부터 자신이 베팅한 금액만큼을 추가로 받게 된다.  
&nbsp; &nbsp;   (이 때, 추가 카드에 따른 베팅 금액은 제외한다.)  
3-2. 게임 종료와 관련된 규칙  
&nbsp; - 카드 덱에 남아 있는 카드의 수가 4장 미만일 시, 전체 게임은 종료 된다.  
&nbsp; - 카드 덱에 남아 있는 카드의 수가 4장일 때, Player는 마지막 게임을 진행할 것인지 말 것인지 선택할 수 있다.  
&nbsp; - 마지막 게임이 무승부로 끝나거나 승부 없이 끝난 경우(카드가 4장 남은 상황에서 Player가 게임 진행을 거절한 경우)  
&nbsp; &nbsp;   참가자들은 Bank에 모인 금액을 절반으로 나눠 갖는다  
&nbsp; &nbsp;   (그 금액이 홀수인 경우, Player에게 유리하게 계산된다)  
&nbsp; - 참가자가 보유한 금액의 크기가, 각 참가자에게 지정된 최소 베팅 금액보다 작아질 시, 전체 게임은 종료된다.  
&nbsp; &nbsp;   (Dealer의 최소 베팅 금액은 최대 베팅 금액과 같다.)  
&nbsp; - 게임 진행 중, 한 참가자(A)가 상대 참가자(B)에게 지불해야 할 금액의 크기가  
&nbsp; &nbsp;   그 참가자(A)의 보유 금액보다 커져서 지불할 수 없게 된 경우  
&nbsp; &nbsp;   그 참가자(A)가 보유한 금액 전부를 상대 참가자(B)에게 지급하고 전체 게임을 종료한다.  


