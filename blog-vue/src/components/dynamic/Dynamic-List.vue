<template>
  <div class="container">
    <!-- 发表动态  -->
    <dynamic-input :category-id="category!.categoryId" @rerender="fetchDynamics()"></dynamic-input>
    <!-- 展示动态  -->
    <div v-for="dynamic of dynamics" :key="dynamic.dynamicId">
      <dynamic-brief :dynamic="dynamic" class="dynamic-brief" @rerender="fetchDynamics()"></dynamic-brief>
    </div>
    <el-pagination
      v-model:current-page="currentPage"
      :page-size="pageSize"
      size="default"

      layout="total, prev, pager, next"
      :total="1000"
      @size-change="fetchDynamics"
      @current-change="fetchDynamics"
    />
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

watch(() => category.value, () => {
  fetchDynamics()
})

// 获取数据
const dynamics = ref<Dynamic[]>([])
// 分页
const currentPage = ref(1)
const pageSize = ref(10)

const fetchDynamics = async () => {
  dynamics.value = await queryDynamic(category.value.categoryId, currentPage.value, pageSize.value).then((res) => res.data)
}
fetchDynamics()

</script>

<style scoped>
.dynamic-brief {
  margin: 1rem 0;
}
</style>
