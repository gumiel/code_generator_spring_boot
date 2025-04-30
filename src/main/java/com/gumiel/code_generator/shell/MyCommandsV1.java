package com.gumiel.code_generator.shell;

import com.gumiel.code_generator.shell.commons.UtilShell;
import com.gumiel.code_generator.shell.functional.*;
import com.gumiel.code_generator.shell.objects.*;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@ShellComponent
public class MyCommandsV1 {

    @ShellMethod(key = "list")
    public List<String> listEntitiesClasses() throws ClassNotFoundException {
        ParamsV1 pv1 = new ParamsV1();
        return UtilShell.listClassesInPackage(pv1.getEntityPathName());
    }

    @ShellMethod(key = "all")
    public String generateDtoV1(@ShellOption String className, String param) throws ClassNotFoundException {

        ParamsV1 pv1 = new ParamsV1();

        Class<?> clazz = null;
        if (!className.contains(".")) {
            System.out.println(pv1.getEntityPackageName()+className);
            clazz = Class.forName(pv1.getEntityPackageName()+className);
        }else{
            clazz = Class.forName(className);
        }

        StringBuilder dtoBuilder = new StringBuilder();

        EntityShell entityShell = new EntityShell(clazz.getSimpleName());// nombre de la clase entidad
        entityShell.setClassName(className); // nombre de la clase con todos los paquetes asociados

        List<AtributesShell> atributesShellList = new ArrayList<>();
        for (var field : clazz.getDeclaredFields()) {
            AtributesShell atributesShell = new AtributesShell();
            List<String> anottationList = new ArrayList<>();
            for (var annotation : field.getAnnotations()) {
                String anottationString = annotation.toString()+"\n";
                anottationList.add(anottationString);
            }
            atributesShell.setAnottationList(anottationList);
            atributesShell.setTypeAttributes(field.getType().getSimpleName());
            atributesShell.setNameAttributes(field.getName());
            atributesShellList.add(atributesShell);
        }
        entityShell.setAtributesShellList(atributesShellList);


        DtoShell dtoShell = new DtoShell(entityShell);
        FilterShell filterShell = new FilterShell(entityShell);
        PojoShell pojoShell = new PojoShell(entityShell);

        ServiceShell serviceShell = new ServiceShell(entityShell, dtoShell, filterShell, pojoShell);
        ControllerShell controllerShell = new ControllerShell(entityShell, dtoShell, filterShell, serviceShell, pojoShell);
        MapperShell mapperShell = new MapperShell(entityShell, dtoShell, pojoShell);
        RepositoryShell repositoryShell = new RepositoryShell(entityShell, dtoShell, filterShell);
        ImplementShell implementShell = new ImplementShell(entityShell, dtoShell, filterShell, pojoShell, mapperShell, serviceShell, repositoryShell);

        if(param==null){

            UtilShell.createFile(entityShell.getNameEntity()+"Dto_bk", dtoShell.generateDto().toString(), pv1.getDtoPathName() /*Parameter.DTO_NAME_PATH*/);
            UtilShell.createFile(entityShell.getNameEntity()+"Filter_bk", filterShell.generateDto().toString(), pv1.getFilterPathName());
            UtilShell.createFile(entityShell.getNameEntity()+"Pojo_bk", pojoShell.generateDto().toString(), pv1.getPojoPathName());
            UtilShell.createFile(entityShell.getNameEntity()+"Service_bk", serviceShell.generateStringService().toString(), pv1.getServicePathName());
            UtilShell.createFile(entityShell.getNameEntity()+"Controller_bk", controllerShell.generateStringController().toString(), pv1.getControllerPathName());
            UtilShell.createFile(entityShell.getNameEntity()+"Mapper_bk", mapperShell.generateStringMapper().toString(), pv1.getMapperPathName());
            UtilShell.createFile(entityShell.getNameEntity()+"Repository_bk", repositoryShell.generateStringRepository().toString(), pv1.getRepositoryPathName());
            UtilShell.createFile(entityShell.getNameEntity()+"ServiceImpl_bk", implementShell.generateStringImplement().toString(), pv1.getImplementPathName());
        }else if(param.equals("DTO")){
            UtilShell.createFile(entityShell.getNameEntity()+"Dto_bk", dtoShell.generateDto().toString(), pv1.getDtoPathName()/*Parameter.DTO_NAME_PATH*/);
        } else if (param.equals("FILTER")) {
            UtilShell.createFile(entityShell.getNameEntity()+"Filter_bk", filterShell.generateDto().toString(), pv1.getFilterPathName());
        } else if (param.equals("POJO")) {
            UtilShell.createFile(entityShell.getNameEntity()+"Pojo_bk", pojoShell.generateDto().toString(), pv1.getPojoPathName());
        } else if (param.equals("SERVICE")) {
            UtilShell.createFile(entityShell.getNameEntity()+"Service_bk", serviceShell.generateStringService().toString(), pv1.getServicePathName());
        } else if (param.equals("CONTROLLER")) {
            UtilShell.createFile(entityShell.getNameEntity()+"Controller_bk", controllerShell.generateStringController().toString(), pv1.getControllerPathName());
        } else if (param.equals("MAPPER")) {
            UtilShell.createFile(entityShell.getNameEntity()+"Mapper_bk", mapperShell.generateStringMapper().toString(), pv1.getMapperPathName());
        } else if (param.equals("REPOSITORY")) {
            UtilShell.createFile(entityShell.getNameEntity()+"Repository_bk", repositoryShell.generateStringRepository().toString(), pv1.getRepositoryPathName());
        } else if (param.equals("IMPLEMENT")) {
            UtilShell.createFile(entityShell.getNameEntity()+"ServiceImpl_bk", implementShell.generateStringImplement().toString(), pv1.getImplementPathName());
        }

        return dtoBuilder.toString();

    }

