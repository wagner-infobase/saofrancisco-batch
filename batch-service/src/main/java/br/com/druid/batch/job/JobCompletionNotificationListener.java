package br.com.druid.batch.job;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import br.com.druid.batch.model.Arqconf;
import br.com.druid.batch.model.Beneficiario;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");
			
			List<Arqconf> results = jdbcTemplate.query("SELECT nm_beneficiario FROM arqconf", new RowMapper<Arqconf>() {
				@Override
				public Arqconf mapRow(ResultSet rs, int row) throws SQLException {
					log.info("Found <" + rs.getString(1) + "> in the database.");
					List<Beneficiario> beneficiarios = new ArrayList<Beneficiario>();
					Beneficiario bene = new Beneficiario();
					bene.setNmBeneficiario(rs.getString(1));
					
					Arqconf conf = new Arqconf(beneficiarios);
					
					return conf;
				}
			});

			for(Arqconf conf : results){
				for(Beneficiario bene : conf.getBeneficiarios())
					System.out.println(bene.getNmBeneficiario());
			}
			
			/*List<Person> results = jdbcTemplate.query("SELECT first_name, last_name FROM people", new RowMapper<Person>() {
				@Override
				public Person mapRow(ResultSet rs, int row) throws SQLException {
					return new Person(rs.getString(1), rs.getString(2));
				}
			});

			for (Person person : results) {
				System.out.println(person.getFirstName());
				log.info("Found <" + person + "> in the database.");
			}*/

		}
	}
}
