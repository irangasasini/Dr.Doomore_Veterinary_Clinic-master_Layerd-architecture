package lk.ijse.dto.Tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor

public class CartTm {
    String code;
    String description;
    int qty;
    int unitPrice;
    int amount;
    Button button;
}
