package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString


public class Appointment {

    private String a_id;
    private String appointment_status;
    private String date;
    private String p_id;
    private String v_id;

}
