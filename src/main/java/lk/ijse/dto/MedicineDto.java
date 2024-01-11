package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class MedicineDto {

    private String m_code;
    private String description;
    private String unitPrice;
    private int qty;
    private  String a_id;
}
