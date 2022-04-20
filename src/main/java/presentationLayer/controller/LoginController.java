package presentationLayer.controller;

import businessLayer.DeliveryService;
import dataLayer.Serializator;
import presentationLayer.exceptii.ExceptieCasuteGoale;
import presentationLayer.exceptii.ExceptieDateIntroduseGresit;
import presentationLayer.exceptii.ExceptieEroareLaConectare;
import presentationLayer.model.*;
import presentationLayer.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    //Declararea modelului si a viewului
    private LoginModel model;
    private LoginView view;

    public LoginModel getModel() {
        return model;
    }

    public LoginController(LoginModel model, LoginView view) {
        //In constructor ne initializam view-ul si modelul intre care face legatura controllerul
        this.model = model;
        this.view = view;

        //Totodata initializam toti ascultatorii butoanelor -> cei care stiu cand utilizatorul a facut ceva in interfata
        this.view.addButonExitListener(new ButonExitListener());
        this.view.addButonLogareAdministratorListener(new ButonLogareAdministratorListener());
        this.view.addButonLogareClientListener(new ButonLogareClientListener());
        this.view.addInregistrareClientListener(new ButonInregistrareClientListener());
        this.view.addButonLogareEmployeeListener(new ButonLogareEmployeeListener());
    }

    //Pentru fiecare ascultator trebuie creata o clasa care sa dezvolte metoda de actionPerformed
    //Metoda in care legam ceea ce ne ofera utilizatorul prin view de ceea ce trebuie sa se execute in model si
    //sa se intample aproape simultan actualizarile pe intreaga aplicatie
    private class ButonLogareAdministratorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            try {
                model.verifcaLogare(view.getUsernameTextField().getText(), view.getPasswordTextField().getPassword(), 0);

                //implementat pentru toate deserializare si verificat daca toate datele sunt ok
                AdministratorModel model = new AdministratorModel(getModel().getDeliveryService());
                AdministratorView view = new AdministratorView(model); //ceea ce vede utilizatorul si cu ce interactioneaza
                AdministratorController controller = new AdministratorController(model, view);

                view.setVisible(true); //facem interfata vizibila
            }catch (ExceptieCasuteGoale e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }catch (ExceptieEroareLaConectare e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "A aparut o eroare in timpiul rularii programului. Reincercati ^_^", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ButonLogareClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            try{
                model.verifcaLogare(view.getUsernameTextField().getText(), view.getPasswordTextField().getPassword(), 2);

                ClientModel model = new ClientModel(getModel().getDeliveryService(), view.getUsernameTextField().getText());
                ClientView view = new ClientView(model); //ceea ce vede utilizatorul si cu ce interactioneaza
                ClientController controller = new ClientController(model, view);

                view.setVisible(true); //facem interfata vizibila
            }catch (ExceptieCasuteGoale e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }catch (ExceptieEroareLaConectare e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "A aparut o eroare in timpiul rularii programului. Reincercati ^_^", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ButonLogareEmployeeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            try {
                model.verifcaLogare(view.getUsernameTextField().getText(), view.getPasswordTextField().getPassword(), 1);

                EmployeeModel model = new EmployeeModel(getModel().getDeliveryService());
                EmployeeView view = new EmployeeView(model); //ceea ce vede utilizatorul si cu ce interactioneaza
                EmployeeController controller = new EmployeeController(model, view);

                view.setVisible(true); //facem interfata vizibila
            }catch (ExceptieCasuteGoale e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }catch (ExceptieEroareLaConectare e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "A aparut o eroare in timpiul rularii programului. Reincercati ^_^", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ButonInregistrareClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            try{
                model.verificaInregistrare(view.getUsernameTextField().getText(), view.getPasswordTextField().getPassword());
                JOptionPane.showMessageDialog(null, "Client inregistrat cu succes", "Anunt", JOptionPane.INFORMATION_MESSAGE);
                model.getSerializator().write(model.getDeliveryService());
            }catch (ExceptieCasuteGoale e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }catch (ExceptieEroareLaConectare e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "A aparut o eroare in timpiul rularii programului. Reincercati ^_^", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ButonExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }
}
