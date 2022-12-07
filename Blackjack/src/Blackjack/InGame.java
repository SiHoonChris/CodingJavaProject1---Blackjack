package Blackjack;

import java.util.Arrays;

// PlayBlackjack(), WhoWins(), FinalMatch()
public class InGame {

	CardDeck CD = new CardDeck();
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
	
	public void PlayBlackjack() { // 게임 진행 전반
		try {
			Timer clock = new Timer();
			Thread timer = new Thread(clock);

			for(int i=0; i<GH.Dealer.length; i++) GH.Dealer[i]=0;
			for(int i=0; i<GH.Player.length; i++) GH.Player[i]=0;
			// 메인 메서드에서 반복문 적용 => 코드 시작부분에서 최신화 안해줄시 계산 오류 생김
			// 예를들어) 이전 turn에서 3장 받았는데 이번 turn에서 2장 받으면, 이전에 받은 3번째 카드가 그대로 남아있음
			
			GH.Player[0]=CD.gamingDeck[0];
			GH.Dealer[0]=CD.gamingDeck[1];
			GH.Player[1]=CD.gamingDeck[2];
			GH.Dealer[1]=CD.gamingDeck[3];
			
			// -------------------- 딜러 파트 시작 & Turn 넘버 기록 -------------------- //
			if(GH.Dealer[0]+GH.Dealer[1]<=11 && (CD.Deck[1].number=="A" || CD.Deck[3].number=="A")) {
				if(CD.Deck[1].number=="A") GH.Dealer[0]=11;
				else if(CD.Deck[3].number=="A") GH.Dealer[1]=11;
				else if(CD.Deck[1].number=="A" && CD.Deck[3].number=="A") GH.Dealer[0]=11;
			}
			if(GH.Dealer[0]+GH.Dealer[1]<=16) {
				GH.Dealer[2]=CD.gamingDeck[4];
			}
			if(CD.Deck[4].number=="A" && GH.Dealer[0]+GH.Dealer[1]<11) {
				GH.Dealer[2]=11;
			}
			
			matchCnt++;
			System.out.println("================= Turn "+matchCnt+" =================");
			timer.start();
	
			System.out.print("[Dealer] =>  ");
			System.out.print(CD.Deck[1].pattern+"-"+CD.Deck[1].number+"\t");
			System.out.print(CD.Deck[3].pattern+"-"+CD.Deck[3].number +(GH.Dealer[2]!=0 ? "\t" : "\n"));
			if(GH.Dealer[2] != 0) {
				System.out.print(CD.Deck[4].pattern+"-"+CD.Deck[4].number+"\n");
			} 
			System.out.printf("=> [%02d]", Arrays.stream(GH.Dealer).sum());
			
			boolean dealerBust=false;
			if(Arrays.stream(GH.Dealer).sum()>21) dealerBust=true;			
			// ---------------------------- 딜러 파트 끝 ---------------------------- //
			
			// --------------------------플레이어 파트 시작 --------------------------- //
			System.out.print("\n[Player] =>  ");
			System.out.print(CD.Deck[0].pattern+"-"+CD.Deck[0].number+"\t");
			System.out.print(CD.Deck[2].pattern+"-"+CD.Deck[2].number+"\n");
			
			if(dealerBust==false) {
				if(CD.Deck[0].number=="A"||CD.Deck[2].number=="A") GH.WhatIsYourAce();
				GH.HitOrStay();
			}
			
			System.out.printf("=> [%02d]", Arrays.stream(GH.Player).sum());
			// -------------------------- 플레이어 파트 끝 ---------------------------- //
			
			// -------------------------- 게임 결과/마무리 ---------------------------- //
			System.out.print("\n\n*RESULT* => ");
			WhoWins();
			clock.ClockOff();
			
			System.out.printf("[Dealer] Win: %d | Draw : %d | Lose : %d\n", dealerStats[0], dealerStats[1], dealerStats[2]);
			System.out.printf("[Player] Win: %d | Draw : %d | Lose : %d\n", playerStats[0], playerStats[1], playerStats[2]);

		} catch(NullPointerException e) {
			int lastCards=0;
			for(int i=0; i<CD.Deck.length; i++) { if(CD.Deck[i]!=null) lastCards++; }
			
			if(lastCards==4) { 
				System.out.printf("================= Turn %d =================\n", ++matchCnt);
				FinalMatch();
			}
			
			GH.GameCloser();
			// -------------------------- 게임 결과/마무리 ---------------------------- //
		}

	} // END - public void PlayBlackjack()
	
	private void WhoWins() { // 승무패 결과
		dealerScore=Arrays.stream(GH.Dealer).sum();
		playerScore=Arrays.stream(GH.Player).sum();
		
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
			GH.Player[0]=CD.gamingDeck[0];
			GH.Dealer[0]=CD.gamingDeck[1]; 
			GH.Player[1]=CD.gamingDeck[2];
			GH.Dealer[1]=CD.gamingDeck[3];
			
			System.out.print("\n[Dealer] =>  ");
			System.out.print(CD.Deck[1].pattern+"-"+CD.Deck[1].number+"\t");
			System.out.print(CD.Deck[3].pattern+"-"+CD.Deck[3].number +(GH.Dealer[2]!=0 ? "\t" : "\n"));
			if(GH.Dealer[2] != 0) {System.out.print(CD.Deck[4].pattern+"-"+CD.Deck[4].number+"\n");} 
			System.out.printf("=> [%02d]", Arrays.stream(GH.Dealer).sum());
			System.out.println();
			
			System.out.print("[Player] =>  ");
			System.out.print(CD.Deck[0].pattern+"-"+CD.Deck[0].number+"\t");
			System.out.print(CD.Deck[2].pattern+"-"+CD.Deck[2].number+"\n");
			if(CD.Deck[0].number=="A"||CD.Deck[2].number=="A") { GH.WhatIsYourAce(); }
			System.out.printf("=> [%02d]", Arrays.stream(GH.Player).sum());
			System.out.println();
			
			System.out.print("*RESULT* => ");
			WhoWins();
			System.out.printf("\n[Dealer] Win: %d | Draw : %d | Lose : %d\n", dealerStats[0], dealerStats[1], dealerStats[2]);
			System.out.printf("[Player] Win: %d | Draw : %d | Lose : %d\n", playerStats[0], playerStats[1], playerStats[2]);
			System.out.println("\n( Cards in a Deck : 0 )");
		}
		else if(GH.lastFourCard==2) GH.GameCloser();
	} // END - private void FinalMatch()

	
} // END - public class InGame {}