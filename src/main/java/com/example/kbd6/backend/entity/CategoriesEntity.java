package com.example.kbd6.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class CategoriesEntity {

    @Id
    @SequenceGenerator(name="category_sequence", sequenceName = "category_sequence",allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE , generator = "category_sequence")
    private long id;
    private String type;
    private String activestatus;
}
