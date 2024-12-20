<template>
  <el-form
    ref="acgFormRef"
    style="width: 90%;"
    class="center"
    :model="acgForm"
    :rules="acgRules"
    label-width="auto"
    label-position="left"
    size="default"
    status-icon
  >
    <!-- acg -->
    <el-row >
      <!--  封面  -->
      <el-col :span="6">
        <el-form-item prop="cover">
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
      </el-col>
      <!--  基本信息  -->
      <el-col :span="18">
        <!--   名称     -->
        <el-form-item prop="name">
          <el-input v-model="acgForm.acgName" placeholder="作品名称" />
        </el-form-item>
        <!--   作者     -->
        <el-form-item prop="author">
          <el-input v-model="acgForm.acgAuthor" placeholder="作品作者" />
        </el-form-item>
        <!--   演员     -->
        <el-form-item prop="author">
          <el-input v-model="acgForm.acgActor" placeholder="作品主演" />
        </el-form-item>
        <!--   作品类型     -->
        <el-form-item prop="category">
          <el-cascader
            v-model="acgForm.categoryId"
            :options="categories"
            :props="props"
            placeholder="作品类型"
            @change="handleCascadeChange"
          />
        </el-form-item>
        <!--   作品标签     -->
        <el-form-item prop="tags">
              <div class="flex gap-2">
                <el-tag
                  v-for="tag in acgTags"
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
        <!--   作品年份     -->
        <el-form-item prop="year">
          <el-date-picker v-model="acgForm.acgYear" type="year" placeholder="作品年份">
          </el-date-picker>
        </el-form-item>
        <!--   作品地区     -->
        <el-form-item prop="region">
          <el-select v-model="acgForm.acgRegion" placeholder="作品地区">
            <el-option v-for="item in acgRegions" :key="item" :label="item" :value="item" />
          </el-select>
        </el-form-item>
        <!--   作品状态    -->
        <el-form-item prop="region">
          <el-select v-model="acgForm.acgState" placeholder="作品状态">
            <el-option v-for="item in acgStates" :key="item.value" :label="item.label" :value="item.value" />
          </el-select>
        </el-form-item>
        <!--   作品章节    -->
        <el-form-item prop="chapter">
          <el-input v-model="acgForm.acgChapter" placeholder="作品章节" />
        </el-form-item>
      </el-col>
    </el-row>
    <!-- 简介 -->
    <el-row>
      <el-form-item prop="introduction"  style="width: 100%">
        <el-input
          type="textarea"
          v-model="acgForm.acgIntro"
          placeholder="简介"
          :rows="3"/>
      </el-form-item>
    </el-row>
    <!-- 补充信息 -->
    <el-row>
      <el-form-item prop="addition"  style="width: 100%">
        <el-input
          type="textarea"
          v-model="acgForm.acgAddition"
          placeholder="补充信息"
          :rows="2"/>
      </el-form-item>
    </el-row>
    <!--  提交取消  -->
    <el-form-item>
      <el-button type="primary" @click="submitForm(acgFormRef)">提交</el-button>
      <el-button @click="reset(acgFormRef)">取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script lang="ts" setup>
import { nextTick, reactive, ref } from 'vue'
import { Plus } from '@element-plus/icons-vue'
import {
  type ElInput,
  type FormInstance,
  type FormRules,
  type UploadProps,
  type UploadUserFile
} from 'element-plus'
import router from '@/router'
import { type Acg, acgRegions, acgStates, acgYears } from '@/entity/acg'
import { queryCategory } from '@/api/category'
import { putObject } from '@/tool/obs'
import { useCategoryStore } from '@/stores/category'
import type { Category } from '@/entity/category'
import { valueToHtml } from '@/tool/text'

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
    categories.value = await queryCategory(useCategoryStore().category.categoryId).then(res => res.data);
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
  acgForm.categoryId = +value[value.length - 1]
}

// 标签
const inputValue = ref('')
const inputVisible = ref(false)
const InputRef = ref<InstanceType<typeof ElInput>>()

const acgTags = ref(["tag 1", "tag 2"])
const handleClose = (tag: string) => {
  acgTags.value.splice(acgTags.value.indexOf(tag), 1)
}
const showInput = () => {
  inputVisible.value = true
  nextTick(() => {
    InputRef.value!.input!.focus()
  })
}
const handleInputConfirm = () => {
  if (inputValue.value) {
    acgTags.value.push(inputValue.value)
  }
  inputVisible.value = false
  inputValue.value = ''
}

// 表单
const acgFormRef = ref<FormInstance>()
// 表单数据
const acgForm = reactive<Acg>({
  acgName: '', // 名称
  acgCover: '', // 封面
  categoryId: '', // 类型
  acgTags: '', // 标签
  acgState: '', // 状态
  acgAuthor: '', // 作者
  acgActor: '', // 演员
  acgYear: '',  // 年份
  acgRegion: '',  // 地区
  acgIntro: '', // 简介
  acgChapter: '', // 章节
  acgAddition: '' // 补充信息
})


const acgRules = reactive<FormRules<Acg>>({})

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      // 处理图片封面
      /*const file = fileList.value[0]
      const fileName = "temp/" + file.name
      putObject(fileName, file.raw)
      acgForm.acgCover = fileName*/
      // 其他数据赋值
      acgForm.acgTags = acgTags.value.toString()
      acgForm.acgIntro = valueToHtml(acgForm.acgIntro)
      // 添加

    } else {
      console.log('error submit!', fields)
    }
  })
}
const reset = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}

</script>

<style scoped>
.hide :deep(.el-upload--picture-card) {
  display: none;
}
</style>
