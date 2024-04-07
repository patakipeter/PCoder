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
     * @param str
     */
    public static void intStringToHexString(String str) {
        System.out.println(String.format("%02X", HEX.hexStringToByte(intToHexString(3))));
        //return String.format("%02X", String.valueOf(Integer.parseInt(adatBe, 16)));
    }

    /**
     * Integer --> Bináris String
     * 
     * A String 8 bit-re kiegészítve adja vissza az értéket
     *
     * @param intSzam
     * @return
     */
    public static String intToBinString(int intSzam) {
        String ret = "";
        int hossz = Integer.toBinaryString(intSzam).length();
        int binMaxHossz, lepes = 8;
        while (true) {
            if (lepes - hossz >= 0) {
                binMaxHossz = lepes;
                break;
            }
            lepes += 8;
        }
        String formatum = "%" + binMaxHossz + "s";
        ret += String.format(formatum, Integer.toBinaryString(intSzam)).replace(" ", "0");
        return ret;
    }

    /**
     * Integer --> Bináris String
     *
     * @param intSzam
     * @param kitoltes hány bit-re legyen kiegészítve
     * @return
     */
    public static String intToBinString(int intSzam, int kitoltes) {
        String ret = "";        
        String formatum = "%" + kitoltes + "s";
        ret += String.format(formatum, Integer.toBinaryString(intSzam)).replace(" ", "0");

        return ret;
    }

    /**
     * Integer --> Hexadecimális String (formázott)
     *
     * @param intSzam
     * @return
     */
    public static String intToHexStringFormazott(int intSzam) {
        return String.format("%02X", intToByte(intSzam));
    }

    /**
     * Integer --> Hexadecimális String (formázatlan)
     *
     * @param intSzam
     * @return
     */
    public static String intToHexString(int intSzam) {
        String hexaStr = Integer.toHexString(intSzam);
        int hLen = hexaStr.length();
        if(hLen % 2 !=0){
            return "0" + hexaStr;
        } else{
            return hexaStr;
        }
    }

    /**
     * Integer --> Byte tömb
     *
     * @param intSzam
     * @return
     */
    public static byte[] intToByteArray(int intSzam) {
        return HEX.hexStringToByteArray(intToHexString(intSzam));
    }

    /**
     * Integer --> Byte
     *
     * @param intSzam
     * @return
     */
    public static byte intToByte(int intSzam) {
        return (byte) intSzam;
    }
}
