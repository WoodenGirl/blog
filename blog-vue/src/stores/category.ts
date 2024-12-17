import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { Category, CategoryTree } from '@/entity/category'

export const useCategoryStore = defineStore('category', () => {
  const category = ref<Category>({
    categoryId: 1,
    categoryName: 'Aprilsxz',
    isInterrupt: 0,
    categoryParent: 0,
  })
  const isEdit = ref(true)
  const categoryParent = ref(0)

  return { category, isEdit, categoryParent }}, {
  persist: {
    storage: sessionStorage,
  }
})
