package chequescanner.impl;

import chequescanner.exception.ScanClientIdByChequeException;
import chequescanner.utils.OcrUtils;
import interfaces.model.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import chequescanner.interfaces.ChequeScanner;

import java.io.InputStream;

public class ChequeScannerImpl implements ChequeScanner {
    @Value("${clientServiceUrl}")
    private String clientServiceUrl;

    private RestTemplate restTemplate;

    public ChequeScannerImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getClientNameByCheque(InputStream dataStream, String fileExt) {
        String clientId = getClientIdByCheque(dataStream, fileExt);

        Client client = restTemplate.getForObject(clientServiceUrl + clientId, Client.class);

        return client.getName();
    }

    private String getClientIdByCheque(InputStream dataStream, String fileExt) {
        String clientId = OcrUtils.scan(dataStream, fileExt);

        if (clientId == null) {
            throw new ScanClientIdByChequeException();
        }

        clientId = clientId.replaceAll("[^0-9a-zA-Z]+", ""); //remove all special chars

        System.out.println("Result: " + clientId);

        return clientId;
    }
}