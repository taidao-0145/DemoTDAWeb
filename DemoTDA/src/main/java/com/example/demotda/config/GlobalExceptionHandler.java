package com.example.demotda.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

//    @ExceptionHandler(Throwable.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ModelAndView exception(final Throwable throwable, final Model model) {
//        logger.error("Exception during execution of SpringSecurity application", throwable);
//        ModelAndView modelAndView = new ModelAndView("user/404");
//        String errorMessage = (throwable != null ? throwable.toString() : "Unknown error");
//        modelAndView.addObject("errorMessage", errorMessage);
//        return modelAndView;
//    }


    @ExceptionHandler(value = { ConfigDataResourceNotFoundException.class })
    public ModelAndView handleResourceNotFoundException(ConfigDataResourceNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/404"); // Chuyển hướng đến trang lỗi 404
        modelAndView.addObject("message", ex.getMessage()); // Truyền thông tin lỗi cho trang lỗi
        return modelAndView;
    }


//    @ExceptionHandler(Exception.class)
//    public String exception(Exception exception){
//        logger.error(String.valueOf(exception));
//        exception.printStackTrace();
//        return "user/404";
//    }
}
