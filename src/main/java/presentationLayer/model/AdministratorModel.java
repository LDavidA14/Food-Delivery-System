package presentationLayer.model;

import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.DeliveryService;
import businessLayer.MenuItem;
import dataLayer.Serializator;
import presentationLayer.exceptii.ExceptieCasuteGoale;
import presentationLayer.exceptii.ExceptieDateIntroduseGresit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdministratorModel {
    private DeliveryService deliveryService;
    private Serializator serializator;
    private List<MenuItem> produseActuale;

    public AdministratorModel(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
        produseActuale = new ArrayList<>();

        serializator = new Serializator();
    }

    public DeliveryService getDeliveryService() {
        return deliveryService;
    }

    public boolean verificaTextGol(String s) {
        if (s.compareTo("") == 0) {
            return true;
        }
        return false;
    }

    public void stergeBProduct(String title) throws ExceptieCasuteGoale {
        if (verificaTextGol(title))
            throw new ExceptieCasuteGoale("Casuta de titlu nu poate fi goala!");
        deliveryService.deleteProduct(title);
        serializator.write(deliveryService);
    }

    public void updateBProduct (String[] date) throws ExceptieCasuteGoale, ExceptieDateIntroduseGresit {
        for(String s : date){
            if (verificaTextGol(s)){
                throw new ExceptieCasuteGoale("Nicio casuta la update nu poate fi lasata goala!");
            }
        }

        double[] valori = new double[6];
        for(int i = 1; i < 7; ++i){
            try {
                valori[i - 1] = Double.parseDouble(date[i]);
                if (valori[i - 1] < 0)
                    throw new ExceptieDateIntroduseGresit("Toate valorile trebuie sa fie numere pozitive!");
            }catch (NumberFormatException | ExceptieDateIntroduseGresit e){
                throw new ExceptieDateIntroduseGresit("Toate valorile trebuie sa fie numere pozitive!");
            }
        }
        MenuItem produsul = new MenuItem(date[0], valori[0], valori[1], valori[2], valori[3], valori[4], valori[5]);
        deliveryService.modifyProduct(produsul);
        serializator.write(deliveryService);
    }

    public void adaugaBProduct(String[] date) throws ExceptieCasuteGoale, ExceptieDateIntroduseGresit {
        for(String s : date){
            if (verificaTextGol(s)){
                throw new ExceptieCasuteGoale("Nicio casuta la adaugara nu poate fi lasata goala!");
            }
        }

        double[] valori = new double[6];
        for(int i = 1; i < 7; ++i){
            try {
                valori[i - 1] = Double.parseDouble(date[i]);
                if (valori[i - 1] < 0)
                    throw new ExceptieDateIntroduseGresit("Toate valorile trebuie sa fie numere pozitive!");
            }catch (NumberFormatException e){
                throw new ExceptieDateIntroduseGresit("Toate valorile trebuie sa fie numere pozitive!");
            }
        }
        List<MenuItem> rez  = deliveryService.getMeniuri().stream().filter(parameter -> parameter.getTitle().compareTo(date[0]) == 0).collect(Collectors.toList());
        if (rez.size() != 0){
            throw new ExceptieDateIntroduseGresit("Acest produs exista deja!");
        }
        deliveryService.addProduct(new BaseProduct(date));
        serializator.write(deliveryService);
    }

    public JTable genereazaTabel(List<MenuItem> deAfisat) {
        List<String> coloane = new ArrayList<>();
        for (Field camp : MenuItem.class.getDeclaredFields()) {
            camp.setAccessible(true);
            coloane.add(camp.getName());
        }

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(coloane.toArray());

        for (Object obj : deAfisat) {
            List<Object> objects = new ArrayList<>();
            //preluam de la toate inregistrarile, valorile fiecarui atribut specific acestuia
            for (Field camp : MenuItem.class.getDeclaredFields()) {
                camp.setAccessible(true);
                try {
                    objects.add(camp.get(obj));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            model.addRow(objects.toArray());
        }
        //crearea propriu zisa a tabelului cu lista de coloane ca si cap de tabel si modelul pe post de continut
        JTable table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        return table;
    }

    public List<MenuItem> adaugaLaCProduct(String title) throws ExceptieCasuteGoale, ExceptieDateIntroduseGresit {
        if(verificaTextGol(title))
            throw new ExceptieCasuteGoale("Casuta de titlu al produsului nu trebuie lasata goala!");
        List<MenuItem> rez  = deliveryService.getMeniuri().stream().filter(parameter -> parameter.getTitle().compareTo(title) == 0).collect(Collectors.toList());
        if (rez.size() == 0){
            throw new ExceptieDateIntroduseGresit("Acest produs nu exista!");
        }

        produseActuale.add(rez.get(0));
        return produseActuale;
    }

    public void adaugaCProduct(String text) throws ExceptieCasuteGoale, ExceptieDateIntroduseGresit {
        if (verificaTextGol(text))
            throw new ExceptieCasuteGoale("Casuta de titlu a Composite Productului nu poate fi lasata goala!");
        CompositeProduct cp = new CompositeProduct();
        cp.setTitle(text);
        cp.getProduse().addAll(produseActuale);
        cp.computeData();

        List<MenuItem> rez  = deliveryService.getMeniuri().stream().filter(parameter -> (parameter.getTitle().compareTo(text) == 0 &&
                parameter instanceof CompositeProduct)).collect(Collectors.toList());
        if (rez.size() != 0){
            throw new ExceptieDateIntroduseGresit("Acest produs exista deja!");
        }
        deliveryService.addProduct(cp);
        produseActuale = new ArrayList<>();
        serializator.write(deliveryService);
    }

    public List<MenuItem> afiseazaProducts() {
        double[] date = new double[]{-1, -1, -1, -1, -1, -1};
        List<MenuItem> rezultat = deliveryService.searchByWhatever("fara",date);
        return rezultat;
    }

    public List<MenuItem> afiseazaCompositeProducts() {
        double[] date = new double[]{-1, -1, -1, -1, -1, -1};
        List<MenuItem> rezultat = deliveryService.searchByWhatever("fara", date);
        rezultat.removeIf(m -> !(m instanceof CompositeProduct));
        return rezultat;
    }

    public void faImport() {
        deliveryService.importProducts();
        serializator.write(deliveryService);
    }
}
