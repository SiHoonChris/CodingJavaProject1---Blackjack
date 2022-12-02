package Blackjack;


import java.util.Scanner;
import java.util.Arrays;


// Methods : getYourCards(), , whoWins(), finalMatch()
// whatIsYourAce(), hitOrStay() => GameHost.java(player의 선택이 필요한 내용/코드들 모아둠)로 이동
// getYourCards(), , whoWins(), finalMatch()는 따로 클래스 파일(InGame.java : Game 진행에 필요한 핵심적인 과정들 모음) 만들어서 거기로 이동
// Blackjack.java는 메인 메서드만 남기기(프로그램 실행용)

class onTheTable{
	int[] Dealer = new int[3];
	int[] Player = new int[12];
	int Dealer_scr;
	int Player_scr;
	int choice=0;
	int score;
	int matchCnt=0;
	int[] dealerStats = {0, 0, 0};  // 승, 무, 패
	int[] playerStats = {0, 0, 0};
	
	Scanner sc = new Scanner(System.in);
	CardDeck CD = new CardDeck();
	GameHost GH = new GameHost();
	
	// 1. 카드 뿌리기
	public void getYourCards() {
		try {
			for(int i=0; i<Dealer.length; i++) Dealer[i]=0;
			for(int i=0; i<Player.length; i++) Player[i]=0;
			// 메인 메서드에서 반복문 적용 => 코드 시작부분에서 최신화 안해줄시 계산 오류 생김
			// 예를들어) 이전 turn에서 3장 받았는데 이번 turn에서 2장 받으면, 이전에 받은 3번째 카드가 그대로 남아있음
			
			Player[0]=CD.gamingDeck[0];
			Dealer[0]=CD.gamingDeck[1];
			Player[1]=CD.gamingDeck[2];
			Dealer[1]=CD.gamingDeck[3];
			
			// 딜러 파트
			if(Dealer[0]+Dealer[1]<=11 && (CD.Deck[1].number=="A" || CD.Deck[3].number=="A"))
			{   if(CD.Deck[1].number=="A") Dealer[0]=11;
				else if(CD.Deck[3].number=="A") Dealer[1]=11;
				else if(CD.Deck[1].number=="A"&&CD.Deck[3].number=="A") Dealer[0]=11; }
			if(Dealer[0]+Dealer[1]<=16) { Dealer[2]=CD.gamingDeck[4]; }
			if(CD.Deck[4].number=="A" && Dealer[0]+Dealer[1]<11) { Dealer[2]=11; }
	
			matchCnt++;
			System.out.println("================= Turn "+matchCnt+" =================");
	
			System.out.print("[Dealer] =>  ");
			System.out.print(CD.Deck[1].pattern+"-"+CD.Deck[1].number+"\t");
			System.out.print(CD.Deck[3].pattern+"-"+CD.Deck[3].number +(Dealer[2]!=0 ? "\t" : "\n"));
			if(Dealer[2] != 0) {System.out.print(CD.Deck[4].pattern+"-"+CD.Deck[4].number+"\n");} 
			System.out.printf("=> [%02d]", Arrays.stream(Dealer).sum());
			System.out.println();
			
			// 플레이어 파트
			System.out.print("[Player] =>  ");
			System.out.print(CD.Deck[0].pattern+"-"+CD.Deck[0].number+"\t");
			System.out.print(CD.Deck[2].pattern+"-"+CD.Deck[2].number+"\n");
			if(CD.Deck[0].number=="A"||CD.Deck[2].number=="A") { whatIsYourAce(); }
			
			hitOrStay();
	
			System.out.printf("=> [%02d]", Arrays.stream(Player).sum());
			System.out.println();
			System.out.print("\n*RESULT* => ");
			
			// 매치 결과
			whoWins();
			System.out.printf("[Dealer] Win: %d | Draw : %d | Lose : %d\n", dealerStats[0], dealerStats[1], dealerStats[2]);
			System.out.printf("[Player] Win: %d | Draw : %d | Lose : %d\n", playerStats[0], playerStats[1], playerStats[2]);
			
		} catch(NullPointerException e) {
			int lastCards=0;
			for(int i=0; i<CD.Deck.length; i++) { if(CD.Deck[i]!=null) lastCards++; }
			
			if(lastCards==4) { 
				System.out.printf("================= Turn %d =================\n", ++matchCnt);
				finalMatch();
			}
			
			GH.GameCloser();
		}
		
	}
	
	// 2. A값 선택
	private void whatIsYourAce() {
		System.out.print("1 or 11 ? =>  ");
		int Answer = sc.nextInt();
		if(CD.Deck[0].number=="A" && Answer==11) { Player[0]=11; }
		else if(CD.Deck[2].number=="A" && Answer==11) { Player[1]=11; }
		else if(CD.Deck[0].number=="A" && CD.Deck[2].number=="A" && Answer==11)
		{ Player[0]=11; }
	}
	private void whatIsYourAce(int n) {
		System.out.print("1 or 11 ? =>  ");
		int Answer = sc.nextInt();
		if(Answer==11) {Player[n]=11;}
		else 		   { return;}
	}
	
