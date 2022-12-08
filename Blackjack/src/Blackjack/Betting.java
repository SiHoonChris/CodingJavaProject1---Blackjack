package Blackjack;


import java.util.Scanner;
import java.util.InputMismatchException;

// BettingDefault(), FirstBetting(), BettingResult(), BettingCalculator(), PayForHit(), TwentyOneEnd()
public class Betting {
	
	static int playerWallet;
	static int dealerWallet;
	static int bank;
	static int minimumBet;
	static int maximumBet;
	static int playerBet;
	boolean unableToBet;
	
	public void BettingDefault() {	// 베팅 관련 금액 설정	
		System.out.println("=========== Setting for Betting ===========");
		
		while(true) {						
			Scanner sc = new Scanner(System.in);
			
			try {
				System.out.println("[Dealer] : How much cash do you have?");
				System.out.print("[Player] : ");
				int money = sc.nextInt();
				
				if(money>=100) {
					System.out.println("[Dealer] : Good.");
					playerWallet=money;
					dealerWallet=playerWallet;
					minimumBet = (int)(dealerWallet*0.02);
					maximumBet = (int)(dealerWallet*0.1);
					break;
				}
				else {
					System.out.println("[Dealer] : You may want to hold over '100'");
				}
			} catch(InputMismatchException e) {
				System.out.println("[Dealer] : In Number, please");
				sc.reset();
			}
		}
		
		System.out.printf("\n[Player] Hold : %,d\n", playerWallet);
		System.out.printf("[Dealer] Hold : %,d\n", dealerWallet);
		System.out.printf("Bet(Minimum)  : %,d\n", minimumBet);
		System.out.printf("Bet(Maximum)  : %,d\n", maximumBet);
		
		System.out.println("\n================= Settled =================\n");
	} // END - public void BettingDefault()
	
	
	public void FirstBetting() {  // 게임 시작 전 베팅
		System.out.println("\n================= Betting =================");
		System.out.printf("Bet Range    : %,d ~ %,d\n", minimumBet, maximumBet);
		System.out.printf("[Dealer] Bet : %,d\n", maximumBet);
		
		while(true) {
			
			Scanner sc = new Scanner(System.in);
			
			try {
				System.out.print("[Player] Bet : ");
				playerBet = sc.nextInt();
				
				if(!(minimumBet<=playerBet)) {
					System.out.printf("[Dealer] : Please, bet more than %,d, or %,d\n", minimumBet, minimumBet);
				}
				else if(!(playerBet<=maximumBet)) {
					System.out.printf("[Dealer] : Please, bet less than %,d, or %,d\n", maximumBet, maximumBet);
				}
				else {
					dealerWallet-=maximumBet;
					playerWallet-=playerBet;
					bank += (playerBet+maximumBet);
					System.out.printf("\n[Dealer] : %,d\n", dealerWallet);
					System.out.printf("[Player] : %,d\n", playerWallet);
					System.out.printf("[Bank]   : %,d\n", bank);
					return;
				}
			} catch(InputMismatchException e) {
				System.out.println("[Dealer] : In Number, please");
				sc.reset();
			}
		}
	} // END - public void FirstBetting()
	
	public void BettingResult() { // 잔액 확인/계산
		BettingCalculator();
		
		// 현황 출력
		System.out.println("\n================= Account =================");
		System.out.printf("[Dealer] : %,d\n", dealerWallet);
		System.out.printf("[Player] : %,d\n", playerWallet);
		System.out.printf("[Bank]   : %,d\n", bank);
		if(GameHost.numberOfHit!=0)
			System.out.printf("[Hit]    : %,d\n", GameHost.numberOfHit);
		System.out.println("===========================================\n\n");
		
		// 초기화
		playerBet=0;
		InGame.dealerWin=false;
		InGame.playerWin=false;
		InGame.dealerWin21=false;
		InGame.playerWin21=false;
		GameHost.numberOfHit=0;
	} // END - public void BettingResult()
	
	private void BettingCalculator() {  // 잔액 계산
		PayForHit(); // 추가 베팅이 이뤄졌을 시 계산 실행
		
		// 승무패에 따라 계산
		if(InGame.dealerWin==true && InGame.playerWin==false) { // [Dealer] Win
			dealerWallet += bank;
			bank=0;
		}
		else if(InGame.playerWin==true && InGame.dealerWin==false) { // [Player] Win
			//- Player가 1회 이상의 'hit' 선택 후 승리했을 시,
			//  Dealer는 Player가 추가베팅한 금액만큼 Player에게 지불해야 한다.
			if(GameHost.numberOfHit!=0) {
				dealerWallet-=GameHost.numberOfHit*minimumBet;
				bank+=GameHost.numberOfHit*minimumBet;
			}
			playerWallet += bank;
			bank=0;
		}
		
		// 21점으로 게임 종료 시 추가되는 프리미엄 실행
		TwentyOneEnd(); // (추가 베팅 제외) 자신이 베팅한 만큼을 상대로부터 추가로 가져온다.
		
	} // END - public void BettingResult()
	
	
	private void PayForHit() { // 2. 추가 베팅
	//- 'hit' 선택시 '최소 베팅 금액'을 추가 베팅해야 함
		if(GameHost.numberOfHit!=0) {
			playerWallet-=GameHost.numberOfHit*minimumBet;
			bank+=GameHost.numberOfHit*minimumBet;
		}
	} // END - public void PayForHit()
	
	
	private void TwentyOneEnd() { //3. '21'로 게임 종료 시
	//- 21로 승리한 Player(Dealer)는 자신이 해당 turn에 베팅한 금액(추가베팅 금액 제외)의 총액만큼을
	//	Dealer(Player)로부터 추가로 받게 된다. (21점이 나왔어도 무승무라면 해당 룰은 적용되지 않음)
	//  (추가 베팅에 대한 중복 보상을 피하기 위함)
		
		// Player가 21점으로 게임 승리시 돈의 이동
		if(InGame.playerWin21==true) {
			dealerWallet-=playerBet;
			bank+=playerBet;
			playerWallet+=bank;
			bank=0;
		}
		// Dealer가 21점으로 게임 승리시 돈의 이동
		if(InGame.dealerWin21==true) {
			playerWallet-=maximumBet;  // 매 turn마다 dealer의 베팅금액은 maximumBet이다.
			bank+=maximumBet;
			dealerWallet+=bank;
			bank=0;
		}
	} // END - private void TwentyOneEnd()
	

} // END - public class Betting {}