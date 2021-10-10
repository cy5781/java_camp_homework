package org.ming.nio.backend.server;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {
    @GetMapping("/api/hello")
    public String sayHello(HttpServletRequest request){
    // String code = request.getHeader("exchange-cloud");
        return "get hello nio";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String serverService(){
        return "hello nio from post";
    }
}
