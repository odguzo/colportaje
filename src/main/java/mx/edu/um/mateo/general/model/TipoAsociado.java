/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.mateo.general.model;

/**
 *
 * @author gibrandemetrioo
 */
public enum TipoAsociado {

    DEPOSITOR("DEPOSITOR"),
    READER("READER"),
    AUTHOR("AUTHOR"),
    EDITOR("EDITOR"),
    ADMINISTRATOR("ADMINISTRATOR");
    private String code;

    private TipoAsociado(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
