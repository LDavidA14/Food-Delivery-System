package presentationLayer.view;

import presentationLayer.model.LoginModel;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    ///Declaram, initial, toate componentele ce vor alcatui aceasta interfata
    private JTextField usernameTextField = new JTextField();
    private JPasswordField passwordTextField = new JPasswordField();

    private JLabel titluLabel = new JLabel("PAGINA PENTRU LOGARE");
    private JLabel usernameLabel = new JLabel("username: ");
    private JLabel passwordLabel = new JLabel("password: ");

    private JButton logareAdministratorButton = new JButton("Logare Administrator");
    private JButton logareClientButton = new JButton("Logare Client");
    private JButton logareEmployeeButton = new JButton("Logare Employee");
    private JButton inregistrareClientButon = new JButton("Inregistrare Client");
    private JButton exitButton  = new JButton("Exit");

    private JPanel panou = new JPanel();

    private LoginModel v_model;   ///pentru a lega functiile din acesta cu obiectele interfetei

    public LoginView(LoginModel model) {
        ///In constructorul view-ului trebuie initializate componentele sale si atribuite caracteristicile pe care dorim sa le aiba acestea
        v_model = model;

        this.setTitle("APLICATIE SISTEM DE GESTIONARE A LIVRARILOR"); ///titlul acesteia
        this.setSize(500, 500); ///dimensiunea ferestrei principale
        panou.setLayout(null); //pentru a nu atasa panoului Layout predefinit

        //setam pozitia tuturor etichetelor si le adaugam la panoul principal
        titluLabel.setBounds(180, 40, 400, 30);
        usernameLabel.setBounds(30, 80, 400, 30);
        passwordLabel.setBounds(30, 120, 400, 30);

        panou.add(titluLabel);
        panou.add(usernameLabel);
        panou.add(passwordLabel);
        //setam pozitia tuturor butoanelor si le adaugam la panoul principal
        logareAdministratorButton.setBounds(140, 190, 210, 30);
        logareClientButton.setBounds(140, 240, 210, 30);
        logareEmployeeButton.setBounds(140, 290, 210, 30);
        inregistrareClientButon.setBounds(140, 360, 210, 30);
        exitButton.setBounds(140, 400, 210, 30);

        panou.add(logareAdministratorButton);
        panou.add(logareClientButton);
        panou.add(logareEmployeeButton);
        panou.add(inregistrareClientButon);
        panou.add(exitButton);

        //setam pozitia tuturor casutelor de text si le adaugam la panoul principal
        usernameTextField.setBounds(100, 80, 300, 30);
        passwordTextField.setBounds(100,120, 300, 30);

        panou.add(usernameTextField);
        panou.add(passwordTextField);
        //ultimele setari legate de View
        this.setContentPane(panou); ///setarea panoului principal ca si continut al ferestrei
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); ///Inchiderea ferestrei la apasarea butonului de iesire
    }

    ///Metode ce le permite butoanelor sa interactioneze cu utilizatorul -> sa reactioneze la click
    public void addButonLogareAdministratorListener(ActionListener Event){
        logareAdministratorButton.addActionListener(Event);
    }

    public void addButonLogareClientListener(ActionListener Event){
        logareClientButton.addActionListener(Event);
    }

    public void addButonLogareEmployeeListener(ActionListener Event){
        logareEmployeeButton.addActionListener(Event);
    }

    public void addInregistrareClientListener(ActionListener Event){
        inregistrareClientButon.addActionListener(Event);
    }

    public void addButonExitListener(ActionListener Event){
        exitButton.addActionListener(Event);
    }

    //Metoda ce preiau casutele de text
    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public JPasswordField getPasswordTextField() {
        return passwordTextField;
    }
}
