import java.util.*;

class Calc {

	private ArrayList<Field> deltaCache = new ArrayList<>();
	private Score preCalcScore;

	void bestDelta() {
		Field best1 = new Field();
		Field best2 = new Field();
		Field best3 = new Field();
		for (Field field : deltaCache) {
			Collections.sort(field.lastMove);
			Collections.sort(best1.lastMove);
			Collections.sort(best2.lastMove);
			Collections.sort(best3.lastMove);
			if (field.currentScore().difference() > best1.currentScore().difference() && ((field.lastMove.equals(best1.lastMove) & field.lastMove.size() > field.lastMove.size()) || best1.lastMove.size() == 1)) {
				best3 = best2;
				best2 = best1;
				best1 = field;
			} else if (field.currentScore().difference() > best2.currentScore().difference() && (field.lastMove.equals(best2.lastMove) || best2.lastMove.size() == 1)) {
				best3 = best2;
				best2 = field;
			} else if (field.currentScore().difference() > best1.currentScore().difference() && (field.lastMove.equals(best3.lastMove) || best3.lastMove.size() == 1)) {
				best3 = field;
			}
		}
		//mass output of best moves
		System.out.println("-----Results from greatest to least-----");
		best1.currentScore().printScore();
		System.out.println(String.format("Resulting score difference: %d\nDifference in score difference: %d", best1.currentScore().difference(), best1.currentScore().difference() - preCalcScore.difference()));
		System.out.print("Path: ");
		best1.lastMoveDump();
		System.out.println("\n");
		best2.currentScore().printScore();
		System.out.println(String.format("Resulting score difference: %d\nDifference in score difference: %d", best2.currentScore().difference(), best2.currentScore().difference() - preCalcScore.difference()));
		System.out.print("Path: ");
		best2.lastMoveDump();
		System.out.println("\n");
		best3.currentScore().printScore();
		System.out.println(String.format("Resulting score difference: %d\nDifference in score difference: %d", best3.currentScore().difference(), best3.currentScore().difference() - preCalcScore.difference()));
		System.out.print("Path: ");
		best3.lastMoveDump();
		System.out.println("\n");
	}

	void moveIterate(Field start, int n) {
		Field input;
		input = (Field) Field.deepClone(start);
		assert input != null;
		deltaCache.clear();
		preCalcScore = input.currentScore();
		deltaCache.add(input);
		for(int i = 0; i < Math.pow(12, n-1); i++) {
			deltaCache.add(input.addGreenStack(deltaCache.get(i)));
			deltaCache.add(input.addPurpleStack(deltaCache.get(i)));
			deltaCache.add(input.addOrangeStack(deltaCache.get(i)));
			deltaCache.add(input.addGreenTower(deltaCache.get(i)));
			deltaCache.add(input.addPurpleTower(deltaCache.get(i)));
			deltaCache.add(input.addOrangeTower(deltaCache.get(i)));
			deltaCache.add(input.swapGreenTowerOrange(deltaCache.get(i)));
			deltaCache.add(input.swapPurpleTowerOrange(deltaCache.get(i)));
			deltaCache.add(input.swapOrangeTowerPurple(deltaCache.get(i)));
			deltaCache.add(input.swapGreenTowerPurple(deltaCache.get(i)));
			deltaCache.add(input.swapPurpleTowerGreen(deltaCache.get(i)));
			deltaCache.add(input.swapOrangeTowerGreen(deltaCache.get(i)));
			input = deltaCache.get(i);
		}
		/*for(int i = 0; i < deltaCache.size() - Math.pow(12, n-1); i++) {
			deltaCache.remove(1);
		}*/
	}
}