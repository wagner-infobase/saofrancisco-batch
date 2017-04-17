package br.com.druid.batch.job;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;

import br.com.druid.batch.model.Arqconf;
import br.com.druid.batch.model.Beneficiario;



public class MultiLineItemWriter implements ItemWriter<Arqconf>, ItemStream {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public MultiLineItemWriter(DataSource dataSource, JdbcTemplate jdbcTemplate) {
		this.dataSource = dataSource;
		this.jdbcTemplate = jdbcTemplate;

	}

	@Override
	public void write(List<? extends Arqconf> items) throws Exception {
		
		String SQL = "INSERT INTO arqconf (nm_beneficiario) VALUES (?)";
		List<Object[]> batch = new ArrayList<Object[]>(); 
		 
		for(Arqconf conf : items){
			for(Beneficiario beneficiario : conf.getBeneficiarios()){
				Object[] values = new Object[] {beneficiario.getNmBeneficiario()};
				batch.add(values);
			}
			
		}

		JdbcBatchItemWriter<Beneficiario> databaseItemWriter = new JdbcBatchItemWriter<Beneficiario>();
		databaseItemWriter.setDataSource(dataSource);
		
		jdbcTemplate.batchUpdate(SQL, batch);
		
		databaseItemWriter.setJdbcTemplate(null);;
		databaseItemWriter
				.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Beneficiario>());
		//databaseItemWriter.setSql(SQL);
		// ItemPreparedStatementSetter<Arqconf> valueSetter = new
		// ArqconfPreparedStatementSetter();
		// databaseItemWriter.setItemPreparedStatementSetter(valueSetter);

	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
	}

	@Override
	public void close() throws ItemStreamException {
	}

}
