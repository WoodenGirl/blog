<template>
  <div id="app">
    <router-view :key="$route.fullPath"></router-view>
  </div>
</template>

<script setup lang="ts">

</script>

<style scoped>
#app {
  height: 960px;
  display:flex;
  flex-direction: column;
  justify-content:space-between;
}

</style>
