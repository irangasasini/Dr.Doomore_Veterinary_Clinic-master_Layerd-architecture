package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString


public class Pet {

    private String p_id;
    private String name;
    private String age;
    private String breed;
    private String c_id;

}
