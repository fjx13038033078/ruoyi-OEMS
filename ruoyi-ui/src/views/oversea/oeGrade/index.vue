<template>
  <div>
    <!-- 境外成绩管理页面内容 -->
    <div>
      <!-- 添加成绩按钮 -->
      <el-row :gutter="20" class="mb-20" style="margin-bottom: 10px; margin-left: 10px; margin-top: 10px">
        <el-col>
          <el-button type="primary"
                     plain
                     icon="el-icon-plus"
                     size="medium"
                     @click="handleAddGrade"
                     v-hasPermi="['oversea:grade:add']">新增成绩
          </el-button>
        </el-col>
      </el-row>

      <!-- 成绩列表 -->
      <el-table :data="gradeList" v-loading="loading" style="width: 100%" border>
        <el-table-column label="成绩ID" prop="gradeId" align="center"></el-table-column>
        <el-table-column label="学生姓名" prop="studentName" align="center"></el-table-column>
        <el-table-column label="课程名称" prop="courseName" align="center"></el-table-column>
        <el-table-column label="成绩" prop="grade" align="center"></el-table-column>
        <el-table-column label="学期" prop="semester" align="center"></el-table-column>
        <el-table-column label="学分" prop="credit" align="center"></el-table-column>
        <el-table-column label="操作" align="center" width="200">
          <template slot-scope="scope">
            <el-button type="text" icon="el-icon-edit" size="mini" @click="handleEdit(scope.row)"
                       v-hasPermi="['oversea:grade:edit']">修改
            </el-button>
            <el-button type="text" icon="el-icon-delete" size="mini" @click="handleDelete(scope.row)"
                       v-hasPermi="['oversea:grade:delete']">删除
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
      @pagination="fetchGrades"
    />

    <!-- 添加/编辑成绩对话框 -->
    <el-dialog :visible.sync="dialogVisible" :title="dialogTitle" width="40%" @close="handleCloseDialog">
      <div>
        <el-form :model="gradeForm" label-width="100px" :rules="rules" ref="gradeForm">
          <el-form-item label="学生" prop="studentId">
            <el-select v-model="gradeForm.studentId" placeholder="请选择学生" style="width: 100%" @change="handleStudentChange">
              <el-option
                v-for="student in studentList"
                :key="student.userId"
                :label="student.nickName"
                :value="student.userId">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="课程" prop="courseId">
            <el-select v-model="gradeForm.courseId" placeholder="请选择课程" style="width: 100%" @change="handleCourseChange" clearable>
              <el-option
                v-for="course in courseList"
                :key="course.courseId"
                :label="course.courseName"
                :value="course.courseId"
                class="course-option">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="成绩" prop="grade">
            <el-input-number v-model="gradeForm.grade" :precision="1" :step="0.5" :min="0" :max="100" style="width: 100%"></el-input-number>
          </el-form-item>
          <el-form-item label="学期" prop="semester">
            <el-input v-model="gradeForm.semester" placeholder="请输入学期（如：23-24-1）"></el-input>
          </el-form-item>
          <el-form-item label="学分" prop="credit">
            <el-input-number v-model="gradeForm.credit" :precision="1" :step="0.5" :min="0" style="width: 100%"></el-input-number>
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
import { listAllGrades, addGrade, updateGrade, deleteGrade } from '@/api/oversea/grade'
import { listAllCourses } from '@/api/oversea/oeCourse'
import { listStudent } from '@/api/system/user'

export default {
  name: 'OeGrade',
  data() {
    return {
      loading: false,
      gradeList: [],
      studentList: [],
      courseList: [],
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
      gradeForm: {
        gradeId: undefined,
        studentId: undefined,
        studentName: '',
        courseId: undefined,
        courseName: '',
        grade: undefined,
        semester: '',
        credit: undefined
      },
      // 表单验证规则
      rules: {
        studentId: [
          { required: true, message: '请选择学生', trigger: 'change' }
        ],
        courseId: [
          { required: true, message: '请选择课程', trigger: 'change' }
        ],
        grade: [
          { required: true, message: '请输入成绩', trigger: 'blur' }
        ],
        semester: [
          { required: true, message: '请输入学期', trigger: 'blur' }
        ],
        credit: [
          { required: true, message: '请输入学分', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.fetchGrades()
    this.fetchStudents()
    this.fetchCourses()
  },
  methods: {
    // 获取成绩列表
    fetchGrades() {
      this.loading = true
      listAllGrades(this.queryParams).then(response => {
        this.gradeList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 获取学生列表
    fetchStudents() {
      listStudent().then(response => {
        this.studentList = response.rows
      })
    },
    // 获取课程列表
    fetchCourses() {
      listAllCourses().then(response => {
        this.courseList = response.rows || []
        console.log('课程列表:', this.courseList)
      })
    },
    // 学生选择变更
    handleStudentChange(value) {
      const student = this.studentList.find(item => item.userId === value)
      if (student) {
        this.gradeForm.studentName = student.userName
      }
    },
    // 课程选择变更
    handleCourseChange(value) {
      console.log('选中的课程ID:', value)
      if (!value) {
        this.gradeForm.courseName = ''
        this.gradeForm.credit = undefined
        return
      }
      const course = this.courseList.find(item => item.courseId === value)
      console.log('找到的课程:', course)
      if (course) {
        this.gradeForm.courseName = course.courseName
        this.gradeForm.credit = course.credit
      }
    },
    // 添加成绩
    handleAddGrade() {
      this.dialogTitle = '新增成绩'
      this.dialogButtonText = '添加'
      this.gradeForm = {
        gradeId: undefined,
        studentId: undefined,
        studentName: '',
        courseId: undefined,
        courseName: '',
        grade: undefined,
        semester: '',
        credit: undefined
      }
      this.dialogVisible = true
    },
    // 修改成绩
    handleEdit(row) {
      this.dialogTitle = '修改成绩'
      this.dialogButtonText = '修改'
      this.gradeForm = {
        gradeId: row.gradeId,
        studentId: row.studentId,
        studentName: row.studentName,
        courseId: row.courseId,
        courseName: row.courseName,
        grade: row.grade,
        semester: row.semester,
        credit: row.credit
      }
      // 确保在打开对话框之前已经获取了课程列表
      if (this.courseList.length === 0) {
        this.fetchCourses()
      }
      this.dialogVisible = true
    },
    // 删除成绩
    handleDelete(row) {
      this.$confirm('确认删除该成绩记录吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteGrade(row.gradeId).then(() => {
          this.$message.success('删除成功')
          this.fetchGrades()
        })
      })
    },
    // 关闭对话框
    handleCloseDialog() {
      this.dialogVisible = false
      if (this.$refs.gradeForm) {
        this.$refs.gradeForm.resetFields()
      }
    },
    // 提交表单
    handleSubmit() {
      this.$refs.gradeForm.validate(valid => {
        if (valid) {
          if (this.dialogButtonText === '添加') {
            addGrade(this.gradeForm).then(() => {
              this.$message.success('添加成功')
              this.dialogVisible = false
              this.fetchGrades()
            })
          } else if (this.dialogButtonText === '修改') {
            updateGrade(this.gradeForm).then(() => {
              this.$message.success('修改成功')
              this.dialogVisible = false
              this.fetchGrades()
            })
          }
        }
      })
    }
  }
}
</script>

<style scoped>
.el-select-dropdown__item {
  color: #606266 !important;
}

.el-select-dropdown__item.selected {
  color: #409EFF !important;
}
</style>
