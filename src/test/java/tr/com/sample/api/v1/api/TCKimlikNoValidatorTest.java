
package tr.com.sample.api.v1.api;

import org.junit.jupiter.api.Test;
import tr.com.common.core.validation.TCKimlikNoValidator;

import static org.junit.jupiter.api.Assertions.*;

public class TCKimlikNoValidatorTest {
    private final TCKimlikNoValidator validator = new TCKimlikNoValidator();

    @Test
    public void testValidTCKimlikNo() {
        // Gerçek algoritmaya uygun geçerli bir TC Kimlik numarası örneği: 10000000146
        assertTrue(validator.isValid("10000000146", null));
    }

    @Test
    public void testInvalidLength() {
        assertFalse(validator.isValid("1234567895", null)); // 10 hane
        assertFalse(validator.isValid("123456789500", null)); // 12 hane
    }

    @Test
    public void testLeadingZero() {
        // İlk hane 0 olamaz
        assertFalse(validator.isValid("02345678950", null));
    }

    @Test
    public void testInvalidChecksum() {
        // Geçerli bir numaranın son hanesi değiştirilmiş hali
        assertFalse(validator.isValid("12345678951", null));
    }

    @Test
    public void testNullOrEmptyNotAllowed() {
        // Null veya boş değer geçersiz olmalı
        assertFalse(validator.isValid(null, null));
        assertFalse(validator.isValid("", null));
    }
}

