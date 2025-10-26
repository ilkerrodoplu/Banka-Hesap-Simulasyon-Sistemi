/**
 * Kalıtım - Vadeli Hesap
 * Belirli vade süresi sonunda faiz getiren hesap tipi
 */
class VadeliHesap extends Hesap {
    private double faizOrani;
    private int vadeGunu;
    private boolean vadeDolduMu;

    public VadeliHesap(String sahipAdi, double ilkBakiye, int vadeGunu, String telefon, String adres, String email) {
        super(sahipAdi, ilkBakiye, telefon, adres, email, "VDL");

        if (vadeGunu < 30) {
            throw new IllegalArgumentException("Vade süresi en az 30 gün olmalıdır!");
        }

        this.vadeGunu = vadeGunu;
        this.faizOrani = hesaplaFaizOrani(vadeGunu);
        this.vadeDolduMu = false;
        islemEkle("Vadeli hesap açıldı. Vade: " + vadeGunu + " gün, Faiz: %" + (faizOrani * 100));
    }

    /**
     * Temel finansal hesaplama - Vade süresine göre faiz oranını hesaplar
     */
    private double hesaplaFaizOrani(int gun) {
        if (gun >= 365) return 0.25;      // 1 yıl ve üzeri: %25
        else if (gun >= 180) return 0.18; // 6 ay: %18
        else if (gun >= 90) return 0.12;  // 3 ay: %12
        else return 0.08;                 // 30-90 gün: %8
    }

    /**
     * Temel finansal hesaplama - Faiz hesaplama
     */
    public double faizHesapla() {
        return getBakiye() * faizOrani;
    }

    /**
     * Vade dolduğunda çağrılır, faizi hesaba ekler
     */
    public void vadeDoldur() {
        if (vadeDolduMu) {
            System.out.println("⚠ Vade zaten dolmuş ve faiz eklenmiş!");
            return;
        }

        double faiz = faizHesapla();
        setBakiye(getBakiye() + faiz);
        vadeDolduMu = true;
        islemEkle("Vade doldu! Faiz eklendi: +" + faiz + " TL");
        System.out.println("✓ Vade doldu! " + faiz + " TL faiz hesabınıza eklendi.");
        System.out.println("✓ Yeni bakiye: " + getBakiye() + " TL");
    }

    @Override
    public boolean paraCek(double miktar) {
        if (!vadeDolduMu) {
            System.out.println("⚠ Uyarı: Vadeli hesaptan erken çekim yapılıyor!");
            System.out.println("⚠ Faiz kaybı yaşanacaktır.");
            islemEkle("Erken çekim yapıldı! Faiz kaybı oluştu.");
        }
        return super.paraCek(miktar);
    }

    @Override
    public void hesapBilgileriGoster() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║      VADELİ HESAP BİLGİLERİ       ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("Hesap No      : " + getHesapNo());
        System.out.println("Hesap Sahibi  : " + getSahipAdi());
        System.out.println("Telefon       : " + getMusteriTelefon());
        System.out.println("Adres         : " + getMusteriAdres());
        System.out.println("Email         : " + getMusteriEmail());
        System.out.println("Bakiye        : " + getBakiye() + " TL");
        System.out.println("Vade Süresi   : " + vadeGunu + " gün");
        System.out.println("Faiz Oranı    : %" + (faizOrani * 100));
        System.out.println("Vade Durumu   : " + (vadeDolduMu ? "✓ Doldu" : "⏳ Devam ediyor"));

        if (!vadeDolduMu) {
            System.out.println("Beklenen Faiz : " + faizHesapla() + " TL");
        }
        System.out.println("────────────────────────────────────");
    }

    @Override
    public String getHesapTipi() {
        return "Vadeli Hesap";
    }
}