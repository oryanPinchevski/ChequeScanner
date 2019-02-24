package chequescanner.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chequescanner.interfaces.ChequeScanner;

@RestController
@RequestMapping("chequeScanner")
public class ChequeScannerController {
	private ChequeScanner chequeScanner;

    public ChequeScannerController(ChequeScanner chequeScanner) {
       this.chequeScanner = chequeScanner;
    }
	
    @GetMapping(path = "/{fileName}", produces = "application/json")
    public String getClientNameByCheque(@PathVariable String fileName) {
        return chequeScanner.getClientNameByCheque(fileName);
    }
}
