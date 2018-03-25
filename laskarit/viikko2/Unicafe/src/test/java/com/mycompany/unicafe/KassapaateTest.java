package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KassapaateTest {

    private Kassapaate paate;
    private Maksukortti kortti;

    @Before
    public void setUp() throws Exception {
        paate = new Kassapaate();
        kortti = new Maksukortti(0);

    }

    @Test
    public void alkuarvo() {
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void EdullinenTasarahalla() {
        assertEquals(0, paate.syoEdullisesti(240));
        assertEquals(100000+240, paate.kassassaRahaa());
    }

    @Test
    public void EdullinenVitosella() {
        assertEquals(260, paate.syoEdullisesti(500));
        assertEquals(100000+240, paate.kassassaRahaa());
    }

    @Test
    public void EdullinenYkkösellä() {
        assertEquals(1, paate.syoEdullisesti(1));
        assertEquals(100000, paate.kassassaRahaa());
    }

    @Test
    public void EdullinenKortilla() {
        assertFalse(paate.syoEdullisesti(kortti));
        kortti.lataaRahaa(240);
        assertTrue(paate.syoEdullisesti(kortti));
    }

    @Test
    public void MaukasTasarahalla() {
        assertEquals(0, paate.syoMaukkaasti(400));
        assertEquals(100000+400, paate.kassassaRahaa());
    }

    @Test
    public void MaukasVitosella() {
        assertEquals(100, paate.syoMaukkaasti((500)));
        assertEquals(100000+400, paate.kassassaRahaa());
    }

    @Test
    public void MaukasYkkösellä() {
        assertEquals(1, paate.syoMaukkaasti(1));
        assertEquals(100000, paate.kassassaRahaa());
    }

    @Test
    public void MaukasKortilla() {
        assertFalse(paate.syoMaukkaasti(kortti));
        kortti.lataaRahaa(400);
        assertTrue(paate.syoMaukkaasti(kortti));
    }

    @Test
    public void lataaRahaaOnnistuneesti() {
        paate.lataaRahaaKortille(kortti, 10);
        assertEquals(100000+10, paate.kassassaRahaa());
        assertEquals(10, kortti.saldo());
    }

    @Test
    public void lataaNegatiivistaRahaa() {
        paate.lataaRahaaKortille(kortti, -10);
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, kortti.saldo());
    }
}