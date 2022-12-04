package Blackjack;

// 상속 관련 내용 참고
// https://kephilab.tistory.com/56
// https://blog.devez.net/ko/99

class Card {
	String pattern;
	String number;
	
	Card(){}
	Card(int x, int y) { // 카드 생성
		switch(x){
		case 1: pattern="SPADE"; break;
		case 2: pattern="CLOVER"; break;
		case 3: pattern="DIAMOND"; break;
		case 4: pattern="HEART"; break;
		}
		switch(y){
		case 1: number="A"; break;
		case 11: number="J"; break;
		case 12: number="Q"; break;
		case 13: number="K"; break;
		default: number=y+"";
		}
	}
	
} // END - class Card {}

//CardDeck(), Shuffle(), Converter(), cardsInTheDeck(int[] Dealer, int[] Player), Renew()
public class CardDeck extends Card {
	
	public static Card[] Deck = new Card[52];
	public static int[] gamingDeck = new int[52];
	int unusedCard = Deck.length; // cardsInTheDeck()
	int sum = 0; // 한 턴에 사용한 카드 장 수 누적 - renew()
	
	CardDeck() { // 카드덱(배열) 생성
		int idx=0;
		
		for(int x=1; x<=4; x++) {
			for(int y=1; y<=13; y++) {
				Deck[idx++] = new Card(x, y);
			}
		}
	} // END - CardDeck()
	
	public void Shuffle() { // 카드 섞기
		for(int i=1; i<=1000; i++) {
			int num   = (int)(Math.random()*52);
			Card temp = Deck[0];
			Deck[0]   = Deck[num];
			Deck[num] = temp;
		}
	} // END - public void shuffle()
	
	public int[] Converter() { // 개별 카드의 값을 계산하기 위해 Card형 데이터를 int형으로 전환
		for(int i=0; i<Deck.length; i++) {
			if(Deck[i].number=="K"||Deck[i].number=="Q"||Deck[i].number=="J")
				{ gamingDeck[i]=10; }
			else if(Deck[i].number=="A")
				{ gamingDeck[i]=1; }
			else
				{ int num = Integer.parseInt(Deck[i].number);
				  gamingDeck[i]=num; }
		}
		return gamingDeck;
	} // END - public int[] Converter()

	public int cardsInTheDeck(int[] Dealer, int[] Player) { // 남은 카드 수 계산
		int dealerUsed=0;
		int playerUsed=0;
		
		for(int i=0; i<Dealer.length; i++) { if(Dealer[i]!=0) dealerUsed++; }
		for(int i=0; i<Player.length; i++) { if(Player[i]!=0) playerUsed++; }

		unusedCard -= (dealerUsed+playerUsed);
		System.out.println("\n( Cards in a Deck : "+unusedCard+" )");
			
		return dealerUsed + playerUsed;
	} // END - public int cardsInTheDeck(int[] Dealer, int[] Player)
		
	public void Renew(int[] ar1, Card[] ar2, int num) { // 카드덱(배열) 최신화 : 사용된 카드들은 지우고 후속 카드들을 앞으로 가져옴
	// 첫 번째 매개변수(gamingDeck), 두 번째 매개변수(Deck), 세 번째 매개변수(한 턴에 사용한 카드 수 : dealerUsed + playerUsed) 
		sum += num;
		for(int i=0; i<ar1.length; i++) {
			if(i<ar1.length-sum) {
				ar1[i]=ar1[i+num];
				ar2[i]=ar2[i+num]; }
			else { 
				ar1[i]=0;
				ar2[i]=null;}
		}

	} // END - public void Renew(int[] ar1, Card[] ar2, int num)
	
} // End - public class CardDeck extends Card {}