package br.com.aurindo.crud.model.resource;

import br.com.aurindo.crud.model.Patient;
import br.com.aurindo.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientResourceMapper {

    @Autowired
    private final EntityLinks entityLinks;
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";

    public PatientResourceMapper(EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }

    public Patient toResource(Patient patient) {
        Patient resource = new Patient(
                patient.getResourceId(),
                patient.getName(),
                patient.getEmail(),
                patient.getMainPhone(),
                patient.getCategory(),
                patient.getSchedule()
        );
        final Link selfLink = entityLinks.linkToSingleResource(Patient.class, patient.getResourceId());
        resource.add(selfLink.withSelfRel());
        resource.add(selfLink.withRel(UPDATE));
        resource.add(selfLink.withRel(DELETE));
        return resource;
    }

    public List<Patient> toResourceCollection(Collection<Patient> domainObjects) {
        return domainObjects.stream()
                .map(t -> toResource(t))
                .collect(Collectors.toList());
    }

}
