package org.lievasoft.instructor.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "locutions")
public class Locution {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String sentence;

	@OneToMany(mappedBy = "locution", cascade = CascadeType.PERSIST)
	private final List<Example> examples = new ArrayList<>();

	public Locution() {
	}

	public Locution(String sentence) {
		this.sentence = sentence;
	}

	public Long getId() {
		return id;
	}

	public String getSentence() {
		return sentence;
	}

	public void addExamples(List<String> examplesToAdd) {
		examplesToAdd.forEach(text -> {
			var example = new Example(text);
			this.examples.add(example);
			example.setLocution(this);
		});
	}
}
