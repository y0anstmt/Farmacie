package controller;

import entitate.Comanda;

import java.util.*;

public class FarmacieController {
    private final List<Comanda> comenzi = new ArrayList<>();
    private final List<Comanda> istoricComenzi = new ArrayList<>();
    private final Map<String, Integer> stock = new HashMap<>();
    private final Map<String, List<String>> keywordToMedicaments = new HashMap<>();

    public FarmacieController() {

        stock.put("Paracetamol", 100);
        stock.put("Ibuprofen", 50);
        stock.put("Aspirin", 75);
        stock.put("Amoxicillin", 30);
        stock.put("Cetirizine", 60);
        stock.put("Osteosuport", 40);
        stock.put("Reumabloc", 20);
        stock.put("Molechinimuno", 10);
        stock.put("Spirulina", 15);
        stock.put("Strepsils", 25);
        stock.put("Aspacardin", 12);
        stock.put("Aspenter", 8);
        stock.put("Cardioton", 5);


        keywordToMedicaments.put("pain", Arrays.asList("Paracetamol", "Ibuprofen, Aspirin"));
        keywordToMedicaments.put("fever", Arrays.asList("Paracetamol", "Ibuprofen, Aspirin"));
        keywordToMedicaments.put("infection", Arrays.asList("Amoxicillin, Cetirizine"));
        keywordToMedicaments.put("allergy", Arrays.asList("Cetirizine"));
        keywordToMedicaments.put("joint", Arrays.asList("Osteosuport, Reumabloc"));
        keywordToMedicaments.put("muscle", Arrays.asList("Molechinimuno"));
        keywordToMedicaments.put("immune", Arrays.asList("Spirulina"));
        keywordToMedicaments.put("throat", Arrays.asList("Strepsils"));
        keywordToMedicaments.put("cardio", Arrays.asList("Aspacardin, Aspenter, Cardioton"));
        keywordToMedicaments.put("blood", Arrays.asList("Cardioton"));
        keywordToMedicaments.put("respiratory", Arrays.asList("Cetirizine, Amoxicillin"));
    }

    public void adaugaMedicament(String medicament, int cantitate) {
        stock.put(medicament, stock.getOrDefault(medicament, 0) + cantitate);
    }

    public void adaugaComanda(Comanda comanda) {
        String medicament = comanda.getMedicament();
        int cantitate = comanda.getCantitate();

        if (!stock.containsKey(medicament)) {
            System.out.println("Error: Medication not found in stock.");
            return;
        }

        int availableStock = stock.get(medicament);
        if (cantitate > availableStock) {
            System.out.println("Error: Not enough stock available for " + medicament + ".");
            return;
        }

        stock.put(medicament, availableStock - cantitate);
        comenzi.add(comanda);
        System.out.println("Order placed successfully. Remaining stock of " + medicament + ": " + stock.get(medicament));
    }

    public List<Comanda> getComenziNeonorate() {
        return new ArrayList<>(comenzi);
    }

    public List<Comanda> getIstoricComenzi() {
        return new ArrayList<>(istoricComenzi);
    }

    public void onoreazaComanda(Comanda comanda) {
        if (comanda != null) {
            comenzi.remove(comanda);
            comanda.setOnorata(true);
            istoricComenzi.add(comanda);
            System.out.println("Order fulfilled: " + comanda.getMedicament());
        }
    }

    public Comanda findOrder(String medicament, int cantitate) {
        return comenzi.stream()
                .filter(c -> c.getMedicament().equals(medicament) && c.getCantitate() == cantitate)
                .findFirst()
                .orElse(null);
    }

    public int cautaMedicament(String medicament) {
        return stock.getOrDefault(medicament, -1);
    }

    public int generateRandomId() {
        return new Random().nextInt(10000);
    }

    public List<String> searchMedicationsByKeyword(String keyword) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : keywordToMedicaments.entrySet()) {
            if (entry.getKey().toLowerCase().contains(keyword.toLowerCase())) {
                result.addAll(entry.getValue());
            }
        }
        return result;
    }
}