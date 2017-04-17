package br.com.druid.batch.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="beneficiario")
public class Beneficiario {

	private String codArqconfHistorico;
	private String codLote;
	private String nmBeneficiario;
	private String dtNascimento;
	private String sexo;
	private String cpf;
	private String pisPasep;
	private String nmMae; 
	private String nroCns;
	private String codIdentBenefcOper;
	private String codTitular;
	private String cco;
	private String dnv;
	private String endResProf;
	private String logradouro;
	private String nroLogradouro;
	private String complementoLogradouro;
	private String bairro;
	private String codMunicipioLogradouro;
	private String cep;
	private String indicadorBrasilExterior;
	private String codMunicipioProfissional;
	private String rps;
	private String spca;
	private String nroPlanOrigRps;
	private String dtContratacao;
	private String relacaoDependencia;
	private String cpt;
	private String ipc;
	private String cnpj;
	private String cei;
	private String dtCancelamento;
	private String codMotivoCancelamento;
	
	
	public Beneficiario() {}
	
	public Beneficiario(String codArqconfHistorico, String codLote, String nmBeneficiario, String dtNascimento,
			String sexo, String cpf, String pisPasep, String nmMae, String nroCns, String codIdentBenefcOper,
			String codTitular, String cco, String dnv, String endResProf, String logradouro, String nroLogradouro,
			String complementoLogradouro, String bairro, String codMunicipioLogradouro, String cep,
			String indicadorBrasilExterior, String codMunicipioProfissional, String rps, String spca,
			String nroPlanOrigRps, String dtContratacao, String relacaoDependencia, String cpt, String ipc, String cnpj,
			String cei, String dtCancelamento, String codMotivoCancelamento) {
		super();
		this.codArqconfHistorico = codArqconfHistorico;
		this.codLote = codLote;
		this.nmBeneficiario = nmBeneficiario;
		this.dtNascimento = dtNascimento;
		this.sexo = sexo;
		this.cpf = cpf;
		this.pisPasep = pisPasep;
		this.nmMae = nmMae;
		this.nroCns = nroCns;
		this.codIdentBenefcOper = codIdentBenefcOper;
		this.codTitular = codTitular;
		this.cco = cco;
		this.dnv = dnv;
		this.endResProf = endResProf;
		this.logradouro = logradouro;
		this.nroLogradouro = nroLogradouro;
		this.complementoLogradouro = complementoLogradouro;
		this.bairro = bairro;
		this.codMunicipioLogradouro = codMunicipioLogradouro;
		this.cep = cep;
		this.indicadorBrasilExterior = indicadorBrasilExterior;
		this.codMunicipioProfissional = codMunicipioProfissional;
		this.rps = rps;
		this.spca = spca;
		this.nroPlanOrigRps = nroPlanOrigRps;
		this.dtContratacao = dtContratacao;
		this.relacaoDependencia = relacaoDependencia;
		this.cpt = cpt;
		this.ipc = ipc;
		this.cnpj = cnpj;
		this.cei = cei;
		this.dtCancelamento = dtCancelamento;
		this.codMotivoCancelamento = codMotivoCancelamento;
	}
	public String getCodArqconfHistorico() {
		return codArqconfHistorico;
	}
	public void setCodArqconfHistorico(String codArqconfHistorico) {
		this.codArqconfHistorico = codArqconfHistorico;
	}
	public String getCodLote() {
		return codLote;
	}
	public void setCodLote(String codLote) {
		this.codLote = codLote;
	}
	public String getNmBeneficiario() {
		return nmBeneficiario;
	}
	public void setNmBeneficiario(String nmBeneficiario) {
		this.nmBeneficiario = nmBeneficiario;
	}
	public String getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getPisPasep() {
		return pisPasep;
	}
	public void setPisPasep(String pisPasep) {
		this.pisPasep = pisPasep;
	}
	public String getNmMae() {
		return nmMae;
	}
	public void setNmMae(String nmMae) {
		this.nmMae = nmMae;
	}
	public String getNroCns() {
		return nroCns;
	}
	public void setNroCns(String nroCns) {
		this.nroCns = nroCns;
	}
	public String getCodIdentBenefcOper() {
		return codIdentBenefcOper;
	}
	public void setCodIdentBenefcOper(String codIdentBenefcOper) {
		this.codIdentBenefcOper = codIdentBenefcOper;
	}
	public String getCodTitular() {
		return codTitular;
	}
	public void setCodTitular(String codTitular) {
		this.codTitular = codTitular;
	}
	public String getCco() {
		return cco;
	}
	public void setCco(String cco) {
		this.cco = cco;
	}
	public String getDnv() {
		return dnv;
	}
	public void setDnv(String dnv) {
		this.dnv = dnv;
	}
	public String getEndResProf() {
		return endResProf;
	}
	public void setEndResProf(String endResProf) {
		this.endResProf = endResProf;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNroLogradouro() {
		return nroLogradouro;
	}
	public void setNroLogradouro(String nroLogradouro) {
		this.nroLogradouro = nroLogradouro;
	}
	public String getComplementoLogradouro() {
		return complementoLogradouro;
	}
	public void setComplementoLogradouro(String complementoLogradouro) {
		this.complementoLogradouro = complementoLogradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCodMunicipioLogradouro() {
		return codMunicipioLogradouro;
	}
	public void setCodMunicipioLogradouro(String codMunicipioLogradouro) {
		this.codMunicipioLogradouro = codMunicipioLogradouro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getIndicadorBrasilExterior() {
		return indicadorBrasilExterior;
	}
	public void setIndicadorBrasilExterior(String indicadorBrasilExterior) {
		this.indicadorBrasilExterior = indicadorBrasilExterior;
	}
	public String getCodMunicipioProfissional() {
		return codMunicipioProfissional;
	}
	public void setCodMunicipioProfissional(String codMunicipioProfissional) {
		this.codMunicipioProfissional = codMunicipioProfissional;
	}
	public String getRps() {
		return rps;
	}
	public void setRps(String rps) {
		this.rps = rps;
	}
	public String getSpca() {
		return spca;
	}
	public void setSpca(String spca) {
		this.spca = spca;
	}
	public String getNroPlanOrigRps() {
		return nroPlanOrigRps;
	}
	public void setNroPlanOrigRps(String nroPlanOrigRps) {
		this.nroPlanOrigRps = nroPlanOrigRps;
	}
	public String getDtContratacao() {
		return dtContratacao;
	}
	public void setDtContratacao(String dtContratacao) {
		this.dtContratacao = dtContratacao;
	}
	public String getRelacaoDependencia() {
		return relacaoDependencia;
	}
	public void setRelacaoDependencia(String relacaoDependencia) {
		this.relacaoDependencia = relacaoDependencia;
	}
	public String getCpt() {
		return cpt;
	}
	public void setCpt(String cpt) {
		this.cpt = cpt;
	}
	public String getIpc() {
		return ipc;
	}
	public void setIpc(String ipc) {
		this.ipc = ipc;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getCei() {
		return cei;
	}
	public void setCei(String cei) {
		this.cei = cei;
	}
	public String getDtCancelamento() {
		return dtCancelamento;
	}
	public void setDtCancelamento(String dtCancelamento) {
		this.dtCancelamento = dtCancelamento;
	}
	public String getCodMotivoCancelamento() {
		return codMotivoCancelamento;
	}
	public void setCodMotivoCancelamento(String codMotivoCancelamento) {
		this.codMotivoCancelamento = codMotivoCancelamento;
	}
	
	
	
	
	
	
}
