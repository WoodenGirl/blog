<template>
  <el-row>
    <!--  用户信息  -->
    <el-col :span="8">
      <user-info></user-info>
    </el-col>
    <!--  我的  -->
    <el-col>

    </el-col>
  </el-row>
</template>

<script setup lang="ts">

import UserInfo from '@/components/user/UserInfo.vue'
</script>

<style scoped>

</style>
