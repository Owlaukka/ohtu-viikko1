package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudenVarastonTilavuusEiOleNegatiivinen() {
        varasto = new Varasto(-2);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudenVarastonTilavuusEiOleNegatiivinenKuormitetulla() {
        varasto = new Varasto(-2, 2);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitettuKonstruktoriAntaaOikeanSaldon() {
        varasto = new Varasto(11, 9);
        assertEquals(9, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitettuKonstruktoriAntaaOikeanTilavuuden() {
        varasto = new Varasto(11, 9);
        assertEquals(11, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudenKuormitetunVarastonSaldoEiOleNegatiivinen() {
        varasto = new Varasto(9, -5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaEiOleYliTaytetty() {
        varasto = new Varasto(9, 12);
        assertEquals(9, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoonLisaysNegatiivisellaMaarallaEiTeeMitaan() {
        varasto.lisaaVarastoon(-2);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    } 

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void liikaLisaysEiNostaSaldoaYliTilavuuden() {
        varasto.lisaaVarastoon(12);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void yliOttaminenVarastostaEiAsetaSaldoaNegatiiviseksi() {
        varasto.lisaaVarastoon(4);
        varasto.otaVarastosta(5);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttoPalauttaaNollan() {
        assertEquals(0.0, varasto.otaVarastosta(-2),vertailuTarkkuus);
    }
    
    @Test
    public void yliOttaminenVarastostaPalauttaaOikein() {
        varasto.lisaaVarastoon(4);
        double annettuMaara = varasto.otaVarastosta(5);
        
        assertEquals(5, annettuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void merkkijonoEsitysOikein() {
        varasto.lisaaVarastoon(4);
        
        assertEquals(varasto.toString(), "saldo = 4.0, vielä tilaa 6.0");
    }

}