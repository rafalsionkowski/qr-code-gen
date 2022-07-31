package pl.rafalsionkowski.qrcode;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;


import org.springframework.stereotype.Service;

@Service

public class QrCodeService {

    private int height = 100;
    private int width = 100;
    private String fileName;


    public void createQR(String data) throws IOException, WriterException {

        String path = "src/main/resources/META-INF/resources/qrCodesTemp/testWithApi.png";
        String charset = "UTF-8";

        Map<EncodeHintType, ErrorCorrectionLevel> hashMap
                = new HashMap<EncodeHintType,
                ErrorCorrectionLevel>();

        hashMap.put(EncodeHintType.ERROR_CORRECTION,
                ErrorCorrectionLevel.L);

        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(data.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, width, height);

        MatrixToImageWriter.writeToFile(
                matrix,
                path.substring(path.lastIndexOf('.') + 1),
                new File(path));
    }

    public static void main(String[] args) throws IOException, WriterException {
        QrCodeService qrCodeService = new QrCodeService();
        qrCodeService.createQR("dasdadsdas");
    }


}
