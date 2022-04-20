package presentationLayer.view;

import presentationLayer.model.AdministratorModel;

import javax.swing.*;
import java.awt.event.ActionListener;

///View-ul reprezinta interfata propriu-zisa - ceea ce vede efectiv utilizatorul
public class AdministratorView extends JFrame {
    ///Declaram, initial, toate componentele ce vor alcatui aceasta interfata
    private JTextField titleTextField = new JTextField();
    private JTextField ratingTextField = new JTextField();
    private JTextField caloriesTextField = new JTextField();
    private JTextField proteinsTextField = new JTextField();
    private JTextField fatsTextField = new JTextField();
    private JTextField sodiumTextField = new JTextField();
    private JTextField priceTextField = new JTextField();
    private JTextField compProductTextField = new JTextField();

    private JLabel titleLabel = new JLabel("Title: ");
    private JLabel ratingLabel = new JLabel("Rating: ");
    private JLabel caloriesLabel = new JLabel("Calories: ");
    private JLabel proteinsLabel = new JLabel("Proteins: ");
    private JLabel fatsLabel = new JLabel("Fats: ");
    private JLabel sodiumLabel = new JLabel("Sodium: ");
    private JLabel priceLabel = new JLabel("Price: ");
    private JLabel compProductLabel = new JLabel("Comp. Product: ");

    private JButton logOutButton = new JButton("Log Out");
    private JButton showProductsButton = new JButton("Afisare ALL Products");
    private JButton showCProductsButton = new JButton("Afisare C. Products");
    private JButton addBProductButton = new JButton("Adauga");
    private JButton updateBProductButton = new JButton("Update");
    private JButton deleteBProductButton = new JButton("Sterge");
    private JButton addCProductButton = new JButton("Adauga C. Product");
    private JButton finishCProductButton = new JButton("Create");
    private JButton clearButton = new JButton("Clear");
    private JButton importButton = new JButton("Import");
    private JButton raportsButton = new JButton("Raporturi");

    private JPanel panou = new JPanel();
    private JScrollPane scrollPaneAllP = new JScrollPane();
    private JScrollPane scrollPaneCP = new JScrollPane();

    private JTable allTabel = new JTable();
    private JTable cProductsTabel = new JTable();

    private AdministratorModel v_model;   ///pentru a lega functiile din acesta cu obiectele interfetei

