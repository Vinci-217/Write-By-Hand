# 面试手写代码

## TODO

- [ ] 手写单例模式
- [ ] 手写HashMap
- [ ] 手写死锁
- [ ] 手撕快排

## 笔面试题记录

- 实现一个类 支持100个线程同时向个银行账户中存入一元钱

```
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ShowMeBug {
    private double balance; // 账户余额
    private final ReentrantLock lock = new ReentrantLock(); // 定义锁

    /**
     * 存款
     *
     * @param money 存入金额
     */
    public void deposit(double money) {
        lock.lock(); // 加锁
        try {
            balance += money;
        } finally {
            lock.unlock(); // 解锁
        }
    }

    /**
     * 获得账户余额
     */
    public double getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        ShowMeBug account = new ShowMeBug();

        // 创建线程池并发执行100个存款操作
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        double money = 1;
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> account.deposit(money));
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {}

        System.out.println("账户余额: " + account.getBalance());
    }
}
```

