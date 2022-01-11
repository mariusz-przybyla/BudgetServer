package BudzetServer.BudzetServer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "createdAt")
    private String createdAt;

    private String name;
    private double price;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;
}
