<template>
    <el-tab-pane label="source" name="source">
      <acg-index></acg-index>
    </el-tab-pane>
    <el-tab-pane label="list" name="list">
      <list-index></list-index>
    </el-tab-pane>
</template>

<script setup lang="ts">
import AcgIndex from '@/components/acg/source/Acg-Index.vue'
import ListIndex from '@/components/acg/list/List-Index.vue'


</script>

<style scoped>
.demo-tabs > .el-tabs__content {
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}
</style>
