class Score {
    int Home;
    int Away;

    void printScore() {
        System.out.println(String.format("Home: %d Away: %d\n", this.Home, this.Away));
    }

    void printScoreDifference() {
        System.out.println(String.format("Score difference: %d", this.difference()));
    }

    /*
    void printHome() {
        System.out.print(this.Home + "\n");
    }

    void printAway() {
        System.out.print(this.Away + "\n");
    }
*/
    int difference() {
        return Home - Away;
    }

}