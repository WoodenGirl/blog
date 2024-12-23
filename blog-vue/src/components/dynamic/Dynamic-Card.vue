<template>
  <el-card>
    <el-row >
      <!--  头像  -->
      <el-col :span="1">
        <el-avatar size="default" fit="cover" :src="'/images/'+ dynamic.avatar"/>
      </el-col>
      <!--  昵称 创建时间 -->
      <el-col :span="22">
        <p class="nickname">{{ dynamic.nickname }}</p>
        {{ formatDate(dynamic.createTime) }}
      </el-col>
      <!--  删除 -->
      <el-col :span="1">
        <el-dropdown>
          <span class="more">...</span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item  @click="removeDynamic"><el-icon><Delete/></el-icon>删除</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-col>
    </el-row>
    <!--  内容  -->
    <div class="content">
      <p>{{ dynamic.dynamicContent }}</p>
    </div>
    <!--  图片  -->
    <div>
      <el-image
        v-for="src in dynamic.dynamicImages"
        :key="src"
        class="imgList"
        :src="'/images/' + src"
        fit="cover"
        @click="handlePicturePreview(src)"
      />
    </div>
    <el-dialog v-model="dialogVisible">
      <img
        w-full
        :src="'/images/' + dialogImageUrl"
        alt="Preview Image"
        style="max-height: 100%; max-width: 100%"
      />
    </el-dialog>
  </el-card>

</template>

<script setup lang="ts">

import { formatDate } from '@/tool/time'
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { Delete } from '@element-plus/icons-vue'
import { deleteDynamic } from '@/api/dynamic'

const props = defineProps(['dynamic'])
const emits = defineEmits(['rerender'])

// 预览图片
const dialogImageUrl = ref('')
const dialogVisible = ref(false)
const handlePicturePreview  = (url: string) => {
  dialogImageUrl.value = url
  dialogVisible.value = true
}

// 删除
const removeDynamic = () => {
  deleteDynamic(props.dynamic.dynamicId).then(() => {
    ElMessage.success("删除成功")
    // 重新渲染
    emits('rerender')
  })
}
</script>

<style scoped>
.more {
  cursor: pointer;
}
.more:focus-visible {
  outline: none;
}
p {
  margin: 0.5rem 0;
}
.imgList {
  display: inline-block;
  margin-right: 3rem;
  max-height: 10rem;
  max-width: 10rem;
  margin-bottom: 1rem;
}
.imgList:hover {
  cursor: pointer;
}
.noDisplay {
  display: none;
}
</style>
