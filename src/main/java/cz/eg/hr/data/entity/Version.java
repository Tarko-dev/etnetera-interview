package cz.eg.hr.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "versions")
public class Version {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "version_number", nullable = false, length = 12)
    private String versionNumber;

    @Column(name = "deprecated_date")
    private LocalDate deprecatedDate;

}
