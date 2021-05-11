import processing.core.*;
import java.util.Timer;
import java.util.TimerTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.opencsv.CSVWriter;

public class JavJavBeans extends PApplet {
	
	static int maxHappiness = 50;
	static int maxFood = 50;
	static int maxEnergy = 50;
	
	
	static int happiness = 50;
	static int food = 50;
	static int energy = 50;
	static int age = 0;
	static int money = 50;
	static int stage = 0;
	static boolean sleeping = false;
	static boolean petting = false;
	static boolean mousePressed = true;
	static boolean gameOver = false;
	
	static boolean gameRunning;
	
	static boolean overPet = false;
	static boolean overButton = false;
	static int whichButton = 1;
	
	static int buttonsSize = 60;
	static int buttonsPositionX = 20;
	static int buttonsSeparation = 15;
	static int miniGameButtonY = 160;
	static int feedButtonY = miniGameButtonY + buttonsSize + buttonsSeparation;
	static int sleepButtonY = miniGameButtonY + 2*buttonsSize + 2*buttonsSeparation;
	
	static float barWidth = 150;
	static float barHeight = 20;
	static float barX = 20;
	
	static Timer time; 
	static Timer sleep;
	static Timer happinessAtZero;
	static Timer foodAtZero;
	static Timer energyAtZero;
	static int happinessAtZeroFlag = 0;
	static int foodAtZeroFlag = 0;
	static int energyAtZeroFlag = 0;
	
	static boolean savingGame = false;
	
	static int pettingCounter = 0; // CHANGED BY DAVID
	
	// NORMAL:
	PImage baby;
	PImage teen;
	PImage adult;
	PImage elder;
	// SAD:
	PImage babySad;
	PImage teenSad;
	PImage adultSad;
	PImage elderSad;
	// SLEEP:
	PImage babySleep;
	PImage teenSleep;
	PImage adultSleep;
	PImage elderSleep;
	// PET:
	PImage babyPet;
	PImage teenPet;
	PImage adultPet;
	PImage elderPet;

	public static void saveGame() {
		
		try {
			File csv = new File("saved_game.csv");
		    FileWriter csvOutput = new FileWriter(csv);
	        CSVWriter csvWriter = new CSVWriter(csvOutput);

	        String[] currentData = {
	        		Integer.toString(happiness),
	        		Integer.toString(food),
	        		Integer.toString(energy),
	        		Integer.toString(age),
	        		Integer.toString(money),
	        		Integer.toString(pettingCounter),
	        		};
	        
	        csvWriter.writeNext(currentData);
	        
	        csvWriter.close();
			
		} catch(IOException e) {
			// TODO Auto-generated catch block
	        e.printStackTrace();
		}
	}
	
	public static void loadGame() {
		try {
			Scanner csv = new Scanner(new File("saved_game.csv"));
			//csv.useDelimiter(",");
			csv = new Scanner(new File("saved_game.csv"));
			csv.useDelimiter("\"|,");
			
			String tempHappiness = csv.next();
			csv.next();
			csv.next();
			String tempFood = csv.next();
			csv.next();
			csv.next();
			String tempEnergy = csv.next();
			csv.next();
			csv.next();
			String tempAge = csv.next();
			csv.next();
			csv.next();
			String tempMoney = csv.next();
			csv.next();
			csv.next();
			String tempPettingCounter = csv.next();
			
//			tempHappiness.replaceAll("\"", "");
//			tempFood.replaceAll("^\"|\"$", "");
//			tempEnergy.replaceAll("^\"|\"$", "");
			
			happiness = Integer.parseInt(tempHappiness);
			food = Integer.parseInt(tempFood);
			energy = Integer.parseInt(tempEnergy);
			age = Integer.parseInt(tempAge);
			money = Integer.parseInt(tempMoney);
			pettingCounter = Integer.parseInt(tempPettingCounter);
			
			System.out.println("SAVED HAPPINESS: "+Integer.toString(happiness));
			System.out.println("SAVED FOOD: "+Integer.toString(food));
			System.out.println("SAVED ENERGY: "+Integer.toString(energy));
			System.out.println("SAVED AGE: "+Integer.toString(age));
			System.out.println("SAVED MONEY: "+Integer.toString(money));
			System.out.println("SAVED PETTING COUNTER: "+Integer.toString(pettingCounter));
			
			switch(age){
			case 1:
				barWidth = 175;
				maxHappiness = 75;
				maxFood = 75;
				maxEnergy = 75;
				break;
			case 2:
				barWidth = 200;
				maxHappiness = 100;
				maxFood = 100;
				maxEnergy = 100;
				break;
			case 3:
				barWidth = 225;
				maxHappiness = 125;
				maxFood = 125;
				maxEnergy = 125;
				break;
			}
		} catch(IOException e) {
			// TODO Auto-generated catch block
	        System.out.print("No saved file. A new game will be created.");
		}
	}
	
