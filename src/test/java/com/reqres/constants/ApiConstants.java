package com.reqres.constants;

/**
 * API Sabitleri (Constants) Sınıfı
 * ─────────────────────────────────
 * Bu sınıf, test projesi genelinde kullanılan sabit değerleri
 * tek bir noktada toplar. Bu yaklaşım:
 *
 *   • "Magic String" kullanımını ortadan kaldırır.
 *   • Bakım kolaylığı sağlar (URL değişirse tek yerden güncellenir).
 *   • Clean Code ve DRY (Don't Repeat Yourself) ilkelerine uygundur.
 *
 * @author Test Mühendisliği Proje Ödevi
 */
public final class ApiConstants {

    // ─── Yapıcı metot private: Bu sınıftan nesne üretilmesi engellenir ───
    private ApiConstants() {
        throw new UnsupportedOperationException(
                "ApiConstants bir utility sınıfıdır, örneklenemez!");
    }

    // ═══════════════════════════════════════════════════════════════
    //  BASE URL
    // ═══════════════════════════════════════════════════════════════

    /** reqres.in API'sinin temel URL adresi */
    public static final String BASE_URL = "https://reqres.in";

    // ═══════════════════════════════════════════════════════════════
    //  ENDPOINT'LER
    // ═══════════════════════════════════════════════════════════════

    /** Tek kullanıcı getirme endpoint'i (parametre olarak id verilir) */
    public static final String SINGLE_USER = "/api/users/{id}";

    /** Kullanıcı listesi endpoint'i */
    public static final String USERS_LIST = "/api/users";

    /** Yeni kullanıcı oluşturma endpoint'i */
    public static final String CREATE_USER = "/api/users";

    // ═══════════════════════════════════════════════════════════════
    //  PERFORMANS EŞİK DEĞERLERİ
    // ═══════════════════════════════════════════════════════════════

    /** Kabul edilebilir maksimum yanıt süresi (milisaniye cinsinden) */
    public static final long MAX_RESPONSE_TIME_MS = 2000L;
}
