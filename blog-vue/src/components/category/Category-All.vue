<template>
  <el-tree
    style="max-width: 600px"
    :data="categoryTrees"
    :props="defaultProps"
    node-key="categoryId"
    :highlight-current="true"
    :current-node-key="currentId"
    @node-click="clickCategory"

  >
    <template #default="{ node, data }">
        <span class="custom-tree-node" >
          <span>{{ node.label }}</span>
          <span v-show="isEdit">
            <a @click="append(data)" > <Plus class="edit-icon"></Plus> </a>
            <a @click="remove(data)"> <Delete class="edit-icon"></Delete> </a>
            <a @click="update(data)"> <Edit class="edit-icon"></Edit> </a>
          </span>
        </span>
    </template>
  </el-tree>
  <Plus @click="append()" class="add-icon" ></Plus>

  <el-dialog v-model="dialogFormVisible" :title="dialogTitle" width="500">
    <el-form :model="category">
      <el-form-item label="目录名称" >
        <el-input v-model="category.categoryName" autocomplete="off" />
      </el-form-item>
      <el-form-item label="是否中断" >
        <el-switch v-model="category.isInterrupt"
                   :active-value="1"
                   :inactive-value="0"/>
      </el-form-item>

    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button type="primary" @click="confirmDialog">确定</el-button>
        <el-button @click="cancelDialog">取消</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { addCategory, deleteCategory, queryCategory, updateCategory } from '@/api/category'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Delete, Edit, Plus } from '@element-plus/icons-vue'
import { useCategoryStore } from '@/stores/category'
import type { Category, CategoryTree } from '@/entity/category'
import router from '@/router'
import { routes } from 'vue-router/auto-routes'


const props = defineProps(['isEdit', 'categoryParent'])


// 目录树
const defaultProps = {
  children: 'categoryChildren',
  label: 'categoryName',
}
const categoryTrees = ref<CategoryTree[]>([])
const currentId = ref<number>()
// 获取目录树数据
const fetchTreeData = async () => {
  categoryTrees.value = await queryCategory(props.categoryParent).then(res => res.data);
  if (useCategoryStore().category.categoryId < 6) {
    // 默认选中第一个节点
    currentId.value = categoryTrees.value[0].categoryId
    clickCategory(categoryTrees.value[0])
  } else {
    currentId.value = useCategoryStore().category.categoryId
  }
};
fetchTreeData()
// 点击目录，将数据去全局保存
const clickCategory = (data: CategoryTree) => {
  useCategoryStore().category = data
}

// 添加修改
// 对话框
const dialogFormVisible = ref(false)
const dialogTitle = ref('添加目录')
const isAdd = ref(true)
const category = ref<Category>({
  categoryId: 0,
  categoryName: '',
  isInterrupt: 0,
  categoryParent: 0
})
// 添加tree节点
const append = (data?: CategoryTree) => {
  isAdd.value = true
  dialogTitle.value=  '添加目录'
  dialogFormVisible.value = true
  category.value.categoryParent = data ? data.categoryId : props.categoryParent
}
// 修改tree节点
const update = (data: CategoryTree) => {
  isAdd.value = false
  dialogTitle.value=  '修改目录'
  dialogFormVisible.value = true
  category.value.categoryId = data.categoryId
  category.value.categoryParent = null
  category.value.categoryName = data.categoryName
  category.value.isInterrupt = data.isInterrupt
  console.log(category.value.isInterrupt)
}
// 取消添加修改
const cancelDialog = () => {
  dialogFormVisible.value = false
  // 清空对话框
  category.value.categoryName = ''
  category.value.isInterrupt = 0
}
const confirmDialog = () => {
  if (isAdd.value == true) {
    addCategory(category.value).then(res => {
      if (res.code == 200) {
        ElMessage.success("添加目录成功！")
      }
    });
  } else {
    updateCategory(category.value).then(res => {
      if (res.code == 200) {
        ElMessage.success("修改目录成功！")
      }
    })
  }
  cancelDialog()
  setTimeout(() => {
    fetchTreeData()
  }, 500)
}

// 删除tree节点
const remove = (data: CategoryTree) => {
  ElMessageBox.confirm(
    'Are you sure to delete it?',
    'Warning',
    {
      confirmButtonText: 'OK',
      cancelButtonText: 'Cancel',
      type: 'warning',
    }
  )
    .then(() => {
     deleteCategory(data.categoryId).then(res => {
       if (res.code === 200) {
         ElMessage.success("删除成功！")
       }
     })
    })
    .catch(() => {
      ElMessage.info('Delete canceled')
    })
}
</script>

<style scoped>
.el-tree{
  background-color: #ffffff;
  --el-tree-node-content-height: 50px;
  --el-tree-node-hover-bg-color: #ECF5FF;
  margin-right: 2rem;
}
.custom-tree-node{
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 18px;
  padding-right: 8px;
}

.edit-icon{
  width: 18px;
  height: 18px;
  margin: 0 5px;
}

.add-icon {
  width: 100%;
  height: 45px;
  color: white;
  padding: 10px;
}
.add-icon:hover {
  color: #c8daed;
  background-color: #F5F7FA;
  cursor: pointer;
}
</style>
