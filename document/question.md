### IDEA
1.IDEA debug启动慢 失败（有断点打在方法上，去掉此类型断点就行）
### JS
[js测试](https://www.w3school.com.cn/tiy/t.asp?f=html_a_target_framename)
1. 超链接target为_blank，导致href中方法无法执行 [解决办法](https://www.iteye.com/blog/czj4451-1989918)
### JAVA


### ftp
then you will need to use mdelete to delete multiple files, as delete will only delete a single file
ftp操作多个文件 使用prompt关闭交互模式
ftp delete命令不能删除文件夹

### soap ui
1.soap ui进行rest api调用时，传递中文参数报400错误。后台JSON解析异常   
[解决办法](https://blog.csdn.net/gongjin28_csdn/article/details/90750283)  
找到soap ui安装目录（可执行文件），在此目录下找到SoapUI-xxx.vmoptions文件，
在文件结尾添加如下两行代码即可。

    -Dsun.jnu.encoding=UTF-8
    -Dfile.encoding=UTF-8

### Mybatis
mybatis 分页参数混乱问题
[解决办法](https://blog.csdn.net/w8y56f/article/details/100710380)

oracle数据库连接池报错
[No more data to read from socket error](https://stackoverflow.com/questions/7839907/no-more-data-to-read-from-socket-error)
```
maxActive="100"
minIdle="10"
maxWait="10000"
initialSize="10"
```
      
      
### spring mvc 返回 return string和return modelandview区别

### div实现y轴滚动条
[DIV实现纵向滚动条overflow-y](cnblogs.com/wellsoho/p/5102014.html)