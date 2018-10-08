package com.guitar.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;


@Data
@Entity
public class Manufacturer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String name;

	@Column(name="FOUNDEDDATE")
	private Date foundedDate;

	@Column(name="AVERAGEYEARLYSALES")
	private BigDecimal averageYearlySales;
	private Boolean active;
	
	@OneToMany(mappedBy = "manufacturer", cascade=CascadeType.ALL)
	private List<Model> models = new ArrayList<Model>();

	@ManyToOne
	@JoinColumn(name="LOCATION_ID")
	private Location headquarters;

}
