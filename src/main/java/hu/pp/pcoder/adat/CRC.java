/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.pp.pcoder.adat;

/**
 *
 * @author patakip
 */
public class CRC {
    /**
     * CRC16-IBM szabványú CRC kód számítása
     *
     * @param adatBe - byte[] típusú adathalmaz
     * @param alapErtekHex - az első XOR művelet értéke hexadecimális integer
     * @param xorPolinomHex - a polinom hexadecimális értéke
     * @param kisendian - boolean, kisendián bájtsorrendű legyen-e a kimenet
     * @return
     */
    public static byte[] crc16IBM(byte[] adatBe, int alapErtekHex, int xorPolinomHex, boolean kisendian) {
        int maszk = 0x01;
        int crc = alapErtekHex;
        int tmp;
        for (byte by : adatBe) {
            tmp = crc ^ (by & 0xff);    // 0xff unsigneddé teszi
            for (int i = 0; i < 8; i++) {
                if ((tmp & maszk) == 1) {
                    tmp = tmp >> 1;
                    tmp = tmp ^ xorPolinomHex;
                } else {
                    tmp = tmp >> 1;
                }
            }
            crc = tmp;
        }
        if (kisendian) {
            return INT.intToByteArray(crc);
        } else {
            return Endian.littleIntToBig(crc);
        }
    }

    /**
     * CRC16-CCIT szabványú CRC kód számítása
     *
     * @param adatBe - byte[] típusú adathalmaz
     * @param alapErtekHex - az első XOR művelet értéke hexadecimális integer
     * @param xorPolinomHex - a polinom hexadecimális értéke
     * @param kisendian - boolean, kisendián bájtsorrendű legyen-e a kimenet
     * @return
     */
    public static byte[] crc16CCIT(byte[] adatBe, int alapErtekHex, int xorPolinomHex, boolean kisendian) {
        //int ret = 0;
        int crc = alapErtekHex;

        for (int b : adatBe) {
            for (int i = 0; i < 8; i++) {
                boolean bit = ((b >> (7 - i) & 1) == 1);
                boolean c15 = ((crc >> 15 & 1) == 1);
                crc <<= 1;
                if (c15 ^ bit) {
                    crc ^= xorPolinomHex;
                }
            }
        }

        crc &= alapErtekHex;
        System.out.println("   " + crc + " " + INT.intToHexString(crc));

        if (kisendian) {
            return INT.intToByteArray(crc);
        } else {
            return Endian.littleIntToBig(crc);
        }

    }
}
