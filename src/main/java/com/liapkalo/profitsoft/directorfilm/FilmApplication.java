package com.liapkalo.profitsoft.directorfilm;

import com.liapkalo.profitsoft.directorfilm.controller.ProcessJsonController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FilmApplication {

    public static void main(String[] args) {
        if (args.length != 2) {
            log.error("Parameters should contain TWO arguments!");
        } else ProcessJsonController.processJson(args[0], args[1]);
    }

}
