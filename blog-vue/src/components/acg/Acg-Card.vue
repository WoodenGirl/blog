<template>
  <el-card style="max-width: 100%">
    <!--   封面   -->
    <el-image
      :src="'/images/' + acg.acgCover"
      style="width: 100%; max-height: 7rem"
    />
    <!--  名称  -->
    <div> {{ acg.acgName }} </div>
  </el-card>
</template>

<script setup lang="ts">
import { formatDate } from '@/tool/time'
defineProps(['acg'])
</script>

<style scoped>

</style>
