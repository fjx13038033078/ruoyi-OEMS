<template>
  <div>
    <!-- 公告管理页面内容 -->
    <div>
      <!-- 新增公告按钮 -->
      <el-row :gutter="20" class="mb-20" style="margin-bottom: 10px; margin-left: 10px; margin-top: 10px;">
        <el-col>
          <el-button type="primary"
                     plain
                     icon="el-icon-plus"
                     size="medium"
                     @click="handleAddAnnouncement"
                     v-hasPermi="['oversea:announcement:add']">新增公告
          </el-button>
        </el-col>
      </el-row>

      <!-- 公告列表 -->
      <el-table :data="announcementList" v-loading="loading" style="width: 100%" border>
        <el-table-column label="公告ID" prop="announcementId" align="center"></el-table-column>
        <el-table-column label="公告标题" prop="title" align="center"></el-table-column>
        <el-table-column label="公告内容" prop="content" align="center">
          <template #default="scope">
            <span>{{ scope.row.content.slice(0, 5) }}...</span>
          </template>
        </el-table-column>
        <el-table-column label="境外学校" prop="universityName" align="center"></el-table-column>
        <el-table-column label="发布时间" prop="releaseDate" align="center"></el-table-column>
        <el-table-column label="结束时间" prop="endDate" align="center"></el-table-column>
        <el-table-column label="操作" align="center" width="300px">
          <template #default="{ row }">
            <el-button type="text" icon="el-icon-paperclip" size="mini" @click="handleSubmitApplication(row)" v-hasPermi="['oversea:announcement:apply']">提交申请</el-button> <!-- 新增的按钮 -->
            <el-button type="text" icon="el-icon-view" size="mini" @click="handleView(row)" v-hasPermi="['oversea:announcement:view']">查看</el-button>
            <el-button type="text" icon="el-icon-edit" size="mini" @click="handleEdit(row)" v-hasPermi="['oversea:announcement:update']">编辑</el-button>
            <el-button type="text" icon="el-icon-delete" size="mini" @click="handleDelete(row)" v-hasPermi="['oversea:announcement:delete']">删除</el-button>
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
      @pagination="fetchAnnouncements"
    />

    <!-- 新增/编辑公告对话框 -->
    <el-dialog :visible.sync="dialogVisible" :title="dialogTitle" width="30%" @close="handleCloseDialog">
      <el-form :model="announcementForm" label-width="100px" ref="announcementFormRef" :rules="rules">
        <el-form-item label="公告标题" prop="title">
          <el-input v-model="announcementForm.title" placeholder="请输入公告标题"></el-input>
        </el-form-item>
        <el-form-item label="公告内容" prop="content">
          <el-input type="textarea" v-model="announcementForm.content" placeholder="请输入公告内容"></el-input>
        </el-form-item>
        <el-form-item label="境外高校" prop="oeUniversityId">
          <el-select v-model="announcementForm.oeUniversityId" placeholder="请选择境外高校">
            <el-option
              v-for="item in universities"
              :key="item.universityId"
              :label="item.universityName"
              :value="item.universityId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="开始时间" prop="releaseDate">
          <el-date-picker v-model="announcementForm.releaseDate" type="datetime" placeholder="选择发布时间"></el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endDate">
          <el-date-picker v-model="announcementForm.endDate" type="datetime" placeholder="选择结束时间"></el-date-picker>
        </el-form-item>
        <el-form-item label="附件上传" prop="fileName">
          <FileUpload v-model="announcementForm.fileName"></FileUpload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" style="text-align: center;">
        <el-button @click="handleCloseDialog">取消</el-button>
        <el-button type="primary" @click="handleSubmit">{{ dialogButton }}</el-button>
      </div>
    </el-dialog>

    <!-- 查看公告详情对话框 -->
    <el-dialog :visible.sync="viewDialogVisible" title="公告详情" width="30%" @close="handleCloseViewDialog">
      <el-form :model="viewAnnouncementForm" label-width="100px">
        <el-form-item label="公告标题">
          <el-input v-model="viewAnnouncementForm.title" disabled></el-input>
        </el-form-item>
        <el-form-item label="公告内容">
          <el-input type="textarea" v-model="viewAnnouncementForm.content" disabled></el-input>
        </el-form-item>
        <el-form-item label="境外高校">
          <el-input v-model="viewAnnouncementForm.universityName" disabled></el-input>
        </el-form-item>
        <el-form-item label="发布时间">
          <el-input v-model="viewAnnouncementForm.releaseDate" disabled></el-input>
        </el-form-item>
        <el-form-item label="结束时间">
          <el-input v-model="viewAnnouncementForm.endDate" disabled></el-input>
        </el-form-item>
        <el-form-item label="附件查看">
          <FileUpload v-model="viewAnnouncementForm.fileName" :is-show-tip="false" :disabled="true"></FileUpload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" style="text-align: center;">
        <el-button @click="handleCloseViewDialog">关闭</el-button>
      </div>
    </el-dialog>

    <!-- 提交申请对话框 -->
    <el-dialog :visible.sync="applicationDialogVisible" title="提交申请" width="30%" @close="handleCloseApplicationDialog">
      <el-form :model="applicationForm" label-width="100px" ref="applicationFormRef" :rules="applicationRules">
        <el-form-item label="交流专业" prop="major">
          <el-input v-model="applicationForm.major" placeholder="请输入交流专业"></el-input>
        </el-form-item>
        <el-form-item label="交流学期" prop="exchangeTerm">
          <el-input v-model="applicationForm.exchangeTerm" placeholder="请输入交流学期"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" style="text-align: center;">
        <el-button @click="handleCloseApplicationDialog">取消</el-button>
        <el-button type="primary" @click="handleSubmitApplicationForm">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listAllAnnouncements,
  addAnnouncement,
  updateAnnouncement,
  deleteAnnouncement,
} from "@/api/oversea/announcement";
import {listAllUniversities} from "@/api/oversea/university";
import {addOutboundApplication} from "@/api/oversea/outboundApplication";

