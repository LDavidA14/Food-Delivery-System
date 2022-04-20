package presentationLayer.controller;

import businessLayer.DeliveryService;
import businessLayer.MenuItem;
import presentationLayer.exceptii.ExceptieCasuteGoale;
import presentationLayer.exceptii.ExceptieDateIntroduseGresit;
import presentationLayer.model.AdministratorModel;
import presentationLayer.model.RaportModel;
import presentationLayer.view.AdministratorView;
import presentationLayer.view.RaportView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdministratorController {
    //Declararea modelului si a viewului
    private AdministratorModel model;
    private AdministratorView view;

    public AdministratorModel getModel() {
        return model;
    }

    public AdministratorController(AdministratorModel model, AdministratorView view) {
        //In constructor ne initializam view-ul si modelul intre care face legatura controllerul
        this.model = model;
        this.view = view;

        //Totodata initializam toti ascultatorii butoanelor -> cei care stiu cand utilizatorul a facut ceva in interfata
        this.view.addButonClearListener(new ButonClearListener());
        this.view.addButonLogOutListener(new ButonLogOutListener());
        this.view.addButonAddBProductListener(new ButonAddBProductListener());
        this.view.addButonUpdateListener(new ButonUpdateListener());
        this.view.addButonDeleteListener(new ButonDeleteListener());
        this.view.addButonAddCProductListener(new ButonAddCProductListener());
        this.view.addButonCreateCProductListener(new ButonCreateCProductListener());
        this.view.addButonImportListener(new ButonImportListener());
        this.view.addButonShowProductsListener(new ButonShowProductsListener());
        this.view.addButonShowCProductsListener(new ButonShowCProductsListener());
        this.view.addButonOpenRaportsListener(new ButonOpenRaportsListener());
    }

    //Pentru fiecare ascultator trebuie creata o clasa care sa dezvolte metoda de actionPerformed
    //Metoda in care legam ceea ce ne ofera utilizatorul prin view de ceea ce trebuie sa se execute in model si
    //sa se intample aproape simultan actualizarile pe intreaga aplicatie
    private class ButonAddBProductListener implements ActionListener {
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
                model.adaugaBProduct(date);
                JOptionPane.showMessageDialog(null, "Produs adaugat cu succes", "Anunt", JOptionPane.INFORMATION_MESSAGE);
            }catch (ExceptieCasuteGoale e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }catch (ExceptieDateIntroduseGresit e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private class ButonUpdateListener implements ActionListener {
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
                model.updateBProduct(date);
                JOptionPane.showMessageDialog(null, "Produs actualizat cu succes", "Anunt", JOptionPane.INFORMATION_MESSAGE);
            }catch (ExceptieCasuteGoale e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }catch (ExceptieDateIntroduseGresit e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private class ButonDeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try{
                model.stergeBProduct(view.getTitleTextField().getText());
                JOptionPane.showMessageDialog(null, "Produs sters cu succes", "Anunt", JOptionPane.INFORMATION_MESSAGE);
            }catch (ExceptieCasuteGoale e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private class ButonAddCProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            List<MenuItem> deAfisat = null;
            try {
                deAfisat = model.adaugaLaCProduct(view.getTitleTextField().getText());
                generareListaCProducts(deAfisat);
                JOptionPane.showMessageDialog(null, "Produs adaugat cu succes", "Anunt", JOptionPane.INFORMATION_MESSAGE);
            } catch (ExceptieCasuteGoale e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            } catch (ExceptieDateIntroduseGresit e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void generareListaCProducts(List<MenuItem> deAfisat) {
        view.setcProductsTabel(model.genereazaTabel(deAfisat));
        view.getScrollPaneCP().setViewportView(view.getcProductsTabel());
        view.getContentPane().add(view.getScrollPaneCP());
    }

    private class ButonCreateCProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                model.adaugaCProduct(view.getCompProductTextField().getText());
                JOptionPane.showMessageDialog(null, "Produs creat cu succes", "Anunt", JOptionPane.INFORMATION_MESSAGE);
                view.setcProductsTabel(new JTable());
                view.getScrollPaneCP().setViewportView(view.getcProductsTabel());
                view.getContentPane().add(view.getcProductsTabel());
            } catch (ExceptieCasuteGoale | ExceptieDateIntroduseGresit e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ButonImportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            model.faImport();
        }
    }

    private class ButonShowProductsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            List<MenuItem> rezulate = model.afiseazaProducts();
            view.setAllTabel(model.genereazaTabel(rezulate));
            view.getScrollPaneAllP().setViewportView(view.getAllTabel());
            view.getContentPane().add(view.getScrollPaneAllP());
        }
    }

    private class ButonShowCProductsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            List<MenuItem> rezulate = model.afiseazaCompositeProducts();
            view.setAllTabel(model.genereazaTabel(rezulate));
            view.getScrollPaneAllP().setViewportView(view.getAllTabel());
            view.getContentPane().add(view.getScrollPaneAllP());
        }
    }

    private class ButonOpenRaportsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            RaportModel model = new RaportModel(getModel().getDeliveryService());
            RaportView view = new RaportView(model);
            RaportController controller = new RaportController(model, view);

            view.setVisible(true);
        }
    }

    private class ButonClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            view.getCaloriesTextField().setText("");
            view.getCompProductTextField().setText("");
            view.getFatsTextField().setText("");
            view.getRatingTextField().setText("");
            view.getProteinsTextField().setText("");
            view.getPriceTextField().setText("");
            view.getSodiumTextField().setText("");
            view.getTitleTextField().setText("");
        }
    }

    private class ButonLogOutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            view.setVisible(false);
        }
    }

}
