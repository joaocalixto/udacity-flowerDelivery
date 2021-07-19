package com.udacity.flowerDelivery.repository.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.flowerDelivery.controller.Views;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.swing.text.StyledEditorKit;
import java.time.LocalDateTime;
import java.util.List;

@NamedQueries({
        @NamedQuery(name ="Delivery.findByName",
                query = "select d from Delivery d where d.name = :name")
})
@Entity
@Getter
@Setter
public class Delivery {


    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    private String name;
    @Column(name = "address_full", length = 500)
    private String address;
    private LocalDateTime deliveryTime;
    @Type(type = "yes_no")
    private Boolean completed = Boolean.FALSE;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Plant> plants;
}
