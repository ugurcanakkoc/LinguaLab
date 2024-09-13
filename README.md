# LinguaLab

**LinguaLab**, Türkçe kelimelerin İngilizce ve Fransızca karşılıklarını öğrenirken, telaffuzlarını duyabileceğiniz ve öğrendiğiniz kelimeleri test edebileceğiniz interaktif bir dil öğrenme uygulamasıdır.

## Özellikler

- **Kelime Listesi**: Uygulama içinde Türkçe kelimelerin bulunduğu bir liste mevcut. 
  - Bir kelimeye tıkladığınızda, açılan popup pencerede İngilizce ve Fransızca karşılıklarını görebilir ve sesli olarak telaffuzunu dinleyebilirsiniz.
  - İsterseniz bu kelimeyi **Öğrendiklerim** listesine kaydedebilirsiniz.

- **Öğrendiklerim Sayfası**: Kaydettiğiniz kelimeler bu sayfada listelenir. Yine, kelimeye tıklayarak İngilizce ve Fransızca karşılıklarını görebilir, sesli telaffuzunu dinleyebilirsiniz.

- **Oyun**: 
  - Öğrendiğiniz kelimelerin rastgele İngilizce veya Fransızca karşılıkları gösterilir.
  - Sizden kelimenin Türkçesini yazmanız istenir.
  - Doğru cevaplar için puan toplayarak ilerlersiniz.

## Kullanılan Teknolojiler

- **Kotlin**: Uygulamanın temel programlama dili.
- **SharedPreferences**: Uygulama verilerini kalıcı olarak saklamak için kullanılıyor.
- **Hilt**: Dependency Injection yapısı olarak kullanıldı.
- **MVVM**: Uygulama yapısında **Model-View-ViewModel** prensipleri uygulandı.
- **Jetpack Compose**: UI tasarımları için Compose kullanıldı ve geleneksel XML'e alternatif bir yapı sunuldu.

## Geliştirme

Bu proje, hem geleneksel XML UI yapısı hem de modern **Jetpack Compose** ile geliştirildi. SharedPreferences ve Hilt ile veri yönetimi ve bağımlılıkları kontrol altında tutarken, mümkün olduğunca MVVM yapısı kullanılmıştır.
