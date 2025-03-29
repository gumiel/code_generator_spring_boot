package com.gumiel.code_generator.shell.objects;

import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Clase AtributesShell
 * Contiene los atributos de una clase
 * Creado por: Henry Perez Gumiel
 * Fecha: 09/03/2025
 */
@NoArgsConstructor
public class AtributesShell {
    String nameAttributes;
    String typeAttributes;
    String importAttributes;
    List<String> anottationList;

    public List<String> getAnottationList() {
        return anottationList;
    }

    public void setAnottationList(List<String> anottationList) {
        this.anottationList = anottationList;
    }

    public String getImportAttributes() {
        return importAttributes;
    }

    public void setImportAttributes(String importAttributes) {
        this.importAttributes = importAttributes;
    }

    public String getTypeAttributes() {
        return typeAttributes;
    }

    public void setTypeAttributes(String typeAttributes) {
        this.typeAttributes = typeAttributes;
    }

    public String getNameAttributes() {
        return nameAttributes;
    }

    public void setNameAttributes(String nameAttributes) {
        this.nameAttributes = nameAttributes;
    }
}
