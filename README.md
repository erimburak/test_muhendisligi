# 🧪 Reqres.in API Test Otomasyon Projesi

[![Java Version](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)
[![Rest Assured](https://img.shields.io/badge/Rest--Assured-5.5.1-green.svg)](https://rest-assured.io/)
[![JUnit 5](https://img.shields.io/badge/JUnit-5.11.4-blue.svg)](https://junit.org/junit5/)
[![Maven](https://img.shields.io/badge/Maven-3.9%2B-red.svg)](https://maven.apache.org/)

> **Yazılım Test Mühendisliği – Proje Ödevi**  
> Reqres.in REST API testlerinin Java, JUnit 5, Rest Assured ve Maven kullanılarak modern tasarım kalıplarıyla (BaseTest, POJO, Constants) uçtan uca otomasyonu.

---

## 📋 Proje Hakkında

Bu proje, popüler [reqres.in](https://reqres.in/) test servisi üzerindeki kullanıcı yönetimi (users) API'lerini test etmek amacıyla geliştirilmiş, **profesyonel standartlarda bir test otomasyon altyapısıdır**.

Projede; **Clean Code**, **SOLID Prensipleri**, **DRY (Don't Repeat Yourself)** yaklaşımı ve **BDD (Given-When-Then)** notasyonu titizlikle uygulanmıştır. Testler, yanıt süreleri (Response Time), veri şeması doğruluğu ve HTTP durum kodları (Status Codes) dahil olmak üzere kritik kriterleri doğrular.

---

## 🛠️ Tasarım Kalıpları ve Mimari Yaklaşım

Projenin sürdürülebilir, modüler ve genişletilebilir olması için aşağıdaki mimari yaklaşımlar benimsenmiştir:

*   **Ortak Yapılandırma (`BaseTest`):** Tekrarlanan base URI, Header bilgileri, `Content-Type` ve `Accept` tanımlamaları merkezi bir `requestSpec` (Request Specification) içinde birleştirilmiştir. Tüm test sınıfları bu sınıfı miras alır.
*   **POJO (Plain Old Java Object) & DTO Modeli (`UserRequest`):** İstek gövdeleri (request payload) sabit stringler yerine Java nesneleri (`UserRequest`) üzerinden tanımlanarak tip güvenliği (Type Safety) sağlanmıştır. Jackson kütüphanesi yardımıyla bu nesneler otomatik olarak JSON formatına serileştirilir.
*   **Merkezi Sabitler (`ApiConstants`):** Projede "Sihirli Metinler" (Magic Strings) kullanılmamış, tüm Endpoint URL'leri ve performans eşik değerleri (örneğin maksimum yanıt süresi) tek bir sabitler sınıfında tanımlanmıştır.
*   **Çıktı & Durum Takibi (Status Logging):** Konsol çıktılarında hem API'nin döndürdüğü **HTTP Durum Kodları (Status Codes)** hem de **Response Body** (Yanıt Gövdesi) anlık olarak loglanarak hata ayıklama (debugging) süreci kolaylaştırılmıştır.

---

## 📁 Proje Dizin Yapısı

```directory
reqres-api-test/
├── pom.xml                                    # Maven bağımlılıkları ve plugin yapılandırmaları
├── README.md                                  # Proje dokümantasyonu
├── .gitignore                                 # Sürüm kontrol sistemi dışlama dosyası
├── docs/
│   └── test_muhendislik.pdf                   # "Yapay Zeka Destekli Yazılım Test Mühendisliği" sunumu
└── src/
    └── test/
        └── java/
            └── com/
                └── reqres/
                    ├── base/
                    │   └── BaseTest.java       # Ortak test konfigürasyonu ve Request Specification
                    ├── constants/
                    │   └── ApiConstants.java   # URL, Endpoint ve zaman aşımı limitleri
                    ├── model/
                    │   └── UserRequest.java    # Kullanıcı oluşturma isteği için POJO modeli
                    └── tests/
                        └── ReqresApiTest.java  # HTTP GET ve POST entegrasyon test senaryoları
```

---

## 🚀 Kurulum ve Testleri Çalıştırma

### Ön Koşullar

Projenin çalıştırılabilmesi için sisteminizde aşağıdaki bileşenlerin yüklü olması gerekir:

1.  **JDK 17 veya üzeri** (Örn: JDK 23)
2.  **Apache Maven 3.9+**

### `JAVA_HOME` Yapılandırması (Önemli)

Maven'ın doğru Java sürümünü kullanabilmesi için `JAVA_HOME` ortam değişkeninin kurulu Java dizinini göstermesi gerekir.

*   **Windows (PowerShell) için geçici tanımlama:**
    ```powershell
    $env:JAVA_HOME = "C:\Program Files\Java\jdk-23"
    ```
*   **Kalıcı Tanımlama (Windows System Settings):**
    `Sistem Ortam Değişkenleri` panelinden `JAVA_HOME` değişkenini Java kurulum dizininize (örneğin `C:\Program Files\Java\jdk-23`) yönlendirin ve `Path` değişkenine `%JAVA_HOME%\bin` ekleyin.

### Terminal Komutları

Sisteminizde global Maven kurulu olmasa dahi, projeyle birlikte gelen **Maven Wrapper**'ı (`mvnw`) kullanarak testlerinizi doğrudan çalıştırabilirsiniz. 

Projenin ana dizininde (root) aşağıdaki komutları kullanın:

#### Windows (PowerShell) için:
```powershell
# Projeyi temizle ve tüm testleri çalıştır
.\mvnw clean test

# Sadece belirli bir test sınıfını çalıştır
.\mvnw test -Dtest=ReqresApiTest
```

#### macOS / Linux / Git Bash için:
```bash
# Çalıştırma yetkisi verin (gerekliyse)
chmod +x mvnw

# Projeyi temizle ve tüm testleri çalıştır
./mvnw clean test

# Sadece belirli bir test sınıfını çalıştır
./mvnw test -Dtest=ReqresApiTest
```


---

## ✅ Test Kapsamı ve Senaryoları

Testler, `ReqresApiTest` sınıfı altında BDD biçiminde kurgulanmıştır:

| Sıra | Metot | Endpoint | Doğrulama & Kontrol Noktaları |
| :--- | :---: | :--- | :--- |
| **1** | `GET` | `/api/users/2` | • HTTP **200 OK** kontrolü<br>• `data` içindeki alanların doğruluğu (id, email, first_name vb.)<br>• Yanıt süresi kontrolü (<2000ms) |
| **2** | `POST` | `/api/users` | • HTTP **201 Created** kontrolü<br>• İstek nesnesiyle dönen verilerin eşleşmesi (name, job)<br>• Otomatik oluşturulan `id` ve `createdAt` varlık kontrolü<br>• Yanıt süresi kontrolü (<2000ms) |

---

## 📊 Örnek Konsol Çıktısı (Console Output)

Testler başarıyla tamamlandığında konsola düşen HTTP Durum Kodu ve JSON Yanıt Gövdesi örnekleri aşağıdaki gibidir:

### 1. GET (Tek Kullanıcı Getirme) Test Çıktısı:
```text
HTTP/1.1 200 OK
{
    "data": {
        "id": 2,
        "email": "janet.weaver@reqres.in",
        "first_name": "Janet",
        "last_name": "Weaver",
        "avatar": "https://reqres.in/img/faces/2-image.jpg"
    },
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}
```

### 2. POST (Yeni Kullanıcı Oluşturma) Test Çıktısı:
```text
HTTP/1.1 201 Created
{
    "name": "Ahmet Yılmaz",
    "job": "QA Engineer",
    "id": "184",
    "createdAt": "2026-06-18T16:24:58.643Z"
}
```

---

## 📈 Akademik Sunum

Proje kapsamında hazırlanan ve modern test mühendisliğindeki yapay zeka yaklaşımlarını ele alan **"Yapay Zeka Destekli Yazılım Test Mühendisliği"** adlı sunum dosyasına aşağıdaki dizinden erişebilirsiniz:

📁 Dizin: `docs/test_muhendislik.pdf`

---

## 👤 Proje Sahibi

*   **Ders/Ödev:** Yazılım Test Mühendisliği Proje Ödevi
*   **Geliştirici:** Test Mühendisliği Öğrencisi
*   **Tarih:** Haziran 2026
