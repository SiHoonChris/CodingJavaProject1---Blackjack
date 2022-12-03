package Blackjack;


public class Blackjack{
	
	public static void main(String[] args){
		GameHost GH = new GameHost();
		CardDeck CD = new CardDeck();
		InGame IG = new InGame();
		
		GH.GameOpener();
		CD.Shuffle();
		CD.Converter();
		
		while(true) {
			IG.PlayBlackjack();
			int cardsUsed = CD.cardsInTheDeck(GH.Dealer, GH.Player);
			CD.Renew(CD.gamingDeck, CD.Deck, cardsUsed);
		}
	}
	
} // end - public class Blackjack {}