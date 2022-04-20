package businessLayer;

import dataLayer.Serializator;
import dataLayer.Utilizator;

import javax.print.attribute.standard.OutputDeviceAssigned;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {
    private static final long serialVersionUID = 95285073459003170L;

    private List<Utilizator> utilizatori = new ArrayList<>();
    private HashSet<MenuItem> meniuri = new HashSet<MenuItem>();
    private Map<Order, List<MenuItem>> comenzi = new HashMap<>();
    private Serializator serializator = new Serializator();

     @Override
    public void importProducts() {
        try{
            assert isWellFormed() : "Conditiile din invariant nu sunt pastrate";
            Stream<String> lines = Files.lines(Paths.get("products.csv"));
            Set<MenuItem> copie = lines.skip(1).map(parameter -> new BaseProduct(parameter.split(","))).collect(Collectors.toSet());
            meniuri.addAll(copie);
            assert isWellFormed() : "Conditiile din invariant nu sunt pastrate";
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void modifyProduct(MenuItem menuItem) {
        assert isWellFormed() : "Conditiile din invariant nu sunt pastrate";
         assert menuItem != null: "MenuItem-ul e null";
        meniuri.removeIf(parameter -> parameter.getTitle().contentEquals(menuItem.getTitle()));
        meniuri.add(menuItem);
        assert isWellFormed() : "Conditiile din invariant nu sunt pastrate";
    }

    @Override
    public void addProduct(MenuItem menuItem) {
        assert isWellFormed() : "Conditiile din invariant nu sunt pastrate";
        assert menuItem != null: "MenuItem-ul e null";
        meniuri.add(menuItem);
        assert isWellFormed() : "Conditiile din invariant nu sunt pastrate";
    }

    @Override
    public void deleteProduct(String title) {
        assert isWellFormed() : "Conditiile din invariant nu sunt pastrate";
        assert title != null: "Title-ul e null";
        meniuri.removeIf(parameter -> parameter.getTitle().contentEquals(title));
        assert isWellFormed() : "Conditiile din invariant nu sunt pastrate";
    }

    @Override
    public String generateRaportIntervalTimp(int oraInceput, int oraFinal) {
        List<Order> rezultat = comenzi.keySet().stream().filter(parameter ->
                (parameter.getData().getHours() >= oraInceput && parameter.getData().getHours() <= oraFinal)).collect(Collectors.toList());
        String rezultate = "";
        for(Order order : rezultat){
            rezultate += "Comanda: " + order.getOrderID() + " facuta de clientul: " + order.getClientID() +
                    " la data de: " + order.getData() + "\n";
        }
        return rezultate;
    }

    @Override
    public String generateRaportComandateDes(int nrComenzi) {
        List<List<MenuItem>> toateComenzile = comenzi.entrySet().stream().map(parameter -> parameter.getValue()).collect(Collectors.toList());
        List<MenuItem> rezultat = toateComenzile.stream().flatMap(parameter -> parameter.stream()).collect(Collectors.toList());
        Map<MenuItem, Long> rez = rezultat.stream().collect(Collectors.groupingBy(parameter -> parameter, Collectors.counting()));

        String rezultate = "";
        for (Map.Entry<MenuItem, Long> m : rez.entrySet()){
            if(m.getValue() >= nrComenzi)
                rezultate += "Produsul " + m.getKey().getTitle() + " a fost comandat de " + m.getValue() + " ori\n";
        }
        return rezultate;
    }

    @Override
    public String generateRaportStatiscClienti(int nrComenzi, double price) {
         List<Order> toateComenzile = comenzi.entrySet().stream().filter(parameter1 ->
                 (parameter1.getKey().getCostTotal() >= price)).map(parameter -> parameter.getKey()).collect(Collectors.toList());
         Map<String, Long> rezultat = toateComenzile.stream().collect(Collectors.groupingBy(parameter -> parameter.getClientID(), Collectors.counting()));

         String rezultate = "";
        for (Map.Entry<String, Long> m : rezultat.entrySet()){
            if(m.getValue() >= nrComenzi)
                rezultate += "Clientul " + m.getKey() + " a comandat de " + m.getValue() + " ori\n";
        }
         return rezultate;
    }

    @Override
    public String generateRaportDinData(Date data) {
         List<List<MenuItem>> toateComenzile = comenzi.entrySet().stream().filter(parameter1 -> (parameter1.getKey().getData().getYear() == data.getYear() &&
                 parameter1.getKey().getData().getMonth() == data.getMonth() && parameter1.getKey().getData().getDay() == data.getDay()))
                 .map(parameter -> parameter.getValue()).collect(Collectors.toList());

         List<MenuItem> rezultat = toateComenzile.stream().flatMap(parameter -> parameter.stream()).collect(Collectors.toList());
         Map<MenuItem, Long> rez = rezultat.stream().collect(Collectors.groupingBy(parameter -> parameter, Collectors.counting()));

         String rezultate = "";
         for (Map.Entry<MenuItem, Long> m : rez.entrySet()){
             rezultate += "Produsul " + m.getKey().getTitle() + " a fost comandat de " + m.getValue() + " ori\n";
         }
         return rezultate;
    }

    @Override
    public List<MenuItem> searchByWhatever(String title, double[] date) {
        List<MenuItem> rezultate;
        Set<MenuItem> intermediare = meniuri;
        //title
        if(title.compareTo("fara") != 0){
            intermediare = intermediare.stream().filter(parameter -> (parameter.getTitle().compareTo(title) == 0)).collect(Collectors.toSet());
        }
        if(date[0] >= 0){
            intermediare = intermediare.stream().filter(parameter -> (parameter.getRating() == date[0])).collect(Collectors.toSet());
        }
        if(date[1] >= 0){
            intermediare = intermediare.stream().filter(parameter -> (parameter.getCalories() == date[1])).collect(Collectors.toSet());
        }
        if(date[2] >= 0){
            intermediare = intermediare.stream().filter(parameter -> (parameter.getProtein() == date[2])).collect(Collectors.toSet());
        }
        if(date[3] >= 0){
            intermediare = intermediare.stream().filter(parameter -> (parameter.getFat() == date[3])).collect(Collectors.toSet());
        }
        if(date[4] >= 0){
            intermediare = intermediare.stream().filter(parameter -> (parameter.getSodium() == date[4])).collect(Collectors.toSet());
        }
        if(date[5] >= 0){
            intermediare = intermediare.stream().filter(parameter -> (parameter.getPrice() == date[5])).collect(Collectors.toSet());
        }

        rezultate = intermediare.stream().distinct().collect(Collectors.toList());
        return rezultate;
    }

    @Override
    public void comanda(Order order, List<MenuItem> produse) {
        assert isWellFormed() : "Conditiile din invariant nu sunt pastrate";
        assert order != null : "Nu s-a primit nicio comanda";
        assert produse != null : "Nu s-a primit niciun produs";
        comenzi.put(order, produse);
        this.setChanged();
        assert isWellFormed() : "Conditiile din invariant nu sunt pastrate";
    }

    public boolean isWellFormed() {
         //Ma asigur ca in niciun CompositeProduct nu exista alte produse inafara din cele din HashSet
        for (MenuItem menuItem : this.getMeniuri()){
            if (menuItem instanceof CompositeProduct){
                for (MenuItem m : ((CompositeProduct) menuItem).getProduse()){
                    if(!this.getMeniuri().contains(m)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public List<Utilizator> getUtilizatori() {
        return utilizatori;
    }

    public HashSet<MenuItem> getMeniuri() {
        return meniuri;
    }

    public Map<Order, List<MenuItem>> getComenzi() {
        return comenzi;
    }
}
