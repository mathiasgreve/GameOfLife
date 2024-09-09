import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI {
    Kontroll kontroll;
    JFrame vindu;
    JPanel panel, konsoll, rutenett;
    JButton startKnapp, stoppKnapp;
    JButton[][] celler;
    public ActionListener start;

    JLabel antLevendeInfo = new JLabel("Antall levende: " + 0);
    JLabel generasjonInfo = new JLabel("Generasjon: " + 0);

    static boolean avsluttet = true;
    Klokke klokke = new Klokke();

    // oppdaterer generasjonsnummer
    void oppdaterGenerasjonsNr() {
        generasjonInfo.setText("Generasjon: " + kontroll.generasjonNr());
    }

    // oppdaterer antall levende celler
    void oppdaterAntallLevende() {
        antLevendeInfo.setText("Antall levende: " + kontroll.antallLevende());
    }

    // klokke som genererer og viser neste generasjon med 2 sekunders intervaller
    class Klokke extends Thread {
        @Override
        public void run() {
            while (!avsluttet) {
                try {
                    if (kontroll.antallLevende() == 0) { // stopper dersom ingen levende celler
                        return;
                    }
                    kontroll.startSpill();
                    kontroll.oppdaterGenerasjonsNr();
                    kontroll.oppdaterAntallLevende();
                    sleep(200);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    // handling for avslutt-knapp
    class AvsluttSpill implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            avsluttet = true;
            System.exit(0);
        }
    }

    // handling for start-knapp
    class StartSpill implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            avsluttet = false;
            klokke.start();
            oppdaterGenerasjonsNr();
            oppdaterAntallLevende();
        }
    }

    GUI(int antRader, int antKolonner, Kontroll k) {
        celler = new JButton[antRader][antKolonner];
        kontroll = k;

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.exit(1);
        }

        // lager et vindu
        JFrame vindu = new JFrame("oO..GaMe Of LiFe..Oo");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // lager bakgrunnspanel og setter det inn i vinduet
        JPanel panelBakgrunn = new JPanel();
        panelBakgrunn.setLayout(new BorderLayout());
        vindu.add(panelBakgrunn);

        // lager og legger til nytt panel (meny) øverst i vinduet
        JPanel panelMeny = new JPanel();
        panelBakgrunn.add(panelMeny);

        // nytt panel til menyknapper øverst i vinduet
        JPanel panelMenyKnapper = new JPanel();
        panelMeny.add(panelMenyKnapper, BorderLayout.NORTH);

        // knapp START
        JButton startKnapp = new JButton("Start");
        start = new StartSpill();
        startKnapp.addActionListener(start);
        panelMenyKnapper.add(startKnapp);

        // knapp AVSLUTT
        JButton avsluttKnapp = new JButton("Avslutt");
        avsluttKnapp.addActionListener(new AvsluttSpill());
        panelMenyKnapper.add(avsluttKnapp);

        // nytt panel (meny) med tekst. øverst i vinduet
        JPanel panelMenyInfo = new JPanel();
        panelMeny.add(panelMenyInfo, BorderLayout.SOUTH);

        // // antall levende
        panelMenyInfo.add(antLevendeInfo);

        // // generasjon
        panelMenyInfo.add(generasjonInfo);

        // lager responsiv klasse for knapper
        class Knapp extends JButton {
            int rad, kol;

            public Knapp(int rad, int kol) {
                super(" ");
                this.rad = rad;
                this.kol = kol;
                super.addActionListener(new settRuteLevende());
            }

            class settRuteLevende implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!kontroll.celleErLevende(rad, kol)) {
                        setText("O");
                        kontroll.settCelleLevende(rad, kol);
                    } else {
                        setText(" ");
                        kontroll.settCelleDoed(rad, kol);
                    }
                }
            }
        }

        // lager rutenettet til GOL
        rutenett = new JPanel();
        rutenett.setLayout(new GridLayout(antRader, antKolonner));
        for (int rx = 0; rx < antRader; ++rx) {
            for (int kx = 0; kx < antKolonner; ++kx) {
                Knapp b = new Knapp(rx, kx);
                celler[rx][kx] = b;
                b.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
                rutenett.add(b);
            }
        }
        panelBakgrunn.add(rutenett, BorderLayout.SOUTH);

        vindu.pack();
        // lager en vindustørrelse
        final int HOYDE = 1000; // i antall pixler
        final int BREDDE = 1200;
        vindu.setSize(BREDDE, HOYDE);

        vindu.setLocationRelativeTo(null);
        vindu.setVisible(true);
    }

    // markerer én rute
    void markerRute(int rad, int kol, char markor) {
        celler[rad][kol].setText(Character.toString(markor));
    }

    // markerer rutene
    void markerRuter() {
        for (int rx = 0; rx < kontroll.hentAntallRader(); ++rx) {
            for (int kx = 0; kx < kontroll.hentAntallKolonner(); ++kx) {
                markerRute(rx, kx, kontroll.hentStatustegnCelle(rx, kx));
            }
        }
    }
}
