<template>
  <el-form
    :model="acgForm"
    label-width="auto"
    style="max-width: 600px"
    label-position="left"
    size="default"
  >
    <el-form-item label="类型:">
      <el-radio-group v-model="acgForm.acgType" v-for="type in types" :key="type">
        <el-radio :label="type" :value="type" class="radio"/>
      </el-radio-group>
    </el-form-item>

    <el-form-item label="风格:">
      <el-radio-group v-model="acgForm.acgType" v-for="type in types" :key="type">
        <el-radio :label="type" :value="type" class="radio"/>
      </el-radio-group>
    </el-form-item>


  </el-form>
</template>

<script setup lang="ts">
import { reactive, ref, watch } from 'vue'
import { storeToRefs } from 'pinia'
import { useCategoryStore } from '@/stores/category'
import {acgTypes} from '@/entity/acg'
import type { ComponentSize } from 'element-plus'

const {category} = storeToRefs(useCategoryStore())

const acgForm = reactive({
  acgType: '',
})

const types = ref()
watch(() => category.value, (newValue) => {
  types.value = acgTypes(newValue!.label)
})

</script>

<style scoped>
/deep/ .el-radio__input {
 display: none;
}
</style>
