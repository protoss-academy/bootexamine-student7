package com.protosstechnology.train.bootexamine.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of={"id"})
public class Document {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String documentNumber;
    @NonNull
    private String description;

}
