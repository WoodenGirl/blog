import { defineStore } from 'pinia'
import { ref } from 'vue'

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
