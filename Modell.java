public class Modell {
    Verden verden;
    int generasjon;
    Kontroll kontroll;

    int antRader;
    int antKolonner;

    Modell(int antRader, int antKolonner, Kontroll kontroll) {
        verden = new Verden(antRader, antKolonner);
        this.antRader = antRader;
        this.antKolonner = antKolonner;
        this.kontroll = kontroll;
    }

    void oppdatering() {
        verden.oppdatering();
    }

    int hentGenNr() {
        return verden.hentGenNr();
    }

    int hentAntallLevende() {
        return verden.hentAntLev();
    }

    void nyVerden() {
        verden = new Verden(antRader, antKolonner);
    }

    void start() {
        oppdatering();
    }

    int hentAntallKolonner() {
        return verden.antKolonner;
    }

    int hentAntallRader() {
        return verden.antKolonner;
    }

}
