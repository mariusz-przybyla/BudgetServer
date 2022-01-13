package BudzetServer.BudzetServer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpenseDto {

    private Long id;
    private String name;
    private double price;
    private String type;
    private String createdAt;
}