	public JavJavBeans(){}

	
	public void settings(){
		size(400, 400);
	}

	public void setup(){
		loadGame();
		background(255);
		// NORMAL:
		baby = loadImage("baby.png");
		teen = loadImage("teen.png");
		adult = loadImage("adult.png");
		elder = loadImage("old.png");
		//frameRate(30);
		// SAD:
		babySad = loadImage("baby_sad.png");
		teenSad = loadImage("teen_sad.png");
		adultSad = loadImage("adult_sad.png");
		elderSad = loadImage("old_sad.png");
		//frameRate(30);
		// SLEEP:
		babySleep = loadImage("baby_sleep.png");
		teenSleep = loadImage("teen_sleep.png");
		adultSleep = loadImage("adult_sleep.png");
		elderSleep = loadImage("old_sleep.png");
		// SLEEP:
		babyPet = loadImage("baby_pet.png");
		teenPet = loadImage("teen_pet.png");
		adultPet = loadImage("adult_pet.png");
		elderPet = loadImage("old_pet.png");
	}
	
	public void draw() {
		buttonSetup();
		petSetup();
		barSetup();
		fill(0);
		textSetup();
		checkValues();
	}
	
	public void buttonSetup() {
		stroke(0);
		if(overButton && whichButton == 1)
			fill(255, 139,106);
		else
			fill(255, 109, 109);
		square(buttonsPositionX, miniGameButtonY, buttonsSize);
		
		if(overButton && whichButton == 2)
			fill(255, 139,106);
		else
			fill(255, 109, 109);
		square(buttonsPositionX, feedButtonY, buttonsSize);
		
		if(overButton && whichButton == 3)
			fill(255, 139,106);
		else
			fill(255, 109, 109);
		square(buttonsPositionX, sleepButtonY, buttonsSize);
		// Test if the cursor is over the box
		checkOverBox();
	}
	
	public void petSetup() {
		stroke(0);
		fill(255);
		square(200, 160, 160);	//temporary pet placement
		if(age == 0) {
			if(sleeping)
				image(babySleep, 200, 160, 160, 160);
			else if(petting)
				image(babyPet, 200, 160, 160, 160);
			else if(happiness <= 20)
				image(babySad, 200, 160, 160, 160);
			else
				image(baby, 200, 160, 160, 160);
		}
		else if(age == 1) {
			if(sleeping)
				image(teenSleep, 200, 160, 160, 160);
			else if(petting)
				image(teenPet, 200, 160, 160, 160);
			else if(happiness <= 30)
				image(teenSad, 200, 160, 160, 160);
			else
				image(teen, 200, 160, 160, 160);
		}
		else if(age == 2) {
			if(sleeping)
				image(adultSleep, 200, 160, 160, 160);
			else if(petting)
				image(adultPet, 200, 160, 160, 160);
			else if(happiness <= 40)
				image(adultSad, 200, 160, 160, 160);
			else
				image(adult, 200, 160, 160, 160);
		}
		else if(age == 3) {
			if(happiness <= 50)
				image(elderSad, 200, 160, 160, 160);
			else if(petting)
				image(elderPet, 200, 160, 160, 160);
			else if(sleeping)
				image(elderSleep, 200, 160, 160, 160);
			else
				image(elder, 200, 160, 160, 160);
		}
		checkOverPet();
	}
	
	public void barSetup() {
		//barWidth, barHeight, and barX declared in class.
		float curve = 8;
		float barSeparation = 20;
		
		int r1 = 255;
		int g1 = 244;
		int b1 = 147;
		int r2 = 249;
		int g2 = 224;
		int b2 = 0;
		float hBarY = 20;
		float hWidth = (float) happiness / maxHappiness * barWidth;
		stroke(242, 218, 0);
		fill(r1,g1,b1);
		rect(barX, hBarY, barWidth, barHeight, curve);
		fill(r2, g2, b2);
		stroke(248, 204, 1);
		rect(barX + 1, hBarY + 1, hWidth - 1, barHeight - 2, curve);
		
		r1 = 255;
		g1 = 149;
		b1 = 152;
		r2 = 255;
		g2 = 83;
		b2 = 87;
		float fBarY = hBarY + barHeight + barSeparation;
		float fWidth = (float) food / maxFood * barWidth;
		stroke(255, 111, 114);
		fill(r1, g1, b1);
		rect(barX, fBarY, barWidth, barHeight, curve);
		stroke(223, 0, 5);
		fill(r2, g2, b2);
		rect(barX + 1, fBarY + 1, fWidth - 1, barHeight - 2, curve);
		
		r1 = 124;
		g1 = 172;
		b1 = 242;
		r2 = 63;
		g2 = 137;
		b2 = 237;
		float eBarY = hBarY + 2 * barHeight + 2 * barSeparation;
		float eWidth = (float) energy / maxEnergy * barWidth;
		stroke(96, 157, 240);
		fill(r1, g1, b1);
		rect(barX, eBarY, barWidth, barHeight, curve);
		stroke(31, 118, 235);
		fill(r2, g2, b2);
		rect(barX + 1, eBarY + 1, eWidth - 1, barHeight - 2, curve);
	}
	
