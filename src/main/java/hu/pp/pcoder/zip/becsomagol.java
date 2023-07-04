/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.pp.pcoder.zip;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

/**
 *
 * @author patakip
 */
public class becsomagol {

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

}
