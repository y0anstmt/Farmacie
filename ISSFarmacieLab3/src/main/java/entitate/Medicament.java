package entitate;

public class Medicament {
    private String nume;

    public Medicament(String nume){
        this.nume=nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNume(){
        return nume;
    }
}
