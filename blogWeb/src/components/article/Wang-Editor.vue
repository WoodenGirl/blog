<template>
  <div style="border: 1px solid #ccc">
    <Toolbar
      style="border-bottom: 1px solid #ccc"
      :editor="editorRef"
      :defaultConfig="toolbarConfig"
      :mode="mode"
    />
    <Editor
      style="height: 500px; overflow-y: hidden"
      v-model="valueHtml"
      :defaultConfig="editorConfig"
      :mode="mode"
      @onCreated="handleCreated"
    />
  </div>
</template>

<script lang="ts" setup>
import '@wangeditor-next/editor/dist/css/style.css' // 引入 css

import { onBeforeUnmount, ref, shallowRef } from 'vue'
import { Editor, Toolbar } from '@wangeditor-next/editor-for-vue'
import { popObject, putObject } from '@/assets/ts/obs'
import { findDifferentAttributes, generateUID } from '@/assets/ts/tool'
import type { ImageElement } from '../../../custom'
import type { IEditorConfig } from '@wangeditor-next/editor'

// 编辑器实例，必须用 shallowRef
const editorRef = shallowRef()

// 内容 HTML
const valueHtml = ref('')

// 模式 mode
const mode = "default"

const toolbarConfig = {}
const editorConfig: Partial<IEditorConfig> = {
  MENU_CONF: {}
}

// 修改 uploadImage 菜单配置
type InsertFnType = (url: string, alt: string, href: string) => void
editorConfig.MENU_CONF['uploadImage'] = {
  // 上传
  async customUpload(file: File, insertFn: InsertFnType) {  // TS 语法
    // 生成文件名
    const suffix = file.name.split('.').pop()
    const fileName = "articleInsert/" + generateUID() + "." + suffix
    // 上传对象
    putObject(fileName, file)
    // 显示图像
    const href = import.meta.env.VITE_OBS_URL + fileName
    insertFn(href, file.name, href)
  },
}
// 插入图片后的回调函数
const imageList1 = ref<ImageElement[]>([])
editorConfig.MENU_CONF['insertImage'] = {
  onInsertedImage(imageNode: ImageElement | null) {  // TS 语法
    if (imageNode == null) return
    imageList1.value.push(imageNode)
  },
}
// 删除图像
const imageList2 = ref<ImageElement[]>([])
const handleSave = () => {
  imageList2.value = editorRef.value.getElemsByType('image')
  const deleteImages = findDifferentAttributes(imageList1.value, imageList2.value)
  deleteImages.forEach((item) => {popObject(item)})
}

// 组件销毁时，也及时销毁编辑器
onBeforeUnmount(() => {
  const editor = editorRef.value
  if (editor == null) return
  editor.destroy()
})

const handleCreated = (editor) => {
  editorRef.value = editor // 记录 editor 实例，重要！
}

defineExpose({
  editorRef,
  mode,
  valueHtml,
  handleSave,
  imageList1
})

</script>

<style scoped>
</style>
