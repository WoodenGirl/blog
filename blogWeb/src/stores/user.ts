import { defineStore } from 'pinia'
import { ref } from 'vue'
import logo from '@/assets/images/logo.png'
import type { User } from '@/entity/user'

export const useUserStore = defineStore('user', () => {
  const user = ref<User>({
    userId: 'user_1111111111111111',
    nickname: 'aprilsxz',
    avatar: logo,
    email: '',
    password: '',
    signature: ''
  })
  return { user }
})
