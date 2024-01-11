package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentDto {
    private String p_id ;
    private String time;
    private  String date;
    private  String amount;
    private String a_id;
}
