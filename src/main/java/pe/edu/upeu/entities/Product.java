package pe.edu.upeu.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Getter
    @Setter
    private String id;
    @NotBlank @NotNull
    @Getter @Setter
    private String name;
    @NotNull @DecimalMin(value = "0.1")
    @Getter @Setter
    private Double price;
    @NotBlank @NotNull
    @Getter @Setter
    private String description;
    @NotBlank @NotNull
    @Getter @Setter
    private String category;
    @NotBlank @NotNull
    @Getter @Setter
    private String image;
}
