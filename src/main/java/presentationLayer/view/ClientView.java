package presentationLayer.view;

import presentationLayer.model.ClientModel;

import javax.swing.*;
import java.awt.event.ActionListener;

///View-ul reprezinta interfata propriu-zisa - ceea ce vede efectiv utilizatorul
public class ClientView extends JFrame {
    ///Declaram, initial, toate componentele ce vor alcatui aceasta interfata
    private JTextField titleTextField = new JTextField();
    private JTextField ratingTextField = new JTextField();
    private JTextField caloriesTextField = new JTextField();
    private JTextField proteinsTextField = new JTextField();
    private JTextField fatsTextField = new JTextField();
    private JTextField sodiumTextField = new JTextField();
    private JTextField priceTextField = new JTextField();
    private JTextField cantitateTextField = new JTextField();

    private JLabel detailsLabel = new JLabel("Produsul: ");
    private JLabel titleLabel = new JLabel("Title: ");
    private JLabel ratingLabel = new JLabel("Rating: ");
    private JLabel caloriesLabel = new JLabel("Calories: ");
    private JLabel proteinsLabel = new JLabel("Proteins: ");
    private JLabel fatsLabel = new JLabel("Fats: ");
    private JLabel sodiumLabel = new JLabel("Sodium: ");
    private JLabel priceLabel = new JLabel("Price: ");
    private JLabel cantitateLabel = new JLabel("Cantitate: ");

    private JButton logOutButton = new JButton("Log Out");
    private JButton searchButton = new JButton("Cauta Produs");
    private JButton addButton = new JButton("Adauga Produs");
    private JButton orderButton = new JButton("Comanda");
    private JButton clearButton = new JButton("Clear");

    private JPanel panou = new JPanel();
    private JScrollPane scrollPane = new JScrollPane();
    private JTable produseTabel = new JTable();

    private ClientModel v_model;   ///pentru a lega functiile din acesta cu obiectele interfetei

    public ClientView(ClientModel model) {
        ///In constructorul view-ului trebuie initializate componentele sale si atribuite caracteristicile pe care dorim sa le aiba acestea
        v_model = model;

        this.setTitle("Client"); ///titlul acesteia
        this.setSize(1000, 700); ///dimensiunea ferestrei principale
        panou.setLayout(null); //pentru a nu atasa panoului Layout predefinit

        //setam pozitia tuturor etichetelor si le adaugam la panoul principal
        detailsLabel.setBounds(20,20,100, 30);
        titleLabel.setBounds(20, 60, 70,30);
        ratingLabel.setBounds(20,100,70,30);
        caloriesLabel.setBounds(20,140,70,30);
        proteinsLabel.setBounds(260, 60, 70, 30);
        fatsLabel.setBounds(260, 100, 70, 30);
        sodiumLabel.setBounds(260, 140, 70, 30);
        priceLabel.setBounds(500, 60, 70, 30);
        cantitateLabel.setBounds(720, 60, 70,30);

        panou.add(detailsLabel);
        panou.add(titleLabel);
        panou.add(ratingLabel);
        panou.add(caloriesLabel);
        panou.add(proteinsLabel);
        panou.add(cantitateLabel);
        panou.add(priceLabel);
        panou.add(sodiumLabel);
        panou.add(fatsLabel);
        //setam pozitia tuturor butoanelor si le adaugam la panoul principal
        logOutButton.setBounds(400, 620, 150,30);
        searchButton.setBounds(540, 100, 150, 30);
        addButton.setBounds(780, 100, 150, 30);
        orderButton.setBounds(780, 140, 150, 30);
        clearButton.setBounds(540, 140, 150, 30);

        panou.add(logOutButton);
        panou.add(searchButton);
        panou.add(addButton);
        panou.add(orderButton);
        panou.add(clearButton);
        //setam pozitia tuturor casutelor de text si le adaugam la panoul principal
        titleTextField.setBounds(80, 60, 150, 30);
        ratingTextField.setBounds(80, 100, 150, 30);
        caloriesTextField.setBounds(80, 140, 150, 30);
        proteinsTextField.setBounds(330, 60, 150, 30);
        priceTextField.setBounds(540, 60, 150,30);
        cantitateTextField.setBounds(780,60, 150, 30);
        fatsTextField.setBounds(330, 100, 150, 30);
        sodiumTextField.setBounds(330, 140, 150, 30);

        panou.add(titleTextField);
        panou.add(ratingTextField);
        panou.add(caloriesTextField);
        panou.add(proteinsTextField);
        panou.add(cantitateTextField);
        panou.add(priceTextField);
        panou.add(fatsTextField);
        panou.add(sodiumTextField);
        //setam pozitia tabelul ce va afisa continutul bazei de date si scorllul acestuia si le adaugam la Frame
        scrollPane.setBounds(20, 190, 900, 400);
        produseTabel.setEnabled(true);
        produseTabel.setVisible(true);

        scrollPane.setViewportView(produseTabel);
        this.getContentPane().add(scrollPane);
        //ultimele setari legate de View
        this.setContentPane(panou); ///setarea panoului principal ca si continut al ferestrei
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); ///Inchiderea ferestrei la apasarea butonului de iesire
    }

    ///Metode ce le permite butoanelor sa interactioneze cu utilizatorul -> sa reactioneze la click
    public void addButonLogOutListener(ActionListener Event){
        logOutButton.addActionListener(Event);
    }

    public void addButonSearchListener(ActionListener Event){
        searchButton.addActionListener(Event);
    }

    public void addButonAddListener(ActionListener Event){
        addButton.addActionListener(Event);
    }

    public void addButonOrderListener(ActionListener Event){
        orderButton.addActionListener(Event);
    }

    public void addButonClearListener(ActionListener Event){
        clearButton.addActionListener(Event);
    }

    //Metoda ce preiau casutele de text
    public JTextField getTitleTextField() {
        return titleTextField;
    }

    public JTextField getRatingTextField() {
        return ratingTextField;
    }

    public JTextField getCaloriesTextField() {
        return caloriesTextField;
    }

    public JTextField getProteinsTextField() {
        return proteinsTextField;
    }

    public JTextField getFatsTextField() {
        return fatsTextField;
    }

    public JTextField getSodiumTextField() {
        return sodiumTextField;
    }

    public JTextField getPriceTextField() {
        return priceTextField;
    }

    public JTextField getCantitateTextField() {
        return cantitateTextField;
    }

    //Metode ce preiau sau seteaza valoarea JTable-ul si/sau a scrollului
    public JTable getProduseTabel() {
        return produseTabel;
    }

    public void setProduseTabel(JTable produseTabel) {
        this.produseTabel = produseTabel;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}
