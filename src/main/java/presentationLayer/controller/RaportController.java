package presentationLayer.controller;

import businessLayer.DeliveryService;
import dataLayer.Serializator;
import presentationLayer.exceptii.ExceptieCasuteGoale;
import presentationLayer.exceptii.ExceptieDateIntroduseGresit;
import presentationLayer.model.RaportModel;
import presentationLayer.view.RaportView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.FileWriter;

public class RaportController {
    //Declararea modelului si a viewului
    private RaportModel model;
    private RaportView view;

    private FileWriter scriitorul;
    private int nrRapoarte = 0;

    public RaportController(RaportModel model, RaportView view) {
        //In constructor ne initializam view-ul si modelul intre care face legatura controllerul
        this.model = model;
        this.view = view;

        //Totodata initializam toti ascultatorii butoanelor -> cei care stiu cand utilizatorul a facut ceva in interfata
        this.view.addButonGenerareRaport1Listener(new ButonGenerareRaport1Listener());
        this.view.addButonGenerareRaport2Listener(new ButonGenerareRaport2Listener());
        this.view.addButonGenerareRaport3Listener(new ButonGenerareRaport3Listener());
        this.view.addButonGenerareRaport4Listener(new ButonGenerareRaport4Listener());
        this.view.addButonLogOutListener(new ButonLogOutListener());
    }

    //Pentru fiecare ascultator trebuie creata o clasa care sa dezvolte metoda de actionPerformed
    //Metoda in care legam ceea ce ne ofera utilizatorul prin view de ceea ce trebuie sa se execute in model si
    //sa se intample aproape simultan actualizarile pe intreaga aplicatie
    private class ButonGenerareRaport1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            try{
                String rezultate = model.generateRaport1(view.getOraStartTextField().getText(), view.getOraFinalTextField().getText());
                scriitorul =  new FileWriter("Raport1_" + nrRapoarte + ".txt");
                nrRapoarte++;
                scriitorul.append("Comenzile din intervalul orar " + view.getOraStartTextField().getText() + " - " + view.getOraFinalTextField().getText()
                + " sunt:\n");
                scriitorul.append(rezultate);
                scriitorul.close();
                JOptionPane.showMessageDialog(null, "Raport generat cu succes", "Anunt", JOptionPane.INFORMATION_MESSAGE);
            }catch (ExceptieCasuteGoale e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
            catch (ExceptieDateIntroduseGresit e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private class ButonGenerareRaport2Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try{
                String rezultate = model.generateRaport2(view.getNrComenzi1TextField().getText());
                scriitorul =  new FileWriter("Raport2_" + nrRapoarte + ".txt");
                nrRapoarte++;
                scriitorul.append("Produsele comandate mai mult de " + view.getNrComenzi1TextField().getText() + " ori sunt:\n");
                scriitorul.append(rezultate);
                scriitorul.close();
                JOptionPane.showMessageDialog(null, "Raport generat cu succes", "Anunt", JOptionPane.INFORMATION_MESSAGE);
            }catch (ExceptieCasuteGoale e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
            catch (ExceptieDateIntroduseGresit e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private class ButonGenerareRaport3Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try{
                String rezultate = model.generateRaport3(view.getNrComenzi2TextField().getText(), view.getPretTextField().getText());
                scriitorul =  new FileWriter("Raport3_" + nrRapoarte + ".txt");
                nrRapoarte++;
                scriitorul.append("Clientii care au comandat mai mult de  " + view.getNrComenzi2TextField().getText() +
                        " ori si cu o valoarea mai mare de " + view.getPretTextField().getText() + " sunt:\n");
                scriitorul.append(rezultate);
                scriitorul.close();
                JOptionPane.showMessageDialog(null, "Raport generat cu succes", "Anunt", JOptionPane.INFORMATION_MESSAGE);
            }catch (ExceptieCasuteGoale e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
            catch (ExceptieDateIntroduseGresit e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private class ButonGenerareRaport4Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try{
                String rezultate = model.generateRaport4(view.getDataTextField().getText());
                scriitorul =  new FileWriter("Raport4_" + nrRapoarte + ".txt");
                nrRapoarte++;
                scriitorul.append("Produsele comandate in data de " + view.getDataTextField().getText() + " sunt:\n");
                scriitorul.append(rezultate);
                scriitorul.close();
                JOptionPane.showMessageDialog(null, "Raport generat cu succes", "Anunt", JOptionPane.INFORMATION_MESSAGE);
            }catch (ExceptieCasuteGoale e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
            catch (ExceptieDateIntroduseGresit e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
            catch (IOException e){
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
}
