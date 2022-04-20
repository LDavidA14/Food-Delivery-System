package presentationLayer.controller;

import businessLayer.DeliveryService;
import businessLayer.MenuItem;
import dataLayer.Serializator;
import presentationLayer.exceptii.ExceptieCasuteGoale;
import presentationLayer.exceptii.ExceptieDateIntroduseGresit;
import presentationLayer.model.ClientModel;
import presentationLayer.view.ClientView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.io.FileWriter;

public class ClientController {
    //Declararea modelului si a viewului
    private ClientModel model;
    private ClientView view;
    private FileWriter scriitorul;
    private int nrBon = 0;

    public ClientController(ClientModel model, ClientView view) {
        //In constructor ne initializam view-ul si modelul intre care face legatura controllerul
        this.model = model;
        this.view = view;

        //Totodata initializam toti ascultatorii butoanelor -> cei care stiu cand utilizatorul a facut ceva in interfata
        this.view.addButonAddListener(new ButonAdaugaProdusListener());
        this.view.addButonClearListener(new ButonClearListener());
        this.view.addButonLogOutListener(new ButonLogOutListener());
        this.view.addButonOrderListener(new ButonComandaListener());
        this.view.addButonSearchListener(new ButonCautaProdusListener());

        view.getCantitateTextField().setText("1");

    }

    //Pentru fiecare ascultator trebuie creata o clasa care sa dezvolte metoda de actionPerformed
    //Metoda in care legam ceea ce ne ofera utilizatorul prin view de ceea ce trebuie sa se execute in model si
    //sa se intample aproape simultan actualizarile pe intreaga aplicatie
    private class ButonCautaProdusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String[] date = new String[7];
            date[0] = view.getTitleTextField().getText();
            date[1] = view.getRatingTextField().getText();
            date[2] = view.getCaloriesTextField().getText();
            date[3] = view.getProteinsTextField().getText();
            date[4] = view.getFatsTextField().getText();
            date[5] = view.getSodiumTextField().getText();
            date[6] = view.getPriceTextField().getText();
            try{
                List<MenuItem>  rezultate = model.cautaProduse(date);
                genereazaTabel(rezultate);
            }catch (ExceptieDateIntroduseGresit e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ButonAdaugaProdusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                model.adaugaLaComanda(view.getTitleTextField().getText(), view.getCantitateTextField().getText());
                JOptionPane.showMessageDialog(null, "Produs adaugat cu succes", "Anunt", JOptionPane.INFORMATION_MESSAGE);
            } catch (ExceptieCasuteGoale e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            } catch (ExceptieDateIntroduseGresit e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ButonClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            view.getCaloriesTextField().setText("");
            view.getCantitateTextField().setText("");
            view.getFatsTextField().setText("");
            view.getRatingTextField().setText("");
            view.getProteinsTextField().setText("");
            view.getPriceTextField().setText("");
            view.getSodiumTextField().setText("");
            view.getTitleTextField().setText("");
        }
    }

    private class ButonComandaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String rezultate = model.gataComanda();
            JOptionPane.showMessageDialog(null, "Comanda creata cu succes", "Anunt", JOptionPane.INFORMATION_MESSAGE);
            try {
                scriitorul = new java.io.FileWriter("Bon" + nrBon + ".txt");
                scriitorul.append(rezultate);
                scriitorul.close();
                getModel().getDeliveryService().notifyObservers(rezultate);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private class ButonLogOutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            view.setVisible(false);
        }
    }

    private void genereazaTabel(List<MenuItem> rezultate) {
        view.setProduseTabel(model.genereazaTabel(rezultate));
        view.getScrollPane().setViewportView(view.getProduseTabel());
        view.getContentPane().add(view.getScrollPane());
    }

    public ClientModel getModel() {
        return model;
    }
}
