package lk.ijse.dao;

import lk.ijse.dao.custom.MedicineDetailsDAO;
import lk.ijse.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;
    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }

    public enum DAOTypes{
        APPOINTMENT,CUSTOMER,DOCTOR,EMPLOYEE,ITEMS,MEDICINE,MEDICINEDETAILS,ORDER,ORDERDETAILS,PAYMENT,PET,SALARY,SCHEDULE,SUPPLIER,USER
    }

    public SuperDAO getDAO(DAOTypes type){
        switch (type){
            case APPOINTMENT:
                return new AppoinmentDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case DOCTOR:
                return new DoctorDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case ITEMS:
                return new ItemsDAOImpl();
            case MEDICINE:
                return new MedicineDAOImpl();
            case MEDICINEDETAILS:
                return new MedicineDetailsDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDERDETAILS:
                return new OrderDetailsDAOImpl();
            case PAYMENT:
                return new PayemntDAOImpl();
            case PET:
                return new PetDAOImpl();
            case SALARY:
                return new SalaryDAOImpl();
            case SCHEDULE:
                return new ScheduleDAOImpl();
            case SUPPLIER:
                return new SuppliersDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
