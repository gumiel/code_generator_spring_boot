package com.gumiel.code_generator.shell;

import com.gumiel.code_generator.shell.commons.UtilShell;
import com.gumiel.code_generator.shell.functional.*;
import com.gumiel.code_generator.shell.objects.*;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@ShellComponent
public class MyCommandsV1 {

    @ShellMethod(key = "list")
    public List<String> listEntitiesClasses() throws ClassNotFoundException {
        return UtilShell.listClassesInPackage(Parameter.ENTITIES_NAME_PATH);
    }

    @ShellMethod(key = "all")
    public String generateDtoV1(@ShellOption String className, String param) throws ClassNotFoundException {

        Class<?> clazz = null;
        if (!className.contains(".")) {
            System.out.println(Parameter.ENTITIES_PACKAGE_SIMPLE+className);
            clazz = Class.forName(Parameter.ENTITIES_PACKAGE_SIMPLE+className);
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
            atributesShell.setTypeAtributes(field.getType().getSimpleName());
            atributesShell.setNameAtributes(field.getName());
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
            UtilShell.createFile(entityShell.getNameEntity()+"Dto_bk", dtoShell.generateDto().toString(), Parameter.DTO_NAME_PATH);
            UtilShell.createFile(entityShell.getNameEntity()+"Filter_bk", filterShell.generateDto().toString(), Parameter.FILTER_NAME_PATH);
            UtilShell.createFile(entityShell.getNameEntity()+"Pojo_bk", pojoShell.generateDto().toString(), Parameter.POJO_NAME_PATH);
            UtilShell.createFile(entityShell.getNameEntity()+"Service_bk", serviceShell.generateStringService().toString(), Parameter.SERVICE_NAME_PATH);
            UtilShell.createFile(entityShell.getNameEntity()+"Controller_bk", controllerShell.generateStringController().toString(), Parameter.CONTROLLER_NAME_PATH);
            UtilShell.createFile(entityShell.getNameEntity()+"Mapper_bk", mapperShell.generateStringMapper().toString(), Parameter.MAPPER_NAME_PATH);
            UtilShell.createFile(entityShell.getNameEntity()+"Repository_bk", repositoryShell.generateStringRepository().toString(), Parameter.REPOSITORY_NAME_PATH);
            UtilShell.createFile(entityShell.getNameEntity()+"ServiceImpl_bk", implementShell.generateStringImplement().toString(), Parameter.IMPLEMENT_NAME_PATH);
        }else if(param.equals("DTO")){
            UtilShell.createFile(entityShell.getNameEntity()+"Dto_bk", dtoShell.generateDto().toString(), Parameter.DTO_NAME_PATH);
        } else if (param.equals("FILTER")) {
            UtilShell.createFile(entityShell.getNameEntity()+"Filter_bk", filterShell.generateDto().toString(), Parameter.FILTER_NAME_PATH);
        } else if (param.equals("POJO")) {
            UtilShell.createFile(entityShell.getNameEntity()+"Pojo_bk", pojoShell.generateDto().toString(), Parameter.POJO_NAME_PATH);
        } else if (param.equals("SERVICE")) {
            UtilShell.createFile(entityShell.getNameEntity()+"Service_bk", serviceShell.generateStringService().toString(), Parameter.SERVICE_NAME_PATH);
        } else if (param.equals("CONTROLLER")) {
            UtilShell.createFile(entityShell.getNameEntity()+"Controller_bk", controllerShell.generateStringController().toString(), Parameter.CONTROLLER_NAME_PATH);
        } else if (param.equals("MAPPER")) {
            UtilShell.createFile(entityShell.getNameEntity()+"Mapper_bk", mapperShell.generateStringMapper().toString(), Parameter.MAPPER_NAME_PATH);
        } else if (param.equals("REPOSITORY")) {
            UtilShell.createFile(entityShell.getNameEntity()+"Repository_bk", repositoryShell.generateStringRepository().toString(), Parameter.REPOSITORY_NAME_PATH);
        } else if (param.equals("IMPLEMENT")) {
            UtilShell.createFile(entityShell.getNameEntity()+"ServiceImpl_bk", implementShell.generateStringImplement().toString(), Parameter.IMPLEMENT_NAME_PATH);
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

        String filePath = Parameter.BASE_NAME_PATH + "/dtos/" + className + "Dto_bk.java";
        Files.deleteIfExists(Paths.get(filePath));
        filePath = Parameter.BASE_NAME_PATH + "/filters/" + className + "Filter_bk.java";
        Files.deleteIfExists(Paths.get(filePath));
        filePath = Parameter.BASE_NAME_PATH + "/pojos/" + className + "Pojo_bk.java";
        Files.deleteIfExists(Paths.get(filePath));
        filePath = Parameter.BASE_NAME_PATH + "/services/" + className + "Service_bk.java";
        Files.deleteIfExists(Paths.get(filePath));
        filePath = Parameter.BASE_NAME_PATH + "/controllers/" + className + "Controller_bk.java";
        Files.deleteIfExists(Paths.get(filePath));
        filePath = Parameter.BASE_NAME_PATH + "/mappers/" + className + "Mapper_bk.java";
        Files.deleteIfExists(Paths.get(filePath));
        filePath = Parameter.BASE_NAME_PATH + "/repositories/" + className + "Repository_bk.java";
        Files.deleteIfExists(Paths.get(filePath));
        filePath = Parameter.BASE_NAME_PATH + "/servicesImpls/" + className + "ServiceImpl_bk.java";
        Files.deleteIfExists(Paths.get(filePath));

        return "File " + className + " deleted successfully.";
    }


}
// dto com.gestion.almacenes.entities.Config
// dto com.gestion.almacenes.entities.ConfigurationParameter
// dto com.gestion.almacenes.entities.CatalogProductStorehouse

