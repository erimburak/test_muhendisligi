# 🧪 Reqres.in API Test Otomasyon Projesi

> **Yazılım Test Mühendisliği – Proje Ödevi**  
> REST API testlerinin Java, Maven, JUnit 5 ve Rest Assured ile otomasyonu.

---

## 📋 Proje Hakkında

Bu proje, [reqres.in](https://reqres.in/) üzerindeki kullanıcı API'lerine yönelik **otomatik entegrasyon testleri** içerir. Clean Code, SOLID prensipleri ve BDD (Given-When-Then) notasyonuyla yazılmıştır.

## 🛠️ Teknoloji Yığını

| Teknoloji       | Versiyon | Açıklama                         |
|-----------------|----------|----------------------------------|
| **Java**        | 17       | Programlama dili                 |
| **Maven**       | 3.9+     | Proje yönetimi ve bağımlılıklar  |
| **JUnit 5**     | 5.11.4   | Test çalıştırıcı (test runner)   |
| **Rest Assured**| 5.5.1    | REST API test kütüphanesi        |
| **Hamcrest**    | 3.0      | Okunabilir assertion kütüphanesi |
| **Jackson**     | 2.18.2   | JSON ↔ POJO serileştirme         |

## 📁 Proje Yapısı

```
reqres-api-test/
├── pom.xml                                    # Maven bağımlılıkları
├── README.md                                  # Bu dosya
├── .gitignore                                 # Git tarafından göz ardı edilecek dosyalar
└── docs/
│   └── test_muhendislik.pdf      # Sunum
└── src/
    └── test/
        └── java/
            └── com/
                └── reqres/
                    ├── base/
                    │   └── BaseTest.java       # Ortak test yapılandırması
                    ├── constants/
                    │   └── ApiConstants.java   # Sabit değerler (URL, endpoint)
                    ├── model/
                    │   └── UserRequest.java    # POST istek modeli (POJO)
                    └── tests/
                        └── ReqresApiTest.java  # GET & POST test senaryoları
```

## 🚀 Testleri Çalıştırma

### Ön Koşullar
- Java 17+ kurulu olmalı
- Maven 3.9+ kurulu olmalı

### Terminal Komutları

```bash
# Tüm testleri çalıştır
mvn clean test

# Belirli bir test sınıfını çalıştır
mvn test -Dtest=ReqresApiTest

# Detaylı çıktı ile çalıştır
mvn test -X
```

## ✅ Test Senaryoları

| #  | Metod | Endpoint          | Doğrulamalar                                        |
|----|-------|-------------------|-----------------------------------------------------|
| 1  | GET   | /api/users/2      | Status 200, body alanları, yanıt süresi < 2s        |
| 2  | POST  | /api/users        | Status 201, body alanları, id oluşturma, süre < 2s  |

## 📊 Sunum

Proje kapsamında hazırlanan **"Yapay Zeka Destekli Yazılım Test Mühendisliği"** sunum    `docs/` klasöründe yer almaktadır.

## 👤 Yazar

- **Proje:** Yazılım Test Mühendisliği Proje Ödevi
- **Tarih:** 2026

## 📄 Lisans

Bu proje eğitim amaçlıdır.
