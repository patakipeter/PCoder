/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.pp.pcoder.adat;

/**
 *
 * @author patakip
 */
public class BYTE {

    /**
     * ------------------------------------------------------------------- 
     *                          Bájt műveletek
     * -------------------------------------------------------------------
     */
    /**
     * Bájt --> Hexadecimális String (formázott)
     *
     * @param adatBe
     * @return
     */
    public static String byteToHexString(byte adatBe) {
        return String.format("%02X", adatBe);
    }

    /**
     * Bájt tömb --> Hexadecimális String (formázott)
     *
     * @param adatBe
     * @return
     */
    public static String byteArrayToHexString(byte[] adatBe) {
        String ret = "";
        for (byte e : adatBe) {
            ret += (String.format("%02X", e));
        }
        return ret;
    }

    /**
     * Bájt --> Bináris string
     *
     * @param adatBe
     * @return
     */
    public static String byteToBinString(byte adatBe) {
        return String.format("%8s", Integer.toBinaryString(adatBe)).replace(" ", "0");
    }

    /**
     * ------------------------------------------------------------------- 
     *                          Bájt tömb műveletek
     * -------------------------------------------------------------------
     */
    /**
     * Bájt tömb --> Hexadecimális String (formázott)
     *
     * Ha a szóköz igaz, akkor minden HEX bájt után tesz egy szóközt
     *
     * @param adatBe
     * @param szokoz
     * @return
     */
    public static String byteArrayToHexString(byte[] adatBe, boolean szokoz) {
        String ret = "";
        for (byte e : adatBe) {
            ret += szokoz ? (String.format("%02X ", e)) : (String.format("%02X", e));
        }
        return ret;
    }

    /**
     * Bájt tömb --> Bináris String
     *
     * @param adatBe
     * @return
     */
    public static String byteArrayToBinString(byte[] adatBe) {
        String ret = "";
        for (byte e : adatBe) {
            ret += String.format("%8s", Integer.toBinaryString(e)).replace(" ", "0");
        }
        return ret;
    }

    /**
     * Bájt tömbök egyesítése
     *
     * @param tomb1
     * @param tomb2
     * @return
     */
    public static byte[] byteArrayEgyesit(byte[] tomb1, byte[] tomb2) {
        byte[] ret = new byte[tomb1.length + tomb2.length];
        for (int i = 0; i < ret.length; ++i) {
            ret[i] = i < tomb1.length ? tomb1[i] : tomb2[i - tomb1.length];
        }
        return ret;
    }
}
