package sample;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//Otteluolio
public class Ottelu {

    private Date päivämäärä;
    private String vastustaja;
    private String tulos;
    private String sijainti;
    private ArrayList<Integer> pelinumerot;
    private ArrayList<Integer> maalimäärät;

    public Ottelu(Date päivämäärä, String vastustaja, String tulos, String sijainti, ArrayList<Integer> pelinumerot, ArrayList<Integer> maalimäärät) {
        this.päivämäärä = päivämäärä;
        this.vastustaja = vastustaja;
        this.tulos = tulos;
        this.sijainti = sijainti;
        this.pelinumerot = pelinumerot;
        this.maalimäärät = maalimäärät;
    }

    public Ottelu() {
        this.pelinumerot = new ArrayList<Integer>();
        this.maalimäärät = new ArrayList<Integer>();
    };

    public Date getPäivämäärä() {
        return päivämäärä;
    }

    public void setPäivämäärä(Date päivämäärä) {
        this.päivämäärä = päivämäärä;
    }

    public String getVastustaja() {
        return vastustaja;
    }

    public void setVastustaja(String vastustaja) {
        this.vastustaja = vastustaja;
    }

    public String getTulos() {
        return tulos;
    }

    public void setTulos(String tulos) {
        this.tulos = tulos;
    }

    public ArrayList<Integer> getPelinumerot() {
        return pelinumerot;
    }

    public void addPelinumero(int pelinumero) {
        this.pelinumerot.add(pelinumero);
    }

    public String getSijainti() {
        return sijainti;
    }

    public void setSijainti(String sijainti) {
        this.sijainti = sijainti;
    }

    public ArrayList<Integer> getMaalimäärät() {
        return maalimäärät;
    }

    public void addMaalimäärä(int maalimäärä) {
        this.maalimäärät.add(maalimäärä);
    }

    @Override
    public String toString() {
        return "Ottelu{" +
                "päivämäärä=" + päivämäärä +
                ", vastustaja='" + vastustaja + '\'' +
                ", tulos='" + tulos + '\'' +
                ", sijainti=" + sijainti +
                ", pelinumero=" + pelinumerot +
                ", maalimäärä=" + maalimäärät +
                '}';
    }

    //Vie ottelun tiedot tallennettavaksi sopivaan muotoon
    public String toFile() {

        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String pelipv = df.format(this.getPäivämäärä());
        String ottelutiedot = pelipv+";"+
                this.getVastustaja()+";"+
                this.getTulos()+";"+
                this.getSijainti()+";";
        StringBuilder maalit = new StringBuilder();
        for (int i=0; i<pelinumerot.size(); i++) {
            maalit.append(pelinumerot.get(i));
            maalit.append(",");
            maalit.append(maalimäärät.get(i));
            if(i+1<pelinumerot.size())
                maalit.append(":");
            else
                maalit.append("\n");
        }
        return ottelutiedot+maalit;
    }
}
