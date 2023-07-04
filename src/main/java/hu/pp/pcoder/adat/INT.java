/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.pp.pcoder.adat;

/**
 *
 * @author patakip
 */
public class INT {

    /**
     * Integer String --> Hexadecimális string (formázott)
     *
     * @param adatBe
     */
    public void intStringToHexString(String adatBe) {
        System.out.println(String.format("%02X", HEX.hexStringToByte(intToHexString(3))));
        //return String.format("%02X", String.valueOf(Integer.parseInt(adatBe, 16)));
    }

    /**
     * Integer --> Bináris String
     *
     * @param adatBe
     * @return
     */
    public String intToBinString(int adatBe) {
        String ret = "";
        int hossz = Integer.toBinaryString(adatBe).length();
        int binMaxHossz = 0;
        for (int i = 8; i < 100000; i = i + 8) {
            if (i - hossz >= 0) {
                binMaxHossz = i;
                break;
            }
        }
        String formatum = "%" + binMaxHossz + "s";
        ret += String.format(formatum, Integer.toBinaryString(adatBe)).replace(" ", "0");
        return ret;
    }

    /**
     * Integer --> Hexadecimális String (formázott)
     *
     * @param adatBe
     * @return
     */
    public static String intToHexStringFormazott(int adatBe) {
        return String.format("%02X", intToByte(adatBe));
    }

    /**
     * Integer --> Hexadecimális String (formázatlan)
     *
     * @param adatBe
     * @return
     */
    public static String intToHexString(int adatBe) {
        return Integer.toHexString(adatBe);
    }

    /**
     * Integer --> Byte tömb
     *
     * @param adatBe
     * @return
     */
    static byte[] intToByteArray(int adatBe) {
        return HEX.hexStringToByteArray(intToHexString(adatBe));
    }

    /**
     * Integer --> Byte
     *
     * @param adatBe
     * @return
     */
    static byte intToByte(int adatBe) {
        return (byte) adatBe;
    }
}
