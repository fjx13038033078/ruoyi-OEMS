<template>
  <div>
    <!-- 学分置换申请列表 -->
    <el-table :data="exchangeApplicationList" v-loading="loading" style="width: 100%" border>
      <el-table-column label="申请ID" prop="applicationId" align="center"></el-table-column>
      <el-table-column label="学生姓名" prop="userName" align="center"></el-table-column>
      <el-table-column label="申请日期" prop="applicationDate" align="center"></el-table-column>
      <el-table-column label="境外课程" prop="overseasCourseName" align="center"></el-table-column>
      <el-table-column label="境外课程学分" prop="overseasCourseCredits" align="center"></el-table-column>
      <el-table-column label="校内课程" prop="domesticCourseName" align="center"></el-table-column>
      <el-table-column label="校内课程学分" prop="domesticCourseCredits" align="center"></el-table-column>
      <el-table-column label="学院审核结果" align="center">
        <template #default="scope">
          <el-tag
            :type="scope.row.collegeReviewResult === 0 ? 'warning' : scope.row.collegeReviewResult === 1 ? 'success' : 'danger'">
            {{
              scope.row.collegeReviewResult === 0 ? '未审核' : scope.row.collegeReviewResult === 1 ? '通过' : '拒绝'
            }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="学校审核结果" align="center">
        <template #default="scope">
          <el-tag
            :type="scope.row.universityReviewResult === 0 ? 'warning' : scope.row.universityReviewResult === 1 ? 'success' : 'danger'">
            {{
              scope.row.universityReviewResult === 0 ? '未审核' : scope.row.universityReviewResult === 1 ? '通过' : '拒绝'
            }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="250px">
        <template #default="{ row }">
          <el-button type="text" icon="el-icon-view" size="mini" @click="handleView(row)" v-hasPermi="['oversea:exchange:view']">查看</el-button>
          <el-button type="text" icon="el-icon-delete" size="mini" @click="handleDelete(row)" v-hasPermi="['oversea:exchange:delete']">删除</el-button>
          <el-button v-if="row.collegeReviewResult === 0" type="text" icon="el-icon-check" size="mini"
                     @click="handleCollegeReview(row)" v-hasPermi="['oversea:exchange:SUAudit']">学院审核
          </el-button>
          <el-button v-if="row.collegeReviewResult === 1 && row.universityReviewResult === 0" type="text"
                     icon="el-icon-check" size="mini" @click="handleUniversityReview(row)" v-hasPermi="['oversea:exchange:UAudit']">学校审核
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="fetchExchangeApplications"
    />

    <!-- 查看学分置换申请详情对话框 -->
    <el-dialog :visible.sync="viewDialogVisible" title="查看学分置换申请详情" width="40%" @close="handleCloseViewDialog">
      <el-form :model="viewExchangeApplicationForm" label-width="100px">
        <el-form-item label="学生姓名">
          <el-input v-model="viewExchangeApplicationForm.userName" disabled></el-input>
        </el-form-item>
        <el-form-item label="申请日期">
          <el-input v-model="viewExchangeApplicationForm.applicationDate" disabled></el-input>
        </el-form-item>
        <el-form-item label="境外课程">
          <el-input v-model="viewExchangeApplicationForm.overseasCourseName" disabled></el-input>
        </el-form-item>
        <el-form-item label="境外课程学分">
          <el-input v-model="viewExchangeApplicationForm.overseasCourseCredits" disabled></el-input>
        </el-form-item>
        <el-form-item label="校内课程">
          <el-input v-model="viewExchangeApplicationForm.domesticCourseName" disabled></el-input>
        </el-form-item>
        <el-form-item label="校内课程学分">
          <el-input v-model="viewExchangeApplicationForm.domesticCourseCredits" disabled></el-input>
        </el-form-item>
        <el-form-item label="学院审核意见">
          <el-input v-model="viewExchangeApplicationForm.collegeReviewComments" disabled></el-input>
        </el-form-item>
        <el-form-item label="学院审核日期">
          <el-input v-model="viewExchangeApplicationForm.collegeReviewDate" disabled></el-input>
        </el-form-item>
        <el-form-item label="学院审核结果">
          <el-input
            :value="viewExchangeApplicationForm.collegeReviewResult === 0 ? '未审核' : viewExchangeApplicationForm.collegeReviewResult === 1 ? '通过' : '拒绝'"
            disabled></el-input>
        </el-form-item>
        <el-form-item label="学院审核人">
          <el-input v-model="viewExchangeApplicationForm.collegeReviewerName" disabled></el-input>
        </el-form-item>
        <el-form-item label="学校审核意见">
          <el-input v-model="viewExchangeApplicationForm.universityReviewComments" disabled></el-input>
        </el-form-item>
        <el-form-item label="学校审核日期">
          <el-input v-model="viewExchangeApplicationForm.universityReviewDate" disabled></el-input>
        </el-form-item>
        <el-form-item label="学校审核结果">
          <el-input
            :value="viewExchangeApplicationForm.universityReviewResult === 0 ? '未审核' : viewExchangeApplicationForm.universityReviewResult === 1 ? '通过' : '拒绝'"
            disabled></el-input>
        </el-form-item>
        <el-form-item label="学校审核人">
          <el-input v-model="viewExchangeApplicationForm.universityReviewerName" disabled></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" style="text-align: center;">
        <el-button @click="handleCloseViewDialog">关闭</el-button>
      </div>
    </el-dialog>

    <!-- 学院审核对话框 -->
    <el-dialog :visible.sync="collegeReviewDialogVisible" title="学院审核" width="40%" @close="handleCloseCollegeReviewDialog">
      <el-form :model="collegeReviewForm" label-width="100px">
        <el-form-item label="审核意见">
          <el-input v-model="collegeReviewForm.collegeReviewComments" type="textarea" placeholder="请输入学院审核意见"></el-input>
        </el-form-item>
        <el-form-item label="审核结果">
          <el-radio-group v-model="collegeReviewForm.collegeReviewResult">
            <el-radio label="1">通过</el-radio>
            <el-radio label="2">拒绝</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" style="text-align: center;">
        <el-button @click="handleCloseCollegeReviewDialog">取消</el-button>
        <el-button type="primary" @click="handleSubmitCollegeReview">提交</el-button>
      </div>
    </el-dialog>

    <!-- 学校审核对话框 -->
    <el-dialog :visible.sync="universityReviewDialogVisible" title="学校审核" width="40%" @close="handleCloseUniversityReviewDialog">
      <el-form :model="universityReviewForm" label-width="100px">
        <el-form-item label="审核意见">
          <el-input v-model="universityReviewForm.universityReviewComments" type="textarea" placeholder="请输入学校审核意见"></el-input>
        </el-form-item>
        <el-form-item label="审核结果">
          <el-radio-group v-model="universityReviewForm.universityReviewResult">
            <el-radio label="1">通过</el-radio>
            <el-radio label="2">拒绝</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" style="text-align: center;">
        <el-button @click="handleCloseUniversityReviewDialog">取消</el-button>
        <el-button type="primary" @click="handleSubmitUniversityReview">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listAllExchangeApplications,
  deleteExchangeApplication,
  updateCollegeReview,
  updateUniversityReview
} from '@/api/oversea/exchangeApplication';

export default {
  data() {
    return {
      loading: false,
      exchangeApplicationList: [],
      total: 0,
      viewDialogVisible: false,
      collegeReviewDialogVisible: false,
      universityReviewDialogVisible: false,
      viewExchangeApplicationForm: {},
      collegeReviewForm: {
        applicationId: "",
        collegeReviewComments: "",
        collegeReviewResult: 0
      },
      universityReviewForm: {
        applicationId: "",
        universityReviewComments: "",
        universityReviewResult: 0
      },
      queryParams: {
        pageNum: 1,
        pageSize: 10
      }
    };
  },
  created() {
    this.fetchExchangeApplications();
  },
  methods: {
    fetchExchangeApplications() {
      this.loading = true;
      listAllExchangeApplications(this.queryParams).then(response => {
        this.exchangeApplicationList = response.rows;
        this.total = response.total;
      }).finally(() => {
        this.loading = false;
      });
    },
    handleView(row) {
      this.viewExchangeApplicationForm = { ...row };
      this.viewDialogVisible = true;
    },
    handleDelete(row) {
      this.$confirm('确定删除该申请吗?', '删除确认', { type: 'warning' })
        .then(() => {
          deleteExchangeApplication(row.applicationId).then(() => {
            this.$message.success('删除成功');
            this.fetchExchangeApplications();
          });
        });
    },
    handleCollegeReview(row) {
      this.collegeReviewForm.applicationId = row.applicationId;
      this.collegeReviewForm.collegeReviewComments = row.collegeReviewComments || '';
      this.collegeReviewForm.collegeReviewResult = row.collegeReviewResult;
      this.collegeReviewDialogVisible = true;
    },
    handleUniversityReview(row) {
      this.universityReviewForm.applicationId = row.applicationId;
      this.universityReviewForm.universityReviewComments = row.universityReviewComments || '';
      this.universityReviewForm.universityReviewResult = row.universityReviewResult;
      this.universityReviewDialogVisible = true;
    },
    handleCloseViewDialog() {
      this.viewDialogVisible = false;
    },
    handleCloseCollegeReviewDialog() {
      this.collegeReviewDialogVisible = false;
    },
    handleCloseUniversityReviewDialog() {
      this.universityReviewDialogVisible = false;
    },
    handleSubmitCollegeReview() {
      updateCollegeReview(this.collegeReviewForm).then(() => {
        this.$message.success('学院审核提交成功');
        this.collegeReviewDialogVisible = false;
        this.fetchExchangeApplications();
      });
    },
    handleSubmitUniversityReview() {
      updateUniversityReview(this.universityReviewForm).then(() => {
        this.$message.success('学校审核提交成功');
        this.universityReviewDialogVisible = false;
        this.fetchExchangeApplications();
      });
    },
  }
};
</script>
