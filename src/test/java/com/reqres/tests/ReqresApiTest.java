package com.reqres.tests;

import com.reqres.base.BaseTest;
import com.reqres.constants.ApiConstants;
import com.reqres.model.UserRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import java.util.concurrent.TimeUnit;

/**
 * Reqres.in API Test Sınıfı
 * ──────────────────────────
 * Bu sınıf, reqres.in üzerindeki kullanıcı (users) endpoint'lerine
 * yönelik entegrasyon testlerini içerir.
 *
 * Test Kapsamı:
 *   1. GET  /api/users/{id}  → Tek kullanıcı sorgulama
 *   2. POST /api/users       → Yeni kullanıcı oluşturma
 *
 * Her test metodu BDD (Given-When-Then) notasyonuyla yazılmıştır.
 * Bu sayede testler okunabilir ve bakımı kolay hâle gelir.
 *
 * @author Test Mühendisliği Proje Ödevi
 */
@DisplayName("Reqres.in API Testleri")
class ReqresApiTest extends BaseTest {

    // ═══════════════════════════════════════════════════════════════
    //  TEST 1: GET – Tek Kullanıcı Getirme
    // ═══════════════════════════════════════════════════════════════

    @Test
    @Order(1)
    @DisplayName("GET /api/users/2 → Kullanıcı bilgileri başarıyla döner")
    void kullaniciBilgileriniGetir_BasariliSonucDoner() {
        /*
         * ── SENARYO ──
         * reqres.in API'sindeki id=2 olan kullanıcıyı sorguluyoruz.
         * Beklentilerimiz:
         *   • HTTP 200 OK dönmeli
         *   • Response body "data" objesi içinde doğru alanlar olmalı
         *   • Yanıt süresi 2 saniyenin altında olmalı
         */

        given()
            .spec(requestSpec)                          // Ortak istek ayarlarını kullan
            .pathParam("id", 2)                         // URL'deki {id} yerine 2 koy

        .when()
            .get(ApiConstants.SINGLE_USER)              // GET isteği gönder

        .then()
            .log().body()                               // Yanıt gövdesini konsola yazdır (debug)

            // ── 1) Status Code Doğrulaması ──
            // API'nin 200 OK döndüğünü kontrol ediyoruz
            .statusCode(200)

            // ── 2) Response Body Doğrulamaları ──
            // "data" objesi içindeki alanları kontrol ediyoruz

            // Kullanıcı ID'sinin 2 olduğunu doğrula
            .body("data.id", equalTo(2))

            // E-posta adresinin boş olmadığını ve doğru değeri içerdiğini doğrula
            .body("data.email", notNullValue())
            .body("data.email", equalTo("janet.weaver@reqres.in"))

            // Kullanıcının adının "Janet" olduğunu doğrula
            .body("data.first_name", equalTo("Janet"))

            // Kullanıcının soyadının "Weaver" olduğunu doğrula
            .body("data.last_name", equalTo("Weaver"))

            // Avatar URL'inin boş olmadığını doğrula
            .body("data.avatar", notNullValue())

            // "support" objesi içinde bir URL alanı olduğunu doğrula
            .body("support.url", notNullValue())

            // ── 3) Yanıt Süresi (Response Time) Doğrulaması ──
            // API yanıtının 2 saniyeden (2000 ms) kısa sürede gelmesini bekliyoruz
            .time(lessThan(ApiConstants.MAX_RESPONSE_TIME_MS), TimeUnit.MILLISECONDS);
    }

    // ═══════════════════════════════════════════════════════════════
    //  TEST 2: POST – Yeni Kullanıcı Oluşturma
    // ═══════════════════════════════════════════════════════════════

    @Test
    @Order(2)
    @DisplayName("POST /api/users → Yeni kullanıcı başarıyla oluşturulur")
    void yeniKullaniciOlustur_BasariliSonucDoner() {
        /*
         * ── SENARYO ──
         * reqres.in API'sine yeni bir kullanıcı oluşturma isteği
         * gönderiyoruz (POJO → JSON serileştirme ile).
         * Beklentilerimiz:
         *   • HTTP 201 Created dönmeli
         *   • Response body'de gönderdiğimiz "name" ve "job" alanları olmalı
         *   • Otomatik üretilen "id" ve "createdAt" alanları boş olmamalı
         *   • Yanıt süresi 2 saniyenin altında olmalı
         */

        // ── İstek gövdesini (Request Body) POJO ile oluşturuyoruz ──
        // Bu yaklaşım String birleştirmeden çok daha güvenli ve okunabilirdir.
        UserRequest yeniKullanici = new UserRequest("Ahmet Yılmaz", "QA Engineer");

        given()
            .spec(requestSpec)                          // Ortak istek ayarlarını kullan
            .body(yeniKullanici)                        // POJO otomatik olarak JSON'a dönüştürülür

        .when()
            .post(ApiConstants.CREATE_USER)             // POST isteği gönder

        .then()
            .log().body()                               // Yanıt gövdesini konsola yazdır (debug)

            // ── 1) Status Code Doğrulaması ──
            // Yeni kaynak oluşturulduğunda HTTP 201 Created bekliyoruz
            .statusCode(201)

            // ── 2) Response Body Doğrulamaları ──

            // Gönderdiğimiz "name" değerinin yanıtta aynen dönmesini bekliyoruz
            .body("name", equalTo("Ahmet Yılmaz"))

            // Gönderdiğimiz "job" değerinin yanıtta aynen dönmesini bekliyoruz
            .body("job", equalTo("QA Engineer"))

            // API'nin otomatik olarak bir "id" üretip döndürdüğünü doğruluyoruz
            .body("id", notNullValue())

            // Oluşturulma tarih/saat bilgisinin dönmesini bekliyoruz
            .body("createdAt", notNullValue())

            // ── 3) Yanıt Süresi (Response Time) Doğrulaması ──
            // API yanıtının 2 saniyeden kısa sürede gelmesini bekliyoruz
            .time(lessThan(ApiConstants.MAX_RESPONSE_TIME_MS), TimeUnit.MILLISECONDS);
    }
}
