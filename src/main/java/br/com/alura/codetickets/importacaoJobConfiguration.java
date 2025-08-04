package br.com.alura.codetickets;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class importacaoJobConfiguration {

    @Autowired
    private PlatformTransactionManager transactionManager;
    /**
     * Método que cria um Job do Spring Batch.
     *
     * @param passoIncial o passo inicial do Job
     * @param jobRepository o repositório de Jobs
     * @return um Job configurado com o passo inicial e um incrementador de ID
     */
    @Bean
    public Job job(Step passoIncial, JobRepository jobRepository) {
        return new JobBuilder("gearacao-tickets", jobRepository)
                .start(passoIncial)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step passoIncial(ItemReader<Importacao> reader, ItemWriter<Importacao> writer, JobRepository jobRepository) {
        return new StepBuilder("passo-incial", jobRepository)
                .<Importacao, Importacao>chunk(200, transactionManager)
                .reader(reader)
                .writer(writer)
                .build();
    }
}
