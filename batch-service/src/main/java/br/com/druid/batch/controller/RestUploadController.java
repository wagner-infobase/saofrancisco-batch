package br.com.druid.batch.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.thoughtworks.xstream.XStream;

import br.com.druid.batch.model.Arqconf;
import br.com.druid.batch.model.Beneficiario;
import br.com.druid.batch.model.FileConfHistory;
import br.com.druid.batch.model.UploadModel;
import br.com.druid.batch.service.OracleCloudService;


@RestController
public class RestUploadController {

    private final Logger logger = LoggerFactory.getLogger(RestUploadController.class);

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C://temp//";

    //Single file upload
    @PostMapping("/api/upload")
    // If not @RestController, uncomment this
    //@ResponseBody
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile uploadfile){

        logger.debug("Single file upload!");
        //System.out.println("Single file upload!");

        if (uploadfile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {

            saveUploadedFiles(Arrays.asList(uploadfile));

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - " +
                uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

    }

    // Multiple file upload
    @PostMapping("/api/upload/multi")
    public ResponseEntity<?> uploadFileMulti(
            @RequestParam("extraField") String extraField,
            @RequestParam("files") MultipartFile[] uploadfiles) {

        logger.debug("Multiple file upload!");
        //System.out.println("Multiple file upload!");
        
        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if (StringUtils.isEmpty(uploadedFileName)) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {

            saveUploadedFiles(Arrays.asList(uploadfiles));

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - "
                + uploadedFileName, HttpStatus.OK);

    }

    // maps html form to a Model
    @PostMapping("/api/upload/multi/model")
    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute UploadModel model) {

        //logger.debug("Multiple file upload! With UploadModel");
        System.out.println("Multiple file upload! With UploadModel");
        
        try {

            saveUploadedFiles(Arrays.asList(model.getFiles()));

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);

    }

    //save file
    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }

            byte[] bytes = file.getBytes();
            //Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            //Files.write(path, bytes);
            
            String xmlRecebido = new String(bytes);
            
            //salva no oracle cloud
            OracleCloudService oraService = new OracleCloudService(xmlRecebido);
            oraService.gravar();
            
            
            
            
            //convertendo a strnig xml para um objeto.
            XStream xstream = new XStream();
            xstream.setClassLoader(Thread.currentThread().getContextClassLoader());
            xstream.alias("arqconf", Arqconf.class);
    		xstream.alias("beneficiario", Beneficiario.class);    		
    		
    		File xmlFile = new File(oraService.getCaminhoDoArquivo());
    		    		
    		Arqconf arqconf = (Arqconf) xstream.fromXML(new FileInputStream(xmlFile));            
          
    		RestTemplate restTemplate = new RestTemplate();
    		
            for(Beneficiario beneficiario : arqconf.getBeneficiarios())
            {
            	System.out.println(beneficiario.getNmBeneficiario());
            	
            	
            	FileConfHistory arqConf = new FileConfHistory();
    			arqConf.setBenefeciaryName(beneficiario.getNmBeneficiario());
    			
    			FileConfHistory fileConfHistoryList = restTemplate.postForObject(
    					"http://127.0.0.1:8094/fileConfHistoryRepository", arqConf, FileConfHistory.class);
            }
            

            
           
            
        }

    }
}
