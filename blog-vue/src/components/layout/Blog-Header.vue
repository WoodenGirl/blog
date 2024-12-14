<template>
  <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    :ellipsis="false"
    @select="handleSelect"
  >
    <el-menu-item index="1">
      <el-avatar :size="50" :src="logo" /> &nbsp; Aplrilsxz
    </el-menu-item>
    <el-menu-item index="2">ACG</el-menu-item>
    <el-menu-item index="3">Interest</el-menu-item>
    <el-menu-item index="4">Technique</el-menu-item>
    <el-menu-item index="5">Life</el-menu-item>

    <el-menu-item index="6"><router-link to="/dynamic">Dynamic</router-link></el-menu-item>
    <el-menu-item index="7" @click="articlesStore.isEdit = false"><router-link to="/create">Create</router-link></el-menu-item>
  </el-menu>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import logo from '@/assets/images/logo.png'
import { useCategoryStore } from '@/stores/category'
import { storeToRefs } from 'pinia'
import router from '@/router'

const activeIndex = ref('0')

const { isEdit, categoryParent } = storeToRefs(useCategoryStore())

const handleSelect = (key: string) => {
  switch (key) {
    case '1': {
      isEdit.value = true
      categoryParent.value = 0
      router.push('/');
      break
    }
    case '2': {
      isEdit.value = false
      categoryParent.value = 2
      router.push('/acg');
      break
    }
    case '3': {
      isEdit.value = false
      categoryParent.value = 3
      router.push('/interest');
      break
    }
    case '4': {
      isEdit.value = false
      categoryParent.value = 4
      router.push('/technique');
      break
    }
    case '5': {
      isEdit.value = false
      categoryParent.value = 5
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
