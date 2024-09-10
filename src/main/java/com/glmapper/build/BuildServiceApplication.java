package com.glmapper.build;

import com.marcnuri.helm.Helm;
import com.marcnuri.helm.PackageCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@SpringBootApplication
public class BuildServiceApplication {

    @Autowired
    private ResourceLoader resourceLoader;

    public static void main(String[] args) {
        SpringApplication.run(BuildServiceApplication.class, args);
    }

    @PostConstruct
    public void init() throws Exception {
        Path path = Paths.get("https://file-hf.atomecho.cn/d/zhangzheng%E6%96%B0%E5%BB%BA%E6%96%87%E4%BB%B6%E5%A4%B9/%E4%B8%8D%E4%B9%B1%E7%A0%81.txt");
        BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));
        String s = reader.readLine();
        System.out.println(s);
    }



}
