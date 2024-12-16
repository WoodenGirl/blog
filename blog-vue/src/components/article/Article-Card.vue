<template>
  <el-card style="max-width: 100%">
    <el-row>
      <!--   封面   -->
      <el-col :span="5">
        <img
          :src="'/images/' + article.articleCover"
          style="width: 100%; max-height: 7rem"
        >
      </el-col>
      <!--   其他信息   -->
      <el-col :span="16" :offset="1">
        <!--   文章标题   -->
        <h2>{{ article.articleTitle }}</h2>
        <!--   文章分类   -->
        <p>文章分类: {{ article.categoryName }}</p>
        <!--   文章标签   -->
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
        <!--   文章创建时间   -->
        <p>{{ article.nickname }}创建于{{ formatDate(article.createTime) }}</p>
      </el-col>
    </el-row>

  </el-card>
</template>

<script setup lang="ts">


import { formatDate } from '@/tool/time'

defineProps(['article'])

</script>

<style scoped>
p {
  margin: 0.5rem 0;
}
</style>
