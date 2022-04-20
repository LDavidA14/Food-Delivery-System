package dataLayer;

import businessLayer.DeliveryService;

import java.io.*;
import java.util.ArrayList;

public class Serializator implements Serializable {

    File numeFisier = new File("fisierRezultat.ser");

    public void write(DeliveryService deliveryService){
        try{

            FileOutputStream file = new FileOutputStream(numeFisier);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(deliveryService);
            out.close();
            file.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public DeliveryService read(){
        Object object = null;
        try{
            FileInputStream file = new FileInputStream(numeFisier);
            ObjectInputStream in = new ObjectInputStream(file);

            object = in.readObject();

            in.close();
            file.close();
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return (DeliveryService) object;
    }
}
