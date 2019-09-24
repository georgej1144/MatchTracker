import java.io.*;
import java.util.*;

class Field implements Cloneable, Serializable {

    int[] green = {0, 0, 0, 22};
    int[] purple = {0, 0, 0, 22};
    int[] orange = {0, 0, 0, 22};
    ArrayList<String> lastMove = new ArrayList<>();

    //private static int[] initializeArr = {0, 0, 0, 22};

    Field() {
        lastMove.add("");
    }

    /*
    private Field(int[] gr, int[] pu, int[] or){
        lastMove.add("");
        green = gr;
        purple = pu;
        orange = or;
    }*/

    /*Field(Field source) {
        green = source.green;
        purple = source.green;
        orange = source.orange;
        lastMove = source.lastMove;
    }*/

    static Object deepClone(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Field clone() throws CloneNotSupportedException {
        return (Field) super.clone();	// return shallow copy
    }

    /*
    private Field cloneField() {
        Field output = null;
        try {
            output = this.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        return output;
    }*/

    Score currentScore() {
        Score output = new Score();

        output.Home = this.green[0] * (this.green[2] + 1);
        output.Home += this.purple[0] * (this.purple[2] + 1);	//calculates total score of your team. each cube in tower increases points of same color cube in stack by 1
        output.Home += this.orange[0] * (this.orange[2] + 1);

        output.Away = this.green[1] * (this.green[2] + 1);
        output.Away += this.purple[1] * (this.purple[2] + 1);	//calculates total score of other team
        output.Away += this.orange[1] * (this.orange[2] + 1);
        return output;
    }

    private void printTowers() {
        System.out.println("Green Cubes in Tower: " + this.green[2]);
        System.out.println("Purple Cubes in Tower: " + this.purple[2]);
        System.out.println("Orange Cubes in Tower: " + this.orange[2]);
    }

    private void printStacks() {
        System.out.println(String.format("Green at home: %d | Green away: %d", this.green[0], this.green[1]));
        System.out.println(String.format("Purple at home: %d | Purple away: %d", this.purple[0], this.purple[1]));
        System.out.println(String.format("Orange at home: %d | Orange away: %d", this.orange[0], this.orange[1]));
    }

    void lastMoveDump() {
        for(int i = 1; i < lastMove.size(); i++) {
            System.out.print(lastMove.get(i));
            if(!(i == lastMove.size() - 1)) System.out.print(", ");

        }
        System.out.println("\n");
    }

    void printAll() {
        Score output;
        output = currentScore();
        printTowers();
        printStacks();
        output.printScore();
        output.printScoreDifference();
    }

    Field addGreenStack(Field seed) {
        Field output;
        output = (Field) Field.deepClone(seed);
        if(!lastMove.get(lastMove.size() - 1).equals("CLOSEg")) {
            assert output != null;
            if(output.green[3] > 0) {
                output.lastMove.add("Add Green Cube");
                output.green[0] += 1;
                output.green[3] -= 1;
            } else {
                output.lastMove.add("No more available Green Cubes");
                output.lastMove.add("CLOSEg");
            }
        }
        return output;
    }

    Field addPurpleStack(Field seed) {
        Field output;
        output = (Field) Field.deepClone(seed);
        if(!lastMove.get(lastMove.size() - 1).equals("CLOSEp")) {
            assert output != null;
            if(output.purple[3] > 0) {
                output.lastMove.add("Add Purple Cube");
                output.purple[0] += 1;
                output.purple[3] -= 1;
            } else {
                output.lastMove.add("No more available Purple Cubes");
                output.lastMove.add("CLOSEp");
            }
        }
        return output;
    }

    Field addOrangeStack(Field seed) {
        Field output;
        output = (Field) Field.deepClone(seed);
        if(!lastMove.get(lastMove.size() - 1).equals("CLOSEo")) {
            assert output != null;
            if(output.orange[3] > 0) {
                output.lastMove.add("Add Orange Cube Stack");
                output.orange[0] += 1;
                output.orange[3] -= 1;
            } else {
                output.lastMove.add("No more available Orange Cubes");
                output.lastMove.add("CLOSEo");
            }
        }
        return output;
    }

    Field addGreenTower(Field seed) {
        Field output;
        output = (Field) Field.deepClone(seed);
        if(!lastMove.get(lastMove.size() - 1).equals("CLOSEg")) {
            assert output != null;
            if (output.green[3] > 0) {
                output.lastMove.add("Add Green Cube Tower");
                output.green[2] += 1;
                output.green[3] -= 1;
            } else {
                output.lastMove.add("No more available Green Cubes");
                output.lastMove.add("CLOSEg");
            }
        }
        return output;
    }

    Field addPurpleTower(Field seed) {
        Field output;
        output = (Field) Field.deepClone(seed);
        if(!lastMove.get(lastMove.size() - 1).equals("CLOSEp")) {
            assert output != null;
            if(output.purple[3] > 0) {
                output.lastMove.add("Add Purple Cube Tower");
                output.purple[2] += 1;
                output.purple[3] -= 1;
            } else {
                output.lastMove.add("No more available Purple Cubes");
                output.lastMove.add("CLOSEp");
            }
        }
        return output;
    }

    Field addOrangeTower(Field seed) {
        Field output;
        output = (Field) Field.deepClone(seed);
        if(!lastMove.get(lastMove.size() - 1).equals("CLOSEo")) {
            assert output != null;
            if(output.orange[3] > 0) {
                output.lastMove.add("Add Orange Cube Tower");
                output.orange[2] += 1;
                output.orange[3] -= 1;
            } else {
                output.lastMove.add("No more available Orange Cubes");
                output.lastMove.add("CLOSEo");
            }
        }
        return output;
    }

    Field swapGreenTowerOrange(Field seed) {
        Field output;
        output = (Field) Field.deepClone(seed);
        if(!lastMove.get(lastMove.size() - 1).equals("CLOSEsgo")) {
            assert output != null;
            if (output.green[3] > 0 && output.orange[2] > 0) {
                output.lastMove.add("Swap Green with Orange");
                output.green[2] += 1;
                output.green[3] -= 1;
                output.orange[2] -= 1;
                output.orange[3] += 1;
            } else {
                output.lastMove.add("Swap Unavailable");
                output.lastMove.add("CLOSEsgo");
            }
        }
        return output;
    }

    Field swapPurpleTowerOrange(Field seed) {
        Field output;
        output = (Field) Field.deepClone(seed);
        if(!lastMove.get(lastMove.size() - 1).equals("CLOSEspo")) {
            assert output != null;
            if(output.purple[3] > 0 && output.orange[2] > 0) {
                output.lastMove.add("Swap Purple with Orange");
                output.purple[2] += 1;
                output.purple[3] -= 1;
                output.orange[2] -= 1;
                output.orange[3] += 1;
            } else {
                output.lastMove.add("Swap Unavailable");
                output.lastMove.add("CLOSEspo");
            }
        }
        return output;
    }

    Field swapOrangeTowerPurple(Field seed) {
        Field output;
        output = (Field) Field.deepClone(seed);
        if(!lastMove.get(lastMove.size() - 1).equals("CLOSEsop")) {
            assert output != null;
            if(output.orange[3] > 0 && output.purple[2] > 0) {
                output.lastMove.add("Swap Orange with Purple");
                output.orange[2] += 1;
                output.orange[3] -= 1;
                output.purple[2] -= 1;
                output.purple[3] += 1;
            } else {
                output.lastMove.add("Swap Unavailable");
                output.lastMove.add("CLOSEsop");
            }
        }
        return output;
    }

    Field swapGreenTowerPurple(Field seed) {
        Field output;
        output = (Field) Field.deepClone(seed);
        if(!lastMove.get(lastMove.size() - 1).equals("CLOSEsgp")) {
            assert output != null;
            if (output.green[3] > 0 && output.purple[2] > 0) {
                output.lastMove.add("Swap Green with Purple");
                output.green[2] += 1;
                output.green[3] -= 1;
                output.purple[2] -= 1;
                output.purple[3] += 1;
            } else {
                output.lastMove.add("Swap Unavailable");
                output.lastMove.add("CLOSEsgp");
            }
        }
        return output;
    }

    Field swapPurpleTowerGreen(Field seed) {
        Field output;
        output = (Field) Field.deepClone(seed);
        if(!lastMove.get(lastMove.size() - 1).equals("CLOSEspg")) {
            assert output != null;
            if(output.purple[3] > 0 && output.green[2] > 0) {
                output.lastMove.add("Swap Purple with Green");
                output.purple[2] += 1;
                output.purple[3] -= 1;
                output.green[2] -= 1;
                output.green[3] += 1;
            } else {
                output.lastMove.add("Swap Unavailable");
                output.lastMove.add("CLOSEspg");
            }
        }
        return output;
    }

    Field swapOrangeTowerGreen(Field seed) {
        Field output;
        output = (Field) Field.deepClone(seed);
        if(!lastMove.get(lastMove.size() - 1).equals("CLOSEsog")) {
            assert output != null;
            if(output.orange[3] > 0 && output.green[2] > 0) {
                output.lastMove.add("Swap Orange with Green");
                output.orange[2] += 1;
                output.orange[3] -= 1;
                output.green[2] -= 1;
                output.green[3] += 1;
            } else {
                output.lastMove.add("Swap Unavailable");
                output.lastMove.add("CLOSEsog");
            }
        }
        return output;
    }
/*
    int scoreDifference() {
        Score output;
        output = currentScore();
        return output.difference();
    }

    int towersOccupied() {
        return this.green[2] + this.purple[2] + this.orange[2];	//calculates how many towers have a cube in them
    }

    int greenCubeDiff() {
        Score output = new Score();
        int[] tGreen = {this.green[0] + 1, this.green[1], this.green[2], this.green[3]};
        Field temp = new Field(tGreen, this.purple, this.orange);
        output = temp.currentScore();
        return output.difference();
    }
    int orangeCubeDiff() {
        Score output = new Score();
        int[] tOrange = {this.orange[0] + 1, this.orange[1], this.orange[2], this.orange[3]};
        Field temp = new Field(this.green, this.purple, tOrange);
        output = temp.currentScore();
        return output.difference();
    }
    int purpleCubeDiff() {
        Score output = new Score();
        int[] tPurple = {this.purple[0] + 1, this.purple[1], this.purple[2], this.purple[3]};
        Field temp = new Field(this.green, tPurple, this.orange);
        output = temp.currentScore();
        return output.difference();
    }

    int greenTowerDiff() {
        Score output = new Score();
        int[] tGreen = {this.green[0], this.green[1], this.green[2] + 1, this.green[3]};
        Field temp = new Field(tGreen, this.purple, this.orange);
        output = temp.currentScore();
        return output.difference();
    }
    int orangeTowerDiff() {
        Score output = new Score();
        int[] tOrange = {this.orange[0], this.orange[1], this.orange[2] + 1, this.orange[3]};
        Field temp = new Field(this.green, this.purple, tOrange);
        output = temp.currentScore();
        return output.difference();
    }
    int purpleTowerDiff() {
        Score output = new Score();
        int[] tPurple = {this.purple[0], this.purple[1], this.purple[2] + 1, this.purple[3]};
        Field temp = new Field(this.green, tPurple, this.orange);
        output = temp.currentScore();
        return output.difference();
    }*/

}