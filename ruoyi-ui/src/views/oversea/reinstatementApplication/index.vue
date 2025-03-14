<template>
  <div>
    <!-- 复学申请列表 -->
    <el-table :data="applicationList" v-loading="loading" style="width: 100%" border>
      <el-table-column label="申请ID" prop="applicationId" align="center"></el-table-column>
      <el-table-column label="学生姓名" prop="userName" align="center"></el-table-column>
      <el-table-column label="申请学期" prop="exchangeTerm" align="center"></el-table-column>
      <el-table-column label="申请日期" prop="reinstatementDate" align="center"></el-table-column>
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
          <el-button type="text" icon="el-icon-view" size="mini" @click="handleView(row)">查看</el-button>
          <el-button type="text" icon="el-icon-delete" size="mini" @click="handleDelete(row)">删除</el-button>
          <el-button v-if="row.collegeReviewResult === 0" type="text" icon="el-icon-check" size="mini"
                     @click="handleCollegeReview(row)">学院审核
          </el-button>
          <el-button v-if="row.collegeReviewResult === 1 && row.universityReviewResult === 0" type="text"
                     icon="el-icon-check" size="mini" @click="handleUniversityReview(row)">学校审核
          </el-button>
          <el-button v-if="row.collegeReviewResult === 1 && row.universityReviewResult === 1" type="text"
                     icon="el-icon-edit" size="mini" @click="handleCreditExchangeRequest(row)">学分置换申请
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
    <el-dialog :visible.sync="viewDialogVisible" title="查看复学申请详情" width="30%" @close="handleCloseViewDialog">
      <el-form :model="viewApplicationForm" label-width="100px">
        <el-form-item label="学生姓名">
          <el-input v-model="viewApplicationForm.userName" disabled></el-input>
        </el-form-item>
        <el-form-item label="申请学期">
          <el-input v-model="viewApplicationForm.exchangeTerm" disabled></el-input>
        </el-form-item>
        <el-form-item label="申请日期">
          <el-input v-model="viewApplicationForm.reinstatementDate" disabled></el-input>
        </el-form-item>
        <el-form-item label="学院审核意见">
          <el-input v-model="viewApplicationForm.collegeReviewComments" disabled></el-input>
        </el-form-item>
        <el-form-item label="学院审核日期">
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
        <el-form-item label="学校审核日期">
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

    <!-- 学分置换申请对话框 -->
    <el-dialog :visible.sync="creditExchangeDialogVisible" title="学分置换申请" width="40%" @close="handleCloseCreditExchangeDialog">
      <el-form :model="creditExchangeForm" label-width="100px">
        <el-form-item label="境外课程" prop="overseasCourseId">
          <el-select v-model="creditExchangeForm.overseasCourseId" placeholder="请选择境外课程" style="width: 100%">
            <el-option
              v-for="course in overseasCourseList"
              :key="course.courseId"
              :label="course.courseName"
              :value="course.courseId">
              <span>{{ course.courseName }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ course.credit }}学分</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="校内课程名">
          <el-input v-model="creditExchangeForm.domesticCourseName" placeholder="请输入校内课程名"></el-input>
        </el-form-item>
        <el-form-item label="校内课程学分">
          <el-input v-model="creditExchangeForm.domesticCourseCredits" placeholder="请输入校内课程学分"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" style="text-align: center;">
        <el-button @click="handleCloseCreditExchangeDialog">取消</el-button>
        <el-button type="primary" @click="handleSubmitCreditExchange">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listAllReinstatementApplications,
  deleteReinstatementApplication,
  updateCollegeReview,
  updateUniversityReview,
} from '@/api/oversea/reinstatementApplication';
import { addExchangeApplication } from "@/api/oversea/exchangeApplication";
import { getCourseByRApplicationId } from "@/api/oversea/oeCourse";

export default {
  data() {
    return {
      loading: false,
      applicationList: [],
      total: 0,
      viewDialogVisible: false,
      collegeReviewDialogVisible: false,
      universityReviewDialogVisible: false,
      creditExchangeDialogVisible: false, // 控制学分置换对话框显示
      viewApplicationForm: {},
      collegeReviewForm: {
        applicationId: "",
        collegeReviewComments: "",
        collegeReviewResult: 1
      },
      universityReviewForm: {
        applicationId: "",
        universityReviewComments: "",
        universityReviewResult: 1
      },
      overseasCourseList: [], // 境外课程列表
      creditExchangeForm: {
        reinstatementApplicationId: "",
        overseasCourseId: undefined,
        domesticCourseName: "",
        domesticCourseCredits: ""
      },
      queryParams: {
        pageNum: 1,
        pageSize: 10
      }
    };
  },
  created() {
    this.fetchApplications();
  },
  methods: {
    fetchApplications() {
      this.loading = true;
      listAllReinstatementApplications(this.queryParams).then(response => {
        this.applicationList = response.rows;
        this.total = response.total;
      })
        .finally(() => {
          this.loading = false;
        });
    },
    handleView(row) {
      this.viewApplicationForm = {...row};
      this.viewDialogVisible = true;
    },
    handleDelete(row) {
      this.$confirm('确定删除该申请吗?', '删除确认', {
        type: 'warning'
      }).then(() => {
        deleteReinstatementApplication(row.applicationId)
          .then(() => {
            this.$message.success('删除成功');
            this.fetchApplications();
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
      updateCollegeReview(this.collegeReviewForm)
        .then(() => {
          this.$message.success('学院审核提交成功');
          this.collegeReviewDialogVisible = false;
          this.fetchApplications();
        });
    },
    handleSubmitUniversityReview() {
      updateUniversityReview(this.universityReviewForm)
        .then(() => {
          this.$message.success('学校审核提交成功');
          this.universityReviewDialogVisible = false;
          this.fetchApplications();
        });
    },
    // 打开学分置换申请对话框
    handleCreditExchangeRequest(row) {
      this.creditExchangeForm.reinstatementApplicationId = row.applicationId;
      // 获取该申请对应的境外课程列表
      getCourseByRApplicationId(row.applicationId).then(response => {
        this.overseasCourseList = response.data;
      });
      this.creditExchangeForm.overseasCourseId = undefined;
      this.creditExchangeForm.domesticCourseName = "";
      this.creditExchangeForm.domesticCourseCredits = "";
      this.creditExchangeDialogVisible = true;
    },

    // 关闭学分置换对话框
    handleCloseCreditExchangeDialog() {
      this.creditExchangeForm = {
        reinstatementApplicationId: "",
        overseasCourseId: undefined,
        domesticCourseName: "",
        domesticCourseCredits: ""
      };
      this.overseasCourseList = [];
      this.creditExchangeDialogVisible = false;
    },

    // 提交学分置换申请
    handleSubmitCreditExchange() {
      addExchangeApplication( this.creditExchangeForm)
        .then(() => {
          this.$message.success('学分置换申请提交成功');
          this.creditExchangeDialogVisible = false;
          this.fetchApplications();  // 更新数据
        });
    },
  },
};
</script>
