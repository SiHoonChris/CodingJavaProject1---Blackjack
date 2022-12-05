package Blackjack;

//1. 기본
//- (Turn마다) 카드 분배 전 Dealer와 Player는 최초 베팅 실시
//(딜러는 최초 베팅 외에는 돈을 걸 수 없음)
//- Player의 최소 베팅 금액은 2(금액 보유량 100 기준), 최대 베팅 금액은 딜러의 베팅 금액
//- 베팅 된 금액은 가상의 공간 'BANK'에 모임. 해당 Turn의 승자는 BANK에 모인 돈 전부를 가져감.
//  (만약 Draw일 경우, 그 금액은 다음 Turn으로 넘어감, 베팅 금액은 누적됨)
//- Player 또는 Dealer의 금액 보유량이 최소 베팅 금액(2)보다 적어질 경우 바로 게임 종료
//2. 추가베팅
//- 'hit' 선택시 최초 베팅 금액의 절반(홀수일 경우 올림 적용)을 추가 베팅해야 함
//- Player가 1회 이상의 'hit' 선택 후 승리했을 시, Dealer는 Player가 추가베팅한 금액만큼 Player에게 지불해야 한다.
//- 'stay' 선택시 추가 베팅 없음
//3. '21'로 게임 종료
//- 21로 승리한 Player(Dealer)는 자신이 베팅한 금액의 총액만큼을 Dealer(Player)로부터 추가로 받게 된다.
//- 예) Player가 추가베팅 포함 총 20를 베팅, 딜러는 10을 베팅. 해당 Turn이 Player의 21점 승리로 종료
//   이 경우, Player는 자신이 베팅한 돈 20 + Dealer가 베팅한 돈 10 + 보너스 20을 얻게 됨
//- Player의 점수와 Dealer의 점수가 21로 동점일 경우 '1.기본'에서의 룰과 동일하게 적용 
//4. 시간 제한
//- 60초 안에 한 Turn이 끝나지 않으면, Player가 Dealer에게 패널티(최소 베팅 금액) 지급

import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Betting {
	static int[] playerWallet = new int [1];
	static int[] dealerWallet = new int [1];
	static int[] bank;
	
	public void BettingDefault() {		
		System.out.println("=========== Setting for Betting ===========");
		
		while(true) {						
			Scanner sc = new Scanner(System.in);
			
			try {
				System.out.print("How much cash do you have? : ");
				int money = sc.nextInt();
				
				playerWallet[0]=money;
				dealerWallet[0]=playerWallet[0];
				break;
			} catch(InputMismatchException e) {
				sc.reset();
			}
		}
		
		System.out.printf("\n[Player] Hold : %,d\n", playerWallet[0]);
		System.out.printf("[Dealer] Hold : %,d\n", dealerWallet[0]);
		System.out.printf("Bet(Minimum)  : %,d\n", (int)(dealerWallet[0]*0.02));
		System.out.printf("Bet(Maximum)  : %,d\n", (int)(dealerWallet[0]*0.1));
		
		System.out.println("\n================= Settled =================\n");
	} // END - public void BettingDefault()
	
	public void FirstBetting() {		
		System.out.println("\n================= Betting =================");
		
		
	}

	
} // END - public class Betting {}