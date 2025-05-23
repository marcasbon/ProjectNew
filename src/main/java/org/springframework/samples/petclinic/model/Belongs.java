package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "belongs")

public class Belongs extends BaseEntity {

	@CreationTimestamp
	@Column(name = "initialDate")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate initialDate;

	
	@Column(name = "finalDate")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate finalDate;

	@NotNull
	@Column(name = "isDepartmentManager")
	private Boolean isDepartmentManager;

	@ManyToOne(optional = false)
	@JoinColumn(name = "userId")
	@JsonIgnore
	//@JsonBackReference(value="user-belongs")
	UserTW userTW;

	@ManyToOne(optional = false)
	@JoinColumn(name = "departmentId")
	@JsonIgnore
	//@JsonBackReference(value="department-belongs")
	Department department;

}
