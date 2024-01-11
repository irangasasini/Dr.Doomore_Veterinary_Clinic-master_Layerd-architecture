package lk.ijse.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class Supplier {

    private String sup_id;
    private String email;
    private String address;
    private String tel_number;
    private String e_id;

}
