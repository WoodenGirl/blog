<template>
 <div class="container">
   <el-row >
     <!--  头像  -->
     <el-col :span="1">
       <el-avatar size="default" fit="cover" :src="'/images/'+ dynamic.avatar"/>
     </el-col>
     <!--  昵称 创建时间 -->
     <el-col :span="22">
       <p class="nickname">{{ dynamic.nickname }}</p>
       {{ formatDate(dynamic.createTime) }}
     </el-col>
   </el-row>
   <!--  内容  -->
   <div class="content">
     <p>{{ dynamic.dynamicContent }}</p>
   </div>
   <!--  图片  -->
   <div>
     <el-image
       v-for="src in dynamic.dynamicImages"
       :key="src"
       class="imgList"
       :src="'/images/' + src"
       fit="cover"
       @click="handlePicturePreview(src)"
     />
   </div>
   <el-dialog v-model="dialogVisible">
     <img
       w-full
       :src="'/images/' + dialogImageUrl"
       alt="Preview Image"
       style="max-height: 100%; max-width: 100%"
     />
   </el-dialog>

   <el-divider/>
   <!--  评论详情  -->
   <comment-all :linked-id="dynamic.dynamicId"></comment-all>
 </div>
</template>

<script setup lang="ts">

import { formatDate } from '@/tool/time'
import CommentAll from '@/components/comment/Comment-All.vue'
import { ref } from 'vue'
import { useRoute } from 'vue-router'
import type { DynamicDetail } from '@/entity/dynamic'
import { queryDynamicById } from '@/api/dynamic'

// 接收参数
const dynamicId = useRoute().params.dynamicId.toString()
const dynamic = ref<DynamicDetail>({
  dynamicId: '',
  dynamicContent: '',
  createTime: '2024-12-23',
  dynamicImages: [],

  userId: '',
  nickname: '',
  avatar: '',
})
const fetchDynamic = async () => {
  dynamic.value = await queryDynamicById(dynamicId).then(res => res.data)
}
fetchDynamic()

// 预览图片
const dialogImageUrl = ref('')
const dialogVisible = ref(false)
const handlePicturePreview  = (url: string) => {
  dialogImageUrl.value = url
  dialogVisible.value = true
}

</script>

<style scoped>
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
</style>
