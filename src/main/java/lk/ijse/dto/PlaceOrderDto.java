package lk.ijse.dto;

import lk.ijse.dto.Tm.CartTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaceOrderDto {
    String appoinmentId;
    Date date;
    Time time;
    int amount;
    List<CartTm> cartTmList;

}
