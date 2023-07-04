/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.pp.pcoder.Geo;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author patakip
 */
public class EOV {
    
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
    
    /* 
    EOV számításoknál használt paraméterek.
    Szükségesek a HD72 és WGS84 közötti transzformációhoz!
    */
    private static final List<Double> hd72TOwgs84_p2 = Arrays.asList(6378160.0, 6356774.516, 6378137.0, 6356752.3142);
    private static final List<Double> wgs84TOhd72_p2 = Arrays.asList(6378137.0, 6356752.3142, 6378160.0, 6356774.516);
    private static final List<Double> hd72TOwgs84_p3 = Arrays.asList(52.684, -71.194, -13.975, 0.3120, 0.1063, 0.3729, 0.0000010191);
    private static final List<Double> wgs84TOhd72_p3 = Arrays.asList(-52.6840, 71.1940, 13.9750, -0.3120, -0.1063, -0.3729, -0.0000010191);
    
    /**
     * <h1>EOV --> WGS84</h1>
     * EOV koordinátákból WGS84 kordináta konvertálása.
     *
     * @param EOVY - EOV Y koordináta lebegőpontosan (double)
     * @param EOVX - EOV X koordináta lebegőpontosan (double)
     *
     * @return WGS84 koordináták listája (List&lt;Double&gt; típusként)
     */
    public List<Double> eovTOwgs84(double EOVY, double EOVX) {
        List<Double> hd72 = eovTOhd72(EOVY, EOVX);
        List<Double> bw = bursaWolfTranformacio(hd72.get(0), hd72.get(1), hd72.get(2), hd72TOwgs84_p2, hd72TOwgs84_p3);
        return bw;
    }

    /**
     * <h1>WGS84 --> EOV</h1>
     * WGS84 koordinátából konvertál EOV koordinátákat a bemenen meghatározott
     * paramétereknek megfelelően és visszaed a listát, ami az EOV koordinátákat
     * tartalmazza
     *
     * @param lat - szélesség fokban lebegőpontosan (double)
     * @param lon - hosszúság fokban lebegőpontosan (double)
     * @param h - magasság méterben
     * @return EOV koordináták listája (List&lt;Double&gt; típusként)
     */
    public List<Double> wgs84TOeov(double lat, double lon, double h) {
        List<Double> bw = bursaWolfTranformacio(lat, lon, h, wgs84TOhd72_p2, wgs84TOhd72_p3);
        List<Double> hd72EOV = hd72TOeov(bw.get(0), bw.get(1));

        return hd72EOV;
    }
    
    /**
     * <h1>EOV koordinátákból HD72 vetületre leképezése.</h1>
     *
     * @param EOVY - EOV Y koordináta lebegőpontosan (double)
     * @param EOVX - EOV X koordináta lebegőpontosan (double)
     * @return HD72 koordináták listája (List&lt;Double&gt; típusként)
     */
    private List<Double> eovTOhd72(double EOVY, double EOVX) {
        double x = 180 * 3600 / Math.PI;
        double c = 1.0007197049;
        double d = 19.048571778;
        double e = d * Math.PI / 180;
        double f = 47.1;
        double g = f * Math.PI / 180;
        double h = 6379296.419;
        double i = 47 + (7.0 / 60.0) + (20.0578 / 3600.0);
        double j = i * Math.PI / 180;
        double k = (EOVX - 200000);
        double l = (EOVY - 650000);
        double m = 2.0 * (Math.atan(Math.exp(k / h)) - Math.PI / 4.0);
        double n = l / h;
        double o = 47.0 + (1.0 / 6.0);
        double p = Math.asin(Math.cos(g) * Math.sin(m) + Math.sin(g) * Math.cos(m) * Math.cos(n));
        double q = Math.asin(Math.sin(n) * Math.cos(m) / Math.cos(p));
        double r = 0.822824894115397;
        double s = (p - j) * x;
        double t = o * Math.PI / 180;
        double u = 6378160;
        double v = 6356774.516;
        double w = (u * u - v * v) * Math.cos(t) * Math.cos(t) / v / v;
        double y = Math.pow((1 + w), 0.5);
        double z = 1.5 * w * Math.tan(t) / x;
        double aa = 0.5 * w * (-1 + Math.tan(t) * Math.tan(t) - w + 5 * w * Math.tan(t) * Math.tan(t)) / y / x / x;
        double ab = t + s * y / x - s * s * z / x + s * s * s * aa / x;
        double ac = e + q / c;
        double ad = ab * 180 / Math.PI;
        double ae = ac * 180 / Math.PI;

        return Arrays.asList(ad, ae, 0.0);
    }
    
