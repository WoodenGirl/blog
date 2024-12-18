<template>
  <el-container>
    <el-aside>
      <category-all :is-edit="isEdit" :category-parent="categoryParent"></category-all>
    </el-aside>
    <el-main class="templateMain">
      <el-tabs v-model="activeName" class="demo-tabs" tab-position="right">
        <router-view :key="$route.fullPath"></router-view>
        <el-tab-pane label="article" :name="articleName" lazy>
          <article-list></article-list>
        </el-tab-pane>
        <el-tab-pane label="dynamic" name="dynamic" lazy>
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

const {isEdit, categoryParent } = storeToRefs(useCategoryStore())

const activeName = ref('first')
const articleName = ref()
articleName.value = categoryParent.value != 2 ? 'first' : 'article'


</script>

<style scoped>
.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}

.el-tabs--right .el-tabs__content,
.el-tabs--left .el-tabs__content {
  height: 100%;
}

.templateMain {
  margin: 0;
  padding: 0;
}
</style>
