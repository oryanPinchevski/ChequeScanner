package chequescanner.impl;

import java.io.File;

import chequescanner.exception.ScanClientIdByChequeException;
import interfaces.model.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import chequescanner.interfaces.ChequeScanner;

import com.asprise.ocr.Ocr;

public class ChequeScannerImpl implements ChequeScanner {
    @Value("${clientServiceUrl}")
    private String clientServiceUrl;

    @Value("${pathToPics}")
    private String pathToPics;

    private RestTemplate restTemplate;

    public ChequeScannerImpl(RestTemplate restTemplate) {
        Ocr.setUp();

        this.restTemplate = restTemplate;
    }

    public String getClientNameByCheque(String fileName) {
        String clientId = getClientIdByCheque(fileName);
        String clientName = null;

        Client client = restTemplate.getForObject(clientServiceUrl + clientId, Client.class);
        clientName = client.getName();

        return clientName;
    }

    private String getClientIdByCheque(String fileName) {
        Ocr ocr = new Ocr();
        ocr.startEngine("eng", Ocr.SPEED_FASTEST);

        ClassLoader classLoader = getClass().getClassLoader();
        String filePath = classLoader.getResource(pathToPics + fileName).getFile();

        String clientId = ocr.recognize(new File[] {new File(filePath)}, Ocr.RECOGNIZE_TYPE_ALL, Ocr.OUTPUT_FORMAT_PLAINTEXT);

        ocr.stopEngine();

        if (clientId == null) {
            throw new ScanClientIdByChequeException();
        }

        clientId = clientId.replaceAll("[^0-9a-zA-Z]+", ""); //remove all special chars

        System.out.println("Result: " + clientId);

        return clientId;
    }
}