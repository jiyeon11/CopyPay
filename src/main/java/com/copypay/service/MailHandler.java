package com.copypay.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailHandler {
    private static final Logger logger = LoggerFactory.getLogger(MailHandler.class);
    private final JavaMailSender sender;
    private final MimeMessage message;
    private final MimeMessageHelper messageHelper;

    public MailHandler(JavaMailSender jSender) throws MessagingException {
        this.sender = jSender;
        message = jSender.createMimeMessage();
        messageHelper = new MimeMessageHelper(message, true, "UTF-8");
    }
    public void setFrom(String fromAddress) throws MessagingException {
        messageHelper.setFrom(fromAddress);
    }
    public void setTo(String email) throws MessagingException {
        messageHelper.setTo(email);
    }
    public void setSubject(String subject) throws MessagingException {
        messageHelper.setSubject(subject);
    }
    public void setText(String text, boolean useHtml) throws MessagingException {
        messageHelper.setText(text, useHtml);
    }

    // 메일 전송
    public void send() {
        try {
            sender.send(message);
        } catch (MailSendException e) { // 메일 전송 중 발생한 MailSendException 처리
            logger.error("메일 전송 실패: {}", e.getMessage());
            throw e;  // 컨트롤러로 예외 던짐
        } catch (Exception e) {         // 그 외 예외 처리
            logger.error("메일 전송 중 예외 발생: {}", e.getMessage());
            throw new MailSendException("메일 전송 중 예외 발생", e);  // 예외를 MailSendException으로 변환해서 던져줌
        }
    }
}