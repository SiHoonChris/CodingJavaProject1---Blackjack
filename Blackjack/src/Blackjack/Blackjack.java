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
			if(!CD.betSystemOff)B.FirstBetting();
			IG.PlayBlackjack();
			int cardsUsed = CD.cardsInTheDeck(GH.Dealer, GH.Player);
			B.BettingResult();
			CD.Renew(CD.gamingDeck, CD.Deck, cardsUsed);
		}
	}
	
} // end - public class Blackjack {}