class Celle {
    boolean levende;
    Celle[] naboer;
    public int antNaboer;
    int antLevendeNaboer;

    public Celle() {
        levende = false;
        naboer = new Celle[8];
        antNaboer = 0;
        antLevendeNaboer = 0;
    }

    void settDoed() {
        levende = false;
    }

    void settLevende() {
        levende = true;
    }

    boolean erLevende() {
        return levende;
    }

    char hentStatusTegn() {
        if (levende) {
            return 'O';
        } else {
            return ' ';
        }
    }

    void leggTilNabo(Celle nabo) {
        naboer[antNaboer] = nabo;
        antNaboer++;
    }

    void tellLevendeNaboer() {
        antLevendeNaboer = 0;
        for (int i = 0; i < antNaboer; i++) {
            if (naboer[i].erLevende()) {
                antLevendeNaboer++;
            }
        }
    }

    void oppdaterStatus() {
        if (levende) {
            if (antLevendeNaboer < 2 || antLevendeNaboer > 3) {
                levende = false;
            }
        } else if (!levende) {
            if (antLevendeNaboer == 3) {
                levende = true;
            }
        }
    }
}
