<template>
  <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    :ellipsis="false"
    @select="handleSelect"
  >
    <el-menu-item index="0">
      <router-link to="/"><el-avatar :size="50" :src="logo" /></router-link>
    </el-menu-item>
    <el-menu-item index="1"><router-link to="/">Home</router-link></el-menu-item>
    <el-menu-item index="3"><router-link to="/dynamic">Dynamic</router-link></el-menu-item>
    <el-menu-item index="2" @click="articlesStore.isEdit = false"><router-link to="/create">Create</router-link></el-menu-item>
  </el-menu>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import logo from '@/assets/images/logo.png'
import { useArticlesStore } from '@/stores/article'

const activeIndex = ref('1')
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}

const articlesStore = useArticlesStore()

</script>

<style scoped>
.el-menu--horizontal > .el-menu-item:nth-child(1) {
  margin-right: auto;
}
</style>
