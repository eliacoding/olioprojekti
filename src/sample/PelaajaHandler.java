package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


//Hakee pelaajan tiedot tekstitiedostosta ArrayListiin
public class PelaajaHandler {

    File teksti = new File("src/sample/pelaajat.txt");
    private ArrayList<Pelaaja> pelaajat;
    public  ArrayList<Pelaaja> haePelaajat(){
        pelaajat = new ArrayList<Pelaaja>();
        try {
            Scanner scanner = new Scanner(new FileInputStream(teksti));
            int i = 0;
            String p;
            String[] nimi = new String[50];
            Integer[] numero = new Integer[50];

            while (scanner.hasNextLine())  {
                try {
                    p = scanner.nextLine();
                    String[] k = p.split(" ");
                    nimi[i] = k[0];
                    numero[i] = Integer.parseInt(k[1]);
                    Pelaaja pelaaja = new Pelaaja();
                    pelaaja.setNimi(nimi[i]);
                    pelaaja.setPelinumero(numero[i]);
                    pelaajat.add(pelaaja);
                    i++;
                } catch (java.util.NoSuchElementException e) {
                    System.out.println("Line max reached");
                }
            }
            return pelaajat;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return pelaajat;
    }

}