    /**
     * <h1>HD72 koordinátákból EOV leképezése.</h1>
     *
     * @param LAT - szélesség fokban lebegőpontosan (double)
     * @param LON - hosszúság fokban lebegőpontosan (double)
     * @return HD72 koordináták listája (List&lt;Double&gt; típusként)
     */
    private List<Double> hd72TOeov(Double LAT, Double LON) {
        double c = 6379296.419;
        double d = 0.0818205679;
        double e = 1.0031100083;
        double f = 1.0007197049;
        double g = 47 + 7 / 60 + 20.0578 / 3600;
        double h = 47.1;
        double i = 19.048571778;
        double j = LAT * Math.PI / 180;
        double l = h * Math.PI / 180;
        double m = Math.tan(j / 2 + Math.PI / 4);
        double n = i * Math.PI / 180;
        double k = LON * Math.PI / 180 - n;
        double o = e * Math.pow(m, f) * Math.pow(((1 - d * Math.sin(j)) / (1 + d * Math.sin(j))), f * d / 2);
        double p = 2 * Math.atan(o) - Math.PI / 2;
        double q = k * f;
        double r = Math.asin(Math.cos(l) * Math.sin(p) - Math.sin(l) * Math.cos(p) * Math.cos(q));
        double s = Math.asin(Math.cos(p) * Math.sin(q) / Math.cos(r));
        double t = g * Math.PI / 180;
        double u = c * Math.log(Math.tan(Math.PI / 4 + r / 2)) + 200000;
        double v = c * s + 650000;

        return Arrays.asList(v, u);
    }
    
    /**
     * <h1>Bursa/Wolf Transzformáció</h1>
     *
     * <p>
     * 3 paraméter az eltolási, 3 az elforgatási tag, és a fennmaradó 1 az
     * alapelipszoidok közötti nagyítási tényezõ. </p>
     *
     * @param FI - fi érték fokban lebegőpontosan (double)
     * @param LAMBDA - labda értéke fokban lebegőpontosan (double)
     * @param MAGASSÁG - magasság méterben lebegőpontosan (double)
     * @param p2 paraméterliszta
     * @param p3 paraméterlista
     * @return WGS84 koordináták listája (List&lt;Double&gt; típusként)
     */
    private List<Double> bursaWolfTranformacio(double fi_deg, double la_deg, double h, List<Double> p2, List<Double> p3) {

        double a1 = p2.get(0);
        double b1 = p2.get(1);
        double a2 = p2.get(2);
        double b2 = p2.get(3);

        double dX = p3.get(0);
        double dY = p3.get(1);
        double dZ = p3.get(2);
        double eX = p3.get(3);
        double eY = p3.get(4);
        double eZ = p3.get(5);
        double k = p3.get(6);

        double f = (a1 - b1) / a1;
        double e2 = 2 * f - f * f;
        double fi = fi_deg * Math.PI / 180;
        double la = la_deg * Math.PI / 180;
        double N = a1 / Math.pow(1 - e2 * Math.sin(fi) * Math.sin(fi), 0.5);
        double X = (N + h) * Math.cos(fi) * Math.cos(la);
        double Y = (N + h) * Math.cos(fi) * Math.sin(la);
        double Z = (N * (1 - e2) + h) * Math.sin(fi);
        double Xv = dX + (1 + k) * (X + degToRad(eZ / 3600) * Y - degToRad(eY / 3600) * Z);
        double Yv = dY + (1 + k) * (-X * degToRad(eZ / 3600) + Y + Z * degToRad(eX / 3600));
        double Zv = dZ + (1 + k) * (X * degToRad(eY / 3600) - Y * degToRad(eX / 3600) + Z);

        double f2 = (a2 - b2) / a2;
        double e22 = 2 * f2 - f2 * f2;
        double ev2 = (a2 * a2 - b2 * b2) / b2 / b2;
        double P = Math.pow(Xv * Xv + Yv * Yv, 0.5);
        double theta = Math.atan2(Zv * a2, P * b2);
        double FI2 = Math.atan2(Zv + ev2 * b2 * Math.sin(theta) * Math.sin(theta) * Math.sin(theta), P - e22 * a2 * Math.cos(theta) * Math.cos(theta) * Math.cos(theta));
        double LA2 = Math.atan2(Yv, Xv);
        double N2 = a2 / Math.pow(1 - e22 * Math.sin(FI2) * Math.sin(FI2), 0.5);
        double fi2 = radToDeg(FI2);
        double la2 = radToDeg(LA2);
        double h2 = P / Math.cos(FI2) - N2;
        return Arrays.asList(fi2, la2, h2);
    }
}
