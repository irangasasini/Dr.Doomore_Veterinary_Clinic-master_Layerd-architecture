package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class MedicineDetailsDto {

    private String m_code;
    private String a_id;
    private int qty;

}
