import java.util.function.BiConsumer;
public class helofunctional {

    public static void main(String[] args) {
        BiConsumer<String,Integer> panggil=
        (nama, nim ) -> System.out.println("hallo,"+ nama + "_"+ nim);
        panggil.accept("ade miko", 240202827);
    }
} 