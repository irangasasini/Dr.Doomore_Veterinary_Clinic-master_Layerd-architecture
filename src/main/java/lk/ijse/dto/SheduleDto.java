package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SheduleDto {

    private  String sh_id;
    private String date;
    private String time;
    private String v_id;
}
