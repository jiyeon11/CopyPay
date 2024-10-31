package com.copypay.controller;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OTPTokenController {

    @GetMapping("/otp-qr")  //otp QR 코드가 나옴
    public String otpQR(Model model) throws Exception {
        GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();
        GoogleAuthenticatorKey googleAuthenticatorKey = googleAuthenticator.createCredentials();
        String key = googleAuthenticatorKey.getKey();  //생성한 키를 DB에 저장해놔야 함
        log.info("googleAuthenticatorKey : " + key);
        String QRUrl = GoogleAuthenticatorQRGenerator.getOtpAuthURL("CopyPay", "user1", googleAuthenticatorKey);
        model.addAttribute("qr", QRUrl);

        return "otp-qr";
    }
}
