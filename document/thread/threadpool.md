### java异步线程如何获取结果
[参考文档](https://www.cnblogs.com/yougewe/p/11666284.html)

1.构造Future(RunnableFuture->FutureTask)对象
2.调用execute并将future对象传入
3.返回future对象
```
public <T> Future<T> submit(Callable<T> task) {
    if (task == null) throw new NullPointerException();
    RunnableFuture<T> ftask = newTaskFor(task);
    execute(ftask);
    return ftask;
}
```

Runnable转Callable
```java
/**
 * A callable that runs given task and returns given result
 */
static final class RunnableAdapter<T> implements Callable<T> {
    final Runnable task;
    final T result;
    RunnableAdapter(Runnable task, T result) {
        this.task = task;
        this.result = result;
    }
    public T call() {
        task.run();
        return result;
    }
}
```

future#get
```
public V get() throws InterruptedException, ExecutionException {
    int s = state;
    // 只要状态值小于 COMPLETING, 就说明任务还未完成, 去等待完成
    if (s <= COMPLETING)
        s = awaitDone(false, 0L);
    // 只要等待完成, 再去把结果取回即可
    return report(s);
}
```

**异步任务submit之后即可获取future对象**
异步任务submit之后即可获取到future对象，此时线程可能还未调度，或者已调度但是线程还未执行结束。
**调用futuretask中的run方法将结果赋值给outcome变量**
**通过futuretask的get方法获取结果**
如果futuretask执行结束，执行返回结果；
如果还未执行完，等待执行结束并返回执行状态；

调度时，调用futuretask#run方法

### java线程池实现原理
