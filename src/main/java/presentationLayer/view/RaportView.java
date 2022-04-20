package presentationLayer.view;

import presentationLayer.model.RaportModel;

import javax.swing.*;
import java.awt.event.ActionListener;


public class RaportView extends JFrame {
    ///Declaram, initial, toate componentele ce vor alcatui aceasta interfata
    private JTextField oraStartTextField = new JTextField();
    private JTextField oraFinalTextField = new JTextField();
    private JTextField nrComenzi1TextField = new JTextField();
    private JTextField nrComenzi2TextField = new JTextField();
    private JTextField pretTextField = new JTextField();
    private JTextField dataTextField = new JTextField();

    private JLabel oraStartLabel = new JLabel("Ora de inceput: ");
    private JLabel oraFinalLabel = new JLabel("Ora de sfarsit: ");
    private JLabel nrComenzi1Label = new JLabel("Nr. Comenzi: ");
    private JLabel nrComenzi2Label = new JLabel("Nr. Comenzi: ");
    private JLabel pretLabel = new JLabel("Pret: ");
    private JLabel dataLabel = new JLabel("Data: ");

    private JButton search1Button = new JButton("Genereaza Rap1");
    private JButton search2Button = new JButton("Genereaza Rap2");
    private JButton search3Button = new JButton("Genereaza Rap3");
    private JButton search4Button = new JButton("Genereaza Rap4");
    private JButton logOutButton = new JButton("Back");

    private JPanel panou = new JPanel();

    private RaportModel v_model;   ///pentru a lega functiile din acesta cu obiectele interfetei

    public RaportView(RaportModel model) {
        ///In constructorul view-ului trebuie initializate componentele sale si atribuite caracteristicile pe care dorim sa le aiba acestea
        v_model = model;

        this.setTitle("Raporturi"); ///titlul acesteia
        this.setSize(710, 300); ///dimensiunea ferestrei principale
        panou.setLayout(null); //pentru a nu atasa panoului Layout predefinit

        //setam pozitia tuturor etichetelor si le adaugam la panoul principal
        oraStartLabel.setBounds(20, 20, 100, 30);
        nrComenzi1Label.setBounds(20, 60, 100, 30);
        nrComenzi2Label.setBounds(20, 100, 100, 30);
        dataLabel.setBounds(20, 140, 100, 30);
        oraFinalLabel.setBounds(290, 20, 100, 30);
        pretLabel.setBounds(290, 100, 100, 30);

        panou.add(oraStartLabel);
        panou.add(nrComenzi1Label);
        panou.add(nrComenzi2Label);
        panou.add(dataLabel);
        panou.add(oraFinalLabel);
        panou.add(pretLabel);
        //setam pozitia tuturor butoanelor si le adaugam la panoul principal
        logOutButton.setBounds(250, 220, 150, 30);
        search1Button.setBounds(550, 20, 130, 30);
        search2Button.setBounds(550, 60, 130, 30);
        search3Button.setBounds(550, 100, 130, 30);
        search4Button.setBounds(550, 140, 130, 30);

        panou.add(logOutButton);
        panou.add(search1Button);
        panou.add(search2Button);
        panou.add(search3Button);
        panou.add(search4Button);
        //setam pozitia tuturor casutelor de text si le adaugam la panoul principal
        oraStartTextField.setBounds(120, 20, 150, 30);
        nrComenzi1TextField.setBounds(120, 60, 150, 30);
        nrComenzi2TextField.setBounds(120, 100, 150, 30);
        dataTextField.setBounds(120, 140, 150, 30);
        oraFinalTextField.setBounds(380, 20, 150, 30);
        pretTextField.setBounds(380, 100, 150, 30);

        panou.add(oraStartTextField);
        panou.add(dataTextField);
        panou.add(nrComenzi1TextField);
        panou.add(nrComenzi2TextField);
        panou.add(oraFinalTextField);
        panou.add(pretTextField);
        //ultimele setari legate de View
        this.setContentPane(panou); ///setarea panoului principal ca si continut al ferestrei
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); ///Inchiderea ferestrei la apasarea butonului de iesire
    }

    ///Metode ce le permite butoanelor sa interactioneze cu utilizatorul -> sa reactioneze la click
    public void addButonLogOutListener(ActionListener Event){
        logOutButton.addActionListener(Event);
    }

    public void addButonGenerareRaport1Listener(ActionListener Event){
        search1Button.addActionListener(Event);
    }

    public void addButonGenerareRaport2Listener(ActionListener Event){
        search2Button.addActionListener(Event);
    }

    public void addButonGenerareRaport3Listener(ActionListener Event){
        search3Button.addActionListener(Event);
    }

    public void addButonGenerareRaport4Listener(ActionListener Event){
        search4Button.addActionListener(Event);
    }

    //Metoda ce preiau casutele de text
    public JTextField getOraStartTextField() {
        return oraStartTextField;
    }

    public JTextField getOraFinalTextField() {
        return oraFinalTextField;
    }

    public JTextField getNrComenzi1TextField() {
        return nrComenzi1TextField;
    }

    public JTextField getNrComenzi2TextField() {
        return nrComenzi2TextField;
    }

    public JTextField getPretTextField() {
        return pretTextField;
    }

    public JTextField getDataTextField() {
        return dataTextField;
    }
}
