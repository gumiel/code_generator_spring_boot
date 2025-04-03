package com.gumiel.code_generator.shell.commons;

import com.gumiel.code_generator.shell.ParamsV1;

public class ToolsParameters {

    private String baseNamePath = ParamsV1.BASE_NAME_PATH;

    public ToolsParameters(){
        baseNamePath = baseNamePath.replaceFirst("^/", "");
    }


    public String getEntityPathName(){
        return formatPath(ParamsV1.ENTITIES_PATH_NAME);
    }

    public String getEntityPackageName(){
        return ParamsV1.BASE_PACKAGE+ ParamsV1.ENTITIES_PATH_NAME.replace("/",".")+".";
    }

    public String getDtoPathName(){
        return baseNamePath+ ParamsV1.DTO_PATH_NAME.replaceFirst("/$", "")+"/";
    }

    public String getDtoPackageName(){
        return ParamsV1.BASE_PACKAGE+ ParamsV1.DTO_PATH_NAME.replace("/",".")+";";
    }

    public String getFilterPathName(){
        return baseNamePath+ ParamsV1.FILTER_PATH_NAME.replaceFirst("/$", "")+"/";
    }

    public String getFilterPackageName(){
        return ParamsV1.BASE_PACKAGE+ ParamsV1.FILTER_PATH_NAME.replace("/",".")+";";
    }

    public String getPojoPathName(){
        return baseNamePath+ ParamsV1.POJO_PATH_NAME.replaceFirst("/$", "")+"/";
    }

    public String getPojoPackageName(){
        return ParamsV1.BASE_PACKAGE+ ParamsV1.POJO_PATH_NAME.replace("/",".")+";";
    }

    public String getServicePathName(){
        return formatPath(ParamsV1.SERVICE_PATH_NAME);
    }

    public String getServicePackageName(){
        return formatPackage(ParamsV1.SERVICE_PATH_NAME);
    }

    public String getControllerPathName(){
        return formatPath(ParamsV1.CONTROLLER_PATH_NAME);
    }

    public String getControllerPackageName(){
        return formatPackage(ParamsV1.CONTROLLER_PATH_NAME);
    }

    public String getMapperPathName(){
        return formatPath(ParamsV1.MAPPER_PATH_NAME);
    }

    public String getMapperPackageName(){
        return formatPackage(ParamsV1.MAPPER_PATH_NAME);
    }

    public String getRepositoryPathName(){
        return formatPath(ParamsV1.REPOSITORY_PATH_NAME);
    }

    public String getRepositoryPackageName(){
        return formatPackage(ParamsV1.REPOSITORY_PATH_NAME);
    }

    public String getImplementPathName(){
        return formatPath(ParamsV1.IMPLEMENT_PATH_NAME);
    }

    public String getImplementPackageName(){
        return formatPackage(ParamsV1.IMPLEMENT_PATH_NAME);
    }
















    private String formatPath(String path1){
        return baseNamePath+path1.replaceFirst("/$", "")+"/";
    }

    private String formatPackage(String package1){
        return ParamsV1.BASE_PACKAGE+package1.replace("/",".")+";";
    }
}
