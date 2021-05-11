import java.util.TimerTask;

public class GameTimer extends TimerTask {

	int ageCount = 0;
	int minuteCount = 0;

//	public synchronized void beginStop() {
//		try {
//			System.out.println("waited!");
//			this.wait();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public void run() {
//		if(JavJavBeans.gameRunning) {
//			beginStop();
//		}
		
		if (JavJavBeans.gameOver) {
			this.cancel();
		} else {
			if (JavJavBeans.happiness > 0)
				JavJavBeans.happiness2(); // CHANGED BY DAVID
				//JavJavBeans.happiness(-2); //change back to -1
			if (JavJavBeans.food > 0)
				JavJavBeans.food(-2); //change back to -1
			if (!JavJavBeans.sleeping)
				JavJavBeans.energy(-2); //change back to -1
			ageCount++;
			minuteCount++;
			if (ageCount == 3 && JavJavBeans.age < 3) {
				JavJavBeans.increaseAge();
				ageCount = 0;
			}
			JavJavBeans.saveGame();
			
			JavJavBeans.printStuff();
		}
	}
}

class SleepTimer extends TimerTask {
	public void run() {
		if (JavJavBeans.gameOver) {
			this.cancel();
		} else {
			JavJavBeans.energy(5);
			System.out.println("Animal is sleeping...");
			System.out.println(JavJavBeans.energy);
		}
	}
}

class AtZeroTimer extends TimerTask {
	int minutesAtZero;
	String problemSource = "";

	public AtZeroTimer(String source) {
		this.problemSource = source;
		this.minutesAtZero = 0;
	}

	public void run() {
		if (JavJavBeans.gameOver) {
			this.cancel();
		} else {
			minutesAtZero++;
			if (problemSource.equals("happiness") && minutesAtZero == 3 && JavJavBeans.happiness == 0) {
				JavJavBeans.gameOver();
				this.cancel();
			} else if (problemSource.equals("happiness"))
				System.out
						.println("Jav Jav's happiness has been at 0 for " + minutesAtZero + " minutes. Do something!");

			if (problemSource.equals("food") && minutesAtZero == 3 && JavJavBeans.food == 0) {
				JavJavBeans.gameOver();
				this.cancel();
			} else if (problemSource.equals("food"))
				System.out.println("Jav Jav's food has been at 0 for " + minutesAtZero + " minutes. Do something!");

			if (problemSource.equals("energy") && minutesAtZero == 3
					&& (JavJavBeans.energy == 0 && !JavJavBeans.sleeping)) {
				JavJavBeans.gameOver();
				this.cancel();
			} else if (problemSource.equals("energy") && !JavJavBeans.sleeping) {
				System.out.println("Jav Jav's energy has been at 0 for " + minutesAtZero + " minutes. Do something!");
			}
			if (problemSource.equals("energy") && JavJavBeans.sleeping)
				minutesAtZero--;
		}
	}
}