    @ShellMethod(key = "dto")
    public String generateDtoV1(@ShellOption String className) throws ClassNotFoundException {
        return generateDtoV1(className, "DTO");
    }

    @ShellMethod(key = "filter")
    public String generateFilterV1(@ShellOption String className) throws ClassNotFoundException {
        return generateDtoV1(className, "FILTER");
    }

    @ShellMethod(key = "pojo")
    public String generatePojoV1(@ShellOption String className) throws ClassNotFoundException {
        return generateDtoV1(className, "POJO");
    }

    @ShellMethod(key = "service")
    public String generateServiceV1(@ShellOption String className) throws ClassNotFoundException {
        return generateDtoV1(className, "SERVICE");
    }

    @ShellMethod(key = "controller")
    public String generateControllerV1(@ShellOption String className) throws ClassNotFoundException {
        return generateDtoV1(className, "CONTROLLER");
    }

    @ShellMethod(key = "mapper")
    public String generateMapperV1(@ShellOption String className) throws ClassNotFoundException {
        return generateDtoV1(className, "MAPPER");
    }

    @ShellMethod(key = "repository")
    public String generateRepositoryV1(@ShellOption String className) throws ClassNotFoundException {
        return generateDtoV1(className, "REPOSITORY");
    }

    @ShellMethod(key = "implement")
    public String generateImplementV1(@ShellOption String className) throws ClassNotFoundException {
        return generateDtoV1(className, "IMPLEMENT");
    }

    @ShellMethod(key = "remove")
    public String removeEntityFile(@ShellOption String className) throws IOException {
        ParamsV1 pv1 = new ParamsV1();

        String filePath = pv1.getDtoPathName() + className + "Dto_bk.java";
        Files.deleteIfExists(Paths.get(filePath));
        filePath = pv1.getFilterPathName() + className + "Filter_bk.java";
        Files.deleteIfExists(Paths.get(filePath));
        filePath = pv1.getPojoPathName() + className + "Pojo_bk.java";
        Files.deleteIfExists(Paths.get(filePath));
        filePath = pv1.getServicePathName() + className + "Service_bk.java";
        Files.deleteIfExists(Paths.get(filePath));
        filePath = pv1.getControllerPathName() + className + "Controller_bk.java";
        Files.deleteIfExists(Paths.get(filePath));
        filePath = pv1.getMapperPathName() + className + "Mapper_bk.java";
        Files.deleteIfExists(Paths.get(filePath));
        filePath = pv1.getRepositoryPathName() + className + "Repository_bk.java";
        Files.deleteIfExists(Paths.get(filePath));
        filePath = pv1.getImplementPathName() + className + "ServiceImpl_bk.java";
        Files.deleteIfExists(Paths.get(filePath));

        return "File " + className + " deleted successfully.";
    }

    @ShellMethod(key = "files")
    public void createFileEntityFile() throws IOException {
        crearCarpetasIO(ParamsV1.ENTITIES_PATH_NAME);
        crearCarpetasIO(ParamsV1.POJO_PATH_NAME);
        crearCarpetasIO(ParamsV1.DTO_PATH_NAME);
        crearCarpetasIO(ParamsV1.FILTER_PATH_NAME);
        crearCarpetasIO(ParamsV1.SERVICE_PATH_NAME);
        crearCarpetasIO(ParamsV1.CONTROLLER_PATH_NAME);
        crearCarpetasIO(ParamsV1.MAPPER_PATH_NAME);
        crearCarpetasIO(ParamsV1.REPOSITORY_PATH_NAME);
        crearCarpetasIO(ParamsV1.IMPLEMENT_PATH_NAME);
        crearCarpetasIO(ParamsV1.REPOSITORY_PATH_NAME);
    }

    public void crearCarpetasIO(String file) {
        // Crear una carpeta en el directorio del proyecto
        File carpeta = new File("."+ParamsV1.BASE_NAME_PATH+"/"+file);

        if(!carpeta.exists()) {
            boolean creado = carpeta.mkdirs(); // mkdirs() para múltiples niveles
            if (creado) {
                System.out.println("Directorio creado: " + carpeta.getAbsolutePath());
            } else {
                System.out.println("No se pudo crear el directorio");
            }
        } else {
            System.out.println("El directorio"+ carpeta.getAbsolutePath()+" ya existe");
        }

    }


}
// dto com.gestion.almacenes.entities.Config
// dto com.gestion.almacenes.entities.ConfigurationParameter
// dto com.gestion.almacenes.entities.CatalogProductStorehouse

