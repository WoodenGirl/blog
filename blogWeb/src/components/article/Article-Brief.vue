<template>
  <el-card style="max-width: 100%">
    <el-row>
      <el-col :span="5">
        <img
          :src="'/images/' + article.articleCover"
          style="width: 100%"
        >
      </el-col>
      <el-col :span="16" :offset="1">
        <h2>{{ article.articleTitle }}</h2>
        <p>文章分类: {{ article.categoryDetail }}</p>
        <p class="flex gap-2">文章标签:
          <el-tag
          v-for="tag in article.articleTags.split(',')"
          :key="tag"
          :disable-transitions="false"
          size="default"
          >
          {{ tag }}
          </el-tag>
        </p>
        <p>{{ article.nickname }}创建于{{ formatDate(article.createdTime) }}</p>
      </el-col>
    </el-row>

  </el-card>
</template>

<script setup lang="ts">

import { formatDate } from '@/assets/ts/tool'

defineProps(['article'])

</script>

<style scoped>
p {
  margin: 0.5rem 0;
}
</style>
