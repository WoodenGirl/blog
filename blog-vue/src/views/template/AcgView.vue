<template>
    <el-tab-pane label="source" name="first">
      <acg-list></acg-list>
    </el-tab-pane>
    <el-tab-pane label="list" name="second">Config</el-tab-pane>
</template>

<script setup lang="ts">
import AcgList from '@/components/acg/source/Acg-List.vue'


</script>

<style scoped>
.demo-tabs > .el-tabs__content {
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}
</style>
