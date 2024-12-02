<template>
  <el-card>
    <el-row>
      <!--  头像  -->
      <el-col :span="1">
        <el-avatar size="default" fit="cover" :src="dynamic.avatar"/>
      </el-col>
      <!--  昵称 创建时间 -->
      <el-col :span="21">
        <p class="nickname">{{ dynamic.nickname }}</p>
        {{ formatDate(dynamic.createdTime) }}
      </el-col>
    </el-row>
    <!--  内容  -->
    <el-row class="content">
      <p>{{ dynamic.dynamicContent }}</p>
    </el-row>

    <!--  评论 点赞  -->
    <el-row>
      <el-col :span="1">
        <a class="icon element-icons" :class="{'el-icon-dianzan1': !isLike, 'el-icon-dianzan2': isLike}" @click="triggerLike"></a> 3
      </el-col>
      <el-col :span="1" :offset="1">
        <a class="icon element-icons" :class="{'el-icon-pinglun1': !isComment, 'el-icon-pinglun2': isComment}" @click="triggerComment"></a> 5
      </el-col>
    </el-row>

    <!--  评论详情  -->
    <el-row :class="{'noDisplay': !isComment}">
      <comment-all :linked-id="dynamic.dynamicId"></comment-all>
    </el-row>

  </el-card>

</template>

<script setup lang="ts">

import { formatDate } from '@/assets/ts/tool'
import CommentAll from '@/components/comment/Comment-All.vue'
import { ref } from 'vue'

defineProps(['dynamic'])

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

</script>

<style scoped>
p {
  margin: 0.5rem 0;
}
.noDisplay {
  display: none;
}
</style>
