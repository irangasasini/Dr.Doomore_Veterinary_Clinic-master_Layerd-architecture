package lk.ijse.dto.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AppointmentTm {
    private String a_id;
    private  String appoinment_status;
    private String date;
    private String p_id;
    private  String v_id;

}
