package com.aircjm.java.base.junit;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


class ProductStreamOperationTest {

    // 测试实体类
    static class ProductInfoConfigDetailResponse {
        private String product;
        private int version;

        public ProductInfoConfigDetailResponse(String product, int version) {
            this.product = product;
            this.version = version;
        }

        public String getProduct() {
            return product;
        }

        public int getVersion() {
            return version;
        }
    }

    @Test
    void testCollectingAndThenWithEmptyList() {
        // 准备空列表
        List<ProductInfoConfigDetailResponse> emptyList = new ArrayList<>();

        // 执行操作
        List<ProductInfoConfigDetailResponse> result = emptyList.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(
                                Comparator.comparing(ProductInfoConfigDetailResponse::getProduct))),
                        ArrayList::new));

        // 验证结果
        assertTrue(result.isEmpty(), "空列表处理后应该仍然是空的");
    }

    @Test
    void testCollectingAndThenWithUniqueProducts() {
        // 准备测试数据（所有产品名称唯一）
        List<ProductInfoConfigDetailResponse> list = Lists.newArrayList(
                new ProductInfoConfigDetailResponse("ProductA", 1),
                new ProductInfoConfigDetailResponse("ProductB", 2),
                new ProductInfoConfigDetailResponse("ProductC", 3)
        );

        // 执行操作
        List<ProductInfoConfigDetailResponse> result = list.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(
                                Comparator.comparing(ProductInfoConfigDetailResponse::getProduct))),
                        ArrayList::new));

        // 验证结果
        assertEquals(3, result.size(), "所有产品名称唯一，结果大小应与输入相同");
        assertTrue(result.stream().map(ProductInfoConfigDetailResponse::getProduct)
                        .collect(Collectors.toSet()).size() == 3,
                "结果中应该没有重复的产品名称");
    }

    @Test
    void testCollectingAndThenWithDuplicateProducts() {
        // 准备测试数据（有重复产品名称）
        List<ProductInfoConfigDetailResponse> list = Lists.newArrayList(
                new ProductInfoConfigDetailResponse("ProductA", 1),
                new ProductInfoConfigDetailResponse("ProductB", 2),
                new ProductInfoConfigDetailResponse("ProductA", 3), // 重复
                new ProductInfoConfigDetailResponse("ProductC", 4),
                new ProductInfoConfigDetailResponse("ProductB", 5)  // 重复
        );

        // 执行操作
        List<ProductInfoConfigDetailResponse> result = list.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(
                                Comparator.comparing(ProductInfoConfigDetailResponse::getProduct))),
                        ArrayList::new));

        // 验证结果
        assertEquals(3, result.size(), "有重复产品名称，结果应该去重");
        assertTrue(result.stream().map(ProductInfoConfigDetailResponse::getProduct)
                        .collect(Collectors.toSet()).size() == 3,
                "结果中应该没有重复的产品名称");

        // 验证保留的是第一个出现的元素（TreeSet会保留排序后的第一个）
        // 注意：TreeSet的保留行为取决于Comparator，这里只保留一个，不保证是原始顺序中的第一个
        assertTrue(result.stream().anyMatch(p -> p.getProduct().equals("ProductA")));
        assertTrue(result.stream().anyMatch(p -> p.getProduct().equals("ProductB")));
        assertTrue(result.stream().anyMatch(p -> p.getProduct().equals("ProductC")));
    }

    @Test
    void testCollectingAndThenWithNullProducts() {
        // 准备包含null的测试数据
        List<ProductInfoConfigDetailResponse> list = new ArrayList<>();
        list.add(new ProductInfoConfigDetailResponse("ProductA", 1));
        list.add(null); // null元素
        list.add(new ProductInfoConfigDetailResponse(null, 2)); // product为null
        list.add(new ProductInfoConfigDetailResponse("ProductB", 3));

        // 执行操作并验证会抛出NullPointerException
        assertThrows(NullPointerException.class, () -> {
            list.stream().collect(
                    Collectors.collectingAndThen(
                            Collectors.toCollection(() -> new TreeSet<>(
                                    Comparator.comparing(ProductInfoConfigDetailResponse::getProduct))),
                            ArrayList::new));
        }, "当存在null元素或product字段为null时应该抛出NullPointerException");
    }

    @Test
    void testCollectingAndThenWithCustomComparator() {
        // 准备测试数据
        List<ProductInfoConfigDetailResponse> list = Lists.newArrayList(
                new ProductInfoConfigDetailResponse("ProductA", 1),
                new ProductInfoConfigDetailResponse("productA", 2), // 大小写不同
                new ProductInfoConfigDetailResponse("ProductB", 3)
        );

        // 使用不区分大小写的比较器
        List<ProductInfoConfigDetailResponse> result = list.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(
                                Comparator.comparing(
                                        ProductInfoConfigDetailResponse::getProduct,
                                        String.CASE_INSENSITIVE_ORDER))),
                        ArrayList::new));

        // 验证结果
        assertEquals(2, result.size(), "不区分大小写比较，'ProductA'和'productA'应该视为相同");
        assertTrue(result.stream().anyMatch(p -> p.getProduct().equalsIgnoreCase("productA")));
        assertTrue(result.stream().anyMatch(p -> p.getProduct().equals("ProductB")));
    }

    @Test
    void testCollectingAndThenPreservesOneElementPerProduct() {
        // 准备测试数据 - 相同产品不同版本
        List<ProductInfoConfigDetailResponse> list = Lists.newArrayList(
                new ProductInfoConfigDetailResponse("ProductA", 1), // 版本1
                new ProductInfoConfigDetailResponse("ProductA", 2), // 版本2
                new ProductInfoConfigDetailResponse("ProductB", 1)
        );

        // 执行操作
        List<ProductInfoConfigDetailResponse> result = list.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(
                                Comparator.comparing(ProductInfoConfigDetailResponse::getProduct))),
                        ArrayList::new));

        // 验证结果
        assertEquals(2, result.size(), "应该只保留每个产品的一个元素");

        // 验证TreeSet保留了哪个版本（TreeSet会保留第一个添加的元素或根据Comparator决定的元素）
        // 这里不能确定保留的是版本1还是版本2，只能确认保留了其中一个
        long productACount = result.stream()
                .filter(p -> "ProductA".equals(p.getProduct()))
                .count();
        assertEquals(1, productACount, "ProductA应该只出现一次");
    }
}
