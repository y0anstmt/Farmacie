package entitate;

public class Comanda {
    private final int id;
    private final String medicament;
    private final int cantitate;
    private boolean onorata;

    public Comanda(int id, String medicament, int cantitate) {
        this.id = id;
        this.medicament = medicament;
        this.cantitate = cantitate;
        this.onorata = false;
    }

    public int getId() {
        return id;
    }

    public String getMedicament() {
        return medicament;
    }

    public int getCantitate() {
        return cantitate;
    }

    public boolean isOnorata() {
        return onorata;
    }

    public void setOnorata(boolean onorata) {
        this.onorata = onorata;
    }

    public void setId(int andIncrement) {
        // This method is not needed as ID is set in the constructor
        // and should not be changed afterwards.
        throw new UnsupportedOperationException("ID cannot be changed after creation.");
    }
}