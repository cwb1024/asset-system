import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class TestApacheEmail {
    //发送文本邮件
    public static void sendTextMail(){
        Email email = new SimpleEmail();
        email.setHostName("smtp.qq.com");
        email.setSmtpPort(25);
        email.setAuthenticator(new DefaultAuthenticator("544926130@qq.com", "1234567890!"));
        email.setTLS(true);
        try {
            email.setFrom("544926130@qq.com");
            email.setMsg("This is a test mail__common_email");
            email.addTo("17611259087@163.com");
            email.setSubject("TestMail文本邮件");
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws EmailException {
        sendTextMail();
    }
}
