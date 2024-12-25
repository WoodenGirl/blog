<template>
  <el-row>
    <!--  头像  -->
    <el-col :span="3">
      <el-avatar :src="user?.avatar"></el-avatar>
    </el-col>
    <!--  昵称  -->
    <el-col :span="9">
      <p>{{ user?.nickname }}</p>
    </el-col>
  </el-row>
  <div>{{user?.signature}}</div>
  <div><a>文章(10)</a></div>
  <div><a>动态(10)</a></div>
  <div><a>list(10)</a></div>
</template>

<script setup lang="ts">
import { queryUserById } from '@/api/user'
import { ref } from 'vue'
import type { User } from '@/entity/user'
import { useUserStore } from '@/stores/user'

const props = defineProps(['userId'])

const user = ref<User | null>(null)
user.value = useUserStore().user
/*const fetchUser = async () => {
  user.value = await queryUserById(props.userId).then((res) => res.data)
}
fetchUser()*/
</script>

<style scoped>

</style>
