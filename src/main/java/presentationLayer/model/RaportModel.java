package presentationLayer.model;

import businessLayer.DeliveryService;
import presentationLayer.exceptii.ExceptieCasuteGoale;
import presentationLayer.exceptii.ExceptieDateIntroduseGresit;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class RaportModel {
    private DeliveryService deliveryService;

    public RaportModel(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    public String generateRaport1(String oraInceput, String oraSfarsit) throws ExceptieCasuteGoale, ExceptieDateIntroduseGresit {
        if(oraInceput.compareTo("") == 0 || oraSfarsit.compareTo("") == 0){
            throw new ExceptieCasuteGoale("Casutele specifice liniei pentru raport, nu pot fi lasate goale!");
        }
        try{
            int oI = Integer.parseInt(oraInceput);
            int oF = Integer.parseInt(oraSfarsit);
            if (oI <= 0 || oF <= 0){
                throw new ExceptieDateIntroduseGresit("Orele trebuie sa fie numere intregi pozitive!");
            }
            if (oI > oF){
                throw  new ExceptieDateIntroduseGresit("Ora de inceput trebuie sa fie mai mica decat cea de sfarsit!");
            }

            return deliveryService.generateRaportIntervalTimp(oI, oF);
        }catch (NumberFormatException e){
            throw new ExceptieDateIntroduseGresit("Orele trebuie sa fie numere intregi pozitive!");
        }
    }

    public String generateRaport2(String nrComenzi) throws ExceptieCasuteGoale, ExceptieDateIntroduseGresit{
        if(nrComenzi.compareTo("") == 0)
            throw new ExceptieCasuteGoale("Casutele specifice liniei pentru raport, nu pot fi lasate goale!");
        try{
            int nr = Integer.parseInt(nrComenzi);
            if (nr < 0){
                throw new ExceptieDateIntroduseGresit("Numarul de comenzi trebuie sa fie numar intreg pozitiv!");
            }
            return deliveryService.generateRaportComandateDes(nr);
        }catch (NumberFormatException e){
            throw new ExceptieDateIntroduseGresit("Numarul de comenzi trebuie sa fie numar intreg pozitiv!");
        }
    }

    public String generateRaport3(String nrComenzi, String pret) throws ExceptieCasuteGoale, ExceptieDateIntroduseGresit{
        if (nrComenzi.compareTo("") == 0 || pret.compareTo("") == 0)
            throw new ExceptieCasuteGoale("Casutele specifice liniei pentru raport, nu pot fi lasate goale!");
        try {
            int nr = Integer.parseInt(nrComenzi);
            double pr = Double.parseDouble(pret);
            if (nr < 0 || pr < 0){
                throw new ExceptieDateIntroduseGresit("Nr de comenzi si pretul trebuie sa fie numere pozitive!");
            }
            return deliveryService.generateRaportStatiscClienti(nr, pr);
        }catch (NumberFormatException e){
            throw new ExceptieDateIntroduseGresit("Nr de comenzi si pretul trebuie sa fie numere pozitive!");
        }
    }

    public String generateRaport4(String data) throws ExceptieCasuteGoale, ExceptieDateIntroduseGresit {
        if (data.compareTo("") == 0)
            throw new ExceptieCasuteGoale("Casutele specifice liniei pentru raport, nu pot fi lasate goale!");
        try{
            Date dataPrimita = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            System.out.println(dataPrimita);
            return deliveryService.generateRaportDinData(dataPrimita);
        }catch (ParseException | IllegalArgumentException e){
            throw new ExceptieDateIntroduseGresit("Data trebuie trecuta: zz/ll/aaaa");
        }
    }
}
