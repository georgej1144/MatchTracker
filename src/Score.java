class Score {
    int Home;
    int Away;

    void printScore() {
        System.out.println(String.format("Home: %d Away: %d\n", this.Home, this.Away));
    }

    void printScoreDifference() {
        System.out.println(String.format("Score difference: %d", this.difference()));
    }

    int difference() {
        return Home - Away;
    }

}