/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.pp.pcoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/**
 *
 * @author patakip
 */
public class ZIP {

    /**
     * String tömörítése byte tömbbe
     * 
     * @param beString
     * @return
     * @throws IOException 
     */
    public byte[] becsomagol(String beString) throws IOException {
        byte[] beByte = beString.getBytes();
        Deflater deflater = new Deflater();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(outputStream, deflater);
        // Bemeneti string tömörítése
        deflaterOutputStream.write(beByte);
        deflaterOutputStream.close();

        return outputStream.toByteArray();
    }
    
    /**
     * Zip-pel tömörített byte kicsomagolása String-ként
     * @param zipByte
     * @return
     * @throws IOException 
     */
    public String kicsomagol(byte[] zipByte) throws IOException{
        Inflater inflater = new Inflater();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(zipByte);
        InflaterInputStream inflaterInputStream = new InflaterInputStream(inputStream, inflater);

        // Tömörített adatok kinyerése
        byte[] buffer = new byte[1024];
        int byteOlvas;
        StringBuilder kicsomagoltTmp = new StringBuilder();

        while ((byteOlvas = inflaterInputStream.read(buffer)) != -1) {
            kicsomagoltTmp.append(new String(buffer, 0, byteOlvas));
        }

        return kicsomagoltTmp.toString();
    }

}
