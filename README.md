# 🏦 Banka Hesap Simülasyonu

**Nesneye Yönelik Programlama Dersi Proje Ödevi**

Java ile geliştirilmiş, konsol tabanlı tam fonksiyonel banka hesap yönetim sistemi.

---

## 📋 Proje Hakkında

Bu proje, temel banka işlemlerini simüle eden ve **OOP (Object-Oriented Programming)** prensiplerini eksiksiz uygulayan bir Java uygulamasıdır. Üç farklı hesap tipi ile çalışır ve kullanıcılara kapsamlı banka işlemleri sunar.

### 🛠️ Geliştirme Ortamı
- **IDE**: IntelliJ IDEA
- **Dil**: Java (JDK 8+)
- **Tarih**: Ekim 2025

---

## ✨ Özellikler

### 💳 Hesap Tipleri

#### 1. Vadesiz Hesap
- Standart banka hesabı işlemleri
- **1000 TL ek hesap limiti**
- Bakiyenin altında para çekme imkanı
- Anlık bakiye görüntüleme

#### 2. Vadeli Hesap
- Vade süresine göre **otomatik faiz hesaplama**
- Faiz oranları:
  - 365+ gün: **%25**
  - 180-364 gün: **%18**
  - 90-179 gün: **%12**
  - 90 günden az: **%8**
- Vade dolunca otomatik faiz ekleme
- Erken çekim uyarı sistemi

#### 3. Kredi Hesabı
- Belirlenen limite kadar kredi kullanımı
- Kredi ödeme sistemi
- **%2 aylık faiz hesaplama**
- Kalan limit takibi

### 🎯 Temel İşlevler

✅ **Hesap Yönetimi**
- Hesap açma (3 farklı tip)
- Hesap kapatma (bakiye 0 kontrolü ile)
- Hesap bilgilerini görüntüleme
- Tüm hesapları listeleme

✅ **Para İşlemleri**
- Para yatırma
- Para çekme
- Bakiye sorgulama
- İşlem geçmişi

✅ **Finansal İşlemler**
- Faiz hesaplama
- Kredi kullanımı
- Kredi ödemesi
- Aylık faiz ekleme

---

## 🧩 OOP Kavramları

Projede kullanılan Nesneye Yönelik Programlama prensipleri:

### 1️⃣ Kapsülleme (Encapsulation)
```java
// Private değişkenler
private String hesapNo;
private double bakiye;

// Public getter metodları
public double getBakiye() {
    return bakiye;
}
```

### 2️⃣ Kalıtım (Inheritance)
```java
// Abstract base class
public abstract class Hesap { }

// Türetilmiş sınıflar
public class VadesizHesap extends Hesap { }
public class VadeliHesap extends Hesap { }
public class KrediHesabi extends Hesap { }
```

### 3️⃣ Soyutlama (Abstraction)
```java
// Abstract metodlar
public abstract void hesapBilgileriGoster();
public abstract String getHesapTipi();
```

### 4️⃣ Çok Biçimlilik (Polymorphism)
```java
// Override edilmiş metodlar
@Override
public boolean paraCek(double miktar) {
    // Her hesap tipi farklı davranış sergiler
}
```

---

## 🚀 Kurulum ve Çalıştırma

### Gereksinimler
- ☕ Java JDK 8 veya üzeri
- 💻 IntelliJ IDEA (önerilir) veya herhangi bir Java IDE

### Adımlar

1. **Projeyi Klonlayın**
```bash
git clone https://github.com/[KULLANICI_ADI]/banka-simulasyonu.git
cd banka-simulasyonu
```

2. **IntelliJ IDEA'da Açın**
```
File > Open > Proje klasörünü seçin
```

3. **Çalıştırın**
```
BankaSimulasyonu.java > Sağ tık > Run 'BankaSimulasyonu.main()'
```

4. **Konsol menüsünden işlemleri gerçekleştirin**

---

## 📖 Kullanım Örnekleri

### Örnek 1: Vadesiz Hesap Açma
```
Ana Menü > 1 (Yeni Hesap Aç)
         > 1 (Vadesiz Hesap)
         > İsim: Ahmet Yılmaz
         > Miktar: 5000

✓ Hesap No: HSP1001
```

### Örnek 2: Para İşlemleri
```
Ana Menü > 2 (Para Yatır)
         > Hesap No: HSP1001
         > Miktar: 2000
         
✓ 2000 TL yatırıldı. Yeni bakiye: 7000 TL
```

### Örnek 3: Vadeli Hesap Faiz Hesaplama
```
Ana Menü > 8 (Vadeli Hesap İşlemleri)
         > Hesap No: HSP1002
         > 1 (Faiz Hesapla)
         
ℹ 1 yıl vade için %25 faiz
ℹ 10000 TL üzerinden 2500 TL faiz kazanacaksınız
```

