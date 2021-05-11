import java.util.Random;
import processing.core.*;
public class Minigame extends PApplet {
			
	static int columns;
	static int rows;
	static String userinput;
	static String [][] board = new String [3][3];
	static int [][] array = new int [3][3];
	static int count_turns = 0;
	static int turn = 0;
	//static int [][] player = new int [9][2];
	static boolean computerTurn = true;
	//static int [][] computer = new int [9][2];
	PFont user_input;
	
		
		public Minigame() {
			
			
		}
		
		public void texts() {
			PFont font;
			font = createFont("Arial", 20);
			textFont(font);
			textSize(30);
			
//			text("a", 90, 70);
//			text("b", 190, 70);
//			text("c", 300, 70);
		}
				
		public static int Turns(){
			
			Random random = new Random();
			int turn = random.nextInt(3);
			if (turn == 0){
				return 0;
			}			else if (turn == 1){
				return 1;
			}
			
			return 2;
		}
		
		
		public void mouseClicked() {
//			System.out.println("Turns"+ turn);
//			System.out.println("count_turns" + count_turns);
//			System.out.println("mouseclicked");
//			System.out.println("computerTurn"+ computerTurn);
			if (computerTurn == false && won()==0) {
				//text("Welcome to tic-tac-toe!", 10, 10);
				if (mouseX <= 153 && mouseY <= 153 && mouseX >= 80 && mouseY >= 80 ) {
					// first square
					if(board[0][0]!="X" && board[0][0] != "O") {
						
						board[0][0]="X";						
						count_turns++;
						drawBoard("X", 104, 120);
						computerTurn = true;
					}
				}
				
				else if(mouseX <= 246 && mouseY <= 153 && mouseX >= 153 && mouseY >= 80){
					// second square
					if(board[0][1]!="X" && board[0][1] != "O") {
						board[0][1]="X";
						//player[ count_turns/2][0]=190;
						//player[ count_turns/2][1]=120;
						
						count_turns++;
						drawBoard("X", 190, 120);
						computerTurn = true;
					}
				}
				
				else if(mouseX <= 320 && mouseY <= 153 && mouseX >= 246 && mouseY >= 80){
					// third square
					if(board[0][2]!="X" && board[0][2] != "O") {
						board[0][2]="X";
						//player[ count_turns/2][0]=275;
						//player[ count_turns/2][1]=120;
						
						count_turns++;
						drawBoard("X", 275, 120);
						computerTurn = true;
					}
				}
				
				else if(mouseX <= 153 && mouseY <= 246 && mouseX >= 80 && mouseY >= 153){
					// fourth square
					if(board[1][0]!="X" && board[1][0] != "O") {
						board[1][0]="X";
						//player[ count_turns/2][0]=104;
						// player[ count_turns/2][1]=207;
						
						count_turns++;
						drawBoard("X", 104, 207);
						computerTurn = true;
					}
				}
				
				else if(mouseX <= 246 && mouseY <= 246 && mouseX >= 153 && mouseY >= 153){
					// fifth square
					if(board[1][1]!="X" && board[1][1] != "O") {
						board[1][1]="X";
						//player[ count_turns/2][0]=190;
						//player[ count_turns/2][1]=207;
						
						count_turns++;
						drawBoard("X", 190, 207);
						computerTurn = true;
					}
				}
				
				else if(mouseX <= 320 && mouseY <= 246 && mouseX >= 153 && mouseY >= 153){
					// sixth square
					if(board[1][2]!="X" && board[1][2] != "O") {
						board[1][2]="X";
						//player[ count_turns/2][0]=275;
						//player[ count_turns/2][1]=207;
						
						count_turns++;
						drawBoard("X", 275, 207);
						computerTurn = true;
					}
				}
				else if(mouseX <= 153 && mouseY <= 320 && mouseX >= 80 && mouseY >= 246){
					// seventh square
					if(board[2][0]!="X" && board[2][0] != "O") {
						board[2][0]="X";
						//player[ count_turns/2][0]=104;
						//player[ count_turns/2][1]=295;
						
						count_turns++;
						drawBoard("X", 104, 295);
						computerTurn = true;
					}
				}
				else if(mouseX <= 246 && mouseY <= 320 && mouseX >= 153 && mouseY >= 246){
					// eighth square
					if(board[2][1]!="X" && board[2][1] != "O") {
						board[2][1]="X";
						//player[ count_turns/2][0]=190;
						//player[ count_turns/2][1]=295;
						
						count_turns++;
						drawBoard("X", 190, 295);
						computerTurn = true;
					}
				}	
				
				else if(mouseX <= 320 && mouseY <= 320 && mouseX >= 246 && mouseY >= 246){
					// nineth square
					
					if(board[2][2]!="X" && board[2][2] != "O") {
						board[2][2]="X";
						//player[ count_turns/2][0]=275;
						//player[ count_turns/2][1]=295;
						
						count_turns++;
						drawBoard("X", 275, 295);
						computerTurn = true;
					}
				}
				
				
			}
			
			else if(won()!=0) {
				stop();
			}
			
			
		}
		
