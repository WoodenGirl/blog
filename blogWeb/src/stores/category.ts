import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useCategoryStore = defineStore('category', () => {
  const categoryId = ref(1)
  const isEdit = ref(true)
  const categoryParent = ref(0)

  return { categoryId, isEdit, categoryParent }}, {
  persist: {
    storage: sessionStorage,
  }
})
