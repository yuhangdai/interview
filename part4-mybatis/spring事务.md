### spring事务使用

### spring事务原理

### spring事务失效原因
- 方法非public
- 类内部调用调用类内部@Transactional标注的方法(其它类调用m2方法会导致m1事务失效)   
[解决办法](https://blog.csdn.net/xwq911/article/details/51444755)
    
    Class A {
        @Transactional 
        public void m1(){
            ...
        }
        
        public void m2(){
            m1();
        }    
    }

- 事务方法内部捕捉了异常