//package spring.security.jwtrefreshtoken.controller;
//
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import spring.security.jwtrefreshtoken.config.service.UserDetailsImpl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//public class TestController {
//
//    @GetMapping("/accessTest")
//    public ResponseEntity<?> access(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return ResponseEntity.ok(userDetails);
//    }
//
//    @GetMapping("/getIp")
//    public String findIp(HttpServletRequest request) {
//        String ip = null;
//        boolean isIpInHeader = false;
//
//        List<String> headerList = new ArrayList<>();
//        headerList.add("X-Forwarded-For");
//        headerList.add("HTTP_CLIENT_IP");
//        headerList.add("HTTP_X_FORWARDED_FOR");
//        headerList.add("HTTP_X_FORWARDED");
//        headerList.add("HTTP_FORWARDED_FOR");
//        headerList.add("HTTP_FORWARDED");
//        headerList.add("Proxy-Client-IP");
//        headerList.add("WL-Proxy-Client-IP");
//        headerList.add("HTTP_VIA");
//        headerList.add("IPV6_ADR");
//
//        for (String header : headerList) {
//            ip = request.getHeader(header);
//            if (StringUtils.hasText(ip) && !ip.equals("unknown")) {
//                isIpInHeader = true;
//                break;
//            }
//        }
//
//        if (!isIpInHeader) {
//            ip = request.getRemoteAddr();
//        }
//
//        return ip;
//    }
//}
