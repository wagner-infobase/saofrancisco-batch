package br.com.druid.batch.model;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="arqconf")
public class Arqconf {

	@XmlElementWrapper(name = "beneficiarios")
    @XmlElement(name = "beneficiario")List<Beneficiario> beneficiarios = new ArrayList<>();

	public Arqconf() {}
	
	public Arqconf(List<Beneficiario> beneficiarios) {
		super();
		this.beneficiarios = beneficiarios;
	}

	public List<Beneficiario> getBeneficiarios() {
		return beneficiarios;
	}
}
