package businessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem implements Serializable {
    private List<MenuItem> produse;

    public CompositeProduct(){
        produse = new ArrayList<MenuItem>();
    }

    public void computeData(){
        double ratTotal = 0;
        double calTotal = 0;
        double proTotal = 0;
        double faTotal = 0;
        double sodiumTotal = 0;
        double priceTotal = 0;

        for (MenuItem m : produse){
            ratTotal += m.getRating();
            calTotal += m.getCalories();
            proTotal += m.getProtein();
            faTotal += m.getFat();
            sodiumTotal += m.getSodium();
            priceTotal += m.getPrice();
        }

        this.setCalories(calTotal);
        this.setFat(faTotal);
        this.setPrice(priceTotal);
        this.setRating(ratTotal / produse.size());
        this.setProtein(proTotal);
        this.setSodium(sodiumTotal);
    }

    public List<MenuItem> getProduse() {
        return produse;
    }

    public void setProduse(List<MenuItem> produse) {
        this.produse = produse;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
