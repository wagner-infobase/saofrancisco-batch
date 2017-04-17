package br.com.druid.batch.job;

import java.text.ParseException;

import javax.sql.DataSource;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.batch.core.Job;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import br.com.druid.batch.model.Arqconf;


@Configuration
@EnableBatchProcessing
public class FileConfJobConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;
    
    @Bean
    public StaxEventItemReader<Arqconf> readerXML() throws UnexpectedInputException, ParseException, Exception  {
    	
    	//byte[] xmlResource = Files.readAllBytes(new File("src/main/resources/arqconf.xml").toPath()); //PEGAR O BYTE DO PARÂMETRO...
    	
    	StaxEventItemReader<Arqconf> xmlFileReader = new StaxEventItemReader<Arqconf>();
        xmlFileReader.setResource(new ClassPathResource("/arqconf.xml"));
        xmlFileReader.setFragmentRootElementName("arqconf");

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Arqconf.class);

        xmlFileReader.setUnmarshaller(marshaller);
        return xmlFileReader;
    	
    	//EXEMPLO DA DOCUMENTACAO
/*		byte[] xmlResource = Files.readAllBytes(new File("src/main/resources/arqconf.xml").toPath()); //PEGAR O BYTE DO PARÂMETRO...

		StaxEventItemReader<Beneficiario> xmlStaxEventItemReader = new StaxEventItemReader<Beneficiario>();
		Resource resource = new ByteArrayResource(xmlResource);

		Map<String, String> aliases = new HashMap<String, String>();
		aliases.put("arqconf", "domain.Arqconf");
		aliases.put("beneficiario", "domain.Beneficiario");
		XStreamMarshaller unmarshaller = new XStreamMarshaller();
		unmarshaller.setAliases(aliases);
		xmlStaxEventItemReader.setUnmarshaller(unmarshaller);
		xmlStaxEventItemReader.setResource(resource);
		xmlStaxEventItemReader.setFragmentRootElementName("arqconf");
		xmlStaxEventItemReader.open(new ExecutionContext());
		xmlStaxEventItemReader.read();
		
		return xmlStaxEventItemReader;*/
	}

    @Bean
    public MultiLineItemWriter writerXML(DataSource dataSource, JdbcTemplate jdbcTemplate) { 
        return new MultiLineItemWriter(dataSource, jdbcTemplate);
    }
    

    // tag::jobstep[]
    @Bean
    public Job importUserJob( @Qualifier("stepArqConf") Step step1, JobCompletionNotificationListener listener) throws UnexpectedInputException, ParseException, Exception {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(StaxEventItemReader<Arqconf> readerXML, MultiLineItemWriter writerXML) throws UnexpectedInputException, ParseException, Exception {
        return stepBuilderFactory.get("stepArqConf")
                .<Arqconf, Arqconf> chunk(10)
                .reader(readerXML)
                .writer(writerXML)
                .build();
    }
    // end::jobstep[]
}
