import java.util.*;

class Calc {

	private ArrayList<Field> deltaCache = new ArrayList<>();
	private Score preCalcScore;

	private boolean compare(Field field1, Field field2) {
	    boolean check = false;
	    Collections.sort(field1.lastMove);
	    Collections.sort(field2.lastMove);
	    for(String move : field1.lastMove) {
	        System.out.println(move);
        }
        for(String move : field2.lastMove) {
            System.out.println(move);
        }
        /*CONDITIONS:
        * if sorted field1 and sorted field2 are completely equal
        * if they have different sizes they aren't
        */
        if(field1.lastMove.size() != field2.lastMove.size()) {
            return true;
        }
        for(int i = 0; i < field1.lastMove.size(); i++) {
            if (!field1.lastMove.get(i).equals(field2.lastMove.get(i))) {
                check = true;
                break;
            }
        }
        System.out.print(check);
        return check;
    }

	void bestDelta() {
		Field best1 = new Field();
		Field best2 = new Field();
		Field best3 = new Field();
		for (Field field : deltaCache) {
			Collections.sort(field.lastMove);
			Collections.sort(best1.lastMove);
			Collections.sort(best2.lastMove);
			Collections.sort(best3.lastMove);
			if (field.currentScore().difference() > best1.currentScore().difference() && compare(field, best1) && compare(field, best2) && compare(field, best3)) {
				best3 = best2;
				best2 = best1;
				best1 = field;
			} else if (field.currentScore().difference() > best2.currentScore().difference() && compare(field, best1) && compare(field, best2) && compare(field, best3)) {
				best3 = best2;
				best2 = field;
			} else if (field.currentScore().difference() > best1.currentScore().difference() && compare(field, best1) && compare(field, best2) && compare(field, best3)) {
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
		this.deltaCache.clear();
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
	}
}