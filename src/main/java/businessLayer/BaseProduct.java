package businessLayer;

import java.io.Serializable;

public class BaseProduct extends MenuItem implements Serializable {

    public BaseProduct(String[] date) {
        super(date[0],  Double.parseDouble(date[1]), Double.parseDouble(date[2]), Double.parseDouble(date[3]),
                Double.parseDouble(date[4]), Double.parseDouble(date[5]), Double.parseDouble(date[6]));
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
