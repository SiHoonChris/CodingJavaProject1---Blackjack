package Blackjack;


public class Blackjack{
	
	public static void main(String[] args){
		GameHost GH = new GameHost();
		CardDeck CD = new CardDeck();
		InGame IG = new InGame();
		Betting B = new Betting();
	
		GH.GameOpener();
		CD.Shuffle();
		CD.Converter();
		B.BettingDefault();
		
		while(true) { // 반복문 시작
			
			// 만약 이전 게임에서 남은 카드가 4장 미만이라면 다음 Turn의 게임 시작 전에 베팅 못하고, 바로 게임 종료
			if(!CardDeck.betSystemOff)  B.FirstBetting(); 
			
			// 카드는 4장 미만(마지막 게임)이면서 무승부로 게임 종료, bank에 참가자들이 베팅한 돈이 남아 있을 때 : 반반 나눔
			if(CardDeck.betSystemOff==true && Betting.bank!=0) {
				B.DrawEnd();
				B.BettingResult();  }  
			
			// 게임 진행(남은 카드가 4장 미만일 때 게임 종료 - 게임 종료 코드는 메서드 내부에서 실행)
			IG.PlayBlackjack(); 

			// 베팅 결과 출력
			if(InGame.fourCardHalt) B.DrawEnd(); //4장의 카드만 남은 상황에서 게임 종료 시, 이 때도 금액 계산하여 출력 
			B.BettingResult();
			if(CardDeck.Deck.length==4) GH.GameCloser();
			
			 // 남은 카드 계산
			int cardsUsed = CD.cardsInTheDeck(GameHost.Dealer, GameHost.Player);
			
			// Turn 종료 후, Player 또는 Dealer의 금액 보유량이 각자의 최소 베팅 금액보다 적어질 경우 바로 게임 종료
			if(Betting.dealerWallet<Betting.maximumBet||Betting.playerWallet<Betting.minimumBet) { 
				if(Betting.bank!=0) { // Turn이 무승부로 종료됐을 시
					B.DrawEnd();
					B.BettingResult();
					GH.GameCloser();
				}
				else  GH.GameCloser(); 
			}
			
			// 카드 배열 최신화(사용한 카드는 제거하고 후속 카드는 앞으로 가져옴)
			CD.Renew(CardDeck.gamingDeck, CardDeck.Deck, cardsUsed);
			
		} // END - while(true) , 반복문 끝
		
	} // END - public static void main(String[] args)
	
} // END - public class Blackjack {}