package cz.eg.hr.controller;

import cz.eg.hr.data.dto.JavascriptFrameworkDto;
import cz.eg.hr.data.entity.JavascriptFramework;
import cz.eg.hr.service.JavascriptFrameworkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/frameworks")
public class JavascriptFrameworkController {

    private final JavascriptFrameworkService service;

    @Autowired
    public JavascriptFrameworkController(JavascriptFrameworkService service) {
        this.service = service;
    }


    @Operation(summary = "Get framework by id")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200", description = "Framework Removed",
                content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = JavascriptFramework.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Something went wrong")
        })
    @GetMapping("/")
    public List<JavascriptFrameworkDto> frameworks() {
        return service.findAll();
    }


    @Operation(summary = "Add new framework")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200", description = "Framework saved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Something went wrong")
        })
    @PostMapping("/")
    public JavascriptFrameworkDto addFramework(@Valid @RequestBody JavascriptFrameworkDto javascriptFrameworkDto) {
        return service.save(javascriptFrameworkDto);
    }


    @Operation(summary = "Get framework by id")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200", description = "Framework found",
                content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = JavascriptFramework.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Framework not found"),
            @ApiResponse(responseCode = "500", description = "Something went wrong")
        })
    @GetMapping("/{id}")
    public JavascriptFrameworkDto findFramework(@PathVariable Long id) {
        return service.findOne(id);
    }


    @Operation(summary = "Edit framework by id")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200", description = "Framework Edited",
                content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = JavascriptFramework.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Framework not found"),
            @ApiResponse(responseCode = "500", description = "Something went wrong")
        })
    @PutMapping("/{id}")
    public JavascriptFrameworkDto editFramework(@Valid @RequestBody JavascriptFrameworkDto javascriptFrameworkDto, @PathVariable Long id) {
        return  service.edit(javascriptFrameworkDto, id);
    }


    @Operation(summary = "Ful-text search")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200", description = "Result of the query",
                content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = JavascriptFramework.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Something went wrong")
        })
    @GetMapping("/search")
    public List<JavascriptFrameworkDto> searchFrameworks(@RequestParam String prompt) {
        return service.fulltextSearch(prompt);
    }

    @Operation(summary = "Delete framework by id")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200", description = "Framework Removed")
        })
    @DeleteMapping("/{id}")
    public void deleteFramework(@PathVariable Long id) {
        service.delete(id);
    }

}
