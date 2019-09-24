import java.util.*;

public class Main {

	private static Field master = new Field();
	private static Scanner input = new Scanner(System.in);	//the scanner
	private static ArrayList<String> inputArr = new ArrayList<>();

/*
	static void scoreStack(String color, int team, int direction) {	//method to score a cube in a stack.
		if(team > 0) {	//team 1 = you, -1 = opponent. controls where you're sending the point
			if(color.equals("green")) {
				green[0] += direction;
				green[3] -= direction;	//if they chose to score green increase green cubes score and decrease reserve
			} else
			if(color.equals("purple")) {
				purple[0] += direction;	//same but with purple
				purple[3] -= direction;
			} else
			if(color.equals("orange")) {
				orange[0] += direction;	//same but with orange
				orange[3] -= direction;
			} else {
					System.out.println("Incorrect input, please restart");	//if no correct color was given
			}
		} else if (team < 0) {
			if(color.equals("green")) {
				green[1] += direction;	//same but with green and for other team scoring
				green[3] -= direction;
			} else
			if(color.equals("purple")) {
				purple[1] += direction;	//same but with purple and for other team scoring
				purple[3] -= direction;
			} else
			if(color.equals("orange")) {
				orange[1] += direction;	//score but with orange and for other team scoring
				orange[3] -= direction;
			} else {
					System.out.println("Incorrect input, please restart");
			}					//cases for if some bullshit was entered
		} else {
					System.out.println("Incorrect input, please restart");
			}
	}

*/
/*
	static void scoreTower(String color, int direction) {	//method to score a cube in a tower
		if(color.equals("green")) {
				green[2] += direction;		//increases score in green tower decrease reserve
				green[3] -= direction;
			} else
			if(color.equals("purple")) {
				purple[2] += direction;		//same but with purple
				purple[3] -= direction;
			} else
			if(color.equals("orange")) {
				orange[2] += direction;		//same but with orange
				orange[3] -= direction;
			} else {
					System.out.println("Incorrect input, please restart");	//case for if some bullshit was entered
			}
	}
*/

	private static void manual() {	//method to manually and rapidly input scores for a whole color at once
		int scoreFor;
		int scoreAgainst;
		int tower;
		for(int i = 0; i < 3; i++) {
			switch (i) {
				case 0:
					System.out.println("Input for green");
					break;
				case 1:
					System.out.println("Input for purple");
					break;
				case 2:
					System.out.println("Input for orange");
					break;
			}
			System.out.println("Number scored for you:");
			scoreFor = input.nextInt();
			System.out.println("Number scored for opponent:");
			scoreAgainst = input.nextInt();
			System.out.println("Number in towers");
			tower = input.nextInt();
			switch (i) {
				case 0:
					//System.out.println("assigning green");
					master.green[0] = scoreFor;
					master.green[1] = scoreAgainst;	//change green array for the inputs given
					master.green[2] = tower;
					master.green[3] = 22 - (master.green[0] + master.green[1]);
					break;
				case 1:
					//System.out.println("assigning purple");
					master.purple[0] = scoreFor;		//change purple array for the inputs given
					master.purple[1] = scoreAgainst;
					master.purple[2] = tower;
					master.purple[3] = 22 - (master.purple[0] + master.purple[1]);
					break;
				case 2:
					//System.out.println("assigning purple");
					master.orange[0] = scoreFor;
					master.orange[1] = scoreAgainst;		//change orange array for the inputs given
					master.orange[2] = tower;
					master.orange[3] = 22 - (master.orange[0] + master.orange[1]);
					break;
			}
		}
	}

	private static void manual(int[] gr, int[] pu, int[] or) {	//method to manually and rapidly input scores for a whole color at once
		for(int i = 0; i < 3; i++) {
			master.green[i] = gr[i];
			master.purple[i] = pu[i];        //change purple array for the inputs given
			master.orange[i] = or[i];
		}
		master.green[3] = 22 - (master.green[0] + master.green[1]);
		master.purple[3] = 22 - (master.purple[0] + master.purple[1]);
		master.orange[3] = 22 - (master.orange[0] + master.orange[1]);
	}

