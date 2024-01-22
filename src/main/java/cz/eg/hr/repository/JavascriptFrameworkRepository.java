package cz.eg.hr.repository;

import cz.eg.hr.data.entity.JavascriptFramework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JavascriptFrameworkRepository extends JpaRepository<JavascriptFramework, Long> {
}
