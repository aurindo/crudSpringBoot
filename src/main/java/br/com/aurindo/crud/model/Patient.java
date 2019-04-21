package br.com.aurindo.crud.model;

import br.com.aurindo.crud.model.enumerator.PatientCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Patient extends ResourceSupport {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String mainPhone;

    private String email;

    @Enumerated(EnumType.ORDINAL)
    private PatientCategory category;

    private Date schedule;

    public Patient() {}

    public Patient(Long id) {
        this.id = id;
    }

    public Patient(
            Long id,
            String name,
            String email,
            String mainPhone,
            PatientCategory category,
            Date schedule) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mainPhone = mainPhone;
        this.category = category;
        this.schedule = schedule;
    }

    @JsonProperty("id")
    public Long getResourceId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(String mainPhone) {
        this.mainPhone = mainPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PatientCategory getCategory() {
        return category;
    }

    public void setCategory(PatientCategory category) {
        this.category = category;
    }

    public Date getSchedule() {
        return schedule;
    }

    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }
}
