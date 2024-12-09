<template>
  <el-form
    :model="acgForm"
    label-width="auto"
    label-position="left"
    size="default"
  >
    <el-form-item label="类型:" v-if="types">
      <el-radio-group v-model="acgForm.acgType" v-for="type in types" :key="type">
        <el-radio :label="type" :value="type" class="radio"/>
      </el-radio-group>
    </el-form-item>

    <el-form-item label="风格:">
      <el-radio-group v-model="acgForm.acgGenre" v-for="genre in genres" :key="genre">
        <el-radio :label="genre" :value="genre" class="radio"/>
      </el-radio-group>
    </el-form-item>

    <el-form-item label="地区:" v-if="category!.label != '小说'">
      <el-radio-group v-model="acgForm.acgRegion" v-for="region in regions" :key="region">
        <el-radio :label="region" :value="region" class="radio"/>
      </el-radio-group>
    </el-form-item>

    <el-form-item label="状态:" v-if="category!.label != '游戏'">
      <el-radio-group v-model="acgForm.acgState" v-for="state in states" :key="state">
        <el-radio :label="state.label" :value="state.value" class="radio"/>
      </el-radio-group>
    </el-form-item>

    <el-form-item label="年份:" v-if="category!.label != '小说'">
      <el-radio-group v-model="acgForm.acgYear" v-for="region in years" :key="region">
        <el-radio :label="region" :value="region" class="radio"/>
      </el-radio-group>
    </el-form-item>

  </el-form>
</template>

<script setup lang="ts">
import { reactive, ref, watch } from 'vue'
import { storeToRefs } from 'pinia'
import { useCategoryStore } from '@/stores/category'
import { acgGenre, acgRegions, acgStates, acgTypes, acgYears, novelGenre } from '@/entity/acg'

const {category} = storeToRefs(useCategoryStore())

const acgForm = reactive({
  acgType: '',
  acgGenre: '',
  acgRegion: '',
  acgState: '',
  acgYear: ''
})

const clearForm = () => {
  acgForm.acgType = ''
  acgForm.acgGenre = ''
  acgForm.acgRegion = ''
  acgForm.acgState = ''
  acgForm.acgYear = ''
}

const types = ref()
const genres = ref()
watch(() => category.value, (newValue) => {
  types.value = acgTypes(newValue!.label)
  genres.value = acgGenre(newValue!.label)
  clearForm()
})
watch(() => acgForm.acgType, (newValue) => {
  if (category.value!.label === '小说') {
    genres.value = novelGenre(newValue)
  }
})
const regions = ref()
regions.value = acgRegions

const years = ref()
years.value = acgYears

const states = ref()
states.value = acgStates

</script>

<style scoped>
/deep/ .el-radio__input {
 display: none;
}
</style>