		public void computer() {
		if (computerTurn == true && won()==0) {
			delay(200);
			Random random = new Random();
			int row = random.nextInt(4);
			int col = random.nextInt(4);
			//text("this method worked!");
			//System.out.println("it worked!");
			
			
			if (row == 0 && col == 0 ) {
					// first square
					if(board[0][0]!="X" && board[0][0] != "O") {
						board[0][0]="O";
						//computer[ count_turns/2][0]=104;
						//computer[ count_turns/2][1]=120;
						
						//count_turns++;
						drawBoard("O", 104, 120);
						computerTurn=false;
						
					}	
			}	
					
				else if(row == 1 && col == 1 ){
				// second square
					if(board[0][1]!="X" && board[0][1] != "O") {
						board[0][1]="O";
						//computer[count_turns/2][0]=190;
						//computer[ count_turns/2][1]=120;
						//count_turns++;
						drawBoard("O", 190, 120);
						computerTurn=false;
					}
					
				}
						
				else if(row == 2 && col == 2 ){
					// third square
					if(board[0][2]!="X" && board[0][2] != "O") {
						board[0][2]="O";
						//computer[ count_turns/2][0]=275;
						//computer[ count_turns/2][1]=120;
						//count_turns++;
						drawBoard("O", 275, 120);
						computerTurn=false;
					}
					
				}
						
				else if(row == 1 && col == 0 ){
					// fourth square
					if(board[1][0]!="X" && board[1][0] != "O") {
						board[1][0]="O";
						//computer[ count_turns/2][0]=104;
						//computer[ count_turns/2][1]=207;
						//count_turns++;
						drawBoard("O", 104, 207);
						computerTurn=false;
					}
					
				}
						
				else if(row == 1 && col == 1 ){
					// fifth square
					if(board[1][1]!="X" && board[1][1] != "O") {
						board[1][1]="O";
						//computer[ count_turns/2][0]=190;
						//computer[ count_turns/2][1]=207;
						//count_turns++;
						drawBoard("O", 190, 207);
						computerTurn=false;
					}
					
				}
						
				else if(row == 1 && col == 2 ){
					// sixth square
					if(board[1][2]!="X" && board[1][2] != "O") {
						board[1][2]="O";
						//computer[ count_turns/2][0]=275;
						//computer[ count_turns/2][1]=207;
						//count_turns++;
						drawBoard("O", 275, 207);
						computerTurn=false;
					}
					
				}
				else if(row == 2 && col == 0 ){
					// seventh square
					if(board[2][0]!="X" && board[2][0] != "O") {
						board[2][0]="O";
						//computer[ count_turns/2][0]=104;
						//computer[ count_turns/2][1]=295;
						//count_turns++;
						drawBoard("O", 104, 295);
						computerTurn=false;
					}
					
				}
				else if(row == 2 && col == 1 ){
					// eighth square
					if(board[2][1]!="X" && board[2][1] != "O") {
						board[2][1]="O";
						//computer[ count_turns/2][0]=190;
						//computer[ count_turns/2][1]=295;
						//count_turns++;
						drawBoard("O", 190, 295);
						computerTurn=false;
					}
					
				}	
						
				else if(row == 2 && col == 2 ){
					// nineth square
							
					if(board[2][2]!="X" && board[2][2] != "O") {
						board[2][2]="O";
						//computer[ count_turns/2][0]=275;
						//computer[ count_turns/2][1]=295;
						//count_turns++;
						drawBoard("O", 275, 295);
						computerTurn=false;
					}
					
				}
			
		}
		
		else if (won()!=0) {
			stop();
		}
			
		}
		
		
					
		
		
