<template>
  <!-- 查询 -->
  <el-form inline>
    <el-form-item>
      <el-input v-model="keyWord" placeholder="输入关键字查询"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary">查询</el-button>
    </el-form-item>

    <a class="createList" @click="router.push('/listCreate')">资历丰富？来创建自己的Acg list 吧！</a>
  </el-form>
  <!-- 展示 -->
  <div v-for="acgList in acgLists" :key="acgList.listId">
    <list-card :acg-list="acgList" class="listCard" @click="router.push('/listPreview/' + acgList.listId)"></list-card>
  </div>
</template>

<script setup lang="ts">

import ListCard from '@/components/acg/list/List-Card.vue'
import { ref } from 'vue'
import type { ListBrief, ListDetail } from '@/entity/list'
import avatar from '@/assets/images/avatar.jpeg'
import test from '@/assets/images/test.jpeg'
import router from '@/router'

const keyWord = ref<string>('')

const acgLists = ref<ListBrief[]>([
  {
    listId: '123',
    listTitle: '好看的治愈系动漫',
    listStatus: 1,

    userId: '123',
    nickname: 'aprilsxz',
    avatar: avatar,
    acgCovers: [test, test, test],
  },
  {
    listId: '123',
    listTitle: '好看的治愈系动漫',
    listStatus: 1,

    userId: '123',
    nickname: 'aprilsxz',
    avatar: avatar,
    acgCovers: [test, test, test],
  },

])
</script>

<style scoped>
.createList {
  display: inline-block;
  position: absolute;
  right: 0;


  font-style: italic;
  text-decoration: underline;
  cursor: pointer;
  color: #2d8dba;
}
.listCard {
  margin-bottom: 20px;
}
</style>
