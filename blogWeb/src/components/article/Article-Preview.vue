<template>
  <div style="width:80%;">
    <!--  文章  -->
    <div class="container">
      <!--  背景  -->
      <el-image :src="'/images/' + article!.articleCover" fit="fill" />

      <!--  标头  -->
      <div class="bgContent">
        <!--  编辑  -->
        <el-row :gutter="20">
          <div class="flex gap-2 edit">
            <a @click="edit()"> <Edit class="edit-icon"></Edit> </a>
            <a @click="remove()"> <Delete class="edit-icon"></Delete> </a>
          </div>
        </el-row>

        <!--  标题  -->
        <el-row :gutter="20">
          <h1 class="center">{{ article!.articleTitle }}</h1>
        </el-row>
        <!--  作者 时间  -->
        <el-row :gutter="20">
          <div class="flex gap-2 center">
            <span>{{ article!.nickname }}</span>
            <span><el-icon size="20"><clock/></el-icon>创建于{{ formatDate(article!.createdTime) }}</span>
            <span><el-icon size="20"><clock/></el-icon>最近更新于{{ formatDate(article!.updateTime) }}</span>
          </div>
        </el-row>
        <!--  分类 标签  -->
        <el-row :gutter="20">
          <div class="flex gap-2 center">
            <span>文章分类: {{ article!.categoryDetail }}</span>
            <span>标签:
          <el-tag
            v-for="tag in article!.articleTags.split(',')"
            :key="tag"
            size="default"
            :disable-transitions="false"
          >
            {{ tag }}
          </el-tag></span>
          </div>
        </el-row>
      </div>

      <!--  正文  -->
      <div class="w-e-text-container article-content-container">
        <div v-html="article!.articleContent" data-slate-editor></div>
      </div>
    </div>
    <el-divider />
    <!--  评论  -->
    <comment-all :linked-id="article!.articleId"></comment-all>
  </div>

</template>

<script setup lang="ts">
import { formatDate } from '@/assets/ts/tool'
import { Clock, Delete, Edit } from '@element-plus/icons-vue'
import { useArticlesStore } from '@/stores/article'
import router from '@/router'
import { deleteArticle } from '@/api/article'
import { ElMessage } from 'element-plus'
import { popObject } from '@/assets/ts/obs'
import { storeToRefs } from 'pinia'
import CommentAll from '@/components/comment/Comment-All.vue'

const articlesStore = useArticlesStore()
const {article} = storeToRefs(articlesStore)

const edit = () => {
  articlesStore.isEdit = true
  router.push("/create")
}
const remove = () => {
  // 从obs删除封面
  popObject(article.value!.articleCover)
  deleteArticle(article.value!.articleId).then(() => {
    ElMessage.success("删除成功！")
    // 跳转上一页
    router.go(-1)
  })
}
</script>

<style scoped>
.container {
  width: 100%;
  position: relative;
}
.el-image {
  opacity: 0.5;
  background-size: cover;
  width: 100%;
  height: 500px;
  position: relative;
}
.bgContent {
  width: 100%;
  position: absolute;
  top: 150px;
}

.edit {
  position: absolute;
  top: 0;
  right: 20px;
}

.el-row {
  margin-bottom: 20px;
}
.edit-icon {
  width: 20px;
}
span {
  margin: 0 0.5rem;
  display: flex;
  justify-content: center;
  align-items: center;
}
.article-content-container {
  margin: 30px 0;
}
</style>
