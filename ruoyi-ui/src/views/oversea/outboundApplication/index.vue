<template>
  <div>
    <!-- 出境申请列表 -->
    <el-table :data="applicationList" v-loading="loading" style="width: 100%" border>
      <el-table-column label="申请ID" prop="applicationId" align="center"></el-table-column>
      <el-table-column label="学生姓名" prop="userName" align="center"></el-table-column>
      <el-table-column label="公告标题" prop="announcementTitle" align="center"></el-table-column>
      <el-table-column label="申请学校" prop="universityName" align="center"></el-table-column>
      <el-table-column label="申请专业" prop="major" align="center"></el-table-column>
      <el-table-column label="申请学期" prop="exchangeTerm" align="center"></el-table-column>
      <el-table-column label="申请日期" prop="applicationDate" align="center"></el-table-column>
      <el-table-column label="学院审核结果" align="center">
        <template #default="scope">
          <el-tag
            :type="scope.row.collegeReviewResult === 0 ? 'warning' : scope.row.collegeReviewResult === 1 ? 'success' : 'danger'">
            {{
              scope.row.collegeReviewResult === 0 ? '未审核' : scope.row.collegeReviewResult === 1 ? '已审核' : '已拒绝'
            }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="学校审核结果" align="center">
        <template #default="scope">
          <el-tag
            :type="scope.row.universityReviewResult === 0 ? 'warning' : scope.row.universityReviewResult === 1 ? 'success' : 'danger'">
            {{
              scope.row.universityReviewResult === 0 ? '未审核' : scope.row.universityReviewResult === 1 ? '已审核' : '已拒绝'
            }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="200px">
        <template #default="{ row }">
          <el-button type="text" icon="el-icon-view" size="mini" @click="handleView(row)"
                     v-hasPermi="['oversea:outbound:view']">查看
          </el-button>
          <el-button type="text" icon="el-icon-delete" size="mini" @click="handleDelete(row)"
                     v-hasPermi="['oversea:outbound:delete']">删除
          </el-button>
          <!-- 根据学院审核结果动态显示按钮 -->
          <el-button v-if="row.collegeReviewResult === 0" type="text" icon="el-icon-check" size="mini"
                     @click="handleCollegeReview(row)" v-hasPermi="['oversea:outbound:SAudit']">学院审核
          </el-button>
          <el-button v-if="row.collegeReviewResult === 1 && row.universityReviewResult === 0" type="text"
                     icon="el-icon-check" size="mini"
                     @click="handleUniversityReview(row)" v-hasPermi="['oversea:outbound:UAudit']">学校审核
          </el-button>
          <el-button v-if="row.collegeReviewResult === 1 && row.universityReviewResult === 1" type="text"
                     icon="el-icon-refresh" size="mini" @click="handleReinstatementApplication(row)"
                     v-hasPermi="['oversea:outbound:apply']">复学申请
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
      @pagination="fetchApplications"
    />

    <!-- 查看申请详情对话框 -->
    <el-dialog :visible.sync="viewDialogVisible" title="查看申请详情" width="30%" @close="handleCloseViewDialog">
      <el-form :model="viewApplicationForm" label-width="100px">
        <el-form-item label="学生姓名">
          <el-input v-model="viewApplicationForm.userName" disabled></el-input>
        </el-form-item>
        <el-form-item label="公告标题">
          <el-input v-model="viewApplicationForm.announcementTitle" disabled></el-input>
        </el-form-item>
        <el-form-item label="交流学校">
          <el-input v-model="viewApplicationForm.universityName" disabled></el-input>
        </el-form-item>
        <el-form-item label="交流专业">
          <el-input v-model="viewApplicationForm.major" disabled></el-input>
        </el-form-item>
        <el-form-item label="交流学期">
          <el-input v-model="viewApplicationForm.exchangeTerm" disabled></el-input>
        </el-form-item>
        <el-form-item label="申请日期">
          <el-input v-model="viewApplicationForm.applicationDate" disabled></el-input>
        </el-form-item>
        <el-form-item label="学院审核意见">
          <el-input v-model="viewApplicationForm.collegeReviewComments" disabled></el-input>
        </el-form-item>
        <el-form-item label="学院审核时间">
          <el-input v-model="viewApplicationForm.collegeReviewDate" disabled></el-input>
        </el-form-item>
        <el-form-item label="学院审核结果">
          <el-input
            :value="viewApplicationForm.collegeReviewResult === 0 ? '未审核' : viewApplicationForm.collegeReviewResult === 1 ? '通过' : '拒绝'"
            disabled></el-input>
        </el-form-item>
        <el-form-item label="学院审核人">
          <el-input v-model="viewApplicationForm.collegeReviewerName" disabled></el-input>
        </el-form-item>
        <el-form-item label="学校审核意见">
          <el-input v-model="viewApplicationForm.universityReviewComments" disabled></el-input>
        </el-form-item>
        <el-form-item label="学校审核时间">
          <el-input v-model="viewApplicationForm.universityReviewDate" disabled></el-input>
        </el-form-item>
        <el-form-item label="学校审核结果">
          <el-input
            :value="viewApplicationForm.universityReviewResult === 0 ? '未审核' : viewApplicationForm.universityReviewResult === 1 ? '通过' : '拒绝'"
            disabled></el-input>
        </el-form-item>
        <el-form-item label="学校审核人">
          <el-input v-model="viewApplicationForm.universityReviewerName" disabled></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" style="text-align: center;">
        <el-button @click="handleCloseViewDialog">关闭</el-button>
      </div>
    </el-dialog>

    <!-- 学院审核对话框 -->
    <el-dialog :visible.sync="collegeReviewDialogVisible" title="学院审核" width="30%"
               @close="handleCloseCollegeReviewDialog">
      <el-form :model="collegeReviewForm" label-width="100px">
        <el-form-item label="审核意见">
          <el-input v-model="collegeReviewForm.collegeReviewComments" type="textarea"
                    placeholder="请输入学院审核意见"></el-input>
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
    <el-dialog :visible.sync="universityReviewDialogVisible" title="学校审核" width="30%"
               @close="handleCloseUniversityReviewDialog">
      <el-form :model="universityReviewForm" label-width="100px">
        <el-form-item label="审核意见">
          <el-input v-model="universityReviewForm.universityReviewComments" type="textarea"
                    placeholder="请输入学校审核意见"></el-input>
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

    <!-- 复学申请 Dialog -->
    <el-dialog :visible.sync="reinstatementDialogVisible" title="复学申请" @close="handleCloseReinstatementDialog">
      <el-form :model="reinstatementForm" label-width="80px">
        <el-form-item label="复学学期">
          <el-input v-model="reinstatementForm.exchangeTerm" placeholder="请输入复学学期"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" style="text-align: center;">
        <el-button @click="reinstatementDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitReinstatementApplication">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listAllOutboundApplications,
  deleteOutboundApplication,
  updateCollegeReview,
  updateUniversityReview
} from "@/api/oversea/outboundApplication";
import {addReinstatementApplication} from "@/api/oversea/reinstatementApplication";

export default {
  data() {
    return {
      loading: false,
      applicationList: [],
      total: 0,
      dialogVisible: false,
      viewDialogVisible: false,
      collegeReviewDialogVisible: false,
      universityReviewDialogVisible: false,
      reinstatementDialogVisible: false, // 控制复学申请 Dialog 的显示与隐藏
      applicationForm: {
        major: "",
        exchangeTerm: "",
        announcementId: "",
      },
      viewApplicationForm: {},
      collegeReviewForm: {
        applicationId: "",
        collegeReviewComments: "",
        collegeReviewResult: 1, // 默认通过
      },
      universityReviewForm: {
        applicationId: "",
        universityReviewComments: "",
        universityReviewResult: 1, // 默认通过
      },
      reinstatementForm: {
        exchangeTerm: '', // 复学学期
        outboundApplicationId: null // 当前行的 outboundApplicationId
      },
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
    };
  },
  created() {
    this.fetchApplications();
  },
  methods: {
    fetchApplications() {
      this.loading = true;
      listAllOutboundApplications(this.queryParams).then((response) => {
        this.applicationList = response.rows;
        this.total = response.total;
        this.loading = false;
      }).catch(() => {
        this.loading = false;
      });
    },
    handleView(row) {
      this.viewApplicationForm = {...row};
      this.viewDialogVisible = true;
    },
    handleDelete(row) {
      this.$confirm("确定删除该出境申请吗？", "提示", {type: "warning"}).then(() => {
        deleteOutboundApplication(row.applicationId).then(() => {
          this.$message.success("删除成功");
          this.fetchApplications();
        }).catch(() => {
          this.$message.error("删除失败");
        });
      });
    },
    handleCollegeReview(row) {
      this.collegeReviewForm = {
        applicationId: row.applicationId,
        collegeReviewComments: row.collegeReviewComments,
        collegeReviewResult: row.collegeReviewResult
      };
      this.collegeReviewDialogVisible = true;
    },
    handleUniversityReview(row) {
      this.universityReviewForm = {
        applicationId: row.applicationId,
        universityReviewComments: row.universityReviewComments,
        universityReviewResult: row.universityReviewResult
      };
      this.universityReviewDialogVisible = true;
    },
    handleCloseCollegeReviewDialog() {
      this.collegeReviewForm = {
        applicationId: "",
        collegeReviewComments: "",
        collegeReviewResult: 1, // 默认通过
      };
      this.collegeReviewDialogVisible = false;
    },
    handleCloseUniversityReviewDialog() {
      this.universityReviewForm = {
        applicationId: "",
        universityReviewComments: "",
        universityReviewResult: 1, // 默认通过
      };
      this.universityReviewDialogVisible = false;
    },
    handleSubmitCollegeReview() {
      // 提交学院审核
      updateCollegeReview(this.collegeReviewForm).then(() => {
        this.$message.success("学院审核成功");
        this.fetchApplications();
        this.handleCloseCollegeReviewDialog();
      }).catch(() => {
        this.$message.error("学院审核失败");
      });
    },
    handleSubmitUniversityReview() {
      // 提交学校审核
      updateUniversityReview(this.universityReviewForm).then(() => {
        this.$message.success("学校审核成功");
        this.fetchApplications();
        this.handleCloseUniversityReviewDialog();
      }).catch(() => {
        this.$message.error("学校审核失败");
      });
    },
    // 显示复学申请 Dialog
    handleReinstatementApplication(row) {
      this.reinstatementForm.outboundApplicationId = row.applicationId; // 设置当前行的 outboundApplicationId
      this.reinstatementDialogVisible = true; // 显示复学申请 Dialog
    },
    // 提交复学申请
    async submitReinstatementApplication() {
      try {
        addReinstatementApplication(this.reinstatementForm);
        this.$message.success('复学申请提交成功');
        this.reinstatementDialogVisible = false; // 关闭对话框
        this.handleCloseReinstatementDialog();
      } catch (error) {
        this.$message.error('复学申请提交失败');
      }
    },
    // 重置表单
    handleCloseReinstatementDialog() {
      this.reinstatementForm.exchangeTerm = '';
      this.reinstatementForm.outboundApplicationId = null;
    },
  },
};
</script>

<style scoped>
</style>
