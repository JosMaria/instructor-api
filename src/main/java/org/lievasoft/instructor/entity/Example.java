package org.lievasoft.instructor.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "examples")
public class Example {

	@Id
	@UuidGenerator
	private String id;

	@ManyToOne
	@JoinColumn(name = "locution_id")
	private Locution locution;

	@Column(nullable = false)
	private String text;

	public Example() {
	}

	public Example(String text) {
		this.text = text;
	}

	public void setLocution(Locution locution) {
		this.locution = locution;
	}
}
