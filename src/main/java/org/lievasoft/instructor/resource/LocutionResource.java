package org.lievasoft.instructor.resource;

import org.lievasoft.instructor.entity.Locution;
import org.lievasoft.instructor.service.LocutionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/locutions")
public class LocutionResource {

    private final LocutionService locutionService;

    public LocutionResource(LocutionService locutionService) {
        this.locutionService = locutionService;
    }

    @GetMapping
    public List<Locution> getLocutions() {
        return locutionService.getLocutions();
    }
}
