package ru.itis.oris.oris_spring.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "test", schema = "oris")
public class TestEntity extends OrisBaseEntity {
}
