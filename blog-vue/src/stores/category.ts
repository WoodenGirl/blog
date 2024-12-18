import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { Category, CategoryTree } from '@/entity/category'

export const useCategoryStore = defineStore('category', () => {
  const category = ref<CategoryTree>({
    categoryId: 1,
    categoryName: 'Aprilsxz',
    isInterrupt: 0,
    categoryChildren: []
  })
  const isEdit = ref(true)
  const categoryParent = ref(0)

  return { category, isEdit, categoryParent }}, {
  persist: {
    storage: sessionStorage,
  }
})
