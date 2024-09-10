package com.glmapper.build.kubernates;

import com.marcnuri.helm.Helm;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1NamespaceList;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.credentials.AccessTokenAuthentication;

import java.io.IOException;
import java.util.List;

/**
 * @Classname K8sApiClient
 * @Description K8sApiClient
 * @Date 2024/9/10 10:02
 * @Created by glmapper
 */
public class K8sApiClient {
    public static void main(String[] args) throws IOException, ApiException {
        String call = Helm.registry().login().insecureSkipTlsVerify().withHost("172.87.7.246:28080").call();
        System.out.println(call);
        ApiClient client = new ClientBuilder().setBasePath("https://172.87.7.246:6443")
                .setVerifyingSsl(false)
                .setAuthentication(new AccessTokenAuthentication("eyJhbGciOiJSUzI1NiIsImtpZCI6ImVwbHhJX1B2NW1wR2ZmdFk1WXNTcERVT3FJNGtVeG5HLTZnbElVT0lYRlkifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlLXN5c3RlbSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJhZG1pbi10b2tlbi13bmt3NiIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50Lm5hbWUiOiJhZG1pbiIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6IjIzZDNlNWI1LTU2NDgtNGUyYi1iY2YxLWZhYzU0NDhjODIxMSIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDprdWJlLXN5c3RlbTphZG1pbiJ9.sHA0U-m6m6awE6zHh4mMH-6wU9ebGMYlsAvr8ZFT5GWRpGp1qZ8Bsiu31FJqjSILVBlOvVlz0BjDtQfe4PGdg8Ly4upILDofvTZMViUzx5UImMQwKr4ywLYBIVmHtChleb91MM1D-arxIxtZxpyL7oar7g83VXwcTg80JFGLv5HzyZcRQ-ZomVT_suFvp4BFvudjHOM6eWYgiSsKYGg5Basa1OHSerhwT3ngI7zEJrmqvl9d8SdymimdNbjwMP_YAvAp-hNcxk1KSUXJi2tp6our6_526xYmu2zx2OT-YRyl1HIIjoKk9UNjBFQHfRo588mKWwWDHKtQA2NKkcLWbQ"))
                .build();
        Configuration.setDefaultApiClient(client);

        CoreV1Api coreV1Api = new CoreV1Api();
        V1NamespaceList v1PodList = coreV1Api.listNamespace( null, null, null, null, null, null, null, null, null, null);
        List<V1Namespace> items = v1PodList.getItems();
        // 使用Gson将集合对象序列化成JSON，在日志中打印出来
        for (V1Namespace item : items) {
            System.out.println(item.getMetadata().getName());
        }

        // 获取指定命名空间下的所有 Pod
        V1PodList podList = coreV1Api.listNamespacedPod("hhm", null, null, null, null, null, null, null, null, null, null);

        // 遍历并输出 Pod 名称
        podList.getItems().forEach(pod -> {
            System.out.println("Pod Name: " + pod.getMetadata().getName());
        });
    }
}
