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
    
