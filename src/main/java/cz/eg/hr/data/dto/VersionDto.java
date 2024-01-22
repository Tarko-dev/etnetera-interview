package cz.eg.hr.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record VersionDto(

    @NotBlank(message = "Version number cannot be empty")
    @Size(min = 5, max = 12)
    @Pattern(regexp = "\\d{1,3}\\.\\d{1,3}\\.\\d{2,}", message = "Version must by in this : \\d+\\.\\d+\\.\\d* format")
    String versionNumber,

    @PastOrPresent(message = "Deprecated date must be past or present date")
    LocalDate deprecatedDate
) {
}
