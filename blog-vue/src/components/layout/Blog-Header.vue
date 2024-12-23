<template>
  <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    :ellipsis="false"
    @select="handleSelect"
  >
    <el-menu-item index="1">
      <el-avatar :size="50" :src="logo" /> &nbsp; 木头女孩
    </el-menu-item>
    <el-menu-item index="2">ACG</el-menu-item>
    <el-menu-item index="3">Interest</el-menu-item>
    <el-menu-item index="4">Technique</el-menu-item>
    <el-menu-item index="5">Life</el-menu-item>

    <el-menu-item index="6"><router-link to="/articleCreate">Create</router-link></el-menu-item>
    <el-menu-item index="7" v-if="useUserStore().user">
      <router-link to="/userCenter">
        <el-avatar :src="useUserStore().user.avatar"></el-avatar>
      </router-link>
    </el-menu-item>
    <el-menu-item index="7" v-else><router-link to="/login">Login</router-link></el-menu-item>
  </el-menu>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import logo from '@/assets/images/logo.png'
import { useCategoryStore } from '@/stores/category'
import { storeToRefs } from 'pinia'
import router from '@/router'
import { useUserStore } from '@/stores/user'

const activeIndex = ref('0')

const { isEdit, categoryParent } = storeToRefs(useCategoryStore())

const handleSelect = (key: string) => {
  switch (key) {
    case '1': {
      isEdit.value = true
      categoryParent.value = 0
      useCategoryStore().activeName = 'article'
      router.push('/');
      break
    }
    case '2': {
      isEdit.value = false
      categoryParent.value = 2
      useCategoryStore().activeName = 'source'
      router.push('/acg');
      break
    }
    case '3': {
      isEdit.value = false
      categoryParent.value = 3
      useCategoryStore().activeName = 'article'
      router.push('/interest');
      break
    }
    case '4': {
      isEdit.value = false
      categoryParent.value = 4
      useCategoryStore().activeName = 'article'
      router.push('/technique');
      break
    }
    case '5': {
      isEdit.value = false
      categoryParent.value = 5
      useCategoryStore().activeName = 'article'
      router.push('/life');
      break
    }
  }
}
</script>

<style scoped>
.el-menu--horizontal > .el-menu-item:nth-child(5) {
  margin-right: auto;
}
</style>
