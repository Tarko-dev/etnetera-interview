package cz.eg.hr.mapper;


import cz.eg.hr.data.dto.JavascriptFrameworkDto;
import cz.eg.hr.data.dto.VersionDto;
import cz.eg.hr.data.entity.JavascriptFramework;
import cz.eg.hr.data.entity.Version;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface JavascriptFrameworkMapper {

    JavascriptFrameworkDto mapToDto (JavascriptFramework javascriptFramework);

    JavascriptFramework mapToEntity (JavascriptFrameworkDto javascriptFrameworkDto);

    VersionDto versionToVersionDto (Version version);

    Version versionDtoToVersion (VersionDto versionDto);

}
