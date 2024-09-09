import java.util.ArrayList;

class Verden {
    Rutenett rutenett;
    int genNr;
    int antRader;
    int antKolonner;

    public Verden(int antRader, int antKolonner) {
        rutenett = new Rutenett(antRader, antKolonner);
        genNr = 0;
        this.antRader = antRader;
        this.antKolonner = antKolonner;
        // rutenett.fyllMedTilfeldigeCeller();
        rutenett.fyllMedDodeCeller();
        rutenett.kobleAlleCeller();
    }

    void tegn() {
        rutenett.tegnRutenett();
        System.out.println("Generasjonsnummer: " + genNr);
        System.out.println("Antall levende celler: " + rutenett.antallLevende());
    }

    void oppdatering() {
        ArrayList<Celle> celleListe = rutenett.hentAlleCeller();
        for (int celle = 0; celle < celleListe.size(); celle++) {
            celleListe.get(celle).tellLevendeNaboer();
        }
        for (int celle = 0; celle < celleListe.size(); celle++) {
            celleListe.get(celle).oppdaterStatus();
        }
        genNr++;
    }

    public int hentGenNr() {
        return genNr;
    }

    public int hentAntLev() {
        return rutenett.antallLevende();
    }
}
