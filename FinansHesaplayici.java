/**
 * Finansal hesaplama araçları sağlar
 * Basit faiz, bileşik faiz, kredi ödemeleri hesaplamaları yapar
 */
class FinansHesaplayici {

    /**
     * Basit faiz hesaplama
     * Formül: Basit Faiz = Ana Para × Oran × Süre
     */
    public static double basitFaizHesapla(double anaPara, double yillikOran, double yil) {
        if (anaPara <= 0 || yillikOran < 0 || yil < 0) {
            System.out.println("❌ Hata: Geçersiz giriş değerleri!");
            return 0;
        }
        return anaPara * (yillikOran / 100) * yil;
    }

    /**
     * Bileşik faiz hesaplama
     * Formül: A = P(1 + r/n)^(nt)
     */
    public static double bilesikFaizHesapla(double anaPara, double yillikOran, double yil, int yildaBilesikSayisi) {
        if (anaPara <= 0 || yillikOran < 0 || yil < 0 || yildaBilesikSayisi <= 0) {
            System.out.println("❌ Hata: Geçersiz giriş değerleri!");
            return 0;
        }

        double oran = yillikOran / 100;
        double tutar = anaPara * Math.pow(1 + (oran / yildaBilesikSayisi), yildaBilesikSayisi * yil);
        return tutar;
    }

    /**
     * Aylık kredi ödemesi hesaplama
     * Formül: M = P[r(1+r)^n]/[(1+r)^n-1]
     */
    public static double aylikKrediOdemesiHesapla(double krediTutari, double yillikOran, double yil) {
        if (krediTutari <= 0 || yillikOran < 0 || yil <= 0) {
            System.out.println("❌ Hata: Geçersiz giriş değerleri!");
            return 0;
        }

        double aylikOran = (yillikOran / 100) / 12;
        int odemeSayisi = (int)(yil * 12);

        if (aylikOran == 0) {
            return krediTutari / odemeSayisi;
        }

        double aylikOdeme = krediTutari * (aylikOran * Math.pow(1 + aylikOran, odemeSayisi)) /
                (Math.pow(1 + aylikOran, odemeSayisi) - 1);
        return aylikOdeme;
    }

    /**
     * Gelecek değer hesaplama (düzenli tasarruf için)
     */
    public static double gelecekDegerHesapla(double aylikYatirim, double yillikOran, double yil) {
        if (aylikYatirim <= 0 || yillikOran < 0 || yil <= 0) {
            System.out.println("❌ Hata: Geçersiz giriş değerleri!");
            return 0;
        }

        double aylikOran = (yillikOran / 100) / 12;
        int ay = (int)(yil * 12);

        if (aylikOran == 0) {
            return aylikYatirim * ay;
        }

        double gelecekDeger = aylikYatirim * ((Math.pow(1 + aylikOran, ay) - 1) / aylikOran);
        return gelecekDeger;
    }

    /**
     * Yatırım getirisi (ROI) hesaplama
     * Formül: ROI = ((Nihai Değer - İlk Yatırım) / İlk Yatırım) × 100
     */
    public static double roiHesapla(double ilkYatirim, double nihaiDeger) {
        if (ilkYatirim <= 0) {
            System.out.println("❌ Hata: İlk yatırım pozitif olmalıdır!");
            return 0;
        }
        return ((nihaiDeger - ilkYatirim) / ilkYatirim) * 100;
    }
}