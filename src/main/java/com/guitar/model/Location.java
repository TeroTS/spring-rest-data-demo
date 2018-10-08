package com.guitar.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Data
@Entity
public class Location {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String state;
	private String country;

	@OneToMany(mappedBy = "headquarters", cascade=CascadeType.ALL)
	private List<Manufacturer> manufacturers = new ArrayList<Manufacturer>();

}
