package br.com.druid.batch.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import oracle.cloudstorage.ftm.CloudStorageClass;
import oracle.cloudstorage.ftm.FileTransferAuth;
import oracle.cloudstorage.ftm.FileTransferManager;
import oracle.cloudstorage.ftm.UploadConfig;
import oracle.cloudstorage.ftm.exception.ClientException;

public class OracleCloudService {


	private final String xmlArqconfGerado;
	private final String nomeArqConfSemExtensao="arqconfimportado";
	private String caminhoDoArquivo;

	public OracleCloudService(String xmlArqconfGerado) {
		this.xmlArqconfGerado=xmlArqconfGerado;
	}

	public void gravar() {
		// Autenticação
		FileTransferAuth auth = new FileTransferAuth("wagner.lima@infobase.com.br", // user
																					// name
				"29QPwoei", // password
				"Storage", // service name
				"https://a433004.storage.oraclecloud.com", // service URL
				"a433004" // identity domain
		);

		FileTransferManager manager = null;
		try {

			// obtem um arquivo com o xml dentro
			File temp = criaArquivoTemporarioComXml();

			manager = FileTransferManager.getDefaultFileTransferManager(auth);
			String containerName = "SF-SIB";
			String objectName = nomeArqConfSemExtensao+".xml";			

			File file = new File(temp.getAbsolutePath());

			UploadConfig uploadConfig = new UploadConfig();
			uploadConfig.setOverwrite(true);
			uploadConfig.setStorageClass(CloudStorageClass.Standard);

			manager.upload(uploadConfig, containerName, objectName, file);

		} catch (ClientException ce) {
			System.out.println("Upload failed. " + ce.getMessage());
			throw ce;
		} catch(IOException ex){
			throw new ClientException(ex);
		}finally {
			if (manager != null) {
				manager.shutdown();
			}
		}
	}

	private File criaArquivoTemporarioComXml() throws IOException{
		
		try {
			
			// create a temp file
			File temp = File.createTempFile(nomeArqConfSemExtensao, ".xml");

			this.setCaminhoDoArquivo(temp.getAbsolutePath());
			// delete temporary file when the program is exited
			temp.deleteOnExit();

			// write to it
			BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
			bw.write(this.xmlArqconfGerado);
			bw.close();
			
			return temp;

		} catch (IOException e) {
			throw e;
		}
	}

	public String getCaminhoDoArquivo() {
		return caminhoDoArquivo;
	}

	public void setCaminhoDoArquivo(String caminhoDoArquivo) {
		this.caminhoDoArquivo = caminhoDoArquivo;
	}
	
	

}
