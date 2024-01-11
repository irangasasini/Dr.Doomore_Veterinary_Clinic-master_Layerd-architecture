package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SuppliersDto {
    private  String sup_id;
    private  String email;
    private  String address;
    private  String tel_number;
    private  String e_id;
}
