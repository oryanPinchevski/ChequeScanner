package chequescanner.rest;

import chequescanner.interfaces.ChequeScanner;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("chequeScanner")
public class ChequeScannerController {
	private ChequeScanner chequeScanner;

    public ChequeScannerController(ChequeScanner chequeScanner) {
       this.chequeScanner = chequeScanner;
    }
	
    @PostMapping(path = "/")
    public String getClientNameByCheque(@RequestParam("file") MultipartFile multipartFile) {
        String clientName = null;

        try {
            String fileExt = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);

            clientName = chequeScanner.getClientNameByCheque(multipartFile.getInputStream(), fileExt);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return clientName;
    }
}
