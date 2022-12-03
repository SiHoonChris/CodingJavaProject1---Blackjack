package Blackjack;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

//Participate(), ParticipateCheck(), GameCloser(), GameOpener(), OnlyFourCards(), WhatIsYourAce(), WhatIsYourAce(int n), HitOrStay()
public class GameHost {

	CardDeck CD; // CardDeck 클래스 호출
	static int[] Dealer = new int[3];  // 매 Turn에서 Dealer가 보유한 카드 
	static int[] Player = new int[12];  // 매 Turn에서 Player가 보유한 카드
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
	
	public void WhatIsYourAce() { // 'A'카드 보유시 그 값에 대하여 1 또는 11 선택
		
		while(true) {
			Scanner sc = new Scanner(System.in);
			
			try {
				System.out.print("1 or 11 ? =>  ");
				int Answer = sc.nextInt();
				
				if(CD.Deck[0].number=="A" && Answer==11) Player[0]=11;
				else if(CD.Deck[2].number=="A" && Answer==11) Player[1]=11;
				else if(CD.Deck[0].number=="A" && CD.Deck[2].number=="A" && Answer==11) Player[0]=11;
				
				if(Answer==1||Answer==11) break;
			}
			catch(InputMismatchException e){
				sc.reset();
			}
			
		}
	} // END - private void whatIsYourAce()
	
	public void WhatIsYourAce(int n) { // 추가로 받은 카드가 'A'카드일 때, 그 값에 대하여 1 또는 11 선택
		
		while(true) {
			Scanner sc = new Scanner(System.in);
			
			try {
				System.out.print("1 or 11 ? =>  ");
				int Answer = sc.nextInt();
				
				if(Answer==11) Player[n]=11;
				
				if(Answer==1||Answer==11) break;
			}
			catch(InputMismatchException e) {
				sc.reset();
			}
			
		}
	} // END - private void whatIsYourAce(int n)
	
	public void HitOrStay() { // Player의 카드 추가 여부 묻기
		Scanner sc = new Scanner(System.in);
		
		int n;
		if(Dealer[2]==0) n=4;
		else 			 n=5;
		
		int num=2;
		for(int i=n ; ; i++) {
			System.out.print("HIT or STAY ? =>  ");
			String select = sc.next();
			select = select.toUpperCase();
				if(select.equals("HIT")) {						
					if(CD.gamingDeck[i]!=0) {
						Player[num]=CD.gamingDeck[i];
						System.out.print(CD.Deck[i].pattern+"-"+CD.Deck[i].number+"\n");
						if(CD.Deck[i].number=="A") { WhatIsYourAce(num); }
						num++;
					} 
					else { return; }
				} else if(select.equals("STAY")) { return; }
			if(Arrays.stream(Player).sum()>21) { return; }
		}
		
	} // END - public void HitOrStay()
	
} // END - public class GameHost {}