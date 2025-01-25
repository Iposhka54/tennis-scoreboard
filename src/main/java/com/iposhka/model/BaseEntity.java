package com.iposhka.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
public abstract class BaseEntity<Key extends Serializable>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key;
}
