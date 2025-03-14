<template>
  <div>
    <!-- 境外学校管理页面内容 -->
    <div>
      <!-- 添加境外学校按钮 -->
      <el-row :gutter="20" class="mb-20" style="margin-bottom: 10px; margin-left: 10px; margin-top: 10px">
        <el-col>
          <el-button type="primary"
                     plain
                     icon="el-icon-plus"
                     size="medium"
                     @click="handleAddUniversity"
                     v-hasPermi="['oversea:university:add']">新增境外学校
          </el-button>
        </el-col>
      </el-row>

      <!-- 境外学校列表 -->
      <el-table :data="universityList" v-loading="loading" style="width: 100%" border>
        <el-table-column label="学校ID" prop="universityId" align="center"></el-table-column>
        <el-table-column label="学校名称" prop="universityName" align="center"></el-table-column>
        <el-table-column label="所在国家" prop="country" align="center"></el-table-column>
        <el-table-column label="操作" align="center" width="410px">
          <template slot-scope="scope">
            <el-button type="text" icon="el-icon-view" size="mini" @click="handleView(scope.row)"
                       v-hasPermi="['oversea:university:view']">查看
            </el-button>
            <el-button type="text" icon="el-icon-edit" size="mini" @click="handleEdit(scope.row)"
                       v-hasPermi="['oversea:university:edit']">编辑
            </el-button>
            <el-button type="text" icon="el-icon-delete" size="mini" @click="handleDelete(scope.row)"
                       v-hasPermi="['oversea:university:delete']">删除
            </el-button>
            <el-button type="text" icon="el-icon-edit" size="mini" @click="handleAddMajor(scope.row)"
                       v-hasPermi="['oversea:university:addMajor']">专业录入
            </el-button>
            <el-button type="text" icon="el-icon-view" size="mini" @click="handleViewMajors(scope.row)"
                       v-hasPermi="['oversea:university:viewMajors']">专业查看
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 分页组件 -->
    <pagination
      v-show="totalUniversities > 0"
      :total="totalUniversities"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="fetchUniversities"
    />

    <!-- 添加/编辑境外学校对话框 -->
    <el-dialog :visible.sync="dialogVisible" :title="dialogTitle" width="30%" @close="handleCloseDialog">
      <!-- 对话框内容 -->
      <div>
        <el-form :model="universityForm" label-width="100px">
          <el-form-item label="学校名称">
            <el-input v-model="universityForm.universityName" :disabled="isReadOnly"></el-input>
          </el-form-item>
          <el-form-item label="所在国家">
            <el-input v-model="universityForm.country" :disabled="isReadOnly"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <!-- 对话框按钮 -->
      <div slot="footer" class="dialog-footer" style="text-align: center;">
        <el-button @click="handleCloseDialog">取消</el-button>
        <el-button type="primary" @click="handleSubmit">{{ dialogButtonText }}</el-button>
      </div>
    </el-dialog>

    <!-- 专业列表对话框 -->
    <el-dialog :visible.sync="majorDialogVisible" title="专业列表" width="40%" @close="handleCloseMajorDialog">
      <el-table :data="majorList" style="width: 100%" border>
        <el-table-column label="专业ID" prop="majorId" width="150" align="center"></el-table-column>
        <el-table-column label="专业名称" prop="majorName" align="center"></el-table-column>
        <el-table-column label="操作" align="center" width="150">
          <template slot-scope="scope">
            <el-button type="text" icon="el-icon-delete" size="mini" @click="handleDeleteMajor(scope.row)"
                       v-hasPermi="['oversea:major:delete']">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer" style="text-align: center;">
        <el-button @click="handleCloseMajorDialog">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAllUniversities, addUniversity, updateUniversity, deleteUniversity, getUniversityById } from '@/api/oversea/university'
import { addMajor, listMajorsByUniversityId, deleteMajor } from '@/api/oversea/major'

export default {
  data() {
    return {
      loading: false,
      universityList: [],
      dialogVisible: false,
      dialogTitle: '',
      dialogButtonText: '',
      totalUniversities: 0,
      isReadOnly: false, // 是否只读模式
      universityForm: {
        universityName: '',
        country: ''
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
      majorDialogVisible: false,
      majorList: []
    }
  },
  created() {
    this.fetchUniversities()
  },
  methods: {
    fetchUniversities() {
      this.loading = true
      listAllUniversities(this.queryParams).then(response => {
        this.universityList = response.rows
        this.totalUniversities = response.total
        this.loading = false
      })
    },
    handleView(row) {
      this.dialogTitle = '查看境外学校'
      this.dialogButtonText = '关闭'
      this.isReadOnly = true
      getUniversityById(row.universityId).then(response => {
        this.universityForm = response.data
        this.dialogVisible = true
      })
    },
    handleAddUniversity() {
      this.isReadOnly = false
      this.dialogTitle = '新增境外学校'
      this.dialogButtonText = '添加'
      this.dialogVisible = true
    },
    handleEdit(row) {
      this.isReadOnly = false
      this.dialogTitle = '编辑境外学校'
      this.dialogButtonText = '更新'
      this.universityForm = Object.assign({}, row)
      this.dialogVisible = true
    },
    handleDelete(row) {
      this.$confirm('确认删除该境外学校吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteUniversity(row.universityId).then(() => {
          this.$message.success('删除成功')
          this.fetchUniversities()
        })
      })
    },
    handleCloseDialog() {
      this.dialogVisible = false
      this.clearForm()
    },
    clearForm() {
      this.universityForm = {
        universityName: '',
        country: ''
      }
    },
    handleSubmit() {
      if (this.dialogButtonText === '添加') {
        addUniversity(this.universityForm).then(() => {
          this.$message.success('添加成功')
          this.dialogVisible = false
          this.clearForm()
          this.fetchUniversities()
        })
      } else if (this.dialogButtonText === '更新') {
        updateUniversity(this.universityForm).then(() => {
          this.$message.success('更新成功')
          this.dialogVisible = false
          this.clearForm()
          this.fetchUniversities()
        })
      }
    },
    handleAddMajor(row) {
      this.$prompt('请输入专业名称', '专业录入', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /.+/,
        inputErrorMessage: '专业名称不能为空'
      }).then(({ value }) => {
        addMajor({ universityId: row.universityId, majorName: value }).then(() => {
          this.$message.success('专业录入成功')
        }).catch(() => {
          this.$message.error('专业录入失败')
        })
      }).catch(() => {
        this.$message.info('已取消录入')
      })
    },
    handleViewMajors(row) {
      listMajorsByUniversityId(row.universityId).then(response => {
        this.majorList = response.data
        this.majorDialogVisible = true
      })
    },
    handleCloseMajorDialog() {
      this.majorDialogVisible = false
      this.majorList = []
    },
    handleDeleteMajor(row) {
      this.$confirm('确认删除该专业吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteMajor(row.majorId).then(() => {
          this.$message.success('删除成功')
          // 重新获取专业列表
          listMajorsByUniversityId(row.universityId).then(response => {
            this.majorList = response.data
          })
        })
      })
    }
  }
}
</script>

<style scoped>
/* 样式可以根据需求调整 */
</style>
