package J6L5.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor

public class Product {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "Name should not be empty.")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30.")
    private String name;

    @Column(name = "price")
    @Min(value = 0, message = "Cost should be greater than 0.")
    private double cost;
}
