package cz.eg.hr.mapper;

import cz.eg.hr.data.entity.JavascriptFramework;
import cz.eg.hr.data.entity.Version;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class EditFrameworkMapper {
    private EditFrameworkMapper() {
    }
    //Eeh :( if possible can we go through this together on the review? would love to see better solution!
    public static JavascriptFramework editEntity (JavascriptFramework existingEntity, JavascriptFramework newEntity) {

        log.info("Merging new and existing entity");
        Set<Version> versionHolder = new HashSet<>();

        if (newEntity.getName()!= null){
            existingEntity.setName(newEntity.getName());
        }
        if (newEntity.getRating()>=0 && newEntity.getRating()<=5){
            existingEntity.setRating(newEntity.getRating());
        }

        if (newEntity.getVersions()!=null){
            existingEntity.getVersions().stream()
                .forEach(version -> {
                    for (Version newVersion: newEntity.getVersions()){
                        if (version.getVersionNumber().equals(newVersion.getVersionNumber())){
                            version.setDeprecatedDate(newVersion.getDeprecatedDate());
                        } else {
                            versionHolder.add(newVersion);
                        }
                    }
                });
        }

        List<Version>finalVersions = versionHolder.stream().filter(version -> {
            for (Version existingVersion: existingEntity.getVersions()){
                if (version.getVersionNumber().equals(existingVersion.getVersionNumber())){
                    return false;
                }
            }
            return true;
        }).toList();

        existingEntity.getVersions().addAll(finalVersions);

        return existingEntity;
    }
}
