package org.lievasoft.instructor.service;

import org.lievasoft.instructor.entity.Locution;
import org.lievasoft.instructor.entity.SentenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocutionService {

    public List<Locution> getLocutions() {
        var examples = List.of("We're leaving in 5 minutes, go get your stuff");
        var sentenceContext = new SentenceContext("prepararse para salir", examples);
        var generalDefinitions = List.of(
                "ve a por buscar tus cosas",
                "ve a buscar tus cosas",
                "recoge tus cosas"
        );

        var locution = new Locution("Go get your stuff", generalDefinitions, List.of(sentenceContext));
        return List.of(locution);
    }
}
