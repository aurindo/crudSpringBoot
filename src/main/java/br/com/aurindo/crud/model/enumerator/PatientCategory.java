package br.com.aurindo.crud.model.enumerator;


public enum PatientCategory {

    PREGNANT("pre"),
    NEW_BORN("nb"),
    CHILDREN("ch");

    private String code;

    PatientCategory(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
