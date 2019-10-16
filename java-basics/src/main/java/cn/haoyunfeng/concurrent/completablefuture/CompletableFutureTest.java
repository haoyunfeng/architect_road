package cn.haoyunfeng.architect.java.concurrent.completablefuture;

import java.util.Date;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @Description:
 * @author: haoyunfeng2
 * @Date: 2019/10/9 4:12 下午
 */
public class CompletableFutureTest {

    /**
     * 没有回调，get阻塞式等待
     * @throws ExecutionException
     * @throws InterruptedException
     */
    static void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                System.out.println("I'll run in a separate thread than the main thread.");
            }
        });
        // Block and wait for the future to complete
        future.get();
    }

    static void runAsyncWithLambda() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("I'll run in a separate thread than the main thread.");
        });
        future.get();
    }

    static void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync( () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of the asynchronous computation";
        });
        String result = future.get();
        System.out.println(result);
    }

    static void supplyAsyncWithExecutor() throws ExecutionException, InterruptedException {
        Executor executor = Executors.newFixedThreadPool(10);
        CompletableFuture<String> future = CompletableFuture.supplyAsync( () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of the asynchronous computation with executor";
        });
        String result = future.get();
        System.out.println(result);
    }

    static void thenApply() throws ExecutionException, InterruptedException {
        // Create a CompletableFuture
        CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getId()+ " :start whatsYourNameFuture" +new Date());
                TimeUnit.SECONDS.sleep(10);
                System.out.println(Thread.currentThread().getId()+ " :end whatsYourNameFuture"+ new Date());
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        });

        CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name ->{
            try {
                System.out.println(Thread.currentThread().getId()+ " :start greetingFuture"+ new Date());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getId()+ " :end greetingFuture"+ new Date());
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "hello" +name;
        });
//        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getId()+" :start test " + new Date());
        System.out.println(Thread.currentThread().getId()+ greetingFuture.get()+ new Date());
        System.out.println(Thread.currentThread().getId()+" :main thread " + new Date());
    }

    static void thenAcceptTest(){
        CompletableFuture.supplyAsync(() ->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return ProductService.getProductDetail("10");
        }).thenAccept(product -> {
            System.out.println("Got product detail from remote service " + product.getProductName());
        });
    }

    static void thenRunTest(){
        CompletableFuture.supplyAsync(()->{
           return  ProductService.getProductDetail("10");
        }).thenRun(()->{
            System.out.println("Got product detail from remote service ");
        });
    }

    /**
     * thenApplyAsync 有Async后缀的是异步执行，不阻塞主进程
     */
    static void thenApplyAsyncTest() throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+Thread.currentThread().getId() + "supplyAsync"+new Date());
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "supplyAsync";
        }).thenApplyAsync(result->{
            System.out.println(Thread.currentThread().getName()+Thread.currentThread().getId() +result+ ".thenApplyAsync.start"+new Date());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+Thread.currentThread().getId() +result+ ".thenApplyAsync.end"+new Date());
            return result + ".thenApplyAsync";
        });
        System.out.println(Thread.currentThread().getName()+Thread.currentThread().getId()+ "start execture"+new Date());
        System.out.println(Thread.currentThread().getName()+Thread.currentThread().getId()+ future.join()+ new Date());
//        System.out.println(Thread.currentThread().getName()+Thread.currentThread().getId()+ future.get()+ new Date());
        System.out.println(Thread.currentThread().getName()+Thread.currentThread().getId()+" :main thread " + new Date());
//        System.out.println(future.join());
    }

    /**
     * thenApply thenCompose 都是串行操作 ，两个future是阻塞式的
     * @throws ExecutionException
     * @throws InterruptedException
     */
    static void thenComposeTest() throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture.supplyAsync(()-> "Hello ")
                .thenApply(s->s+ "World")
                .thenApply(String::toLowerCase);
        System.out.println(future.get());

        future = CompletableFuture.supplyAsync(()->"10").thenApply(Integer::parseInt).thenApply(i->i*10.0);
        System.out.println(future.get());

        future = CompletableFuture.supplyAsync(()->"Hello")
                .thenCompose(s-> CompletableFuture.supplyAsync(()->s+"World"));
        System.out.println(future.get());

        future = CompletableFuture.supplyAsync(()-> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"."+Thread.currentThread().getId()+":step1"+new Date());
            return "100";
        }).thenCompose(s -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"."+Thread.currentThread().getId()+":step2"+new Date());
            return CompletableFuture.supplyAsync(()->s + "100");
        }).thenCompose(s-> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"."+Thread.currentThread().getId()+":step3"+new Date());
            return CompletableFuture.supplyAsync(()->Double.parseDouble(s));
        });
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"."+Thread.currentThread().getId()+":main"+new Date());
        System.out.println(future.get());
    }

    /**
     * 连接的future是并行的
     * @throws ExecutionException
     * @throws InterruptedException
     */
    static void thenCombineTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"."+Thread.currentThread().getId()+":futre1"+new Date());
            return "100";
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"."+Thread.currentThread().getId()+":futre2"+new Date());
            return 100;
        });
        CompletableFuture<Double> future = future1.thenCombine(future2,(s,i)->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"."+Thread.currentThread().getId()+":futremain"+new Date());
            return Double.parseDouble(s+i);
        });
        System.out.println(Thread.currentThread().getName()+"."+Thread.currentThread().getId()+":main Thread"+ new Date());
        System.out.println(Thread.currentThread().getName()+"."+Thread.currentThread().getId()+":"+future.get() +new Date());
    }

    /**
     * 和thenCombine基本类似，只是返回的completableFuture是void的
     * @throws ExecutionException
     * @throws InterruptedException
     */
    static void thenAcceptBothTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"."+Thread.currentThread().getId()+":futre1"+new Date());
            return "100";
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"."+Thread.currentThread().getId()+":futre2"+new Date());
            return 100;
        });

        CompletableFuture<Void> future = future1.thenAcceptBoth(future2,(s,i)->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Double.parseDouble(s+i));
            System.out.println(Thread.currentThread().getName()+"."+Thread.currentThread().getId()+":futremain"+new Date());
        });

        future.get();
    }

    public static void main(String []args) throws ExecutionException, InterruptedException {
//        runAsync();
//        runAsyncWithLambda();
//        supplyAsync();
//        supplyAsyncWithExecutor();
        thenApply();
//        thenRunTest();
//        thenAcceptTest();
//        thenApplyAsyncTest();
//        thenComposeTest();
//        thenCombineTest();
    }
}
