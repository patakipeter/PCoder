/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.pp.pcoder.Geo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author patakip
 */
public class Konvert {

    /**
     * Föld sugara méterben
     */
    int R = 6372800;

    /**
     * <h1>DEG --> RAD</h1>
     * Fokból radián
     *
     * @param deg - fokban meghatározott lebegőpontos szám (double)
     * @return Radiánban a paraméterként megadott érték (douible)
     */
    public double degToRad(double deg) {
        return deg * (Math.PI / 180.0);
    }

    /**
     * <h1>RAD --> DEG</h1>
     * Radiánból fok
     *
     * @param rad - radiánban a konvertálni kívánt érték (double)
     * @return Fokban a paraméterként megadott érték (douible)
     */
    public double radToDeg(double rad) {
        return rad * (180.0 / Math.PI);
    }

    

    /**
     * <h1>WGS84 DMS --> DD</h1>
     * DMS-ből tizedes WGS84
     *
     * @param egtaj
     * @param d
     * @param min
     * @param sec
     * @return
     */
    public double dmsToDd(char egtaj, int d, int min, double sec) {
        switch (egtaj) {
            case 'N':
                return d + (min / 60.0) + (sec / 3600.0);
            case 'E':
                return d + (min / 60.0) + (sec / 3600.0);
            case 'W':
                return -1 * (d + (min / 60.0) + (sec / 3600.0));
            case 'S':
                return -1 * (d + (min / 60.0) + (sec / 3600.0));
            default:
                throw new AssertionError();
        }
    }

    /**
     * <h1>WGS84 DMM --> DD</h1>
     * DDM-ből tizedes WGS84
     *
     * @param egtaj - Az égtáj stringként (N, S, W, E)
     * @param d - fok egész számként (int)
     * @param min - perc lebegőpontosan (double)
     * @return - Decimális WGS84 formátum (double)
     */
    public double dmmToDd(char egtaj, int d, double min) {
        switch (egtaj) {
            case 'N':
                return d + (min / 60.0);
            case 'E':
                return d + (min / 60.0);
            case 'W':
                return -1 * (d + (min / 60.0));
            case 'S':
                return -1 * (d + (min / 60.0));
            default:
                throw new AssertionError();
        }
    }

    /**
     * <h1>WGS84 DD --> DMS</h1>
     * Tizedes WGS84-ből DMS
     *
     * @param lat
     * @param lon
     * @return Két tömböt ad vissza egy tömb elemeként. A 0. tömb a lat-ból, az
     * 1. a lon-ból konvertált formátum. A belső tömbök felépítése
     * egységesen:<br>
     * 0: égtáj (String)<br>
     * 1: fok (int)<br>
     * 2: perc (int)<br>
     * 3: másodperc (double)
     */
    public List<List> ddToDms(double lat, double lon) {
        List latKonvertalt = new LinkedList<>();
        List lonKonvertalt = new LinkedList<>();

        latKonvertalt.add((lat >= 0.0) ? "N" : "S");
        lat = Math.abs(lat);
        int latD = (int) lat;
        latKonvertalt.add(latD);
        int latM = (int) ((lat - (int) lat) * 60);
        latKonvertalt.add(latM);
        double latS = (lat - latD - latM / 60.0) * 3600;
        latKonvertalt.add(latS);
                
        lonKonvertalt.add((lon >= 0.0) ? "E" : "W");
        lon = Math.abs(lon);
        int lonD = (int) lon;
        lonKonvertalt.add(lonD);
        int lonM = (int) ((lon - (int) lon) * 60);
        lonKonvertalt.add(lonM);
        double lonS = (lon - lonD - lonM / 60.0) * 3600;
        lonKonvertalt.add(lonS);

        return Arrays.asList(latKonvertalt, lonKonvertalt);
    }

    /**
     * <h1>WGS84 DD --> DDM</h1>
     * Tizedes WGS84-ből DDM
     *
     * @param lat
     * @param lon
     * @return Két tömböt ad vissza egy tömb elemeként. A 0. tömb a lat-ból, az
     * 1. a lon-ból konvertált formátum. A belső tömbök felépítése
     * egységesen:<br>
     * 0: égtáj (String)<br>
     * 1: fok (int)<br>
     * 2: perc (double)
     */
    public List<List> ddToDdm(double lat, double lon) {
        List latKonvertalt = new LinkedList<>();
        List lonKonvertalt = new LinkedList<>();

        latKonvertalt.add((lat >= 0.0) ? "N" : "S");
        lat = Math.abs(lat);
        int latD = (int) lat;
        latKonvertalt.add(latD);
        double latDM = ((lat - latD) * 60.0);
        latKonvertalt.add(latDM);
        
        lonKonvertalt.add((lon >= 0.0) ? "E" : "W");
        lon = Math.abs(lon);
        int lonD = (int) lon;
        lonKonvertalt.add(lonD);
        double lonDM = ((lon - lonD) * 60.0);
        lonKonvertalt.add(lonDM);

        return Arrays.asList(latKonvertalt, lonKonvertalt);
    }
    
    /**
     * <h1>MonitorPlus --> DD</h1>
     * A MonitorPlus program által használt formátum átalakítása decimális
     * WGS84 formátumra
     * 
     * @param forras
     * @return Egy tomb, aminek két eleme a decimális szélesség és hosszúság
     */
    public List monitorplusToWGS84(String forras){
        List konvertalt = new LinkedList();
        String[] adat = forras.split(" ");

        int latD, latM, lonD = 0, lonM = 0;
        double latS, lonS = 0;
        char latEg, lonEg = 0;

        latD = Integer.parseInt(adat[0].substring(0, 2));
        latEg = adat[0].charAt(2);
        latM = Integer.parseInt(adat[0].substring(3, 5));
        latS = Double.parseDouble(adat[0].substring(5));

        // Meg kell nézni, hogy melyik karakter mutatja az égtájat, hogy biztos jól legyenek az értékek kiolvasva
        if (Character.isAlphabetic(adat[1].charAt(2))){
            lonD = Integer.parseInt(adat[1].substring(0, 2));
            lonEg = adat[1].charAt(2);
            lonM = Integer.parseInt(adat[1].substring(3, 5));
            lonS = Double.parseDouble(adat[1].substring(5));
        } else if (Character.isAlphabetic(adat[1].charAt(3))){
            lonD = Integer.parseInt(adat[1].substring(0, 3));
            lonEg = adat[1].charAt(3);
            lonM = Integer.parseInt(adat[1].substring(4, 6));
            lonS = Double.parseDouble(adat[1].substring(6));
        }
        
        konvertalt.add(dmsToDd(latEg, latD, latM, latS));
        konvertalt.add(dmsToDd(lonEg, lonD, lonM, lonS));
        
        return konvertalt;
    }
    
    
    
    /**
     * Kerekítés lebegőpontos számok esetén
     * 
     * @param szamErtek
     * @param tizedeskSzama
     * @return 
     */
    public static double kerekites(double szamErtek, int tizedeskSzama) {
        if (tizedeskSzama < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, tizedeskSzama);
        szamErtek = szamErtek * factor;
        long tmp = Math.round(szamErtek);
        return (double) tmp / factor;
    }
 

}
