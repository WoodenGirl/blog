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
      <el-col :span="17" :offset="1">
        <!--   文章标题   -->
        <h2>{{ article.articleTitle }}</h2>
        <!--   文章分类   -->
        <p>文章分类: {{ article.categoryName }}</p>
        <!--   文章标签   -->
        <p class="flex gap-2">文章标签:
          <el-tag
          v-for="tag in article.articleTags"
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

      <el-col :span="1">
        <el-dropdown>
          <span class="more">...</span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="editArticle"><el-icon><Edit/></el-icon>编辑</el-dropdown-item>
              <el-dropdown-item  @click="removeArticle"><el-icon><Delete/></el-icon>删除</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>

      </el-col>
    </el-row>

  </el-card>
</template>

<script setup lang="ts">


import { formatDate } from '@/tool/time'
import { Delete, Edit } from '@element-plus/icons-vue'
import { deleteArticle } from '@/api/article'
import { ElMessage } from 'element-plus'
import router from '@/router'

const props = defineProps(['article'])
const emits = defineEmits(['rerender'])

const editArticle = () => {
  router.push('/articleCreate?articleId='+ props.article.articleId)
}
const removeArticle = () => {
  deleteArticle(props.article.articleId).then((res) => {
    if (res.code === 200) {
      ElMessage.success("删除文章成功！")
      emits('rerender')
    }
  })
}

</script>

<style scoped>
p {
  margin: 0.5rem 0;
}
.more {
  cursor: pointer;
}
.more:focus-visible {
  outline: none;
}
</style>
