import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Soyutlama (Abstraction) - Ana Hesap Sınıfı
 * Tüm hesap tiplerinin ortak özelliklerini ve metodlarını içerir
 */
public abstract class Hesap {
    // Kapsülleme (Encapsulation) - private değişkenler
    private String hesapNo;
    private String sahipAdi;
    private double bakiye;
    private static int vadesizSayaci = 1000;
    private static int vadeliSayaci = 1000;
    private static int krediSayaci = 1000;
    private ArrayList<String> islemGecmisi;
    private String musteriTelefon;
    private String musteriAdres;
    private String musteriEmail;
    private boolean aktif; // Hesap aktif mi?

    /**
     * Constructor - Yeni hesap oluşturur
     * @param sahipAdi Hesap sahibinin adı
     * @param ilkBakiye İlk bakiye miktarı
     * @param telefon Müşteri telefonu
     * @param adres Müşteri adresi
     * @param email Müşteri email
     * @param hesapTipi Hesap tipi kodu (VDS, VDL, KRE)
     */
    public Hesap(String sahipAdi, double ilkBakiye, String telefon, String adres, String email, String hesapTipi) {
        if (ilkBakiye < 0) {
            throw new IllegalArgumentException("İlk bakiye negatif olamaz!");
        }

        // Hesap tipine göre numara üret
        if (hesapTipi.equals("VDS")) {
            this.hesapNo = "HSP_VDS" + (++vadesizSayaci);
        } else if (hesapTipi.equals("VDL")) {
            this.hesapNo = "HSP_VDL" + (++vadeliSayaci);
        } else if (hesapTipi.equals("KRE")) {
            this.hesapNo = "HSP_KRE" + (++krediSayaci);
        }

        this.sahipAdi = sahipAdi;
        this.bakiye = ilkBakiye;
        this.musteriTelefon = telefon;
        this.musteriAdres = adres;
        this.musteriEmail = email;
        this.islemGecmisi = new ArrayList<>();
        this.aktif = true;
        islemEkle("Hesap açıldı. İlk bakiye: " + ilkBakiye + " TL");
    }

    // Getter metodları
    public String getHesapNo() { return hesapNo; }
    public String getSahipAdi() { return sahipAdi; }
    public double getBakiye() { return bakiye; }
    public String getMusteriTelefon() { return musteriTelefon; }
    public String getMusteriAdres() { return musteriAdres; }
    public String getMusteriEmail() { return musteriEmail; }

    // Setter metodları
    public void setMusteriTelefon(String telefon) { this.musteriTelefon = telefon; }
    public void setMusteriAdres(String adres) { this.musteriAdres = adres; }
    public void setMusteriEmail(String email) { this.musteriEmail = email; }
    protected void setBakiye(double bakiye) { this.bakiye = bakiye; }

    /**
     * İşlem geçmişine kayıt ekler
     * @param islem İşlem açıklaması
     */
    protected void islemEkle(String islem) {
        String zaman = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        );
        islemGecmisi.add(zaman + " - " + islem);
    }

    /**
     * İşlem geçmişini gösterir
     */
    public void islemGecmisiGoster() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║          İŞLEM GEÇMİŞİ                   ║");
        System.out.println("╚═══════════════════════════════════════╝");

        if (islemGecmisi.isEmpty()) {
            System.out.println("ℹ Henüz işlem yapılmamış.");
        } else {
            for (String islem : islemGecmisi) {
                System.out.println(islem);
            }
        }
        System.out.println("═══════════════════════════════════════════");
    }

    /**
     * Para yatırma işlemi
     * @param miktar Yatırılacak miktar
     */
    public void paraYatir(double miktar) {
        if (miktar > 0) {
            bakiye += miktar;
            islemEkle("Para yatırıldı: +" + miktar + " TL (Yeni bakiye: " + bakiye + " TL)");
            System.out.println("✓ " + miktar + " TL yatırıldı. Yeni bakiye: " + bakiye + " TL");
        } else {
            System.out.println("❌ Geçersiz miktar! Pozitif bir değer giriniz.");
        }
    }

    /**
     * Para çekme işlemi
     * Çok Biçimlilik - Alt sınıflarda override edilebilir
     * @param miktar Çekilecek miktar
     * @return İşlem başarılı mı?
     */
    public boolean paraCek(double miktar) {
        if (miktar <= 0) {
            System.out.println("❌ Geçersiz miktar! Pozitif bir değer giriniz.");
            return false;
        }

        if (miktar <= bakiye) {
            bakiye -= miktar;
            islemEkle("Para çekildi: -" + miktar + " TL (Kalan bakiye: " + bakiye + " TL)");
            System.out.println("✓ " + miktar + " TL çekildi. Kalan bakiye: " + bakiye + " TL");
            return true;
        } else {
            System.out.println("❌ Yetersiz bakiye! Mevcut bakiye: " + bakiye + " TL");
            return false;
        }
    }

    /**
     * Soyutlama - Alt sınıflar bu metodu implement etmek zorunda
     */
    public abstract void hesapBilgileriGoster();

    /**
     * Soyutlama - Alt sınıflar hesap tipini döndürür
     * @return Hesap tipi
     */
    public abstract String getHesapTipi();
}