package BudzetServer.BudzetServer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddExpenseDto {

    private String name;
    private Double price;
    private Long categoryId;
}