		public void drawBoard(String symbol, int x, int y) {
			//board[x][y]="X";
			text(symbol, x, y);
		}
		
		
		public void settings(){
		   	size(400, 400);
		}		public void setup(){
		   	background(255);
		   	fill(0);
		   	/*
		   	Random random = new Random();
		     turn = random.nextInt(3);
		     */
		     texts();
		     Board();
		     /*
		    while (won() !=0) {
		    	game();
		    	System.out.println("count_turns" + count_turns);
		    	System.out.println("turn" + turn);
		     }
		     */
		  
		}
		public void draw() {
			
			//drawBoard("work", 90, 90);
			/*
			for ( int x = 0; x<9; x++ ) {
				
				player[x][0]=100;
				computer[x][0]=100;
						
				
			}	
			
			for (int i = 0; i <=count_turns/2; i++ ) {
				
					
					drawBoard("X", player[i][0], player[i][1]);
					drawBoard("O", computer[i][0], computer[i][1]);
					
				
				
				
			}
			*/
			
			if (won() == 0) {
				game();
			}
			
			else if (won() != 0) {
				stop();
			}
			
			
			
		}
		public void Board() {
			
			
			line(80, 153, 320, 153);
			line(80, 246, 320, 246);
			line(153, 80, 153, 320);
			line(246, 80, 246, 320);
		}
		
		
		public int won() {
			
			// Check horizontal:
			for(int i=0; i<3; i++) {
				
				if(board[i][0]==board[i][1] && board[i][1]==board[i][2] && board[i][0]=="X"){
					System.out.println("the player has won");
					return 1;
				}
				
				else if (board[i][0]==board[i][1] && board[i][1]==board[i][2] && board[i][0]=="O") {
					System.out.println("the computer has won");
					return 2;
				}
			}
						
			for(int j=0; j<3; j++) {
				
				if(board[0][j]==board[1][j] && board[1][j]==board[2][j] && board[0][j]=="X"){
					System.out.println("the player has won");
					return 3;	
				}
				
				else if (board[0][j]==board[1][j] && board[1][j]==board[2][j] && board[0][j]=="O") {
					System.out.println("the computer has won");
					return 4;
				}
			}
			
			// Check for X diagonal TOP LEFT to BOTTOM RIGHT:
			if(board[0][0]==board[1][1] && board[1][1]==board[2][2] && board[0][0]=="X"){
				System.out.println("the player has won");
				return 5;	
			}
			
			// Check for O diagonal TOP RIGHT to BOTTOM LEFT:
			else if (board[2][0]==board[1][1] && board[1][1]==board[0][2] && board[2][0]=="O") {
				System.out.println("the computer has won");
				return 6;
			}
			
			// Check for X diagonal TOP RIGHT to BOTTOM LEFT:
			if(board[0][2]==board[1][1] && board[1][1]==board[2][0] && board[0][2]=="X"){
				System.out.println("the player has won");
				return 7;	
			}
			
			// Check for O diagonal TOP RIGHT to BOTTOM LEFT:
			if(board[0][2]==board[1][1] && board[1][1]==board[2][0] && board[0][2]=="O"){
				System.out.println("the computer has won");
				return 7;	
			}
			
			return 0;
				
		}
			
		public void game() {
			
			if (turn==0) {
				//mouseClicked();
				
				computer();
			}
			
			else if(turn==1) {
				computer();
				//mouseClicked();
			}
			/*
			if (won() == 0) {
				if (turn == 0) {
					if (count_turns%2 ==0) {
						mouseClicked();
						System.out.println("player's turn");
						//computer();
						count_turns++;
						System.out.println("count turns" + count_turns);
						won();
						System.out.println("won" + won());
					}
					
					else if(count_turns%2==1) {
						computer();
						count_turns++;
						System.out.println("computer's turn");
						System.out.println("count turns" + count_turns);
						won();
						System.out.println("won" + won());
					}
				}
				
				else if (turn ==1) {
					if (count_turns%2 ==0) {
						computer();
						count_turns++;
						System.out.println("computer's turn");
						System.out.println("count turns" + count_turns);
						
						won();
						System.out.println("won" + won());
					}
					
					else if(count_turns%2==1) {
						mouseClicked();
						count_turns++;
						System.out.println("player's turn");
						System.out.println("count turns" + count_turns);
						won();
						
						System.out.println("won" + won());
					}
				}
			}
			
			System.out.println("someone has won!");
			*/
			
		}
		
		public static void execute() {
			
			PApplet.main("Minigame");
			
		}
		
	
}