    public AdministratorView(AdministratorModel model) {
        ///In constructorul view-ului trebuie initializate componentele sale si atribuite caracteristicile pe care dorim sa le aiba acestea
        v_model = model;

        this.setTitle("Administrator"); ///titlul acesteia
        this.setSize(1000, 700); ///dimensiunea ferestrei principale
        panou.setLayout(null); //pentru a nu atasa panoului Layout predefinit

        //setam pozitia tuturor etichetelor si le adaugam la panoul principal
        titleLabel.setBounds(20, 20, 70,30);
        ratingLabel.setBounds(20,60,70,30);
        caloriesLabel.setBounds(20,100,70,30);
        proteinsLabel.setBounds(20, 140, 70, 30);
        fatsLabel.setBounds(20, 180, 70, 30);
        sodiumLabel.setBounds(260, 20, 70, 30);
        priceLabel.setBounds(260, 60, 70, 30);
        compProductLabel.setBounds(550, 20, 100,30);

        panou.add(titleLabel);
        panou.add(ratingLabel);
        panou.add(caloriesLabel);
        panou.add(proteinsLabel);
        panou.add(compProductLabel);
        panou.add(priceLabel);
        panou.add(sodiumLabel);
        panou.add(fatsLabel);
        //setam pozitia tuturor butoanelor si le adaugam la panoul principal
        logOutButton.setBounds(400, 620, 150,30);
        clearButton.setBounds(820, 20, 150, 30);
        addBProductButton.setBounds(300, 100, 150, 30);
        updateBProductButton.setBounds(300,140, 150, 30);
        deleteBProductButton.setBounds(300,180, 150, 30);
        addCProductButton.setBounds(650,60, 150, 30);
        finishCProductButton.setBounds(820,60, 150, 30);
        importButton.setBounds(750, 300, 170, 30);
        showProductsButton.setBounds(750, 350, 170, 30);
        showCProductsButton.setBounds(750, 400, 170, 30);
        raportsButton.setBounds(750, 450, 170, 30);

        panou.add(logOutButton);
        panou.add(clearButton);
        panou.add(addBProductButton);
        panou.add(updateBProductButton);
        panou.add(deleteBProductButton);
        panou.add(addCProductButton);
        panou.add(finishCProductButton);
        panou.add(importButton);
        panou.add(showProductsButton);
        panou.add(showCProductsButton);
        panou.add(raportsButton);
        //setam pozitia tuturor casutelor de text si le adaugam la panoul principal
        titleTextField.setBounds(80, 20, 150, 30);
        ratingTextField.setBounds(80, 60, 150, 30);
        caloriesTextField.setBounds(80, 100, 150, 30);
        proteinsTextField.setBounds(80, 140, 150, 30);
        fatsTextField.setBounds(80, 180, 150, 30);
        sodiumTextField.setBounds(330, 20, 150, 30);
        priceTextField.setBounds(330, 60, 150,30);
        compProductTextField.setBounds(650,20, 150, 30);

        panou.add(titleTextField);
        panou.add(ratingTextField);
        panou.add(caloriesTextField);
        panou.add(proteinsTextField);
        panou.add(compProductTextField);
        panou.add(priceTextField);
        panou.add(fatsTextField);
        panou.add(sodiumTextField);
        //setam pozitia tabelul ce va afisa continutul bazei de date si scorllul acestuia si le adaugam la Frame
        scrollPaneCP.setBounds(470, 110, 500, 100);
        scrollPaneAllP.setBounds(20, 230, 700, 390);
        cProductsTabel.setEnabled(true);
        cProductsTabel.setVisible(true);
        allTabel.setVisible(true);
        allTabel.setEnabled(true);

        scrollPaneCP.setViewportView(cProductsTabel);
        scrollPaneAllP.setViewportView(allTabel);
        this.getContentPane().add(scrollPaneCP);
        this.getContentPane().add(scrollPaneAllP);
        //ultimele setari legate de View
        this.setContentPane(panou); ///setarea panoului principal ca si continut al ferestrei
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); ///Inchiderea ferestrei la apasarea butonului de iesire
    }

    ///Metode ce le permite butoanelor sa interactioneze cu utilizatorul -> sa reactioneze la click
    public void addButonLogOutListener(ActionListener Event){
        logOutButton.addActionListener(Event);
    }

    public void addButonClearListener(ActionListener Event){
        clearButton.addActionListener(Event);
    }

    public void addButonAddBProductListener(ActionListener Event){
        addBProductButton.addActionListener(Event);
    }

    public void addButonUpdateListener(ActionListener Event){
        updateBProductButton.addActionListener(Event);
    }

    public void addButonDeleteListener(ActionListener Event){
        deleteBProductButton.addActionListener(Event);
    }

    public void addButonAddCProductListener(ActionListener Event){
        addCProductButton.addActionListener(Event);
    }

    public void addButonCreateCProductListener(ActionListener Event){
        finishCProductButton.addActionListener(Event);
    }

    public void addButonImportListener(ActionListener Event){
        importButton.addActionListener(Event);
    }

    public void addButonShowProductsListener(ActionListener Event){
        showProductsButton.addActionListener(Event);
    }

    public void addButonShowCProductsListener(ActionListener Event){
        showCProductsButton.addActionListener(Event);
    }

    public void addButonOpenRaportsListener(ActionListener Event){
        raportsButton.addActionListener(Event);
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

    public JTextField getCompProductTextField() {
        return compProductTextField;
    }

    //Metode ce preiau sau seteaza valoarea JTable-ul si/sau a scrollului
    public JTable getAllTabel() {
        return allTabel;
    }

    public JTable getcProductsTabel() {
        return cProductsTabel;
    }

    public void setTitleTextField(JTextField titleTextField) {
        this.titleTextField = titleTextField;
    }

    public void setAllTabel(JTable allTabel) {
        this.allTabel = allTabel;
    }

    public void setcProductsTabel(JTable cProductsTabel) {
        this.cProductsTabel = cProductsTabel;
    }

    public JScrollPane getScrollPaneAllP() {
        return scrollPaneAllP;
    }

    public JScrollPane getScrollPaneCP() {
        return scrollPaneCP;
    }
}
