package com.gumiel.code_generator.shell.functional;

import com.gumiel.code_generator.shell.ConfigShell;
import com.gumiel.code_generator.shell.commons.UtilShell;
import com.gumiel.code_generator.shell.objects.DtoShell;
import com.gumiel.code_generator.shell.objects.EntityShell;
import com.gumiel.code_generator.shell.objects.FilterShell;
import com.gumiel.code_generator.shell.objects.PojoShell;
import lombok.Getter;

/**
 * Clase ImplementShell
 * Contiene los atributos para generar una implementación de un servicio
 * Creado por: Henry Perez Gumiel
 * Fecha: 09/03/2025
 */
@Getter
public class ImplementShell {
    String nameImplement;
    String nameImplementLC;
    EntityShell entityShell;
    DtoShell dtoShell;
    PojoShell pojoShell;
    FilterShell filterShell;
    MapperShell mapperShell;
    ServiceShell serviceShell;
    RepositoryShell repositoryShell;

    public ImplementShell(EntityShell entityShell, DtoShell dtoShell, FilterShell filterShell, PojoShell pojoShell, MapperShell mapperShell, ServiceShell serviceShell, RepositoryShell repositoryShell) {
        this.entityShell = entityShell;
        this.dtoShell = dtoShell;
        this.filterShell = filterShell;
        this.pojoShell = pojoShell;
        this.repositoryShell = repositoryShell;
        this.nameImplement = entityShell.getNameEntity()+"ServiceImpl_bk";
        this.nameImplementLC = UtilShell.getFirstLetterLowerCase(this.nameImplement);
        this.mapperShell = mapperShell;
        this.serviceShell = serviceShell;
    }

    public StringBuilder generateStringImplement(){

        String entityShellName = entityShell.getNameEntity();
        String entityShellNameLC = UtilShell.getFirstLetterLowerCase(entityShellName);
        String dtoShellName = dtoShell.getNameDto();
        String dtoShellNameLC = UtilShell.getFirstLetterLowerCase(dtoShell.getNameDto());
        String filterShellName = filterShell.getNameFilter();
        String mapperShellName = mapperShell.getNameMapper();
        String mapperShellNameLC = UtilShell.getFirstLetterLowerCase(mapperShellName);
        String pojoShellName = pojoShell.getNamePojo();
        String serviceShellName = serviceShell.getNameService();
        String repositoryShellName = repositoryShell.getNameRepository();
        String repositoryShellNameLC = UtilShell.getFirstLetterLowerCase(repositoryShellName);
        String implementShellName = this.getNameImplement();

        StringBuilder builder = new StringBuilder();
        builder.append("package "+ConfigShell.IMPLEMENT_PACKAGE).append("\n\n")
                .append("import "+ConfigShell.BASE_PACKAGE+".commons.util.PagePojo;\n")
                .append("import "+ConfigShell.BASE_PACKAGE+".dtos.").append(dtoShellName).append(";\n")
                .append("import "+ConfigShell.BASE_PACKAGE+".entities.*;\n")
                .append("import "+ConfigShell.BASE_PACKAGE+".filters.").append(filterShellName).append(";\n")
                .append("import "+ConfigShell.BASE_PACKAGE+".mappers.").append(mapperShellName).append(";\n")
                .append("import "+ConfigShell.BASE_PACKAGE+".pojos.").append(pojoShellName).append(";\n")
                .append("import "+ConfigShell.BASE_PACKAGE+".repositories.*;\n")
                .append("import "+ConfigShell.BASE_PACKAGE+".services.").append(serviceShellName).append(";\n")
                .append("import jakarta.persistence.EntityNotFoundException;\n")
                .append("import jakarta.transaction.Transactional;\n")
                .append("import lombok.AllArgsConstructor;\n")
                .append("import org.springframework.data.domain.Page;\n")
                .append("import org.springframework.data.domain.PageRequest;\n")
                .append("import org.springframework.data.domain.Pageable;\n")
                .append("import org.springframework.data.domain.Sort;\n")
                .append("import org.springframework.stereotype.Service;\n\n")
                .append("import java.util.List;\n\n")

                .append("@Transactional\n")
                .append("@Service\n")
                .append("@AllArgsConstructor\n")
                .append("public class ").append(implementShellName).append(" implements\n")
                .append("        ").append(serviceShellName).append(" {\n\n")
                .append("  private final ").append(repositoryShellName).append(" ").append(repositoryShellNameLC).append(";\n")
                .append("  private final ").append(mapperShellName).append(" ").append(mapperShellNameLC).append(";\n")
                .append("\n")
                .append(methodGetAll())
                .append("\n")
                .append(methodCreate())
                .append("\n")
                .append(methodUpdate())
                .append("\n")
                .append(methodGetById())
                .append("\n")
                .append(methodDelete())
                .append("\n")
                .append(methodDisabled())
                .append("\n")
                .append(methodPageable())
                .append("\n")
                .append(methodFind())
                .append("\n")
                .append("}\n");
        return builder;
    }

