package com.reqres.base;

import com.reqres.constants.ApiConstants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

/**
 * Temel Test Sınıfı (Base Test)
 * ──────────────────────────────
 * Tüm test sınıflarının miras alacağı (extend edeceği) ortak
 * yapılandırma sınıfıdır. Bu sayede:
 *
 *   • Her testte tekrar eden baseURI, contentType gibi ayarlar
 *     tek bir yerde tanımlanır (DRY prensibi).
 *   • Yeni test sınıfları eklendiğinde sadece bu sınıfı extend
 *     etmek yeterlidir.
 *   • Loglamanın merkezi yönetimi sağlanır.
 *
 * @author Test Mühendisliği Proje Ödevi
 */
public abstract class BaseTest {

    /** Tüm testlerde kullanılacak ortak istek özelliklerini (spec) tutar */
    protected static RequestSpecification requestSpec;

    /**
     * Testler çalışmadan ÖNCE bir kez çalışır (JUnit 5 @BeforeAll).
     * Rest Assured'ın global ayarlarını ve ortak request specification'ı
     * burada yapılandırıyoruz.
     */
    @BeforeAll
    static void setupAll() {

        // ── Global Base URI ayarı ──
        RestAssured.baseURI = ApiConstants.BASE_URL;

        // ── Ortak Request Specification oluşturma ──
        // Her istekte tekrar yazmamak için Content-Type ve Accept
        // başlıklarını burada bir kez belirliyoruz.
        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("x-api-key", "free_user_3DismabOaJpBXoHwY48dCDclfba") // YENİ EKLENEN SATIR
                .build();

    }
}
