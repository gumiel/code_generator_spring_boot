package com.gumiel.code_generator.shell.objects;

import com.gumiel.code_generator.shell.ParamsV1;
import com.gumiel.code_generator.shell.commons.ToolsShell;
import com.gumiel.code_generator.shell.commons.UtilShell;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase PojoShell
 * Contiene los atributos para generar un pojo
 * Creado por: Henry Perez Gumiel
 * Fecha: 09/03/2025
 */
public class PojoShell extends ToolsShell {
    String namePojo;
    String namePojoLC;
    String namePackage;
    List<StringBuilder> importList;
    List<StringBuilder> anottationList;
    String startPojo;
    String endPojo;
    StringBuilder body;
    EntityShell entityShell;
    ParamsV1 pv1;

    public PojoShell(EntityShell entityShell) {
        this.entityShell = entityShell;
        this.namePojo = entityShell.getNameEntity()+"Pojo_bk";
        this.constructHeader();
        this.body = new StringBuilder();
        this.namePojoLC = UtilShell.getFirstLetterLowerCase(this.namePojo);
    }

    public void constructHeader() {
        pv1 = new ParamsV1();
        this.fillNamePackage();
        this.fillImports();
        this.fillAnottations();
        this.createStartDto();
        this.createEndDto();
        this.fillBody();
    }

    public void fillNamePackage() {
        this.namePackage = "package "+ pv1.getPojoPackageName()+"\n\n";
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
                    this.convertType(atributesShell.getTypeAttributes())
            );
            bodyV1.append(" ");
            bodyV1.append(
                    (this.isTypeValid(atributesShell.getTypeAttributes())) ?
                            atributesShell.getNameAttributes():
                            atributesShell.getNameAttributes()+"Id"
            );
            bodyV1.append(";\n");
        });
        body = bodyV1;
    }

    public void createStartDto() {
        startPojo = "public class " + namePojo + " {\n";
    }

    public void createEndDto() {
        endPojo = "}\n";
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
        dtoBuilder.append(startPojo);
        this.fillBody();
        dtoBuilder.append(body);
        dtoBuilder.append(endPojo);
        return dtoBuilder;
    }

    public String getNamePojo() {
        return namePojo;
    }

    public String getNamePojoLC() {
        return namePojoLC;
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

    public String getStartPojo() {
        return startPojo;
    }

    public String getEndPojo() {
        return endPojo;
    }

    public StringBuilder getBody() {
        return body;
    }

    public EntityShell getEntityShell() {
        return entityShell;
    }
}
