package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AppointmentDto {
    private String a_id;
    private String appoinment_status;
    private String date;
    private String p_id;
    private String v_id;


}
