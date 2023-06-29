package topen.khjs.howtoearth;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/")
    public String redirectToIndex(){
        return "redirect:/index.html";
    }
}

// 유후 서버가 열린

