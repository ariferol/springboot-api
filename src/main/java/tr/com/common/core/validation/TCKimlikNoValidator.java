
/**
 * TCKimlikNoValidator, @TCKimlikNo anotasyonu ile işaretlenen alanların doğrulamasını otomatik yapar.
 * Developer'ların ekstra kod yazmasına gerek kalmaz.
 *
 * isValid metodu, TC Kimlik Numarası'nın kurallarını kontrol eder.
 */
package tr.com.common.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author arif.erol
 */
public class TCKimlikNoValidator implements ConstraintValidator<TCKimlikNo, String> {
    @Override
    public boolean isValid(String v, ConstraintValidatorContext c) {

        // 1. Kural: TC Kimlik Numarası null veya boş olamaz ve 11 karakter olmalıdır
        if (v == null || v.trim().isEmpty()) return false;

        String s = v.trim();
        if (s.length() != 11) return false;

        // 2. Kural: Her hanesi bir rakam olmalıdır
        if (!s.matches("\\d{11}")) return false;

        // 3. Kural: İlk hane 0 olamaz
        if (s.charAt(0) == '0') return false;

        // Rakamları diziye aktar
        int[] d = new int[11];
        for (int i = 0; i < 11; i++) {
            d[i] = s.charAt(i) - '0';
        }

        // 4. Kural: ((1+3+5+7+9).basamakların toplamı * 7 - (2+4+6+8).basamakların toplamı) % 10 = 10. basamak
        int sumOdd = d[0] + d[2] + d[4] + d[6] + d[8]; // 1,3,5,7,9. basamaklar (0-indexli)
        int sumEven = d[1] + d[3] + d[5] + d[7];        // 2,4,6,8. basamaklar (0-indexli)
        int check10 = ((sumOdd * 7) - sumEven) % 10;
        if (check10 < 0) check10 += 10;
        if (d[9] != check10) return false;

        // 5. Kural: İlk 10 hanenin toplamı % 10 = 11. basamak
        int sumFirst10 = 0;
        for (int i = 0; i < 10; i++) sumFirst10 += d[i];
        int check11 = sumFirst10 % 10;
        if (d[10] != check11) return false;

        return true;
    }
}
