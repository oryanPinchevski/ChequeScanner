package chequescanner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ScanClientIdByChequeException extends RuntimeException {
    public ScanClientIdByChequeException() {
        super("Error scan client id from cheque");
    }
}
