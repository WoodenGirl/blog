<template>
  <div class="acgSingle">
    <!--   封面   -->
    <div class="imageWrapper">
      <el-image
        :src="acg.acgCover"
        class="acgCover"
        fit="cover"
      />
      <div class="acgStatus">
        {{ acg.acgState == 0 ? '连载中/' + acg.acgChapter : '已完结'}}
      </div>
    </div>
    <!--  名称  -->
    <div class="acgName"> {{ acg.acgName }} </div>
  </div>
</template>

<script setup lang="ts">

defineProps(['acg'])

</script>

<style scoped>
.acgSingle {
  width: 13rem;
  display: inline-block;
  padding: 0 1rem 1rem 0;
}
.imageWrapper {
  height: 16rem;
  position: relative;
}
.acgCover {
  width: 100%;
  height: 100%;
  border-radius: 2%;
}
.acgStatus {
  position: absolute;
  bottom: 0;
  right: 0;
  font-size: 18px;
  color: white;
}
.acgName {
  width: 100%;
  text-align: left;
}
.acgName:hover {
  color: #31beff;
}
</style>
