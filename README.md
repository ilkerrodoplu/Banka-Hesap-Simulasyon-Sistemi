# Banka Hesap Simülasyon Sistemi

## 📋 Proje Hakkında 

Bu proje, Java programlama dilinde nesne yönelimli programlama (OOP) prensiplerini kullanarak geliştirilmiş kapsamlı bir banka hesap yönetim sistemidir. Vadesiz hesap, vadeli hesap ve kredi hesabı olmak üzere üç farklı hesap tipini yönetebilen, finansal hesaplamalar yapabilen interaktif bir konsol uygulamasıdır.

## 🎯 Özellikler

### Hesap Yönetimi
- **Vadesiz Hesap (HSP_VDS)**: Standart çek hesabı işlevselliği, 1000 TL ek hesap limiti
- **Vadeli Hesap (HSP_VDL)**: Vade süresine göre değişken faiz oranları (30-365+ gün)
- **Kredi Hesabı (HSP_KRE)**: Belirlenen limit dahilinde kredi kullanımı, %2 aylık faiz

### Temel İşlemler
- Para yatırma ve çekme
- Hesaplar arası para transferi
- Hesap açma ve kapatma
- Bakiye sorgulama
- İşlem geçmişi görüntüleme
- Müşteri bilgileri güncelleme

### Finansal Hesaplayıcı
- **Basit Faiz**: Ana para × Oran × Süre
- **Bileşik Faiz**: A = P(1 + r/n)^(nt)
- **Kredi Ödemesi**: Aylık taksit hesaplama
- **Gelecek Değer**: Düzenli tasarruf planlaması
- **ROI (Yatırım Getirisi)**: Yatırım performans analizi

## 🏗️ Mimari ve OOP Prensipleri

### Sınıf Yapısı

```
Hesap (Abstract)
├── VadesizHesap
├── VadeliHesap
└── KrediHesabi

BankaSistemi (Hesap Yönetimi)
FinansHesaplayici (Statik Hesaplama Araçları)
BankaSimulasyonu (Ana Program & UI)
```

### Uygulanan OOP Kavramları

**1. Soyutlama (Abstraction)**
- `Hesap` soyut sınıfı tüm hesap tiplerinin temelini oluşturur
- `hesapBilgileriGoster()` ve `getHesapTipi()` soyut metodları zorunlu implementasyon sağlar

**2. Kapsülleme (Encapsulation)**
- Private değişkenler (bakiye, hesapNo, müşteri bilgileri)
- Public getter/setter metodları ile kontrollü erişim
- İşlem geçmişi korumalı metodlarla yönetilir

**3. Kalıtım (Inheritance)**
- Tüm hesap tipleri `Hesap` sınıfından türetilir
- Ortak özellikler ve davranışlar üst sınıfta tanımlanır
- Alt sınıflar kendilerine özgü özellikleri ekler

**4. Çok Biçimlilik (Polymorphism)**
- `paraCek()` metodu her hesap tipinde farklı davranır
- `hesapBilgileriGoster()` her sınıf kendi formatında bilgi gösterir
- ArrayList<Hesap> ile farklı tiplerdeki hesaplar tek listede tutulur

## 💻 Kurulum ve Çalıştırma

### Gereksinimler
- Java JDK 8 veya üzeri
- Konsol/Terminal

### Derleme
```bash
javac BankaSimulasyonu.java
```

### Çalıştırma
```bash
java BankaSimulasyonu
```

## 📖 Kullanım Kılavuzu

### Hesap Açma
1. Ana menüden "1 - Yeni Hesap Aç" seçeneğini seçin
2. Müşteri bilgilerini girin (ad, telefon, adres, email)
3. Hesap tipini seçin (Vadesiz/Vadeli/Kredi)
4. İlk bakiye veya kredi limitini belirleyin

### Para İşlemleri
- **Yatırma**: Hesap numarası ve miktar girişi
- **Çekme**: Bakiye kontrolü ile güvenli çekim
- **Transfer**: İki hesap arası doğrudan aktarım

### Kredi İşlemleri
- Kredi kullanımı (limit dahilinde)
- Kredi ödemesi
- Aylık faiz hesaplama ve ekleme

### Vadeli Hesap İşlemleri
- Faiz hesaplama (vade dolmadan önce)
- Vade doldurma (faizi hesaba ekleme)
- Erken çekim (faiz kaybı uyarısı ile)

## 🔒 Güvenlik Özellikleri

- Email format doğrulaması (@ ve . kontrolü)
- Türkçe karakter engelleme (email için)
- Telefon numarası validasyonu (05XXXXXXXXX formatı)
- Negatif tutar kontrolü
- InputMismatchException yakalama
- Bakiye yetersizliği kontrolü
- Kredi limiti aşım koruması

## 📊 Finansal Hesaplamalar

### Faiz Oranları (Vadeli Hesap)
- 30-90 gün: %8
- 90-180 gün: %12
- 180-365 gün: %18
- 365+ gün: %25

### Kredi Hesabı
- Minimum limit: 1000 TL
- Aylık faiz oranı: %2
- Otomatik faiz hesaplama

## 🎨 Kullanıcı Arayüzü

- ASCII art kullanılarak tasarlanmış görsel menüler
- Emoji ve sembollerle zenginleştirilmiş çıktılar
- Renkli kutu çizgileri (Unicode box-drawing characters)
- Detaylı işlem onayları ve uyarılar
- Anlaşılır hata mesajları

## 📝 Örnek Senaryolar

### Senaryo 1: Vadesiz Hesap
```
1. 5000 TL ile vadesiz hesap açın
2. 1000 TL para çekin
3. 6000 TL para çekin (ek hesap devreye girer)
4. İşlem geçmişini görüntüleyin
```

### Senaryo 2: Vadeli Hesap
```
1. 10000 TL ile 365 günlük vadeli hesap açın
2. Beklenen faizi hesaplayın (%25 = 2500 TL)
3. Vadeyi doldurun
4. Yeni bakiyeyi kontrol edin (12500 TL)
```

### Senaryo 3: Kredi Hesabı
```
1. 5000 TL limitli kredi hesabı açın
2. 3000 TL kredi kullanın
3. Aylık faiz ekleyin (60 TL)
4. 1000 TL kredi ödeyin
```

## 🛠️ Teknik Detaylar

### Veri Yapıları
- `ArrayList<Hesap>`: Tüm hesapları dinamik olarak saklar
- `ArrayList<String>`: İşlem geçmişi için
- Static counter'lar: Benzersiz hesap numarası üretimi

### Tarih/Zaman Yönetimi
- `LocalDateTime`: İşlem zamanı kaydı
- `DateTimeFormatter`: Özelleştirilmiş tarih formatı

### Hata Yönetimi
- Try-catch blokları
- IllegalArgumentException fırlatma
- Kullanıcı dostu hata mesajları
  
