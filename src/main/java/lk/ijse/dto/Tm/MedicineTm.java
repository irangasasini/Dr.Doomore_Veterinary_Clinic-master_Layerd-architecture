package lk.ijse.dto.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class MedicineTm {

    private String m_code;
    private String description;
    private String unitPrice;
    private int qty;
    private  String a_id;
}


