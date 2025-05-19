package service;

import repo.Repository;
import entitate.Comanda;
import java.util.List;

public class service {
    private final Repository repo = new Repository();

    public void adaugaComanda(Comanda comanda) {
        repo.adaugaComanda(comanda);
    }

    public List<Comanda> getToateComenzile() {
        return repo.getToateComenzile();
    }
}
