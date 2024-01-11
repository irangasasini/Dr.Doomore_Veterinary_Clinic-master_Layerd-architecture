package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBOFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        APPOINTMENT, CUSTOMER, DOCTOR, EMPLOYEE, MEDICINE, MEDICINEDETAILS, ORDER, PAYMENT, PET, PLACEORDER, PRODUCT, SALARY, SCHEDULE, SUPPLIER, USER
    }

    public SuperBO getBO(BOTypes type) {
        switch (type) {
            case APPOINTMENT:
                return new AppointmentBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case DOCTOR:
                return new DocterBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case MEDICINE:
                return new MedicineBOImpl();
            case MEDICINEDETAILS:
                return new MedicineDetailBOImpl();
            case ORDER:
                return new OrdersBOImpl();
            case PAYMENT:
                return new PaymentsBOImpl();
            case PET:
                return new PetsBOImpl();
            case PLACEORDER:
                return new PlaceOrderBOImpl();
            case PRODUCT:
                return new ProductBOImpl();
            case SALARY:
                return new SalaryBOImpl();
            case SCHEDULE:
                return new SheduleBOImpl();
            case SUPPLIER:
                return new SuppliersBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
