<template>
  <!-- acg -->
  <div class="container">
    <!-- 基本信息 -->
    <el-row>
      <!--  封面  -->
      <el-col :span="4">
        <el-image
          :src="acgDetail.acg.acgCover"
          class="acgCover"
          fit="cover"
        />
      </el-col>
      <!--  基本信息  -->
      <el-col :span="20">
        <!--   名称     -->
        <p class="acgName">{{ acgDetail.acg.acgName }}</p>
        <!--   其他信息     -->
        <div class="acgInfo">
          <p>作者：{{ acgDetail.acg.acgAuthor }}</p>
          <p>类型：{{ acgDetail.categoryName }}</p>
          <p class="flex gap-2">标签:
            <el-tag
              v-for="tag in acgDetail.acg.acgTags.split(',')"
              :key="tag"
              size="default"
              :disable-transitions="false"
            >
              {{ tag }}
            </el-tag>
          </p>
          <p>年份：{{ acgDetail.acg.acgYear }}</p>
          <p>地区：{{ acgDetail.acg.acgRegion }}</p>
        </div>
      </el-col>
    </el-row>
    <!-- 简介 -->
    <el-row>
      <el-text line-clamp="2">
        简介：<span v-html="acgDetail.acg.acgIntro"></span>
      </el-text>
    </el-row>
    <!-- 补充信息 -->
    <el-row>
      <el-text line-clamp="2">
        补充信息：{{ acgDetail.acg.acgAddition }}
      </el-text>
    </el-row >
    <!-- 评论 -->
    <comment-all :linked-id="acgDetail.acg.acgId"></comment-all>
  </div>

</template>

<script setup lang="ts">
import { useRoute } from 'vue-router'
import type { Acg, AcgDetail } from '@/entity/acg'
import { ref } from 'vue'
import test from '@/assets/images/test.jpeg'
import CommentAll from '@/components/comment/Comment-All.vue'

const acgId = useRoute().params.acgId
const acgSingle = ref<Acg>({
  acgId: '456',   // id
  acgName: '夏目友人帐 第七季', // 名称
  acgAuthor: '绿川幸', // 作者
  acgActor: '神谷浩史', // 演员
  acgYear: '2024-10-7',  // 年份
  acgRegion: '日本',  // 地区
  acgIntro: '从小就能看见妖怪的少年夏目贵志自从他继承了外祖母收妖怪作小弟时作为证明写有妖怪名字的“友人帐”之后，就一直过着被妖怪追逐的日子。<br/>后来遇到自称保镖的妖怪猫咪老师，自此在它的陪伴下，决定将名字还给妖怪的夏目，开始和各式各样的妖怪相遇，并逐渐理解妖怪内心的想法。<br/>在和妖怪的相遇离别，以及了解心地善良的妖怪们的回忆的过程中，夏目不知不觉得到了很多珍贵的东西。', // 简介
  acgCover: test, // 封面
  acgState: 0, // 状态 '0': 连载中，'1': 已完结
  acgChapter: 9, // 章节
  acgAddition: 'none',  // 补充
  acgTags: '妖怪',  // 标签 大女主等

  categoryId: 51, // 类型
})
const acgDetail = ref<AcgDetail>({
  acg: acgSingle.value,
  categoryName: '动漫-奇幻', // 类型
})
</script>

<style scoped>
.acgCover {
  width: 12rem;
  height: 16rem;
}
.acgName {
  font-size: 1.5rem;
  margin-bottom: 1rem;
}
.acgInfo p {
  margin: 0.5rem 0;
}
.el-row {
  margin-bottom: 20px;
}
</style>
