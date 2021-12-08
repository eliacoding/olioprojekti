package sample;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;

public class Main {



    PelaajaHandler ph;

    public static void main(String[] args) throws FileNotFoundException, ParseException {

        PelaajaHandler ph = new PelaajaHandler();

        ArrayList<Pelaaja> pelaajat = ph.haePelaajat();


        new MyFrame() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
    }
}
