<template>
  <el-card style="overflow: visible">
    <el-row >
      <!--  头像  -->
      <el-col :span="1">
        <el-avatar :src="useUserStore().user.avatar" size="default" fit="cover"></el-avatar>
      </el-col>
      <!--  输入  -->
      <el-col :span="22" style="margin-left: 1rem">
          <!--  输入框  -->
          <el-input
            type="textarea"
            :autosize="{ minRows: 2, maxRows: 4 }"
            v-model="dynamicContent"
            placeholder="Please input"
            class="dynamic-input"
          />
          <!--  上传文件  -->
          <el-upload
            :file-list="fileList"
            action="#"
            list-type="picture-card"
            :limit="5"
            :auto-upload="false"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove"
            :on-change="handleChange"
            class="dynamic-upload"
            :class="{'displayNone': displayNone}"
          >
            <el-icon><Plus /></el-icon>
          </el-upload>
          <!--  分割线  -->
          <el-divider style="margin: 0.5rem 0"/>
          <!--  配置栏 -->
          <el-row>
            <!--  表情  -->
            <el-col :span="1">
              <V3Emoji
                @click-emoji="appendText"
                :recent="true"
                fix-pos="auto"
              />
            </el-col >
            <!--  上传图片  -->
            <el-col :span="1">
              <a class="icon element-icons" :class="{'el-icon-tupian1': displayNone, 'el-icon-tupian2': !displayNone}" @click="displayNone = !displayNone"></a>
            </el-col>
            <!--  提交  -->
            <el-col :span="1" :offset="19">
              <el-button @click="submit">发布动态</el-button>
            </el-col>
          </el-row>

      </el-col>
    </el-row>
  </el-card>
  <el-dialog v-model="dialogVisible">
    <img w-full :src="dialogImageUrl" alt="Preview Image" />
  </el-dialog>

</template>

<script setup lang="ts">
import V3Emoji from 'vue3-emoji'
import 'vue3-emoji/dist/style.css'
import { useUserStore } from '@/stores/user'
import { ref } from 'vue'
import { ElMessage, type UploadProps, type UploadUserFile } from 'element-plus'
import { addDynamic } from '@/api/dynamic'
import { putObject } from '@/tool/obs'
import type { Dynamic } from '@/entity/dynamic'

const props = defineProps(['categoryId'])
const emits = defineEmits(['rerender'])
// 文本框
const dynamicContent = ref('')

// 添加表情
const appendText = (emoji: any) => {
  dynamicContent.value += emoji
}

// 上传图片
const displayNone = ref(true)
const fileList = ref<UploadUserFile[]>([])
const dialogImageUrl = ref('')
const dialogVisible = ref(false)
const handleChange: UploadProps['onChange'] = (uploadFile, uploadFiles) => {
  fileList.value.push(uploadFile)
}
const handleRemove: UploadProps['onRemove'] = (uploadFile, uploadFiles) => {
  fileList.value.pop()
}
const handlePictureCardPreview: UploadProps['onPreview'] = (uploadFile) => {
  dialogImageUrl.value = uploadFile.url!
  dialogVisible.value = true
}

// 发表
const submit = () => {
  console.log(fileList.value)
  const dynamic = ref<Dynamic>({
    dynamicContent: dynamicContent.value,
    dynamicImages: [],
    userId: useUserStore().user.userId,
    categoryId: props.categoryId,
  })

  // 上传图片
  fileList.value.forEach((file) => {
    const fileName = "temp/" + file.name
    // 上传对象
    putObject(fileName, file.raw)
    dynamic.value.dynamicImages.push(fileName)
  })

  // 上传动态
  addDynamic(dynamic.value).then((res) => {
    if (res.code == 200) {
      ElMessage.success("发表成功！")
      // 清空输入框
      dynamicContent.value = ''
      // 清空图片列表
      fileList.value = []
      displayNone.value = true
      // 重新渲染
      emits('rerender')
    }
  })
}

</script>

<style scoped>
.dynamic-input {
  margin-bottom: 5px;
}
.icon {
  cursor: pointer;
  font-size: 28px;
}
</style>
