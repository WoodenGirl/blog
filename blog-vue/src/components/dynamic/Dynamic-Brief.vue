<template>
  <el-card>
    <el-row >
      <!--  头像  -->
      <el-col :span="1">
        <el-avatar size="default" fit="cover" :src="'/images/'+ dynamic.avatar"/>
      </el-col>
      <!--  昵称 创建时间 -->
      <el-col :span="21" style="margin-left: 1rem">
        <p class="nickname">{{ dynamic.nickname }}</p>
        {{ formatDate(dynamic.createTime) }}
      </el-col>
      <!--  删除 -->
      <el-col :span="1">
        <a @click="remove()"> <Delete class="edit-icon"></Delete> </a>
      </el-col>
    </el-row>
    <!--  内容  -->
    <el-row class="content">
      <p>{{ dynamic.dynamicContent }}</p>
    </el-row>
    <!--  图片  -->
    <el-row>
      <el-image
        v-for="src in srcList"
        :key="src"
        class="imgList"
        :src="'/images/' + src"
        fit="cover"
        @click="handlePicturePreview(src)"
      />
    </el-row>
    <el-dialog v-model="dialogVisible">
      <img
        w-full
        :src="'/images/' + dialogImageUrl"
        alt="Preview Image"
        style="max-height: 100%; max-width: 100%"
      />
    </el-dialog>
    <!--  评论 点赞  -->
    <el-row>
      <el-col :span="1">
        <a class="icon element-icons" :class="{'el-icon-dianzan1': !isLike, 'el-icon-dianzan2': isLike}" @click="triggerLike"></a> 3
      </el-col>
      <el-col :span="1" :offset="1">
        <a class="icon element-icons" :class="{'el-icon-pinglun1': !isComment, 'el-icon-pinglun2': isComment}" @click="triggerComment"></a>
        {{ commentCount }}
      </el-col>
    </el-row>

    <!--  评论详情  -->
    <el-row :class="{'noDisplay': !isComment}">
      <comment-all :linked-id="dynamic.dynamicId"></comment-all>
    </el-row>

  </el-card>

</template>

<script setup lang="ts">

import { formatDate } from '@/tool/time'
import CommentAll from '@/components/comment/Comment-All.vue'
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { Delete } from '@element-plus/icons-vue'
import { deleteDynamic } from '@/api/dynamic'
import { countComment } from '@/api/comment'

const props = defineProps(['dynamic'])
const emits = defineEmits(['rerender'])

// 图片列表
const srcList = ref<string[]>([])
srcList.value = props.dynamic.dynamicImages.split(",")
srcList.value.pop()
// 预览图片
const dialogImageUrl = ref('')
const dialogVisible = ref(false)
const handlePicturePreview  = (url: string) => {
  dialogImageUrl.value = url
  dialogVisible.value = true
}

// 删除
const remove = () => {
  deleteDynamic(props.dynamic.dynamicId).then(() => {
    ElMessage.success("删除成功")
    // 重新渲染
    emits('rerender')
  })
}

// 点赞
const isLike = ref(false)
const triggerLike = () => {
  isLike.value = !isLike.value
}
// 评论
const isComment = ref(false)
const triggerComment = () => {
  isComment.value = !isComment.value
}
// 评论数
const commentCount = ref()
countComment(props.dynamic.dynamicId).then(res => commentCount.value = res.data)

</script>

<style scoped>
.edit-icon {
  width: 20px;
}
p {
  margin: 0.5rem 0;
}
.imgList {
  display: inline-block;
  margin-right: 3rem;
  max-height: 10rem;
  max-width: 10rem;
  margin-bottom: 1rem;
}
.imgList:hover {
  cursor: pointer;
}
.noDisplay {
  display: none;
}
</style>
