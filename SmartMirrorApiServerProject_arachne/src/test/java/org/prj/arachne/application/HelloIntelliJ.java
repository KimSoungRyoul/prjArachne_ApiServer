package org.prj.arachne.application;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HelloIntelliJ {

    public static void main(String[] args) {




        Resource resource = new ClassPathResource("/templates/simpleform.html");




        try {

            byte[] encoded = Files.readAllBytes(Paths.get(resource.getURI()));
            String str= new String(encoded, "UTF-8");



            System.out.println(str);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
