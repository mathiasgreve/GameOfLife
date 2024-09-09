public class Kontroll {
    GUI gui;
    Modell modell;

    Kontroll(int antRader, int antKolonner) {
        gui = new GUI(antRader, antKolonner, this);
        modell = new Modell(antRader, antKolonner, this);
    }

    void startSpill() {
        modell.start();
        gui.markerRuter();
    }

    int generasjonNr() {
        return modell.hentGenNr();
    }

    int antallLevende() {
        return modell.hentAntallLevende();
    }

    void oppdaterGenerasjonsNr() {
        gui.oppdaterGenerasjonsNr();
    }

    void oppdaterAntallLevende() {
        gui.oppdaterAntallLevende();
    }

    boolean celleErLevende(int rad, int kol) {
        return modell.verden.rutenett.hentCelle(rad, kol).erLevende();
    }

    void settCelleLevende(int rad, int kol) {
        modell.verden.rutenett.hentCelle(rad, kol).settLevende();
    }

    void settCelleDoed(int rad, int kol) {
        modell.verden.rutenett.hentCelle(rad, kol).settDoed();
    }

    int hentAntallKolonner() {
        return modell.hentAntallKolonner();
    }

    int hentAntallRader() {
        return modell.hentAntallRader();
    }

    char hentStatustegnCelle(int rad, int kol) {
        return modell.verden.rutenett.hentCelle(rad, kol).hentStatusTegn();
    }

}
