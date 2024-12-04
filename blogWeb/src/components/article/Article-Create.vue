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
      <wang-editor ref="wangEditorRef"></wang-editor>
    </el-form-item>
    <!--  提交取消  -->
    <el-form-item label=" ">
      <el-button type="primary" @click="submitForm(articleFormRef)">publish</el-button>
      <el-button @click="saveForm(articleFormRef)">save</el-button>
    </el-form-item>
  </el-form>
</template>

<script lang="ts" setup>
import { nextTick, onMounted, reactive, ref } from 'vue'
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
import { addArticle, updateArticle } from '@/api/article'
import router from '@/router'
import WangEditor from '@/components/tool/Wang-Editor.vue'
import { useArticlesStore } from '@/stores/article'
import { useUserStore } from '@/stores/user'
import { getNow } from '@/assets/ts/tool'
import type { ArticleForm } from '@/entity/article'
import { popObject, putObject } from '@/assets/ts/obs'


// 图片
const fileList = ref<UploadUserFile[]>([])
// 控制多图片上传
const hideUpload = ref(false)
// 删除图片
const handlePictureCardRemove: UploadProps['onRemove'] = () => {
  if (fileList.value[0].status == "success") {popObject(fileList.value[0].name)}
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
const wangEditorRef = ref<InstanceType<typeof WangEditor>>(null)

// 表单

const formSize = ref<ComponentSize>('default')
const articleFormRef = ref<FormInstance>()
// 表单数据
const articleForm = reactive<ArticleForm>({
  articleId: '',
  articleTitle: '',
  articleCover: '',
  userId: useUserStore().user.userId,
  categoryId: '',
  articleTags: '',
  articleContent: '',
  createdTime: '',
  updateTime: ''
})
// 编辑
const articlesStore = useArticlesStore()
if (articlesStore.isEdit && articlesStore.article) {
  const article = articlesStore.article
  articleForm.articleId = article.articleId
  articleForm.createdTime = article.createdTime
  articleForm.articleTitle = article.articleTitle
  articleForm.categoryId = article.categoryId
  articleTags.value = article.articleTags.split(',')
  // 显示文章
  onMounted(() => {
    setTimeout(() => {
      wangEditorRef.value.valueHtml = article.articleContent
    }, 1500)
  })
  // 获取文章所有图片, 方便之后比较删除图片
  onMounted(() => {
    setTimeout(() => {
      wangEditorRef.value.imageList1 = wangEditorRef.value.editorRef.getElemsByType('image')
    }, 1500)
  })

  // 显示封面
  articleForm.articleCover = article.articleCover
  hideUpload.value = true
  fileList.value.push({name: article.articleCover, url: "/images/" + article.articleCover})
}

const rules = reactive<FormRules<ArticleForm>>({})

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      // 处理图片封面
      const file = fileList.value[0]
      if (file.status != "success") { // 若图片不在服务器上，上传
        // 生成文件名称
        const suffix = file.name.split('.').pop()
        const fileName = "articleCover/" + file.uid + "." + suffix
        // 上传对象
        putObject(fileName, file.raw)
        // 赋值
        articleForm.articleCover = fileName
      }
      // 其他数据赋值
      articleForm.articleTags = articleTags.value.toString()
      articleForm.articleContent = wangEditorRef.value.valueHtml
      // 删除文章未使用图片
      wangEditorRef.value.handleSave()

      if (articlesStore.isEdit) { // 修改
        // 更新修改时间
        articleForm.updateTime = getNow()
        updateArticle(articleForm).then(res => {
          if (res.code == 200) {
            ElMessage.success("修改成功！")
          }
        })
      } else { // 添加
        // 记录时间
        articleForm.createdTime = getNow()
        articleForm.updateTime = getNow()
        addArticle(articleForm).then(res => {
          if (res.code == 200) {
            ElMessage.success("创建成功！")
          }
        })
      }
      // 跳转到首页
      useArticlesStore().fetchArticles()
      router.push('/');
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