    public String getNameImplement() {
        return nameImplement;
    }

    public String getNameImplementLC() {
        return nameImplementLC;
    }

    public EntityShell getEntityShell() {
        return entityShell;
    }

    public DtoShell getDtoShell() {
        return dtoShell;
    }

    public PojoShell getPojoShell() {
        return pojoShell;
    }

    public FilterShell getFilterShell() {
        return filterShell;
    }

    public MapperShell getMapperShell() {
        return mapperShell;
    }

    public ServiceShell getServiceShell() {
        return serviceShell;
    }

    public RepositoryShell getRepositoryShell() {
        return repositoryShell;
    }

    private StringBuilder methodGetAll(){

        StringBuilder method = new StringBuilder();
        String entityShellName = entityShell.getNameEntity();
        String entityShellNameLC = UtilShell.getFirstLetterLowerCase(entityShellName);
        String dtoShellName = dtoShell.getNameDto();
        String dtoShellNameLC = UtilShell.getFirstLetterLowerCase(dtoShell.getNameDto());
        String filterShellName = filterShell.getNameFilter();
        String mapperShellName = mapperShell.getNameMapper();
        String mapperShellNameLC = UtilShell.getFirstLetterLowerCase(mapperShellName);
        String pojoShellName = pojoShell.getNamePojo();
        String serviceShellName = serviceShell.getNameService();
        String repositoryShellName = repositoryShell.getNameRepository();
        String repositoryShellNameLC = UtilShell.getFirstLetterLowerCase(repositoryShellName);
        String implementShellName = this.getNameImplement();

        method.append("  @Override\n")
            .append("  public List<").append(pojoShellName).append("> getAll() {\n")
            .append("    return ").append(mapperShellNameLC).append(".fromEntityListToPojoList(\n")
            .append("            ").append(repositoryShellNameLC).append(".findByActiveTrue()\n")
            .append("    );\n")
            .append("  }\n");
        return method;
    }

    private StringBuilder methodCreate(){

        StringBuilder method = new StringBuilder();
        String entityShellName = entityShell.getNameEntity();
        String entityShellNameLC = UtilShell.getFirstLetterLowerCase(entityShellName);
        String dtoShellName = dtoShell.getNameDto();
        String dtoShellNameLC = UtilShell.getFirstLetterLowerCase(dtoShell.getNameDto());
        String filterShellName = filterShell.getNameFilter();
        String mapperShellName = mapperShell.getNameMapper();
        String mapperShellNameLC = UtilShell.getFirstLetterLowerCase(mapperShellName);
        String pojoShellName = pojoShell.getNamePojo();
        String serviceShellName = serviceShell.getNameService();
        String repositoryShellName = repositoryShell.getNameRepository();
        String repositoryShellNameLC = UtilShell.getFirstLetterLowerCase(repositoryShellName);
        String implementShellName = this.getNameImplement();

        method.append("  @Override\n")
            .append("  public ").append(pojoShellName).append(" create(").append(dtoShellName).append(" ").append(dtoShellNameLC).append(") {\n\n")
            .append("    ").append(entityShellName).append(" ").append(entityShellNameLC).append(" = ").append(mapperShellNameLC).append(".fromDtoToEntity(").append(dtoShellNameLC).append(", null);\n\n")
            .append("    return ").append(mapperShellNameLC).append(".fromEntityToPojo(\n")
            .append("            ").append(repositoryShellNameLC).append(".save(").append(entityShellNameLC).append(")\n")
            .append("    );\n")
            .append("  }\n\n");
        return method;
    }

