<template>
  <div class="container">
    <!-- 标题 -->
    <p class="listTitle">{{ acgList.listTitle }}</p>
    <!-- 用户 -->
    <p class="flex gap-2 listUser">
      <el-avatar :src="acgList.avatar"></el-avatar>
      {{ acgList.nickname }}
    </p>
    <!-- acgRemark -->
    <div v-for="acgRemark in acgList.userRemarkDetails">
      <el-card class="margin20 acgRemark">
        <el-row>
          <!-- 封面 -->
          <el-col :span="4">
            <el-image :src="acgRemark.acgCover" class="acgCover" @click="router.push('/acgPreview/' + acgRemark.acgId)"></el-image>
          </el-col>
          <!-- 信息 -->
          <el-col :span="20">
            <p>名称：{{ acgRemark.acgName }}</p>
            <p>作者：{{ acgRemark.acgAuthor }}</p>
            <p><span>简介：<br/></span><el-text line-clamp="3" v-html="acgRemark.acgIntro"></el-text></p>
            <p>评分：<el-rate v-model="acgRemark.userRate" allow-half disabled :colors="['#99A9BF', '#F7BA2A', '#FF9900']" /></p>
            <p><span>评论：<br/></span><el-text line-clamp="4" v-html="acgRemark.userReview"></el-text></p>
          </el-col>
        </el-row>
      </el-card>
    </div>
    <!--  评论  -->
    <comment-all :linked-id="acgList.listId"></comment-all>
  </div>


</template>

<script setup lang="ts">
import type { ListDetail } from '@/entity/list'
import { ref } from 'vue'
import avatar from '@/assets/images/avatar.jpeg'
import test from '@/assets/images/test.jpeg'
import router from '@/router'
import CommentAll from '@/components/comment/Comment-All.vue'

defineProps(['listId'])
const acgList = ref<ListDetail>(
  {
     listId: '123',
     listTitle: '好看的治愈系动漫',

     userId: '123',
     nickname: 'aprilsxz',
     avatar: avatar,

     userRemarkDetails: [
       {
         acgId: '123',
         acgName: '夏目友人帐',
         acgAuthor: '绿川幸',
         acgIntro: '从小就能看见妖怪的少年夏目贵志自从他继承了外祖母收妖怪作小弟时作为证明写有妖怪名字的“友人帐”之后，就一直过着被妖怪追逐的日子。<br/>后来遇到自称保镖的妖怪猫咪老师，自此在它的陪伴下，决定将名字还给妖怪的夏目，开始和各式各样的妖怪相遇，并逐渐理解妖怪内心的想法。<br/>在和妖怪的相遇离别，以及了解心地善良的妖怪们的回忆的过程中，夏目不知不觉得到了很多珍贵的东西。',
         acgCover: test,
         userRate: 4.5,
         userReview: '从小就能看见妖怪的少年夏目贵志自从他继承了外祖母收妖怪作小弟时作为证明写有妖怪名字的“友人帐”之后，就一直过着被妖怪追逐的日子。<br/>后来遇到自称保镖的妖怪猫咪老师，自此在它的陪伴下，决定将名字还给妖怪的夏目，开始和各式各样的妖怪相遇，并逐渐理解妖怪内心的想法。<br/>在和妖怪的相遇离别，以及了解心地善良的妖怪们的回忆的过程中，夏目不知不觉得到了很多珍贵的东西。',
       },
     ],
   },
)
</script>

<style scoped>

.listTitle {
  font-size: 1.5rem;
}
.acgCover {
  width: 12rem;
  height: 16rem;
  cursor: pointer;
}
</style>
