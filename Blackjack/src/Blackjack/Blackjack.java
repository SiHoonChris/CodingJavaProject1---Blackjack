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
		
		while(true) {
			if(!CardDeck.betSystemOff) {
				B.FirstBetting();
			} // 만약 이전 게임에서 남은 카드가 4장 미만이라면 다음 Turn 게임 시작 전에 베팅 못하고, 바로 게임 종료
			if(CardDeck.betSystemOff==true && Betting.bank!=0) {
				Betting.bank -= Math.ceil(Betting.bank/2);
				Betting.playerWallet += Math.ceil(Betting.bank/2);
				Betting.dealerWallet += Betting.bank;
				B.BettingResult();
			} // 카드는 4장 미만 & 무승부로 게임 종료, bank에 참가자들이 베팅한 돈이 남아 있을 때 : 반반 나눔 
			
			IG.PlayBlackjack(); // 게임 진행(남은 카드가 4장 미만일 때 게임 종료)
			int cardsUsed = CD.cardsInTheDeck(GameHost.Dealer, GameHost.Player); // 남은 카드 계산
			B.BettingResult();
			if(Betting.dealerWallet<Betting.maximumBet||Betting.playerWallet<Betting.minimumBet) { 
				// 1. 게임 종료 후, Player 또는 Dealer의 금액 보유량이 각자의 최소 베팅 금액보다 적어질 경우 바로 게임 종료
				// 2. 무승부로 게임 종료 시, Bank 안에 있는 금액을 반반 나눠 가짐
				if(Betting.bank!=0) {
					Betting.bank -= Math.ceil(Betting.bank/2);
					Betting.playerWallet += Math.ceil(Betting.bank/2);
					Betting.dealerWallet += Betting.bank;
					B.BettingResult();
					GH.GameCloser();
				}
				else { GH.GameCloser(); }
			}
			CD.Renew(CardDeck.gamingDeck, CardDeck.Deck, cardsUsed);
		}
	}
	//- 마지막 경기가 무승부로 끝나면, bank에 모인 돈 반반 나눠가짐(bank에 모인 돈이 홀수일 경우 Player가 더 받는다.)
	
} // end - public class Blackjack {}