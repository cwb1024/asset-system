package com.chengwenbi.util;

import com.chengwenbi.domain.entity.Mail;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailUtil {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public boolean send(Mail mail) {
        // 发送email
        HtmlEmail email = new HtmlEmail();
        try {
            // 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
            email.setHostName(EmailPropertyUtil.getString("mailHost"));
            // 字符编码集的设置
            email.setCharset(Mail.ENCODEING);
            // 收件人的邮箱
            email.addTo(mail.getReceiver());
            // 发送人的邮箱
            email.setFrom(EmailPropertyUtil.getString("mailUsername"), "软件学院资产管理系统");
            // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
            email.setAuthentication(EmailPropertyUtil.getString("mailUsername"),EmailPropertyUtil.getString("mailPassword"));
            // 要发送的邮件主题
            email.setSubject("软件学院资产管理系统通知消息");
            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
            email.setMsg(mail.getMessage());
            // 发送
            email.send();
            if (logger.isDebugEnabled()) {
                logger.debug(EmailPropertyUtil.getString("mailUsername") + " 发送邮件到 " + mail.getReceiver());
            }
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
            logger.info(EmailPropertyUtil.getString("mailUsername") + " 发送邮件到 " + mail.getReceiver()
                    + " 失败");
            return false;
        }
    }

    public static void main(String[] args) {
        Mail mail1 = new Mail();
        mail1.setReceiver("1159076075@qq.com");
        mail1.setMessage("哈哈哈");
        new MailUtil().send(mail1);
    }

}
