package org.lievasoft.instructor.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "translations")
public class Translation {

	@Id
	@UuidGenerator
	private String id;

	@Column(nullable = false)
	private String text;

	@ManyToOne
	@JoinColumn(name = "locution_id", nullable = false)
	private Locution locution;

	public Translation() {
	}

	public Translation(String text) {
		this.text = text;
	}

	public void setLocution(Locution locution) {
		this.locution = locution;
	}
}
