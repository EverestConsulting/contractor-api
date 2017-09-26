package com.contractor.service.httpclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class HttpClientWorker {

    private static final Logger LOG = LoggerFactory.getLogger(HttpClient.class.getSimpleName());
    private static HttpClientWorker INSTANCE;
    private static ExecutorService worker;


    private HttpClientWorker() {
        worker = Executors.newCachedThreadPool();
    }

    public static void init() {
        if (null != INSTANCE) {
            LOG.warn(String.format("%s already instantiated", HttpClientWorker.class.getSimpleName()));
            return;
        }
        INSTANCE = new HttpClientWorker();
    }

    public static HttpClientWorker instance() {
        if (null == INSTANCE) {
            LOG.error(String.format("%s not properly instantiated", HttpClientWorker.class.getSimpleName()));
        }
        return INSTANCE;
    }

    public String execute(Callable<String> task) {
        String result = null;
        Future<String> future;
        try {
            future = worker.submit(task);
            if (future.isDone()) {
                result = future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            LOG.error("Error while executing task", e);
        }

        return result;
    }

}
