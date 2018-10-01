import io.github.biezhi.ome.OhMyEmail;
import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;
import java.security.GeneralSecurityException;
import static io.github.biezhi.ome.OhMyEmail.SMTP_QQ;

public class TestMail {
    @Before
    public void before() throws GeneralSecurityException {
        // 配置，一次即可
        OhMyEmail.config(SMTP_QQ(true), "544926130@qq.com", "1234567890!");
    }

    @Test
    public void testSendText() throws MessagingException {
        OhMyEmail.subject("这是一封测试TEXT邮件")
                .from("544926130@qq.com")
                .to("544926130@qq.com")
                .text("信件内容")
                .send();
    }
}