    private StringBuilder methodUpdate(){

        StringBuilder method = new StringBuilder();
        String entityShellName = entityShell.getNameEntity();
        String entityShellNameLC = UtilShell.getFirstLetterLowerCase(entityShellName);
        String dtoShellName = dtoShell.getNameDto();
        String dtoShellNameLC = UtilShell.getFirstLetterLowerCase(dtoShell.getNameDto());
        String filterShellName = filterShell.getNameFilter();
        String mapperShellName = mapperShell.getNameMapper();
        String mapperShellNameLC = UtilShell.getFirstLetterLowerCase(mapperShellName);
        String pojoShellName = pojoShell.getNamePojo();
        String serviceShellName = serviceShell.getNameService();
        String repositoryShellName = repositoryShell.getNameRepository();
        String repositoryShellNameLC = UtilShell.getFirstLetterLowerCase(repositoryShellName);
        String implementShellName = this.getNameImplement();

        method.append("  @Override\n")
            .append("  public ").append(pojoShellName).append(" update(Integer id, ").append(dtoShellName).append(" ").append(dtoShellNameLC).append(") {\n")
            .append("    ").append(entityShellName).append(" ").append(entityShellNameLC).append("Found = this.find").append(entityShellName).append("ById(id);\n\n")
            .append("    ").append(entityShellName).append(" ").append(entityShellNameLC).append(" = ").append(mapperShellNameLC).append(".fromDtoToEntity(").append(dtoShellNameLC).append(", ").append(entityShellNameLC).append("Found);\n\n")
            .append("    return ").append(mapperShellNameLC).append(".fromEntityToPojo(\n")
            .append("            ").append(repositoryShellNameLC).append(".save(").append(entityShellNameLC).append(")\n")
            .append("    );\n\n")
            .append("  }\n");
        return method;
    }

    private StringBuilder methodGetById(){

        StringBuilder method = new StringBuilder();
        String entityShellName = entityShell.getNameEntity();
        String entityShellNameLC = UtilShell.getFirstLetterLowerCase(entityShellName);
        String dtoShellName = dtoShell.getNameDto();
        String dtoShellNameLC = UtilShell.getFirstLetterLowerCase(dtoShell.getNameDto());
        String filterShellName = filterShell.getNameFilter();
        String mapperShellName = mapperShell.getNameMapper();
        String mapperShellNameLC = UtilShell.getFirstLetterLowerCase(mapperShellName);
        String pojoShellName = pojoShell.getNamePojo();
        String serviceShellName = serviceShell.getNameService();
        String repositoryShellName = repositoryShell.getNameRepository();
        String repositoryShellNameLC = UtilShell.getFirstLetterLowerCase(repositoryShellName);
        String implementShellName = this.getNameImplement();

        method.append("  @Override\n")
            .append("  public ").append(pojoShellName).append(" getById(Integer id) {\n")
            .append("    return ").append(mapperShellNameLC).append(".fromEntityToPojo(\n")
            .append("            this.find").append(entityShellName).append("ById(id)\n")
            .append("    );\n")
            .append("  }\n\n");
        return method;
    }

    private StringBuilder methodDelete(){

        StringBuilder method = new StringBuilder();
        String entityShellName = entityShell.getNameEntity();
        String entityShellNameLC = UtilShell.getFirstLetterLowerCase(entityShellName);
        String dtoShellName = dtoShell.getNameDto();
        String dtoShellNameLC = UtilShell.getFirstLetterLowerCase(dtoShell.getNameDto());
        String filterShellName = filterShell.getNameFilter();
        String mapperShellName = mapperShell.getNameMapper();
        String mapperShellNameLC = UtilShell.getFirstLetterLowerCase(mapperShellName);
        String pojoShellName = pojoShell.getNamePojo();
        String serviceShellName = serviceShell.getNameService();
        String repositoryShellName = repositoryShell.getNameRepository();
        String repositoryShellNameLC = UtilShell.getFirstLetterLowerCase(repositoryShellName);
        String implementShellName = this.getNameImplement();

        method.append("  @Override\n")
            .append("  public void delete(Integer id) {\n")
            .append("    ").append(entityShellName).append(" ").append(entityShellNameLC).append(" = this.find").append(entityShellName).append("ById(id);\n")
            .append("    ").append(repositoryShellNameLC).append(".delete(").append(entityShellNameLC).append(");\n")
            .append("  }\n\n");
        return method;
    }