export default {
  data() {
    return {
      loading: false,
      announcementList: [],
      total: 0,
      dialogVisible: false,
      dialogTitle: "新增公告",
      dialogButton: "添加",
      universities: [],  // 用于存储高校列表
      announcementForm: {
        title: "",
        content: "",
        releaseDate: "",
        endDate: "",
        fileName: "",
        oeUniversityId: "",
      },
      viewDialogVisible: false,
      viewAnnouncementForm: {},
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
      applicationDialogVisible: false, // 控制提交申请对话框显示
      applicationForm: {
        major: "",// 专业
        exchangeTerm: "", // 交流学期
        universityId: "",
        announcementId: null, // 公告ID
      },
      applicationRules: {
        exchangeTerm: [{ required: true, message: "请输入交流学期", trigger: "blur" }],
        major: [{ required: true, message: "请输入交流专业", trigger: "blur" }],
      },
      rules: {
        title: [{ required: true, message: "请输入公告标题", trigger: "blur" }],
        content: [{ required: true, message: "请输入公告内容", trigger: "blur" }],
        releaseDate: [{ required: true, message: "请选择发布时间", trigger: "change" }],
        endDate: [{ required: true, message: "请选择结束时间", trigger: "change" }],
        oeUniversityId: [{ required: true, message: "请选择境外高校", trigger: "change" }]  // 增加高校选择规则
      }
    };
  },
  created() {
    this.fetchAnnouncements();
    this.fetchUniversities();
  },
  methods: {
    // 获取境外高校列表
    fetchUniversities() {
      listAllUniversities({}).then(response => {
        this.universities = response.rows;  // 假设返回的数据结构是这样的
      }).catch(error => {
        this.$message.error("获取境外高校列表失败");
      });
    },
    // 获取公告列表
    fetchAnnouncements() {
      this.loading = true;
      listAllAnnouncements(this.queryParams).then((response) => {
        this.announcementList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 新增公告
    handleAddAnnouncement() {
      this.dialogTitle = "新增公告";
      this.dialogButton = "添加";
      this.announcementForm = {
        title: "",
        content: "",
        releaseDate: "",
        endDate: "",
        fileName: "",
        oeUniversityId: ""  // 清空选择的高校
      };
      this.dialogVisible = true;
    },
    // 编辑公告
    handleEdit(row) {
      this.dialogTitle = "编辑公告";
      this.dialogButton = "更新";
      this.announcementForm = { ...row };
      this.dialogVisible = true;
    },
    // 删除公告
    handleDelete(row) {
      this.$confirm("确定删除该公告吗？", "提示", { type: "warning" }).then(() => {
        deleteAnnouncement(row.announcementId).then(() => {
          this.$message.success("删除成功");
          this.fetchAnnouncements();
        });
      });
    },
    // 查看公告详情
    handleView(row) {
      this.viewAnnouncementForm = { ...row };
      this.viewDialogVisible = true;
    },
    // 提交表单
    handleSubmit() {
      this.$refs.announcementFormRef.validate(valid => {
        if (valid) {
          if (this.dialogButton === "添加") {
            addAnnouncement(this.announcementForm).then(() => {
              this.$message.success("新增成功");
              this.dialogVisible = false;
              this.fetchAnnouncements();
            });
          } else {
            updateAnnouncement(this.announcementForm).then(() => {
              this.$message.success("更新成功");
              this.dialogVisible = false;
              this.fetchAnnouncements();
            });
          }
        }
      });
    },
    // 关闭查看详情对话框
    handleCloseViewDialog() {
      this.viewDialogVisible = false;
    },
    // 关闭对话框
    handleCloseDialog() {
      this.dialogVisible = false;
      this.announcementForm = {
        title: "",
        content: "",
        releaseDate: "",
        endDate: "",
        fileName: "",
      };
    },
    // 提交申请
    handleSubmitApplication(row) {
      this.applicationForm.announcementId = row.announcementId; // 获取当前公告的ID
      this.applicationForm.universityId = row.oeUniversityId; // 获取当前公告的境外高校ID
      this.applicationForm.major = "";
      this.applicationForm.exchangeTerm = ""; // 清空输入框内容
      this.applicationDialogVisible = true; // 打开对话框
    },
    // 提交申请表单
    handleSubmitApplicationForm() {
      this.$refs.applicationFormRef.validate(valid => {
        if (valid) {
          addOutboundApplication(this.applicationForm).then(() => {
            this.$message.success("申请提交成功");
            this.applicationDialogVisible = false;
          }).catch(() => {
            this.$message.error("提交申请失败");
          });
        }
      });
    },
    // 关闭提交申请对话框
    handleCloseApplicationDialog() {
      this.applicationDialogVisible = false;
      this.applicationForm = { exchangeTerm: "", announcementId: null }; // 重置表单
    },
  },
};
</script>

<style scoped>
/* 样式可以根据需求进一步调整 */
</style>
