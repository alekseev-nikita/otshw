import com.google.common.base.Splitter;

public class HelloOtus {
    public static void main(String[] args) {
        splitMe();
    }

    private static void splitMe(){
        String s = ",a,,b,";
        Iterable<String> splitLIne = Splitter.on(',').split(s);
        splitLIne.forEach(System.out::println);
    }
}
