/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.pp.pcoder;

/**
 *
 * @author patakip
 */
public class Endian {
    /**
     * Kisendián integer nagyendiánná konvertálása
     *
     * @param adatBe
     * @return
     */
    static byte[] littleIntToBig(int adatBe) {
        int bufferMeret = (String.valueOf(adatBe).length()) / 2;
        byte[] ret = new byte[bufferMeret];
        for (int i = 0; i < bufferMeret; i++) {
            ret[i] = (byte) (adatBe >> 8 * i);
        }
        return ret;
    }
}
