package com.stir.cscu9t4practical1;

public class SprintEntry extends Entry {

        private int repetitions;
        private int recovery;
        private int dista;

        public SprintEntry(String n, int d, int m, int y, int h, int min, int s, float dist, int rep, int rec) {
            super(n, d, m, y, h, min, s, dist);
            this.repetitions = rep;
            this.recovery = rec;
            this.dista = (int) dist;
        }

    public int getDista() {
        return dista;
    }

    public int getRepetitions() {
            return repetitions;
        }

        public int getRecovery() {
            return recovery;
        }

        public String getSprintEntry() {
            String result = getName()+" sprinted " + getRepetitions() + "x" + getDista() + "m"
                    + " in " + getHour()+":"+ getMin()+":"+ getSec() +
                    " with " + getRecovery() + " minutes recovery on "
                    + getDay()+"/"+ getMonth()+"/"+ getYear() + "\n";
            return result;
        }
}
