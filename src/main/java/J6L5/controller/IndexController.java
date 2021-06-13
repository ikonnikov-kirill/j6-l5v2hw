package J6L5.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class IndexController {
    @GetMapping("/")
    public String index() {
        log.info("Redirect to /products");
        return "redirect:/products";
    }
}
