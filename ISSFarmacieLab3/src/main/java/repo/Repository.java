package repo;

import entitate.Comanda;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Repository {
    private final List<Comanda> comenzi = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public void adaugaComanda(Comanda comanda) {
        comanda.setId(idCounter.getAndIncrement());
        comenzi.add(comanda);
    }

    public List<Comanda> getComenziNeonorate() {
        List<Comanda> result = new ArrayList<>();
        for (Comanda c : comenzi) {
            if (!c.isOnorata()) {
                result.add(c);
            }
        }
        return result;
    }

    public List<Comanda> getComenziOnorate() {
        List<Comanda> result = new ArrayList<>();
        for (Comanda c : comenzi) {
            if (c.isOnorata()) {
                result.add(c);
            }
        }
        return result;
    }

    public void onoreazaComanda(int id) {
        for (Comanda c : comenzi) {
            if (c.getId() == id) {
                c.setOnorata(true);
                break;
            }
        }
    }

    public List<Comanda> getToateComenzile() {
        return new ArrayList<>(comenzi);
    }
}