package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PetDto {

    private String p_id;

    private String name;

    private String age;

    private  String breed;

    private String c_id;


}
