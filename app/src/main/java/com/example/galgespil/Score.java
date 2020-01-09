package com.example.galgespil;

public class Score {

    String navn;
    int score;


    public Score(String navn, int score){
        this.navn = navn;
        this.score = score;
    }

        //Getters og setters
        public String getNavn() {
            return navn;
        }

        public void setNavn(String navn) {
            this.navn = navn;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

    @Override
    public String toString() {
        return navn + " " + score;
    }
}


