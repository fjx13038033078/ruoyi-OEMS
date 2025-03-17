<template>
  <div>
    <!-- 校内专业管理页面内容 -->
    <div>
      <!-- 添加校内专业按钮 -->
      <el-row :gutter="20" class="mb-20" style="margin-bottom: 10px; margin-left: 10px; margin-top: 10px">
        <el-col>
          <el-button type="primary"
                     plain
                     icon="el-icon-plus"
                     size="medium"
                     @click="handleAddMajor"
                     v-hasPermi="['oversea:inMajor:add']">新增校内专业
          </el-button>
        </el-col>
      </el-row>

      <!-- 校内专业列表 -->
      <el-table :data="majorList" v-loading="loading" style="width: 100%" border>
        <el-table-column label="专业ID" prop="id" align="center"></el-table-column>
        <el-table-column label="专业名称" prop="majorName" align="center"></el-table-column>
        <el-table-column label="操作" align="center" width="200">
          <template slot-scope="scope">
            <el-button type="text" icon="el-icon-edit" size="mini" @click="handleEdit(scope.row)"
                       v-hasPermi="['oversea:inMajor:edit']">修改
            </el-button>
            <el-button type="text" icon="el-icon-delete" size="mini" @click="handleDelete(scope.row)"
                       v-hasPermi="['oversea:inMajor:delete']">删除
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
      @pagination="fetchMajors"
    />

    <!-- 添加/编辑校内专业对话框 -->
    <el-dialog :visible.sync="dialogVisible" :title="dialogTitle" width="40%" @close="handleCloseDialog">
      <div>
        <el-form :model="majorForm" label-width="100px" :rules="rules" ref="majorForm">
          <el-form-item label="专业名称" prop="majorName">
            <el-input v-model="majorForm.majorName" placeholder="请输入专业名称"></el-input>
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
import { listAllMajors, addMajor, updateMajor, deleteMajor } from '@/api/oversea/oeInMajor'


export default {
  name: 'OeInMajor',
  data() {
    return {
      loading: false,
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
      majorForm: {
        id: undefined,
        majorName: '',
      },
      // 表单验证规则
      rules: {
        majorName: [
          { required: true, message: '请输入专业名称', trigger: 'blur' }
        ],
      }
    }
  },
  created() {
    this.fetchMajors()
  },
  methods: {
    // 获取专业列表
    fetchMajors() {
      this.loading = true
      listAllMajors(this.queryParams).then(response => {
        this.majorList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 添加专业
    handleAddMajor() {
      this.dialogTitle = '新增校内专业'
      this.dialogButtonText = '添加'
      this.majorForm = {
        id: undefined,
        majorName: '',
      }
      this.dialogVisible = true
    },
    // 修改专业
    handleEdit(row) {
      this.dialogTitle = '修改校内专业'
      this.dialogButtonText = '修改'
      this.majorForm = Object.assign({}, row)
      this.dialogVisible = true
    },
    // 删除专业
    handleDelete(row) {
      this.$confirm('确认删除该专业吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteMajor(row.id).then(() => {
          this.$message.success('删除成功')
          this.fetchMajors()
        })
      })
    },
    // 关闭对话框
    handleCloseDialog() {
      this.dialogVisible = false
      if (this.$refs.majorForm) {
        this.$refs.majorForm.resetFields()
      }
    },
    // 提交表单
    handleSubmit() {
      this.$refs.majorForm.validate(valid => {
        if (valid) {
          if (this.dialogButtonText === '添加') {
            addMajor(this.majorForm).then(() => {
              this.$message.success('添加成功')
              this.dialogVisible = false
              this.fetchMajors()
            })
          } else if (this.dialogButtonText === '修改') {
            updateMajor(this.majorForm).then(() => {
              this.$message.success('修改成功')
              this.dialogVisible = false
              this.fetchMajors()
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
