package chequescanner.interfaces;

import java.io.InputStream;

public interface ChequeScanner {
	String getClientNameByCheque(InputStream dataStream, String fileExt);
}
