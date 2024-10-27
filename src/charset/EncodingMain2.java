package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 한글이 깨지는 가장 큰 2가지 이유
 - EUC-KR(MS949), UTF-8이 서로 호환되지 않음
 - 한글이 깨지는 대부분의 문제는 UTF-8로 인코딩한 한글을 EUC-KR(MS949)로 디코딩하거나 반대의 경우이다.
 */

public class EncodingMain2 {
    private static final Charset EUC_KR = Charset.forName("EUC-KR");
    private static final Charset MS_949 = Charset.forName("MS949");

    public static void main(String[] args) {
        System.out.println("== 영문 ASCII 인코딩 ==");
        test("A", StandardCharsets.US_ASCII, StandardCharsets.US_ASCII);
        test("A", StandardCharsets.ISO_8859_1, StandardCharsets.ISO_8859_1);
        test("A", StandardCharsets.US_ASCII, EUC_KR);
        test("A", StandardCharsets.US_ASCII, StandardCharsets.UTF_8);
        test("A", StandardCharsets.US_ASCII, StandardCharsets.UTF_16); //디코딩 실패

        System.out.printf("== 한글 인코딩 - 기본 ==");
        test("가", StandardCharsets.US_ASCII, StandardCharsets.US_ASCII);
        test("가", StandardCharsets.ISO_8859_1, StandardCharsets.ISO_8859_1);
        test("가", EUC_KR, EUC_KR);
        test("가", StandardCharsets.UTF_8, StandardCharsets.UTF_8);
        test("가", StandardCharsets.UTF_16BE, StandardCharsets.UTF_16BE);

        System.out.printf("== 한글 인코딩 - 복잡한 문자 ==");
        test("뷁", EUC_KR, EUC_KR); //실패
        test("뷁", StandardCharsets.UTF_8, StandardCharsets.UTF_8); //성공
        test("뷁", StandardCharsets.UTF_16BE, StandardCharsets.UTF_16BE); //성공

        System.out.printf("== 한글 인코딩 - 디코딩이 다른경우 ==");
        test("뷁", MS_949, EUC_KR); //인코딩은 가능, 디코딩 X (뷁같은 없는 단어는 EUC_KR에 없기때문)
        test("뷁", EUC_KR, StandardCharsets.UTF_8); //x
        test("뷁", MS_949, StandardCharsets.UTF_8); //x (제일 많은 케이스)
        test("뷁", StandardCharsets.UTF_8, MS_949); //x (제일 많은 케이스)

        System.out.printf("== 영문 인코딩 - 디코딩이 다른경우 ==");
        test("A", EUC_KR, StandardCharsets.UTF_8);
        test("A", MS_949, StandardCharsets.UTF_8 );
        test("A", StandardCharsets.UTF_8, StandardCharsets.UTF_16BE); //x

    }

    private static void test(String text, Charset encodingCharset, Charset decodingCharset){
        byte[] encoded = text.getBytes(encodingCharset);
        String decoded = new String(encoded, decodingCharset);
        System.out.printf("%s -> [%s] 인코딩 -> %s %sbyte -> [%s] 디코딩 -> %s\n",
                text, encodingCharset, Arrays.toString(encoded), encoded.length,
                decodingCharset, decoded);
    }
}
