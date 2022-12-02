package Blackjack;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameHost {
// Participate(), ParticipateCheck(), GameCloser(), GameOpener(), OnlyFourCards()

	int lastFourCard; // 카드가 4장 남았을시 게임 진행에 대한 대답
	
	private void Participate() { // 게임 시작 전 확인
		System.out.println("==========================================");
		System.out.println("                Blackjack                 ");
		System.out.println("==========================================\n");
		System.out.println("            (Press the number)\n");
		System.out.println("                  Play? ");
		System.out.println("                1. Yes  ");
		System.out.println("                2. No   ");
		System.out.print  ("                => ");
	} // END - public void Participate()
	
	private void ParticipateCheck() { // Participate()에서 잘못 입력 시 재질문
		System.out.println("==========================================");
		System.out.println("                Blackjack                 ");
		System.out.println("==========================================\n");
		System.out.println("       (Press the 'number' : 1 or 2)\n");
		System.out.println("                  Play? ");
		System.out.println("                1. Yes  ");
		System.out.println("                2. No   ");
		System.out.print  ("                => ");
	} // END - public void ParticipateCheck()
	
	public void GameCloser() { // 게임 종료(이후 프로그램 종료)
		System.out.println("\n==========================================");
		System.out.println("             [ GAME END! ] ");
		System.out.println("             - THANK YOU -");
		System.out.println("==========================================");
		System.out.println("                  - created by SiHoonChris");
		System.exit(0);
	} // END - public void GameCloser()
	
	public void GameOpener() { // 게임 오프닝 진행
		int cnt=0;
		
		while(true) {
			Scanner sc = new Scanner(System.in);
			int choice;
			
			try {			
				if(cnt==0) Participate();
				else ParticipateCheck();
				choice=sc.nextInt();
				
				if(choice==1) {
					try {				
						System.out.println("\n");
						for(int i=5; i>=1; i--) {
							System.out.println("                  "+i);
							// https://www.delftstack.com/ko/howto/java/how-to-delay-few-seconds-in-java/
							TimeUnit.SECONDS.sleep(1);
						}
						System.out.println("\n");;
						break;
					}
					catch(Exception e) {e.printStackTrace();}
					sc.close();
				}
				if(choice==2) {
					GameCloser();
				}
				
				cnt++;
			}
			catch(InputMismatchException e) {
				// https://www.geeksforgeeks.org/scanner-reset-method-in-java-with-examples/
				sc.reset(); // 반복문 안에서 Scanner 선언 및 생성
			}
		}
	} // END - public void GameOpener()
	
	public int OnlyFourCards() { // 카드가 4장 남았을시 게임 진행에 대한 질문
		Scanner sc = new Scanner(System.in); //어떤 값을 입력하던 String타입으로 받아짐 - 예외 발생 안함. 그래서 굳이 반복문 안에다 안넣음

		System.out.println("Only 4 cards remain");
		System.out.println("[Player] cannot make a decision for hit/stay in this turn");
		
		while(true) {
			System.out.println("(Press the number)");
			System.out.print("1. continue  or  2. halt       => ");
			String choice = sc.next();
			
			if(choice.equals("1")) lastFourCard=1;
			else if(choice.equals("2")) lastFourCard=2;
			
			if(lastFourCard==1||lastFourCard==2) {
				sc.close();
				return lastFourCard;
			}
		}
	} // END - public void OnlyFourCards()
	
} // END - public class GameHost {}