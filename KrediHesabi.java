/**
 * Kalıtım - Kredi Hesabı
 * Kullanıcının belirli limit dahilinde kredi kullanmasını sağlar
 */
class KrediHesabi extends Hesap {
    private double krediLimiti;
    private double kullanilanKredi;
    private double faizOrani = 0.02; // %2 aylık faiz

    public KrediHesabi(String sahipAdi, double krediLimiti, String telefon, String adres, String email) {
        super(sahipAdi, 0, telefon, adres, email,"KRE");

        if (krediLimiti < 1000) {
            throw new IllegalArgumentException("Kredi limiti en az 1000 TL olmalıdır!");
        }

        this.krediLimiti = krediLimiti;
        this.kullanilanKredi = 0;
        islemEkle("Kredi hesabı açıldı. Limit: " + krediLimiti + " TL");
    }

    /**
     * Kredi kullanma işlemi
     */
    public void krediKullan(double miktar) {
        if (miktar <= 0) {
            System.out.println("❌ Geçersiz miktar!");
            return;
        }

        if ((kullanilanKredi + miktar) <= krediLimiti) {
            kullanilanKredi += miktar;
            islemEkle("Kredi kullanıldı: +" + miktar + " TL (Toplam borç: " + kullanilanKredi + " TL)");
            System.out.println("✓ " + miktar + " TL kredi kullanıldı.");
            System.out.println("Kullanılan kredi: " + kullanilanKredi + " TL");
            System.out.println("Kalan limit: " + (krediLimiti - kullanilanKredi) + " TL");
        } else {
            System.out.println("❌ Kredi limiti aşıldı!");
            System.out.println("Kalan kullanılabilir limit: " + (krediLimiti - kullanilanKredi) + " TL");
        }
    }

    /**
     * Kredi ödeme işlemi
     */
    public void krediOde(double miktar) {
        if (miktar <= 0) {
            System.out.println("❌ Geçersiz ödeme miktarı!");
            return;
        }

        if (miktar <= kullanilanKredi) {
            kullanilanKredi -= miktar;
            islemEkle("Kredi ödendi: -" + miktar + " TL (Kalan borç: " + kullanilanKredi + " TL)");
            System.out.println("✓ " + miktar + " TL kredi ödendi.");
            System.out.println("Kalan borç: " + kullanilanKredi + " TL");

            if (kullanilanKredi == 0) {
                System.out.println("🎉 Tebrikler! Tüm borcunuzu kapattınız.");
            }
        } else {
            System.out.println("❌ Borcunuzdan fazla ödeme yapılamaz!");
            System.out.println("Toplam borcunuz: " + kullanilanKredi + " TL");
        }
    }

    /**
     * Temel finansal hesaplama - Aylık faiz ekleme
     */
    public void aylikFaizEkle() {
        if (kullanilanKredi > 0) {
            double faiz = kullanilanKredi * faizOrani;
            kullanilanKredi += faiz;
            islemEkle("Aylık faiz eklendi: +" + faiz + " TL (Yeni borç: " + kullanilanKredi + " TL)");
            System.out.println("⚠ Aylık faiz eklendi: " + faiz + " TL");
            System.out.println("Yeni toplam borç: " + kullanilanKredi + " TL");
        } else {
            System.out.println("ℹ Kullanılan kredi olmadığı için faiz eklenmedi.");
        }
    }

    @Override
    public void hesapBilgileriGoster() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║      KREDİ HESABI BİLGİLERİ         ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("Hesap No       : " + getHesapNo());
        System.out.println("Hesap Sahibi   : " + getSahipAdi());
        System.out.println("Telefon        : " + getMusteriTelefon());
        System.out.println("Adres          : " + getMusteriAdres());
        System.out.println("Email          : " + getMusteriEmail());
        System.out.println("Kredi Limiti   : " + krediLimiti + " TL");
        System.out.println("Kullanılan     : " + kullanilanKredi + " TL");
        System.out.println("Kalan Limit    : " + (krediLimiti - kullanilanKredi) + " TL");
        System.out.println("Faiz Oranı     : %" + (faizOrani * 100) + " (Aylık)");
        System.out.println("Kullanım Oranı : %" + String.format("%.1f", (kullanilanKredi / krediLimiti * 100)));

        if (kullanilanKredi > 0) {
            System.out.println("⚠ Bir sonraki ay faiz: " + (kullanilanKredi * faizOrani) + " TL");
        } else {
            System.out.println("✓ Borcunuz bulunmamaktadır.");
        }
        System.out.println("──────────────────────────────────────");
    }

    @Override
    public String getHesapTipi() {
        return "Kredi Hesabı";
    }

    public double getKullanilanKredi() { return kullanilanKredi; }
}