package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.*;

/*
문자를 컴퓨터가 이해할 수있는 숫자(byte)로 변경하는 것을 문자 인코딩이라 한다.
String.getByte(Charset)메서드를 사용하면 String 문자를 byte 배열로 변경할수 있다.
이때 중요한 점이 있는데, 문자를 byte로 변경하려면 문자 집합이 필요하다는 것이다. 따라서 어떤 문자 집합을 참고해서 byte로 변경할지 정해야 한다.
String.getByte()의 인자로 Charset 객체를 전달하면 된다.
- 문자 집합을 지정하지 않으면 현재 시스템에서 사용하는 기본 문자 집합을 인코딩에서 사용한다.

*정리
자바의 byte를 사용해도 실제 메모리에 저장되는 값은 동일이다. (10110000, 1010001)
자바의 byte 타입이 첫 비트로 음수를 표현하기 때문에, 화면에 보여지는 10진수 숫자만 다를뿐이다.
 */

public class EncodingMain1 {

    private static final Charset EUC_KR = Charset.forName("EUC-KR");
    private static final Charset MS_949 = Charset.forName("MS949");

    public static void main(String[] args) {
        System.out.println("== ASCII 영문 처리 ==");
        encoding("A", US_ASCII);
        encoding("A", ISO_8859_1);
        encoding("A", EUC_KR);
        encoding("A", UTF_8);
        encoding("A", UTF_16BE); //호환이 안된다. 2바이트 단위이기 때문에

        System.out.println("== 한글 지원 ==");
        encoding("가", EUC_KR);
        encoding("가", MS_949);
        encoding("가", UTF_8); //3바이트를 사용한다.
        encoding("가", UTF_16BE); //2바이트를 사용한다.

        String str = "hello";
        byte[] bytes = str.getBytes();
        System.out.println("bytes = " + Arrays.toString(bytes  )); //5바이트 사용

    }

    private static void encoding(String text, Charset charset){
        byte[] bytes = text.getBytes(charset);
        System.out.printf("%s -> [%s] 인코딩 -> %s %sbyte\n", text, charset, Arrays.toString(bytes), bytes.length);
    }
}
