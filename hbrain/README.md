# Template Spring Boot

## JAVA项目开发流程

复制该项目做为JAVA项目模板。
 





```
$ cp template-spring-boot your-project-name
```

修改`pom.xml`，将第7行`artifactId`修改为对应的项目ID，将第8行`version`修改为对应的版本号，其他地方根据需要修改。

```
<groupId>com.haizhi</groupId>
<artifactId>{your-project-name}</artifactId>
<version>{your-project-version}</version>
<packaging>jar</packaging>
```

修改`Dockerfile`，更新对应的`jar`包名称。

```
FROM 10.10.150.149:5000/java:8-jre

ADD target/{your-project-name}-{your-project-version}.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
```

修改`.gitlab-ci.yml`，将第23行的镜像名修改为对应的项目名称。

```
- tag=$(date +"%Y%m%d-%H%M%S")
- image=10.10.150.149:5000/ruyi-ai/{your-project-name}:$tag
- docker login -u yy -p 000000 10.10.150.149:5000
- docker build -t $image .
- docker push $image
- echo "$image"
```

增加项目需要的`.java`文件以及单元测试，并提交代码。

```
git add src/main/java/com/haizhi/web/HelloController.java
git add src/test/java/com/haizhi/web/HelloControllerTests.java
git commit -m "commit comment"
git push origin master
```

提交`Merge Requests`

```
Gitlab -> Merge Requests -> New merge request
```

查看Pipeline 的执行结果，详细页面：http://gitlab.ruyi.ai/dev/template-spring-boot/pipelines

* 通过`maven-build`查看单元测试是否通过
* 通过`docker-build`查看`Docker`镜像的标签

```
# maven-build result

[INFO] Downloading: https://repo.maven.apache.org/maven2/asm/asm-analysis/3.2/asm-analysis-3.2.jar
[INFO] Downloaded: https://repo.maven.apache.org/maven2/org/sonatype/sisu/sisu-inject-plexus/1.4.2/sisu-inject-plexus-1.4.2.jar (197 KB at 7.4 KB/sec)
[INFO] Downloading: https://repo.maven.apache.org/maven2/asm/asm-util/3.2/asm-util-3.2.jar
[INFO] Downloaded: https://repo.maven.apache.org/maven2/asm/asm-analysis/3.2/asm-analysis-3.2.jar (18 KB at 0.7 KB/sec)
[INFO] Downloading: https://repo.maven.apache.org/maven2/com/google/guava/guava/18.0/guava-18.0.jar
[INFO] Downloaded: https://repo.maven.apache.org/maven2/commons-io/commons-io/1.3.2/commons-io-1.3.2.jar (86 KB at 3.1 KB/sec)
[INFO] Downloaded: https://repo.maven.apache.org/maven2/asm/asm-util/3.2/asm-util-3.2.jar (36 KB at 1.3 KB/sec)
[INFO] Downloaded: https://repo.maven.apache.org/maven2/org/jdom/jdom/1.1/jdom-1.1.jar (150 KB at 5.3 KB/sec)
[INFO] Downloaded: https://repo.maven.apache.org/maven2/org/sonatype/sisu/sisu-guice/2.1.7/sisu-guice-2.1.7-noaop.jar (461 KB at 12.8 KB/sec)
[INFO] Downloaded: https://repo.maven.apache.org/maven2/com/google/guava/guava/18.0/guava-18.0.jar (2204 KB at 29.0 KB/sec)
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 08:21 min
[INFO] Finished at: 2017-06-18T12:34:14+00:00
[INFO] Final Memory: 28M/158M
[INFO] ------------------------------------------------------------------------
Uploading artifacts...
target/*.jar: found 1 matching files               
Uploading artifacts to coordinator... ok            id=267 responseStatus=201 Created token=ZVH1Di85
Build succeeded



# docker-build result

$ docker push $image
The push refers to a repository [10.10.150.149:5000/ruyi-ai/template-spring-boot]
3db427a0bbf7: Preparing
73ad47d4bc12: Preparing
c22c27816361: Preparing
04dba64afa87: Preparing
500ca2ff7d52: Preparing
782d5215f910: Preparing
0eb22bfb707d: Preparing
a2ae92ffcd29: Preparing
782d5215f910: Waiting
0eb22bfb707d: Waiting
a2ae92ffcd29: Waiting
73ad47d4bc12: Mounted from java
04dba64afa87: Mounted from java
500ca2ff7d52: Mounted from java
c22c27816361: Mounted from java
782d5215f910: Mounted from java
0eb22bfb707d: Mounted from ruyi-ai/python
a2ae92ffcd29: Mounted from ruyi-ai/python
3db427a0bbf7: Pushed
20170618-125701: digest: sha256:eb1e936877e00328f72ddbb9f55906128e722cb30a24fb0f8af3f34857098272 size: 1999
$ echo "$image"
10.10.150.149:5000/ruyi-ai/template-spring-boot:20170618-125701
Build succeeded

# so the image tag of this build is `20170618-125701`

```

修改`template-spring-boot.yaml`

```

kind: Service
apiVersion: v1
metadata:
  name: {your-project-name}-service
spec:
  type: NodePort
  selector:
    app: {your-project-name}
  ports:
    - protocol: TCP
      port: 8080
      nodePort: {your-project-port}
---
apiVersion: apps/v1beta1
kind: Deployment
metadata:
  name: {your-project-name}-deployment
spec:
  replicas: 1
  template:
    metadata:
      labels:
        app: {your-project-name}
    spec:
      containers:
      - name: {your-project-name}-controller
        image: 10.10.150.149:5000/ruyi-ai/{your-project-name}:{image-tag}
        ports:
        - containerPort: 8080
      imagePullSecrets:
        - name: 10.10.150.149

```

部署服务

```
kubectl --kubeconfig {your_config_file}.conf -n dev apply -f template-spring-boot.yaml

```

## FAQ

#### 如何引用本地`maven`包

将本地`maven`包提交到代码库

```
git add lib/{local-package-name}-{local-package-version}.jar
```
在`pom.xml`中添加依赖

```
<dependency>
  <groupId>local.lib</groupId>
  <artifactId>{local-package-name}</artifactId>
  <version>{local-package-version}</version>
</dependency>

```

在单元测试过程中添加安装命令，修改`.gitlab-ci.yml`

```
maven-build:
  stage: test
  image: 10.10.150.149:5000/maven:3-jdk-8
  script:
    - mvn install:install-file -Dfile=lib/{local-package-name}-{local-package-version}.jar  -DgroupId=local.lib -DartifactId={local-package-name}  -Dversion={local-package-version} -Dpackaging=jar
    - mvn clean package -B
  artifacts:
    paths:
      - target/*.jar
```


## 引用

* [官方文档](http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/)
