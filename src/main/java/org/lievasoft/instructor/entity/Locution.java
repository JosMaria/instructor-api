package org.lievasoft.instructor.entity;

import jakarta.persistence.*;
import org.lievasoft.instructor.dto.locution.LocutionCreateDto;

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

	@OneToMany(mappedBy = "locution", cascade = CascadeType.PERSIST)
	private final List<Translation> translations = new ArrayList<>();

	public Locution() {
	}

	public Locution(LocutionCreateDto locutionCreateDTO) {
		this.sentence = locutionCreateDTO.sentence();
		this.addExamples(locutionCreateDTO.examples());
		this.addTranslations(locutionCreateDTO.translations());
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

	public void addTranslations(List<String> translationsToAdd) {
		translationsToAdd.forEach(text ->{
			var translation = new Translation(text);
			this.translations.add(translation);
			translation.setLocution(this);
		});
	}
}
