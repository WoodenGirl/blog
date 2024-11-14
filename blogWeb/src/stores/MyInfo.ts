import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { User } from '@/assets/ts/interface'

export const useMyInfoStore = defineStore('myInfo', () => {
  const myInfo = ref<User>()
  return { myInfo }
})
