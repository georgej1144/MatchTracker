import java.util.*;

public class Main {

	private static Field master = new Field();
	private static Scanner input = new Scanner(System.in);	//the scanner
	private static ArrayList<String> inputArr = new ArrayList<>();

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
		String in;
		while(true) {
			master.printAll();
			if(useless) {
				Calc.moveIterate(master, movesAhead);		//runs to find best next move
				Calc.bestDelta();
			}
			System.out.println("How many moves ahead to calculate?");
			movesAhead = input.nextInt();
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
			useless = true;
		}
	}
}