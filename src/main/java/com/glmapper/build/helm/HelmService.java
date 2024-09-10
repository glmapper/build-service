package com.glmapper.build.helm;

import com.marcnuri.helm.Helm;
import com.marcnuri.helm.RepoCommand;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;

/**
 * @Classname HelmService
 * @Description HelmService
 * @Date 2024/8/27 18:00
 * @Created by glmapper
 */
@Service
public class HelmService {

    /**
     * Create a Helm chart
     *
     * @param chartDir    The directory of the Helm chart
     * @param serviceName The name of the service
     */
    public void createHelm(String chartDir, String serviceName) {
        Helm.create()
                // Name of the chart to create
                .withName(serviceName)
                // Path to the directory where the new chart directory will be created
                .withDir(Paths.get(chartDir)).call();
    }


    /**
     * Package a Helm chart
     *
     * @param chartDir The directory of the Helm chart
     * @param tgzDir   The directory of the tgz file
     */
    public void packageHelm(String chartDir, String tgzDir) {
        Helm result = new Helm(Paths.get(chartDir));
        result.packageIt().withDestination(Paths.get(tgzDir)).call();
    }


    /**
     * Package a Helm chart
     *
     * @param chartDir The directory of the Helm chart
     * @param tgzDir   The directory of the tgz file
     */
    public void packageListHelm(String chartDir, String tgzDir) {
        Helm.registry().login().withHost("http://172.87.7.246:28080").call();
    }
}
