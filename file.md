### Java读取Jar包里的文件

#### 读取jar包内文件
  项目没有打成jar包使用
  
    // 使用resource可以读取URI形式的文件
    // URI是这种形式地：协议名称://域名.根域名/目录/文件名.后缀
    // 如file://d:xx/xx/aa/bb.txt
    Resource resource = this.getClass().getResource("xx");
    // 但是当文件打包到jar文件中文件路径变成
    // file://d:xx/xx/aa/bb.txt!jar:xxx
    // 此时我们通过resource方式无法正确获取文件信息
    
    // 但是我们可以通过流方式获取
    InputStream inputStream = this.getClass()getResourceAsStream("Book1.xls"));
    
  **jar包里的文件，我们是没法以file形式读取的，而只能以InputStram的形式读取**
  
#### 读取jar包外文件
    String filePath = System.getProperty("user.dir") + "/resources/xx.properties";     
    InputStream in = new BufferedInputStream(new FileInputStream(filePath));
    
   
#### 参考文档
   (Java读取jar包外文件) [https://blog.csdn.net/daoshud1/article/details/77646074]
   (Java 如何获取 jar 包外的资源) [https://blog.csdn.net/weixin_36242811/article/details/89216147]