/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.pp.pcoder.adat;

/**
 *
 * @author patakip
 */
public class HEX {

    /**
     * Hexadecimális adatot tartalmazó String --> Byte tömb
     *
     * @param adatBe
     * @return
     */
    public static byte[] hexStringToByteArray(String adatBe) {
        int len = adatBe.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(adatBe.charAt(i), 16) << 4)
                    + Character.digit(adatBe.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * Hexadecimális adatot tartalmazó String --> Byte
     *
     * @param adatBe
     * @return
     */
    public static byte hexStringToByte(String adatBe) {
        return (byte) ((Character.digit(adatBe.charAt(0), 16) << 4) + Character.digit(adatBe.charAt(0 + 1), 16));
    }
}
