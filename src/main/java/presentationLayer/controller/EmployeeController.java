package presentationLayer.controller;

import businessLayer.DeliveryService;
import dataLayer.Serializator;
import presentationLayer.model.EmployeeModel;
import presentationLayer.view.EmployeeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeController {
    //Declararea modelului si a viewului
    private EmployeeModel model;
    private EmployeeView view;

    public EmployeeController(EmployeeModel model, EmployeeView view) {
        //In constructor ne initializam view-ul si modelul intre care face legatura controllerul
        this.model = model;
        this.view = view;

        //Totodata initializam toti ascultatorii butoanelor -> cei care stiu cand utilizatorul a facut ceva in interfata
        this.view.addButonLogOutListener(new ButonLogOutListener());

        model.getDeliveryService().addObserver(view);
    }

    //Pentru fiecare ascultator trebuie creata o clasa care sa dezvolte metoda de actionPerformed
    //Metoda in care legam ceea ce ne ofera utilizatorul prin view de ceea ce trebuie sa se execute in model si
    //sa se intample aproape simultan actualizarile pe intreaga aplicatie
    private class ButonLogOutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            view.setVisible(false);
        }
    }
}
