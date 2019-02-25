package chequescanner.utils;

import com.asprise.ocr.Ocr;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class OcrUtils {
    static {
        Ocr.setUp();
    }

    public static String scan(InputStream dataStream, String fileExt) {
        String res = null;

        try {
            Iterator<ImageReader> it  = ImageIO.getImageReadersByFormatName(fileExt);
            ImageReader imageReader = it.next();

            MemoryCacheImageInputStream memoryCacheImageInputStream = new MemoryCacheImageInputStream(dataStream);
            imageReader.setInput(memoryCacheImageInputStream);

            Ocr ocr = new Ocr();
            ocr.startEngine("eng", Ocr.SPEED_FASTEST);

            res = ocr.recognize(imageReader.read(0), Ocr.RECOGNIZE_TYPE_ALL, Ocr.OUTPUT_FORMAT_PLAINTEXT);

            ocr.stopEngine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }
}
