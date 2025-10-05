# Laporan Praktikum Minggu 1 (sesuaikan minggu ke berapa?)
Topik: [Implementasi “Hello World” Menggunakan Tiga Paradigma Pemrograman di Java}
## Identitas
- Nama  : Ade Miko
- NIM   : 240202827
- Kelas : 3IKRA

---

## Tujuan
1. Mahasiswa dapat memahami konsep tiga dasar paradigma pemrograman: procedural, oop, dan fungcional. ( dalam bahasa java)
2. Mahasiswa dapat mampu mengimplementasikan program sederhana “Hello World, Im [nama] - [nim]” (Hello word, im ade miko-2402022827) menggunakan ketiga paradigma tersebut.
3. Mahasiswa dapat mengenali perbedaan gaya penulisan kode dan pola pikir dalam krtiga pemrograman tersebut.
4. Mahasiswa menguasai dasar penggunaan method, class–object, dan lambda expression pada bahasa Java.
---

## Dasar Teori
1. Paradigma Prosedural
Paradigma prosedural adalah gaya pemrograman yang berfokus pada urutan langkah atau instruksi. Program dibangun dengan memanfaatkan fungsi atau prosedur untuk menyelesaikan suatu tugas. Kelebihannya adalah sederhana dan mudah dipahami untuk program kecil, namun untuk program besar menjadi sulit dikelola.

2. Paradigma Berorientasi Objek (OOP)
Paradigma OOP memandang program sebagai kumpulan objek yang memiliki atribut (data/properti) dan perilaku (method/fungsi). Konsep utama dalam OOP adalah enkapsulasi, pewarisan, dan polimorfisme. Dengan pendekatan ini, program menjadi lebih modular, terstruktur, dan mudah dikembangkan.

3. Paradigma Fungsional
Paradigma fungsional memandang komputasi sebagai evaluasi fungsi matematika. Program dibangun dengan fungsi-fungsi yang bersifat murni (pure function) dan menghindari perubahan data secara langsung (immutable). Dalam Java, paradigma ini dapat diimplementasikan menggunakan lambda expression atau functional interface.

Dalam pemrograman berorientasi objek (Object-Oriented Programming / OOP), terdapat beberapa konsep dasar yang sangat penting dipahami, yaitu:

1. Class
Class adalah blueprint atau cetak biru dari objek. Di dalam class didefinisikan atribut (data/properti) dan method (fungsi/perilaku) yang nantinya akan dimiliki oleh objek. Dengan adanya class, kode program menjadi lebih terstruktur dan dapat digunakan kembali (reusable).

2. Object
Object adalah hasil instansiasi dari class. Jika class dianalogikan sebagai cetak biru rumah, maka object adalah rumah nyata yang dibangun dari cetak biru tersebut. Setiap object memiliki data dan perilaku sesuai dengan class yang membangunnya.

3. Enkapsulasi
Enkapsulasi adalah mekanisme penyembunyian data dari akses langsung oleh luar class, dengan cara membatasi akses atribut dan hanya memperbolehkan manipulasi data melalui method tertentu. Konsep ini menjaga keamanan data dan mengurangi ketergantungan antarbagian program.

---

## Langkah Praktikum
Langkah Praktikum

1. Persiapan dan Setup

   .Membuat folder proyek oop-202501-240202827 di Visual Studio Code.
   .Menyiapkan struktur folder src untuk menyimpan file Java.
   .Menggunakan Java Development Kit (JDK) versi terbaru agar program dapat dijalankan.

2. Pembuatan File/Kode Program

.Membuat file helloProcedural.java → berisi implementasi program dengan paradigma Prosedural, menggunakan method dan tanpa method.
.Membuat file helloOOP.java → berisi implementasi program dengan paradigma OOP, menggunakan class mahasiswa dengan atribut dan method.
.Membuat file helofunctional.java → berisi implementasi program dengan paradigma Fungsional, menggunakan BiConsumer dan lambda expression.

3. Proses Coding

.Menuliskan kode program sesuai paradigma masing-masing.
.Menyimpan file .java dalam folder src.
.Melakukan pengecekan sintaks agar tidak terjadi error.

4. Menjalankan Program

.Meng-compile program menggunakan perintah javac (jika manual) atau langsung menggunakan fitur Run di VS Code.
.Menjalankan file hasil compile (.class) sehingga muncul output:
   Paradigma Prosedural → "Hello World I am ade miko - 240202827"
   Paradigma OOP → "hallow, ade miko - 240202827"
   Paradigma Fungsional → "hallo, ade miko - 240202827"

5. Commit ke Git

Setelah program berhasil dijalankan, mahasiswa melakukan commit ke repository Git dengan pesan commit misalnya:
"Add hello world program using procedural paradigm"
"Add hello world program using OOP paradigm"
"Add hello world program using functional paradigm"
---

## Kode Program

