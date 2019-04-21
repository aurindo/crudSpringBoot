package br.com.aurindo.crud.service;

import br.com.aurindo.crud.exception.CrudEntityNotFounException;
import br.com.aurindo.crud.model.Patient;
import br.com.aurindo.crud.model.User;
import br.com.aurindo.crud.repository.PatientRepository;
import br.com.aurindo.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        patientRepository.findAll().forEach(patients::add);
        return patients;
    }

    public void delete(Long id) {
        patientRepository.delete(new Patient(id));
    }

    public Patient update(Long id, Patient patient) throws CrudEntityNotFounException {
        Optional<Patient> userOptional = findById(id);

        if (userOptional.isPresent()) {
            patient.setId(id);
            return patientRepository.save(patient);
        }

        throw new CrudEntityNotFounException(User.class, patient.getResourceId());
    }
}
