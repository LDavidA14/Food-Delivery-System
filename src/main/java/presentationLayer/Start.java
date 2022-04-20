package presentationLayer;

import businessLayer.DeliveryService;
import dataLayer.Serializator;
import presentationLayer.controller.LoginController;
import presentationLayer.model.LoginModel;
import presentationLayer.view.LoginView;


//Mainul aplicatiei, unde se leaga toate componentele MVC-ului si, ca si rezultat, avem o aplicatie functionala - Simulatorul de Cozi
public class Start {
    public static void main(String[] args) {

        LoginModel model = new LoginModel();
        LoginView view = new LoginView(model); //ceea ce vede utilizatorul si cu ce interactioneaza
        LoginController controller = new LoginController(model, view);

        view.setVisible(true); //facem interfata vizibila
    }
}
