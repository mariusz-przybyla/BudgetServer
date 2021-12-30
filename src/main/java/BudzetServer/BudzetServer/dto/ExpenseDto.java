package BudzetServer.BudzetServer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExpenseDto {

    private Long id;
    private String name;
    private double price;
    private String type;
}
