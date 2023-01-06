package eShopCrudOperations.eShopCrudOperations.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


@Entity
@Table(name="news")
public class News {

    @Id
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;


    @Column(name="description")
    private String description;

}
