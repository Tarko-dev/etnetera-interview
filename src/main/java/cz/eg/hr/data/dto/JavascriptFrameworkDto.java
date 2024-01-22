package cz.eg.hr.data.dto;

import jakarta.validation.constraints.*;
import java.util.List;


public record JavascriptFrameworkDto(
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 50 characters")
    String name,

    List<VersionDto> versions,

    @Min(value = 0, message = "Rating must be a non-negative integer")
    @Max(value = 5, message = "Rating must be between 0 and 5")
    int rating
) {
}
