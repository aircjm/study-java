package com.aircjm.study.cloud.web.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // 启用 Mockito JUnit 5 扩展
public class UserServiceTest {

    @Mock // 创建 UserRepository 的 mock 对象
    private UserRepository userRepository;

    @InjectMocks // 将 userRepository mock 对象注入到 userService 中
    private UserService userService;

    @Test
    void getUserById_existingUser_returnsUser() {
        // 1. 设置 mock 对象的行为 (when...thenReturn)
        String userId = "123";
        User mockUser = new User(userId, "Alice");
        when(userRepository.findById(userId)).thenReturn(mockUser);

        // 2. 调用被测试的方法
        User result = userService.getUserById(userId);

        // 3. 验证结果
        assertEquals("Alice", result.getName());

        // 4. 可选地，验证 mock 对象的方法是否被调用 (verify)
        verify(userRepository, times(1)).findById(userId);
        verifyNoMoreInteractions(userRepository); // 确保没有其他交互
    }

    @Test
    void getUserById_nonExistingUser_returnsNull() {
        // 设置 mock 对象的行为：当查找不存在的用户 ID 时，返回 null
        String nonExistingUserId = "456";
        when(userRepository.findById(nonExistingUserId)).thenReturn(null);

        // 调用被测试的方法
        User result = userService.getUserById(nonExistingUserId);

        // 验证结果
        assertNull(result);

        // 验证 userRepository.findById 是否被调用
        verify(userRepository, times(1)).findById(nonExistingUserId);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void createUser_validUser_callsSaveMethod() {
        // 1. 设置测试数据
        String userId = "789";
        String userName = "Bob";

        // 2. 调用被测试的方法
        userService.createUser(userId, userName);

        // 3. 验证 userRepository 的 save 方法是否被调用，并使用了正确的参数
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();
        assertEquals(userId, capturedUser.getId());
        assertEquals(userName, capturedUser.getName());

        verifyNoMoreInteractions(userRepository);
    }
}