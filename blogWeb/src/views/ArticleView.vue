<template>
  <div style="width:80%;">
    <article-preview :article="article"></article-preview>
    <el-divider />
    <comment-all :linked-id="article?.articleId"></comment-all>
  </div>
</template>

<script setup lang="ts">
import CommentAll from '@/components/comment/Comment-All.vue'
import ArticlePreview from '@/components/article/Article-Preview.vue'
import { useArticlesStore } from '@/stores/article'
import { storeToRefs } from 'pinia'

const {article} = storeToRefs(useArticlesStore())

</script>

<style scoped>
</style>
