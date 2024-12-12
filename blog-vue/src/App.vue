<template>
  <div id="app">
    <el-container>
      <el-header><blog-header></blog-header></el-header>
      <el-container class="main">
        <el-container>
          <el-main>
            <router-view :key="$route.fullPath"></router-view>
          </el-main>
        </el-container>
      </el-container>
      <el-footer><blog-footer></blog-footer></el-footer>
    </el-container>
  </div>
</template>
<script setup lang="ts">

import BlogHeader from '@/components/layout/Blog-Header.vue'
import BlogFooter from '@/components/layout/Blog-Footer.vue'

</script>

<style scoped>
#app {
  height: 960px;
  display:flex;
  flex-direction: column;
  justify-content:space-between;
}
.main {
  flex: 1;
  overflow: hidden;
}
</style>