	private static void processInput() {
		String[] split;
		int[] gr = {0, 0, 0};
		int[] pu = {0, 0, 0};
		int[] or = {0, 0, 0};
		for(int i = 0; i < 3; i++) {
			split = inputArr.get(i).split("\\s+");
			switch (i) {
				case 0:
					for (int o = 0; o < 3; o++) {
						gr[o] = Integer.parseInt(split[o]);
					}
					break;
				case 1:
					for (int o = 0; o < 3; o++) {
						pu[o] = Integer.parseInt(split[o]);
					}
					break;
				case 2:
					for (int o = 0; o < 3; o++) {
						or[o] = Integer.parseInt(split[o]);
					}
					break;
			}
		}
		inputArr.clear();
		manual(gr, pu, or);
	}

	public static void main(String[] args) {
		System.out.println("initialize");
		Calc Calc = new Calc();
		boolean useless = false;
		int movesAhead = 0;
		String in;	//general string input
		//Score readout = new Score();
		//boolean loop = true;	//keeps while look going. if i just said while(true) it would bitch about closing the scanner
		//boolean manualBypass = false;		//bypasses standard dialogue
		//String color = "";	//string for color input

		/*int team = 0;		//int to track target team
		int direction = 0;	//int to track direction (score or descore)
		int scoreFor = 0;		//amount to score for you (used in manual entry)
		int scoreAgainst = 0;	//amount to score for opponent (used in manual entry)
		int tower = 0;		//amount to score in towers (used in manual entry)*/
		while(true) {	//user input loop
			//in = "";
			//color = "";
			//manualBypass = false;
			master.printAll();
			if(useless) {
				Calc.moveIterate(master, movesAhead);		//runs to find best next move
				Calc.bestDelta();
			}
			System.out.println("How many moves ahead to calculate?");
			movesAhead = input.nextInt();
			//System.out.println("What happened? [score or descore]");
			System.out.println("Insert scores for ");
			input.nextLine();
			for(int i = 0; i < 3; i++) {
				switch (i) {
					case 0:
						System.out.print("green");
						break;
					case 1:
						System.out.print("purple");
						break;
					case 2:
						System.out.print("orange");
						break;
				}
				System.out.print(". Format [Your stack] [Their stack] [Towers]\n");
				inputArr.add(input.nextLine());
			}
			processInput();
			master.printAll();
/*
			in = input.next();	//ask question and wait for responce (score, descore, manual, out)
			switch (in) {
				case "manual":    //if manual go down manual question/responce line and bypasses rest to reset
					manual();
					break;
				case "out":    //if out just dump the arrays
					master.printAll();
					break;
				default:
					System.out.println("Input invalid, please try again.");
					//manualBypass = true; //skip the rest if some bullshit was entered
					break;
			}*/

/*
			if(!manualBypass) {		//continue question line saving the inputs to decide what to do
				System.out.println("What color was scored? [orange, purple, green]");
				color = input.next(); //no need for flag for incorrect input

				System.out.println("Where did you score? [stack or tower]");
				in = input.next();		//continue question line saving the inputs to decide what to do
				if(in.equals("stack")) {

					System.out.println("Who scored? [us or opponent]");
					in = input.next();			//continue question line saving the inputs to decide what to do
					if(in.equals("us")) {
						team = 1;
					} else if(in.equals("opponent")) {
						team = -1;
					} else {
						team = 0; //flags for incorrect input
					}
					scoreStack(color, team, direction);
				} else if(in.equals("tower")) {
					scoreTower(color, direction);
				} else {
						color = ""; //flags for incorrect input
				}
			}*/
			useless = true;
		}
		//input.close();		//closes input stream to satisfy compiler but this is outside of the loop
	}
}