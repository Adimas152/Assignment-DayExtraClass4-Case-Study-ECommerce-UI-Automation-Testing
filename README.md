# Assignment Day Extra Class 4 – Case Study: E-Commerce UI Automation Testing (Shopify)

Automation Web UI Testing menggunakan **Selenium + TestNG + Gradle** pada website:
- https://sauce-demo.myshopify.com/

---

## 1) Preparation
### Tools & Framework
- Java
- Selenium WebDriver
- TestNG
- Gradle
- IntelliJ IDEA (IDE)
- Browser: Chrome / Firefox (sesuai parameter di suite)

### Test Environment
Konfigurasi environment menggunakan file properties, contoh:
- `src/test/resources/config/staging.properties`

---

## 2) Website/App Analysis (Elemen penting yang diuji)
Fokus automation test pada flow E2E checkout dan validasi pembayaran:

### Main Features Covered
1. **Catalog / Product Detail**
    - Akses katalog produk
    - Pilih produk pertama yang tersedia
    - Klik **Add to cart**
2. **Cart**
    - Akses halaman cart
    - Klik **Checkout**
3. **Checkout – Contact & Delivery**
    - Input email, nama, alamat, kota, provinsi, postal code, phone
4. **Checkout – Payment Validation**
    - Input kartu kredit via **iframe** (Shopify card fields)
    - Validasi error saat kartu tidak valid
    - Assertion error message:
        - Banner error payment
        - Field error card number / expiry

---