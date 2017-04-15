package br.com.druid.batch.model;
import java.util.ArrayList;
import java.util.List;

public class Arqconf {

	List<Beneficiario> beneficiarios = new ArrayList<>();

	public Arqconf(List<Beneficiario> beneficiarios) {
		super();
		this.beneficiarios = beneficiarios;
	}

	public List<Beneficiario> getBeneficiarios() {
		return beneficiarios;
	}

	
	
	
}
