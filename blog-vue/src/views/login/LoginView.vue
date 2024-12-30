<template>
  <div class="loginContainer">
    <el-form
      ref="loginFormRef"
      style="max-width: 500px"
      :model="loginForm"
      :rules="loginRules"
      label-width="auto"
      class="loginForm"
      size="default"
      status-icon
    >
      <p class="loginTitle">Login</p>

      <el-form-item prop="nickname">
        <el-input v-model="loginForm.nickname" placeholder="nickname"/>
      </el-form-item>

      <el-form-item prop="password">
        <el-input v-model="loginForm.password" type="password" autocomplete="off" placeholder="password"/>
      </el-form-item>

      <a class="registerA">还没有账号？点击注册一个</a>

      <el-form-item>
        <el-button class="loginBtn" @click="login(loginFormRef)">
          login
        </el-button>
      </el-form-item>

      <a :href="loginUrl">
        <el-image :src="qq" class="qqIcon"></el-image>
      </a>

    </el-form>
  </div>


</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
import type { User } from '@/entity/user'
import router from '@/router'
import qq from '@/assets/images/qq.png'
import { useRoute } from 'vue-router'
import { loginByQQ } from '@/api/QQLogin'

const loginFormRef = ref<FormInstance>()
const loginForm = reactive<User>({
  nickname: 'Hello',
  password: '12345678'
})
const loginRules = reactive<FormRules<User>>({
  nickname: [
    { min: 3, max: 5, message: 'Length should be 3 to 5', trigger: 'blur' },
  ],
  password: [
    {min: 8, max: 20, message: 'Length should be 8 to 20', trigger: 'blur', },
  ],
})
const login = async (formEl: FormInstance | undefined) => {
  console.log("click")
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      router.push('/')
      console.log('submit!')
    } else {
      console.log('error submit!', fields)
    }
  })
}
const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}

const loginUrl = 'https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=' + import.meta.env.VITE_APP_ID + '&redirect_uri=https%3A%2F%2Fwww.aprilsxz.fun%2Flogin'

// 获取code
const code = useRoute().query.code?.toString()
console.log(code)
if (code) {
  loginByQQ(code).then(res => {
    if (res.code === 200) {
      router.push('/')
    }
  })
}

</script>

<style scoped>
.loginContainer {
  background-image: url('@/assets/images/loginBg.jpg');
  background-size: cover;
  width: 100%;
  height: 100%;
}
.loginTitle {
  text-align: center;
  font-size: 26px;
  margin-bottom: 1rem;
}
.loginForm {
  padding: 3rem;
  margin: 10rem auto;
}
.registerA {
  display: inline-block;
  margin-bottom: 1rem;
  color: #9a6e3a;
}
.loginBtn {
  width: 100%;
}
.qqIcon {
  width: 5rem;
}

</style>

