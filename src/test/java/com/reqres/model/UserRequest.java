package com.reqres.model;

/**
 * Kullanıcı Oluşturma İstek Modeli (POJO – Plain Old Java Object)
 * ──────────────────────────────────────────────────────────────────
 * POST /api/users endpoint'ine gönderilecek JSON Request Body'nin
 * Java karşılığıdır. Jackson kütüphanesi bu nesneyi otomatik olarak
 * JSON formatına serileştirir (serialize eder).
 *
 * Örnek JSON çıktısı:
 * <pre>
 * {
 *   "name": "Ahmet",
 *   "job": "QA Engineer"
 * }
 * </pre>
 *
 * @author Test Mühendisliği Proje Ödevi
 */
public class UserRequest {

    /** Kullanıcının adı */
    private String name;

    /** Kullanıcının mesleği / görevi */
    private String job;

    // ─── Boş Yapıcı (Jackson serileştirme için gerekli) ───
    public UserRequest() {
    }

    // ─── Parametreli Yapıcı (Kolay nesne oluşturma) ───
    public UserRequest(String name, String job) {
        this.name = name;
        this.job = job;
    }

    // ═══════════════════════════════════════════════════════════════
    //  GETTER & SETTER Metodları
    // ═══════════════════════════════════════════════════════════════

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
