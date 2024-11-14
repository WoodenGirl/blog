<template>
  <el-timeline style="max-width: 70%">
    <el-timeline-item
      :timestamp="formatDate(article.updateTime)"
      placement="top"
      v-for="article in articles"
      :key="article.articleId"
    >
      <article-brief :article="article" @click="toPreview(article)"></article-brief>
    </el-timeline-item>
  </el-timeline>
</template>

<script setup lang="ts">
import ArticleBrief from '@/components/article/Article-Brief.vue'
import { formatDate } from '@/assets/ts/date'
import router from '@/router'
import type { Article } from '@/assets/ts/interface'
import { useArticlesStore } from '@/stores/articles'
import { storeToRefs } from 'pinia'

// 获取数据
const articlesStore = useArticlesStore()
const {articles} = storeToRefs(articlesStore)
if (articlesStore.queryAll) articlesStore.fetchArticles()


// 点击查看文章
const toPreview = (article: Article) => {
  router.push({name: 'preview', params: {articleJson: JSON.stringify(article)}});
}


</script>

<style scoped>

</style>
