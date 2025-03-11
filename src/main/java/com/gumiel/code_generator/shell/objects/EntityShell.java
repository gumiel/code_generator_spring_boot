package com.gumiel.code_generator.shell.objects;

import com.gumiel.code_generator.shell.commons.UtilShell;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Clase EntityShell
 * Contiene los atributos para generar una entidad
 * Creado por: Henry Perez Gumiel
 * Fecha: 09/03/2025
 */

@NoArgsConstructor
public class EntityShell {
    private String className; // nombre de clase con todos los paquetes asociados
    private String nameEntity;
    private String nameEntityLC;
    private List<AtributesShell> atributesShellList;

    public EntityShell(String nameEntity) {
        this.nameEntity = nameEntity;
        this.nameEntityLC = UtilShell.getFirstLetterLowerCase(this.nameEntity);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getNameEntity() {
        return nameEntity;
    }

    public void setNameEntity(String nameEntity) {
        this.nameEntity = nameEntity;
    }

    public String getNameEntityLC() {
        return nameEntityLC;
    }

    public void setNameEntityLC(String nameEntityLC) {
        this.nameEntityLC = nameEntityLC;
    }

    public List<AtributesShell> getAtributesShellList() {
        return atributesShellList;
    }

    public void setAtributesShellList(List<AtributesShell> atributesShellList) {
        this.atributesShellList = atributesShellList;
    }
}
