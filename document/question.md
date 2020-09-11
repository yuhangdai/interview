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
