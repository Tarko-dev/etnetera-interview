package cz.eg.hr.config;




import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurationContext;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurer;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SearchConfig implements LuceneAnalysisConfigurer {
    @Override
    public void configure(LuceneAnalysisConfigurationContext context) {
        //configuration for full-text searching
        //https://docs.jboss.org/hibernate/stable/search/getting-started/orm/en-US/html_single/index.html#getting-started-analysis
    }
}
