package org.giscience.utils.geogrid.projections;

import org.giscience.utils.geogrid.geometry.FaceCoordinates;
import org.giscience.utils.geogrid.geometry.GeoCoordinates;
import static org.junit.Assert.assertTrue;

import org.giscience.utils.geogrid.generic.Trigonometric;
import org.junit.Test;

/**
 *
 * @author Franz-Benjamin Mocnik
 */
public class ISEAProjectionTest {
    private final double _precision = 1e-9;
    private final int _iterations = 1000000;

    @Test
    public void projectionApartFromPoles() throws Exception {
        ISEAProjection p = new ISEAProjection();
        p.setOrientation(0,0);
        //GeoCoordinates g = new GeoCoordinates(-82.95636854081724, -71.80894832544845);
        GeoCoordinates c = new GeoCoordinates(-45.670508, -2.473789);
        FaceCoordinates c2 = p.sphereToIcosahedron(c);
        GeoCoordinates c3 = p.icosahedronToSphere(c2);
        assertTrue(Math.abs(c3.getLat() - c.getLat()) < this._precision);
        assertTrue((Math.abs(c3.getLon() - c.getLon()) % 360) * Trigonometric.cos(c.getLat()) < this._precision);

        for (int i = 0; i < this._iterations; i++) {
            c = new GeoCoordinates(Math.random() * 179.99 - 89.995, Math.random() * 360);
            c2 = p.sphereToIcosahedron(c);
            c3 = p.icosahedronToSphere(c2);
            assertTrue(Math.abs(c3.getLat() - c.getLat()) < this._precision);
            assertTrue((Math.abs(c3.getLon() - c.getLon()) % 360) * Trigonometric.cos(c.getLat()) < this._precision);
        }
    }
}
