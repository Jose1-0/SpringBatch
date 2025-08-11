package br.com.alura.codetickets;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

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
    @Bean
    public ItemReader<Importacao> reader() {
        FlatFileItemReader<Importacao> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource("Files/dados.csv"));
        reader.setLinesToSkip(1); // Pula o cabeçalho

        DefaultLineMapper<Importacao> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(";");
        tokenizer.setNames("cpf", "cliente", "nacimento", "evento", "dataEvento", "tipoIngresso", "valor");

        lineMapper.setLineTokenizer(tokenizer);

        BeanWrapperFieldSetMapper<Importacao> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Importacao.class);
        fieldSetMapper.setCustomEditors(Map.of(
                LocalDate.class, new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String text) {
                        setValue(LocalDate.parse(text));
                    }
                }
        ));

        lineMapper.setFieldSetMapper(fieldSetMapper);
        reader.setLineMapper(lineMapper);

        return reader;
    }

    @Bean
    public ItemWriter<Importacao> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Importacao>()
                .dataSource(dataSource)
                .sql(
                        "INSERT INTO importacao (cpf, cliente, nacimento, evento, data_evento, tipo_ingresso, valor, hora_importacao) " +
                                "VALUES (:cpf, :cliente, :nacimento, :evento, :dataEvento, :tipoIngresso, :valor, :horaImportacao)"
                )
                .itemSqlParameterSourceProvider(
                        new BeanPropertyItemSqlParameterSourceProvider<>())
                .build();
    }
}
