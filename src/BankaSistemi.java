import java.util.ArrayList;

/**
 * Banka Sistemi - Hesap Yönetim Sınıfı
 * Tüm hesapları yönetir, ekleme/silme/listeleme işlemlerini gerçekleştirir
 */
class BankaSistemi {
    // Kapsülleme - Hesaplar private olarak saklanır
    private ArrayList<Hesap> hesaplar;

    public BankaSistemi() {
        hesaplar = new ArrayList<>();
    }

    /**
     * Yeni hesap açma işlemi
     */
    public void hesapAc(Hesap hesap) {
        hesaplar.add(hesap);
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║  ✓ HESAP BAŞARIYLA AÇILDI!        ║");
        System.out.println("╚════════════════════════════════════╝");
        hesap.hesapBilgileriGoster();
    }

    /**
     * Hesap kapatma işlemi
     */
    public boolean hesapKapat(String hesapNo) {
        for (int i = 0; i < hesaplar.size(); i++) {
            if (hesaplar.get(i).getHesapNo().equals(hesapNo)) {
                Hesap hesap = hesaplar.get(i);

                // Kredi hesabı kontrolü
                if (hesap instanceof KrediHesabi) {
                    KrediHesabi kredi = (KrediHesabi) hesap;
                    if (kredi.getKullanilanKredi() > 0.01) {
                        System.out.println("❌ Kredi hesabı kapatılamadı!");
                        System.out.println("Önce kredi borcunu kapatmalısınız: " + kredi.getKullanilanKredi() + " TL");
                        return false;
                    }
                }

                // Bakiye kontrolü (0.01 TL tolerans)
                if (Math.abs(hesap.getBakiye()) < 0.01) {
                    hesaplar.remove(i);
                    System.out.println("✓ Hesap başarıyla kapatıldı: " + hesapNo);
                    System.out.println("Hesap sahibi: " + hesap.getSahipAdi());
                    System.out.println("Hesap tipi: " + hesap.getHesapTipi());
                    return true;
                } else {
                    System.out.println("❌ Hesap kapatılamadı!");
                    System.out.println("Hesap bakiyesi 0 olmadan kapatılamaz.");
                    System.out.println("Mevcut bakiye: " + hesap.getBakiye() + " TL");
                    return false;
                }
            }
        }
        System.out.println("❌ Hesap bulunamadı: " + hesapNo);
        return false;
    }

    /**
     * Hesap numarasına göre hesap bulma
     */
    public Hesap hesapBul(String hesapNo) {
        for (Hesap hesap : hesaplar) {
            if (hesap.getHesapNo().equals(hesapNo)) {
                return hesap;
            }
        }
        return null;
    }

    /**
     * Para transferi işlemi
     */
    public boolean paraTransfer(String kaynakHesapNo, String hedefHesapNo, double miktar) {
        Hesap kaynak = hesapBul(kaynakHesapNo);
        Hesap hedef = hesapBul(hedefHesapNo);

        if (kaynak == null) {
            System.out.println("❌ Gönderen hesap bulunamadı!");
            return false;
        }

        if (hedef == null) {
            System.out.println("❌ Alıcı hesap bulunamadı!");
            return false;
        }

        if (kaynak.getHesapNo().equals(hedef.getHesapNo())) {
            System.out.println("❌ Aynı hesaba transfer yapılamaz!");
            return false;
        }

        if (kaynak.paraCek(miktar)) {
            hedef.paraYatir(miktar);
            System.out.println("✓ Transfer başarılı!");
            System.out.println("Gönderen: " + kaynak.getSahipAdi() + " (" + kaynakHesapNo + ")");
            System.out.println("Alıcı: " + hedef.getSahipAdi() + " (" + hedefHesapNo + ")");
            return true;
        }

        return false;
    }

    /**
     * Tüm hesapları listeleme
     * Çok Biçimlilik - Her hesap tipi kendi bilgilerini gösterir
     */
    public void tumHesaplariListele() {
        if (hesaplar.isEmpty()) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║  ℹ SİSTEMDE HESAP BULUNMUYOR      ║");
            System.out.println("╚════════════════════════════════════╝");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║          TÜM HESAPLAR LİSTESİ                 ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.println("Toplam hesap sayısı: " + hesaplar.size());

        for (Hesap hesap : hesaplar) {
            hesap.hesapBilgileriGoster();
        }

        // Özet bilgi
        System.out.println("\n════════════════════════════════════════════════");
        System.out.println("ÖZET:");
        int vadesizSayisi = 0, vadeliSayisi = 0, krediSayisi = 0;
        double toplamBakiye = 0;

        for (Hesap hesap : hesaplar) {
            if (hesap instanceof VadesizHesap) {
                vadesizSayisi++;
            } else if (hesap instanceof VadeliHesap) {
                vadeliSayisi++;
            } else if (hesap instanceof KrediHesabi) {
                krediSayisi++;
            }
            toplamBakiye += hesap.getBakiye();
        }

        System.out.println("Vadesiz Hesap: " + vadesizSayisi);
        System.out.println("Vadeli Hesap : " + vadeliSayisi);
        System.out.println("Kredi Hesabı : " + krediSayisi);
        System.out.println("Toplam Bakiye: " + toplamBakiye + " TL");
        System.out.println("════════════════════════════════════════════════");
    }

    /**
     * Müşteri bilgilerini güncelleme
     */
    public void musteriBilgileriGuncelle(String hesapNo, String yeniTelefon, String yeniAdres, String yeniEmail) {
        Hesap hesap = hesapBul(hesapNo);
        if (hesap == null) {
            System.out.println("❌ Hesap bulunamadı!");
            return;
        }

        boolean guncellendi = false;

        if (yeniTelefon != null && !yeniTelefon.isEmpty()) {
            hesap.setMusteriTelefon(yeniTelefon);
            guncellendi = true;
            System.out.println("✓ Telefon güncellendi: " + yeniTelefon);
        }

        if (yeniAdres != null && !yeniAdres.isEmpty()) {
            hesap.setMusteriAdres(yeniAdres);
            guncellendi = true;
            System.out.println("✓ Adres güncellendi: " + yeniAdres);
        }

        if (yeniEmail != null && !yeniEmail.isEmpty()) {
            hesap.setMusteriEmail(yeniEmail);
            guncellendi = true;
            System.out.println("✓ Email güncellendi: " + yeniEmail);
        }

        if (guncellendi) {
            System.out.println("✓ Müşteri bilgileri başarıyla güncellendi!");
        } else {
            System.out.println("ℹ Hiçbir bilgi güncellenmedi.");
        }
    }

}
