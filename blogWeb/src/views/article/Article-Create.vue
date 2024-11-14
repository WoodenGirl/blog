<template>
  <el-form
    ref="articleFormRef"
    style="max-width: 1200px"
    :model="articleForm"
    :rules="rules"
    label-width="auto"
    label-position="left"
    :size="formSize"
    status-icon
  >
    <!--  标题  -->
    <el-form-item label="Article Title" prop="title">
      <el-input v-model="articleForm.articleTitle" placeholder="input article title here" />
    </el-form-item>
    <!--  封面  -->
    <el-form-item label="Article Cover" prop="cover">
      <el-upload
        :class="hideUpload ? 'hide' : ''"
        :file-list="fileList"
        action="#"
        list-type="picture-card"
        :auto-upload="false"
        :on-preview="handlePictureCardPreview"
        :on-remove="handlePictureCardRemove"
        :on-change="handlePictureCardChange"
      >
        <el-icon><Plus /></el-icon>
      </el-upload>
      <el-dialog v-model="dialogVisible">
        <img
          w-full
          :src="dialogImageUrl"
          alt="Preview Image"
          style="max-height: 100%; max-width: 100%"
        />
      </el-dialog>
    </el-form-item>
    <!--  类别  -->
    <el-form-item label="Article Category" prop="category">
      <el-cascader
        v-model="articleForm.categoryId"
        :options="categories"
        :props="props"
        @change="handleCascadeChange"
      />
    </el-form-item>
    <!--  标签  -->
    <el-form-item label="Article Tags" prop="tags">
      <div class="flex gap-2">
        <el-tag
          v-for="tag in articleTags"
          :key="tag"
          closable
          size="large"
          :disable-transitions="false"
          @close="handleClose(tag)"
        >
          {{ tag }}
        </el-tag>
        <el-input
          v-if="inputVisible"
          ref="InputRef"
          v-model="inputValue"
          class="w-20"
          size="default"
          @keyup.enter="handleInputConfirm"
          @blur="handleInputConfirm"
        />
        <el-button v-else class="button-new-tag" size="default" @click="showInput">
          + New Tag
        </el-button>
      </div>
    </el-form-item>
    <!--  正文  -->
    <el-form-item label="Article Content" prop="content">
      <wang-editor @getValue="getValue"></wang-editor>
    </el-form-item>
    <!--  提交取消  -->
    <el-form-item label=" ">
      <el-button type="primary" @click="submitForm(articleFormRef)">Create</el-button>
      <el-button @click="saveForm(articleFormRef)">Save</el-button>
    </el-form-item>
  </el-form>
</template>

<script lang="ts" setup>
import { nextTick, reactive, ref } from 'vue'
import { Plus } from '@element-plus/icons-vue'
import {
  type ComponentSize,
  type ElInput, ElMessage,
  type FormInstance,
  type FormRules,
  type UploadProps,
  type UploadUserFile
} from 'element-plus'
import { queryCategory } from '@/api/category'
import { addArticle } from '@/api/article'
import router from '@/router'
import WangEditor from '@/components/tool/Wang-Editor.vue'

// 图片
const fileList = ref<UploadUserFile[]>([])
// 控制多图片上传
const hideUpload = ref(false)
// 删除图片
const handlePictureCardRemove: UploadProps['onRemove'] = () => {
  hideUpload.value = false
  fileList.value.pop()
}
// 选取图片
const handlePictureCardChange: UploadProps['onChange'] = (uploadFile) => {
  hideUpload.value = true
  fileList.value.push(uploadFile)
}
// 预览图片
const dialogImageUrl = ref('')
const dialogVisible = ref(false)
const handlePictureCardPreview: UploadProps['onPreview'] = uploadFile => {
  dialogImageUrl.value = uploadFile.url!
  dialogVisible.value = true
}

// 类别
const categories = ref()
const fetchTreeData = async () => {
  try {
    categories.value = await queryCategory().then(res => res.data);
  } catch (error) {
    console.error('Error fetching tree data:', error);
  }
};
fetchTreeData()
const props = {
  expandTrigger: 'hover' as const,
  checkStrictly: true,
  value: 'id',
  label: 'label',
  children: 'children',
}
const handleCascadeChange = (value: string) => {
  articleForm.categoryId = value[value.length - 1]
  console.log("categoryid: " + articleForm.categoryId)
}

// 标签
const inputValue = ref('')
const inputVisible = ref(false)
const InputRef = ref<InstanceType<typeof ElInput>>()

const articleTags = ref(["tag 1", "tag 2"])
const handleClose = (tag: string) => {
  articleTags.value.splice(articleTags.value.indexOf(tag), 1)
}
const showInput = () => {
  inputVisible.value = true
  nextTick(() => {
    InputRef.value!.input!.focus()
  })
}
const handleInputConfirm = () => {
  if (inputValue.value) {
    articleTags.value.push(inputValue.value)
  }
  inputVisible.value = false
  inputValue.value = ''
}

// 编辑器
const getValue = (html: any) => {
  articleForm.articleContent = html
}

// 表单
const formData = new FormData()

interface ArticleForm {
  articleTitle: string
  userId: number
  categoryId: string
  articleTags: string
  articleContent: string
}
const formSize = ref<ComponentSize>('default')
const articleFormRef = ref<FormInstance>()
const articleForm = reactive<ArticleForm>({
  articleTitle: '',
  userId: 1,
  categoryId: '',
  articleTags: '',
  articleContent: '',
})
const rules = reactive<FormRules<ArticleForm>>({})

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      // 图片
      formData.append('articleCover', fileList.value[0].raw)
      // 其他数据
      articleForm.articleTags = articleTags.value.toString()
      formData.append('articleJson', JSON.stringify(articleForm))
      console.log("articleJson:" + formData.get('articleJson'))
      // 添加
      addArticle(formData).then(res => {
        ElMessage({
          message: '创建成功！',
          type: 'success',
        })
        // 跳转到首页
        router.push('/')
      })
    } else {
      console.log('error submit!', fields)
    }
  })
}
const saveForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  //formEl.resetFields()
}
</script>

<style scoped>
.hide :deep(.el-upload--picture-card) {
  display: none;
}
</style>
