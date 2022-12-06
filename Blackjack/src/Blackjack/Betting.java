package Blackjack;

//구현할 규칙(남은 내용들)
//1. 기본
//- Player 또는 Dealer의 금액 보유량이 최소 베팅 금액(2)보다 적어질 경우 바로 게임 종료
//- 베팅 과정 중에 보유금이 바닥날 경우 1(예를 들어, 이전 게임 결과가 Draw여서 Bank에 돈이 묶여 있는 상태이기에 최소 베팅 금액보다
//  보유금이 더 적다. 그렇기에 현재 게임에서 베팅이 불가능하다. => bank에 쌓인 돈 전부 패자에게 넘겨주고 경기 종료)
//- 베팅 과정 중에 보유금이 바닥날 경우 1(게임 시작 전 베팅을 함으로써 보유금이 최소 베팅 금액보다 적게 남았다.
//  => 정상 진행 / 이후 결과에 따라 룰 적용)
//2. 추가베팅
//- 'hit' 선택시 최초 베팅 금액의 절반(홀수일 경우 올림 적용)을 추가 베팅해야 함
//- Player가 1회 이상의 'hit' 선택 후 승리했을 시, Dealer는 Player가 추가베팅한 금액만큼 Player에게 지불해야 한다.
//3. '21'로 게임 종료
//- 21로 승리한 Player(Dealer)는 자신이 베팅한 금액의 총액만큼을 Dealer(Player)로부터 추가로 받게 된다.
//- 예) Player가 추가베팅 포함 총 20를 베팅, 딜러는 10을 베팅. 해당 Turn이 Player의 21점 승리로 종료
//   이 경우, Player는 자신이 베팅한 돈 20 + Dealer가 베팅한 돈 10 + 보너스 20을 얻게 됨
//- Player의 점수와 Dealer의 점수가 21로 동점일 경우 '1.기본'에서의 룰과 동일하게 적용 
//4. 시간 제한
//- 60초 안에 한 Turn이 끝나지 않으면, Player가 Dealer에게 패널티(최소 베팅 금액) 지급
//- 안내문 출력)  [Dealer] : Penalty [Time-Over : 60sec.] 
//                        [Player] (-10), [Dealer] (+10)

import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;

// BettingDefault(), FirstBetting(), BettingResult()
public class Betting {
	InGame IG = new InGame();
	
	static int playerWallet;
	static int dealerWallet;
	static int bank;
	static int minimumBet;
	static int maximumBet;
	static int playerBet;
	
	
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
	
	
	public void BettingResult() {  // 잔액 확인/계산
		// 승무패에 따라 계산
		if(IG.dealerWin==true && IG.playerWin==false) {
			dealerWallet += bank;
			bank=0;
		}
		else if(IG.playerWin==true && IG.dealerWin==false) {
			playerWallet += bank;
			bank=0;
		}
		
		// 현황 출력
		System.out.println("\n================= Account =================");
		System.out.printf("[Dealer] : %,d\n", dealerWallet);
		System.out.printf("[Player] : %,d\n", playerWallet);
		System.out.printf("[Bank]   : %,d\n", bank);
		System.out.println("===========================================\n\n");
		
		// 초기화
		playerBet=0;
		IG.dealerWin=false;
		IG.playerWin=false;
		
	} // END - public void BettingResult()
	
	
} // END - public class Betting {}