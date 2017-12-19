import com.peramdy.utils.SecurityUtils;
import org.junit.Test;

/**
 * @author peramdy
 * @date 2017/12/19
 */
public class SecurityUtilsTest {

    @Test
    public void md5() {
        String key2 = SecurityUtils.encodeMd5("nihao");
        System.out.println(key2);
    }

    @Test
    public void md5_2() {
        String key2 = SecurityUtils.encodeMd52("nihao");
        System.out.println(key2);
    }

    @Test
    public void base64Encode() {
        String key1 = SecurityUtils.encodeBase64("194ce5d0b89c47ff6b30bfb491f9dc26");
        System.out.println(key1);
    }

    @Test
    public void base64Decode() {
        String key1 = SecurityUtils.descodeBase64("MTk0Y2U1ZDBiODljNDdmZjZiMzBiZmI0OTFmOWRjMjY=");
        System.out.println(key1);
    }

}
