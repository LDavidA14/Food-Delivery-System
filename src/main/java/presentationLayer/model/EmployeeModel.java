package presentationLayer.model;

import businessLayer.DeliveryService;

public class EmployeeModel {

    private DeliveryService deliveryService;

    public EmployeeModel(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    public DeliveryService getDeliveryService() {
        return deliveryService;
    }
}
