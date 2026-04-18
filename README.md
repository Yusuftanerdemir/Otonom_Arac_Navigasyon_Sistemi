# Otonom Araç Navigasyon Sistemi: Güvenlik Modülü

Bu proje, 2 boyutlu bir düzlemde tanımlanmış iki farklı engel sınıfı arasında, matematiksel olarak ispatlanmış en güvenli navigasyon rotasını hesaplayan bir güvenlik modülüdür. Sistem, basit bir ayrıştırıcı çizgi yerine, her iki sınıfa da maksimum uzaklıkta kalan bir "Güvenlik Koridoru" (Maximum Margin) oluşturur.

## 🚀 Proje Amacı
Otonom araçlar için geliştirilen bu modül; sensör gürültülerini, LiDAR sapmalarını ve fiziksel manevra kısıtlarını tolere edebilmek adına **Hard-Margin Support Vector Machine (SVM)** algoritmasını kullanır. Algoritma, engelleri sadece ayırmakla kalmaz, konumlama hatalarını tolere edecek en geniş güvenlik alanını hesaplar.

## 🧠 Matematiksel Model
Algoritma, aşağıdaki matematiksel kısıtlar altında bir dışbükey optimizasyon (convex optimization) problemi çözer:

- **Merkez Rota Denklemi:** $w \cdot x + b = 0$
- **Güvenlik Koridoru Genişliği:** $2 / ||w||$
- **Optimizasyon Hedefi:** $\min \frac{1}{2} ||w||^2$ (Koridoru maksimize etmek için ağırlık vektörünü minimize eder).
- **Kısıt (Hard-Margin):** Tüm engel noktaları için $y_i(w \cdot x_i + b) \ge 1$ koşulu sağlanmalıdır.

Bu model, yerel minimum tuzaklarına düşmeden her zaman matematiksel olarak ispatlanmış tek bir mutlak güvenli çözüm üretir.

## 🛠 Teknik Özellikler ve Yazılım Mimarisi
Proje, Nesne Yönelimli Programlama (OOP) prensiplerine ve katmanlı mimariye uygun olarak Java dilinde geliştirilmiştir:

- **Modüler Yapı:** Coordinate (Veri), ObstacleSet (Koleksiyon) ve SafetyOptimizer (Algoritma) sınıfları ile katmanlar arası izolasyon sağlanmıştır.
- **Sıfır Bellek Sızıntısı (Zero Memory Leak):** Optimizasyon döngüsü (Gradient Descent) içerisinde hiçbir şekilde yeni nesne üretimi yapılmaz. Tüm hesaplamalar primitif diziler üzerinden gerçekleştirilerek Java Garbage Collector üzerindeki yük sıfıra indirilmiş ve gerçek zamanlı sürüş güvenliği artırılmıştır.
- **Veri Güvenliği:** Veri modelleri Immutable (değiştirilemez) yapıda tasarlanarak çoklu işlem süreçlerinde veri tutarlılığı garanti altına alınmıştır.

## 📊 Performans Analizi (Big-O)
- **Eğitim (Sınırı İnşa Etme):** $O(N^2)$ (İteratif optimizasyon süreci veri setiyle karesel ilişkilidir.)
- **Çıkarım (Karar Verme):** $O(1)$ (Sınır bulunduktan sonra engel kontrolü sabit zamanda gerçekleşir.)

## 💻 Kurulum ve Çalıştırma
Java IDE'niz (IntelliJ, Eclipse, VS Code) ile projeyi açın.
Main.java dosyasını çalıştırın.

## 📈 Örnek Çıktı
Uygulama çalıştırıldığında konsol üzerinden aşağıdaki veriler üretilir:
Hesaplanan Optimum Sınır Denklemi ($w_1, w_2, b$)
Hesaplanan Güvenlik Koridoru Genişliği (Birim cinsinden)
İşlem Süresi (Milisaniye cinsinden)