	public void textSetup() {
		textAlign(LEFT);
		float textX = barX;
		float textY = barHeight - 2;
		PFont font;
		font = createFont("arial.ttf", 20);
		textFont(font);
		text("Happiness", textX, textY);
		text("Food", textX, textY + 2*barHeight);
		text("Energy", textX, textY + 4*barHeight);
		text("Drag to pet", 230, 345);
		
		stroke(255);
		fill(255);
		rect(280, 0, 120, 30);
		fill(0);
		text("Money: " + money, 280, 20);
		
		textAlign(CENTER);
		textLeading(20);
		float hLabelX = buttonsPositionX;
		float hLabelY = miniGameButtonY;
		text("Mini Game", hLabelX, hLabelY + 5, buttonsSize, buttonsSize - 5);
		
		float fLabelX = buttonsPositionX;
		float fLabelY = feedButtonY;
		text("Feed", fLabelX, fLabelY + 15, buttonsSize, buttonsSize - 5);
		
		float sLabelX = buttonsPositionX;
		float sLabelY = sleepButtonY;
		text("Sleep", sLabelX, sLabelY + 15, buttonsSize, buttonsSize - 5);
	}
	
	public void checkOverBox() {
		if (mouseX > buttonsPositionX && mouseX < buttonsPositionX+buttonsSize && mouseY > miniGameButtonY && mouseY < miniGameButtonY+buttonsSize) {
			overButton = true;
			whichButton = 1;
		} else if(mouseX > buttonsPositionX && mouseX < buttonsPositionX+buttonsSize && mouseY > feedButtonY && mouseY < feedButtonY+buttonsSize) {
			overButton = true;
			whichButton = 2;
		} else if(mouseX > buttonsPositionX && mouseX < buttonsPositionX+buttonsSize && mouseY > sleepButtonY && mouseY < sleepButtonY+buttonsSize) {
			overButton = true;
			whichButton = 3;
		} else {
			overButton = false;
		}
	}
	
	public void checkOverPet() {
		if(mouseX > 200 && mouseX < 200 + 160 && mouseY > 160 && mouseY < 160+160) {
			overPet = true;
		} else {
			overPet = false;
		}
	}

	public static void animal(){
		
	}

	//pass a random difficulty value, more difficult = more money
	public static void miniGame() {
		if(energy >= 10 && food>= 5 && happiness > happiness * 0.4 && !sleeping) {
			//MINIGAME STUFF
			//gameRunning = true;
			//beginStop();
			energy(-10);
			food(-5);
			money(10); //pass money() function that gives more money depending on minigame difficulty.
			happiness2(); // CHANGED BY DAVID
			Minigame.execute();
		}
	}
	
	public static void feed(){
		if(money >= 5 && !sleeping) {
			//System.out.println("test");
			money(-5);
			food(10);
			happiness2(); // CHANGED BY DAVID
			 if(energy < maxEnergy / 2) {
				 energy(5);
				if(energy > maxEnergy / 2)
					energy = maxEnergy / 2;
			} else {
			//NOTIFY YOU HAVE NOT ENOUGH MONEY!!
			}
		}
	}


	//need time
	public static void sleep(){
		if(!sleeping) {
			pettingCounter = 0; // CHANGED BY DAVID
			sleeping = true;
			sleep = new Timer();
			sleep.schedule(new SleepTimer(), 5000, 5000); //set back to 1 minute
			System.out.println("Going to sleep...");
		}
	}

	//pet animal (increase happiness by x, decrease energy and food by 1
	public static void pet(){
		if(sleeping) {
			sleeping = false;
			petting = true;
			sleep.cancel();
			//sleep.cancel();
			System.out.println("Waking up!");
			saveGame();
		}
		else if(energy > 0) {
			happiness2(); // CHANGED BY DAVID
			pettingCounter++; // CHANGED BY DAVID
			//happiness(5);
			if(happiness > maxHappiness) 
				happiness = maxHappiness;
			if(pettingCounter < 30 && energy < 2*maxEnergy/3) {
				energy(1); // CHANGED BY DAVID
			}
			//energy(-1);
		}
	}

