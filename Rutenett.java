import java.util.ArrayList;

class Rutenett {
    protected int antRader;
    protected int antKolonner;
    protected Celle[][] rutene;

    public Rutenett(int rader, int kolonner) {
        antRader = rader;
        antKolonner = kolonner;
        rutene = new Celle[rader][kolonner];
    }

    void lagCelle(int rad, int kol) {
        Celle celle = new Celle();
        if (Math.random() <= .3333) {
            celle.settLevende();
        }
        rutene[rad][kol] = celle;
    }

    void fyllMedTilfeldigeCeller() {
        for (int rad = 0; rad < antRader; rad++) {
            for (int kol = 0; kol < antKolonner; kol++) {
                if (rutene[rad][kol] == null) {
                    lagCelle(rad, kol);
                }
            }
        }
    }

    void lagDodCelle(int rad, int kol) {
        Celle celle = new Celle();

        // tildeler celler liv tilfeldig

        // if(Math.random()<=.3333){
        // celle.settLevende();
        // }
        rutene[rad][kol] = celle;

    }

    void fyllMedDodeCeller() {
        for (int rad = 0; rad < antRader; rad++) {
            for (int kol = 0; kol < antKolonner; kol++) {
                if (rutene[rad][kol] == null) {
                    lagDodCelle(rad, kol);
                }
            }
        }
    }

    Celle hentCelle(int rad, int kol) {
        if ((rad < antRader && kol < antKolonner) && (rad >= 0 && kol >= 0)) {
            return rutene[rad][kol];
        } else
            return null;
    }

    ArrayList<Celle> hentAlleCeller() {
        ArrayList<Celle> celleListe = new ArrayList<Celle>();
        for (int rad = 0; rad < antRader; rad++) {
            for (int kol = 0; kol < antKolonner; kol++) {
                celleListe.add(rutene[rad][kol]);
            }
        }
        return celleListe;
    }

    void tegnRutenett() {
        for (int rad = 0; rad < antRader; rad++) {
            for (int kol = 0; kol < antKolonner; kol++) {
                if (kol < antKolonner - 1) {
                    System.out.print(rutene[rad][kol].hentStatusTegn());
                } else {
                    System.out.println(rutene[rad][kol].hentStatusTegn());
                }
            }
        }
    }

    void settNaboer(int rad, int kol) {
        Celle denneCella = this.hentCelle(rad, kol);
        int naboR;
        int naboK;

        for (naboR = rad - 1; naboR < (rad + 2); naboR++) {
            for (naboK = kol - 1; naboK < (kol + 2); naboK++) {
                Celle nabo = this.hentCelle(naboR, naboK);
                if (nabo != null && (naboR != rad || naboK != kol)) {
                    denneCella.leggTilNabo(nabo);
                }
            }
        }
    }

    void kobleAlleCeller() {
        for (int rad = 0; rad < antRader; rad++) {
            for (int kol = 0; kol < antKolonner; kol++) {
                this.settNaboer(rad, kol);
            }
        }
    }

    int antallLevende() {
        int antLevende = 0;
        for (int rad = 0; rad < antRader; rad++) {
            for (int kol = 0; kol < antKolonner; kol++) {
                if (rutene[rad][kol].erLevende()) {
                    antLevende++;
                }
            }
        }
        return antLevende;
    }
}
