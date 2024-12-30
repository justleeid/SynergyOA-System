package com.fno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动程序
 * 
 * @author ry
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
        org.flowable.spring.boot.FlowableSecurityAutoConfiguration.class})
@ComponentScan(value={"com.fno.*"})
public class Application
{
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(Application.class, args);
        long endTime = System.currentTimeMillis();
        System.out.println("项目启动花费时间：" + (endTime - startTime)/1000 + "秒");
        System.out.println("             ___                            ___                                                                                    \n" +
                "            (   )                          (   )                                                                                   \n" +
                "    .--.     | |_       .---.   ___ .-.     | |_           .--.     ___  ___    .--.      .--.      .--.       .--.        .--.    \n" +
                "  /  _  \\   (   __)    / .-, \\ (   )   \\   (   __)       /  _  \\   (   )(   )  /    \\    /    \\    /    \\    /  _  \\     /  _  \\   \n" +
                " . .' `. ;   | |      (__) ; |  | ' .-. ;   | |         . .' `. ;   | |  | |  |  .-. ;  |  .-. ;  |  .-. ;  . .' `. ;   . .' `. ;  \n" +
                " | '   | |   | | ___    .'`  |  |  / (___)  | | ___     | '   | |   | |  | |  |  |(___) |  |(___) |  | | |  | '   | |   | '   | |  \n" +
                " _\\_`.(___)  | |(   )  / .'| |  | |         | |(   )    _\\_`.(___)  | |  | |  |  |      |  |      |  |/  |  _\\_`.(___)  _\\_`.(___) \n" +
                "(   ). '.    | | | |  | /  | |  | |         | | | |    (   ). '.    | |  | |  |  | ___  |  | ___  |  ' _.' (   ). '.   (   ). '.   \n" +
                " | |  `\\ |   | ' | |  ; |  ; |  | |         | ' | |     | |  `\\ |   | |  ; '  |  '(   ) |  '(   ) |  .'.-.  | |  `\\ |   | |  `\\ |  \n" +
                " ; '._,' '   ' `-' ;  ' `-'  |  | |         ' `-' ;     ; '._,' '   ' `-'  /  '  `-' |  '  `-' |  '  `-' /  ; '._,' '   ; '._,' '  \n" +
                "  '.___.'     `.__.   `.__.'_. (___)         `.__.       '.___.'     '.__.'    `.__,'    `.__,'    `.__.'    '.___.'     '.___.'   \n");

        System.out.println("启动成功，欢迎使用");
    }
}
