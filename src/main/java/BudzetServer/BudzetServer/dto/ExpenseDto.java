package BudzetServer.BudzetServer.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class ExpenseDto {

    private Long id;
    private String name;
    private double price;
    private String type;
    private String createdAt;
}
