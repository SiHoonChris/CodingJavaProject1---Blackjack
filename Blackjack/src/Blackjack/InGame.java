package Blackjack;

import java.util.Arrays;

// Methods : 
// PlayBlackjack(), WhoWins(), FinalMatch()
public class InGame {

	GameHost GH = new GameHost();

	int dealerScore;
	int playerScore;
	int choice=0;
	int score;
	int matchCnt=0;
	int[] dealerStats = {0, 0, 0};  // 승, 무, 패
	int[] playerStats = {0, 0, 0};
	static boolean dealerWin;
	static boolean playerWin;
	static boolean dealerWin21;
	static boolean playerWin21;
	static boolean fourCardHalt;
	
	public void PlayBlackjack() { // 게임 진행 전반
		try {
			Timer T = new Timer();
			Thread timer = new Thread(T);

			for(int i=0; i<GameHost.Dealer.length; i++) GameHost.Dealer[i]=0;
			for(int i=0; i<GameHost.Player.length; i++) GameHost.Player[i]=0;
			// 메인 메서드에서 반복문 적용 => 코드 시작부분에서 최신화 안해줄시 계산 오류 생김
			// 예를들어) 이전 turn에서 3장 받았는데 이번 turn에서 2장 받으면, 이전에 받은 3번째 카드가 그대로 남아있음
			
			GameHost.Player[0]=CardDeck.gamingDeck[0];
			GameHost.Dealer[0]=CardDeck.gamingDeck[1];
			GameHost.Player[1]=CardDeck.gamingDeck[2];
			GameHost.Dealer[1]=CardDeck.gamingDeck[3];
			
		// -------------------- 딜러 파트 시작 & Turn 넘버 기록 -------------------- //
			if(GameHost.Dealer[0]+GameHost.Dealer[1]<=11 && (CardDeck.Deck[1].number=="A" || CardDeck.Deck[3].number=="A")) {
				if(CardDeck.Deck[1].number=="A") GameHost.Dealer[0]=11;
				else if(CardDeck.Deck[3].number=="A") GameHost.Dealer[1]=11;
				else if(CardDeck.Deck[1].number=="A" && CardDeck.Deck[3].number=="A") GameHost.Dealer[0]=11;
			}
			if(GameHost.Dealer[0]+GameHost.Dealer[1]<=16) {
				GameHost.Dealer[2]=CardDeck.gamingDeck[4];
			}
			if(CardDeck.Deck[4].number=="A" && GameHost.Dealer[0]+GameHost.Dealer[1]<11) {
				GameHost.Dealer[2]=11;
			}
			
			matchCnt++;
			System.out.println("================= Turn "+matchCnt+" =================");
			timer.start();
			
			System.out.print("[Dealer] =>  ");
			System.out.print(CardDeck.Deck[1].pattern+"-"+CardDeck.Deck[1].number+"\t");
			System.out.print(CardDeck.Deck[3].pattern+"-"+CardDeck.Deck[3].number +(GameHost.Dealer[2]!=0 ? "\t" : "\n"));
			if(GameHost.Dealer[2] != 0) {
				System.out.print(CardDeck.Deck[4].pattern+"-"+CardDeck.Deck[4].number+"\n");
			} 
			System.out.printf("=> [%02d]", Arrays.stream(GameHost.Dealer).sum());
			
			boolean dealerBust=false;
			if(Arrays.stream(GameHost.Dealer).sum()>21) dealerBust=true;			
		// ---------------------------- 딜러 파트 끝 ---------------------------- //
			
		// --------------------------플레이어 파트 시작 --------------------------- //
			System.out.print("\n[Player] =>  ");
			System.out.print(CardDeck.Deck[0].pattern+"-"+CardDeck.Deck[0].number+"\t");
			System.out.print(CardDeck.Deck[2].pattern+"-"+CardDeck.Deck[2].number+"\n");
			
			if(dealerBust==false) {
				if(CardDeck.Deck[0].number=="A"||CardDeck.Deck[2].number=="A") GH.WhatIsYourAce();
				if(!(Betting.playerWallet<Betting.minimumBet)) GH.HitOrStay();
			}
			
			System.out.printf("=> [%02d]", Arrays.stream(GameHost.Player).sum());
		// -------------------------- 플레이어 파트 끝 ---------------------------- //
			
		// -------------------------- 게임 결과/마무리 ---------------------------- //
			System.out.print("\n\n*RESULT* => ");
			WhoWins();
			T.ClockOff();
			
			System.out.printf("[Dealer] Win: %d | Draw : %d | Lose : %d\n", dealerStats[0], dealerStats[1], dealerStats[2]);
			System.out.printf("[Player] Win: %d | Draw : %d | Lose : %d\n", playerStats[0], playerStats[1], playerStats[2]);

		} catch(NullPointerException e) {
			int lastCards=0;
			for(int i=0; i<CardDeck.Deck.length; i++) { if(CardDeck.Deck[i]!=null) lastCards++; }
			
			if(lastCards==4) { 
				System.out.printf("================= Turn %d =================\n", ++matchCnt);
				FinalMatch();
			}
			else  GH.GameCloser();
		// -------------------------- 게임 결과/마무리 ---------------------------- //
		}

	} // END - public void PlayBlackjack()
	
