<template>
  <el-container>
    <el-aside>
      <category-all :is-edit="isEdit" :category-parent="categoryParent"></category-all>
    </el-aside>
    <el-main>
      <el-tabs v-model="activeName" class="demo-tabs">
        <router-view :key="$route.fullPath"></router-view>
        <el-tab-pane label="article" name="article">
          <article-list></article-list>
        </el-tab-pane>
        <el-tab-pane label="dynamic" name="dynamic">
          <dynamic-list></dynamic-list>
        </el-tab-pane>
      </el-tabs>
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import CategoryAll from '@/components/category/Category-All.vue'
import { storeToRefs } from 'pinia'
import { useCategoryStore } from '@/stores/category'
import DynamicList from '@/components/dynamic/Dynamic-List.vue'
import ArticleList from '@/components/article/Article-List.vue'
import { ref } from 'vue'

const activeName = ref('article')
const {isEdit, categoryParent } = storeToRefs(useCategoryStore())


</script>

<style scoped>

</style>