### Örnek 4: Kredi Kullanımı
```
Ana Menü > 7 (Kredi İşlemleri)
         > Hesap No: HSP1003
         > 1 (Kredi Kullan)
         > Miktar: 5000
         
✓ 5000 TL kredi kullanıldı
Kalan limit: 15000 TL
```

---

## 📂 Proje Yapısı

```
BankaSimulasyonu/
│
├── src/
│   ├── Hesap.java              # Abstract base class
│   ├── VadesizHesap.java       # Vadesiz hesap sınıfı
│   ├── VadeliHesap.java        # Vadeli hesap sınıfı
│   ├── KrediHesabi.java        # Kredi hesabı sınıfı
│   ├── BankaSistemi.java       # Hesap yönetim sistemi
│   └── BankaSimulasyonu.java   # Ana program (Main)
│
├── README.md                   # Bu dosya
├── proje_takimi.txt           # Takım bilgileri
└── .gitignore
```

---

## 👥 Proje Takımı

| Görev | Sorumluluk |
|-------|-----------|
| **Üye 1** | Proje Yöneticisi & Abstract Sınıf Tasarımı |
| **Üye 2** | Vadesiz ve Vadeli Hesap Sınıfları |
| **Üye 3** | Kredi Hesabı ve Finansal Hesaplamalar |
| **Üye 4** | Banka Sistemi ve Menü Yapısı |
| **Üye 5** | Test, Dokümantasyon ve Video |

Detaylı bilgi için [proje_takimi.txt](proje_takimi.txt) dosyasına bakınız.

---

## 🎥 Sunum Videosu

Projenin çalışma mantığını ve OOP kavramlarını açıklayan 10 dakikalık sunum videomuz:

🔗 **[Video Linki Buraya Eklenecek]**

Videoda:
- ✅ Proje tanıtımı
- ✅ OOP kavramlarının açıklaması
- ✅ Canlı kod demonstrasyonu
- ✅ Tüm özelliklerin gösterimi

---

## 📊 Öne Çıkan Kodlar

### Polymorphism Örneği
```java
// Her hesap tipi paraCek() metodunu farklı şekilde kullanır
Hesap hesap1 = new VadesizHesap("Ali", 1000);
Hesap hesap2 = new VadeliHesap("Ayşe", 10000, 365);

hesap1.paraCek(1500); // Ek hesap kullanır
hesap2.paraCek(5000); // Faiz kaybı uyarısı verir
```

### Encapsulation Örneği
```java
// Bakiye direkt değiştirilemez, sadece metodlar üzerinden
public class Hesap {
    private double bakiye; // Private - dışarıdan erişilemez
    
    public void paraYatir(double miktar) {
        if (miktar > 0) {
            this.bakiye += miktar; // Kontrollü erişim
        }
    }
}
```

---

## 🔧 Test Senaryoları

Sistemin test edilmesi için hazırlanmış senaryolar:

1. **Ek Hesap Limiti Testi**: Vadesiz hesaptan bakiyeden fazla para çekme
2. **Faiz Hesaplama Testi**: Farklı vade süreleri için faiz oranları
3. **Kredi Limit Testi**: Limitin üzerinde kredi kullanma denemesi
4. **Hesap Kapatma Testi**: Bakiyesi 0 olmayan hesabı kapatma
5. **Çok Biçimlilik Testi**: Farklı hesap tiplerini aynı listede tutma

---

## 📌 Notlar

- ⚠️ Hesap kapatmak için bakiyenin **0 TL** olması gerekir
- ⚠️ Vadeli hesaptan erken çekim yapılırsa **faiz kaybı** olur
- ⚠️ Kredi hesabında her ay otomatik **%2 faiz** eklenir
- ℹ️ Hesap numaraları otomatik olarak **HSP1001, HSP1002...** şeklinde oluşturulur
- ℹ️ Vadesiz hesaplarda **1000 TL ek hesap limiti** vardır

---

## 📝 Lisans

Bu proje **eğitim amaçlı** geliştirilmiştir. Nesneye Yönelik Programlama dersi kapsamında hazırlanmıştır.

---

## 📧 İletişim

Proje hakkında sorularınız için:
- 📬 GitHub Issues kullanabilirsiniz
- 📧 [E-posta adresiniz]

---

## 🌟 Teşekkürler

Bu projeyi inceleyen ve değerlendiren herkese teşekkür ederiz! 

⭐ Beğendiyseniz yıldız vermeyi unutmayın!

---

**© 2025 - Nesneye Yönelik Programlama Proje Ödevi**
