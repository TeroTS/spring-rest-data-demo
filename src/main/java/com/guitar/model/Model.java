package com.guitar.model;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


@Data
@Entity
@NamedQuery(name="Model.findAllModelsByType", query="select m from Model m where m.modelType.name = :name")
public class Model {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String name;	
	private BigDecimal price;
	private int frets;
	
	@Column(name="WOODTYPE")
	private String woodType;
	
	@Column(name="YEARFIRSTMADE")
	private Date yearFirstMade;
	
	@ManyToOne
    @JoinColumn(name="manufacturer_id")
	private Manufacturer manufacturer;

	@ManyToOne
	@JoinColumn(name="MODELTYPE_ID")
	private ModelType modelType;

}
