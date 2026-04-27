Software Quality Evaluation Tool (ISO/IEC 25010 & 25023)

Bu uygulama, yazılım sistemlerinin kalitesini **ISO/IEC 25010** standartlarına göre ölçmek için geliştirilmiştir. Teknik metrikler **ISO/IEC 25023** formülleri kullanılarak analiz edilir.
 Kalite Karakteristikleri
Proje kapsamında aşağıdaki boyutlar değerlendirilmektedir:
- Functional Suitability (Fonksiyonel Uygunluk)
- Reliability (Güvenilirlik)
- Performance Efficiency (Performans Verimliliği)
- Maintainability (Bakım Yapılabilirlik)
- Security (Güvenlik)

  Hesaplama Mantığı
- Higher is Better:** $1 + ((Değer - Min) / (Max - Min)) * 4$
- Lower is Better:** $5 - ((Değer - Min) / (Max - Min)) * 4$
- Tüm puanlar 1-5 arasına sabitlenir ve en yakın 0.5'e yuvarlanır.

 📂 Dosya Yapısı
- `Criterion.java`: Metrik puanlama mantığı.
- `QualityDimension.java`: Karakteristik puan ortalamaları.
- `SWSystem.java`: Sistem raporlama ve analiz.
- `SWSystemData.java`: Sistem verileri (ShopSphere, GlobalBank).
- `Main.java`: Uygulama giriş noktası.