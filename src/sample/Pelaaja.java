package sample;

public class Pelaaja {

    private String nimi;
    private Integer pelinumero;

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public Integer getPelinumero() {
        return pelinumero;
    }

    public void setPelinumero(Integer pelinumero) {
        this.pelinumero = pelinumero;
    }

    public Pelaaja(String nimi, Integer pelinumero) {
        this.nimi = nimi;
        this.pelinumero = pelinumero;
    }

    public Pelaaja(){}

    @Override
    public String toString() {
        return "Pelaaja: " + nimi +
                ", Pelinumero: " + pelinumero;
    }
}
