/**
 * Clasa pentru ErrorControllerImpl
 * @author Zarnescu Dragos
 * @version 11 Ianuarie 2025
 */

package com.dragos.gestiune_informatii.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorControllerImpl implements ErrorController {

    // Handle page not found (404) and other errors
//    @RequestMapping("/error")
//    public String handleError() {
//        // You can return a custom error page (like 404.html)
//        return "error/404";  // This will map to src/main/resources/templates/error/404.html
//    }

}
