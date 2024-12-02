<template>
  <div style="width:70%;">
    <!-- 发表动态  -->
    <dynamic-input :category-id="categoryId" @rerender="fetchDynamics"></dynamic-input>
    <!-- 展示动态  -->
    <div v-for="dynamic of dynamics" :key="dynamic.dynamicId">
      <dynamic-brief :dynamic="dynamic"></dynamic-brief>
    </div>
  </div>

</template>

<script setup lang="ts">
import DynamicBrief from '@/components/dynamic/Dynamic-Brief.vue'
import { queryDynamic } from '@/api/dynamic'
import { ref } from 'vue'
import DynamicInput from '@/components/dynamic/Dynamic-Input.vue'
import type { Dynamic } from '@/entity/dynamic'
import { useCategoryStore } from '@/stores/category'

const categoryId = useCategoryStore().categoryId

// 获取数据
const dynamics = ref<Dynamic[]>([])
const fetchDynamics = async () => {
  await queryDynamic(categoryId).then((res) => {
    dynamics.value = res.data
  })
}
fetchDynamics()



</script>

<style scoped>

</style>
