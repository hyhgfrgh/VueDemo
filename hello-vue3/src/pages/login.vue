<template>
  <div class="login-container">
    <h2>用户登录</h2>
    
    <form @submit.prevent="handleLogin" class="login-form">
      <div class="form-group">
        <label for="username">用户名/邮箱:</label>
        <input 
          id="username" 
          type="text" 
          v-model="credentials.username" 
          required
        >
      </div>
     
      <div class="form-group">
        <label for="password">密码:</label>
        <input 
          id="password" 
          type="password" 
          v-model="credentials.password" 
          required
        >
      </div>

      <p v-if="error" class="error-message">{{ error }}</p>

      <button type="submit" :disabled="isLoading">
        {{ isLoading ? '登录中...' : '登录' }}
      </button>
    </form>
    
    <p class="register-link">
      还没有账号？<router-link to="/register">立即注册</router-link>
    </p>
  </div>
</template>

<script setup>
    // 导入 Vue 核心功能
    import { ref, reactive } from 'vue';
    // 导入路由实例，用于导航
    import { useRouter } from 'vue-router';
    // 导入之前创建的认证服务
    import { login } from '../auth'; 

    // 获取路由实例
    const router = useRouter();

    // 定义响应式数据
    const credentials = reactive({
    username: '',
    password: ''
    });

    const isLoading = ref(false);
    const error = ref('');

    // 处理登录表单提交的函数
    const handleLogin = async () => {
    // 1. 重置状态
    error.value = '';
    isLoading.value = true;

    try {
        // 2. 调用认证服务中的 login 函数
        const userData = await login({
        username: credentials.username,
        password: credentials.password
        });

        // 3. 登录成功：重定向到主页 (假设是 '/')
        // 此时 token 已经在 auth.js 中存储到 localStorage
        console.log('登录成功，用户信息:', userData);
        
        // 使用 replace 导航，用户无法通过后退键回到登录页
        router.replace('/'); 

    } catch (err) {
        // 4. 登录失败：显示错误消息
        console.error('登录失败:', err);
        // 根据后端返回的错误信息来设置 error 消息
        error.value = '登录失败，请检查用户名和密码。'; 
        // 生产环境中，您可能需要更详细地解析 err.response.data.message
        
    } finally {
        // 5. 无论成功或失败，都要解除加载状态
        isLoading.value = false;
    }
    };
</script>

<style scoped>
    /* 简单的 CSS 样式，您可以根据需要美化 */
    .login-container {
    max-width: 400px;
    margin: 50px auto;
    padding: 20px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    text-align: center;
    }

    .login-form {
    display: flex;
    flex-direction: column;
    }

    .form-group {
    text-align: left;
    margin-bottom: 15px;
    }

    label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
    }

    input {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box; /* 确保 padding 不会增加宽度 */
    }

    button {
    padding: 10px 15px;
    background-color: #42b983; /* Vue 绿色 */
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
    transition: background-color 0.3s;
    }

    button:hover:not(:disabled) {
    background-color: #368e65;
    }

    button:disabled {
    background-color: #a3d9bf;
    cursor: not-allowed;
    }

    .error-message {
    color: red;
    margin-top: -10px;
    margin-bottom: 15px;
    }

    .register-link {
        margin-top: 20px;
        font-size: 0.9em;
    }
</style>