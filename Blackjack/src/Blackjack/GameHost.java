package Blackjack;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameHost {
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
						System.out.println("\n");
						break;
					}
					catch(Exception e) {e.printStackTrace();}
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
	
} // END - public class GameHost {}