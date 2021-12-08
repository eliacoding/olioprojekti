package sample;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Date;
import java.util.Scanner;

//Käyttöliittymä ja sen toiminnot
public abstract class MyFrame extends JFrame implements ActionListener {

    static JOptionPane alert;
    JTextField date;
    JTextField opp_goals;
    JTextField team_goals;
    JTextField opponent;
    JTextField gs_done;
    JButton submit;
    JComboBox choose_match;
    JComboBox home_away;
    JComboBox players;
    JButton add_player;
    JButton show_match;

    //Yhdistää syötetyt maalit String muotoon
    public String tulosString(int maalit1, int maalit2) {
        return maalit1+"-"+maalit2;
    }

    public static void alertMsg(String infoMessage, String titleBar) {
        alert.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    protected ArrayList<Ottelu> ottelut;
    protected ArrayList<Pelaaja> pelaajat;
    protected ArrayList<Integer> pelinrot;
    protected ArrayList<Integer> tehd_maalit;

    MyFrame() throws FileNotFoundException, ParseException {

        OtteluHandler oh = new OtteluHandler();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        PelaajaHandler ph = new PelaajaHandler();

        pelinrot = new ArrayList<>();
        tehd_maalit = new ArrayList<>();

        //------------------------------------------------------------

        date = new JTextField();
        date.setText("Pvm: xx.xx.xxxx");

        opponent = new JTextField();
        opponent.setText("Vastustajajoukkue");

        String[] location = new String[2];
        location[0] = "koti";
        location[1] = "vieras";
        home_away = new JComboBox(location);

        opp_goals = new JTextField();
        opp_goals.setText("Vastustajan maalit");

        team_goals = new JTextField();
        team_goals.setText("Joukkueen maalit");

        ArrayList<Pelaaja> pelaajat = ph.haePelaajat();
        players = new JComboBox(pelaajat.toArray());

        gs_done = new JTextField();
        gs_done.setText("Pelaajan maalit");

        submit = new JButton();
        submit.setText("Lisää ottelu");

        add_player = new JButton();
        add_player.setText("Lisää Pelaaja");

        ArrayList<Ottelu> ottelut = oh.lataaTiedosto();
        choose_match = new JComboBox(ottelut.toArray());

        show_match = new JButton("Hae ottelu");
        //------------------------------------------------------------

        //Lisää pelaajan numeron ja tehdyn maalimäärän ArrayListiin (Lisää Pelaaja nappi)
        add_player.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Scanner sc3 = new Scanner(gs_done.getText());
                    int maalit_per_pelinro = sc3.nextInt();

                    tehd_maalit.add(maalit_per_pelinro);

                    Pelaaja valittu = (Pelaaja)players.getSelectedItem();
                    int valittu_nro = valittu.getPelinumero();

                    pelinrot.add(valittu_nro);

                    gs_done.setText("");

                    MyFrame.alertMsg("Lisätty pelaaja nro: "+ valittu_nro+", Tehdyt maalit: "+maalit_per_pelinro, "Pelaaja lisätty");
                }catch (Error ex){
                }
            }
        });

        //Luo uuden ottelun annettuilla tiedoilla ja suorittaa lisääOttelu metodin OtteluHandlerissa (Lisää ottelu nappi)
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Scanner sc = new Scanner(team_goals.getText());
                    Scanner sc2 = new Scanner(opp_goals.getText());
                    int maalit1 = sc.nextInt();
                    int maalit2 = sc2.nextInt();

                    String tulos = tulosString(maalit1, maalit2);

                    String vastustaja = opponent.getText();

                    String sijainti = (String)home_away.getSelectedItem();

                    String pvm = date.getText();

                    System.out.println(pvm);
                    Date date = new SimpleDateFormat("dd.MM.yyyy").parse(pvm);

                    Ottelu ottelu = new Ottelu(date, vastustaja, tulos, sijainti, pelinrot, tehd_maalit);
                    oh.lisääOttelu(ottelu);
                    MyFrame.alertMsg("Ottelu lisätty", "");

                } catch (Exception exception) {
                    if (!(exception instanceof java.util.InputMismatchException)) {
                        exception.printStackTrace();
                    } else {
                        MyFrame.alertMsg("Syötä vain numeroita", "Maalit");
                    }
                }
            }
        });

        //Näyttää valitun ottelun tiedot (Hae ottelu nappi)
        show_match.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ottelu ottelu = (Ottelu) choose_match.getSelectedItem();
                MyFrame.alertMsg(ottelu.toString(), "Ottelun tiedot");
            }
        });

        this.add(date);
        this.add(opponent);
        this.add(home_away);
        this.add(team_goals);
        this.add(opp_goals);
        this.add(players);
        this.add(gs_done);
        this.add(add_player);
        this.add(choose_match);
        this.add(submit);
        this.add(show_match);
        this.pack();
        this.setVisible(true);
    }

}
