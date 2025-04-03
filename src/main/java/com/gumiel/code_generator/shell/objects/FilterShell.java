package com.gumiel.code_generator.shell.objects;

import com.gumiel.code_generator.shell.ParamsV1;
import com.gumiel.code_generator.shell.commons.ToolsShell;
import com.gumiel.code_generator.shell.commons.UtilShell;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase FilterShell
 * Contiene los atributos para generar un filtro
 * Creado por: Henry Perez Gumiel
 * Fecha: 09/03/2025
 */
public class FilterShell extends ToolsShell {
    String nameFilter;
    String nameFilterLC;
    String namePackage;
    List<StringBuilder> importList;
    List<StringBuilder> anottationList;
    String startFilter;
    String endFilter;
    StringBuilder body;
    EntityShell entityShell;
    ParamsV1 pv1;

    public FilterShell(EntityShell entityShell) {
        this.entityShell = entityShell;
        this.nameFilter = entityShell.getNameEntity()+"Filter_bk";
        this.constructHeader();
        this.body = new StringBuilder();
        this.nameFilterLC = UtilShell.getFirstLetterLowerCase(this.nameFilter);
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
        this.namePackage = "package "+ pv1.getFilterPackageName()+"\n\n";
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
        importList.add(new StringBuilder("import lombok.Builder;\n"));
        importList.add(new StringBuilder("import lombok.ToString;\n"));
        importList.add(new StringBuilder("import lombok.NoArgsConstructor;\n"));
        importList.add(new StringBuilder("\n"));
    }

    public void fillAnottations() {

        anottationList = new ArrayList<>();
        anottationList.add(new StringBuilder("@Builder\n"));
        anottationList.add(new StringBuilder("@ToString\n"));
        anottationList.add(new StringBuilder("@Getter\n"));
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
        bodyV1.append("private String search;\n");
        body = bodyV1;
    }

    public void createStartDto() {
        startFilter = "public class " + nameFilter + " {\n";
    }

    public void createEndDto() {
        endFilter = "}\n";
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
        dtoBuilder.append(startFilter);
        this.fillBody();
        dtoBuilder.append(body);
        dtoBuilder.append(endFilter);
        return dtoBuilder;
    }

    public String getNameFilter() {
        return nameFilter;
    }
}
