package lk.ijse.dto.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentTm {

    private String p_id ;
    private String time;
    private  String date;
    private  String amount;
    private String a_id;
}

