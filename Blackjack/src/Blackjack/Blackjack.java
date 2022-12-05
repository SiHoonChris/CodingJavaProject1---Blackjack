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
		B.BettingDefault(); // (게임 시작 전) 베팅 관련 설정

		while(true) {
			// Turn 시작 전 최초 베팅(FirstBetting()) (게임 중에 이뤄지는 베팅은 InGame.java에서)
			IG.PlayBlackjack();
			int cardsUsed = CD.cardsInTheDeck(GH.Dealer, GH.Player);
			CD.Renew(CD.gamingDeck, CD.Deck, cardsUsed);
		}
	}
	
} // end - public class Blackjack {}