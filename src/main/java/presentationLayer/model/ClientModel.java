package presentationLayer.model;

import businessLayer.DeliveryService;
import businessLayer.MenuItem;
import businessLayer.Order;
import dataLayer.Serializator;
import presentationLayer.exceptii.ExceptieCasuteGoale;
import presentationLayer.exceptii.ExceptieDateIntroduseGresit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ClientModel {
    private DeliveryService deliveryService;
    private Serializator serializator;
    private String username;
    private List<MenuItem> dinComanda;
    double total = 0;
    private int nrOrders = 0;

    public ClientModel(DeliveryService deliveryService, String text) {
        //Constructorul e gol, modelul e definit doar de functiile sale
        this.deliveryService = deliveryService;
        serializator = new Serializator();
        dinComanda = new ArrayList<>();
        username = text;
    }

    public boolean verificaTextGol(String s) {
        if (s.compareTo("") == 0) {
            return true;
        }
        return false;
    }

    public List<MenuItem> cautaProduse(String[] date) throws ExceptieDateIntroduseGresit {
        String title;
        double[] valori = new double[6];

        if (verificaTextGol(date[0])) {
            title = "fara";
        } else {
            title = date[0];
        }

        for (int i = 1; i < 7; ++i) {
            if (verificaTextGol(date[i]))
                valori[i - 1] = -1;
            else {
                try {
                    valori[i - 1] = Double.parseDouble(date[i]);
                    if (valori[i - 1] < 0)
                        throw new ExceptieDateIntroduseGresit("Datele, inafara de nume, trebuie sa fie numere pozitive!");
                } catch (NumberFormatException e) {
                    throw new ExceptieDateIntroduseGresit("Datele, inafara de nume, trebuie sa fie numere pozitive!");
                }
            }
        }
            return deliveryService.searchByWhatever(title, valori);
    }

    public JTable genereazaTabel(List<MenuItem> rezultate) {
        List<String> coloane = new ArrayList<>();
        for (Field camp : MenuItem.class.getDeclaredFields()) {
            camp.setAccessible(true);
            coloane.add(camp.getName());
        }

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(coloane.toArray());

        for (Object obj : rezultate) {
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
        table.getColumnModel().getColumn(0).setPreferredWidth(300);
        return table;
    }

    public void adaugaLaComanda(String title, String cantitate) throws ExceptieCasuteGoale, ExceptieDateIntroduseGresit {
        if (verificaTextGol(cantitate) || verificaTextGol(title)){
            throw new ExceptieCasuteGoale("Casuta de titlu si cea de cantitate nu pot fi lasate goale!");
        }
        try{
            int can = Integer.parseInt(cantitate);
            if(can < 0){
                throw new ExceptieDateIntroduseGresit("Cantitatea trebuie sa fie un numar intreg pozitiv!");
            }
            List<MenuItem> filtrarea = deliveryService.getMeniuri().stream().filter(parameter -> parameter.getTitle().compareTo(title) == 0).collect(Collectors.toList());
            if(filtrarea.size() == 0){
                throw new ExceptieDateIntroduseGresit("Acest Produs nu exista!");
            }
            total += filtrarea.get(0).getPrice() * can;
            dinComanda.add(filtrarea.get(0));
        }catch (NumberFormatException e){
            throw new ExceptieDateIntroduseGresit("Cantitatea trebuie sa fie un numar intreg pozitiv!");
        }
    }

    public String gataComanda(){
        Date data = new Date();
        Order order = new Order(nrOrders, username, data, total);
        deliveryService.comanda(order, dinComanda);

        String bonul = "Clientul " + username + " a facut comanda " + nrOrders + " la data de " + data + "\n";
        for(MenuItem m : dinComanda){
            bonul += m.getTitle() + "\n";
        }
        bonul += "Total: " + total + "\n";
        nrOrders++;
        dinComanda = new ArrayList<>();
        total = 0;
        serializator.write(deliveryService);
        return bonul;
    }

    public DeliveryService getDeliveryService() {
        return deliveryService;
    }
}
