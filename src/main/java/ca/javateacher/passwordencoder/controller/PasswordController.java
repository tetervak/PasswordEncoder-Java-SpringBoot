package ca.javateacher.passwordencoder.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PasswordController {

    private final PasswordEncoder passwordEncoder;


    public PasswordController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value={"/","/input"})
    public String input(){
        return "Input";
    }

    @GetMapping("/encode")
    public ModelAndView encode(@RequestParam String password){
        if(password == null || password.isEmpty()){
            return new ModelAndView("Input",
                    "message","The password is left empty");
        }else{
            String[] encoded = new String[10];
            for(int ind = 0; ind < encoded.length; ind++){
                encoded[ind] = passwordEncoder.encode(password);
            }
            ModelAndView mv = new ModelAndView("Output");
            mv.addObject("password", password);
            mv.addObject("encoded", encoded);
            return mv;
        }
    }
}