	// 3. 플레이어 카드 추가
	private void hitOrStay() {
		if(Dealer[2]==0) {
			int num=2;
			for(int i=4 ; ; i++) {
				System.out.print("HIT or STAY ? =>  ");
				String select = sc.next();
				select = select.toUpperCase();
					if(select.equals("HIT")) {
						if(CD.gamingDeck[i]!=0) {
							Player[num]=CD.gamingDeck[i];
							System.out.print(CD.Deck[i].pattern+"-"+CD.Deck[i].number+"\n");
							if(CD.Deck[i].number=="A") { whatIsYourAce(num); }
							num++;
						}
						else { return; }
					} else if(select.equals("STAY")) { return; }
				if(Arrays.stream(Player).sum()>21) { return; }				
			}
		}
		else {
			int num=2;
			for(int i=5 ; ; i++) {
				System.out.print("HIT or STAY ? =>  ");
				String select = sc.next();
				select = select.toUpperCase();
					if(select.equals("HIT")) {						
						if(CD.gamingDeck[i]!=0) {
							Player[num]=CD.gamingDeck[i];
							System.out.print(CD.Deck[i].pattern+"-"+CD.Deck[i].number+"\n");
							if(CD.Deck[i].number=="A") { whatIsYourAce(num); }
							num++;
						} 
						else { return; }
					} else if(select.equals("STAY")) { return; }
				if(Arrays.stream(Player).sum()>21) { return; }
			}
		}
	}
	
	// 4. 승무패 결과
	private void whoWins() {
		Dealer_scr=Arrays.stream(Dealer).sum();
		Player_scr=Arrays.stream(Player).sum();
		
		if(Dealer_scr<=21 && Player_scr<=21) {
			if(Dealer_scr>Player_scr) { 
				System.out.println("[Dealer] WIN!");
				dealerStats[0]++;
				playerStats[2]++;
			}
			else if(Dealer_scr<Player_scr) {
				System.out.println("[Player] WIN!");
				playerStats[0]++;
				dealerStats[2]++;
			}
			else {
				System.out.println("- DRAW -");
				playerStats[1]++;
				dealerStats[1]++;
			}
		}
		else {
			if(Dealer_scr>21 && Player_scr<=21) {
				System.out.println("[Player] WIN!");
				playerStats[0]++;
				dealerStats[2]++;
			}
			else if(Player_scr>21 && Dealer_scr<=21) {
				System.out.println("[Dealer] WIN!");
				dealerStats[0]++;
				playerStats[2]++;
			}
			else {
				System.out.println("- DRAW -");
				playerStats[1]++;
				dealerStats[1]++;
			}
		}
	}
	
	// 5. 남은 카드가 4장일 때 자동으로 실행되는 메서드
	private void finalMatch() {
		GH.OnlyFourCards();
		
		if(GH.lastFourCard==1) {
			Player[0]=CD.gamingDeck[0];
			Dealer[0]=CD.gamingDeck[1]; 
			Player[1]=CD.gamingDeck[2];
			Dealer[1]=CD.gamingDeck[3];
			
			System.out.print("\n[Dealer] =>  ");
			System.out.print(CD.Deck[1].pattern+"-"+CD.Deck[1].number+"\t");
			System.out.print(CD.Deck[3].pattern+"-"+CD.Deck[3].number +(Dealer[2]!=0 ? "\t" : "\n"));
			if(Dealer[2] != 0) {System.out.print(CD.Deck[4].pattern+"-"+CD.Deck[4].number+"\n");} 
			System.out.printf("=> [%02d]", Arrays.stream(Dealer).sum());
			System.out.println();
			
			System.out.print("[Player] =>  ");
			System.out.print(CD.Deck[0].pattern+"-"+CD.Deck[0].number+"\t");
			System.out.print(CD.Deck[2].pattern+"-"+CD.Deck[2].number+"\n");
			if(CD.Deck[0].number=="A"||CD.Deck[2].number=="A") { whatIsYourAce(); }
			System.out.printf("=> [%02d]", Arrays.stream(Player).sum());
			System.out.println();
			
			System.out.print("*RESULT* => ");
			whoWins();
			System.out.printf("\n[Dealer] Win: %d | Draw : %d | Lose : %d\n", dealerStats[0], dealerStats[1], dealerStats[2]);
			System.out.printf("[Player] Win: %d | Draw : %d | Lose : %d\n", playerStats[0], playerStats[1], playerStats[2]);
			System.out.println("\n( Cards in a Deck : 0 )");
		}
		else if(GH.lastFourCard==2) GH.GameCloser();
	}
	
} // end - class onTheTable


public class Blackjack{
	
	public static void main(String[] args){
		GameHost GH = new GameHost();
		CardDeck CD = new CardDeck();
		onTheTable T = new onTheTable();
		
		GH.GameOpener();
		CD.Shuffle();
		CD.Converter();
		
		while(true) {
			T.getYourCards();
			int cardsUsed = CD.cardsInTheDeck(T.Dealer, T.Player);
			CD.Renew(CD.gamingDeck, CD.Deck, cardsUsed);
		}
	}
	
} // end - public class Blackjack