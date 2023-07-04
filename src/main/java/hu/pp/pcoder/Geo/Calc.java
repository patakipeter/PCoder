/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.pp.pcoder.Geo;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author patakip
 */
public class Calc {
    /**
     * Föld sugara méterben
     */
    private int R = 6372800;
    
    
    /**
     * <h1>DEG --> RAD</h1>
     * Fokból radián
     *
     * @param deg - fokban meghatározott lebegőpontos szám (double)
     * @return Radiánban a paraméterként megadott érték (douible)
     */
    private double degToRad(double deg) {
        return deg * (Math.PI / 180.0);
    }

    /**
     * <h1>RAD --> DEG</h1>
     * Radiánból fok
     *
     * @param rad - radiánban a konvertálni kívánt érték (double)
     * @return Fokban a paraméterként megadott érték (douible)
     */
    private double radToDeg(double rad) {
        return rad * (180.0 / Math.PI);
    }
    
    /**
     * <h1>WGS84 Távolság számítás</h1>
     * Két WGS84 koordinátapár távolságát adja vissza. A függvény lebegőpontos
     * értékekkel dolgozik
     *
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return
     */
    public double tavolsag(double lat1, double lon1, double lat2, double lon2) {

        double fi1 = degToRad(lat1);
        double fi2 = degToRad(lat2);
        double deltafi = degToRad(lat2 - lat1);
        double deltalambda = degToRad(lon2 - lon1);

        double a = Math.pow(Math.sin(deltafi / 2), 2) + Math.cos(fi1) * Math.cos(fi2) * Math.pow(Math.sin(deltalambda / 2), 2);

        return 2 * R * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }

    /**
     * <h1>WGS84 Azimut irány meghatározása</h1>
     * Két WGS84 koordinátapár irányát adja vissza. A függvény lebegőpontos
     * értékekkel dolgozik
     *
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return
     */
    public double azimutIrany(double lat1, double lon1, double lat2, double lon2) {

        lat1 = degToRad(lat1);
        lon1 = degToRad(lon1);
        lat2 = degToRad(lat2);
        lon2 = degToRad(lon2);

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double d1 = Math.log(Math.tan(lat2 / 2 + Math.PI / 4) / Math.tan(lat1 / 2 + Math.PI / 4));
        if (Math.abs(dlon) > Math.PI) {
            if (dlon > 0) {
                dlon = (2 * Math.PI - dlon) * -1;
            } else {
                dlon = 2 * Math.PI + dlon;
            }
        }
        return (radToDeg(Math.atan2(dlon, d1)) + 360.0) % 360.0;
    }

    /**
     * <h1>WGS84 Célszámítás</h1>
     * WGS84 célszámítás egy megadott ponthoz képest megadott irányba és
     * távolságba.
     *
     * @param lat
     * @param lon
     * @param tav
     * @param irany
     * @return A cél WGS84 koordináták listája (List&lt;Double&gt; típusként),
     * ahol a 0. elem a Lat és 1. elem a lon.
     */
    public List<Double> celszamitas(double lat, double lon, double tav, double irany) {
        List<Double> celLista = new LinkedList<>();

        double radIrany = degToRad(irany);
        double radLat = degToRad(lat);
        double radLon = degToRad(lon);

        double latCel = Math.asin(Math.sin(radLat) * Math.cos(tav / R) + Math.cos(radLat) * Math.sin(tav / R) * Math.cos(radIrany));
        double lonCel = radLon + Math.atan2(Math.sin(radIrany) * Math.sin(tav / R) * Math.cos(radLat), Math.cos(tav / R) - Math.sin(radLat) * Math.sin(latCel));

        latCel = radToDeg(latCel);
        lonCel = radToDeg(lonCel);

        celLista.add(latCel);
        celLista.add(lonCel);
        return celLista;
    }
}
