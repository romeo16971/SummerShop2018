package com.shosu.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ignite.cache.query.annotations.QuerySqlField;


@Entity
@Table(name ="Employee" )
public class EmployeeDTO implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4670975277601946408L;

	@Id @GeneratedValue
    @QuerySqlField(index = true)
    @Column(name ="id")
    private Integer id;
     
    @QuerySqlField(index = true)
    @Column(name = "name")
    private String name;
    



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



    

}