    private StringBuilder methodDisabled(){

        StringBuilder method = new StringBuilder();
        String entityShellName = entityShell.getNameEntity();
        String entityShellNameLC = UtilShell.getFirstLetterLowerCase(entityShellName);
        String dtoShellName = dtoShell.getNameDto();
        String dtoShellNameLC = UtilShell.getFirstLetterLowerCase(dtoShell.getNameDto());
        String filterShellName = filterShell.getNameFilter();
        String mapperShellName = mapperShell.getNameMapper();
        String mapperShellNameLC = UtilShell.getFirstLetterLowerCase(mapperShellName);
        String pojoShellName = pojoShell.getNamePojo();
        String serviceShellName = serviceShell.getNameService();
        String repositoryShellName = repositoryShell.getNameRepository();
        String repositoryShellNameLC = UtilShell.getFirstLetterLowerCase(repositoryShellName);
        String implementShellName = this.getNameImplement();

        method.append("  @Override\n")
            .append("  public void disabled(Integer id) {\n")
            .append("    ").append(entityShellName).append(" ").append(entityShellNameLC).append(" = this.find").append(entityShellName).append("ById(id);\n")
            .append("    if (Boolean.TRUE.equals(").append(entityShellNameLC).append(".getActive())) {\n")
            .append("      ").append(entityShellNameLC).append(".setActive(false);\n")
            .append("      ").append(repositoryShellNameLC).append(".save(").append(entityShellNameLC).append(");\n")
            .append("    } else {\n")
            .append("      throw new RuntimeException(\"El identificador ya fue eliminado\");\n")
            .append("    }\n")
            .append("  }\n\n");
        return method;
    }

    private StringBuilder methodPageable(){

        StringBuilder method = new StringBuilder();
        String entityShellName = entityShell.getNameEntity();
        String entityShellNameLC = UtilShell.getFirstLetterLowerCase(entityShellName);
        String dtoShellName = dtoShell.getNameDto();
        String dtoShellNameLC = UtilShell.getFirstLetterLowerCase(dtoShell.getNameDto());
        String filterShellName = filterShell.getNameFilter();
        String mapperShellName = mapperShell.getNameMapper();
        String mapperShellNameLC = UtilShell.getFirstLetterLowerCase(mapperShellName);
        String pojoShellName = pojoShell.getNamePojo();
        String serviceShellName = serviceShell.getNameService();
        String repositoryShellName = repositoryShell.getNameRepository();
        String repositoryShellNameLC = UtilShell.getFirstLetterLowerCase(repositoryShellName);
        String implementShellName = this.getNameImplement();

        method.append("  @Override\n")
            .append("  public PagePojo<").append(pojoShellName).append("> pageable(Integer pageNumber, Integer pageSize, String sortField,\n")
            .append("      String sortOrder, ").append(filterShellName).append(" filter) {\n\n")
            .append("    Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortField);\n")
            .append("    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);\n\n")
            .append("    Page<").append(entityShellName).append("> ").append(entityShellNameLC).append("Page = ").append(repositoryShellNameLC).append(".findAll(pageable);\n\n")
            .append("    return ").append(mapperShellNameLC).append(".toPagePojo(").append(entityShellNameLC).append("Page);\n")
            .append("  }\n");
        return method;
    }

    private StringBuilder methodFind(){

        StringBuilder method = new StringBuilder();
        String entityShellName = entityShell.getNameEntity();
        String entityShellNameLC = UtilShell.getFirstLetterLowerCase(entityShellName);
        String dtoShellName = dtoShell.getNameDto();
        String dtoShellNameLC = UtilShell.getFirstLetterLowerCase(dtoShell.getNameDto());
        String filterShellName = filterShell.getNameFilter();
        String mapperShellName = mapperShell.getNameMapper();
        String mapperShellNameLC = UtilShell.getFirstLetterLowerCase(mapperShellName);
        String pojoShellName = pojoShell.getNamePojo();
        String serviceShellName = serviceShell.getNameService();
        String repositoryShellName = repositoryShell.getNameRepository();
        String repositoryShellNameLC = UtilShell.getFirstLetterLowerCase(repositoryShellName);
        String implementShellName = this.getNameImplement();

        method.append("  private ").append(entityShellName).append(" find").append(entityShellName).append("ById(Integer id) {\n")
            .append("    return ").append(repositoryShellNameLC).append(".findById(id).orElseThrow(\n")
            .append("        () -> new EntityNotFoundException(\"No fue encontrado el identificador\"+ id.toString())\n")
            .append("    );\n")
            .append("  }\n");
        return method;
    }
}
