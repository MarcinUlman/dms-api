package dev.ulman.dms.api;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    public MyErrorController() {
    }

    @GetMapping("/error")
    public String errorHandling(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null){
            Integer statusCode = Integer.valueOf(status.toString());
            System.out.println(statusCode);
            if (statusCode == HttpStatus.NOT_FOUND.value())
                return "error/error-404";
            else if (statusCode == HttpStatus.FORBIDDEN.value())
                return "error/error-403";
            else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value())
                return "error/error-500";
        }
        System.out.println(status);
        return "error/error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}