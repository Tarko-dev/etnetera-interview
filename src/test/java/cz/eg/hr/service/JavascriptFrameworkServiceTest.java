package cz.eg.hr.service;


import cz.eg.hr.data.dto.JavascriptFrameworkDto;
import cz.eg.hr.data.dto.VersionDto;
import cz.eg.hr.data.entity.JavascriptFramework;
import cz.eg.hr.data.entity.Version;
import cz.eg.hr.mapper.JavascriptFrameworkMapper;
import cz.eg.hr.repository.JavascriptFrameworkRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class JavascriptFrameworkServiceTest {

    @Mock
    private JavascriptFrameworkRepository repository;

    @Mock
    private JavascriptFrameworkMapper mapper;

    @Mock
    private EntityManager entityManager;

    private JavascriptFrameworkService testedService;

    @BeforeEach
    public void setUp() {
        testedService = new JavascriptFrameworkService(repository, mapper, entityManager);
    }


    @Test
    void findAll() {
        //GIVEN
        LocalDate date = LocalDate.now();
        JavascriptFramework framework = JavascriptFramework.builder()
            .id(1L)
            .name("name")
            .versions(Collections.singletonList(new Version()))
            .rating(5)
            .build();

        List<JavascriptFramework> frameworks = Collections.singletonList(framework);

        List<JavascriptFrameworkDto> frameworksDtos = Collections.singletonList(new JavascriptFrameworkDto("name", Collections.singletonList(new VersionDto("00.00.00", date)), 5));


        Mockito.when(repository.findAll()).thenReturn(frameworks);
        Mockito.when(mapper.mapToDto(framework)).thenReturn(frameworksDtos.get(0));

        //WHEN
        List<JavascriptFrameworkDto> result = testedService.findAll();

        //THEN
        assertEquals(frameworksDtos.get(0), result.get(0));

    }

    @Test
    void edit (){
        //GIVEN
        JavascriptFrameworkDto frameworkDto = new JavascriptFrameworkDto(
            "Framework2",
            Collections.singletonList(new VersionDto("00.00.00", null)),
            5
        );

        JavascriptFramework javascriptFrameworkMerged = JavascriptFramework.builder()
            .id(1L)
            .name("Framework2")
            .rating(5)
            .versions(Collections.singletonList(new Version(null, "00.00.00", null)))
            .build();

        JavascriptFramework javascriptFramework = JavascriptFramework.builder()
            .id(1L)
            .name("Framework")
            .rating(1)
            .versions(Collections.emptyList())
            .build();

        JavascriptFrameworkDto javascriptFrameworkMergedDto = new JavascriptFrameworkDto(
            "Framework",
            Collections.singletonList(new VersionDto("00.00.00", null)),
            5
        );

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(javascriptFramework));
        Mockito.when(mapper.mapToEntity(frameworkDto)).thenReturn(javascriptFrameworkMerged);
        Mockito.when(repository.save(any())).thenReturn(javascriptFrameworkMerged);
        Mockito.when(mapper.mapToDto(javascriptFrameworkMerged)).thenReturn(javascriptFrameworkMergedDto);

        //WHEN
        JavascriptFrameworkDto result = testedService.edit(frameworkDto, 1L);

        //THEN
        assertEquals(5, result.rating());
        assertEquals("00.00.00", result.versions().get(0).versionNumber());

    }

}
