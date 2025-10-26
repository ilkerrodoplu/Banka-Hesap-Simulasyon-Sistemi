/**
 * Kalıtım (Inheritance) - Hesap sınıfından türetilmiş Vadesiz Hesap
 * Normal çek hesabı gibi çalışır, ek hesap limiti vardır
 */
class VadesizHesap extends Hesap {
    private double ekHesapLimiti;

    public VadesizHesap(String sahipAdi, double ilkBakiye, String telefon, String adres, String email) {
        super(sahipAdi, ilkBakiye, telefon, adres, email,"VDS");
        this.ekHesapLimiti = 1000;
        islemEkle("Vadesiz hesap açıldı. Ek hesap limiti: " + ekHesapLimiti + " TL");
    }


    /**
     * Çok Biçimlilik (Polymorphism) - Override edilmiş para çekme
     */
    @Override
    public boolean paraCek(double miktar) {
        if (miktar <= 0) {
            System.out.println("❌ Geçersiz miktar!");
            return false;
        }

        double kullanilabilir = getBakiye() + ekHesapLimiti;

        if (miktar <= kullanilabilir) {
            setBakiye(getBakiye() - miktar);
            islemEkle("Para çekildi: -" + miktar + " TL (Bakiye: " + getBakiye() + " TL)");
            System.out.println("✓ " + miktar + " TL çekildi. Kalan bakiye: " + getBakiye() + " TL");

            if (getBakiye() < 0) {
                System.out.println("⚠ Uyarı: Ek hesap kullanılıyor! Borç: " + Math.abs(getBakiye()) + " TL");
                islemEkle("Ek hesap kullanıldı. Borç: " + Math.abs(getBakiye()) + " TL");
            }
            return true;
        } else {
            System.out.println("❌ Limit aşıldı! Maksimum çekilebilecek: " + kullanilabilir + " TL");
            return false;
        }
    }

    @Override
    public void hesapBilgileriGoster() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║     VADESİZ HESAP BİLGİLERİ       ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("Hesap No       : " + getHesapNo());
        System.out.println("Hesap Sahibi   : " + getSahipAdi());
        System.out.println("Telefon        : " + getMusteriTelefon());
        System.out.println("Adres          : " + getMusteriAdres());
        System.out.println("Email          : " + getMusteriEmail());
        System.out.println("Bakiye         : " + getBakiye() + " TL");
        System.out.println("Ek Hesap Limiti: " + ekHesapLimiti + " TL");
        System.out.println("Kullanılabilir : " + (getBakiye() + ekHesapLimiti) + " TL");

        if (getBakiye() < 0) {
            System.out.println("⚠ Ek Hesap Borcu: " + Math.abs(getBakiye()) + " TL");
        }
        System.out.println("────────────────────────────────────");
    }

    @Override
    public String getHesapTipi() {
        return "Vadesiz Hesap";
    }
}
