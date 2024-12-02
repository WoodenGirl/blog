<template>
  <el-row style="width: 100%">
    <!--  头像  -->
    <el-col :span="1">
      <el-avatar :src="useUserStore().user.avatar" size="default" fit="cover"></el-avatar>
    </el-col>
    <!--  输入  -->
    <el-col :span="20" class="comment-input">
      <el-input
        type="textarea"
        autosize
        v-model="commentContent"
        placeholder="Please input"
      >
      </el-input>
    </el-col>
    <!--  提交  -->
    <el-col :span="3" class="comment-commit">
      <el-button @click="submit">发表评论</el-button>
    </el-col>
  </el-row>

</template>

<script setup lang="ts">
import { useUserStore } from '@/stores/user'
import { ref } from 'vue'

const emits = defineEmits(['submitComment'])

const commentContent = ref()
const submit = () => {
  // 传递评论内容，发送事件
  emits('submitComment', commentContent.value)
  // 清空输入框
  commentContent.value = ''
}
</script>

<style scoped>
.comment-input {
  padding: 5px;
  text-align: center;
}
.comment-commit {
  padding: 5px;
  text-align: center;
}
</style>
