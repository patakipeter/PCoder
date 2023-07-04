/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.pp.pcoder.ZIP;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/**
 *
 * @author patakip
 */
public class kicsomagol {
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
