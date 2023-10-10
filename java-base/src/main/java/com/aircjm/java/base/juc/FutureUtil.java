package com.aircjm.java.base.juc;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public class FutureUtil {
    /**
     * 设置CF状态为失败
     */
    public static <T> CompletableFuture<T> failed(Throwable ex) {
        CompletableFuture<T> completableFuture = new CompletableFuture<>();
        completableFuture.completeExceptionally(ex);
        return completableFuture;
    }

    /**
     * 设置CF状态为成功
     */
    public static <T> CompletableFuture<T> success(T result) {
        CompletableFuture<T> completableFuture = new CompletableFuture<>();
        completableFuture.complete(result);
        return completableFuture;
    }

    /**
     * 将List<CompletableFuture<T>> 转为 CompletableFuture<List<T>>
     */
    public static <T> CompletableFuture<List<T>> sequence(Collection<CompletableFuture<T>> completableFutures) {
        return CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture<?>[0]))
                .thenApply(v -> completableFutures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList())
                );
    }

    /**
     * 将List<CompletableFuture<List<T>>> 转为 CompletableFuture<List<T>>
     * 多用于分页查询的场景
     */
    public static <T> CompletableFuture<List<T>> sequenceList(Collection<CompletableFuture<List<T>>> completableFutures) {
        return CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture<?>[0]))
                .thenApply(v -> completableFutures.stream()
                        .flatMap(listFuture -> listFuture.join().stream())
                        .collect(Collectors.toList())
                );
    }

    /**
     * 将List<CompletableFuture<T>> 转为 CompletableFuture<List<T>>，并过滤调null值
     */
    public static <T> CompletableFuture<List<T>> sequenceNonNull(Collection<CompletableFuture<T>> completableFutures) {
        return CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture<?>[0]))
                .thenApply(v -> completableFutures.stream()
                        .map(CompletableFuture::join)
                        .filter(e -> e != null)
                        .collect(Collectors.toList())
                );
    }

    /**
     * 将List<CompletableFuture<List<T>>> 转为 CompletableFuture<List<T>>，并过滤调null值
     * 多用于分页查询的场景
     */
    public static <T> CompletableFuture<List<T>> sequenceListNonNull(Collection<CompletableFuture<List<T>>> completableFutures) {
        return CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture<?>[0]))
                .thenApply(v -> completableFutures.stream()
                        .flatMap(listFuture -> listFuture.join().stream().filter(e -> e != null))
                        .collect(Collectors.toList())
                );
    }

    /**
     * 将List<CompletableFuture<Map<K, V>>> 转为 CompletableFuture<Map<K, V>>
     *
     * @Param filterFunction 自定义过滤策略
     */
    public static <T> CompletableFuture<List<T>> sequence(Collection<CompletableFuture<T>> completableFutures,
                                                          Predicate<? super T> filterFunction) {
        return CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture<?>[0]))
                .thenApply(v -> completableFutures.stream()
                        .map(CompletableFuture::join)
                        .filter(filterFunction)
                        .collect(Collectors.toList())
                );
    }

    /**
     * 将List<CompletableFuture<List<T>>> 转为 CompletableFuture<List<T>>
     *
     * @Param filterFunction 自定义过滤策略
     */
    public static <T> CompletableFuture<List<T>> sequenceList(Collection<CompletableFuture<List<T>>> completableFutures,
                                                              Predicate<? super T> filterFunction) {
        return CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture<?>[0]))
                .thenApply(v -> completableFutures.stream()
                        .flatMap(listFuture -> listFuture.join().stream().filter(filterFunction))
                        .collect(Collectors.toList())
                );
    }

}