```java
// procedural
public class helloProcedural {

    //menggunakan method:
    public static void sapa(String nama, String nim){
        System.out.println("Hello World I'm "+ nama + " -"+ nim);
    }
    
    //tidak menggunakan method
    public static void main(String[] args) {
         
        String nama = "ade miko";
        String nim = "240202827";

        System.out.println( "Hellow word, im " + nama +"-" + nim);

        sapa("ade miko ", "240202827");
        
    } 
         
   }
    
```
```java
//OOP
class mahasiswa {
    String nama;
    int nim; 

    mahasiswa(String nm, int ni){nama=nm; nim=ni; }
    void panggil(){ System.out.println( "hallow, "+ nama + "_" + nim);}
}
public class helloOOP {

    public static void main(String[] args) {
        mahasiswa m = new mahasiswa("ade miko", 240202827);
        m.panggil();
    }
}
```
```JAVA
//FUNCIONAL
import java.util.function.BiConsumer;
public class helofunctional {

    public static void main(String[] args) {
        BiConsumer<String,Integer> panggil=
        (nama, nim ) -> System.out.println("hallo,"+ nama + "_"+ nim);
        panggil.accept("ade miko", 240202827);
    }
}
```
---

## Hasil Eksekusi
(
1. procedural
![Screenshot hasil](screenshots/1.procedural.jpg)'

2. OOP 
![Screenshot hasil](screenshots/2.OOP.jpg)'

3. Funcional
![Screenshot hasil](screenshots/3.FUNCIONAL.jpg)'

)
---

## Analisis
(
1. Jalannya Kode Program
   -Prosedural: kode dijalankan berurutan dalam main().
   -OOP: membuat class dan object, lalu memanggil method untuk menampilkan output.
   -Fungsional: menggunakan lambda expression untuk mencetak output.
2. Perbedaan dengan Minggu Sebelumnya
   -Minggu ini membandingkan tiga paradigma: Prosedural, OOP, dan Fungsional.
3. Kendala dan Solusi
Kendala: bingung membedakan implementasi, error nama class/file, sulit memahami lambda.
Solusi: membaca teori lagi, mencoba kode bertahap, dan memperhatikan pesan error compiler.
)
---

## Kesimpulan
Praktikum minggu ini memberikan pemahaman tentang tiga paradigma pemrograman, yaitu prosedural, berorientasi objek (OOP), dan fungsional. Dari percobaan yang dilakukan dapat disimpulkan bahwa paradigma prosedural menjalankan kode secara berurutan dan sederhana, paradigma OOP menggunakan class dan object sehingga program menjadi lebih terstruktur dan mudah dikembangkan, sedangkan paradigma fungsional dengan lambda expression membuat kode lebih ringkas dan efisien. Dengan mempelajari ketiganya, mahasiswa dapat memahami perbedaan cara kerja setiap paradigma serta mengetahui kelebihan masing-masing pendekatan dalam menyelesaikan permasalahan pemrograman.

---

## Quiz
(1. [Tuliskan kembali pertanyaan 1 dari panduan]  
1. Apakah OOP selalu lebih baik dari prosedural?
   OOP tidak selalu lebih baik daripada prosedural, karena OOP cocok untuk aplikasi yang kompleks dan membutuhkan modularitas, sedangkan prosedural lebih sederhana dan cepat untuk program kecil.
2. Kapan functional programming lebih cocok digunakandibanding OOP atau prosedural?
   Functional programming lebih cocok digunakan ketika program banyak melakukan manipulasi data, membutuhkan kode yang bebas efek samping, atau dijalankan secara paralel, misalnya pada aplikasi data-intensive.
3. Bagaimana paradigma (prosedural, OOP, fungsional) memengaruhi maintainability dan scalability aplikasi?
   Paradigma yang digunakan memengaruhi maintainability dan scalability aplikasi; prosedural cenderung sulit dikelola dan kurang scalable saat proyek besar, OOP lebih modular dan mudah dikembangkan sehingga lebih scalable, sedangkan fungsional membuat kode mudah diuji dan prediktif serta scalable untuk aplikasi data-intensive.
4. Mengapa OOP lebih cocok untuk mengembangkan aplikasi POS dibanding prosedural?
   OOP sangat cocok untuk mengembangkan aplikasi POS karena POS memiliki banyak entitas seperti produk, transaksi, dan pelanggan yang bisa dijadikan objek, serta memudahkan penambahan fitur baru tanpa merusak kode lama.
5. Bagaimana paradigma fungsional dapat membantu Mengurangi kode berulang (boilerplate code)?
   Paradigma fungsional membantu mengurangi kode berulang dengan fungsi yang reusable dan pure, serta memanfaatkan higher-order functions dan composition sehingga operasi yang sama dapat dilakukan hanya dengan beberapa baris kode.


2. [Tuliskan kembali pertanyaan 2 dari panduan]  
   **Jawaban:** …  

3. [Tuliskan kembali pertanyaan 3 dari panduan]  
   **Jawaban:** …  )
