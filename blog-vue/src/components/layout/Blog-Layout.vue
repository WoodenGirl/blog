<template>
  <el-container>
    <el-header><blog-header></blog-header></el-header>
    <el-container>
      <el-container>
        <el-main>
          <router-view :key="$route.fullPath"></router-view>
        </el-main>
      </el-container>
    </el-container>
    <el-footer><blog-footer></blog-footer></el-footer>
  </el-container>
</template>

<script setup lang="ts">
import BlogFooter from '@/components/layout/Blog-Footer.vue'
import BlogHeader from '@/components/layout/Blog-Header.vue'
</script>

<style scoped>
/*.templateMain {
  padding-top: 0;
}*/
</style>
