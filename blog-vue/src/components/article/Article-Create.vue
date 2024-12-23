<template>
  <div class="container">
    <el-form
      ref="articleFormRef"
      style="max-width: 1200px"
      :model="articleForm"
      :rules="rules"
      label-width="auto"
      label-position="left"
      size="default"
      status-icon
    >
      <!--  标题  -->
      <el-form-item label="文章标题" prop="articleTitle">
        <el-input
          v-model="articleForm.articleTitle"
          placeholder=""
        />
      </el-form-item>
      <!--  封面  -->
      <el-form-item label="文章封面" prop="articleCover">
        <el-upload
          :class="hideUpload ? 'hide' : ''"
          :file-list="fileList"
          accept="image/*"
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
      <el-form-item label="文章类别" prop="categoryId">
        <el-cascader
          v-model="articleForm.categoryId"
          :options="categories"
          :props="props"
          @change="handleCascadeChange"
        />
      </el-form-item>
      <!--  标签  -->
      <el-form-item label="文章标签" prop="articleTags">
        <div class="flex gap-2">
          <el-tag
            v-for="tag in articleForm.articleTags"
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
          <el-button
            v-else
            class="button-new-tag"
            size="default"
            @click="showInput"
          >
            + New Tag
          </el-button>
        </div>
      </el-form-item>
      <!--  正文  -->
      <el-form-item label="文章正文" prop="articleContent">
        <wang-editor ref="wangEditorRef" @focusout="articleFormRef!.validateField('articleContent')"></wang-editor>
      </el-form-item>
      <!--  提交取消  -->
      <el-form-item label=" ">
        <el-button type="primary" @click="submitForm(articleFormRef)">
          publish
        </el-button>
        <el-button @click="saveForm(articleFormRef)">save</el-button>
      </el-form-item>
    </el-form>
  </div>
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
import { addArticle, queryDetailArticle, updateArticle } from '@/api/article'
import router from '@/router'
import WangEditor from '@/components/article/Wang-Editor.vue'
import { useUserStore } from '@/stores/user'
import type { Article, ArticleDetail } from '@/entity/article'
import { queryCategory } from '@/api/category'
import { putObject } from '@/tool/obs'
import { routes } from 'vue-router/auto-routes'
import { useRoute } from 'vue-router'

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
  articleFormRef.value!.validateField('articleCover')
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
    categories.value = await queryCategory(0).then(res => res.data);
  } catch (error) {
    console.error('Error fetching tree data:', error);
  }
};
fetchTreeData()
const props = {
  expandTrigger: 'hover' as const,
  checkStrictly: true,
  value: 'categoryId',
  label: 'categoryName',
  children: 'categoryChildren',
}
const handleCascadeChange = (value: string) => {
  articleForm.categoryId = value[value.length - 1]
}

// 标签
const inputValue = ref('')
const inputVisible = ref(false)
const InputRef = ref<InstanceType<typeof ElInput>>()

const handleClose = (tag: string) => {
  articleForm.articleTags.splice(articleForm.articleTags.indexOf(tag), 1)
}
const showInput = () => {
  inputVisible.value = true
  nextTick(() => {
    InputRef.value!.input!.focus()
  })
}
const handleInputConfirm = () => {
  if (inputValue.value) {
    articleForm.articleTags.push(inputValue.value)
  }
  inputVisible.value = false
  inputValue.value = ''
}

// 编辑器
const wangEditorRef = ref<InstanceType<typeof WangEditor> | null>(null)

// 表单
const articleFormRef = ref<FormInstance>()
// 表单数据
const articleForm = reactive<Article>({
  articleId: '',
  articleTitle: '',
  articleCover: '',
  userId: useUserStore().user.userId,
  categoryId: '',
  articleTags: [],
  articleContent: '',
  articleStatus: 1,
})

// 校验正文
const validateContent = (rule: any, value: any, callback: any) => {
  if (wangEditorRef.value!.editorRef.getText() === '') {
    callback(new Error('文章正文不能为空！'))
  } else {
    callback()
  }
}
// 校验标签
const validateTags = (rule: any, value: any, callback: any) => {
  if (articleForm.articleTags.length < 1) {
    callback(new Error('至少填写1个标签！'))
  } else if (articleForm.articleTags.length > 3) {
    callback(new Error('最多填写3个标签！'))
  } else {
    callback()
  }
}
// 校验封面
const validateCover = (rule: any, value: any, callback: any) => {
  if (fileList.value.length === 0) {
    callback(new Error('请选择文章封面！'))
  } else {
    callback()
  }
}
// 校验
const rules = reactive<FormRules<Article>>({
  articleTitle: [
    { required: true, message: '请输入文章标题', trigger: 'blur' },
    { min: 1, max: 15, message: '字数15以下', trigger: 'blur' },
  ],
  articleCover: [
    { required: true, validator: validateCover},
  ],
  categoryId: [
    { required: true, message: '请选择文章类别', trigger: 'change',},
  ],
  articleTags: [
    { required: true, validator: validateTags, trigger: ['blur','change'] },
  ],
  articleContent: [
    { required: true, validator: validateContent},
  ],
})

// 编辑
const articleId = useRoute().query.articleId?.toString()
if (articleId) {
  const article = ref<ArticleDetail | null>(null)
  queryDetailArticle(articleId).then(res => article.value = res.data)  // 显示文章
  onMounted(() => {
    setTimeout(() => {
      wangEditorRef.value!.valueHtml = article.value!.articleContent
      // 显示封面
      articleForm.articleCover = article.value!.articleCover
      hideUpload.value = true
      fileList.value.push({name: article.value!.articleCover, url: "/images/" + article.value!.articleCover})
      // 其他数据
      articleForm.articleId = article.value!.articleId
      articleForm.articleTitle = article.value!.articleTitle
      articleForm.categoryId = article.value!.categoryId
      articleForm.articleContent = article.value!.articleContent
      articleForm.articleTags = article.value!.articleTags
    }, 1000)
  })
}

// 提交
const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      // 处理图片封面
      const file = fileList.value[0]
      if (file.status != "success") { // 若图片不在服务器上，上传
        const fileName = "temp/" + file.name
        putObject(fileName, file.raw)
        articleForm.articleCover = fileName
      }
      // 其他数据赋值
      articleForm.articleContent = wangEditorRef.value!.valueHtml

      if (articleId) { // 修改
        updateArticle(articleForm).then(res => {
          if (res.code == 200) {
            ElMessage.success("修改成功！")
          }
        })
      } else { // 添加
        addArticle(articleForm).then(res => {
          if (res.code == 200) {
            ElMessage.success("创建成功！")
          }
        })
      }
      // 返回上一页并刷新
      router.go(-1)
    }
  })
}

// 保存
const saveForm = (formEl: FormInstance | undefined) => {
  articleForm.articleStatus = 0
  submitForm(formEl)
}
</script>

<style scoped>
.hide :deep(.el-upload--picture-card) {
  display: none;
}
</style>
