package br.com.aurindo.crud.exception;

public class CrudEntityNotFounException extends Exception {

    private Class clazz;
    private Long id;

    public CrudEntityNotFounException(Class clazz, Long id) {
        this.clazz = clazz;
        this.id = id;
    }
}
