import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useCategoryStore = defineStore('category', () => {
  const category = ref<Tree>({
    id: 1,
    label: 'Aprilsxz',
    children: []
  })
  const isEdit = ref(true)
  const categoryParent = ref(0)

  return { category, isEdit, categoryParent }}, {
  persist: {
    storage: sessionStorage,
  }
})