	private void WhoWins() { // 승무패 결과
		dealerScore=Arrays.stream(GameHost.Dealer).sum();
		playerScore=Arrays.stream(GameHost.Player).sum();
		
		if(dealerScore<=21 && playerScore<=21) {
			if(dealerScore>playerScore) { 
				System.out.println("[Dealer] WIN!");
				dealerWin=true;
				if(dealerScore==21) dealerWin21=true;
				dealerStats[0]++;
				playerStats[2]++;
			}
			else if(dealerScore<playerScore) {
				System.out.println("[Player] WIN!");
				playerWin=true;
				if(playerScore==21) playerWin21=true;
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
			if(dealerScore>21 && playerScore<=21) {
				System.out.println("[Player] WIN!");
				playerWin=true;
				if(playerScore==21) playerWin21=true;
				playerStats[0]++;
				dealerStats[2]++;
			}
			else if(playerScore>21 && dealerScore<=21) {
				System.out.println("[Dealer] WIN!");
				dealerWin=true;
				if(dealerScore==21) dealerWin21=true;
				dealerStats[0]++;
				playerStats[2]++;
			}
		}
	} // END - private void WhoWins()
	
	private void FinalMatch() { // 남은 카드가 4장일 때 자동으로 실행되는 메서드
		GH.OnlyFourCards();
		
		if(GH.lastFourCard==1) {
			GameHost.Player[0]=CardDeck.gamingDeck[0];
			GameHost.Dealer[0]=CardDeck.gamingDeck[1]; 
			GameHost.Player[1]=CardDeck.gamingDeck[2];
			GameHost.Dealer[1]=CardDeck.gamingDeck[3];
			
			System.out.print("\n[Dealer] =>  ");
			System.out.print(CardDeck.Deck[1].pattern+"-"+CardDeck.Deck[1].number+"\t");
			System.out.print(CardDeck.Deck[3].pattern+"-"+CardDeck.Deck[3].number +(GameHost.Dealer[2]!=0 ? "\t" : "\n"));
			if(GameHost.Dealer[2] != 0) {System.out.print(CardDeck.Deck[4].pattern+"-"+CardDeck.Deck[4].number+"\n");} 
			System.out.printf("=> [%02d]", Arrays.stream(GameHost.Dealer).sum());
			System.out.println();
			
			System.out.print("[Player] =>  ");
			System.out.print(CardDeck.Deck[0].pattern+"-"+CardDeck.Deck[0].number+"\t");
			System.out.print(CardDeck.Deck[2].pattern+"-"+CardDeck.Deck[2].number+"\n");
			if(CardDeck.Deck[0].number=="A"||CardDeck.Deck[2].number=="A") { GH.WhatIsYourAce(); }
			System.out.printf("=> [%02d]", Arrays.stream(GameHost.Player).sum());
			System.out.println();
			
			System.out.print("*RESULT* => ");
			WhoWins();
			System.out.printf("\n[Dealer] Win: %d | Draw : %d | Lose : %d\n", dealerStats[0], dealerStats[1], dealerStats[2]);
			System.out.printf("[Player] Win: %d | Draw : %d | Lose : %d\n", playerStats[0], playerStats[1], playerStats[2]);
		}
		else if(GH.lastFourCard==2)  fourCardHalt=true;
		
	} // END - private void FinalMatch()

	
} // END - public class InGame {}