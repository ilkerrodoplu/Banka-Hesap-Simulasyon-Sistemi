import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * BANKA HESAP SİMÜLASYONU
 * Ana Program Sınıfı - Kullanıcı arayüzü ve menü sistemi
 */
public class BankaSimulasyonu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankaSistemi banka = new BankaSistemi();

        // Başlık
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║    BANKA HESAP SİMÜLASYONU SİSTEMİ        ║");
        System.out.println("║    Basit Banka Hesap Yönetim Sistemi      ║");
        System.out.println("╚═══════════════════════════════════════════╝");

        // Ana döngü
        while (true) {
            try {
                menuGoster();
                System.out.print("Seçiminiz: ");
                int secim = guvenliIntAl(scanner);

                switch (secim) {
                    case 1:
                        hesapAcMenu(scanner, banka);
                        break;
                    case 2:
                        paraYatirMenu(scanner, banka);
                        break;
                    case 3:
                        paraCekMenu(scanner, banka);
                        break;
                    case 4:
                        bakiyeGoruntuleMenu(scanner, banka);
                        break;
                    case 5:
                        hesapKapatMenu(scanner, banka);
                        break;
                    case 6:
                        banka.tumHesaplariListele();
                        break;
                    case 7:
                        krediIslemleriMenu(scanner, banka);
                        break;
                    case 8:
                        vadeliIslemleriMenu(scanner, banka);
                        break;
                    case 9:
                        paraTransferMenu(scanner, banka);
                        break;
                    case 10:
                        islemGecmisiMenu(scanner, banka);
                        break;
                    case 11:
                        musteriBilgileriGuncelleMenu(scanner, banka);
                        break;
                    case 12:
                        finansHesaplayiciMenu(scanner);
                        break;
                    case 0:
                        System.out.println("\n╔═════════════════════════════════════════╗");
                        System.out.println("║  Sistemden çıkılıyor... İyi günler!     ║");
                        System.out.println("╚═════════════════════════════════════════╝");
                        scanner.close();
                        return;
                    default:
                        System.out.println("❌ Geçersiz seçim! Lütfen 0-12 arası bir sayı girin.");
                }
            } catch (Exception e) {
                System.out.println("❌ Bir hata oluştu: " + e.getMessage());
            }

            beklet();
        }
    }

    /**
     * Ana menüyü ekrana yazdırır
     */
    private static void menuGoster() {
        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("║              ANA MENÜ                     ║");
        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.println("║  1. Yeni Hesap Aç                         ║");
        System.out.println("║  2. Para Yatır                            ║");
        System.out.println("║  3. Para Çek                              ║");
        System.out.println("║  4. Bakiye Görüntüle                      ║");
        System.out.println("║  5. Hesap Kapat                           ║");
        System.out.println("║  6. Tüm Hesapları Listele                 ║");
        System.out.println("║  7. Kredi İşlemleri                       ║");
        System.out.println("║  8. Vadeli Hesap İşlemleri                ║");
        System.out.println("║  9. Para Transferi                        ║");
        System.out.println("║  10. İşlem Geçmişi                        ║");
        System.out.println("║  11. Müşteri Bilgilerini Güncelle         ║");
        System.out.println("║  12. Finans Hesaplayıcı                   ║");
        System.out.println("║  0. Çıkış                                 ║");
        System.out.println("╚═══════════════════════════════════════════╝");
    }

    /**
     * Güvenli integer alma metodu - InputMismatchException kontrolü
     */
    private static int guvenliIntAl(Scanner scanner) {
        while (true) {
            try {
                int sayi = scanner.nextInt();
                scanner.nextLine(); // Buffer temizle
                return sayi;
            } catch (InputMismatchException e) {
                System.out.println("❌ Geçersiz giriş! Lütfen bir sayı giriniz.");
                System.out.print("Tekrar deneyin: ");
                scanner.nextLine(); // Hatalı girişi temizle
            }
        }
    }

    /**
     * Güvenli double alma metodu
     */
    private static double guvenliDoubleAl(Scanner scanner) {
        while (true) {
            try {
                double sayi = scanner.nextDouble();
                scanner.nextLine(); // Buffer temizle
                if (sayi < 0) {
                    System.out.println("❌ Negatif değer girilemez!");
                    System.out.print("Tekrar deneyin: ");
                    continue;
                }
                return sayi;
            } catch (InputMismatchException e) {
                System.out.println("❌ Geçersiz giriş! Lütfen bir sayı giriniz.");
                System.out.print("Tekrar deneyin: ");
                scanner.nextLine();
            }
        }
    }

    /**
     * Email formatını kontrol eder
     */
    private static String emailAl(Scanner scanner) {
        while (true) {
            System.out.print("Email: ");
            String email = scanner.nextLine().trim();

            // Küçük harfe çevir
            email = email.toLowerCase();

            // Türkçe karakter kontrolü
            if (email.matches(".*[çÇğĞıİöÖşŞüÜ].*")) {
                System.out.println("❌ Email Türkçe karakter içeremez!");
                continue;
            }

            if (email != null && !email.trim().isEmpty() &&
                    email.contains("@") && email.contains(".")) {
                return email;
            }

            System.out.println("❌ Geçersiz email formatı! @ ve . içermelidir.");
        }
    }

    /**
     * Telefon numarası al - Doğrulama ile
     */
    private static String telefonAl(Scanner scanner) {
        while (true) {
            System.out.print("Telefon numarası (05XXXXXXXXX): ");
            String telefon = scanner.nextLine().trim();

            if (telefon == null || telefon.trim().isEmpty()) {
                System.out.println("❌ Telefon numarası boş olamaz!");
                continue;
            }

            String temiz = telefon.replaceAll("[\\s\\-\\(\\)\\+]", "");

            // 11 hane kontrolü
            if (temiz.length() != 11) {
                System.out.println("❌ Telefon numarası 11 hane olmalıdır!");
                continue;
            }

            // Sadece rakam kontrolü
            if (!temiz.matches("\\d+")) {
                System.out.println("❌ Telefon numarası sadece rakam içermelidir!");
                continue;
            }

            // 05 ile başlama kontrolü
            if (!temiz.startsWith("05")) {
                System.out.println("❌ Telefon numarası 05 ile başlamalıdır!");
                continue;
            }

            return telefon;
        }
    }

    /**
     * Hesap açma menüsü
     */
    private static void hesapAcMenu(Scanner scanner, BankaSistemi banka) {
        try {
            System.out.println("\n╔═══════════════════════════════════╗");
            System.out.println("║      YENİ HESAP AÇMA              ║");
            System.out.println("╚═══════════════════════════════════╝");

            // Müşteri bilgilerini al
            System.out.print("Hesap sahibinin adı soyadı: ");
            String ad = scanner.nextLine().trim();

            if (ad.isEmpty()) {
                System.out.println("❌ Hesap sahibi adı boş olamaz!");
                return;
            }

            String telefon = telefonAl(scanner);

            System.out.print("Adres: ");
            String adres = scanner.nextLine().trim();

            if (adres.isEmpty()) {
                System.out.println("❌ Adres boş olamaz!");
                return;
            }

            String email = emailAl(scanner);

            // Hesap tipini seç
            System.out.println("\n1. Vadesiz Hesap (HSP_VDS)");
            System.out.println("2. Vadeli Hesap (HSP_VDL)");
            System.out.println("3. Kredi Hesabı (HSP_KRE)");
            System.out.print("Hesap tipi seçiniz: ");
            int tip = guvenliIntAl(scanner);

            Hesap yeniHesap = null;

            if (tip == 1) {
                System.out.print("İlk yatırılacak miktar (TL): ");
                double ilkBakiye = guvenliDoubleAl(scanner);
                yeniHesap = new VadesizHesap(ad, ilkBakiye, telefon, adres, email);
                banka.hesapAc(yeniHesap);
            } else if (tip == 2) {
                System.out.print("İlk yatırılacak miktar (TL): ");
                double ilkBakiye = guvenliDoubleAl(scanner);
                System.out.print("Vade süresi (gün - örn: 90, 180, 365): ");
                int vade = guvenliIntAl(scanner);
                yeniHesap = new VadeliHesap(ad, ilkBakiye, vade, telefon, adres, email);
                banka.hesapAc(yeniHesap);
            } else if (tip == 3) {
                System.out.print("Kredi limiti (TL - min 1000): ");
                double limit = guvenliDoubleAl(scanner);
                yeniHesap = new KrediHesabi(ad, limit, telefon, adres, email);
                banka.hesapAc(yeniHesap);
            } else {
                System.out.println("❌ Geçersiz hesap tipi!");
                return;
            }

        } catch (IllegalArgumentException e) {
            System.out.println("❌ Hata: " + e.getMessage());
        }
    }

    /**
     * Hesap bilgisi başlığı gösterir
     */
    private static void hesapBaslikGoster(Hesap hesap) {
        System.out.println("\n┌────────────────────────────────────────────┐");
        System.out.println("│ Hesap No: " + hesap.getHesapNo() + " | Tür: " + hesap.getHesapTipi());
        System.out.println("└────────────────────────────────────────────┘");
    }

    /**
     * Para yatırma menüsü
     */
    private static void paraYatirMenu(Scanner scanner, BankaSistemi banka) {
        System.out.print("\nHesap No: ");
        String hesapNo = scanner.nextLine().trim();
        Hesap hesap = banka.hesapBul(hesapNo);

        if (hesap != null) {
            hesapBaslikGoster(hesap);
            System.out.print("Yatırılacak miktar (TL): ");
            double miktar = guvenliDoubleAl(scanner);
            hesap.paraYatir(miktar);
        } else {
            System.out.println("❌ Hesap bulunamadı veya kapalı!");
        }
    }

    /**
     * Para çekme menüsü
     */
    private static void paraCekMenu(Scanner scanner, BankaSistemi banka) {
        System.out.print("\nHesap No: ");
        String hesapNo = scanner.nextLine().trim();
        Hesap hesap = banka.hesapBul(hesapNo);

        if (hesap != null) {
            hesapBaslikGoster(hesap);
            System.out.print("Çekilecek miktar (TL): ");
            double miktar = guvenliDoubleAl(scanner);
            hesap.paraCek(miktar);
        } else {
            System.out.println("❌ Hesap bulunamadı veya kapalı!");
        }
    }

    /**
     * Bakiye görüntüleme menüsü
     */
    private static void bakiyeGoruntuleMenu(Scanner scanner, BankaSistemi banka) {
        System.out.print("\nHesap No: ");
        String hesapNo = scanner.nextLine().trim();
        Hesap hesap = banka.hesapBul(hesapNo);

        if (hesap != null) {
            hesap.hesapBilgileriGoster();
        } else {
            System.out.println("❌ Hesap bulunamadı veya kapalı!");
        }
    }

    /**
     * Hesap kapatma menüsü
     */
    private static void hesapKapatMenu(Scanner scanner, BankaSistemi banka) {
        System.out.print("\nKapatılacak Hesap No: ");
        String hesapNo = scanner.nextLine().trim();

        Hesap hesap = banka.hesapBul(hesapNo);
        if (hesap != null) {
            hesapBaslikGoster(hesap);
        }

        banka.hesapKapat(hesapNo);
    }

    /**
     * Kredi işlemleri menüsü
     */
    private static void krediIslemleriMenu(Scanner scanner, BankaSistemi banka) {
        System.out.print("\nHesap No: ");
        String hesapNo = scanner.nextLine().trim();
        Hesap hesap = banka.hesapBul(hesapNo);

        if (hesap instanceof KrediHesabi) {
            KrediHesabi kredi = (KrediHesabi) hesap;

            hesapBaslikGoster(hesap);

            System.out.println("\n╔═══════════════════════════════════╗");
            System.out.println("║      KREDİ İŞLEMLERİ              ║");
            System.out.println("╚═══════════════════════════════════╝");
            System.out.println("1. Kredi Kullan");
            System.out.println("2. Kredi Öde");
            System.out.println("3. Aylık Faiz Ekle");
            System.out.print("Seçim: ");
            int secim = guvenliIntAl(scanner);

            switch (secim) {
                case 1:
                    System.out.print("Kullanılacak miktar (TL): ");
                    double kullanim = guvenliDoubleAl(scanner);
                    kredi.krediKullan(kullanim);
                    break;
                case 2:
                    System.out.print("Ödenecek miktar (TL): ");
                    double odeme = guvenliDoubleAl(scanner);
                    kredi.krediOde(odeme);
                    break;
                case 3:
                    kredi.aylikFaizEkle();
                    break;
                default:
                    System.out.println("❌ Geçersiz seçim!");
            }
        } else if (hesap != null) {
            hesapBaslikGoster(hesap);
            System.out.println("❌ Bu hesap bir kredi hesabı değil!");
        } else {
            System.out.println("❌ Hesap bulunamadı veya kapalı!");
        }
    }

    /**
     * Vadeli hesap işlemleri menüsü
     */
    private static void vadeliIslemleriMenu(Scanner scanner, BankaSistemi banka) {
        System.out.print("\nHesap No: ");
        String hesapNo = scanner.nextLine().trim();
        Hesap hesap = banka.hesapBul(hesapNo);

        if (hesap instanceof VadeliHesap) {
            VadeliHesap vadeli = (VadeliHesap) hesap;

            hesapBaslikGoster(hesap);

            System.out.println("\n╔═══════════════════════════════════╗");
            System.out.println("║    VADELİ HESAP İŞLEMLERİ         ║");
            System.out.println("╚═══════════════════════════════════╝");
            System.out.println("1. Faiz Hesapla");
            System.out.println("2. Vadeyi Doldur (Faiz Ekle)");
            System.out.print("Seçim: ");
            int secim = guvenliIntAl(scanner);

            switch (secim) {
                case 1:
                    double faiz = vadeli.faizHesapla();
                    System.out.println("ℹ Hesaplanacak faiz: " + faiz + " TL");
                    System.out.println("ℹ Vade dolduğunda toplam: " + (vadeli.getBakiye() + faiz) + " TL");
                    break;
                case 2:
                    vadeli.vadeDoldur();
                    break;
                default:
                    System.out.println("❌ Geçersiz seçim!");
            }
        } else if (hesap != null) {
            hesapBaslikGoster(hesap);
            System.out.println("❌ Bu hesap bir vadeli hesap değil!");
        } else {
            System.out.println("❌ Hesap bulunamadı veya kapalı!");
        }
    }

    /**
     * Para transferi menüsü
     */
    private static void paraTransferMenu(Scanner scanner, BankaSistemi banka) {
        System.out.println("\n╔═══════════════════════════════════╗");
        System.out.println("║        PARA TRANSFERİ             ║");
        System.out.println("╚═══════════════════════════════════╝");
        System.out.print("Gönderen Hesap No: ");
        String kaynakHesap = scanner.nextLine().trim();

        Hesap kaynak = banka.hesapBul(kaynakHesap);
        if (kaynak != null) {
            System.out.println("\nGönderen:");
            hesapBaslikGoster(kaynak);
        }

        System.out.print("\nAlıcı Hesap No: ");
        String hedefHesap = scanner.nextLine().trim();

        Hesap hedef = banka.hesapBul(hedefHesap);
        if (hedef != null) {
            System.out.println("\nAlıcı:");
            hesapBaslikGoster(hedef);
        }

        System.out.print("\nTransfer miktarı (TL): ");
        double miktar = guvenliDoubleAl(scanner);

        banka.paraTransfer(kaynakHesap, hedefHesap, miktar);
    }

    /**
     * İşlem geçmişi menüsü
     */
    private static void islemGecmisiMenu(Scanner scanner, BankaSistemi banka) {
        System.out.print("\nHesap No: ");
        String hesapNo = scanner.nextLine().trim();
        Hesap hesap = banka.hesapBul(hesapNo);

        if (hesap != null) {
            hesapBaslikGoster(hesap);
            hesap.islemGecmisiGoster();
        } else {
            System.out.println("❌ Hesap bulunamadı veya kapalı!");
        }
    }

    /**
     * Müşteri bilgileri güncelleme menüsü
     */
    private static void musteriBilgileriGuncelleMenu(Scanner scanner, BankaSistemi banka) {
        System.out.println("\n╔═══════════════════════════════════╗");
        System.out.println("║  MÜŞTERİ BİLGİLERİNİ GÜNCELLE     ║");
        System.out.println("╚═══════════════════════════════════╝");
        System.out.print("Hesap No: ");
        String hesapNo = scanner.nextLine().trim();

        Hesap hesap = banka.hesapBul(hesapNo);
        if (hesap == null) {
            System.out.println("❌ Hesap bulunamadı veya kapalı!");
            return;
        }

        hesapBaslikGoster(hesap);

        System.out.println("\nMevcut Bilgiler:");
        System.out.println("Telefon: " + hesap.getMusteriTelefon());
        System.out.println("Adres: " + hesap.getMusteriAdres());
        System.out.println("Email: " + hesap.getMusteriEmail());

        System.out.print("\nYeni telefon (boş bırakmak için Enter): ");
        String yeniTelefon = scanner.nextLine().trim();

        System.out.print("Yeni adres (boş bırakmak için Enter): ");
        String yeniAdres = scanner.nextLine().trim();

        System.out.print("Yeni email (boş bırakmak için Enter): ");
        String yeniEmail = scanner.nextLine().trim();

        banka.musteriBilgileriGuncelle(hesapNo, yeniTelefon, yeniAdres, yeniEmail);
    }

    /**
     * Finans hesaplayıcı menüsü
     */
    private static void finansHesaplayiciMenu(Scanner scanner) {
        boolean devam = true;

        while (devam) {
            try {
                System.out.println("\n╔═══════════════════════════════════════╗");
                System.out.println("║       FİNANS HESAPLAYICI              ║");
                System.out.println("╠═══════════════════════════════════════╣");
                System.out.println("║  1. Basit Faiz Hesaplama              ║");
                System.out.println("║  2. Bileşik Faiz Hesaplama            ║");
                System.out.println("║  3. Kredi Ödeme Hesaplama             ║");
                System.out.println("║  4. Gelecek Değer Hesaplama           ║");
                System.out.println("║  5. Yatırım Getirisi (ROI) Hesaplama  ║");
                System.out.println("║  6. Ana Menüye Dön                    ║");
                System.out.println("╚═══════════════════════════════════════╝");
                System.out.print("Seçiminiz: ");

                int secim = guvenliIntAl(scanner);

                switch (secim) {
                    case 1:
                        basitFaizHesaplaMenu(scanner);
                        break;
                    case 2:
                        bilesikFaizHesaplaMenu(scanner);
                        break;
                    case 3:
                        krediOdemeHesaplaMenu(scanner);
                        break;
                    case 4:
                        gelecekDegerHesaplaMenu(scanner);
                        break;
                    case 5:
                        roiHesaplaMenu(scanner);
                        break;
                    case 6:
                        devam = false;
                        System.out.println("Ana menüye dönülüyor...");
                        break;
                    default:
                        System.out.println("❌ Geçersiz seçim!");
                }
            } catch (Exception e) {
                System.out.println("❌ Hata: " + e.getMessage());
            }
        }
    }

    /**
     * Basit faiz hesaplama menüsü
     */
    private static void basitFaizHesaplaMenu(Scanner scanner) {
        System.out.println("\n--- Basit Faiz Hesaplama ---");
        System.out.print("Ana para (TL): ");
        double anaPara = guvenliDoubleAl(scanner);
        System.out.print("Yıllık faiz oranı (%): ");
        double oran = guvenliDoubleAl(scanner);
        System.out.print("Süre (yıl): ");
        double yil = guvenliDoubleAl(scanner);

        double faiz = FinansHesaplayici.basitFaizHesapla(anaPara, oran, yil);
        if (faiz > 0) {
            double toplam = anaPara + faiz;
            System.out.println("\n--- Sonuçlar ---");
            System.out.println("Ana Para: " + String.format("%.2f", anaPara) + " TL");
            System.out.println("Faiz Oranı: %" + oran);
            System.out.println("Süre: " + yil + " yıl");
            System.out.println("Basit Faiz: " + String.format("%.2f", faiz) + " TL");
            System.out.println("Toplam Tutar: " + String.format("%.2f", toplam) + " TL");
        }
    }

    /**
     * Bileşik faiz hesaplama menüsü
     */
    private static void bilesikFaizHesaplaMenu(Scanner scanner) {
        System.out.println("\n--- Bileşik Faiz Hesaplama ---");
        System.out.print("Ana para (TL): ");
        double anaPara = guvenliDoubleAl(scanner);
        System.out.print("Yıllık faiz oranı (%): ");
        double oran = guvenliDoubleAl(scanner);
        System.out.print("Süre (yıl): ");
        double yil = guvenliDoubleAl(scanner);
        System.out.print("Yılda bileşik sayısı (1=yıllık, 4=3 aylık, 12=aylık): ");
        int bilesik = guvenliIntAl(scanner);

        double sonuc = FinansHesaplayici.bilesikFaizHesapla(anaPara, oran, yil, bilesik);
        if (sonuc > 0) {
            double faiz = sonuc - anaPara;
            System.out.println("\n--- Sonuçlar ---");
            System.out.println("Ana Para: " + String.format("%.2f", anaPara) + " TL");
            System.out.println("Faiz Oranı: %" + oran);
            System.out.println("Süre: " + yil + " yıl");
            System.out.println("Bileşik Sayısı: " + bilesik);
            System.out.println("Bileşik Faiz: " + String.format("%.2f", faiz) + " TL");
            System.out.println("Nihai Tutar: " + String.format("%.2f", sonuc) + " TL");
        }
    }

    /**
     * Kredi ödeme hesaplama menüsü
     */
    private static void krediOdemeHesaplaMenu(Scanner scanner) {
        System.out.println("\n--- Kredi Ödeme Hesaplama ---");
        System.out.print("Kredi tutarı (TL): ");
        double kredi = guvenliDoubleAl(scanner);
        System.out.print("Yıllık faiz oranı (%): ");
        double oran = guvenliDoubleAl(scanner);
        System.out.print("Kredi vadesi (yıl): ");
        double yil = guvenliDoubleAl(scanner);

        double aylikOdeme = FinansHesaplayici.aylikKrediOdemesiHesapla(kredi, oran, yil);
        if (aylikOdeme > 0) {
            double toplamOdeme = aylikOdeme * yil * 12;
            double toplamFaiz = toplamOdeme - kredi;
            System.out.println("\n--- Sonuçlar ---");
            System.out.println("Kredi Tutarı: " + String.format("%.2f", kredi) + " TL");
            System.out.println("Faiz Oranı: %" + oran);
            System.out.println("Kredi Vadesi: " + yil + " yıl");
            System.out.println("Aylık Ödeme: " + String.format("%.2f", aylikOdeme) + " TL");
            System.out.println("Toplam Ödeme: " + String.format("%.2f", toplamOdeme) + " TL");
            System.out.println("Toplam Faiz: " + String.format("%.2f", toplamFaiz) + " TL");
        }
    }

    /**
     * Gelecek değer hesaplama menüsü
     */
    private static void gelecekDegerHesaplaMenu(Scanner scanner) {
        System.out.println("\n--- Gelecek Değer Hesaplama ---");
        System.out.print("Aylık yatırım tutarı (TL): ");
        double aylik = guvenliDoubleAl(scanner);
        System.out.print("Yıllık faiz oranı (%): ");
        double oran = guvenliDoubleAl(scanner);
        System.out.print("Süre (yıl): ");
        double yil = guvenliDoubleAl(scanner);

        double gelecekDeger = FinansHesaplayici.gelecekDegerHesapla(aylik, oran, yil);
        if (gelecekDeger > 0) {
            double toplamYatirim = aylik * yil * 12;
            double kazanc = gelecekDeger - toplamYatirim;
            System.out.println("\n--- Sonuçlar ---");
            System.out.println("Aylık Yatırım: " + String.format("%.2f", aylik) + " TL");
            System.out.println("Faiz Oranı: %" + oran);
            System.out.println("Süre: " + yil + " yıl");
            System.out.println("Toplam Yatırılan: " + String.format("%.2f", toplamYatirim) + " TL");
            System.out.println("Kazanılan Faiz: " + String.format("%.2f", kazanc) + " TL");
            System.out.println("Gelecek Değer: " + String.format("%.2f", gelecekDeger) + " TL");
        }
    }

    /**
     * ROI hesaplama menüsü
     */
    private static void roiHesaplaMenu(Scanner scanner) {
        System.out.println("\n--- Yatırım Getirisi (ROI) Hesaplama ---");
        System.out.print("İlk yatırım (TL): ");
        double ilkYatirim = guvenliDoubleAl(scanner);
        System.out.print("Nihai değer (TL): ");
        double nihaiDeger = guvenliDoubleAl(scanner);

        if (ilkYatirim > 0) {
            double roi = FinansHesaplayici.roiHesapla(ilkYatirim, nihaiDeger);
            double kar = nihaiDeger - ilkYatirim;
            System.out.println("\n--- Sonuçlar ---");
            System.out.println("İlk Yatırım: " + String.format("%.2f", ilkYatirim) + " TL");
            System.out.println("Nihai Değer: " + String.format("%.2f", nihaiDeger) + " TL");
            System.out.println("Kâr/Zarar: " + String.format("%.2f", kar) + " TL");
            System.out.println("ROI: %" + String.format("%.2f", roi));
        }
    }

    /**
     * Kullanıcının Enter tuşuna basmasını bekler
     */
    private static void beklet() {
        System.out.println("\nDevam etmek için Enter'a basın...");
        try {
            System.in.read();
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (Exception e) {
            // Hata yok sayılır
        }
    }
}