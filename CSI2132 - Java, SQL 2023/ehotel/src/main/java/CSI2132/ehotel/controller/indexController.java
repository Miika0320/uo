package CSI2132.ehotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class indexController {


    @GetMapping("login.html")
    public String login() {
      return "login"; // this returns the template name to be rendered from resources/templates. You don't need to provide the extension.
    }

    @GetMapping("custSignUp.html")
    public String signup() {
      return "custSignUp"; // this returns the template name to be rendered from resources/templates. You don't need to provide the extension.
    }
  
}
