import { defineStore } from 'pinia'
import { ref } from 'vue'
import avatar from '@/assets/images/avatar.jpeg'
import type { User } from '@/entity/user'

export const useUserStore = defineStore('user', () => {
  const user = ref<User>({
    userId: 'user_1111111111111111',
    nickname: 'aprilsxz',
    avatar: avatar,
    email: '',
    password: '',
    signature: ''
  })
  return { user }}, {
  persist: {
    storage: sessionStorage,
  }
})
