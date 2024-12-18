<template>
  <el-card>
    <p class="listTitle">{{ acgList.listTitle }}</p>
    <p class="flex gap-2 listUser"><el-avatar :src="acgList.avatar"></el-avatar>{{ acgList.nickname }}</p>
    <el-image
      v-for="acgCover in acgList.acgCovers.slice(0,14)"
      :src="acgCover"
      class="acgCover"
    />
  </el-card>
</template>

<script setup lang="ts">
defineProps(['acgList'])

</script>

<style scoped>
.listTitle {
  font-size: 1.3rem;
}
.listUser {
  margin: 0.5rem 0;
}
.acgCover {
  display: inline-block;
  width: 5rem;
  height: 7rem;
  margin-right: 1rem;
}
</style>
