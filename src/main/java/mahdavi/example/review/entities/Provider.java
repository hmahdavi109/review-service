package mahdavi.example.review.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PROVIDER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Provider extends EntityBase {

    @Column(name = "NAME", nullable = false, length = 100, unique = true)
    private String name;


    @Column(name = "DESCRIPTION", length = 500)
    private String description;


}
