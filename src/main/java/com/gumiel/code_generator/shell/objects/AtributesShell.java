package com.gumiel.code_generator.shell.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Clase AtributesShell
 * Contiene los atributos de una clase
 * Creado por: Henry Perez Gumiel
 * Fecha: 09/03/2025
 */
@NoArgsConstructor
public class AtributesShell {
    String nameAtributes;
    String typeAtributes;
    String importAtributes;
    List<String> anottationList;

    public List<String> getAnottationList() {
        return anottationList;
    }

    public void setAnottationList(List<String> anottationList) {
        this.anottationList = anottationList;
    }

    public String getImportAtributes() {
        return importAtributes;
    }

    public void setImportAtributes(String importAtributes) {
        this.importAtributes = importAtributes;
    }

    public String getTypeAtributes() {
        return typeAtributes;
    }

    public void setTypeAtributes(String typeAtributes) {
        this.typeAtributes = typeAtributes;
    }

    public String getNameAtributes() {
        return nameAtributes;
    }

    public void setNameAtributes(String nameAtributes) {
        this.nameAtributes = nameAtributes;
    }
}
