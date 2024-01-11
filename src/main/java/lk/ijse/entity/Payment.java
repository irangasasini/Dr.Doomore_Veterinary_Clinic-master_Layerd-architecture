package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString


public class Payment {

    private String p_id;
    private String time;
    private String date;
    private String amount;
    private String a_id;

}
