package businessLayer;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface IDeliveryServiceProcessing {

    public void importProducts();
    public void modifyProduct(MenuItem menuItem);
    public void addProduct(MenuItem menuItem);
    public void deleteProduct(String title);
    public String generateRaportIntervalTimp(int oraInceput, int oraFinal);
    public String generateRaportComandateDes(int nrComenzi);
    public String generateRaportStatiscClienti(int nrComenzi, double price);
    public String generateRaportDinData(Date data);
    public List<MenuItem> searchByWhatever(String title, double[] date);
    public void comanda(Order order, List<MenuItem> produse);
}
