package br.com.aurindo.crud.controller;

import br.com.aurindo.crud.exception.CrudEntityNotFounException;
import br.com.aurindo.crud.model.Patient;
import br.com.aurindo.crud.model.resource.PatientResourceMapper;
import br.com.aurindo.crud.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
@ExposesResourceFor(Patient.class)
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientResourceMapper patientResourceMapper;

    @PostMapping
    public ResponseEntity<Patient> save(
            @RequestBody Patient patient) throws CrudEntityNotFounException {

        Patient createdPatient = patientService.save(patient);

        Patient resource = patientResourceMapper.toResource(createdPatient);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(
            @PathVariable(value="id", required = true) Long id,
            @RequestBody Patient patient) {
        try {
            patient = patientService.update(id, patient);

            Patient resource = patientResourceMapper.toResource(patient);
            return new ResponseEntity<>(resource, HttpStatus.OK);
        } catch (CrudEntityNotFounException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(
            @PathVariable(value="id", required = true) Long id) throws CrudEntityNotFounException {
        Optional<Patient> patientOptional = patientService.findById(id);
        if (patientOptional.isPresent()) {
            return ResponseEntity.ok(patientResourceMapper.toResource(patientOptional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Patient>> findAll() throws CrudEntityNotFounException {
        List<Patient> resource = patientResourceMapper.toResourceCollection(patientService.findAll());
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(
            @PathVariable(value="id", required = true) Long id) throws CrudEntityNotFounException {

        patientService.delete(id);

        return ResponseEntity.ok().build();
    }

}
