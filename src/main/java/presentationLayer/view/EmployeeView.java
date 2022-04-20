package presentationLayer.view;

import presentationLayer.model.EmployeeModel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

public class EmployeeView extends JFrame implements Observer {
    ///Declaram, initial, toate componentele ce vor alcatui aceasta interfata
    private JLabel timeLabel = new JLabel();

    private JButton logOutButton = new JButton("Log Out");;

    private JTextArea rezultate = new JTextArea();
    private JScrollPane scroll = new JScrollPane(rezultate);

    private JPanel panou = new JPanel();

    private EmployeeModel v_model;   ///pentru a lega functiile din acesta cu obiectele interfetei

    public EmployeeView(EmployeeModel model) {
        ///In constructorul view-ului trebuie initializate componentele sale si atribuite caracteristicile pe care dorim sa le aiba acestea
        v_model = model;
        time();

        this.setTitle("Employee"); ///titlul acesteia
        this.setSize(500, 500); ///dimensiunea ferestrei principale
        panou.setLayout(null); //pentru a nu atasa panoului Layout predefinit

        //setam pozitia tuturor etichetelor si le adaugam la panoul principal
        timeLabel.setBounds(20, 10, 300, 50);

        panou.add(timeLabel);
        //setam pozitia tuturor butoanelor si le adaugam la panoul principal
        logOutButton.setBounds(170, 420, 150, 30);

        panou.add(logOutButton);
        //setam caracteristicele zonei unde se vor afisa rezultatele si a scroll-ului si le adaugam la panoul principal
        rezultate.setEditable(false);
        scroll.setBounds(20, 80, 440, 320);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panou.add(scroll);
        //ultimele setari legate de View
        this.setContentPane(panou); ///setarea panoului principal ca si continut al ferestrei
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); ///Inchiderea ferestrei la apasarea butonului de iesire
    }

    //Metoda care face posibila afisarea continua a informatiilor legate de timp
    public void time(){
        Thread clock = new Thread()
        {
            public void run(){
                try{
                    while (true){
                        Calendar cal = new GregorianCalendar();
                        int ziua = cal.get(Calendar.DAY_OF_MONTH);
                        int luna = cal.get(Calendar.MONTH);
                        int an = cal.get(Calendar.YEAR);

                        int sec = cal.get(Calendar.SECOND);
                        int min = cal.get(Calendar.MINUTE);
                        int h = cal.get(Calendar.HOUR_OF_DAY);
                        timeLabel.setText("Data: " + ziua + "/" + luna + "/" + an + "  Ora: " + h + ":" + min + ":" + sec);
                        sleep(1000);
                    }

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        clock.start();
    }

    ///Metode ce le permite butoanelor sa interactioneze cu utilizatorul -> sa reactioneze la click
    public void addButonLogOutListener(ActionListener Event){
        logOutButton.addActionListener(Event);
    }

    //Metoda ce seteaza valoarea din zona de afisare
    public void setRezultate(String rezultate) {
        this.rezultate.setText(rezultate);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.rezultate.append((String)arg);
    }
}
