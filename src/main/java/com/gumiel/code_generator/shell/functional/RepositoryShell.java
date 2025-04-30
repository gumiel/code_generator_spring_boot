package com.gumiel.code_generator.shell.functional;

import com.gumiel.code_generator.shell.ParamsV1;
import com.gumiel.code_generator.shell.commons.ToolsShell;
import com.gumiel.code_generator.shell.commons.UtilShell;
import com.gumiel.code_generator.shell.objects.AtributesShell;
import com.gumiel.code_generator.shell.objects.DtoShell;
import com.gumiel.code_generator.shell.objects.EntityShell;
import com.gumiel.code_generator.shell.objects.FilterShell;

/**
 * Clase RepositoryShell
 * Contiene los atributos para generar un repositorio
 * Creado por: Henry Perez Gumiel
 * Fecha: 09/03/2025
 */
public class RepositoryShell extends ToolsShell {

    String nameRepository;
    String nameRepositoryLC;
    EntityShell entityShell;
    DtoShell dtoShell;
    FilterShell filterShell;
    ParamsV1 pv1;

    public RepositoryShell(EntityShell entityShell, DtoShell dtoShell, FilterShell filterShell) {
        pv1 = new ParamsV1();
        this.entityShell = entityShell;
        this.dtoShell = dtoShell;
        this.filterShell = filterShell;
        this.nameRepository = entityShell.getNameEntity()+"Repository_bk";
        this.nameRepositoryLC = UtilShell.getFirstLetterLowerCase(this.nameRepository);
    }



    public StringBuilder generateStringRepository() {

        String entityShellName = entityShell.getNameEntity();
        String repositoryShellName = this.getNameRepository();
        String entityFilterName = filterShell.getNameFilter();

        StringBuilder builder = new StringBuilder();
        builder.append("package "+ pv1.getRepositoryPackageName()).append("\n\n")
                .append("import "+ ParamsV1.BASE_PACKAGE+".entities.").append(entityShellName).append(";\n")
                .append("import "+ ParamsV1.BASE_PACKAGE+".filters.").append(entityFilterName).append(";\n")
                .append("import org.springframework.data.domain.Page;\n")
                .append("import org.springframework.data.domain.Pageable;\n")
                .append("import org.springframework.data.jpa.repository.Query;\n")
                .append("import org.springframework.data.jpa.repository.JpaRepository;\n\n")
                .append("import java.util.List;\n")
                .append("import java.util.Optional;\n\n")
                .append("public interface ").append(repositoryShellName).append(" extends JpaRepository<").append(entityShellName).append(", Integer> {\n\n")
                .append("  List<").append(entityShellName).append("> findByActiveTrue();\n\n")
                .append("  Optional<").append(entityShellName).append("> findByIdAndActiveIsTrue(Integer id);\n\n")
                .append("  Page<").append(entityShellName).append("> findAll(Pageable pageable);\n")
                .append("\n")
                .append(this.findAll())
                .append("}\n");
        return builder;
    }

    public StringBuilder findAll() {

        String entityName = entityShell.getNameEntity();
        String alias = String.valueOf(entityShell.getNameEntity().charAt(0));
        StringBuilder query = new StringBuilder();

        query.append("  @Query(\"\"\"\n")
             .append("          SELECT ").append(alias).append("\n")
             .append("          FROM ").append(entityName).append(" ").append(alias).append("\n")
             .append("          WHERE ").append(alias).append(".active = TRUE\n")
             .append(this.findAllWithAnd())
             .append(this.findAllWithAndSearch())
             .append("  \"\"\")\n")
             .append("  Page<").append(entityName).append("> pageable(").append(filterShell.getNameFilter()).append(" filter, Pageable pageable);\n");
        return query;
    }

    public StringBuilder findAllWithAnd(){
        StringBuilder query = new StringBuilder();
        String alias = String.valueOf(entityShell.getNameEntity().charAt(0));
        for(AtributesShell atributesShell : entityShell.getAtributesShellList()){
            String nameAttribute = atributesShell.getNameAttributes();
            String typeAttribute = atributesShell.getTypeAttributes();
            if(this.isTypeValidPageable(typeAttribute)){
                if(this.isTypeValidForEquals(typeAttribute)){
                    query.append("          AND ( :#{#filter.").append(nameAttribute).append("} IS NULL OR ").append(alias).append(".").append(nameAttribute).append("= :#{#filter.").append(nameAttribute).append("} )\n");
                }
                if(this.isTypeValidForLike(typeAttribute)){
                    query.append("          AND ( :#{#filter.").append(nameAttribute).append("} IS NULL OR lower(").append(alias).append(".").append(nameAttribute).append(") LIKE lower(concat('%', :#{#filter.").append(nameAttribute).append("}, '%')) )\n");
                }
            }
        }
        return query;
    }

    public StringBuilder findAllWithAndSearch(){
        StringBuilder query = new StringBuilder();
        String alias = String.valueOf(entityShell.getNameEntity().charAt(0));
        query.append("          AND (\n");
        query.append("            (:#{#filter.search} IS NULL OR :#{#filter.search} = '')\n");
        for(AtributesShell atributesShell : entityShell.getAtributesShellList()){
            String nameAttribute = atributesShell.getNameAttributes();
            String typeAttribute = atributesShell.getTypeAttributes();
            if(this.isTypeValidForSearch(typeAttribute)){
                query.append("            OR lower(").append(alias).append(".").append(nameAttribute).append(") LIKE lower(concat('%', :#{#filter.search}, '%'))\n");
            }
        }
        query.append("          )\n");
        return query;
    }

    public String getNameRepository() {
        return nameRepository;
    }

    public String getNameRepositoryLC() {
        return nameRepositoryLC;
    }
}
