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