	public static void food(int amount){
		food += amount;
		if(food > maxFood)
			food = maxFood;
		else if(food < 0)
			food = 0;
	}

	//need minigame
	public static void money(int amount){
		money += amount;
	}

//	public static void happiness(int amount) { // CHANGED BY DAVID (commented out)
//		happiness += amount;
//		if(happiness > maxHappiness) 
//			happiness = maxHappiness;
//		else if(happiness < 0)
//			happiness = 0;
//	}
	
	// Updates happiness
	public static void happiness2() { // CHANGED BY DAVID
		happiness = food/2 + energy/2;
		if(happiness > maxHappiness) 
			happiness = maxHappiness;
		else if(happiness < 0)
			happiness = 0;
	}

	//need time
	public static void energy(int amount){
		energy += amount;
		if(energy > maxEnergy)
			energy = maxEnergy;
		else if(energy < 0)
			energy = 0;
	}
	
	//need time
	public static void increaseAge(){

		age += 1;
		
		switch(age){
			case 1:
				barWidth = 175;
				maxHappiness = 75;
				maxFood = 75;
				maxEnergy = 75;
				break;
			case 2:
				barWidth = 200;
				maxHappiness = 100;
				maxFood = 100;
				maxEnergy = 100;
				break;
			case 3:
				barWidth = 225;
				maxHappiness = 125;
				maxFood = 125;
				maxEnergy = 125;
				break;
		}
//		barWidth += 25;
//		maxHappiness += 25;
//		maxFood += 25;
//		maxEnergy += 25;
		
	}
	
	// DELETE THIS LATER!!!!!
	public static void printStuff(){
		System.out.println("Happines: "+happiness);
		System.out.println("Food: "+food);
		System.out.println("Energy: "+energy);
		System.out.println("Age: "+age);
		System.out.println("Money: "+money);
	}
	
	
	public void checkValues() {
		if(happiness == 0 && happinessAtZeroFlag == 0) {
			happinessAtZeroFlag = 1;
			happinessAtZero = new Timer();
			happinessAtZero.schedule(new AtZeroTimer("happiness"), 5000, 5000); //change to 1 min
			System.out.println("Whoa! Happiness! Do something!");
		}else if(happiness > 0 && happinessAtZeroFlag == 1){
			happinessAtZeroFlag = 0;
			happinessAtZero.cancel();
			System.out.println("Thanks for solving happines");
		}
		if(food == 0 && foodAtZeroFlag == 0) {
			foodAtZeroFlag = 1;
			foodAtZero = new Timer();
			foodAtZero.schedule(new AtZeroTimer("food"), 5000, 5000); //change to 1 min
			System.out.println("Whoa! Food! Do something!");
		}else if(food > 0 && foodAtZeroFlag == 1){
			foodAtZeroFlag = 0;
			foodAtZero.cancel();
			System.out.println("Thanks for solving food.");
		}
		if(energy == 0 && energyAtZeroFlag == 0) {
			energyAtZeroFlag = 1;
			energyAtZero = new Timer();
			energyAtZero.schedule(new AtZeroTimer("energy"), 5000, 5000); //change to 1 min
			System.out.println("Whoa! energy! Do something!");
		}else if(energy > 0 && energyAtZeroFlag == 1){
			energyAtZeroFlag = 0;
			energyAtZero.cancel();
			System.out.println("Thanks for solving energy.");
		}
	}
	
	public static void gameOver() {
		System.out.println("You neglected your pet and it died... rip.");
		//Set the file to have the default beginning values
		gameOver = true;
		//stop the buttons from functioning.
	}
	
	public static void execute(){
		//printStuff();
		time = new Timer();
		time.schedule(new GameTimer(), 10000, 10000); //Set back to every 30s
		PApplet.main("JavJavBeans");
	}
	
	public void mouseClicked() {
		if(!gameOver) {
			if(overButton) {
				switch(whichButton) {
				case 1:
					//MINIGAME STUFF
					miniGame();
					printStuff();
					saveGame();
					break;
				case 2:
					//FEED (Check if you have money)
					feed();
					printStuff();
					fill(70);
					saveGame();
					break;
				case 3:
					//SLEEP STUFF
					sleep();
					saveGame();
					break;
				}
			}
		}
	}
	
	public void mousePressed() {
		if(overPet) {
			petting = true;
			pet();
			printStuff();
			saveGame();
		}
	}
	public void mouseReleased() {
		petting = false;
	}

}
