<template>
  <div>
    <!-- 境外课程管理页面内容 -->
    <div>
      <!-- 添加境外课程按钮 -->
      <el-row :gutter="20" class="mb-20" style="margin-bottom: 10px; margin-left: 10px; margin-top: 10px">
        <el-col>
          <el-button type="primary"
                     plain
                     icon="el-icon-plus"
                     size="medium"
                     @click="handleAddCourse"
                     v-hasPermi="['oversea:course:add']">新增境外课程
          </el-button>
        </el-col>
      </el-row>

      <!-- 境外课程列表 -->
      <el-table :data="courseList" v-loading="loading" style="width: 100%" border>
        <el-table-column label="课程ID" prop="courseId" align="center"></el-table-column>
        <el-table-column label="课程名称" prop="courseName" align="center"></el-table-column>
        <el-table-column label="所属学校" prop="universityName" align="center"></el-table-column>
        <el-table-column label="学分" prop="credit" align="center"></el-table-column>
        <el-table-column label="操作" align="center" width="200">
          <template slot-scope="scope">
            <el-button type="text" icon="el-icon-edit" size="mini" @click="handleEdit(scope.row)"
                       v-hasPermi="['oversea:course:edit']">修改
            </el-button>
            <el-button type="text" icon="el-icon-delete" size="mini" @click="handleDelete(scope.row)"
                       v-hasPermi="['oversea:course:delete']">删除
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
      @pagination="fetchCourses"
    />

    <!-- 添加/编辑境外课程对话框 -->
    <el-dialog :visible.sync="dialogVisible" :title="dialogTitle" width="40%" @close="handleCloseDialog">
      <div>
        <el-form :model="courseForm" label-width="100px" :rules="rules" ref="courseForm">
          <el-form-item label="课程名称" prop="courseName">
            <el-input v-model="courseForm.courseName"></el-input>
          </el-form-item>
          <el-form-item label="所属学校" prop="universityId">
            <el-select v-model="courseForm.universityId" placeholder="请选择所属学校" style="width: 100%">
              <el-option
                v-for="university in universityList"
                :key="university.universityId"
                :label="university.universityName"
                :value="university.universityId">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="学分" prop="credit">
            <el-input-number v-model="courseForm.credit" :precision="1" :step="0.5" :min="0"></el-input-number>
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
import { listAllCourses, addCourse, updateCourse, deleteCourse } from '@/api/oversea/oeCourse'
import { listAllUniversities } from '@/api/oversea/university'

export default {
  data() {
    return {
      loading: false,
      courseList: [],
      universityList: [],
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
      courseForm: {
        courseId: undefined,
        courseName: '',
        universityId: undefined,
        credit: undefined
      },
      // 表单验证规则
      rules: {
        courseName: [
          { required: true, message: '请输入课程名称', trigger: 'blur' }
        ],
        universityId: [
          { required: true, message: '请选择所属学校', trigger: 'change' }
        ],
        credit: [
          { required: true, message: '请输入学分', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.fetchCourses()
    this.fetchUniversities()
  },
  methods: {
    // 获取课程列表
    fetchCourses() {
      this.loading = true
      listAllCourses(this.queryParams).then(response => {
        this.courseList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 获取学校列表
    fetchUniversities() {
      listAllUniversities().then(response => {
        this.universityList = response.rows
      })
    },
    // 添加课程
    handleAddCourse() {
      this.dialogTitle = '新增境外课程'
      this.dialogButtonText = '添加'
      this.courseForm = {
        courseId: undefined,
        courseName: '',
        universityId: undefined,
        credit: undefined
      }
      this.dialogVisible = true
    },
    // 修改课程
    handleEdit(row) {
      this.dialogTitle = '修改境外课程'
      this.dialogButtonText = '修改'
      this.courseForm = Object.assign({}, row)
      this.dialogVisible = true
    },
    // 删除课程
    handleDelete(row) {
      this.$confirm('确认删除该课程吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteCourse(row.courseId).then(() => {
          this.$message.success('删除成功')
          this.fetchCourses()
        })
      })
    },
    // 关闭对话框
    handleCloseDialog() {
      this.dialogVisible = false
      if (this.$refs.courseForm) {
        this.$refs.courseForm.resetFields()
      }
    },
    // 提交表单
    handleSubmit() {
      this.$refs.courseForm.validate(valid => {
        if (valid) {
          if (this.dialogButtonText === '添加') {
            addCourse(this.courseForm).then(() => {
              this.$message.success('添加成功')
              this.dialogVisible = false
              this.fetchCourses()
            })
          } else if (this.dialogButtonText === '修改') {
            updateCourse(this.courseForm).then(() => {
              this.$message.success('修改成功')
              this.dialogVisible = false
              this.fetchCourses()
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
