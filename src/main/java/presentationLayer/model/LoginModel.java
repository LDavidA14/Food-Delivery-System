package presentationLayer.model;

import businessLayer.DeliveryService;
import dataLayer.Serializator;
import dataLayer.Utilizator;
import presentationLayer.exceptii.ExceptieCasuteGoale;
import presentationLayer.exceptii.ExceptieEroareLaConectare;

public class LoginModel {

    private DeliveryService deliveryService;
    private Serializator serializator;

    public LoginModel(){
        //In constructor cream deliveryService-ul de baza si preluam datele initiale, in rest Modelul e definit doar functiile sale

        serializator = new Serializator();
        deliveryService = serializator.read();
        /*deliveryService.importProducts();
        adaugaUtilizatori();
        serializator.write(deliveryService);*/
    }

    public void verifcaLogare(String text, char[] password, int tip) throws ExceptieCasuteGoale, ExceptieEroareLaConectare {
        if(text.compareTo("") == 0 || password.length == 0){
            throw new ExceptieCasuteGoale("Casutele nu pot fi lasate goale!");
        }
        boolean gasit = false;
        for(Utilizator u : deliveryService.getUtilizatori()){
            if(u.getUsername().compareTo(text) == 0 && u.getPassword().compareTo(String.valueOf(password)) == 0 &&
                    u.getTip() == tip)
                gasit = true;
        }
        if(!gasit){
            throw new ExceptieEroareLaConectare("Datele introduse sunt gresite!");
        }
    }

    public void verificaInregistrare(String text, char[] password) throws ExceptieCasuteGoale, ExceptieEroareLaConectare {
        if(text.compareTo("") == 0 || password.length == 0){
            throw new ExceptieCasuteGoale("Casutele nu pot fi lasate goale!");
        }

        boolean gasit = false;
        //username-ul trebuie sa fie diferit
        for(Utilizator u : deliveryService.getUtilizatori()){
            if(u.getUsername().compareTo(text) == 0 && u.getTip() == 2)
                gasit = true;
        }
        if(gasit){
            throw new ExceptieEroareLaConectare("Acest Client exista deja");
        }
        deliveryService.getUtilizatori().add(new Utilizator(text, String.valueOf(password), 2));
    }

    public void adaugaUtilizatori(){
        Utilizator u1 = new Utilizator("1", "1", 0);
        Utilizator u2 = new Utilizator("1", "1", 1);
        deliveryService.getUtilizatori().add(u1);
        deliveryService.getUtilizatori().add(u2);

    }

    public Serializator getSerializator() {
        return serializator;
    }

    public DeliveryService getDeliveryService() {
        return deliveryService;
    }
}
