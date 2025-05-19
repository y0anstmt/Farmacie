package controller;

import entitate.Comanda;
import service.service;
import java.util.List;

public class SectieController {
    private final service service = new service();

    public void trimiteComanda(String medicament, int cantitate) {
        Comanda comanda = new Comanda(0, medicament, cantitate);
        service.adaugaComanda(comanda);
    }

    public List<Comanda> getIstoricComenzi() {
        return service.getToateComenzile();
    }
}