<template>
  <div>
    <!-- 培养方案管理页面内容 -->
    <div>
      <!-- 添加培养方案按钮 -->
      <el-row :gutter="20" class="mb-20" style="margin-bottom: 10px; margin-left: 10px; margin-top: 10px">
        <el-col>
          <el-button type="primary"
                     plain
                     icon="el-icon-plus"
                     size="medium"
                     @click="handleAddProgram"
                     v-hasPermi="['oversea:trainProgram:add']">新增培养方案
          </el-button>
        </el-col>
      </el-row>

      <!-- 培养方案列表 -->
      <el-table :data="programList" v-loading="loading" style="width: 100%" border>
        <el-table-column label="方案ID" prop="id" align="center"></el-table-column>
        <el-table-column label="专业名称" prop="majorName" align="center"></el-table-column>
        <el-table-column label="课程名称" prop="courseName" align="center"></el-table-column>
        <el-table-column label="课程学分" prop="credit" align="center"></el-table-column>
        <el-table-column label="课程属性" prop="property" align="center">
          <template slot-scope="scope">
            {{ scope.row.property === 0 ? '必修' : '选修' }}
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="200">
          <template slot-scope="scope">
            <el-button type="text" icon="el-icon-edit" size="mini" @click="handleEdit(scope.row)"
                       v-hasPermi="['oversea:trainProgram:edit']">修改
            </el-button>
            <el-button type="text" icon="el-icon-delete" size="mini" @click="handleDelete(scope.row)"
                       v-hasPermi="['oversea:trainProgram:delete']">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 分页组件 -->
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="fetchPrograms"
    />

    <!-- 添加/编辑培养方案对话框 -->
    <el-dialog :visible.sync="dialogVisible" :title="dialogTitle" width="40%" @close="handleCloseDialog">
      <div>
        <el-form :model="programForm" label-width="100px" :rules="rules" ref="programForm">
          <el-form-item label="专业名称" prop="majorId">
            <el-select v-model="programForm.majorId" placeholder="请选择专业" style="width: 100%">
              <el-option
                v-for="major in majorList"
                :key="major.majorId"
                :label="major.majorName"
                :value="major.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="课程名称" prop="courseName">
            <el-input v-model="programForm.courseName" placeholder="请输入课程名称"></el-input>
          </el-form-item>
          <el-form-item label="课程学分" prop="credit">
            <el-input-number v-model="programForm.credit" :precision="1" :step="0.5" :min="0" style="width: 100%"></el-input-number>
          </el-form-item>
          <el-form-item label="课程属性" prop="property">
            <el-select v-model="programForm.property" placeholder="请选择课程属性" style="width: 100%">
              <el-option label="必修" :value="0"></el-option>
              <el-option label="选修" :value="1"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer" style="text-align: center;">
        <el-button @click="handleCloseDialog">取消</el-button>
        <el-button type="primary" @click="handleSubmit">{{ dialogButtonText }}</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAllTrainPrograms, addTrainProgram, updateTrainProgram, deleteTrainProgram } from '@/api/oversea/trainProgram'
import { listAllMajors } from '@/api/oversea/oeInMajor'

export default {
  name: 'TrainProgram',
  data() {
    return {
      loading: false,
      programList: [],
      majorList: [],
      dialogVisible: false,
      dialogTitle: '',
      dialogButtonText: '',
      total: 0,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
      // 表单数据
      programForm: {
        id: undefined,
        majorId: undefined,
        courseName: '',
        credit: undefined,
        property: undefined
      },
      // 表单验证规则
      rules: {
        // majorId: [
        //   { required: true, message: '请选择专业', trigger: 'change' }
        // ],
        courseName: [
          { required: true, message: '请输入课程名称', trigger: 'blur' }
        ],
        credit: [
          { required: true, message: '请输入课程学分', trigger: 'blur' }
        ],
        property: [
          { required: true, message: '请选择课程属性', trigger: 'change' }
        ]
      }
    }
  },
  created() {
    this.fetchPrograms()
    this.fetchMajors()
  },
  methods: {
    // 获取培养方案列表
    fetchPrograms() {
      this.loading = true
      listAllTrainPrograms(this.queryParams).then(response => {
        this.programList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 获取专业列表
    fetchMajors() {
      listAllMajors().then(response => {
        this.majorList = response.rows
      })
    },
    // 添加培养方案
    handleAddProgram() {
      this.dialogTitle = '新增培养方案'
      this.dialogButtonText = '添加'
      this.programForm = {
        id: undefined,
        majorId: undefined,
        courseName: '',
        credit: undefined,
        property: undefined
      }
      this.dialogVisible = true
    },
    // 修改培养方案
    handleEdit(row) {
      this.dialogTitle = '修改培养方案'
      this.dialogButtonText = '修改'
      this.programForm = {
        id: row.id,
        majorId: row.majorId,
        courseName: row.courseName,
        credit: row.credit,
        property: row.property
      }
      this.dialogVisible = true
    },
    // 删除培养方案
    handleDelete(row) {
      this.$confirm('确认删除该培养方案吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteTrainProgram(row.id).then(() => {
          this.$message.success('删除成功')
          this.fetchPrograms()
        })
      })
    },
    // 关闭对话框
    handleCloseDialog() {
      this.dialogVisible = false
      if (this.$refs.programForm) {
        this.$refs.programForm.resetFields()
      }
    },
    // 提交表单
    handleSubmit() {
      this.$refs.programForm.validate(valid => {
        if (valid) {
          if (this.dialogButtonText === '添加') {
            addTrainProgram(this.programForm).then(() => {
              this.$message.success('添加成功')
              this.dialogVisible = false
              this.fetchPrograms()
            })
          } else if (this.dialogButtonText === '修改') {
            updateTrainProgram(this.programForm).then(() => {
              this.$message.success('修改成功')
              this.dialogVisible = false
              this.fetchPrograms()
            })
          }
        }
      })
    }
  }
}
</script>

<style scoped>
/* 样式可以根据需求调整 */
</style>
