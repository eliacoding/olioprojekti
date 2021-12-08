package sample;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.IOException;

//Ottelun käsittelemiseen tarvittavat metodit
public class OtteluHandler {

    File ottelut = new File("src/sample/ottelut.txt");
    String filename = "src/sample/ottelut.txt";
    private Date päivämäärä;
    private String tulos;
    private String vastustaja;
    private ArrayList<Pelaaja> maalintekijät;

    //Lisää uuden ottelun tekstitiedostoon
    public void lisääOttelu(Ottelu ottelu) throws IOException{
        try {
            Files.write(Paths.get(filename), ottelu.toFile().getBytes(), StandardOpenOption.APPEND);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Hakee ottelun tiedot tekstitiedostosta ArrayListiin
    public ArrayList<Ottelu> lataaTiedosto() throws FileNotFoundException, ParseException {
        ArrayList<Ottelu> temp_ottelut = new ArrayList<Ottelu>();
        Scanner s = new Scanner(ottelut);
        Ottelu ottelu = new Ottelu();
        while (s.hasNextLine()) {
            String rivi = s.nextLine();

            String[] pilkottu = rivi.split(";");

            Date date = new SimpleDateFormat("dd.MM.yyyy").parse(pilkottu[0]);
            ottelu.setPäivämäärä(date);

            ottelu.setVastustaja(pilkottu[1]);
            ottelu.setTulos(pilkottu[2]);
            ottelu.setSijainti(pilkottu[3]);

            String[] maalit = pilkottu[4].split(":");

            for (int i=0; i<maalit.length; i++) {

                String[] temp_goals = maalit[i].split(",");

                int temp_pelaaja = Integer.parseInt(temp_goals[0]);
                int temp_maalit = Integer.parseInt(temp_goals[1]);

                ottelu.addPelinumero(temp_pelaaja);
                ottelu.addMaalimäärä(temp_maalit);
            }
            temp_ottelut.add(ottelu);
        }
        return temp_ottelut;
    }
}
