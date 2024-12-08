<template>
  <el-tree
    style="max-width: 600px"
    :data="dataSource"
    node-key="id"
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
  <Plus @click="append(null)" class="add-icon" ></Plus>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { addCategory, deleteCategories, queryCategory, updateCategory } from '@/api/category'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Delete, Edit, Plus } from '@element-plus/icons-vue'
import { useCategoryStore } from '@/stores/category'


const props = defineProps(['isEdit', 'categoryParent'])

interface Tree {
  id: number
  label: string
  children?: Tree[]
}
// tree数据
const dataSource = ref<Tree[]>([])
const currentId = ref<number>()
// 获取tree数据
const fetchTreeData = async () => {
  dataSource.value = await queryCategory(props.categoryParent).then(res => res.data);
  // 默认选中第一个节点
  currentId.value = dataSource.value[0].id
  clickCategory(dataSource.value[0])
};
fetchTreeData()

const clickCategory = (data: Tree) => {
  useCategoryStore().categoryId = data.id
}

// 添加tree节点
const append = (data: any) => {
  ElMessageBox.prompt('Please input category name', 'Add category', {
    confirmButtonText: 'OK',
    cancelButtonText: 'Cancel',
  })
    .then(({ value }) => {
      const newChild = {categoryName: value, categoryParent: data ? data.id : props.categoryParent}
      addCategory(newChild).then((res) => {
        if (res.code === 200) {
          fetchTreeData()
        }
      });
    })
    .catch(() => {
      ElMessage.info('Input canceled')
    })
}
// 修改tree节点
const update = (data: Tree) => {
  ElMessageBox.prompt('Please input category name', 'Update category', {
    confirmButtonText: 'OK',
    cancelButtonText: 'Cancel',
    inputValue: data.label
  })
    .then(({ value }) => {
      const newChild = {categoryId: data.id, categoryName: value}
      updateCategory(newChild).then((res) => {
        if (res.code === 200) {
          fetchTreeData()
        }
      });
    })
    .catch(() => {
      ElMessage.info('Input canceled')
    })
}

// 删除tree节点
const remove = (data: Tree) => {
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
      const categoryIds = [data.id]
      // 若存在子目录，删除子目录
      if (data.children) {
        for (const tree of data.children) {
          categoryIds.push(tree.id)
        }
      }
      deleteCategories(categoryIds).then((res) => {
        if (res.code === 200) {
          fetchTreeData()
          ElMessage.success('Delete completed')
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
  --el-tree-node-hover-bg-color: #ECF5FF
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
