<template>
  <div style="width:70%;">
    <!-- 发表动态  -->
    <dynamic-input :category-id="category!.id" @rerender="fetchDynamics(category!.id)"></dynamic-input>
    <!-- 展示动态  -->
    <div v-for="dynamic of dynamics" :key="dynamic.dynamicId">
      <dynamic-brief :dynamic="dynamic" class="dynamic-brief" @rerender="fetchDynamics(category!.id)"></dynamic-brief>
    </div>
  </div>

</template>

<script setup lang="ts">
import DynamicBrief from '@/components/dynamic/Dynamic-Brief.vue'
import { queryDynamic } from '@/api/dynamic'
import { ref, watch } from 'vue'
import DynamicInput from '@/components/dynamic/Dynamic-Input.vue'
import type { Dynamic } from '@/entity/dynamic'
import { storeToRefs } from 'pinia'
import { useCategoryStore } from '@/stores/category'

const {category} = storeToRefs(useCategoryStore())

watch(() => category.value, (newValue) => {
  fetchDynamics(newValue!.id)
})

// 获取数据
const dynamics = ref<Dynamic[]>([])
const fetchDynamics = async (categoryId: number) => {
  dynamics.value = await queryDynamic(categoryId).then((res) => res.data)
}
fetchDynamics(category.value!.id)

</script>

<style scoped>
.dynamic-brief {
  margin: 1rem 0;
}
</style>
