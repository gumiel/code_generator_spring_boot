package com.gumiel.code_generator.shell;

/**
 * Clase ConfigShell
 * Contiene las rutas y paquetes de los archivos a generar
 * Creado por: Henry Perez Gumiel
 * Fecha: 09/03/2025
 */
public class ConfigShell {

    private ConfigShell() {
        throw new IllegalStateException("Utility class");
    }

    public static final String BASE_NAME_PATH = "src/main/java/com/gumiel/code_generator";
    public static final String BASE_PACKAGE = "com.gumiel.code_generator";

    public static final String ENTITIES_NAME_PATH = BASE_NAME_PATH+"/entities"; // NO TIENE '/' AL FINAl
    public static final String ENTITIES_PACKAGE_SIMPLE = BASE_PACKAGE+".entities.";

    public static final String DTO_NAME_PATH = BASE_NAME_PATH+"/dtos/";
    public static final String DTO_PACKAGE = BASE_PACKAGE+".dtos;";

    public static final String FILTER_NAME_PATH = BASE_NAME_PATH+"/filters/";
    public static final String FILTER_PACKAGE = BASE_PACKAGE+".filters;";

    public static final String POJO_NAME_PATH = BASE_NAME_PATH+"/pojos/";
    public static final String POJO_PACKAGE = BASE_PACKAGE+".pojos;";

    public static final String SERVICE_NAME_PATH = BASE_NAME_PATH+"/services/";
    public static final String SERVICE_PACKAGE = BASE_PACKAGE+".services;";

    public static final String CONTROLLER_NAME_PATH = BASE_NAME_PATH+"/controllers/";
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE+".controllers;";

    public static final String MAPPER_NAME_PATH = BASE_NAME_PATH+"/mappers/";
    public static final String MAPPER_PACKAGE = BASE_PACKAGE+".mappers;";

    public static final String REPOSITORY_NAME_PATH = BASE_NAME_PATH+"/repositories/";
    public static final String REPOSITORY_PACKAGE = BASE_PACKAGE+".repositories;";

    public static final String IMPLEMENT_NAME_PATH = BASE_NAME_PATH+"/servicesImpls/";
    public static final String IMPLEMENT_PACKAGE = BASE_PACKAGE+".servicesImpls;";


}

