package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString


public class Medicine {

    private String m_code;
    private String description;
    private String unitPrice;
    private int qty;
    private String a_id;

}
