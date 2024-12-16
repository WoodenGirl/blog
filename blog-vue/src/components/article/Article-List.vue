<template>
  <el-timeline style="max-width: 70%">
    <el-timeline-item
      :timestamp="formatDate(article.updateTime)"
      placement="top"
      v-for="article in articles"
      :key="article.articleId"
    >
      <article-card :article="article" @click="toPreview(article.articleId)"></article-card>
    </el-timeline-item>
  </el-timeline>


  <el-pagination
    v-model:current-page="currentPage"
    :page-size="pageSize"
    size="default"

    layout="total, prev, pager, next"
    :total="1000"
    @size-change="fetchArticles"
    @current-change="fetchArticles"
  />

</template>

<script setup lang="ts">
import ArticleCard from '@/components/article/Article-Card.vue'
import { formatDate } from '@/tool/time'
import router from '@/router'
import { storeToRefs } from 'pinia'
import type { ArticleBrief } from '@/entity/article'
import { useCategoryStore } from '@/stores/category'
import { ref, watch } from 'vue'
import { queryBriefArticle } from '@/api/article'

const {category} = storeToRefs(useCategoryStore())

watch(() => category.value, () => {
  console.log(category.value)
  fetchArticles()
})

// 获取数据
const articles = ref<ArticleBrief[]>([])
// 分页
const currentPage = ref(1)
const pageSize = ref(10)

const fetchArticles = async () => {
  articles.value = await queryBriefArticle(category.value.categoryId, currentPage.value, pageSize.value).then((res) => res.data)
}
fetchArticles()

// 点击查看文章
const toPreview = (articleId: string) => {
  router.push({path: `/articles/`+ articleId});
}
</script>

<style scoped>

</style>
