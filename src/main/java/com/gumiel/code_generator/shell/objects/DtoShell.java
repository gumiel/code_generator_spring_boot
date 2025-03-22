package com.gumiel.code_generator.shell.objects;


import com.gumiel.code_generator.shell.Parameter;
import com.gumiel.code_generator.shell.commons.ToolsShell;
import com.gumiel.code_generator.shell.commons.UtilShell;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class DtoShell extends ToolsShell {

    String pathName;
    String nameDto;
    String nameDtoLC;
    String namePackage;
    List<StringBuilder> importList;
    List<StringBuilder> anottationList;
    String startDto;
    String endDto;
    StringBuilder body;
    EntityShell entityShell;

    public DtoShell(EntityShell entityShell) {
        this.pathName = "src/main/java/com/gestion/almacenes/dtos/";
        this.entityShell = entityShell;
        this.nameDto = entityShell.getNameEntity()+"Dto_bk";
        this.constructHeader();
        this.body = new StringBuilder();
        this.nameDtoLC = UtilShell.getFirstLetterLowerCase(this.nameDto);
    }

    public void constructHeader() {
        this.fillNamePackage();
        this.fillImports();
        this.fillAnottations();
        this.createStartDto();
        this.createEndDto();
        this.fillBody();
    }

    public void fillNamePackage() {
        this.namePackage = "package "+ Parameter.DTO_PACKAGE+"\n\n";
    }

    public void fillImports() {
        importList = new ArrayList<>();
        importList.add(new StringBuilder("import java.time.LocalDateTime;\n"));
        importList.add(new StringBuilder("import java.math.BigDecimal;\n"));
        importList.add(new StringBuilder("import java.time.LocalDateTime;\n"));
        importList.add(new StringBuilder("import java.time.LocalDate;\n"));
        importList.add(new StringBuilder("import java.time.LocalTime;\n"));
        importList.add(new StringBuilder("import lombok.Getter;\n"));
        importList.add(new StringBuilder("import lombok.Setter;\n"));
        importList.add(new StringBuilder("import lombok.NoArgsConstructor;\n"));
        importList.add(new StringBuilder("\n"));
    }

    public void fillAnottations() {

        anottationList = new ArrayList<>();
        anottationList.add(new StringBuilder("@Getter\n"));
        anottationList.add(new StringBuilder("@Setter\n"));
        anottationList.add(new StringBuilder("@NoArgsConstructor\n"));
    }

    public void fillBody() {
        StringBuilder bodyV1 = new StringBuilder();
        entityShell.getAtributesShellList().forEach(atributesShell -> {
            bodyV1.append("private ");
            bodyV1.append(
                    this.convertType(atributesShell.getTypeAtributes())
            );
            bodyV1.append(" ");
            bodyV1.append(
                    (this.isTypeValid(atributesShell.getTypeAtributes())) ?
                            atributesShell.getNameAtributes():
                            atributesShell.getNameAtributes()+"Id"
            );
            bodyV1.append(";\n");
        });
        body = bodyV1;
    }

    public void createStartDto() {
        startDto = "public class " + nameDto + " {\n";
    }

    public void createEndDto() {
        endDto = "}\n";
    }

    public StringBuilder generateDto() {
        StringBuilder dtoBuilder = new StringBuilder();
        dtoBuilder.append(namePackage);
        for (StringBuilder importLine : importList) {
            dtoBuilder.append(importLine);
        }
        for (StringBuilder anottationLine : anottationList) {
            dtoBuilder.append(anottationLine);
        }
        dtoBuilder.append(startDto);
        this.fillBody();
        dtoBuilder.append(body);
        dtoBuilder.append(endDto);
        return dtoBuilder;
    }



    public String getPathName() {
        return pathName;
    }

    public String getNameDto() {
        return nameDto;
    }

    public String getNameDtoLC() {
        return nameDtoLC;
    }

    public String getNamePackage() {
        return namePackage;
    }

    public List<StringBuilder> getImportList() {
        return importList;
    }

    public List<StringBuilder> getAnottationList() {
        return anottationList;
    }

    public String getStartDto() {
        return startDto;
    }

    public String getEndDto() {
        return endDto;
    }

    public StringBuilder getBody() {
        return body;
    }

    public EntityShell getEntityShell() {
        return entityShell;
    }
}
