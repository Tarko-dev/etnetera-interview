package cz.eg.hr.service;


import cz.eg.hr.JavascriptFrameworkNotFoundException;
import cz.eg.hr.data.dto.JavascriptFrameworkDto;
import cz.eg.hr.data.entity.JavascriptFramework;
import cz.eg.hr.mapper.EditFrameworkMapper;
import cz.eg.hr.mapper.JavascriptFrameworkMapper;
import cz.eg.hr.repository.JavascriptFrameworkRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class JavascriptFrameworkService {

    private JavascriptFrameworkRepository frameworkRepository;

    private JavascriptFrameworkMapper mapper;


    private EntityManager entityManager;

    public List<JavascriptFrameworkDto> findAll() {
        log.info("Finding all Javascript Frameworks");
        return frameworkRepository.findAll().stream()
            .map(entities -> mapper.mapToDto(entities))
            .toList();
    }

    public JavascriptFrameworkDto findOne(Long id){
        log.info("Finding Javascript Framework by id: {}", id);
        return mapper.mapToDto(frameworkRepository.findById(id)
            .orElseThrow(() -> new JavascriptFrameworkNotFoundException("Javascript Framework under ID: " + id + " not found")));
    }

    public JavascriptFrameworkDto save(JavascriptFrameworkDto javascriptFrameworkDto) {
        log.info("Preparing new JavascriptFramework Entity");
        mapper.mapToEntity(javascriptFrameworkDto);
        log.info("Saving prepared Entity");
        return mapper.mapToDto(
            frameworkRepository.save(mapper.mapToEntity(javascriptFrameworkDto))
        );
    }

    public void delete(Long id) {
        frameworkRepository.deleteById(id);
        log.info("JavascriptFramework under id: {} was deleted", id);
    }

    @Transactional
    public JavascriptFrameworkDto edit(JavascriptFrameworkDto javascriptFrameworkDto, Long id) {
        log.info("Finding Javascript Framework by id: {}", id);
        JavascriptFramework existingEntity = frameworkRepository.findById(id)
            .orElseThrow(() -> new JavascriptFrameworkNotFoundException("Javascript Framework under ID: " + id + " not found"));

        log.info("Preparing new JavascriptFramework Entity");
        JavascriptFramework newEntity = mapper.mapToEntity(javascriptFrameworkDto);

        newEntity = EditFrameworkMapper.editEntity(existingEntity, newEntity);

        log.info("Entities successfully merged");

        log.info("Saving prepared Entity");
        JavascriptFramework savedEntity = frameworkRepository.save(newEntity);

        return mapper.mapToDto(savedEntity);
    }

    public List<JavascriptFrameworkDto> fulltextSearch (String prompt) {
        SearchSession searchSession = Search.session(entityManager);

        log.info("Looking for matches");
        SearchResult<JavascriptFramework> result = searchSession.search(JavascriptFramework.class)
            .where(find -> find.match()
                .field("name")
                .matching(prompt))
            .fetch(20);

        log.info("Found {} matches", result.hits().size());
        return result.hits().stream()
            .map(hit -> mapper.mapToDto(hit))
            .toList();
    }

}
